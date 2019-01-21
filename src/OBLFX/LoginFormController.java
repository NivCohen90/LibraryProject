package OBLFX;

import Users.IGeneralData;
import Users.Loan;
import Users.LoansTable;
import Users.Order;
import Users.IGeneralData.Menuicons;
import Users.IGeneralData.operationsReturn;
import Users.ServerData;
import Users.Subscriber;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSpinnerUI;

import Client.CommonHandler;
import Client.Main;
import Client.SideMenu;
import Client.WindowButtons;
import OBLFX.IGUIcontroller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginFormController implements IGUIcontroller {

	final static String WrongDataMsg = "wrong User Name or Password.";
	private String UserName;
	private String Password;
	private CommonHandler commonClient;

	@FXML
	public void initialize() {
		try {
			commonClient = new CommonHandler(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	void CheckUserNameInput(KeyEvent event) {
		WrongData.setText("");
		IGUIcontroller.CheckOnlyNumbers(UserNameText, UserNameAlertLabel, 9, UserNameErrorDigits);
	}

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

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch (op) {
		case returnSubscriber:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					try {
						SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.SubscriberMenu);
						sideMenu.setclicked(Menuicons.SubscriberCard);
						Main.root.setLeft(sideMenu.getVBox());
						AnchorPane pane;
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
						Main.root.setRight(pane);
					} catch (IOException e) {
						IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(),
								e.getMessage());
					}
				}
			});
			SubscriberCardController a = new SubscriberCardController();
			String FullName = ((Subscriber) msg).getFirstName() + " " + ((Subscriber) msg).getLastName();
			String ID = ((Subscriber) msg).getID();
			String Email = ((Subscriber) msg).getEmail();
			String Status = ((Subscriber) msg).getStatus();
			String SubNumber = ((Subscriber) msg).getSubscriberNumber();
			ArrayList<Loan> loans = ((Subscriber) msg).getLoans();
			a.setSubscriberCard(FullName, ID, Email, Status, SubNumber, loans, null);
			break;
		case returnLibrarian:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					try {
						SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.LibrarianMenu);
						sideMenu.setclicked(Menuicons.LibrarianCard);
						Main.root.setLeft(sideMenu.getVBox());
						AnchorPane pane;
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
						Main.root.setRight(pane);
					} catch (IOException e) {
						IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(),
								e.getMessage());
					}
				}
			});
			break;
		case returnLibrarianManager:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					try {
						SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.LibrarianManagerMenu);
						sideMenu.setclicked(Menuicons.ManagerCard);
						Main.root.setLeft(sideMenu.getVBox());
						AnchorPane pane;
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
						Main.root.setRight(pane);
					} catch (IOException e) {
						IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(),
								e.getMessage());
					}
				}
			});
			break;
		case returnError:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					WrongData.setText((String) msg);
				}
			});

			break;
		default:
		}

	}
}
