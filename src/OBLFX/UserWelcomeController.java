package OBLFX;

import java.io.IOException;

import Client.Main;
import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserWelcomeController implements IGUIcontroller {
    @FXML
    private Label UserName;

    @FXML
    private Button Logout;

    @FXML
    void Logout(ActionEvent event) {
    	SideMenu sideMenu = new SideMenu(GeneralData.MenuType.MainMenu);
		Main.root.setLeft(sideMenu.getVBox());
		AnchorPane pane;
		
		try {
			pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen));
			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			Main.root.setRight(pane);
			if(GeneralData.userSubscriber!=null)
				GeneralData.userSubscriber=null;
			if(GeneralData.userLibrarian!=null)
				GeneralData.userLibrarian=null;
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
		}
		UserName.setText("Guest");
		Logout.setVisible(false);

    }
    
	@FXML
	public void initialize() {
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}