package Server;

import java.sql.SQLException;
import java.sql.Statement;

public class BookQueries {
	
	private static String sqlQuery;
	private static Statement s;
	
	public static boolean isDemanded(String bookCatalogNumber) {
		sqlQuery=String.format("SELECT isWanted from obl.book where BookCatalogNumber= %s", bookCatalogNumber);
		try {
			s=mysqlConnection.conn.createStatement();
			String answer= s.executeQuery(sqlQuery).toString();
			if(answer.equals("0"))
				return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean isDemandedByLoanID(String loanID)
	{
		sqlQuery=String.format("Select BookCatalogNumber from obl.loan where LoanID= %s", loanID);
		try {
			s=mysqlConnection.conn.createStatement();
			String answer= s.executeQuery(sqlQuery).toString();
			return isDemanded(answer);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
