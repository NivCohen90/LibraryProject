package Server;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import Interfaces.IAlert;
import SystemObjects.GeneralData.*;
import SystemObjects.ServerData;
import Users.Subscriber;
import SystemObjects.*;

public class SubscriberQueries {

	public static ServerData UpdateSubscriberInformation(Subscriber s) throws SQLException {
		try {
			PreparedStatement Statment = mysqlConnection.conn.prepareStatement(UpdateSubscriberquery());
			Statment.setString(1, s.getFirstName());
			Statment.setString(2, s.getLastName());
			Statment.setString(3, s.getEmail());
			Statment.setString(4, s.getPhoneNumber());
			Statment.setString(5, s.getID());
			int Succes1 = Statment.executeUpdate();
			Statment.closeOnCompletion();
			Statment = mysqlConnection.conn.prepareStatement(UpdateSubscriberStatusquery());
			Statment.setString(2, s.getStatus());
			Statment.setString(1, s.getID());
			Statment.executeUpdate();
			Statment.closeOnCompletion();
			ServerData Result = new ServerData(operationsReturn.returnSuccessMsg,
					"SubscriberID: " + s.getID() + " details as been updated successfuly.");
			ServerController.updateLog("SubscriberID: " + s.getID() + " Succesfuly Updated.");
			return Result;
		} catch (SQLException e) {
			ServerData Result = new ServerData(operationsReturn.returnError, "Update Details failed for SubscriberID: " + s.getID());
			//ServerData Result = new ServerData(operationsReturn.returnException, e);
			return Result;
		}
	}

	/**
	 * 
	 * @return update Subscriber query.
	 */

	public static String UpdateSubscriberquery() {

		String updateString = "UPDATE obl.user SET "
				+ "obl.user.FirstName=?, obl.user.LastName=?, obl.user.Email=?, obl.user.PhoneNumber=? WHERE obl.user.ID=?";
		return updateString;
	}

	public static String UpdateSubscriberStatusquery() {

		String updateString = "UPDATE obl.subscriber SET " + "obl.subscriber.Status=? WHERE obl.subscriber.ID=?";
		return updateString;
	}

	public static String getSubscriberStatus(String subID) throws SQLException {
		String loanString = String.format("Select Status from obl.Subscriber s where ID=%s", subID);
		Statement st;
		st = mysqlConnection.conn.createStatement();
		ResultSet rs = st.executeQuery(loanString);
		if (rs.next())
			return rs.getString("Status");
		return "";
	}
	
	public static String getSubscriberStatusSubID(String subID) throws SQLException {
		String loanString = String.format("Select Status from obl.Subscriber s where SubscriberID=%s", subID);
		Statement st;
		st = mysqlConnection.conn.createStatement();
		ResultSet rs = st.executeQuery(loanString);
		if (rs.next())
			return rs.getString("Status");
		return "";
	}

	/**
	 * add order by subscriber to database, subscriberID, book catalog in order
	 * object
	 * 
	 * @param orderToAdd order object with details of order
	 * @return object for sending to client with appropriate message
	 */
	public static ServerData addOrderToDB(Order orderToAdd) {

		int count = 0;
		ServerData result;
		Statement stmt = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String queryCheck = String.format(
				"SELECT * FROM obl.book where CatalogNumber = '%s' and NumberOfCopies!=NumberOfOrders;",
				orderToAdd.getBookCatalogNumber());
		String query = String.format(
				"INSERT INTO `obl`.`order` (`SubscriberID`,`bookCatalogNumber`,`OrderDate`,`OrderStatus`)"
						+ "VALUES ('%s', '%s', '%s', 'Active');",
				orderToAdd.getSubscriberID(), orderToAdd.getBookCatalogNumber(),
				dateFormat.format(orderToAdd.getOrderDate()));
		String queryUpBook = String.format(
				"UPDATE book SET NumberOfOrders = NumberOfOrders + 1 where CatalogNumber = '%s';",
				orderToAdd.getBookCatalogNumber());
		String queryCheckOrder = String.format(
				"SELECT * FROM obl.order where bookCatalogNumber = '%s' and SubscriberID='%s';",
				orderToAdd.getBookCatalogNumber(), orderToAdd.getSubscriberID());
		String queryCheckLoan = String.format(
				"SELECT * FROM obl.loan where bookCatalogNumber = '%s' and SubscriberID='%s' and LoanStatus!='Finish';",
				orderToAdd.getBookCatalogNumber(), orderToAdd.getSubscriberID());
		 System.out.println(queryCheckLoan);
		try {
			stmt = mysqlConnection.conn.createStatement();
			if (stmt.executeQuery(queryCheck).next()) {
				if (!stmt.executeQuery(queryCheckOrder).next())
					if (!stmt.executeQuery(queryCheckLoan).next()) {
						count = stmt.executeUpdate(queryUpBook);
						count = stmt.executeUpdate(query);
						result = new ServerData(operationsReturn.returnSuccessMsg, "order added to queue");

					} else
						result = new ServerData(operationsReturn.returnError, "loan for this book exists, can not order");
				else
					result = new ServerData(operationsReturn.returnError, "order for this book already exist");
			} else
				result = new ServerData(operationsReturn.returnError, "order queue is full");
		} catch (SQLException e) {
			if (count == 1) {
				String queryDownBook = String.format(
						"UPDATE book SET NumberOfOrders = NumberOfOrders - 1 where CatalogNumber = '%s';",
						orderToAdd.getBookCatalogNumber());
				try {
					if (stmt == null)
						stmt = mysqlConnection.conn.createStatement();
					count = stmt.executeUpdate(queryDownBook);
				} catch (SQLException e1) {
					result = new ServerData(operationsReturn.returnException, e1);
				}
			}
			if (e.getMessage().contains("Duplicate entry"))
				result = new ServerData(operationsReturn.returnError, "order for this book already exist");
			else
				result = new ServerData(operationsReturn.returnException, e);
		}
		return result;
	}

