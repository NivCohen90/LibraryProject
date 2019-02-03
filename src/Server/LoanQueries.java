package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.ServerData;

public class LoanQueries {

	private static Statement st;
	private static String sqlQuery;

	public static ResultSet getSubLoansSet(String subID) throws SQLException {

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
		sqlQuery = String.format(
				"UPDATE obl.loan SET ReturnDate= DATE_ADD('%s', INTERVAL 7 DAY)  WHERE LoanID='%s' AND SubscriberID= '%s'",
				newReturnDate.toString(), loanID, subID);
		st = mysqlConnection.conn.createStatement();
		st.executeUpdate(sqlQuery);
	}

	public static LocalDate loanReturnDate(String subID, String loanID) throws SQLException {
		sqlQuery = String.format("Select ReturnDate from obl.loan l where LoanID='%s' AND SubscriberID= '%s'", loanID,
				subID);
		st = mysqlConnection.conn.createStatement();
		ResultSet rs = st.executeQuery(sqlQuery);
		if (rs.next()) {
			return rs.getDate("ReturnDate").toLocalDate();
		}
		return null;
	}

	public static ServerData createNewLoan(Loan newLoan) {
		ServerData result = new ServerData(operationsReturn.returnError, "error in database");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			st = mysqlConnection.conn.createStatement();
			String queryCheckLoan = String.format(
					"SELECT * FROM obl.loan where bookCatalogNumber = '%s' and SubscriberID='%s' and LoanStatus!='Finish';",
					newLoan.getBookCatalogNumber(), newLoan.getSubscriberID());
			if (!st.executeQuery(queryCheckLoan).next()) {
				String queryCheckOrder = String.format(
						"SELECT * FROM obl.order where bookCatalogNumber = '%s' and SubscriberID='%s';",
						newLoan.getBookCatalogNumber(), newLoan.getSubscriberID());
				ResultSet rs = st.executeQuery(queryCheckOrder);
				if (rs.next()) {
					String queryOrder = String.format(
							"UPDATE `obl`.`order` SET `OrderStatus` = 'Finished' WHERE OrderID=%s",
							rs.getString("OrderID"));
					st.executeQuery(queryOrder);
				}
				String queryUpCopy = String.format("UPDATE `obl`.`bookcopy` SET `isLoaned` = '1' WHERE CopyID='%s'",
						newLoan.getCopyID());
				st.executeUpdate(queryUpCopy);
				sqlQuery = String.format(
						"INSERT INTO obl.loan (SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate, LoanStatus) VALUES "
								+ "('%s', '%s', '%s', '%s', '%s', '%s')",
						newLoan.getSubscriberID(), newLoan.getBookCatalogNumber(),
						newLoan.getCopyID(), dateFormat.format(newLoan.getStartDate()),
						dateFormat.format(newLoan.getReturnDate()), newLoan.getLoanStatus());
				st.executeUpdate(sqlQuery);
				result = new ServerData(operationsReturn.returnSuccessMsg, "loan added to subscriber");
			}
			else
				result = new ServerData(operationsReturn.returnError, "loan for book already exist");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static LocalDate calcNewReturnDate(LocalDate sDate, boolean demanded) {

		if (demanded)
			return sDate.plusDays(3);

		else
			return sDate.plusDays(14);
	}

	public static int totalLoansAmount() {
		sqlQuery = ("SELECT count(LoanID) FROM obl.loan;");
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			return rs.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Object> getSubscriberActiveLoans(String subID) throws SQLException {

		String sqlQuery = String.format(
				"Select l.*, b.BookName, b.AuthorName from obl.loan l inner join obl.book b on b.CatalogNumber=l.BookCatalogNumber where l.SubscriberID='%s' and l.LoanStatus='Active';",
				subID);
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

			Loan a = new Loan(LoanID, SubscriberID, BookCatalogNumber, CopyID, StartDate, ReturnDate, LoanStatus,
					BookName, BookAuthors);
			Loans.add(a);
		}
		return Loans;

	}

	public static int totalBookLoansAmount(String catalogNumber) {
		sqlQuery = String.format("SELECT count(LoanID) as loanCount FROM obl.loan where BookCatalogNumber= '%s';",
				catalogNumber);
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			return rs.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Integer> totalBooksLateLoansAmount() {
		ArrayList<Integer> bookLateCount = new ArrayList<Integer>();
		sqlQuery = "SELECT count(BookCatalogNumber) as countLate FROM obl.loan where LoanStatus='Late' group by BookCatalogNumber;";
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while (rs.next()) {
				bookLateCount.add(rs.getInt("countLate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookLateCount;
	}

	public static ArrayList<Integer> totalLateReturnsDuration() {
		ArrayList<Integer> lateReturnsDuration = new ArrayList<Integer>();
		sqlQuery = "SELECT StartDate, ReturnDate FROM obl.loan WHERE LoanStatus='late';";
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while (rs.next()) {
				LocalDate sDate = rs.getDate("StartDate").toLocalDate();
				LocalDate eDate = rs.getDate("ReturnDate").toLocalDate();
				Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
				lateReturnsDuration.add((int) diff.toDays());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lateReturnsDuration;
	}

	public static ArrayList<String> booksWithLoans() {
		ArrayList<String> books = new ArrayList<String>();
		sqlQuery = "SELECT DISTINCT(BookCatalogNumber) FROM obl.loan;";
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while (rs.next())
				books.add(rs.getString("BookCatalogNumber"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public static int specificBookLateLoansAmount(String catalogNumber) {
		sqlQuery = String.format("SELECT count(LoanID) FROM obl.loan WHERE BookCatalogNumber='%s'", catalogNumber);
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			return rs.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int numberOfBooksWithLateLoans() {
		sqlQuery = "SELECT COUNT(DISTINCT(BookCatalogNumber)) FROM obl.loan WHERE LoanStatus='Late';";
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			return rs.getInt(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ArrayList<Integer> specificBookLateLoansDuration(String bookCatalogNumber) {
		ArrayList<Integer> lateReturnsDuration = new ArrayList<Integer>();
		sqlQuery = String.format(
				"SELECT StartDate, ReturnDate FROM obl.loan WHERE LoanStatus='late' AND BookCatalogNumber='%s';",
				bookCatalogNumber);
		try {
			st = mysqlConnection.conn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while (rs.next()) {
				LocalDate sDate = rs.getDate("StartDate").toLocalDate();
				LocalDate eDate = rs.getDate("ReturnDate").toLocalDate();
				Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
				lateReturnsDuration.add((int) diff.toDays());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lateReturnsDuration;
	}

	public static void updateLoanReturnDateManualy(String subIDExtend, String loanIDExtend, Date dateExtend,
			String librarianID) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		sqlQuery = String.format("UPDATE obl.loan SET ReturnDate= '%s'  WHERE LoanID=%s AND SubscriberID= %s",
				dateFormat.format(dateExtend), loanIDExtend, subIDExtend);
		String queryManual = String.format(
				"INSERT INTO `obl`.`manualupdateloan` (`LibrarianID`,`LoanID`,`ReturnDate`) VALUES('%s','%s','%s');",
				librarianID, loanIDExtend, dateFormat.format(dateExtend));
		st = mysqlConnection.conn.createStatement();
		st.executeUpdate(sqlQuery);
		st.executeUpdate(queryManual);
	}
}
