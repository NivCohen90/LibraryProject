package Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;

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
			String answer= bookCatalogNumber(loanID);
			return isDemanded(answer);
	}
	
	public static String bookCatalogNumber(String loanID) {
		
		sqlQuery=String.format("Select BookCatalogNumber from obl.loan where LoanID= %s", loanID);
		try {
			s=mysqlConnection.conn.createStatement();
			return s.executeQuery(sqlQuery).toString();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
		return null;
}
	
	public static boolean checkOrdersForBook(String loanID)
	{
		Statement stmt;
		String bookCatalogNumber= BookQueries.bookCatalogNumber(loanID);
		String queryCheckOrder = String.format(
				"SELECT b.BookName, ordDetail.* FROM obl.`book` b inner join \r\n" + 
				"	(	SELECT * FROM obl.order ord inner join\r\n" + 
				"			(select sub.SubscriberID as subID, u.Email, u.FirstName, u.LastName from obl.subscriber sub inner join obl.user u on u.ID=sub.ID) subEmail on ord.SubscriberID = subEmail.subID\r\n" + 
				"	) ordDetail on b.CatalogNumber = ordDetail.bookCatalogNumber where ordDetail.`bookCatalogNumber` = %s ORDER BY ordDetail.OrderDate ASC;", bookCatalogNumber);
		try {
			stmt = mysqlConnection.conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryCheckOrder);
			return rs.next();
		}
	 catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
	}
	
	public static String checkOrdersForBookAndNotice(String bookCatalogNumber) {
		Connection con = mysqlConnection.conn;
		Statement stmt = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String queryCheckOrder = String.format(
				"SELECT b.BookName, ordDetail.* FROM obl.`book` b inner join \r\n" + 
				"	(	SELECT * FROM obl.order ord inner join\r\n" + 
				"			(select sub.SubscriberID as subID, u.Email, u.FirstName, u.LastName from obl.subscriber sub inner join obl.user u on u.ID=sub.ID) subEmail on ord.SubscriberID = subEmail.subID\r\n" + 
				"	) ordDetail on b.CatalogNumber = ordDetail.bookCatalogNumber where ordDetail.`bookCatalogNumber` = %s ORDER BY ordDetail.OrderDate ASC;", bookCatalogNumber);

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryCheckOrder);
			if (rs.next()) {
				String todayDate = dateFormat.format(new Date());
				String subscriberID = rs.getString("SubscriberID");
				String orderNotice = String.format("to: %s\nfrom: ort braude Library\n\nsubject:\nHello, %s %s\nthe book you ordered - %s has arrived to the library.\nyou have 2 days to realize your order or the order will be canceled.",  rs.getString("Email"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("BookName"));
				System.out.println(orderNotice);
				
				String queryUpdateOrder = String.format(
						"UPDATE `obl`.`order` SET `BookArrivedTime` = '%s' WHERE `OrderID` = %s;",
						todayDate, rs.getString("OrderID"));
				stmt.executeUpdate(queryUpdateOrder);
				System.out.println(queryUpdateOrder);
				
				
				return subscriberID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ServerData returnBook(String SubscriberID, String bookCatalogNumber) {
		Statement stmt = null;
		int bookCopyID=0;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = mysqlConnection.conn;
		
		// check book and subscriber exist
		String queryCheckBook = String.format("SELECT * FROM obl.book where CatalogNumber=%s", bookCatalogNumber);
		String queryCheckSub = String.format("SELECT * FROM obl.subscriber where SubscriberID=%s", SubscriberID);
		//System.out.println(queryCheckBook);
		//System.out.println(queryCheckSub);
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryCheckBook);

			if (!rs.next())
				return new ServerData(operationsReturn.returnError,"book doesn't exist");
				//System.out.println("book not exist");

			rs = stmt.executeQuery(queryCheckSub);
			if (!rs.next())
				return new ServerData(operationsReturn.returnError,"subscriber doesn't exist");
				//System.out.println("subscriber not exist");

			// check loan status
			String queryCheckLoan = String.format("SELECT * FROM obl.loan loan inner join obl.subscriber sub on loan.SubscriberID=sub.SubscriberID where loan.BookCatalogNumber = %s and loan.SubscriberID=%s and loan.LoanStatus='Active'",bookCatalogNumber, SubscriberID);
			rs = stmt.executeQuery(queryCheckLoan);
			
			if (!rs.next())
				return new ServerData(operationsReturn.returnError,"loan doesn't exist");
				//System.out.println("loan not exist");

			bookCopyID = rs.getInt("CopyID");
			
			if (rs.getString("LoanStatus").equals("Late")) {
				String querySubStatus="";
				switch (rs.getInt("FelonyNumber")) {
				case 1: // change status to active and decrease felony
					querySubStatus = String.format("UPDATE `obl`.`subscriber` SET `Status` = 'Active', FelonyNumber = FelonyNumber-1 WHERE `SubscriberID` = %s;",SubscriberID);
					System.out.println("status freeze to active,decrease felony");
					break;
				case 2: // decrease felony
					querySubStatus = String.format("UPDATE `obl`.`subscriber` SET FelonyNumber = FelonyNumber-1 WHERE `SubscriberID` = %s;",SubscriberID);
					System.out.println("status freeze to active,decrease felony");
					break;
				case 3: // status from lock to freeze and decrease felony
					querySubStatus = String.format("UPDATE `obl`.`subscriber` SET `Status` = 'Freeze', FelonyNumber = FelonyNumber-1 WHERE `SubscriberID` = %s;",SubscriberID);
					System.out.println("status lock to freeze,decrease felony");
					break;
				}
				stmt.executeUpdate(querySubStatus);
				System.out.println(querySubStatus);
				System.out.println("changed status");
				
				// update loan status to late finish
				String returnDate = dateFormat.format(new Date());
				String queryLoanStatus = String.format("UPDATE `obl`.`loan` SET `LoanStatus` = 'LateFinish', ReturnDate='%s' WHERE `LoanID` = %s;", returnDate, rs.getString("LoanID"));
				stmt.executeUpdate(queryLoanStatus);
				System.out.println(queryLoanStatus);
			}
			else
			{
				// update loan status to finish
				String returnDate = dateFormat.format(new Date());
				String queryLoanStatus = String.format("UPDATE `obl`.`loan` SET `LoanStatus` = 'Finish', ReturnDate='%s' WHERE `LoanID` = %s;", returnDate, rs.getString("LoanID"));
				stmt.executeUpdate(queryLoanStatus);
				System.out.println(queryLoanStatus);
			}
			
			// check is order exist, and notice subscriber
			String noticeSubscribeID = checkOrdersForBookAndNotice(bookCatalogNumber);
			if (noticeSubscribeID == null) {
				// update available copies
				String queryBookCopiesUp = String.format(
						"UPDATE `obl`.`book` SET `AvailableCopies` = AvailableCopies+1 WHERE `CatalogNumber` = %s;",
						bookCatalogNumber);
				stmt.executeUpdate(queryBookCopiesUp);
				System.out.println(queryBookCopiesUp);
			}
			
			//update isLoaned in bookcopy
			String queryBookCopy = String.format(
					"UPDATE `obl`.`bookcopy` SET `isLoaned` = 0 WHERE `CatalogNumber` = %s and CopyID=%s;",
					bookCatalogNumber, bookCopyID);
			stmt.executeUpdate(queryBookCopy);
			System.out.println(queryBookCopy);
			
			// send success message
			System.out.println("return loan success");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
