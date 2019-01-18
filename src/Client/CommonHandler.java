package Client;

import Users.ServerData;
import java.io.IOException;
import java.util.ArrayList;

import OBLFX.IGUIcontroller;
import Users.IGeneralData;

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
	public void searchBookInServer(String fieldInput, IGeneralData.operations searchType)
	{
		ServerData serverData;
		switch (searchType) {
		case searchByBookName:
		case searchByBookAuthor:
		case searchByBookSubject:
			serverData = new ServerData(searchType, fieldInput);
			break;
		case searchByBookDescription:
			//smart search
			serverData = new ServerData(searchType, fieldInput);
			break;
		default:
			serverData = new ServerData(searchType, fieldInput);
			break;
		}
		try {
			sendToServer(serverData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loginUser(String ID, String Password)
	{
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(ID);
    	List.add(Password);
		ServerData loginInfo = new ServerData(IGeneralData.operations.Login, ID, Password);
		try
		{
			//sending serverData to server, checking it's not null
			if(loginInfo!=null) {
				sendToServer(loginInfo);
			}
			else
				throw new Exception("loginInfo is null");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
