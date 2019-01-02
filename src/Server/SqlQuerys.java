package Server;

public class SqlQuerys {

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
}
