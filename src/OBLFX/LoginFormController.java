package OBLFX;

import Users.Librarian;
import Users.Subscriber;

import java.io.IOException;
import java.util.ArrayList;

import Client.CommonHandler;
import Client.Main;
import Client.SideMenu;
import OBLFX.IGUIcontroller;
import SystemObjects.IGeneralData;
import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.IGeneralData.operationsReturn;
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
			sideMenu = new SideMenu(IGeneralData.MenuType.SubscriberMenu);
			Main.root.setLeft(sideMenu.getVBox());
			Main.root.setRight(SideMenu.APReaderCardFXML);
			SubscriberCardController a = new SubscriberCardController();
			String FullName = ((Subscriber) msg).getFirstName() + " " + ((Subscriber) msg).getLastName();
			String ID = ((Subscriber) msg).getID();
			String Email = ((Subscriber) msg).getEmail();
			String Status = ((Subscriber) msg).getStatus();
			String SubNumber = ((Subscriber) msg).getSubscriberNumber();
			String PhoneNumber = ((Subscriber) msg).getPhoneNumber();
			ArrayList<Loan> loans = ((Subscriber) msg).getActiveLoans();
			ArrayList<Order> orders = ((Subscriber) msg).getActiveOrders();
			a.setSubscriberCard(FullName, PhoneNumber, ID, Email, Status, SubNumber, loans, orders);
			SubscriberHistoryController subHistoryCon = new SubscriberHistoryController();
			subHistoryCon.setSubscriberHistory((Subscriber) msg);

			break;
		case returnLibrarian:
			sideMenu = new SideMenu(IGeneralData.MenuType.LibrarianMenu);
			Main.root.setLeft(sideMenu.getVBox());
			Main.root.setRight(SideMenu.APReaderCardFXML);
			break;
		case returnLibrarianManager:
			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					try {
						SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.LibrarianManagerMenu);
						Main.root.setLeft(sideMenu.getVBox());
						AnchorPane pane;
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
						Main.root.setRight(pane);

						CardLibrarianController librarianCon = new CardLibrarianController();
						librarianCon.setLibrarianToDisplay((Librarian) msg);

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