	/**
	 * check is there are orders with arrived book in database, and if date is over
	 * 2 days update status to finished (didn't loan book after it arrived)
	 */
	public static void updateMissedOrderes() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date();
		LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
		Date twoDaysDate = Date.from(twoDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String query = String.format(
				"SELECT * FROM obl.order WHERE BookArrivedTime >= '%s' and OrderStatus='Active';",
				dateFormat.format(twoDaysDate));
		// System.out.println(query);
		Statement stmt = null;
		String queryUpdate = "";
		try {
			stmt = mysqlConnection.conn.createStatement();
			ResultSet missedOrders = stmt.executeQuery(query);
			ArrayList<String> books = new ArrayList<>();
			while (missedOrders.next()) {
				queryUpdate += String.format(
						"UPDATE `obl`.`order` SET `OrderStatus` = 'Finished' WHERE SubscriberID=%s and bookCatalogNumber=%s;-",
						missedOrders.getString("SubscriberID"), missedOrders.getString("bookCatalogNumber"));
				if(!books.contains(missedOrders.getString("bookCatalogNumber")))
					books.add(missedOrders.getString("bookCatalogNumber"));
			}
			String[] queryUpdateArray = queryUpdate.split("-");
			if(queryUpdateArray.length != 0)
			{
				for (String s : queryUpdateArray) {
					stmt.executeUpdate(s);
				}
				for(String iBook: books)
				{
					BookQueries.checkOrdersForBookAndNotice(iBook);
				}
			}
		} catch (SQLException e) {
			IAlert.ExceptionAlert(e);
		}
	}

	public static String getSubscriberID(String subscriberID) throws SQLException {
		String loanString = String.format("Select SubscriberID from obl.Subscriber s where ID=%s", subscriberID);
		Statement st;
		st = mysqlConnection.conn.createStatement();
		ResultSet rs = st.executeQuery(loanString);
		if (rs.next())
			return rs.getString("SubscriberID");
		return "";
	}

}

/*
 * public static void insertToDB(Student s) throws SQLException {
 * PreparedStatement Statment = conn.prepareStatement(SqlQuerys.addStudent());
 * // String query = "INSERT INTO users VALUES ('" + data[0] + "', '" + data[1]
 * + // "', '"+data[2]+"', '"+data[3]+"');"; // System.out.println(query);
 * Statment.setString(1, s.getStudentID()); Statment.setString(2,
 * s.getStudentName()); Statment.setString(3, s.getStatusMembership());
 * Statment.setString(4, s.getFreeze()); Statment.setString(5,
 * s.getOperation()); //Statment.setString(6, s.getStudentID());
 * Statment.execute(); ServerController.updateLog("Student Added Succesfuly.");
 * }
 * 
 * 
 * 
 * /*public static ArrayList<Student> getAllStudents() {
 * 
 * try {
 * 
 * ArrayList<Student> students = new ArrayList<Student>(); Statement stmt =
 * conn.createStatement(); // the ResultSet will hold the query result which we
 * can manipulate ResultSet rs = stmt.executeQuery("SELECT * FROM student");
 * while (rs.next()) { Student student = new Student("", "", "", "", "");
 * student.setStudentID(rs.getString(1));
 * student.setStudentName(rs.getString(2));
 * student.setStatusMembership(rs.getString(3));
 * student.setFreeze(rs.getString(4));
 * student.setOperation(rs.getString(5).toString()); students.add(student); }
 * return students; } catch (SQLException e) {
 * ServerController.updateLog(e.getMessage().toString()); e.printStackTrace(); }
 * return null;
 * 
 * }
 */