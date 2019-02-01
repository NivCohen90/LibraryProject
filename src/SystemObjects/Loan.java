package SystemObjects;
import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String BookName;
	private String BookAuthors;
	private Date StartDate;
	private Date ReturnDate; 
	private String LoanID; 
	private String SubscriberID;
	private String BookCatalogNumber;
	private String CopyID;
	private String LoanStatus;
	
	public Loan() {}
	
	public Loan(String loanID, String subscriberID, String bookCatalogNumber, String copyID, Date startDate, Date returnDate, String loanStatus, String bookName, String bookAuthors) {
		this.StartDate = startDate;
		this.ReturnDate= returnDate;
		this.LoanID = loanID;
		this.SubscriberID = subscriberID;
		this.BookCatalogNumber = bookCatalogNumber;
		this.CopyID = copyID;
		this.LoanStatus = loanStatus;
		this.BookName = bookName;
		this.BookAuthors = bookAuthors;
	}
	
	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookAuthors() {
		return BookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		BookAuthors = bookAuthors;
	}

	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getReturnDate() {
		return ReturnDate;
	}
	public void setReturnDate(Date returnDate) {
		ReturnDate = returnDate;
	}
	public String getLoanID() {
		return LoanID;
	}
	public void setLoanID(String loanID) {
		LoanID = loanID;
	}
	public String getSubscriberID() {
		return SubscriberID;
	}
	public void setSubscriberID(String subscriberID) {
		SubscriberID = subscriberID;
	}
	public String getBookCatalogNumber() {
		return BookCatalogNumber;
	}
	public void setBookCatalogNumber(String bookCatalogNumber) {
		BookCatalogNumber = bookCatalogNumber;
	}
	public String getCopyID() {
		return CopyID;
	}
	public void setCopyID(String copyID) {
		CopyID = copyID;
	}
	public String getLoanStatus() {
		return LoanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		LoanStatus = loanStatus;
	}
	
	
}
