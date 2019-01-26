package Server;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Interfaces.IGeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.ServerData;
import Users.Subscriber;

public class LoginQueris {
	private String userName;
	private String Password;

	public LoginQueris(String name, String pass) {
		userName = name;
		Password = pass;
	}

	public static ServerData Login(LoginQueris Login) {

		try {
			String query = "SELECT * FROM obl.user WHERE ID = '" + Login.userName + "' AND Password =  '"
					+ Login.Password + "';";
			Statement s = mysqlConnection.conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				if (rs.getString(7).equals("0")) {
					Subscriber Sub = new Subscriber(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6));
					String getsubquery = "SELECT * FROM obl.subscriber WHERE ID = '" + Sub.getID() + "';";
					Statement getSub = mysqlConnection.conn.createStatement();
					ResultSet subRes = getSub.executeQuery(getsubquery);
					while (subRes.next()) {
						Sub.setSubscriberNumber(subRes.getString(2));
						Sub.setStatus(subRes.getString(3));
						Sub.setFellonyNumber(subRes.getInt(4));
					}
					String getSubLoansquery = "SELECT * FROM obl.loan WHERE SubscriberID = '"
							+ Sub.getSubscriberNumber() + "';";
//					Statement getSubLoans = mysqlConnection.conn.createStatement();
//					ResultSet subLoansRes = s.executeQuery(getSubLoansquery);
					subRes = s.executeQuery(getSubLoansquery);
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
					String getSubOredersquery = "SELECT * FROM obl.order WHERE SubscriberID = '"
							+ Sub.getSubscriberNumber() + "';";
					subRes = s.executeQuery(getSubOredersquery);
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
				} else if (rs.getString(7).equals("1")) {
					ArrayList<Object> Librarian = new ArrayList<Object>();
					ServerData result = new ServerData(Librarian, operationsReturn.returnLibrarian);
					return result;
				}
				else if(rs.getString(7).equals("2")) {
					ArrayList<Object> LibrarianManager = new ArrayList<Object>();
					ServerData result = new ServerData(LibrarianManager, operationsReturn.returnLibrarianManager);
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ArrayList<Object> Error = new ArrayList<Object>();
			Error.add(e.getMessage());
			ServerData result = new ServerData(Error, operationsReturn.returnError);
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
