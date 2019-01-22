package Users;
import java.io.Serializable;
import java.util.ArrayList;

public class Subscriber extends User implements Serializable{
	public String Status;
	public String PhoneNumber;
	public String SubscriberNumber;
	public ArrayList<Loan> Loans;
	public ArrayList<Order> Orders;
	public ArrayList<Loan> ActivityHistory;
	public int fellonyNumber;
		
	public Subscriber() {}
	
	public Subscriber(String iD, String firstName, String lastName, String email, String phoneNumber, String password) {
		super(firstName, lastName, email, iD, password,1);
		PhoneNumber = phoneNumber;
		Loans = new ArrayList<Loan>();
		ActivityHistory = new ArrayList<Loan>();
		fellonyNumber = 0;
		
	}
	
	public Subscriber(String iD, String firstName, String lastName, String email, String phoneNumber, String password, String status) {
		super(firstName, lastName, email, iD, password,1);
		PhoneNumber = phoneNumber;
		Loans = new ArrayList<Loan>();
		Orders = new ArrayList<Order>();
		ActivityHistory = new ArrayList<Loan>();
		fellonyNumber = 0;
		Status = status;
		
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
	
	public void setOrders(ArrayList<Order> orders) {
		Orders = orders;
	}
	public ArrayList<Order> getOrders() {
		return Orders;
	}

}
