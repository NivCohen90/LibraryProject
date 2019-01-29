package Server;

public class CreateDatabase {
	
	final static String userTable = "CREATE TABLE IF NOT EXISTS `user` (\r\n" + 
			"		  `ID` varchar(10) NOT NULL,\r\n" + 
			"		  `FirstName` varchar(10) DEFAULT NULL,\r\n" + 
			"		  `LastName` varchar(10) DEFAULT NULL,\r\n" + 
			"		  `Email` varchar(30) DEFAULT NULL,\r\n" + 
			"		  `PhoneNumber` varchar(20) DEFAULT NULL,\r\n" + 
			"		  `Password` varchar(20) DEFAULT NULL,\r\n" + 
			"		  `Level` int(11) DEFAULT NULL,\r\n" + 
			"		  PRIMARY KEY (`ID`),\r\n" + 
			"		  UNIQUE KEY `Subcriber_ID_UNIQUE` (`ID`)\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	final static String subscriberTable = "CREATE TABLE IF NOT EXISTS `subscriber` (\r\n" + 
			"		  `ID` varchar(10) NOT NULL,\r\n" + 
			"		  `SubscriberID` varchar(45) NOT NULL,\r\n" + 
			"		  `Status` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `FelonyNumber` int(11) DEFAULT NULL,\r\n" + 
			"		  PRIMARY KEY (`ID`),\r\n" + 
			"		  UNIQUE KEY `SubscriberID_UNIQUE` (`SubscriberID`),\r\n" + 
			"		  CONSTRAINT `SubscriberID` FOREIGN KEY (`ID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	final static String librarianTable = "CREATE TABLE IF NOT EXISTS `librarian` (\r\n" + 
			"		  `ID` varchar(10) NOT NULL,\r\n" + 
			"		  `Affiliation` varchar(45) DEFAULT NULL,\r\n" + 
			"		  PRIMARY KEY (`ID`),\r\n" + 
			"		  CONSTRAINT `LibrarinID` FOREIGN KEY (`ID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	

	final static String bookTable = "CREATE TABLE  IF NOT EXISTS `book` (\r\n" + 
			"		  `CatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"		  `BookName` varchar(500) NOT NULL,\r\n" + 
			"		  `AuthorName` varchar(500) DEFAULT NULL,\r\n" + 
			"		  `Subject` varchar(500) DEFAULT NULL,\r\n" + 
			"		  `NumberOfCopies` int(11) DEFAULT NULL,\r\n" + 
			"		  `AvailableCopies` int(11) DEFAULT NULL,\r\n" + 
			"		  `NumberOfOrders` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `ShelfLocation` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `EditionNumber` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `purchesDate` date DEFAULT NULL,\r\n" + 
			"		  `isWanted` tinyint(4) DEFAULT '0',\r\n" + 
			"		  `Description` varchar(500) DEFAULT NULL,\r\n" + 
			"		  `ContextTable` varchar(45) DEFAULT NULL,\r\n" + 
			"		  PRIMARY KEY (`CatalogNumber`),\r\n" + 
			"		  UNIQUE KEY `CatalogNumber_UNIQUE` (`CatalogNumber`)\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

	
	final static String loanTable = "CREATE TABLE IF NOT EXISTS `loan` (\r\n" + 
			"		  `LoanID` varchar(45) NOT NULL,\r\n" + 
			"		  `SubscriberID` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `BookCatalogNumber` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `CopyID` varchar(45) DEFAULT NULL,\r\n" + 
			"		  `StartDate` date DEFAULT NULL,\r\n" + 
			"		  `ReturnDate` date DEFAULT NULL,\r\n" + 
			"		  `LoanStatus` varchar(45) DEFAULT NULL,\r\n" + 
			"		  PRIMARY KEY (`LoanID`),\r\n" + 
			"		  KEY `loanBookCN_idx` (`BookCatalogNumber`),\r\n" + 
			"		  KEY `loanSubID_idx` (`SubscriberID`),\r\n" + 
			"		  CONSTRAINT `loanBookCN` FOREIGN KEY (`BookCatalogNumber`) REFERENCES `book` (`catalognumber`) ON DELETE CASCADE ON UPDATE CASCADE,\r\n" + 
			"		  CONSTRAINT `loanSubID2` FOREIGN KEY (`SubscriberID`) REFERENCES `subscriber` (`subscriberid`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	final static String orderTable = "CREATE TABLE IF NOT EXISTS `bookcopy` (\r\n" + 
			"  `CopyID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `CatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  `isLoaned` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
			"  PRIMARY KEY (`CopyID`,`CatalogNumber`),\r\n" + 
			"  KEY `copy_idx` (`CatalogNumber`),\r\n" + 
			"  CONSTRAINT `copy` FOREIGN KEY (`CatalogNumber`) REFERENCES `book` (`catalognumber`)\r\n" + 
			") ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\r\n" + 
			"";
	
	final static String bookcopyTable = "CREATE TABLE IF NOT EXISTS `bookcopy` (\r\n" + 
			"  		`CopyID` int(11) GENERATED ALWAYS AS (0) VIRTUAL,\r\n" + 
			"  		`CatalogNumber` varchar(45) NOT NULL,\r\n" + 
			"  		`isLoaned` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
			"		PRIMARY KEY (`CatalogNumber`),\r\n" + 
			"		CONSTRAINT `bookCopyCatalog` FOREIGN KEY (`CatalogNumber`) REFERENCES `book` (`catalognumber`) ON DELETE CASCADE ON UPDATE CASCADE\r\n" + 
			"		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	
}






		


		


		


		




















