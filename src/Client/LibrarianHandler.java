package Client;

import java.sql.Date;
import java.util.ArrayList;

import OBLFX.IGUIcontroller;
import Users.Book;
import Users.IGeneralData;
import Users.ServerData;
import Users.Subscriber;
import Users.Librarian;


public class LibrarianHandler extends IHandler{
	
	public LibrarianHandler(IGUIcontroller guiController){
		super();
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
	
	public void extendLoanByLibrarian() {}
	
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
		ServerData loginInfo = new ServerData(IGeneralData.operations.AddBooK,List);
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
		ServerData loginInfo = new ServerData(IGeneralData.operations.deleteBook,List);
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
		ServerData loginInfo = new ServerData(IGeneralData.operations.getBookDetails,List);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		
	}
	
	public void addBookCopyToCatalog() {}
	
	public void removeBookCopyFromCatalog() {}
	
	public void updateBookCopyinCatalog() {}

}
