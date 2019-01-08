package Server;

public class LoadDataBase {
	
	final static String Path = "LOAD DATA INFILE '";
	
	final static String userTableStart = "user.csv' INTO TABLE ";
	final static String userTable = ".user\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(ID,FirstName,LastName,Email,PhoneNumber,Password,Level);";
	
	final static String subscriberTableStart = "subscriber.csv' INTO TABLE ";	
	final static String subscriberTable = ".subscriber\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(ID,SubscriberID,Status,FelonyNumber);";
	
	final static String librarianTableStart = "librarian.csv' INTO TABLE ";		
	final static String librarianTable = ".librarian\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(ID,Affiliation);";
	
	final static String bookTableStart = "book.csv' INTO TABLE ";
	final static String bookTable = ".book\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(CatalogNumber,BookName,AuthorName,Subject,NumberOfCopies,AvailableCopies,NumberOfOrders,ShelfLocation,EditionNumber,purchesDate,isWanted,Description,ContextTable);";

	final static String loanTableStart = "loan.csv' INTO TABLE ";
	final static String loanTable = ".loan\r\n" +
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(LoanID,SubscriberID,BookCatalogNumber,CopyID,StartDate,ReturnDate,LoanStatus);";
	
	final static String orderTableStart = "order.csv' INTO TABLE ";
	final static String orderTable = ".order\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(SubscriberID,BookCatalogNumber,OrderDate,BookArrivedTime);";
	
	final static String bookcopyTableStart = "bookcopy.csv' INTO TABLE ";
	final static String bookcopyTable = ".bookcopy\r\n" + 
			"FIELDS TERMINATED BY ','  ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\r\\n'\r\n" + 
			"IGNORE 1 LINES\r\n" + 
			"(CopyID,CatalogNumber,Loan);";
}


