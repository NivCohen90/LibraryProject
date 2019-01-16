package Client;


import java.io.IOException;
import OBLFX.IGUIcontroller;


public class SubscriberHandler extends IHandler{
	
	public SubscriberHandler(IGUIcontroller guiController) throws IOException {
		super();
		currentControllerGUIobj = guiController;
	}
	
	public void updateDetails() {}
	
	public void extendLoad() {}
	
	public void orderBook() {}

}
