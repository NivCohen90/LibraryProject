package OBLFX;


import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;
import java.io.IOException;

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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginFormController implements IGUIcontroller{
	
	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	final static String WrongDataMsg = "wrong User Name or Password.";
	private String UserName;
	private String Password;
	private CommonHandler commonClient;
	
	@FXML
	public void initialize() {
		try 
		{
			commonClient = new CommonHandler(this);
		} 
		catch (IOException e) 
		{
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
    	IGUIcontroller.CheckOnlyNumbers(UserNameText, UserNameAlertLabel,9,UserNameErrorDigits);
	}
	@FXML
	void Login(ActionEvent event) {
		if	(UserNameText.getText().length() != 9 || !UserNameText.getText().matches(OnlyNumbers) || PasswordText.getText().length() == 0) {
			WrongData.setText(WrongDataMsg);
		}
		else 
		{ 
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
			SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.SubscriberMenu);
			Main.root.setLeft(sideMenu.getVBox());
			break;
		case returnLibrarian:
			break;
		case returnLibrarianManager:
			break;
		case returnError:
			WrongData.setText((String) msg);
			break;
		default:
		}
		
	}
}
