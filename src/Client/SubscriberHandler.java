package Client;

import Users.ServerData;
import Users.Subscriber;

import java.io.IOException;

import OBLFX.IGUIcontroller;
import Users.IGeneralData.operations;
import Users.IGeneralData.operationsReturn;

public class SubscriberHandler extends IHandler{
	
	public SubscriberHandler(IGUIcontroller guiController) throws IOException {
		super();
		currentControllerGUIobj = guiController;
	}
	
	public void updateDetails() {}
	
	public void extendLoad() {}
	
	public void orderBook() {}

}
