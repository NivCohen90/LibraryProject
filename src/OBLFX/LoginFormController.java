package OBLFX;

import Users.IGeneralData;
import Users.Loan;
import Users.LoansTable;
import Users.Order;
import Users.IGeneralData.Menuicons;
import Users.IGeneralData.operationsReturn;
import Users.Subscriber;

import java.io.IOException;
import java.util.ArrayList;


import Client.CommonHandler;
import Client.Main;
import Client.SideMenu;
import OBLFX.IGUIcontroller;
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
	   /**
     * CheckUserNameInput is a method that check if the user put input,if he put input check if its exactly 9.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
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
		switch (op) {
		case returnSubscriber:
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
					SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.SubscriberMenu);
					sideMenu.setclicked(Menuicons.SubscriberCard);
					Main.root.setLeft(sideMenu.getVBox());
//					SideMenu.APReaderCardFXML.setStyle(FXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(SideMenu.APReaderCardFXML);
					SubscriberCardController a = new SubscriberCardController();			
					String FullName = ((Subscriber) msg).getFirstName() + " " + ((Subscriber) msg).getLastName();
					String ID = ((Subscriber) msg).getID();
					String Email = ((Subscriber) msg).getEmail();
					String Status = ((Subscriber) msg).getStatus();
					String SubNumber = ((Subscriber) msg).getSubscriberNumber();
					String PhoneNumber = ((Subscriber) msg).getPhoneNumber();
					ArrayList<Loan> loans = ((Subscriber) msg).getLoans();
					ArrayList<Order> orders = ((Subscriber) msg).getOrders();
					System.out.println(FullName);
					System.out.println(ID);
					System.out.println(Email);
					System.out.println(Status);
					System.out.println(SubNumber);
					TextField FullNameField = (TextField) SideMenu.APReaderCardFXML.lookup("#FullNameField");
					TextField PhoneNumberField = (TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField");
					TextField IDField = (TextField) SideMenu.APReaderCardFXML.lookup("#IDField");
					TextField EmailField = (TextField) SideMenu.APReaderCardFXML.lookup("#EmailField");
					TextField StatusField = (TextField) SideMenu.APReaderCardFXML.lookup("#StatusField");
					TextField SubscriberNumberField = (TextField) SideMenu.APReaderCardFXML.lookup("#SubscriberNumberField");
					FullNameField.setText(FullName);
					PhoneNumberField.setText(PhoneNumber);
					IDField.setText(ID);
					EmailField.setText(Email);
					StatusField.setText(Status);
					SubscriberNumberField.setText(SubNumber);
					for (Loan iloan : loans) {
						LoansTable loan = new LoansTable("missing", "missing", iloan.getStartDate(), iloan.getReturnDate());
						a.ObservableLoansList.add(loan);
					}
					
//				}
//			});
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
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
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
						pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML));
						pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
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
