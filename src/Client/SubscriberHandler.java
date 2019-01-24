package Client;

import java.io.IOException;
import java.util.ArrayList;

import OBLFX.ConnectionSettingsController;
import OBLFX.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.IGeneralData;
import SystemObjects.ServerData;
import SystemObjects.IGeneralData.operations;
import Users.Subscriber;

/**
 * class client for subscriber methods
 * @author ofir
 */
public class SubscriberHandler extends IHandler {
	public static ConnectionSettingsController conn = new ConnectionSettingsController();

	public SubscriberHandler(IGUIcontroller guiController) {
		super(conn.getServerIPAddress(), conn.getPort());
		currentControllerGUIobj = guiController;
	}

	public void updateDetails(Object user, Object userEdit) {
		ArrayList<Object> List = new ArrayList<Object>();
		List.add(user);
		List.add(userEdit);
		ServerData loginInfo = new ServerData(IGeneralData.operations.updatePersonalDetails, List);
		try {
			sendToServer(loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void extendLoan() {
	}

	/**
	 * send order request to server
	 * @param subscriberOrdered subscriber that make the order
	 * @param orderedBook book subscriber ordered
	 * @param orderDate	date order is made
	 */
	public void orderBook(Subscriber subscriberOrdered, Book orderedBook, String orderDate) {
		ServerData serverData = new ServerData(operations.orderBook, subscriberOrdered, orderedBook, orderDate);
		try {
			sendToServer(serverData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
