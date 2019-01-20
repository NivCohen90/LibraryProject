package Client;

import java.io.IOException;
import java.util.ArrayList;

import OBLFX.IGUIcontroller;
import Users.Book;
import Users.IGeneralData;
import Users.ServerData;
import Users.Subscriber;
import Users.IGeneralData.operations;

public class SubscriberHandler extends IHandler {

	public SubscriberHandler(IGUIcontroller guiController) throws IOException {
		super();
		currentControllerGUIobj = guiController;
	}

	public void updateDetails(Object user, Object userEdit) {
		ArrayList<Object> List = new ArrayList<Object>();
		List.add(user);
		List.add(userEdit);
		ServerData loginInfo = new ServerData(IGeneralData.operations.updatePersonalDetails, List);
		try {
			// sending serverData to server, checking it's not null
			if (loginInfo != null)
				sendToServer(loginInfo);
			else
				throw new Exception("loginInfo is null");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void extendLoad() {
	}

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
