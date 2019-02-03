package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;

/**
 * @author NivPC
 *
 */
public class SearchUserQueries {
	/**
	 * Indicate the search type of the User.
	 * 
	 * @author NivPC
	 *
	 */
	enum searchtype {
	SubscriberbyID, SubscriberbyNumber, SubscriberbyName, SubscriberbyEmail, LibrarianbyID, LibrarianbyAffiliation,
	LibrarianbyName, LibrarianbyEmail
	};

	/**
	 * Search user queries.
	 */
	private static final String getUserLevel = "SELECT obl.user.Level\r\n" + "FROM obl.user\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getSubscriberInformation = "SELECT obl.user.ID, obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.PhoneNumber,obl.user.Password, obl.user.Level, obl.subscriber.SubscriberID, obl.subscriber.Status, obl.subscriber.FelonyNumber\r\n"
			+ "FROM obl.user\r\n" + "INNER JOIN obl.subscriber ON obl.user.ID=subscriber.ID\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getLibrarianInformation = "SELECT obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.ID, obl.user.PhoneNumber,obl.user.Password, obl.user.Level, obl.librarian.Affiliation  \r\n"
			+ "FROM obl.user\r\n" + "INNER JOIN obl.librarian ON obl.user.ID=obl.librarian.ID\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getUserLoans = "SELECT * FROM obl.loan WHERE SubscriberID = '";
	private static final String getUserOrders = "SELECT * FROM obl.order WHERE SubscriberID = '";
	private static final String getBookNameAndAuthor = "SELECT obl.book.BookName, obl.book.AuthorName FROM obl.book WHERE obl.book.CatalogNumber = '";
	private static final String Endquery = "';";

