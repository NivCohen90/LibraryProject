package Users;

public interface IGeneralData{

	enum operations {
		Login, searchByBookName, searchByBookAuthor, searchByBookSubject, searchByBookDescription,
		updatePersonalDetails, orderBook, extandLoan, viewActiveLoans, viewActivityHistory, CreateNewLoan,
		updateReturnDateManualy, returnBook, watchReadersCard, CreateNewSubscriber, deleteBooK,
		changeSubscriberStatus, watchEmployeesData, createReports,AddBooK,getBookDetails;
	};
	
	enum operationsReturn {
		returnSubscriber, returnLibrarian,returnLibrarianManager, returnBook,returnBookCopy, returnLoan, returnOrder,
		returnSubscriberArray, returnLibrarianArray, returnBookArray, returnBookCopyArray, returnLoanArray, returnOrderArray,returnError};		

	
	enum subscriberSearchFields {IDField, subscriberNumberField, emailField, fullNameField}; 
	enum bookSearchFields {bookNameField, authorNameField, subjectField, freeTextField};
	
	enum Menuicons {Nothing, Login, SearchBook, SearchLibrarian, SearchSubscriber, SubscriberCard, ManagerCard, LibrarianCard, Exit, History, Report, catalog, CreateLoan, ReturnBook, CreateSubscriber, ChangeSubscriberStatus, Statistics, Connection};
	enum MenuType {MainMenu, SubscriberMenu, LibrarianMenu, LibrarianManagerMenu};
	
}
// Login (UserName,Password) search(Text,op)