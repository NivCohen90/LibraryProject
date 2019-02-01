package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Interfaces.IAlert;
import SystemObjects.Book;
import Users.Subscriber;

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
		sqlQuery = ("UPDATE obl.loan SET ReturnDate= DATE_ADD(newReturnDate, INTERVAL 7 DAY)  WHERE LoanID=loanID AND SubscriberID= subID");
		st = mysqlConnection.conn.createStatement();
		st.executeQuery(sqlQuery);
	}

	public static LocalDate loanReturnDate(String subID, String loanID) throws SQLException {
		sqlQuery = ("Select ReturnDate from obl.loan l where SubscriberID=subID and LoanID=loanID");
		st = mysqlConnection.conn.createStatement();
		return st.executeQuery(sqlQuery).getDate("created_date").toLocalDate();
	}
	
//	public static void createNewLoan(Subscriber s, Book b) {
//		String sqlQuery=("INSERT INTO obl.loan LoanID, SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate, LoanStatus VALUES "
//				+ "(%s, %s %s %s, % , % , %s", ((Subscriber)s).getSubscriberNumber(), );
//	}

}
