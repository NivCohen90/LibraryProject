package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.ServerData;

public class LoanQueries {

	private static Statement st;
	private static String sqlQuery;

	public static ResultSet getLoansSet(String subID) throws SQLException {

		sqlQuery = ("Select * from obl.loan l where SubscriberID=subID");
		st = mysqlConnection.conn.createStatement();
		return st.executeQuery(sqlQuery);

	}

	public static ResultSet specificLoan(String subID, String loanID) throws SQLException {

		sqlQuery = ("Select * from obl.loan l where SubscriberID=subID and LoanID=loadID");
		st = mysqlConnection.conn.createStatement();
		return st.executeQuery(sqlQuery);

	}

	public static void updateLoanReturnDate(String subID, String loanID) throws SQLException {
		LocalDate newReturnDate = loanReturnDate(subID, loanID);
		sqlQuery = String.format("UPDATE obl.loan SET ReturnDate= DATE_ADD('%s', INTERVAL 7 DAY)  WHERE LoanID=%s AND SubscriberID= %s",newReturnDate.toString(),loanID, subID);
		st = mysqlConnection.conn.createStatement();
		st.executeUpdate(sqlQuery);
	}

	public static LocalDate loanReturnDate(String subID, String loanID) throws SQLException {
		sqlQuery = String.format("Select ReturnDate from obl.loan l where LoanID=%s AND SubscriberID= %s",loanID, subID);
		st = mysqlConnection.conn.createStatement();
		ResultSet rs = st.executeQuery(sqlQuery);
		if(rs.next())
		{
			return rs.getDate("ReturnDate").toLocalDate();
		}
		return null;
	}
	
	public static void createNewLoan(Loan newLoan) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
				sqlQuery=String.format("INSERT INTO obl.loan LoanID, SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate, LoanStatus VALUES "
						+ "(%s, %s, %s, %s, %s, %s, %s)", newLoan.getLoanID(), newLoan.getSubscriberID(), newLoan.getBookCatalogNumber(), newLoan.getCopyID(), 
						dateFormat.format(newLoan.getStartDate()), dateFormat.format(newLoan.getReturnDate()), newLoan.getLoanStatus());
			st = mysqlConnection.conn.createStatement();
			st.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static LocalDate calcReturnDate(LocalDate sDate, boolean demanded){
		
		if (demanded)
			return sDate.plusDays(3);
		
		else return sDate.plusDays(14);
	}
	
	public static ArrayList<Object> getSubscriberActiveLoans(String subID) throws SQLException {

		String sqlQuery = String.format("Select l.*, b.BookName, b.AuthorName from obl.loan l inner join obl.book b on b.CatalogNumber=l.BookCatalogNumber where l.SubscriberID=%s and l.LoanStatus='Active';", subID);
		Statement stmt = mysqlConnection.conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery);
		
		ArrayList<Object> Loans = new ArrayList<Object>();
		while (rs.next()) {
			String LoanID = rs.getString("LoanID");
			String SubscriberID = rs.getString("SubscriberID");
			String BookCatalogNumber = rs.getString("BookCatalogNumber");
			String CopyID = rs.getString("CopyID");
			Date StartDate = rs.getDate("StartDate");
			Date ReturnDate = rs.getDate("ReturnDate"); 
			String LoanStatus = rs.getString("LoanStatus");
			String BookName = rs.getString("BookName");
			String BookAuthors = rs.getString("AuthorName");

			Loan a = new Loan(LoanID, SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate, LoanStatus, BookName, BookAuthors);
			Loans.add(a);
		}
		return Loans;

	}

}
