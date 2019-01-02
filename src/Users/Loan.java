package Users;
import java.util.Date;

public class Loan {

	private Date StartDate;
	private Date ReturnDate; 
	private static int LoanID; 
	
	public Loan(Date startDate, Date returnDate, int loanID) {
		StartDate = startDate; //צריך לעדכן שיקבל את התאריך הנוכחי
		ReturnDate= returnDate;//צריך לדאוג שהסרבר יחשב את זה בהתאם לביקוש הספר
		LoanID = loanID;
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
	public int getLoanID() {
		return LoanID;
	}
	public void setLoanID(int loanID) {
		LoanID = loanID;
	}
	
	
}