	/**
	 * @author Niv Cohen.
	 * @param search - The text that we want to search.
	 * @param type   - Search by type (ID, Affiliation, Name, Email, SubID)
	 * @return ServerData Object.
	 */
	public static ServerData searchUser(String search, searchtype type) {
		ServerData Result = null;
		ResultSet Res;
		ArrayList<Object> SubscriberList = new ArrayList<Object>();
		ArrayList<Object> LibrarianList = new ArrayList<Object>();
		try {
			Statement stmt;
			switch (type) {
			case LibrarianbyAffiliation:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchLibrarianByAffiliation(search));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Librarian with that Affiliation (" + search + ").");
					return Result;
				} else {
					do {
						String LibID = Res.getString(1);
						Librarian Lib = (Librarian) getUserInformation(LibID, "1");
						if (Lib != null)
							LibrarianList.add(Lib);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(LibrarianList, operationsReturn.returnLibrarianArray);
				return Result;
			case LibrarianbyEmail:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "Email", "1"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Librarian with that Email (" + search + ").");
					return Result;
				} else {
					do {
						String LibID = Res.getString(1);
						Librarian Lib = (Librarian) getUserInformation(LibID, "1");
						if (Lib != null)
							LibrarianList.add(Lib);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(LibrarianList, operationsReturn.returnLibrarianArray);
				return Result;
			case LibrarianbyID:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "ID", "1"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Librarian with that ID (" + search + ").");
					return Result;
				} else {
					do {
						String LibID = Res.getString(1);
						Librarian Lib = (Librarian) getUserInformation(LibID, "1");
						if (Lib != null)
							LibrarianList.add(Lib);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(LibrarianList, operationsReturn.returnLibrarianArray);
				return Result;
			case LibrarianbyName:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "Name", "1"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Librarian with that Name (" + search + ").");
					return Result;
				} else {
					do {
						String LibID = Res.getString(1);
						Librarian Lib = (Librarian) getUserInformation(LibID, "1");
						if (Lib != null)
							LibrarianList.add(Lib);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(LibrarianList, operationsReturn.returnLibrarianArray);
				return Result;
			case SubscriberbyEmail:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "Email", "0"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Subscriber with that Email (" + search + ").");
					return Result;
				} else {
					do {
						String SubID = Res.getString(1);
						Subscriber sub = (Subscriber) getUserInformation(SubID, "0");
						if (sub != null)
							SubscriberList.add(sub);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(SubscriberList, operationsReturn.returnSubscriberArray);
				return Result;
			case SubscriberbyID:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "ID", "0"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Subscriber with that ID (" + search + ").");
					return Result;
				} else {
					do {
						String SubID = Res.getString(1);
						Subscriber sub = (Subscriber) getUserInformation(SubID, "0");
						if (sub != null)
							SubscriberList.add(sub);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(SubscriberList, operationsReturn.returnSubscriberArray);
				return Result;
			case SubscriberbyName:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchUserID(search, "Name", "0"));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Subscriber with that Name (" + search + ").");
					return Result;
				} else {
					do {
						String SubID = Res.getString(1);
						Subscriber sub = (Subscriber) getUserInformation(SubID, "0");
						if (sub != null)
							SubscriberList.add(sub);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(SubscriberList, operationsReturn.returnSubscriberArray);
				return Result;
			case SubscriberbyNumber:
				stmt = mysqlConnection.conn.createStatement();
				Res = stmt.executeQuery(searchSubBySubscriberID(search));
				if (!Res.next()) {
					Result = new ServerData(operationsReturn.returnError,
							"There is no Subscriber with that Name (" + search + ").");
					return Result;
				} else {
					do {
						String SubID = Res.getString(1);
						Subscriber sub = (Subscriber) getUserInformation(SubID, "0");
						if (sub != null)
							SubscriberList.add(sub);
					} while (Res.next());
				}
				stmt.closeOnCompletion();
				Result = new ServerData(SubscriberList, operationsReturn.returnSubscriberArray);
				return Result;
			default:
				break;

			}
			return Result;
		} catch (

		SQLException e) {
			e.printStackTrace();
			Result = new ServerData(operationsReturn.returnError, "Cant Communicate with Database.");
			return Result;
		}

	}

	/**
	 * 
	 * @param Name       - text to search.
	 * @param searchtype - Search type.
	 * @param Level      - 0=Subscriber, 1=Librarian.
	 * @return ID of the User as String.
	 */
	public static String searchUserID(String Name, String searchtype, String Level) {
		String getUserID;
		if (searchtype.equals("Name") && Level.equals("0")) {
			return getUserID = "SELECT obl.user.ID FROM obl.user WHERE obl.user.FirstName LIKE '%" + Name
					+ "%' OR obl.user.LastName LIKE '%" + Name + "%' AND obl.user.Level='0';";
		}
		if (searchtype.equals("Name") && Level.equals("1")) {
			return getUserID = "SELECT obl.user.ID FROM obl.user WHERE (obl.user.FirstName LIKE '%" + Name
					+ "%' OR obl.user.LastName LIKE '%" + Name + "%') AND obl.user.Level='1';";
		}
		if (Level.equals("0")) {
			return getUserID = "SELECT obl.user.ID FROM obl.user WHERE obl.user." + searchtype + " LIKE '%" + Name
					+ "%' AND obl.user.Level='0';";
		}
		if (Level.equals("1")) {
			return getUserID = "SELECT obl.user.ID FROM obl.user WHERE obl.user." + searchtype + " LIKE '%" + Name
					+ "%' AND obl.user.Level='1';";
		}
		return null;
	}

	/**
	 * @author Niv Cohen.
	 * @param Text - Subscriber ID to search the Student ID.
	 * @return ID of the User as String.
	 */
	public static String searchSubBySubscriberID(String Text) {
		String getUserID;
		return getUserID = "SELECT obl.user.ID\r\n" + "FROM obl.user\r\n"
				+ "INNER JOIN obl.subscriber ON obl.user.ID=subscriber.ID\r\n"
				+ "WHERE obl.subscriber.SubscriberID LIKE '%" + Text + "%';";
	}

	/**
	 * @author Niv Cohen.
	 * @param Text - Librarian Affiliation to search the Librarian ID.
	 * @return ID of the User as String.
	 */
	public static String searchLibrarianByAffiliation(String Text) {
		String getUserID;
		return getUserID = "SELECT obl.user.ID\r\n" + "FROM obl.user\r\n"
				+ "INNER JOIN obl.librarian ON obl.user.ID=librarian.ID\r\n" + "WHERE obl.librarian.Affiliation LIKE '%"
				+ Text + "%' AND obl.user.Level='1';";
	}

	/**
	 * 
	 * @param ID    - The id of the user.
	 * @param Level - The level of the user (0=Subscriber, 1=Librarian).
	 * @return Object - Subscriber or Librarian with all of the information about
	 *         them.
	 */
	public static Object getUserInformation(String ID, String Level) {
		try {
			Statement s = mysqlConnection.conn.createStatement();
			ResultSet Users;
			ResultSet Res;
			ArrayList<Loan> Loans = new ArrayList<Loan>();
			ArrayList<Loan> LoansActivityHistory = new ArrayList<Loan>();
			ArrayList<Order> Orders = new ArrayList<Order>();
			ArrayList<Order> OrdersActivityHistory = new ArrayList<Order>();

			if (Level.equals("0")) {
				Users = s.executeQuery(getSubscriberInformation + ID + Endquery);
				s.closeOnCompletion();
				while (Users.next()) {
					Subscriber Sub = new Subscriber(Users.getString(1), Users.getString(2), Users.getString(3),
							Users.getString(4), Users.getString(5), Users.getString(6), Users.getString(8),
							Users.getString(9), Users.getInt(10));
					Statement s1 = mysqlConnection.conn.createStatement();
					Res = s1.executeQuery(getUserLoans + Sub.getSubscriberNumber() + Endquery);
					s1.closeOnCompletion();
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
						stmt.closeOnCompletion();
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
					Statement s2 = mysqlConnection.conn.createStatement();
					Res = s2.executeQuery(getUserOrders + Sub.getSubscriberNumber() + Endquery);
					s2.closeOnCompletion();
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
					return Sub;
				}

			} else if (Level.equals("1")) {
				Res = s.executeQuery(getLibrarianInformation + ID + Endquery);
				s.closeOnCompletion();
				while (Res.next()) {
					Librarian librarian = new Librarian(Res.getString(1), Res.getString(2), Res.getString(3),
							Res.getString(4), Res.getString(5), Res.getString(6), Res.getString(8), 1);
					return librarian;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
