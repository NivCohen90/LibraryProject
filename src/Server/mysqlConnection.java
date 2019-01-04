package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import App.*;

public class mysqlConnection {

	static Connection conn;
 
	public static String SetmysqlConnection(String DatabaseIP, String SchemeName, String UserName, String Password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			String Connection = "jdbc:mysql://";
			Connection = Connection + DatabaseIP;
			//Connection = Connection + "/" + SchemeName;
			//Connection = Connection + "?serverTimezone=UTC";
			conn = DriverManager.getConnection(Connection, UserName, Password);
			Statement createdb = conn.createStatement();
			createdb.execute("CREATE DATABASE IF NOT EXISTS " + SchemeName + ";");
			createdb.execute("SHOW DATABASES;");
			createdb.execute("USE " + SchemeName + ";");
			createdb.execute(CreateDatabase.userTable);
			createdb.execute(CreateDatabase.subscriberTable);
			createdb.execute(CreateDatabase.librarianTable);
			createdb.execute(CreateDatabase.bookTable);
			createdb.execute(CreateDatabase.loanTable);
			createdb.execute(CreateDatabase.orderTable);
			createdb.execute(CreateDatabase.bookcopyTable);
			ServerController.updateLog("SQL connection succeed - Connected to " + SchemeName + "Database (IP: " + DatabaseIP + ").");
			return "succeed";
		} catch (SQLException ex) {/* handle any errors */
			ex.printStackTrace();
			String Error = "SQLException: " + ex.getMessage();
			Error = Error + "\n";
			Error = Error + "SQLState: " + ex.getSQLState();
			Error = Error + "\n";
			Error = Error + "VendorError: " + ex.getErrorCode();
			ServerController.updateLog(Error);
			return "Error";
		}
		
	}
	public static boolean StopConnectionWithDatabase() {
		try {
			conn.close();
			ServerController.updateLog("SQL disconnected.");
			return true;
		} catch (SQLException e) {
			ServerController.updateLog(e.getMessage().toString());
			return false;
		}
	}

	public static void insertToDB(Student s) throws SQLException {
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
	}

	public static ArrayList<Student> getAllStudents() {

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

	}
	
	public static boolean CloseDBConnection() {
		try {
			conn.close();
			ServerController.updateLog("Connection Stopped with Database");
			return true;
		} catch (SQLException e) {
			ServerController.updateLog(e.getMessage());
			return false;
		}
	}
	
}
