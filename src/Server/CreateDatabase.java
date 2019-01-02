package Server;

public class CreateDatabase {
	
	final static String userTable = "CREATE TABLE IF NOT EXISTS `user` (\r\n" + 
			"  `ID` VARCHAR(10) NOT NULL,\r\n" + 
			"  `FirstName` VARCHAR(10) NULL,\r\n" + 
			"  `LastName` VARCHAR(10) NULL,\r\n" + 
			"  `Email` VARCHAR(30) NULL,\r\n" + 
			"  `PhoneNumber` VARCHAR(20) NULL,\r\n" + 
			"  `Password` VARCHAR(20) NULL,\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"  UNIQUE KEY `Subcriber_Id_UNIQUE` (`ID`)\r\n" + 
			"  );";
	
	final static String subscriberTable = "CREATE TABLE IF NOT EXISTS `subscriber` (\r\n" + 
			"		  `ID` VARCHAR(10) NOT NULL,\r\n" + 
			"		  `SubscriberID` VARCHAR(45) NOT NULL,\r\n" + 
			"		  `Status` VARCHAR(45) NULL,\r\n" + 
			"		  `FellonyNumber` INT NULL,\r\n" + 
			"		  PRIMARY KEY (`ID`, `SubscriberID`),\r\n" + 
			"		  CONSTRAINT `SubscriberID`\r\n" + 
			"		    FOREIGN KEY (`ID`)\r\n" + 
			"		    REFERENCES `user` (`ID`)\r\n" + 
			"		    ON DELETE CASCADE\r\n" + 
			"		    ON UPDATE CASCADE);";
	
	final static String librarianTable = "CREATE TABLE IF NOT EXISTS `librarian` (\r\n" + 
			"  `Id` VARCHAR(10) NOT NULL,\r\n" + 
			"  `Affiliation` VARCHAR(45) NULL,\r\n" + 
			"  `isManager` tinyint(4) DEFAULT '0',\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"    CONSTRAINT `LibrarinID`\r\n" + 
			"    FOREIGN KEY (`ID`)\r\n" + 
			"    REFERENCES `user` (`ID`)\r\n" + 
			"    ON DELETE CASCADE\r\n" + 
			"    ON UPDATE CASCADE);";
	
	final static String bookTable = "CREATE TABLE IF NOT EXISTS `book` (\r\n" + 
			"  `CatalogNumber` VARCHAR(45) NOT NULL,\r\n" + 
			"  `BookName` VARCHAR(45) NOT NULL,\r\n" + 
			"  `AuthorName` VARCHAR(45) NULL,\r\n" + 
			"  `Subject` VARCHAR(45) NULL,\r\n" + 
			"  `NumberOfCopies` INT NULL,\r\n" + 
			"  `AvailableCopies` INT NULL,\r\n" + 
			"  `ShelfLocation` VARCHAR(45) NULL,\r\n" + 
			"  `EditionNumber` VARCHAR(45) NULL,\r\n" + 
			"  PRIMARY KEY (`CatalogNumber`),\r\n" + 
			"  UNIQUE INDEX `CatalogNumber_UNIQUE` (`CatalogNumber` ASC) VISIBLE);";
	
	final static String loanTable = "CREATE TABLE IF NOT EXISTS `loan` (\r\n" + 
			"  `LoanID` varchar(45) NOT NULL,\r\n" + 
			"  `SubsriberID` varchar(10) DEFAULT NULL,\r\n" + 
			"  `BookCatalogNumber` varchar(45) DEFAULT NULL,\r\n" + 
			"  `StartDate` datetime DEFAULT NULL,\r\n" + 
			"  `ReturnDate` datetime DEFAULT NULL,\r\n" + 
			"`LoanStatus` varchar(45) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`LoanID`),\r\n" + 
			"  KEY `SubscriberID_idx` (`SubsriberID`),\r\n" + 
			"  KEY `CatalogNumber_idx` (`BookCatalogNumber`),\r\n" + 
			"  CONSTRAINT `loanCatalogNumber` FOREIGN KEY (`BookCatalogNumber`) REFERENCES `book` (`catalognumber`),\r\n" + 
			"  CONSTRAINT `loanSubscriberID` FOREIGN KEY (`SubsriberID`) REFERENCES `subscriber` (`id`)\r\n" + 
			") ;";
	
	final static String orderTable = "CREATE TABLE IF NOT EXISTS `order` (\r\n" + 
			"  `SubscriberID` varchar(10) NOT NULL,\r\n" + 
			"  `bookCatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `OrderDate` datetime NOT NULL,\r\n" + 
			"  `BookArrived` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
			"  KEY `SubscriberID_idx` (`SubscriberID`),\r\n" + 
			"  KEY `CatalogNumber_idx` (`bookCatalogNumber`),\r\n" + 
			"  CONSTRAINT `orderBookCN` FOREIGN KEY (`SubscriberID`) REFERENCES `subscriber` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
			"  CONSTRAINT `orderSubscriberID` FOREIGN KEY (`bookCatalogNumber`) REFERENCES `book` (`catalognumber`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ;";
	
	
}














