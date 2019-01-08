package Server;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;


public class mysqlConnection {

	static Connection conn;
	static String DataBase;
 
	@SuppressWarnings("deprecation")
	public static boolean SetmysqlConnection(String DatabaseIP, String SchemeName, String UserName, String Password) {
		System.out.println(SchemeName);
		DataBase = SchemeName;
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
	
	public static boolean LoadDataFromCSV(String Path) {
		Statement createdb;
		try {
			String PathFixed = "";
			System.out.println(Path);
		      for (String retval : Path.split("\\\\")) {
		          PathFixed += retval + "/";
		       }
			createdb = conn.createStatement();
			createdb.execute(LoadDataBase.Path +  PathFixed + LoadDataBase.userTableStart + DataBase + LoadDataBase.userTable);
			ServerController.updateLog("SQL Loaded userTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.subscriberTableStart + DataBase +  LoadDataBase.subscriberTable);
			ServerController.updateLog("SQL Loaded subscriberTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.librarianTableStart + DataBase +  LoadDataBase.librarianTable);
			ServerController.updateLog("SQL Loaded librarianTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.bookTableStart + DataBase +  LoadDataBase.bookTable);
			ServerController.updateLog("SQL Loaded bookTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.bookcopyTableStart + DataBase +  LoadDataBase.bookcopyTable);
			ServerController.updateLog("SQL Loaded bookcopyTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.loanTableStart + DataBase +  LoadDataBase.loanTable);
			ServerController.updateLog("SQL Loaded loanTable successfuly.");
			createdb.execute(LoadDataBase.Path + PathFixed + LoadDataBase.orderTableStart + DataBase +  LoadDataBase.orderTable);
			ServerController.updateLog("SQL Loaded orderTable successfuly.");
			ServerController.updateLog("SQL Load " + DataBase + "tables data succeed.");
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
				dropdb.execute("DROP DATABASE " + DataBase);
				ServerController.updateLog("DATABASE " + DataBase + " Dropped");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				String Error1 = "SQLException: " + e1.getMessage();
				Error1 = Error1 + "\n";
				Error1 = Error1 + "SQLState: " + e1.getSQLState();
				Error1 = Error1 + "\n";
				Error1 = Error1 + "VendorError: " + e1.getErrorCode();
				ServerController.updateLog(Error);
				ServerController.updateLog("DATABASE " + DataBase + " Failed to Drop");
			}
			return false;
		}

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
