package Users;
import java.io.Serializable;
import java.util.ArrayList;

public class Subscriber extends User implements Serializable{
	private String Status;
	private String PhoneNumber;
	private String SubscriberNumber;
	private ArrayList<Loan> ActiveLoans;
	private ArrayList<Order> ActiveOrders;
	private ArrayList<Loan> HistoryLoans;
	private ArrayList<Order> HistoryOrders;
	private int fellonyNumber;
		
	public Subscriber() {}
	
	public Subscriber(String iD, String firstName, String lastName, String email, String phoneNumber, String password) {
		super(firstName, lastName, email, iD, password,1);
		PhoneNumber = phoneNumber;
		ActiveLoans = new ArrayList<Loan>();
		HistoryLoans = new ArrayList<Loan>();
		ActiveOrders = new ArrayList<Order>();
		HistoryOrders = new ArrayList<Order>();
		fellonyNumber = 0;
		
	}
	
	public Subscriber(String iD, String firstName, String lastName, String email, String phoneNumber, String password, String status) {
		super(firstName, lastName, email, iD, password,1);
		PhoneNumber = phoneNumber;
		ActiveLoans = new ArrayList<Loan>();
		HistoryLoans = new ArrayList<Loan>();
		ActiveOrders = new ArrayList<Order>();
		HistoryOrders = new ArrayList<Order>();
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
	public ArrayList<Loan> getActiveLoans() {
		return ActiveLoans;
	}
	public void setActiveLoans(ArrayList<Loan> loans) {
		ActiveLoans = loans;
	}
	public ArrayList<Loan> getHistoryLoans() {
		return HistoryLoans;
	}
	public void setHistoryLoans(ArrayList<Loan> activityHistory) {
		HistoryLoans = activityHistory;
	}
	public ArrayList<Order> getActiveOrders() {
		return ActiveOrders;
	}

	public void setActiveOrders(ArrayList<Order> activeOrders) {
		ActiveOrders = activeOrders;
	}

	public ArrayList<Order> getHistoryOrders() {
		return HistoryOrders;
	}

	public void setHistoryOrders(ArrayList<Order> historyOrders) {
		HistoryOrders = historyOrders;
	}
	public int getFellonyNumber() {
		return fellonyNumber;
	}
	public void setFellonyNumber(int fellonyNumber) {
		this.fellonyNumber = fellonyNumber;
	}
	
	

}
