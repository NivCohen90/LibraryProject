package Server;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Interfaces.IAlert;

public class LoanQueries {
	
	private static Statement st ;
	private static String sqlQuery;

	public static ResultSet getLoansSet(String subID) throws SQLException {
		
		sqlQuery=("Select * from obl.loan l where SubscriberID=subID");
			st= mysqlConnection.conn.createStatement();
			return st.executeQuery(sqlQuery);

	}
	
	public static ResultSet specificLoan(String subID, String loanID) throws SQLException {
		
		sqlQuery=("Select * from obl.loan l where SubscriberID=subID and LoanID=loadID");
		st= mysqlConnection.conn.createStatement();
		return st.executeQuery(sqlQuery);

	}
	
	public static void updateLoanReturnDate(String subID, String loanID, LocalDate newReturnDate) throws SQLException {
		
		sqlQuery=("UPDATE obl.loan 	SET ReturnDate = newReturnDate WHERE LoanID =  loanID and SubscriberID=subID");
			st= mysqlConnection.conn.createStatement();
			st.executeQuery(sqlQuery);

	}
	
	public static LocalDate loanReturnDate(String subID, String loanID) throws SQLException
	{
		sqlQuery=("Select ReturnDate from obl.loan l where SubscriberID=subID and LoanID=loanID");
			st= mysqlConnection.conn.createStatement();
			return st.executeQuery(sqlQuery).getDate("created_date").toLocalDate();
	}
	
}
