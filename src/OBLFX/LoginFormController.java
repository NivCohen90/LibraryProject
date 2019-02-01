package OBLFX;

import Users.Librarian;
import Users.Subscriber;

import java.io.IOException;
import java.util.ArrayList;

import Client.CommonHandler;
import Client.Main;
import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.GeneralData.Menuicons;
import SystemObjects.GeneralData.operationsReturn;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Matan,Niv
 * LoginFormController controls LoginFormFXML
 */
public class LoginFormController implements IGUIcontroller {
	public static CommonHandler commonClient;
	final static String WrongDataMsg = "wrong User Name or Password.";
	private String UserName;
	private String Password;

	@FXML
	public void initialize() {
	}

	@FXML
	private TextField UserNameText;

	@FXML
	private Button LoginButton;

	@FXML
	private PasswordField PasswordText;

	@FXML
	private Label UserNameAlertLabel;

	@FXML
	private Label WrongData;

	@FXML
	/**
	 * CheckUserNameInput is a method that check if the user put input,if he put
	 * input check if its exactly 9.,if he didn't gave input the method will alert
	 * the user. this method also check that all this field only contains this
	 * letters[0-9].
	 */
	void CheckUserNameInput(KeyEvent event) {
		WrongData.setText("");
		IGUIcontroller.CheckOnlyNumbers(UserNameText, UserNameAlertLabel, 9, UserNameErrorDigits);
	}

	/**
	 * Login is a method that check if all off the fields are filled
	 */
	@FXML
	void Login(ActionEvent event) {
		if (UserNameText.getText().length() != 9 || !UserNameText.getText().matches(OnlyNumbers)
				|| PasswordText.getText().length() == 0) {
			WrongData.setText(WrongDataMsg);
		} else {
			UserName = UserNameText.getText();
			Password = PasswordText.getText();
			PasswordText.clear();
			UserNameText.clear();
			WrongData.setText("");
			UserNameAlertLabel.setText("");
			commonClient.loginUser(UserName, Password);

		}
	}

	/**
	 * Update User with the result and update system with user details
	 */
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		SideMenu sideMenu;
		switch (op) {
		case returnSubscriber:
			sideMenu = new SideMenu(GeneralData.MenuType.SubscriberMenu);
			Main.root.setLeft(sideMenu.getVBox());
			SideMenu.APReaderCardFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			Main.root.setRight(SideMenu.APReaderCardFXML);
			
			SubscriberCardController subCon = new SubscriberCardController();
			subCon.setSubscriberCard((Subscriber) msg);
			SideMenu.controllerMap.get(Menuicons.SubscriberCard).setConnection();
			
			SubscriberHistoryController subHistoryCon = new SubscriberHistoryController();
			subHistoryCon.setSubscriberHistory((Subscriber) msg);
			
			GeneralData.userSubscriber = ((Subscriber) msg);

			break;
		case returnLibrarian:
			sideMenu = new SideMenu(GeneralData.MenuType.LibrarianMenu);
			Main.root.setLeft(sideMenu.getVBox());	
			SideMenu.APCardLibrarianFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			Main.root.setRight(SideMenu.APCardLibrarianFXML);
					
			CardLibrarianController librarianCon = new CardLibrarianController();
			librarianCon.setLibrarianToDisplay((Librarian) msg);
			GeneralData.userLibrarian = ((Librarian) msg);
			
			break;
		case returnLibrarianManager:
			sideMenu = new SideMenu(GeneralData.MenuType.LibrarianManagerMenu);
			Main.root.setLeft(sideMenu.getVBox());	
			SideMenu.APCardLibrarianManagerFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			Main.root.setRight(SideMenu.APCardLibrarianManagerFXML);
					
			CardLibrarianManagerController librarianManCon = new CardLibrarianManagerController();
			librarianManCon.setLibrarianToDisplay((Librarian) msg);
			GeneralData.userLibrarian = ((Librarian) msg);
			break;
		case returnError:
			((Label) SideMenu.APLoginFXML.lookup("#WrongData")).setText((String) msg);
			break;
		case returnException:
			IAlert.ExceptionAlert((Exception)msg);
			break;
		default:
		}

	}

	@Override
	public void setConnection() {
		commonClient = new CommonHandler(this);
	}

	@Override
	public void closeConnection() {
		if (commonClient != null)
			commonClient.quit();
	}

}
