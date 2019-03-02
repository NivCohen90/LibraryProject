package Server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Interfaces.IAlert;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;

public class mysqlConnection {

	static Connection conn;
	static String DB_SchemeName;
	static boolean DB_Dropped;
	static String DB_IP;
	static String DB_UserName;
	static String DB_Password;

	/**
	 * 
	 * @param DatabaseIP
	 * @param SchemeName
	 * @param UserName
	 * @param Password
	 * @return if connection and build database was succesful.
	 */
	@SuppressWarnings("deprecation")
	public static boolean SetmysqlConnection(String DatabaseIP, String SchemeName, String UserName, String Password) {
		DB_SchemeName = SchemeName;
		DB_IP = DatabaseIP;
		DB_UserName = UserName;
		DB_Password = Password;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */}

		try {
			String Connection = "jdbc:mysql://";
			Connection = Connection + DatabaseIP;
			Connection = Connection + "?useSSL=false";
			conn = DriverManager.getConnection(Connection, UserName, Password);
			Statement createdb = conn.createStatement();
			createdb.execute("CREATE DATABASE IF NOT EXISTS " + DB_SchemeName + ";");
			createdb.execute("SHOW DATABASES;");
			createdb.execute("USE " + SchemeName + ";");
			createdb.execute(CreateDatabase.userTable);
			createdb.execute(CreateDatabase.subscriberTable);
			createdb.execute(CreateDatabase.librarianTable);
			createdb.execute(CreateDatabase.bookTable);
			createdb.execute(CreateDatabase.loanTable);
			createdb.execute(CreateDatabase.orderTable);
			createdb.execute(CreateDatabase.bookcopyTable);
			createdb.execute(CreateDatabase.latereturnTable);
			createdb.execute(CreateDatabase.manualUpdateTable);
			ServerController.updateLog(
					"SQL connection succeed - Connected to " + SchemeName + " Database (IP: " + DatabaseIP + ").");
			DB_Dropped = false;
			return true;
		} catch (SQLException ex) {/* handle any errors */
			ex.printStackTrace();
			String Error = "SQLException: " + ex.getMessage();
			Error = Error + "\n";
			Error = Error + "SQLState: " + ex.getSQLState();
			Error = Error + "\n";
			Error = Error + "VendorError: " + ex.getErrorCode();
			ServerController.updateLog(Error);
			return false;
		}

	}

	/**
	 * 
	 * @param Path
	 * @return if load data from CSV files was succesful.
	 */
	public static boolean LoadDataFromCSV(String Path) {
		Statement createdb;
		try {
			String PathFixed = "";
			for (String retval : Path.split("\\\\")) {
				PathFixed += retval + "/";
			}
			if (DB_Dropped) {
				SetmysqlConnection(DB_IP, DB_SchemeName, DB_UserName, DB_Password);
				DB_Dropped = false;
			}
			createdb = conn.createStatement();
			ServerController.updateLog("Load Data from path:\n" + PathFixed + "\ninto " + DB_SchemeName + " Database");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.userTableStart + DB_SchemeName
					+ LoadDataBase.userTable);
			ServerController.updateLog("SQL Loaded userTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.subscriberTableStart + DB_SchemeName
					+ LoadDataBase.subscriberTable);
			ServerController.updateLog("SQL Loaded subscriberTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.librarianTableStart + DB_SchemeName
					+ LoadDataBase.librarianTable);
			ServerController.updateLog("SQL Loaded librarianTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.bookTableStart + DB_SchemeName
					+ LoadDataBase.bookTable);
			ServerController.updateLog("SQL Loaded bookTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.bookcopyTableStart + DB_SchemeName
					+ LoadDataBase.bookcopyTable);
			ServerController.updateLog("SQL Loaded bookcopyTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.loanTableStart + DB_SchemeName
					+ LoadDataBase.loanTable);
			ServerController.updateLog("SQL Loaded loanTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.orderTableStart + DB_SchemeName
					+ LoadDataBase.orderTable);
			ServerController.updateLog("SQL Loaded orderTable successfuly.");
			ServerController.updateLog("SQL Loaded " + DB_SchemeName + " tables data successfuly.");
			addPDFtoBooks();
			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			String Error = "SQLException: " + e.getMessage();
			Error = Error + "\n";
			Error = Error + "SQLState: " + e.getSQLState();
			Error = Error + "\n";
			Error = Error + "VendorError: " + e.getErrorCode();
			ServerController.updateLog(Error);
			try {
				Statement dropdb = conn.createStatement();
				dropdb.execute("DROP DATABASE " + DB_SchemeName);
				DB_Dropped = true;
				ServerController.updateLog("DATABASE " + DB_SchemeName + " Dropped");

			} catch (SQLException e1) {
				e1.printStackTrace();
				String Error1 = "SQLException: " + e1.getMessage();
				Error1 = Error1 + "\n";
				Error1 = Error1 + "SQLState: " + e1.getSQLState();
				Error1 = Error1 + "\n";
				Error1 = Error1 + "VendorError: " + e1.getErrorCode();
				ServerController.updateLog(Error1);
				ServerController.updateLog("DATABASE " + DB_SchemeName + " Failed to Drop");
			}
			return false;
		}

	}

	private static void addPDFtoBooks() {
		String[] CatalogNumber = new String[19];
		CatalogNumber[0] = "119202486";
		CatalogNumber[1] = "130330663";
		CatalogNumber[2] = "130926418";
		CatalogNumber[3] = "131989243";
		CatalogNumber[4] = "137566107";
		CatalogNumber[5] = "1558600698";
		CatalogNumber[6] = "1584886412";
		CatalogNumber[7] = "201001624";
		CatalogNumber[8] = "201870738";
		CatalogNumber[9] = "321210298";
		CatalogNumber[10] = "321313798";
		CatalogNumber[11] = "471078476";
		CatalogNumber[12] = "471509973";
		CatalogNumber[13] = "475723557";
		CatalogNumber[14] = "534950973";
		CatalogNumber[15] = "534953417";
		CatalogNumber[16] = "70049084";
		CatalogNumber[17] = "71002022";
		CatalogNumber[18] = "71230572";
		for (int i = 0; i < 19; i++) {
			try {
				String sql = String.format("UPDATE book Set ContextTable=? where CatalogNumber='%s';", CatalogNumber[i]);
	    		FileInputStream fis = new FileInputStream("src/PDFBook/" + CatalogNumber[i] + ".pdf");	//input from file
				byte[] mybytearray = null;
				mybytearray = new byte[fis.available()]; // byte array of file
	    		BufferedInputStream bis;		//buffer input	
    			//fis = new FileInputStream(pdfFile);
    			bis = new BufferedInputStream(fis);
    			bis.read(mybytearray,0,mybytearray.length);				//read from file to byte array
    			fis.close();
    			bis.close();
				PreparedStatement statement = conn.prepareStatement(sql);
				InputStream is = new ByteArrayInputStream(mybytearray);
				statement.setBlob(1, is);
				// System.out.println(statement.toString());
				statement.executeUpdate();
				statement.close();
			} catch (SQLException | IOException e) {
				IAlert.ExceptionAlert(e);
			}
		}

	}

	/**
	 * Stop Connection with the database.
	 * 
	 * @return flag that indicate the succesful of the method.
	 */
	public static boolean CloseDBConnection() {
		try {
			conn.close();
			ServerController.updateLog("Connection Stopped with Database");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			String Error = "SQLException: " + e.getMessage();
			Error = Error + "\n";
			Error = Error + "SQLState: " + e.getSQLState();
			Error = Error + "\n";
			Error = Error + "VendorError: " + e.getErrorCode();
			ServerController.updateLog(Error);
			return false;
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
	 * public static ArrayList<Student> UpdateStudentInformation(Student s) throws
	 * SQLException { PreparedStatement Statment =
	 * conn.prepareStatement(SqlQuerys.UpdateStudent()); Statment.setString(1,
	 * s.getStudentID()); Statment.setString(2, s.getStudentName());
	 * System.out.println(s.getStatusMembership()); Statment.setString(3,
	 * s.getStatusMembership()); Statment.setString(4, s.getFreeze());
	 * Statment.setString(5, s.getOperation()); Statment.setString(6,
	 * s.getStudentID()); Statment.executeUpdate();
	 * ServerController.updateLog("StudentID: " + s.getStudentID() +
	 * " Updated Succesfuly."); return getAllStudents(); }
	 */

	/*
	 * public static ArrayList<Student> getAllStudents() {
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

}
