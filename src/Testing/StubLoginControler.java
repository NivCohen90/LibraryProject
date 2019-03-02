package Testing;

import Client.CommonHandler;
import Client.Main;
import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;
import OBLFX.CardLibrarianController;
import OBLFX.CardLibrarianManagerController;
import OBLFX.SubscriberCardController;
import OBLFX.SubscriberHistoryController;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.Menuicons;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StubLoginControler implements IGUIcontroller{

	private String massage;
	public static CommonHandler commonClient;
	public StubLoginControler() {};
	@Override
	public  void receiveMassageFromServer(Object msg, operationsReturn op) {
		// TODO Auto-generated method stub
		switch (op) {
		case returnSubscriber:
			massage="Subscriber "+((Subscriber)msg).getSubscriberNumber()+" "+((Subscriber)msg).getPassword();
			break;
		case returnLibrarian:
			massage="Librarian "+((Librarian)msg).getID()+" "+((Librarian)msg).getPassword();
			break;
		case returnLibrarianManager:
			massage="Librarian Manager "+((Librarian)msg).getID()+" "+((Librarian)msg).getPassword();
			break;
		case returnError:
			massage=(String)msg;
			break;
		case returnException:
			massage = ((Exception)msg).toString();
			break;
		default:
		}
	}
	public String GetMassage()
	{
		return massage;
	}

	@Override
	public void setConnection() {
		commonClient = new CommonHandler(this);
	}

	@Override
	public void closeConnection() {
		if (commonClient != null)
			commonClient.quit();
	}
}


