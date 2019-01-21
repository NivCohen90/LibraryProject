package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Client.WindowButtons;
import Users.Loan;
import Users.ServerData;
import Users.Subscriber;
import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;

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
					System.out.println(rs.getString(1) + "\n" + rs.getString(2) + "\n" + rs.getString(3) + "\n"
							+ rs.getString(4) + "\n" + rs.getString(5) + "\n" + rs.getString(6));
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
					Statement getSubLoans = mysqlConnection.conn.createStatement();
					ResultSet subLoansRes = getSub.executeQuery(getSubLoansquery);
					ArrayList<Loan> Loans = new ArrayList<Loan>();
					ArrayList<Loan> ActivityHistory = new ArrayList<Loan>();
					while (subLoansRes.next()) {
						Loan a = new Loan(subLoansRes.getString(1), subLoansRes.getString(2), subLoansRes.getString(3),
								subLoansRes.getString(4), subLoansRes.getDate(5), subLoansRes.getDate(6),
								subLoansRes.getString(7));
						if (a.getLoanStatus().equals("Finish")) {
							ActivityHistory.add(a);
						} else {
							Loans.add(a);
						}
					}
					Sub.setActiveLoans(Loans);
					Sub.setHistoryLoans(ActivityHistory);
					ArrayList<Object> subs = new ArrayList<Object>();
					subs.add(Sub);
					ServerData result = new ServerData(subs, operationsReturn.returnSubscriber);
					return result;
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			ArrayList<Object> Error = new ArrayList<Object>();
			Error.add(e.getStackTrace());
			ServerData result = new ServerData(Error, operationsReturn.returnError);
			return result;
		}
		return null;
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
