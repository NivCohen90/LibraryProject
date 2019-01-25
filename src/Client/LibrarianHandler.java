package Client;

import java.sql.Date;
import java.util.ArrayList;

import OBLFX.ConnectionSettingsController;
import OBLFX.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.IGeneralData;
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



	
	
	public void createNewLoan(String catalogNumber,String SubscriberID,Date Returndate,Date Startdate) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(catalogNumber);
    	List.add(SubscriberID);
    	List.add(Returndate);
    	List.add(Startdate);
		ServerData loginInfo = new ServerData(IGeneralData.operations.CreateNewLoan,List);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		
		
	}
	
	public void extendLoanByLibrarian(String bookCtalogNumber,Date newReturnDate) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(bookCtalogNumber);
    	List.add(newReturnDate);
		
	}
	
	public void returnBook(String catalogNumber,String SubscriberID,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(catalogNumber);
    	List.add(SubscriberID);
		ServerData loginInfo = new ServerData(IGeneralData.operations.returnBook,List);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
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
    	List.add(librarian);
		ServerData loginInfo = new ServerData(IGeneralData.operations.CreateNewSubscriber,List);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void changeSubscriberStatus() {}
	
	public void FreezeSubscriber() {}
	
	public void createReports() {}
	
	public void getEmployeesData() {}
	
	public void searchSubscriber() {}
	
	/*manage catalog*/
	
	public void addBookToCatalog(Book book,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(book);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List,IGeneralData.operations.AddBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeBookFromCatalog(String catalogNumber,String CopyNumber,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(catalogNumber);
    	List.add(CopyNumber);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, IGeneralData.operations.deleteBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBookinCatalog(Book book,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(book);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, IGeneralData.operations.updateBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		
	}
	
	public void addBookCopyToCatalog(String Catalog,String numberToAdd,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(Catalog);
    	List.add(numberToAdd);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(List, IGeneralData.operations.AddBook);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void removeBookCopyFromCatalog() {}
	
	public void updateBookCopyinCatalog() {}

}
