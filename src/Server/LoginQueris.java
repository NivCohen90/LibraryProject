package Server;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Subscriber;

public class LoginQueris {
	private String userName;
	private String Password;

	private static final String getUserLevel = "SELECT obl.user.Level\r\n" + "FROM obl.user\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String getSubscriberInformation = "SELECT obl.user.ID, obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.PhoneNumber,obl.user.Password, obl.user.Level, obl.subscriber.SubscriberID, obl.subscriber.Status, obl.subscriber.FelonyNumber\r\n"
			+ "FROM obl.user\r\n" + "INNER JOIN obl.subscriber ON obl.user.ID=subscriber.ID\r\n"
			+ "WHERE obl.user.ID = '";
	private static final String ANDPassword = "' AND obl.user.Password = '";
	private static final String getUserLoans = "SELECT * FROM obl.loan WHERE SubscriberID = '";
	private static final String getUserOrders = "SELECT * FROM obl.order WHERE SubscriberID = '";
	private static final String Endquery = "';";

	public LoginQueris(String name, String pass) {
		userName = name;
		Password = pass;
	}

	@SuppressWarnings("resource")
	public static ServerData Login(LoginQueris Login) {

		try {
			Statement s = mysqlConnection.conn.createStatement();
			ResultSet subRes = s.executeQuery(getUserLevel + Login.userName + ANDPassword + Login.Password + Endquery);
			while (subRes.next()) {
				if (subRes.getString(1).equals("0")) {
					subRes = s.executeQuery(
							getSubscriberInformation + Login.userName + ANDPassword + Login.Password + Endquery);
					while (subRes.next()) {
						Subscriber Sub = new Subscriber(subRes.getString(1), subRes.getString(2), subRes.getString(3),
								subRes.getString(4), subRes.getString(5), subRes.getString(6), subRes.getString(8),
								subRes.getString(9), subRes.getInt(10));
						subRes = s.executeQuery(getUserLoans + Sub.getSubscriberNumber() + Endquery);
						ArrayList<Loan> Loans = new ArrayList<Loan>();
						ArrayList<Loan> LoansActivityHistory = new ArrayList<Loan>();
						while (subRes.next()) {
							Loan a = new Loan(subRes.getString(1), subRes.getString(2), subRes.getString(3),
									subRes.getString(4), subRes.getDate(5), subRes.getDate(6), subRes.getString(7));
							if (a.getLoanStatus().equals("Finish")) {
								LoansActivityHistory.add(a);
							} else {
								Loans.add(a);
							}
						}
						subRes = s.executeQuery(getUserOrders + Sub.getSubscriberNumber() + Endquery);
						ArrayList<Order> Orders = new ArrayList<Order>();
						ArrayList<Order> OrdersActivityHistory = new ArrayList<Order>();
						while (subRes.next()) {
							Order a = new Order(subRes.getString(1), subRes.getString(2), subRes.getDate(3),
									subRes.getDate(4));
							Orders.add(a);
//						if (a..equals("Finish")) {
//							OrdersActivityHistory.add(a);
//						} else {
//						}
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
				} else if (subRes.getString(7).equals("1")) {
					ArrayList<Object> Librarian = new ArrayList<Object>();
					ServerData result = new ServerData(Librarian, operationsReturn.returnLibrarian);
					return result;
				} else if (subRes.getString(7).equals("2")) {
					ArrayList<Object> LibrarianManager = new ArrayList<Object>();
					ServerData result = new ServerData(LibrarianManager, operationsReturn.returnLibrarianManager);
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Object> Error = new ArrayList<Object>();
			Error.add(e.getMessage());
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

}
