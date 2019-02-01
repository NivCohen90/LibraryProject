package Client;

import java.time.LocalDate;
//import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import Interfaces.IHandler;
import OBLFX.ConnectionSettingsController;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.Loan;
import SystemObjects.ServerData;
import Users.Subscriber;
import Users.Librarian;

/**
 * class client for librarian methods
 * @author ofir
 *
 */
public class LibrarianHandler extends IHandler{
	public static ConnectionSettingsController conn = new ConnectionSettingsController();
	
	public LibrarianHandler(IGUIcontroller guiController){
		super(conn.getServerIPAddress(), conn.getPort());
		currentControllerGUIobj = guiController;
	}

	public void calcReturnDate(LocalDate sDate, String bookCatalogNumber) {
		ServerData data = new ServerData(GeneralData.operations.calcReturnDate, sDate, bookCatalogNumber);
	try {
		sendToServer(data);
	}
	catch (Exception e) {
		IAlert.ExceptionAlert(e);
		e.printStackTrace();
	}	
	}

	
	
	public void createNewLoan(String catalogNumber,String SubscriberID,Date Returndate,Date StartDate) {
		Loan newLoan=new Loan(null, SubscriberID, catalogNumber, null, StartDate, Returndate, "Active", null, null);

		ServerData loginInfo = new ServerData(GeneralData.operations.CreateNewLoan,newLoan);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	
		
	}
	
	public void extendLoanByLibrarian(String bookCtalogNumber,Date newReturnDate) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(bookCtalogNumber);
    	List.add(newReturnDate);
		ServerData loginInfo = new ServerData(GeneralData.operations.extandLoan,List);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void returnBook(String catalogNumber,String SubscriberID,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(catalogNumber);
    	List.add(SubscriberID);
    	List.add(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		ServerData loginInfo = new ServerData(List, GeneralData.operations.returnBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	
		
		
		
	}
	
	/**
	 * 
	 * @param newSub
	 * @param librarian
	 */
	public  void createNewSubscriber(Subscriber newSub,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(newSub);
		ServerData loginInfo = new ServerData(List, GeneralData.operations.CreateNewSubscriber);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
		
		
	}
	
	public void searchSubscriber() {}
	
	/*manage catalog*/
	
	public void addBookToCatalog(Book book,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(book);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List,GeneralData.operations.AddBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
		
	}
	
	public void removeBookFromCatalog(String catalogNumber,String CopyNumber,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(catalogNumber);
    	List.add(CopyNumber);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, GeneralData.operations.deleteBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}
	
	public void updateBookinCatalog(Book book,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(book);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, GeneralData.operations.updateBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	
		
		
		
	}
	
	public void addBookCopyToCatalog(String Catalog,String numberToAdd,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(Catalog);
    	List.add(numberToAdd);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, GeneralData.operations.AddBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	
	}
	
	public void removeBookCopyFromCatalog() {}
	
	public void updateBookCopyinCatalog() {}

}
