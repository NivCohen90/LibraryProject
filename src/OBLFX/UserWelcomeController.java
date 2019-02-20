package OBLFX;

import java.io.IOException;

import Client.Delta;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserWelcomeController implements IGUIcontroller {

	final Delta dragDelta = new Delta();

	@FXML
	private Label UserName;

	@FXML
	private Button Logout;

	@FXML
	private Label txt2;

	@FXML
	private Label txt1;

	@FXML
	void Logout(ActionEvent event) {
		SideMenu sideMenu = new SideMenu(GeneralData.MenuType.MainMenu);
		Main.root.setLeft(sideMenu.getVBox());
		AnchorPane pane;

		try {
			pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen));
			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			Main.root.setRight(pane);
			if (GeneralData.userSubscriber != null)
				GeneralData.userSubscriber = null;
			if (GeneralData.userLibrarian != null)
				GeneralData.userLibrarian = null;
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
		}
		UserName.setText("Guest");
		Logout.setVisible(false);

	}

	@FXML
	void MouseDragged(MouseEvent event) {
		if (event.getButton() != MouseButton.MIDDLE) {
			Main.PrimaryStage.getScene().getWindow().setX(event.getScreenX() - dragDelta.getX());
			Main.PrimaryStage.getScene().getWindow().setY(event.getScreenY() - dragDelta.getY());
		}
	}

	@FXML
	void MousePressed(MouseEvent event) {
		if (event.getButton() != MouseButton.MIDDLE) {
			dragDelta.setX(event.getSceneX());
			dragDelta.setY(event.getSceneY());
		}
	}

	@FXML
	public void initialize() {
	}

	public void setCatalog() {
		txt1.setVisible(false);
		txt2.setText("Manage Catalog");
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