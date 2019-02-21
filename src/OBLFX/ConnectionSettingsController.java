package OBLFX;

import Client.SideMenu;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ConnectionSettingsController implements IGUIcontroller {
	final static String GREEN_COLOR = "-fx-control-inner-background: #1AFE01";
	final static String RED_COLOR = "-fx-control-inner-background: RED";
	static String IPAddress = "localhost";
	static int PortNumber = 5555;
	public static boolean ConnectedFLAG = false;

	@FXML
	private TextField ServerIPAddress;

	@FXML
	private TextField Port;

	@FXML
	private TextField ConnectionStatusTXTField;
    
    @FXML
    private TextArea ExceptionMsg;
    
    @FXML
    private Button ChangeIPandPort;

    @FXML
    private Button SaveChanges;

    @FXML
    private Button Cancel;

    @FXML
    void CancelChanges(ActionEvent event) {
    	setServerIPAddress(IPAddress);
    	setPort(PortNumber);
    	Cancel.setVisible(false);
    	SaveChanges.setVisible(false);
    	ChangeIPandPort.setVisible(true);
    	TextField ServerIPAddress = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ServerIPAddress");
    	ServerIPAddress.setEditable(false);
    	TextField Port = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#Port");
    	Port.setEditable(false);
    }

    @FXML
    void ChangeServerIPandPort(ActionEvent event) {
    	TextField ServerIPAddress = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ServerIPAddress");
    	ServerIPAddress.setEditable(true);
    	TextField Port = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#Port");
    	Port.setEditable(true);
    	Cancel.setVisible(true);
    	SaveChanges.setVisible(true);
    	ChangeIPandPort.setVisible(false);
    	IPAddress = getServerIPAddress();
    	PortNumber = getPort();
    }

    @FXML
    void SaveIPandPortChanges(ActionEvent event) {
    	Cancel.setVisible(false);
    	SaveChanges.setVisible(false);
    	ChangeIPandPort.setVisible(true);
    	TextField ServerIPAddress = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ServerIPAddress");
    	ServerIPAddress.setEditable(false);
    	TextField Port = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#Port");
    	Port.setEditable(false);
    	IPAddress = getServerIPAddress();
    	PortNumber = getPort();
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
	
	public void setServerIPAddress(String IP) {
		TextField ServerIPAddress = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#ServerIPAddress");
		ServerIPAddress.setText(IP);
	}
	
	public void setPort(int PortNumber) {
		TextField Port = (TextField) SideMenu.APConnectionSettingsFXML.lookup("#Port");
		Port.setText(Integer.toString(PortNumber));
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	public int getPortNumber() {
		return PortNumber;
	}
	public boolean getConnectedFlag() {
		return ConnectedFLAG;
	}
	public void setConnectedFlag(boolean condition) {
		ConnectedFLAG = condition;
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void initialize() {
		if (ConnectedFLAG) {			
			ConnectionStatusTXTField.setText("Connected");
			ConnectionStatusTXTField.setStyle(GREEN_COLOR);
		} else {
			ConnectionStatusTXTField.setText("Disconnected");
			ConnectionStatusTXTField.setStyle(RED_COLOR);
		}
		ServerIPAddress.setText(IPAddress);
		Port.setText(Integer.toString(PortNumber));
	}

}
