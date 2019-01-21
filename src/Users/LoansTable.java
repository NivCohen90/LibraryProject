package Users;

import java.io.Serializable;
import java.util.Date;

public class LoansTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String BookName;
	String Authors;
	Date StartDate;
	Date EndDate;
	
	
	public LoansTable(String bookName, String authors, Date date, Date date2) {
		super();
		BookName = bookName;
		Authors = authors;
		StartDate = date;
		EndDate = date2;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthors() {
		return Authors;
	}
	public void setAuthors(String authors) {
		Authors = authors;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
	
}
