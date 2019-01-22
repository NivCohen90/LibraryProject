package Users;

public interface IGeneralData {

	/**
	 * this enum indicate the server which operation to do.
	 * @author nivco
	 *
	 */
	enum operations {
		Login, searchByBookName, searchByBookAuthor, searchByBookSubject, searchByBookDescription, searchByCatalogNumber,
		searchByLibrarianName, searchByLibrarianAffiliation, searchByLibrarianEmail, searchByLibrarianID,
		searchBySubscriberName, searchBySubscriberStudentID, searchBySubscriberEmail, searchBySubscriberID,
		updatePersonalDetails, orderBook, extandLoan, viewActiveLoans, viewActivityHistory, CreateNewLoan,
		updateReturnDateManualy, returnBook, watchReadersCard, CreateNewSubscriber, ManageCatalog,
		changeSubscriberStatus, watchEmployeesData, createReports,AddBooK, deleteBook, getBookDetails;
	};
	
	/**
	 * this enum indicate the client which type of Object return from the server.
	 * @author nivco
	 *
	 */
	enum operationsReturn {
		returnSubscriber, returnLibrarian,returnLibrarianManager, returnBook, returnBookCopy, returnLoan, returnOrder,
		returnSubscriberArray, returnLibrarianArray, returnBookArray, returnBookCopyArray, returnLoanArray, returnOrderArray,
		returnSuccessMsg, returnError};	


	enum subscriberSearchFields {
		IDField, subscriberNumberField, emailField, fullNameField
	};

	enum bookSearchFields {
		bookNameField, authorNameField, subjectField, freeTextField
	};

	/**
	 * can indicate the sideMenu which icon to load for each button.
	 * @author nivco
	 *
	 */
	enum Menuicons {
		Nothing, Login, SearchBook, SearchLibrarian, SearchSubscriber, SubscriberCard, ManagerCard, LibrarianCard, Exit,
		History, Report, catalog, CreateLoan, ReturnBook, CreateSubscriber, ChangeSubscriberStatus, Statistics,
		Connection
	};

	/**
	 * indicate the sideMenu which menu to load.
	 * @author nivco
	 *
	 */
	enum MenuType {
		MainMenu, SubscriberMenu, LibrarianMenu, LibrarianManagerMenu
	};

}
// Login (UserName,Password) search(Text,op)