package Users;
import java.io.Serializable;
import java.util.ArrayList;

import SystemObjects.Loan;
import SystemObjects.Order;

public class Subscriber extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String Status;
	public String PhoneNumber;
	public String SubscriberNumber;
	public ArrayList<Loan> ActiveLoans;
	public ArrayList<Loan> HistoryLoans;
	public ArrayList<Order> ActiveOrders;
	public ArrayList<Order> HistoryOrders;
	public int fellonyNumber;
		
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
		ActiveOrders = new ArrayList<Order>();
		HistoryLoans = new ArrayList<Loan>();
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

	public int getFellonyNumber() {
		return fellonyNumber;
	}
	public void setFellonyNumber(int fellonyNumber) {
		this.fellonyNumber = fellonyNumber;
	}

	public ArrayList<Loan> getActiveLoans() {
		return ActiveLoans;
	}

	public void setActiveLoans(ArrayList<Loan> activeLoans) {
		ActiveLoans = activeLoans;
	}

	public ArrayList<Loan> getHistoryLoans() {
		return HistoryLoans;
	}

	public void setHistoryLoans(ArrayList<Loan> historyLoans) {
		HistoryLoans = historyLoans;
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
	


}
