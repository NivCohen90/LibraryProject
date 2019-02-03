package Server;

/**
 * Create tables queries.
 * 
 * @author NivPC
 *
 */
public class CreateDatabase {
	final static String userTable = "CREATE TABLE IF NOT EXISTS `user` (\r\n" + 
			"  `ID` varchar(10) NOT NULL,\r\n" + 
			"  `FirstName` varchar(45) DEFAULT NULL,\r\n" + 
			"  `LastName` varchar(45) DEFAULT NULL,\r\n" + 
			"  `Email` varchar(50) DEFAULT NULL,\r\n" + 
			"  `PhoneNumber` varchar(20) DEFAULT NULL,\r\n" + 
			"  `Password` varchar(50) DEFAULT NULL,\r\n" + 
			"  `Level` int(11) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"  UNIQUE KEY `Subcriber_ID_UNIQUE` (`ID`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String subscriberTable = "CREATE TABLE IF NOT EXISTS `subscriber` (\r\n" + 
			"  `SubscriberID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `ID` varchar(45) NOT NULL,\r\n" + 
			"  `Status` varchar(45) DEFAULT 'Active',\r\n" + 
			"  `FelonyNumber` int(11) DEFAULT '0',\r\n" + 
			"  PRIMARY KEY (`SubscriberID`),\r\n" + 
			"  KEY `subID_idx` (`ID`),\r\n" + 
			"  CONSTRAINT `subID` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String librarianTable = "CREATE TABLE IF NOT EXISTS `librarian` (\r\n" + 
			"  `ID` varchar(10) NOT NULL,\r\n" + 
			"  `Affiliation` varchar(45) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`ID`),\r\n" + 
			"  CONSTRAINT `LibrarinID` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String bookTable = "CREATE TABLE IF NOT EXISTS `book` (\r\n" + 
			"  `CatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `BookName` varchar(500) NOT NULL,\r\n" + 
			"  `AuthorName` varchar(500) DEFAULT NULL,\r\n" + 
			"  `Subject` varchar(500) DEFAULT NULL,\r\n" + 
			"  `NumberOfCopies` int(11) DEFAULT '0',\r\n" + 
			"  `AvailableCopies` int(11) DEFAULT '0',\r\n" + 
			"  `NumberOfOrders` int(11) DEFAULT '0',\r\n" + 
			"  `ShelfLocation` varchar(45) DEFAULT NULL,\r\n" + 
			"  `EditionNumber` varchar(45) DEFAULT NULL,\r\n" + 
			"  `purchesDate` date DEFAULT NULL,\r\n" + 
			"  `isWanted` tinyint(4) DEFAULT '0',\r\n" + 
			"  `Description` varchar(500) DEFAULT NULL,\r\n" + 
			"  `ContextTable` varchar(45) DEFAULT NULL,\r\n" + 
			"  `BookCopyIndex` int(11) DEFAULT '0',\r\n" + 
			"  `isArchived` tinyint(4) DEFAULT '0',\r\n" + 
			"  PRIMARY KEY (`CatalogNumber`),\r\n" + 
			"  UNIQUE KEY `CatalogNumber_UNIQUE` (`CatalogNumber`)\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String loanTable = "CREATE TABLE IF NOT EXISTS `loan` (\r\n" + 
			"  `LoanID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `SubscriberID` int(11) NOT NULL,\r\n" + 
			"  `BookCatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `CopyID` varchar(45) DEFAULT NULL,\r\n" + 
			"  `StartDate` date DEFAULT NULL,\r\n" + 
			"  `ReturnDate` date DEFAULT NULL,\r\n" + 
			"  `LoanStatus` varchar(45) DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`LoanID`,`SubscriberID`,`BookCatalogNumber`),\r\n" + 
			"  KEY `loanBookCN_idx` (`BookCatalogNumber`),\r\n" + 
			"  KEY `loanSubID_idx` (`SubscriberID`),\r\n" + 
			"  CONSTRAINT `loanBookCN` FOREIGN KEY (`BookCatalogNumber`) REFERENCES `book` (`CatalogNumber`) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
			"  CONSTRAINT `loanSubID` FOREIGN KEY (`SubscriberID`) REFERENCES `subscriber` (`SubscriberID`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String orderTable = "CREATE TABLE IF NOT EXISTS `order` (\r\n" + 
			"  `OrderID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `SubscriberID` int(11) NOT NULL,\r\n" + 
			"  `bookCatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `OrderDate` datetime NOT NULL,\r\n" + 
			"  `BookArrivedTime` datetime DEFAULT NULL,\r\n" + 
			"  `OrderStatus` varchar(45) NOT NULL DEFAULT 'Active',\r\n" + 
			"  PRIMARY KEY (`OrderID`,`bookCatalogNumber`,`SubscriberID`),\r\n" + 
			"  KEY `SubscriberID_idx` (`OrderID`),\r\n" + 
			"  KEY `CatalogNumber_idx` (`bookCatalogNumber`),\r\n" + 
			"  KEY `orderSubID_idx` (`SubscriberID`),\r\n" + 
			"  CONSTRAINT `orderSubID` FOREIGN KEY (`SubscriberID`) REFERENCES `subscriber` (`SubscriberID`),\r\n" + 
			"  CONSTRAINT `orderSubscriberID` FOREIGN KEY (`bookCatalogNumber`) REFERENCES `book` (`CatalogNumber`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	final static String bookcopyTable = "CREATE TABLE IF NOT EXISTS `bookcopy` (\r\n" + 
			"  `CopyID` int(11) NOT NULL,\r\n" + 
			"  `CatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `isLoaned` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
			"  `isArchived` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
			"  PRIMARY KEY (`CatalogNumber`,`CopyID`),\r\n" + 
			"  CONSTRAINT `bookCopyCatalog` FOREIGN KEY (`CatalogNumber`) REFERENCES `book` (`CatalogNumber`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	final static String latereturnTable = "CREATE TABLE IF NOT EXISTS `latereturns` (\r\n" + 
			"  `LoanID` int(11) NOT NULL,\r\n" + 
			"  `SubID` int(11) NOT NULL,\r\n" + 
			"  `FaultKind` varchar(45) DEFAULT NULL,\r\n" + 
			"  `ExpectedReturnDate` date DEFAULT NULL,\r\n" + 
			"  `OriginalReturnDate` date DEFAULT NULL,\r\n" + 
			"  PRIMARY KEY (`LoanID`,`SubID`),\r\n" + 
			"  KEY `lateSubID_idx` (`SubID`),\r\n" + 
			"  CONSTRAINT `lateLoanID` FOREIGN KEY (`LoanID`) REFERENCES `loan` (`loanid`) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
			"  CONSTRAINT `lateSubID` FOREIGN KEY (`SubID`) REFERENCES `subscriber` (`subscriberid`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n" + 
			"";
	final static String manualUpdateTable = "CREATE TABLE IF NOT EXISTS `manualupdateloan` (\r\n" + 
			"  `UpdateID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `LibrarianID` varchar(10) NOT NULL,\r\n" + 
			"  `LoanID` int(11) NOT NULL,\r\n" + 
			"  `ReturnDate` date NOT NULL,\r\n" + 
			"  PRIMARY KEY (`UpdateID`),\r\n" + 
			"  KEY `updateLibrarianID_idx` (`LibrarianID`),\r\n" + 
			"  KEY `updateLoanID_idx` (`LoanID`),\r\n" + 
			"  CONSTRAINT `updateLibrarianID` FOREIGN KEY (`LibrarianID`) REFERENCES `librarian` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
			"  CONSTRAINT `updateLoanID` FOREIGN KEY (`LoanID`) REFERENCES `loan` (`loanid`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n" + 
			"";

}
