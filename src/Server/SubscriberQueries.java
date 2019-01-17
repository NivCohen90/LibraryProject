package Server;

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
