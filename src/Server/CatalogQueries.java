package Server;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
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
		bookToAdd.setContextTable(bookToAdd.getCatalogNumber() + ".pdf");
		String query = String.format(
				"INSERT INTO `obl`.`book` (`CatalogNumber`, `BookName`, `AuthorName`, `Subject`, `NumberOfCopies`, `AvailableCopies`, `NumberOfOrders`, `ShelfLocation`, `EditionNumber`, `purchesDate`, `isWanted`, `Description`, `ContextTable`,`BookCopyIndex`)"
						+ "VALUES (%s);",
				bookDetailsQuery(bookToAdd, queryType.Insert));

		String checkCatalogNumber = String.format(
				"SELECT `CatalogNumber` FROM `obl`.`book` WHERE obl.book.CatalogNumber='%s';",
				bookToAdd.getCatalogNumber());

		// System.out.println(query);
		try {
			stmt = con.createStatement();
			ResultSet catalogNumber = stmt.executeQuery(checkCatalogNumber);
			while (catalogNumber.next()) {
				throw new SQLException("This book with this catalog number is already in database.");
			}
			File pdfoutFile;
			FileOutputStream fos; // input from file
			BufferedOutputStream bos; // buffer input
			pdfoutFile = new File("src/PDFBook/" + bookToAdd.getContextTable());
			if (pdfoutFile.exists())
				throw new FileAlreadyExistsException("pdf file with this name already exist");
			stmt.executeUpdate(query);
			fos = new FileOutputStream(pdfoutFile);
			bos = new BufferedOutputStream(fos);
			bos.write(bookToAdd.getContextTableByteArray(), 0, bookToAdd.getContextTableByteArray().length); // read
																												// from
																												// file
																												// to
																												// byte
																												// array
			bos.flush();
			fos.flush();
			fos.close();
			for (int i = 1; i <= bookToAdd.getNumberOfLibraryCopies(); i++) {
				String queryCopies = "INSERT INTO `obl`.`bookcopy`\r\n" + "(`CopyID`,\r\n" + "`CatalogNumber`)\r\n"
						+ "VALUES\r\n" + "(" + i + ",\r\n" + bookToAdd.getCatalogNumber() + ");";
				stmt.executeUpdate(queryCopies);
			}
			String queryCopiesAdded = String.format("SELECT * FROM obl.bookcopy where CatalogNumber='%s';",
					bookToAdd.getCatalogNumber());
			ResultSet copies = stmt.executeQuery(queryCopiesAdded);
			String copiesIDs = "Copies ID added : ";
			while (copies.next())
				copiesIDs += String.format("%d, ", copies.getInt(1));
			copiesIDs = copiesIDs.substring(0, copiesIDs.length() - 2);
			copiesIDs = copiesIDs + ".";
			result = new ServerData(operationsReturn.returnSuccessMsg, successMsg + "\n" + copiesIDs);
		} catch (MySQLIntegrityConstraintViolationException e) {
			result = new ServerData(operationsReturn.returnException, new Exception("Catalog number already exist"));
		} catch (FileAlreadyExistsException e) {
			result = new ServerData(operationsReturn.returnError, e.getMessage());
		} catch (SQLException | IOException e) {
			result = new ServerData(operationsReturn.returnException, e);
		}
		return result;
	}

	public static ServerData addBookCopyToDB(String catalog, String numberToAdd) {
		String successMsg = numberToAdd + " Book Copies Added Succesfuly.";
		String failMsg = "There is no book with this Catalog Number.";
		ServerData result;
		Statement stmt;
		int bookCopyIndex = -1;
		int AddNumber = -1;
		int AvailibaleCopy = -1;
		int NumberOfCopy = -1;

		AddNumber = Integer.valueOf(numberToAdd);
		String checkCatalogNumber = "SELECT obl.book.CatalogNumber,obl.book.BookCopyIndex,obl.book.NumberOfCopies,obl.book.AvailableCopies FROM `obl`.`book` WHERE obl.book.CatalogNumber='"
				+ catalog + "';";
		try {
			stmt = con.createStatement();
			ResultSet catalogNumber = stmt.executeQuery(checkCatalogNumber);
			while (catalogNumber.next()) {
				bookCopyIndex = catalogNumber.getInt(2);
				NumberOfCopy = catalogNumber.getInt(3);
				AvailibaleCopy = catalogNumber.getInt(4);
				for (int i = bookCopyIndex + 1; i <= bookCopyIndex + AddNumber; i++) {
					String queryCopies = "INSERT INTO `obl`.`bookcopy`\r\n" + "(`CopyID`,\r\n" + "`CatalogNumber`)\r\n"
							+ "VALUES\r\n" + "(" + i + ",\r\n" + catalog + ");";
					Statement stmt1 = con.createStatement();
					stmt1.executeUpdate(queryCopies);
					stmt1.closeOnCompletion();
				}
				PreparedStatement Pstmt = con.prepareStatement(
						"UPDATE obl.book SET NumberOfCopies=?,AvailableCopies=?,BookCopyIndex=? WHERE CatalogNumber=?;");
				Pstmt.setInt(1, NumberOfCopy + AddNumber);
				Pstmt.setInt(2, AvailibaleCopy + AddNumber);
				Pstmt.setInt(3, bookCopyIndex + AddNumber);
				Pstmt.setString(4, catalog);
				Pstmt.executeUpdate();
				Pstmt.closeOnCompletion();
				result = new ServerData(operationsReturn.returnSuccessMsg, successMsg);
				return result;
			}
			result = new ServerData(operationsReturn.returnError, failMsg);
			return result;
		} catch (SQLException e) {
			ArrayList<Object> list = new ArrayList<Object>();
			list.add(e);
			result = new ServerData(operationsReturn.returnException, e);
			return result;
		}
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
			result = new ServerData(operationsReturn.returnException, e);
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
			query = String.format("'%s', '%s', '%s', '%s', %d, %d, 0, '%s', '%s', '%s', '%s', '%s', '%s', %d",
					bookWithDetails.getCatalogNumber(), bookWithDetails.getBookName(), bookWithDetails.getAuthorName(),
					bookWithDetails.getSubject(), bookWithDetails.getNumberOfLibraryCopies(),
					bookWithDetails.getAvailableCopies(), bookWithDetails.getShelfLoaction(),
					bookWithDetails.getEditionNumber(), dateFormat.format(bookWithDetails.getPurchesDate()),
					bookWithDetails.getIsWanted() ? 1 : 0, bookWithDetails.getDescription(),
					bookWithDetails.getContextTable(), bookWithDetails.getNumberOfLibraryCopies());
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
