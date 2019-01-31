package Server;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import Interfaces.IAlert;
import SystemObjects.GeneralData.*;
import SystemObjects.ServerData;
import SystemObjects.*;

public class SubscriberQueries {

	public static String UpdateStudent() {

		String updateString = "UPDATE student SET "
				+ "StudentID=?, StudentName=?, StatusMembership=?, Freeze=?, Operations=? WHERE StudentID=?;";
		return updateString;
	}

	public static String addStudent() {

		String updateString = "INSERT INTO student VALUES(?, ?, ?, ?, ?)";
		// WHERE StudentID = ?
		return updateString;
	}
	
	/**
	 * add order by subscriber to database, subscriberID, book catalog in order object
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
		// System.out.println(query);
		try {
			stmt = mysqlConnection.conn.createStatement();
			if (stmt.executeQuery(queryCheck).next()) {
				count = stmt.executeUpdate(queryUpBook);
				// System.out.println(queryUpBook);
				count = stmt.executeUpdate(query);
				// System.out.println(query);
				result = new ServerData(operationsReturn.returnSuccessMsg, "order added to queue");
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
				result = new ServerData(operationsReturn.returnError,
						"order for this book already exist");
			else
				result = new ServerData(operationsReturn.returnException, e);
		}
		return result;
	}

	/**
	 * check is there are orders with arrived book in database, and if date is over 2 days update status to finished (didn't loan book after it arrived)
	 */
	public static void updateMissedOrderes() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date today = new Date();
		LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
		Date twoDaysDate = Date.from(twoDaysAgo.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String query = String.format("SELECT * FROM obl.order WHERE OrderDate <= '%s' and OrderDate >= '%s' and OrderStatus='Active' and BookArrivedTime != 'null';",
				dateFormat.format(today), dateFormat.format(twoDaysDate));
		//System.out.println(query);
		Statement stmt = null;
		String queryUpdate = "";
		try {
			stmt = mysqlConnection.conn.createStatement();
			ResultSet missedOrders = stmt.executeQuery(query);
			while (missedOrders.next()) {
				queryUpdate += String.format(
						"UPDATE `obl`.`order` SET `OrderStatus` = 'Finished' WHERE SubscriberID=%s and bookCatalogNumber=%s;-",
						missedOrders.getString("SubscriberID"), missedOrders.getString("bookCatalogNumber"));
			}
			String[] queryUpdateArray = queryUpdate.split("-");
			for (String s : queryUpdateArray) {
				stmt.executeUpdate(s);
			}
		} catch (SQLException e) {
			IAlert.ExceptionAlert(e);
		}
	}
	
	/*public static void insertToDB(Student s) throws SQLException {
	PreparedStatement Statment = conn.prepareStatement(SqlQuerys.addStudent());
	// String query = "INSERT INTO users VALUES ('" + data[0] + "', '" + data[1] +
	// "', '"+data[2]+"', '"+data[3]+"');";
	// System.out.println(query);
	Statment.setString(1, s.getStudentID());
	Statment.setString(2, s.getStudentName());
	Statment.setString(3, s.getStatusMembership());
	Statment.setString(4, s.getFreeze());
	Statment.setString(5, s.getOperation());
	//Statment.setString(6, s.getStudentID());
	Statment.execute();
	ServerController.updateLog("Student Added Succesfuly.");
}

public static ArrayList<Student> UpdateStudentInformation(Student s) throws SQLException {
	PreparedStatement Statment = conn.prepareStatement(SqlQuerys.UpdateStudent());
	Statment.setString(1, s.getStudentID());
	Statment.setString(2, s.getStudentName());
	System.out.println(s.getStatusMembership());
	Statment.setString(3, s.getStatusMembership());
	Statment.setString(4, s.getFreeze());
	Statment.setString(5, s.getOperation());
	Statment.setString(6, s.getStudentID());
	Statment.executeUpdate();
	ServerController.updateLog("StudentID: " + s.getStudentID() +  " Updated Succesfuly.");
	return getAllStudents();
}*/

/*public static ArrayList<Student> getAllStudents() {

	try {
		
		ArrayList<Student> students = new ArrayList<Student>();
		Statement stmt = conn.createStatement();
		// the ResultSet will hold the query result which we can manipulate
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		while (rs.next()) {
			Student student = new Student("", "", "", "", "");
			student.setStudentID(rs.getString(1));
			student.setStudentName(rs.getString(2));
			student.setStatusMembership(rs.getString(3));
			student.setFreeze(rs.getString(4));
			student.setOperation(rs.getString(5).toString());
			students.add(student);
		}
		return students;
	} catch (SQLException e) {
		ServerController.updateLog(e.getMessage().toString());
		e.printStackTrace();
	}
	return null;

}*/
}
