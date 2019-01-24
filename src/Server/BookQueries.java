package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import SystemObjects.Book;
import SystemObjects.IGeneralData;
import SystemObjects.ServerData;

public class BookQueries {
	private static final String StartSearchquery = "SELECT * FROM `book` WHERE `";
	private static final String MiddleSearchquery = "` LIKE + '%";
	private static final String EndSearchquery = "%'";

	enum Cols {
		CatalogNumber, BookName, AuthorName, Subject, Description
	};

	public static ServerData SearchBook(String SearchString, Cols bookname) {
		ServerData Result;
		try {
			String query = StartSearchquery + bookname + MiddleSearchquery + SearchString + EndSearchquery;
			ArrayList<Object> books = new ArrayList<Object>();
			Statement s;
			s = mysqlConnection.conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13)));
			}
			Result = new ServerData(books, IGeneralData.operationsReturn.returnBookArray);
			return Result;
		} catch (SQLException e) {
			ArrayList<Object> ErrorMsgs = new ArrayList<>();
			ErrorMsgs.add(e);
			Result = new ServerData(ErrorMsgs, IGeneralData.operationsReturn.returnError);
			return Result;
		}
	}

	public static ServerData SearchBook(String SearchString) {
		ServerData Result;
		try {
			String queryBookName = StartSearchquery + Cols.BookName + MiddleSearchquery + SearchString + EndSearchquery;
			String queryAuthorName = StartSearchquery + Cols.AuthorName + MiddleSearchquery + SearchString + EndSearchquery;
			String querySubject = StartSearchquery + Cols.Subject + MiddleSearchquery + SearchString + EndSearchquery;
			String queryDescription = StartSearchquery + Cols.Description + MiddleSearchquery + SearchString + EndSearchquery;
			
			ArrayList<Object> books = new ArrayList<Object>();
			Statement s;
			s = mysqlConnection.conn.createStatement();
			ResultSet rs = s.executeQuery(queryBookName);
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13)));
			}
			rs = s.executeQuery(queryAuthorName);
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13)));
			}
			
			rs = s.executeQuery(querySubject);
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13)));
			}
			
			rs = s.executeQuery(queryDescription);
			while (rs.next()) {
				books.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13)));
			}	
			Result = new ServerData(books, IGeneralData.operationsReturn.returnBookArray);
			return Result;
		} catch (SQLException e) {
			ArrayList<Object> ErrorMsgs = new ArrayList<>();
			ErrorMsgs.add(e);
			Result = new ServerData(ErrorMsgs, IGeneralData.operationsReturn.returnError);
			return Result;
		}
	}

}
