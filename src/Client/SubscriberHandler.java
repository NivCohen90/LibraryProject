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
import SystemObjects.Order;
import Users.Subscriber;

/**
 * class client for subscriber methods
 * @author ofir
 */
public class SubscriberHandler extends IHandler {
	public static ConnectionSettingsController conn = new ConnectionSettingsController();

	public SubscriberHandler(IGUIcontroller guiController) {
		super(conn.getIPAddress(), conn.getPortNumber());
		currentControllerGUIobj = guiController;
	}

	public void updateDetails(String FirstName,String LastName,String PhoneNumber,String Email,Subscriber subscriberDetails) {
		ArrayList<Object> List = new ArrayList<Object>();
		List.add(FirstName);
		List.add(LastName);
		List.add(PhoneNumber);
		List.add(Email);
		List.add(subscriberDetails);;
		ServerData loginInfo = new ServerData(GeneralData.operations.updateSubscriberDetails, List);
		try {
			sendToServer(loginInfo);
		} catch (Exception e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}

	public void extendLoan(String subscriberID, String LoanID) {
		ServerData serverData= new ServerData(operations.extandLoan, subscriberID, LoanID);
		try {
			sendToServer(serverData);
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}

	/**
	 * send order request to server
	 * @param subscriberOrdered subscriber that make the order
	 * @param orderedBook book subscriber ordered
	 * @param orderDate	date order is made
	 */
	 public void orderBook(Order newOrder) {
			ServerData serverData = new ServerData(operations.orderBook, newOrder);
			try {
				sendToServer(serverData);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
	 }

}
