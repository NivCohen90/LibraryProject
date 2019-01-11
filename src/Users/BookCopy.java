package Users;

import java.io.Serializable;
import java.util.Date;

public class BookCopy extends Book implements Serializable{
	
private String CopyID;
private String CatalogNumber;
private boolean Loan;

public BookCopy() {}

public BookCopy(String bookName, String catalogNumber, String shelfLoaction, int availableCopies, int numberOfLibraryCopies, String description, String subject, String contextTable, String editionNumber, Date purchesDate,String CopyID,String CatalogNumber)
{
	super(bookName,catalogNumber,shelfLoaction,availableCopies,numberOfLibraryCopies,description,subject,contextTable,editionNumber,purchesDate);
	this.CopyID=CopyID;
	this.CatalogNumber=CatalogNumber;
	Loan=false;
}

public void setCopyID(String id)
{
	CopyID=id;
}

public String getCopyID()
{
	return CopyID;
}

public void setCatalogNumber(String catalogNumber)
{
	CatalogNumber=catalogNumber;
}

public String getCatalogNumber()
{
	return CatalogNumber;
}

public void setLoan(boolean newLoanStatus)
{
	Loan= newLoanStatus;
}

public boolean getLoan()
{
	return Loan;
}

}
