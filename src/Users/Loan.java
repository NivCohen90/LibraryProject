package Users;
import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable{

	private Date StartDate;
	private Date ReturnDate; 
	private String LoanID; 
	private String SubscriberID;
	private String BookCatalogNumber;
	private String CopyID;
	private String LoanStatus;
	
	public Loan() {}
	
	public Loan(Date startDate, Date returnDate, String loanID, String subscriberID, String bookCatalogNumber,
			String copyID, String loanStatus) {
		StartDate = startDate;
		ReturnDate= returnDate;
		LoanID = loanID;
		SubscriberID = subscriberID;
		BookCatalogNumber = bookCatalogNumber;
		CopyID = copyID;
		LoanStatus = loanStatus;
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
