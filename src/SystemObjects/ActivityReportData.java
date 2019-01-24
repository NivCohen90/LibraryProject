package SystemObjects;

import java.util.Date;

public class ActivityReportData {

	public ActivityReportData(Date startDate, Date endDate, int activeUser, int freezedUser, int notActiveUser,
			int loanedCopiesNum, int lateReturnsUser) {
		this.startDate = startDate;
		this.endDate = endDate;
		ActiveUser = activeUser;
		FreezedUser = freezedUser;
		NotActiveUser = notActiveUser;
		LoanedCopiesNum = loanedCopiesNum;
		LateReturnsUser = lateReturnsUser;
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
	public int getActiveUser() {
		return ActiveUser;
	}
	public void setActiveUser(int activeUser) {
		ActiveUser = activeUser;
	}
	public int getFreezedUser() {
		return FreezedUser;
	}
	public void setFreezedUser(int freezedUser) {
		FreezedUser = freezedUser;
	}
	public int getNotActiveUser() {
		return NotActiveUser;
	}
	public void setNotActiveUser(int notActiveUser) {
		NotActiveUser = notActiveUser;
	}
	public int getLoanedCopiesNum() {
		return LoanedCopiesNum;
	}
	public void setLoanedCopiesNum(int loanedCopiesNum) {
		LoanedCopiesNum = loanedCopiesNum;
	}
	public int getLateReturnsUser() {
		return LateReturnsUser;
	}
	public void setLateReturnsUser(int lateReturnsUser) {
		LateReturnsUser = lateReturnsUser;
	}
	private Date startDate;
	private Date endDate;
	private int ActiveUser;
	private int FreezedUser;
	private int NotActiveUser;
	private int LoanedCopiesNum;
	private int LateReturnsUser;
	
}
