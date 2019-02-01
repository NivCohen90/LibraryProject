package SystemObjects;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String BookName;
	private String BookAuthors;
	private Date OrderDate;
	private Date BookArrivedTime;
	private String SubscriberID;
	private String BookCatalogNumber;
	private String OrderID;
	private String OrderStatus;

	public Order() {
	}

	public Order(Date orderDate, Date bookArrivedTime, String subscriberID, String bookCatalogNumber, String bookName, String bookAuthors, String orderID, String orderStatus) {
		this.OrderDate = orderDate;
		this.BookArrivedTime = bookArrivedTime;
		this.SubscriberID = subscriberID;
		this.BookCatalogNumber = bookCatalogNumber;
		this.BookName = bookName;
		this.BookAuthors = bookAuthors;
		this.OrderID = orderID;
		this.OrderStatus = orderStatus;
	}
	
	public Order(String orderID, String subscriberID, String bookCatalogNumber, Date orderDate, Date bookArrivedTime, String orderStatus, String bookName, String bookAuthors) {
		this.SubscriberID = subscriberID;
		this.BookCatalogNumber = bookCatalogNumber;
		this.OrderDate = orderDate;
		this.BookArrivedTime = bookArrivedTime;
		this.BookName = bookName;
		this.BookAuthors = bookAuthors;
		this.OrderID = orderID;
		this.OrderStatus = orderStatus;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookAuthors() {
		return BookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		BookAuthors = bookAuthors;
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
