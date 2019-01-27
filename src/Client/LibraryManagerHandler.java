package Client;

import java.io.IOException;
import java.time.LocalDate;

import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import Interfaces.IHandler;
import OBLFX.ConnectionSettingsController;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operations;


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
	
	
public void createReport(LocalDate startDate, LocalDate endDate, operations reportType) {
		
		ServerData report;
		
		switch(reportType) {
		case createActivityReport:{
			report= new ServerData(reportType, startDate, endDate);
			break;
		}
		default:
			report= new ServerData(reportType, startDate);
		}
		try {
			sendToServer(report);
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}
}
