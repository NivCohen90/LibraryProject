package Users;
import java.util.ArrayList;

public class Subscriber extends User{
	
	private String Status;
	private String PhoneNumber;
	private String SubscriberNumber;
	private ArrayList<Loan> Loans;
	private ArrayList<Loan> ActivityHistory;
	private int fellonyNumber;
		
	
	public Subscriber(String firstName, String lastName, String email, String iD, String password, String status,String phoneNumber, String subscriberNumber) {
		super(firstName, lastName, email, iD, password);
		Status = status;
		PhoneNumber = phoneNumber;
		SubscriberNumber = subscriberNumber;
		Loans = new ArrayList<Loan>();
		ActivityHistory = new ArrayList<Loan>();
		fellonyNumber = 0;
		
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getSubscriberNumber() {
		return SubscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		SubscriberNumber = subscriberNumber;
	}
	public ArrayList<Loan> getLoans() {
		return Loans;
	}
	public void setLoans(ArrayList<Loan> loans) {
		Loans = loans;
	}
	public ArrayList<Loan> getActivityHistory() {
		return ActivityHistory;
	}
	public void setActivityHistory(ArrayList<Loan> activityHistory) {
		ActivityHistory = activityHistory;
	}
	public int getFellonyNumber() {
		return fellonyNumber;
	}
	public void setFellonyNumber(int fellonyNumber) {
		this.fellonyNumber = fellonyNumber;
	}
	
	

}
