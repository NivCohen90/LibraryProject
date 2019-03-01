package unitTests;

import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;
import javafx.application.Platform;

public class GUIStub implements IGUIcontroller{

	//for checking if correct user in login, otherwise variable is null 
	Subscriber loggedinSubscriber = null;
	Librarian loggedinLibrarian = null;
	String errorMessage = null;
	
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		switch(op)
		{
			case returnSubscriber:
				loggedinSubscriber = (Subscriber) msg;
				break;
			case returnLibrarian:
				loggedinLibrarian = (Librarian) msg;
				break;
			case returnError:
				errorMessage = (String) msg;
				break;
		}
	}

	@Override
	public void setConnection() {
		
	}

	@Override
	public void closeConnection() {
		
	}

}
