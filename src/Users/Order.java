package Users;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	
	private Date OrderDate;
	private Date BookArrivedTime;
	private String SubscriberID;
	private String BookCatalogNumber;
	
	public Order() {}
	
	public Order(Date orderDate, Date bookArrivedTime, String subscriberID, String bookCatalogNumber) {
		this.OrderDate = orderDate;
		this.BookArrivedTime = bookArrivedTime;
		this.SubscriberID = subscriberID;
		this.BookCatalogNumber = bookCatalogNumber;
	}
	
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public Date getBookArrivedTime() {
		return BookArrivedTime;
	}
	public void setBookArrivedTime(Date bookArrivedTime) {
		BookArrivedTime = bookArrivedTime;
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

	
	
}
