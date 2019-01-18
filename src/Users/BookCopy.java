package Users;

import java.io.Serializable;
import java.util.Date;

public class BookCopy extends Book implements Serializable{
	
private String CopyID;
private String CatalogNumber;
private boolean Loan;



public BookCopy(String catalogNumber,String bookName,String authorName,String subject,int numberOfLibraryCopies,int availableCopies,int NumberOfOrders,String shelfLoaction,String editionNumber,Date purchesDate,boolean bool,String description, String contextTable,String CopyID)
{
	super(catalogNumber,bookName,authorName,subject,numberOfLibraryCopies,availableCopies,NumberOfOrders,shelfLoaction,editionNumber,purchesDate,bool,description,contextTable);
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
