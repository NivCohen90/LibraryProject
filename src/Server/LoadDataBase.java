package Server;

public class LoadDataBase {
	final static String userTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'..\\CSV\\user.csv\r\n" + 
			"INTO TABLE 'userTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(ID,FirstName,LastName,Email,PhoneNumber,Password);";
			
	final static String subscriberTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'subscriberTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";
			
	final static String librarianTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'librarianTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";

	final static String bookTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'bookTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";

	final static String loanTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'loanTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";

	final static String orderTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'orderTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";

	final static String bookcopyTable = "LOAD DATA LOCAL INFILE  \r\n" + 
			"'c:/temp/some-file.csv'\r\n" + 
			"INTO TABLE 'bookcopyTable'  \r\n" + 
			"FIELDS TERMINATED BY ',' \r\n" + 
			"ENCLOSED BY '\"'\r\n" + 
			"LINES TERMINATED BY '\\n'\r\n" + 
			"IGNORE 1 ROWS\r\n" + 
			"(field_1,field_2 , field_3);";
}
