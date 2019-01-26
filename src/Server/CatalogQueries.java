package Server;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Interfaces.IGeneralData.operationsReturn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import SystemObjects.ServerData;
import SystemObjects.Book;

/**
 * class for server catalog related queries requests for database
 * 
 * @author ofir
 *
 */
public class CatalogQueries {

	/**
	 * enum for which sql query type using
	 */
	enum queryType {
		Insert, Update
	};

	static Connection con = mysqlConnection.conn;

	/**
	 * add book to database
	 * 
	 * @param bookToAdd book object with details to add
	 * @return ServerData object for sending to client with appropriate message;
	 *         success or error
	 */
	public static ServerData addBookToDB(Book bookToAdd) {
		String successMsg = "Book added to Catalog";
		ServerData result;
		Statement stmt;
		String query = String.format(
				"INSERT INTO `obl`.`book` (`CatalogNumber`, `BookName`, `AuthorName`, `Subject`, `NumberOfCopies`, `AvailableCopies`, `NumberOfOrders`, `ShelfLocation`, `EditionNumber`, `purchesDate`, `isWanted`, `Description`, `ContextTable`)"
						+ "VALUES (%s);",
				bookDetailsQuery(bookToAdd, queryType.Insert));
		// System.out.println(query);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			File pdfoutFile;
			FileOutputStream fos; // input from file
			BufferedOutputStream bos; // buffer input
			pdfoutFile = new File("src/PDFBook/" + bookToAdd.getContextTable());
			fos = new FileOutputStream(pdfoutFile);
			bos = new BufferedOutputStream(fos);
			bos.write(bookToAdd.getContextTableByteArray(), 0, bookToAdd.getContextTableByteArray().length); // read from file to byte array
			bos.flush();
			fos.flush();
			fos.close();
			result = new ServerData(operationsReturn.returnSuccessMsg, successMsg);
		} catch (MySQLIntegrityConstraintViolationException e) {
			result = new ServerData(operationsReturn.returnError, new Exception("Catalog number already exist"));
		} catch (SQLException | IOException e) {
			result = new ServerData(operationsReturn.returnError, e);
		}
		return result;
	}

	/**
	 * update book to database
	 * 
	 * @param bookToUpdate book object with details to update
	 * @return ServerData object for sending to client with appropriate message;
	 *         success or error
	 */
	public static ServerData updateBookInDB(Book bookToUpdate) {
		String successMsg = "Book updated in Catalog";
		ServerData result;
		Statement stmt;
		String query = String.format("UPDATE `obl`.`book`\r\n" + "SET %s WHERE `CatalogNumber` = '%s';",
				bookDetailsQuery(bookToUpdate, queryType.Update), bookToUpdate.getCatalogNumber());
		// System.out.println(query);
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			result = new ServerData(operationsReturn.returnSuccessMsg, successMsg);
		} catch (SQLException e) {
			result = new ServerData(operationsReturn.returnError, e);
		}
		return result;
	}

	/**
	 * create string with book details for sql query
	 * 
	 * @param bookWithDetails book object with details
	 * @return String with sql detail format
	 */
	private static String bookDetailsQuery(Book bookWithDetails, queryType queryType) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String query = "";
		switch (queryType) {
		case Insert:
			query = String.format("'%s', '%s', '%s', '%s', 0, 0, 0, '%s', '%s', '%s', '%s', '%s', '%s'",
					bookWithDetails.getCatalogNumber(), bookWithDetails.getBookName(), bookWithDetails.getAuthorName(),
					bookWithDetails.getSubject(), bookWithDetails.getShelfLoaction(),
					bookWithDetails.getEditionNumber(), dateFormat.format(bookWithDetails.getPurchesDate()),
					bookWithDetails.getIsWanted() ? 1 : 0, bookWithDetails.getDescription(),
					bookWithDetails.getContextTable());
			break;
		case Update:
			query = String.format(
					"`BookName` = '%s', `AuthorName` = '%s', `Subject` = '%s', `NumberOfCopies` = %d, `AvailableCopies` = %d, `NumberOfOrders` = %d, `ShelfLocation` = '%s', `EditionNumber` = '%s', `purchesDate` = '%s', `isWanted` = '%s', `Description` = '%s', `ContextTable` = '%s'",
					bookWithDetails.getBookName(), bookWithDetails.getAuthorName(), bookWithDetails.getSubject(),
					bookWithDetails.getNumberOfLibraryCopies(), bookWithDetails.getAvailableCopies(),
					bookWithDetails.getNumberOfOrders(), bookWithDetails.getShelfLoaction(),
					bookWithDetails.getEditionNumber(), dateFormat.format(bookWithDetails.getPurchesDate()),
					bookWithDetails.getIsWanted() ? 1 : 0, bookWithDetails.getDescription(),
					bookWithDetails.getContextTable());
			break;
		}
		return query;
	}

}
