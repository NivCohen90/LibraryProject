package SystemObjects;

import java.io.Serializable;
import java.util.Date;

public class OrdersTable implements Serializable{
	String BookName;
	String Authors;
	Date OrderDate;
	Date ArrivedDate;
	
	public OrdersTable(String bookName, String authors, Date orderDate, Date arrivedDate) {
		super();
		BookName = bookName;
		Authors = authors;
		OrderDate = orderDate;
		ArrivedDate = arrivedDate;
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

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public Date getArrivedDate() {
		return ArrivedDate;
	}

	public void setArrivedDate(Date arrivedDate) {
		ArrivedDate = arrivedDate;
	}
	
}
