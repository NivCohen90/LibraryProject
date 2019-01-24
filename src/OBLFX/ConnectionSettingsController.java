package OBLFX;

import Client.SideMenu;
import SystemObjects.IGeneralData.operationsReturn;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class ConnectionSettingsController implements IGUIcontroller {

	public static String GREEN_COLOR = "-fx-control-inner-background: #1AFE01";
	public static String RED_COLOR = "-fx-control-inner-background: RED";
	public boolean ConnectedFLAG = false;

	@FXML
	private TextField ServerIPAddress;

	@FXML
	private TextField Port;

	@FXML
	private TextField ConnectionStatusTXTField;
    
    @FXML
    private TextArea ExceptionMsg;

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setConnection() {
		TextField ConnectionStatusTXTField = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ConnectionStatusTXTField");
		if (ConnectedFLAG) {			
			ConnectionStatusTXTField.setText("Connected");
			ConnectionStatusTXTField.setStyle(GREEN_COLOR);
		} else {
			ConnectionStatusTXTField.setText("Disconnected");
			ConnectionStatusTXTField.setStyle(RED_COLOR);
		}
	}
	
	public String getServerIPAddress() {
		TextField ServerIPAddress = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ServerIPAddress");
		return ServerIPAddress.getText();
	}
	
	public int getPort() {
		TextField Port = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#Port");
		return Integer.parseInt(Port.getText());
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void initialize() {
	}

}
