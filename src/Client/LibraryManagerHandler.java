package Client;
import java.sql.Date;
import java.util.ArrayList;

import OBLFX.ConnectionSettingsController;
import OBLFX.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.IGeneralData;
import SystemObjects.ServerData;
import Users.Subscriber;
import Users.Librarian;

/**
 * class client for library Manager methods
 * @author Liad
 *
 */


public class LibraryManagerHandler extends IHandler{
	public static ConnectionSettingsController conn = new ConnectionSettingsController();

	public LibraryManagerHandler(IGUIcontroller guiController){
		super(conn.getServerIPAddress(), conn.getPort());
		currentControllerGUIobj = guiController;
	}
	
	
	 
}
