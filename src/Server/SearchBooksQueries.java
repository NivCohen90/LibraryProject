package Server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.ServerData;

/**
 * 
 * @author Niv Cohen.
 *
 */
public class SearchBooksQueries {

	private static final String StartSearchquery = "SELECT * FROM `book` WHERE `";
	private static final String MiddleSearchquery = "` LIKE + '%";
	private static final String EndSearchquery = "%'";
	private static final String FreeTextquery1 = "SELECT * FROM `book` WHERE (`CatalogNumber` LIKE '%";
	private static final String FreeTextquery2 = "%' OR `BookName` LIKE '%";
	private static final String FreeTextquery3 = "%' OR `AuthorName` LIKE '%";
	private static final String FreeTextquery4 = "%' OR `Subject` LIKE '%";
	private static final String FreeTextquery5 = "%' OR `Description` LIKE '%";
	private static final String FreeTextquery6 = "%')";

	enum Cols {
		CatalogNumber, BookName, AuthorName, Subject, Description
	};

	/**
	 * Return Book by specific search.
	 * 
	 * @param SearchString
	 * @param colName
	 * @return Server Data with the book(s) details or Error or Exception.
	 */
	public static ServerData SearchBookByColName(String SearchString, Cols colName) {
		ServerData Result;
		try {
			String query;
			if (colName.equals(Cols.CatalogNumber)) {
				query = StartSearchquery + colName + "` LIKE + '" + SearchString + "'";
			} else {
				query = StartSearchquery + colName + MiddleSearchquery + SearchString + EndSearchquery;
			}

			ArrayList<Object> books = new ArrayList<Object>();
			Statement s;
			s = mysqlConnection.conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			if (!rs.next()) {
				Result = new ServerData(GeneralData.operationsReturn.returnError,
						"The is no book with this " + colName + ".");
				return Result;
			} else {
				do {
					String ContextTable = rs.getString("CatalogNumber")+".pdf";
					Book b = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
							rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10),
							rs.getBoolean(11), rs.getString(12), ContextTable);
					if(rs.getBytes("ContextTable") != null)
						b.setContextTableByteArray(rs.getBlob("ContextTable").getBytes(1, (int) rs.getBlob("ContextTable").length()));
					else
					{
						b.setContextTable("");
						b.setContextTableByteArray(null);
					}
					books.add(b);
					
					/* get pdf file */
//					if (ContextTable.contains("pdf")) {
//						
//						InputStream fis; // input from file
//						BufferedInputStream bis; // buffer input
//						fis = rs.getClass().getResourceAsStream("/PDFBook/" + rs.getString("ContextTable"));
//						byte[] mybytearray = new byte[fis.available()];
//						bis = new BufferedInputStream(fis);
//						bis.read(mybytearray, 0, fis.available()); // read from file to byte array
//						bis.close();
//						fis.close();
//						Book newBook = (Book) books.get(books.size() - 1);
//						newBook.setContextTableByteArray(mybytearray);
//					}
				} while (rs.next());
				Result = new ServerData(books, GeneralData.operationsReturn.returnBookArray);
			}
			return Result;
		} catch (SQLException e) {
			ArrayList<Object> ErrorMsgs = new ArrayList<>();
			ErrorMsgs.add(e);
			Result = new ServerData(ErrorMsgs, GeneralData.operationsReturn.returnException);
			return Result;
		}
	}

	/**
	 * search book by FreeText.
	 * 
	 * @param SearchString
	 * @return
	 */
	public static ServerData FreeTextSearch(String SearchString) {
		ServerData Result;
		try {
			String freeTextquery = FreeTextquery1 + SearchString + FreeTextquery2 + SearchString + FreeTextquery3
					+ SearchString + FreeTextquery4 + SearchString + FreeTextquery5 + SearchString + FreeTextquery6;
			ArrayList<Object> books = new ArrayList<Object>();
			Statement s;
			s = mysqlConnection.conn.createStatement();
			ResultSet rs = s.executeQuery(freeTextquery);
			while (rs.next()) {
				String ContextTable = rs.getString("CatalogNumber")+".pdf";
				Book b = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), ContextTable);
				if(rs.getBytes("ContextTable") != null)
					b.setContextTableByteArray(rs.getBlob("ContextTable").getBytes(1, (int) rs.getBlob("ContextTable").length()));
				else
				{
					b.setContextTable("");
					b.setContextTableByteArray(null);
				}
				books.add(b);
			}
				
				/* get pdf file */
//				if (ContextTable.contains("pdf")) {
//					
//					File pdfFile;
//					InputStream fis; // input from file
//					BufferedInputStream bis; // buffer input
//					fis = rs.getClass().getResourceAsStream("/PDFBook/" + rs.getString("ContextTable"));
//					byte[] mybytearray = new byte[fis.available()];
//					bis = new BufferedInputStream(fis);
//					bis.read(mybytearray, 0, fis.available()); // read from file to byte array
//					bis.close();
//					fis.close();
//					Book newBook = (Book) books.get(books.size() - 1);
//					newBook.setContextTableByteArray(mybytearray);
//				}
//			}
			Result = new ServerData(books, GeneralData.operationsReturn.returnBookArray);
			return Result;
		} catch (SQLException e) {
			ArrayList<Object> ErrorMsgs = new ArrayList<>();
			ErrorMsgs.add(e);
			Result = new ServerData(ErrorMsgs, GeneralData.operationsReturn.returnException);
			return Result;
		}
	}

}
