package Client;

import java.io.IOException;
import java.time.LocalDate;

import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import Interfaces.IHandler;
import OBLFX.ConnectionSettingsController;
import SystemObjects.ServerData;
<<<<<<< HEAD
import Users.Subscriber;
=======
import SystemObjects.GeneralData.operations;
>>>>>>> branch 'master' of https://github.com/NivCohen90/LibraryProject.git


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
	
	
	public void changeSubscriberStatus(Subscriber sub, String status) {
		
		ServerData data= new ServerData(operations.changeSubscriberStatus, sub, status);//

		try {
			
			sendToServer(data);
			
		} catch (IOException e) {
			Interfaces.IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}	}
	
	
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
<<<<<<< HEAD
			Interfaces.IAlert.ExceptionAlert(e);
=======
			IAlert.ExceptionAlert(e);
>>>>>>> branch 'master' of https://github.com/NivCohen90/LibraryProject.git
			e.printStackTrace();
		}
	}
}
