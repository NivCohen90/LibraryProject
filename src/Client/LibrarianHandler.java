package Client;

import java.io.IOException;
import OBLFX.IGUIcontroller;


public class LibrarianHandler extends IHandler{
	
	public LibrarianHandler(IGUIcontroller guiController) throws IOException {
		super();
		currentControllerGUIobj = guiController;
	}
	
	public void createNewLoan() {}
	
	public void extendLoanByLibrarian() {}
	
	public void returnBook() {}
	
	public void createNewSubscriber() {}
	
	public void changeSubscriberStatus() {}
	
	public void createReports() {}
	
	public void getEmployeesData() {}
	
	public void searchSubscriber() {}
	
	/*manage catalog*/
	
	public void addBookToCatalog() {}
	
	public void removeBookFromCatalog() {}
	
	public void updateBookinCatalog() {}
	
	public void addBookCopyToCatalog() {}
	
	public void removeBookCopyFromCatalog() {}
	
	public void updateBookCopyinCatalog() {}

}
