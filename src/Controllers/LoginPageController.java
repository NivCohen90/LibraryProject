package Controllers;

// Matan working here!

import java.io.IOException;

import Client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;

public class LoginPageController {


	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	final static String WrongDataMsg = "wrong User Name or Password.";
	
	@FXML
	private TextField UserNameLabel;

	@FXML
	private Button LoginButton;

	@FXML
	private PasswordField PasswordLabel;

	@FXML
	private Label UserNameAlertLabel;

	@FXML
	private Label WrongData;

	@FXML
	void CheckUserNameInput(KeyEvent event) {

		if (UserNameLabel.getText().length() > 9) {
			
			UserNameAlertLabel.setText(UserNameErrorDigits);
			
		} else if (!UserNameLabel.getText().matches(OnlyNumbers)) {
			
			UserNameAlertLabel.setText(UserNameErrorNumebrs);

		} else 
		{
			
			UserNameAlertLabel.setText("");
		}
	}

	@FXML
	void Login(ActionEvent event) {
		if	(UserNameLabel.getText().length() != 9 || !UserNameLabel.getText().matches(OnlyNumbers) || PasswordLabel.getText().length() == 0) {
			WrongData.setText(WrongDataMsg);
		}
		else 
		{
			PasswordLabel.clear();
			UserNameLabel.clear();
			WrongData.setText("");
			UserNameAlertLabel.setText("");
		}
	}

	
}
