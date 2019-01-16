package Client;

import Users.ServerData;
import java.io.IOException;
import java.util.ArrayList;

import OBLFX.IGUIcontroller;
import Users.IGeneralData.operations;
import Users.Book;
import Users.IGeneralData;
import Users.IGeneralData.bookSearchFields;;

public class CommonHandler extends IHandler{
	
	public CommonHandler(IGUIcontroller guiController) throws IOException {
		super();
		//currentControllerGUIobj defined in IHandler interface, will save the GUI controller input was sent from
		currentControllerGUIobj = guiController;	 
	}

	/**
	 * method to send search book request to server
	 * @param fieldInput the input text in search
	 * @param searchType what kind of search the server will do
	 */
	public void searchBookInServer(String fieldInput, bookSearchFields searchType)
	{
		ServerData serverData = null;
		
		switch(searchType)	//switch between different search types
		{
			case bookNameField:
			{
				//book object to add to array in serverData object
				Book bookData = new Book();
				bookData.setBookName(fieldInput);
				
				//create serverData to send to server
				serverData = new ServerData(operations.searchByBookName, bookData);		
			}
		default:
			break;
		}
		try
		{
			//sending serverData to server, checking it's not null
			if(serverData!=null)
				sendToServer(serverData);
			else
				throw new Exception("serverData is null");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loginUser(String ID, String Password)
	{
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(ID);
    	List.add(Password);
		ServerData loginInfo = new ServerData(IGeneralData.operations.Login,List);
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


}
