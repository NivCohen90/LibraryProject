package Users;

public interface IGeneralData{

	enum operations {
		Login, searchByBookName, searchByBookAuthor, searchByBookSubject, searchByBookDescription,
		updatePersonalDetails, orderBook, extandLoan, viewActiveLoans, viewActivityHistory, CreateNewLoan,
		updateReturnDateManualy, returnBook, watchReadersCard, CreateNewSubscriber, ManageCatalog,
		changeSubscriberStatus, watchEmployeesData, createReports;
	};
	
	enum operationsReturn {
		returnSubscriber, returnLibrarian,returnLibrarianManager, returnBook, returnBookCopy, returnLoan, returnOrder,
		returnSubscriberArray, returnLibrarianArray, returnBookArray, returnBookCopyArray, returnLoanArray, returnOrderArray,returnError};		

	
	enum subscriberSearchFields {IDField, subscriberNumberField, emailField, fullNameField}; 
	enum bookSearchFields {bookNameField, authorNameField, subjectField, freeTextField};
	
}
// Login (UserName,Password) search(Text,op)