package SystemObjects;

import java.io.Serializable;
import java.util.Date;

public class OrdersTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String BookName;
	String Authors;
	Date OrderDate;
	Date ArrivedDate;
}
