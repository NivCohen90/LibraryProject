package Users;

public interface IGeneralData{

	enum operations {
		Login, searchByBookName, searchByBookAuthor, searchByBookSubject, searchByBookDescription, searchByCatalogNumber,
		searchByLibrarianName, searchByLibrarianAffiliation, searchByLibrarianEmail, searchByLibrarianID,
		searchBySubscriberName, searchBySubscriberStudentID, searchBySubscriberEmail, searchBySubscriberID,
		updatePersonalDetails, orderBook, extandLoan, viewActiveLoans, viewActivityHistory, CreateNewLoan,
		updateReturnDateManualy, returnBook, watchReadersCard, CreateNewSubscriber, ManageCatalog,
		changeSubscriberStatus, watchEmployeesData, createReports,AddBooK;
	};
	
	enum operationsReturn {
		returnSubscriber, returnLibrarian,returnLibrarianManager, returnBook, returnBookCopy, returnLoan, returnOrder,
		returnSubscriberArray, returnLibrarianArray, returnBookArray, returnBookCopyArray, returnLoanArray, returnOrderArray,
		returnSuccessMsg, returnError};	

	
	enum subscriberSearchFields {IDField, subscriberNumberField, emailField, fullNameField}; 
	enum bookSearchFields {bookNameField, authorNameField, subjectField, freeTextField};
	enum Menuicons {Nothing, Login, SearchBook, SearchLibrarian, SearchSubscriber, SubscriberCard, ManagerCard, LibrarianCard, Exit, History, Report, catalog, CreateLoan, ReturnBook, CreateSubscriber, ChangeSubscriberStatus, Statistics, Connection};
	enum MenuType {MainMenu, SubscriberMenu, LibrarianMenu, LibrarianManagerMenu};
	
}
// Login (UserName,Password) search(Text,op)