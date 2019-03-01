package Server;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Interfaces.ILoginQueris;
import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;

/**
 * User Login queries.
 * 
 * @author NivPC
 *
 */
public class LoginQueris implements ILoginQueris {
	private String userName;
	private String Password;

	private static final String getUserLevel = "SELECT obl.user.Level\r\n" + "FROM obl.user\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getSubscriberInformation = "SELECT obl.user.ID, obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.PhoneNumber,obl.user.Password, obl.user.Level, obl.subscriber.SubscriberID, obl.subscriber.Status, obl.subscriber.FelonyNumber\r\n"
			+ "FROM obl.user\r\n" + "INNER JOIN obl.subscriber ON obl.user.ID=subscriber.ID\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getLibrarianInformation = "SELECT obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.ID, obl.user.Password, obl.user.PhoneNumber, obl.user.Level, obl.librarian.Affiliation  \r\n"
			+ "FROM obl.user\r\n" + "INNER JOIN obl.librarian ON obl.user.ID=obl.librarian.ID\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String ANDPassword = "' AND obl.user.Password = '";
	private static final String getUserLoans = "SELECT * FROM obl.loan WHERE SubscriberID = '";
	private static final String getUserOrders = "SELECT * FROM obl.order WHERE SubscriberID = '";
	private static final String getBookNameAndAuthor = "SELECT obl.book.BookName, obl.book.AuthorName FROM obl.book WHERE obl.book.CatalogNumber = '";
	private static final String Endquery = "';";

	public LoginQueris(String name, String pass) {
		userName = name;
		Password = pass;
	}

	/**
	 * 
	 * @param Login
	 * @return Server Data with the user information or Error or Exception.
	 */
	@SuppressWarnings("resource")
	public ServerData Login(LoginQueris Login) {

		try {
			Statement s = mysqlConnection.conn.createStatement();
			ResultSet Res = s.executeQuery(getUserLevel + Login.userName + ANDPassword + Login.Password + Endquery);
			while (Res.next()) {
				if (Res.getString(1).equals("0")) {
					Res = s.executeQuery(
							getSubscriberInformation + Login.userName + ANDPassword + Login.Password + Endquery);
					while (Res.next()) {
						Subscriber Sub = new Subscriber(Res.getString(1), Res.getString(2), Res.getString(3),
								Res.getString(4), Res.getString(5), Res.getString(6), Res.getString(8),
								Res.getString(9), Res.getInt(10));
						Res = s.executeQuery(getUserLoans + Sub.getSubscriberNumber() + Endquery);
						ArrayList<Loan> Loans = new ArrayList<Loan>();
						ArrayList<Loan> LoansActivityHistory = new ArrayList<Loan>();
						while (Res.next()) {
							String LoanID = Res.getString(1);
							String SubscriberID = Res.getString(2);
							String BookCatalogNumber = Res.getString(3);
							String CopyID = Res.getString(4);
							Date StartDate = Res.getDate(5);
							Date ReturnDate = Res.getDate(6);
							String LoanStatus = Res.getString(7);
							String BookName = "Coul'd not load this data.";
							String BookAuthors = "Coul'd not load this data.";
							Statement stmt = mysqlConnection.conn.createStatement();
							ResultSet bookInfo = stmt.executeQuery(getBookNameAndAuthor + BookCatalogNumber + Endquery);
							while (bookInfo.next()) {
								BookName = bookInfo.getString(1);
								BookAuthors = bookInfo.getString(2);
							}
							Loan a = new Loan(LoanID, SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate,
									LoanStatus, BookName, BookAuthors);
							if (LoanStatus.equals("Finish")) {
								LoansActivityHistory.add(a);
							} else {
								Loans.add(a);
							}
						}
						Res = s.executeQuery(getUserOrders + Sub.getSubscriberNumber() + Endquery);
						ArrayList<Order> Orders = new ArrayList<Order>();
						ArrayList<Order> OrdersActivityHistory = new ArrayList<Order>();
						while (Res.next()) {
							String OrderID = Res.getString(1);
							String SubscriberID = Res.getString(2);
							String BookCatalogNumber = Res.getString(3);
							Date OrderDate = Res.getDate(4);
							Date BookArrivedTime = Res.getDate(5);
							String OrderStatus = Res.getString(6);
							String BookName = "Coul'd not load this data.";
							String BookAuthors = "Coul'd not load this data.";
							Statement stmt = mysqlConnection.conn.createStatement();
							ResultSet bookInfo = stmt.executeQuery(getBookNameAndAuthor + BookCatalogNumber + Endquery);
							while (bookInfo.next()) {
								BookName = bookInfo.getString(1);
								BookAuthors = bookInfo.getString(2);
							}
							Order a = new Order(OrderID, SubscriberID, BookCatalogNumber, OrderDate, BookArrivedTime,
									OrderStatus, BookName, BookAuthors);
							if (a.equals("Finish")) {
								OrdersActivityHistory.add(a);
							} else {
								Orders.add(a);
							}
						}
						Sub.setActiveLoans(Loans);
						Sub.setHistoryLoans(LoansActivityHistory);
						Sub.setActiveOrders(Orders);
						Sub.setHistoryOrders(OrdersActivityHistory);
						ArrayList<Object> subs = new ArrayList<Object>();
						subs.add(Sub);
						ServerData result = new ServerData(subs, operationsReturn.returnSubscriber);
						return result;
					}
				} else if (Res.getString(1).equals("1")) {
					ArrayList<Object> Librarian = new ArrayList<Object>();
					Res = s.executeQuery(
							getLibrarianInformation + Login.userName + ANDPassword + Login.Password + Endquery);
					while (Res.next()) {
						Librarian librarian = new Librarian(Res.getString(1), Res.getString(2), Res.getString(3),
								Res.getString(4), Res.getString(5), Res.getString(6), Res.getString(8), 1);
						Librarian.add(librarian);
					}
					ServerData result = new ServerData(Librarian, operationsReturn.returnLibrarian);
					return result;
				} else if (Res.getString(1).equals("2")) {
					ArrayList<Object> LibrarianManager = new ArrayList<Object>();
					Res = s.executeQuery(
							getLibrarianInformation + Login.userName + ANDPassword + Login.Password + Endquery);
					while (Res.next()) {
						Librarian librarianManager = new Librarian(Res.getString(1), Res.getString(2), Res.getString(3),
								Res.getString(4), Res.getString(5), Res.getString(6), Res.getString(8), 2);
						LibrarianManager.add(librarianManager);
					}
					ServerData result = new ServerData(LibrarianManager, operationsReturn.returnLibrarianManager);
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Object> Error = new ArrayList<Object>();
			Error.add(e);
			ServerData result = new ServerData(Error, operationsReturn.returnException);
			return result;
		}
		ArrayList<Object> Error = new ArrayList<Object>();
		Error.add("Can't Login - the Username/Password are wrong.");
		ServerData result = new ServerData(Error, operationsReturn.returnError);
		return result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public ServerData Login(ILoginQueris Login) {
		// TODO Auto-generated method stub
		return null;
	}

}
