package Client;

import java.io.IOException;
import java.util.ArrayList;

import OBLFX.IGUIcontroller;
import Users.Book;
import Users.IGeneralData;
import Users.ServerData;
import Users.Subscriber;
import Users.Librarian;


public class LibrarianHandler extends IHandler{
	
	public LibrarianHandler(IGUIcontroller guiController) throws IOException {
		super();
		currentControllerGUIobj = guiController;
	}



	
	
	public void createNewLoan() {}
	
	public void extendLoanByLibrarian() {}
	
	public void returnBook() {}
	
	public  void createNewSubscriber(Subscriber newSub,Librarian librarian) {
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(newSub);
    	List.add(librarian);
		ServerData loginInfo = new ServerData(IGeneralData.operations.CreateNewSubscriber,List);
		try
		{
			//sending serverData to server, checking it's not null
			if(loginInfo!=null)
				sendToServer(loginInfo);
			else
				throw new Exception("loginInfo is null");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void changeSubscriberStatus() {}
	
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
			//sending serverData to server, checking it's not null
			if(loginInfo!=null)
				sendToServer(loginInfo);
			else
				throw new Exception("loginInfo is null");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeBookFromCatalog() {}
	
	public void updateBookinCatalog() {}
	
	public void addBookCopyToCatalog() {}
	
	public void removeBookCopyFromCatalog() {}
	
	public void updateBookCopyinCatalog() {}

}
