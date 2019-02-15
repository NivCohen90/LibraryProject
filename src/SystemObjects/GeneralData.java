package SystemObjects;

import Users.Librarian;
import Users.Subscriber;

public class GeneralData {

	/**
	 * this enum indicate the server which operation to do.
	 * 
	 * @author nivco
	 *
	 */

	public static Subscriber userSubscriber = null;
	public static Subscriber searchedSubscriber = null;
	public static Librarian userLibrarian = null;

	public enum operations {
		Login, searchByBookName, searchByBookAuthor, searchByBookSubject, searchByBookDescription,
		searchByCatalogNumber, searchByFreeText, searchByLibrarianName,
		searchByLibrarianAffiliation, searchByLibrarianEmail, searchByLibrarianID, searchBySubscriberName,
		searchBySubscriberStudentID, searchBySubscriberEmail, searchBySubscriberID, orderBook, extandLoan,
		viewActiveLoans, viewActivityHistory, CreateNewLoan, updateReturnDateManualy, returnBook, watchReadersCard,
		CreateNewSubscriber, ManageCatalog, updateSubscriberDetails, watchEmployeesData, createActivityReport,
		createLoansReport, createLateReturnsReport, AddBook, AddBookCopy, updateBook, deleteBook, getBookDetails,
		calcReturnDate;
	};

	/**
	 * this enum indicate the client which type of Object return from the server.
	 * 
	 * @author nivco
	 *
	 */
	public enum operationsReturn {
		returnSubscriber, returnLibrarian, returnLibrarianManager, returnBook, returnBookCopy, returnLoan, returnOrder,
		returnSubscriberArray, returnLibrarianArray, returnBookArray, returnBookCopyArray, returnLoanArray,
		returnOrderArray, returnSuccessMsg, returnError, returnLoanReportData, returnLateReturnsReportData,
		returnActivityReportData, returnException, returnReturnDate, returnDate
	};

	public enum subscriberSearchFields {
		IDField, subscriberNumberField, emailField, fullNameField
	};

	public enum bookSearchFields {
		bookNameField, authorNameField, subjectField, freeTextField
	};

	/**
	 * 
	 * @author Liad
	 *
	 */
	public enum reportsType {
		activityReport, loansReport, lateReturnsReport
	};

	/**
	 * 
	 * @author Liad
	 *
	 */
	public enum reportReference {
		Demanded, Regular, GeneralLatesAmount, GeneralLatesDuration, BookLatesAmount, BookLatesDuration
	};

	/**
	 * can indicate the sideMenu which icon to load for each button.
	 * 
	 * @author nivco
	 *
	 */
	public enum Menuicons {
		Nothing, Login, SearchBook, SearchLibrarian, SearchSubscriber, SubscriberCard, ManagerCard, LibrarianCard, Exit,
		History, Report, catalog, CreateLoan, ReturnBook, CreateSubscriber, ChangeSubscriberStatus, Statistics,
		Connection, AddBook, AddBookCopy, UpdateBook, DeleteBook, CreateReport
	};

	/**
	 * indicate the sideMenu which menu to load.
	 * 
	 * @author nivco
	 *
	 */
	public enum MenuType {
		MainMenu, SubscriberMenu, LibrarianMenu, LibrarianManagerMenu, SubMenu
	};

}