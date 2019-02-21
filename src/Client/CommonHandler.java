package Client;

import java.io.IOException;
import java.util.ArrayList;

import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import Interfaces.IHandler;
import OBLFX.ConnectionSettingsController;
import SystemObjects.GeneralData;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operations;
import Users.Subscriber;

/**
 * class client for common methods for librarian and subscriber
 * will communicate with server
 * @author ofir
 */

public class CommonHandler extends IHandler{
	
	public static ConnectionSettingsController conn = new ConnectionSettingsController();
	public CommonHandler(IGUIcontroller guiController){
		//currentControllerGUIobj defined in IHandler interface, will save the GUI controller input was sent from
		super(conn.getIPAddress(), conn.getPortNumber());
		currentControllerGUIobj = guiController;	 
	}

	/**
	 * method to send search book request to server
	 * @param fieldInput the input text in search
	 * @param searchType what kind of search the server will do
	 */
	public void searchInServer(String searchInput, operations searchType) {
		ServerData serverData = new ServerData(searchType, searchInput);
		try {
			sendToServer(serverData);
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * send login request to server
	 * @param ID id of user
	 * @param Password password of user
	 */
	public void loginUser(String ID, String Password)
	{
		ArrayList<Object> List = new ArrayList<Object>();
    	List.add(ID);
    	List.add(Password);
		ServerData loginInfo = new ServerData(GeneralData.operations.Login, ID, Password);
		try
		{
			sendToServer(loginInfo);
		}
		catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}

	public void changeSubscriberDetails(Subscriber sub) {
		
		ServerData data= new ServerData(operations.updateSubscriberDetails, sub);

		try {
			
			sendToServer(data);
			
		} catch (IOException e) {
			Interfaces.IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	}
}
