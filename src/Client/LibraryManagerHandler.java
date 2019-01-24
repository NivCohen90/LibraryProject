package Client;

import java.io.IOException;
import java.time.LocalDate;

import OBLFX.ConnectionSettingsController;
import OBLFX.IGUIcontroller;

import SystemObjects.ServerData;
import SystemObjects.IGeneralData.operations;


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
			e.printStackTrace();
		}
	}
}
