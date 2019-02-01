package SystemObjects;

import java.util.Date;

public class ActivityReportData {

	private Date startDate;
	private Date endDate;
	private int ActiveSubscribersAmount;
	private int FreezedSubscribersAmount;
	private int NotActiveSubscribersAmount;
	private int LoanedBooksAmount;
	private int LateReturnsSubAmount;
	
	public ActivityReportData(Date startDate, Date endDate, int activeUser, int freezedUser, int notActiveUser,
			int loanedCopiesNum, int lateReturnsUser) {
		this.startDate = startDate;
		this.endDate = endDate;
		ActiveSubscribersAmount = activeUser;
		FreezedSubscribersAmount = freezedUser;
		NotActiveSubscribersAmount = notActiveUser;
		LoanedBooksAmount = loanedCopiesNum;
		LateReturnsSubAmount = lateReturnsUser;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getActiveSubscribersAmount() {
		return ActiveSubscribersAmount;
	}
	public void setActiveSubscribersAmount(int activeSubAmount) {
		ActiveSubscribersAmount = activeSubAmount;
	}
	public int getFreezedSubscribersAmount() {
		return FreezedSubscribersAmount;
	}
	public void setFreezedSubscribersAmount(int freezedSubAmount) {
		FreezedSubscribersAmount = freezedSubAmount;
	}
	public int getNotActiveSubscribersAmount() {
		return NotActiveSubscribersAmount;
	}
	public void setNotActiveSubscribersAmount(int notActiveSubAmount) {
		NotActiveSubscribersAmount = notActiveSubAmount;
	}
	public int getLoanedBooksAmount() {
		return LoanedBooksAmount;
	}
	public void setLoanedBooksAmount(int loanedBooksAmount) {
		LoanedBooksAmount = loanedBooksAmount;
	}
	public int getLateReturnsSubAmount() {
		return LateReturnsSubAmount;
	}
	public void setLateReturnsSubAmount(int lateReturnsSubAmount) {
		LateReturnsSubAmount = lateReturnsSubAmount;
	}
	
	
}
