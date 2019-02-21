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
 * 
 * @author Liad
 *
 */

public class LibraryManagerHandler extends IHandler {
	public static ConnectionSettingsController conn = new ConnectionSettingsController();

	public LibraryManagerHandler(IGUIcontroller guiController) {
		super(conn.getIPAddress(), conn.getPortNumber());
		currentControllerGUIobj = guiController;
	}

	public void createReport(LocalDate startDate, LocalDate endDate, operations reportType) {

		ServerData report = null;

		switch (reportType) {
		case createActivityReport:
			try {
				report = new ServerData(reportType, startDate, endDate);
				sendToServer(report);
			} catch (IOException e) {

				Interfaces.IAlert.ExceptionAlert(e);

				IAlert.ExceptionAlert(e);

				e.printStackTrace();
			}
			break;
		case createLoansReport:
		case createLateReturnsReport:
			try {
				report = new ServerData(reportType, startDate);
				sendToServer(report);
			} catch (IOException e) {

				Interfaces.IAlert.ExceptionAlert(e);

				IAlert.ExceptionAlert(e);

				e.printStackTrace();
			}
		default:
			break;
		}
	}
}
