package OBLFX;

import Client.SubscriberHandler;
import Users.IGeneralData.operationsReturn;
import Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * UpdatePersonalDataController controls UpdatePersonalDetailsFXML
 */
public class UpdatePersonalDataController implements IGUIcontroller {

	private SubscriberHandler SubClient;

	@FXML
	private TextField FirstNameTextField;

	@FXML
	private TextField PhoneNumberTextField;

	@FXML
	private TextField SubscriberIDTextField;

	@FXML
	private TextField LastNameTextField;

	@FXML
	private TextField StatusTextField;

	@FXML
	private TextField StudentIDTextField;

	@FXML
	private Button SaveChangeBTN;

	@FXML
	private Label FirstNameLabel;

	@FXML
	private Label StudentIDLabel;

	@FXML
	private Label PhoneNumberLabel;

	@FXML
	private Label SaveChangeMSG;

	@FXML
	private TextField EmailTextField;

	@FXML
	private Label EmailLabel;

	@FXML
	private Label LastNameLabel;
    /**
     * CheckEmail is a method that check if the user put input.,if he didn't gave input the method will alert the user.
    */
	@FXML
	void CheckEmail(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(EmailTextField, EmailLabel);
	}
    /**
     * CheckFirstName is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z].
    */
	@FXML
	void CheckFirstName(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(FirstNameTextField, FirstNameLabel, OnlyLetters, OnlyLetterError);
		IGUIcontroller.CheckIfUserPutInput(FirstNameTextField, FirstNameLabel);
	}
    /**
     * CheckLastName is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z].
    */
	@FXML
	void CheckLastName(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(LastNameTextField, LastNameLabel, OnlyLetters, OnlyLetterError);
		IGUIcontroller.CheckIfUserPutInput(LastNameTextField, LastNameLabel);
	}
    /**
     * CheckPhone is a method that check if the user put input,if user put input its check that is length is 10.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
	@FXML
	void CheckPhone(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextField, PhoneNumberLabel, 10, PhoneNumberErrorDigits);
	}
    /**
     * SaveBTNAction is a method that check if all off the fields are filled
    */
	@FXML
	void SaveBTNAction(ActionEvent event) {
		int count = 0;
		if ((IGUIcontroller.CheckOnlyLetter(FirstNameTextField, FirstNameLabel, OnlyLetters, OnlyLetterError))
				&& IGUIcontroller.CheckIfUserPutInput(FirstNameTextField, FirstNameLabel)) {
			count++;
		}
		if ((IGUIcontroller.CheckOnlyLetter(LastNameTextField, LastNameLabel, OnlyLetters, OnlyLetterError))
				&& (IGUIcontroller.CheckIfUserPutInput(LastNameTextField, LastNameLabel))) {
			count++;
		}
		if ((IGUIcontroller.CheckIfUserPutInput(EmailTextField, EmailLabel))
				&& (EmailTextField.getText().contains("@") && EmailTextField.getText().contains("."))) {
			count++;
		}
		if (IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextField, PhoneNumberLabel, 10, PhoneNumberErrorDigits)) {
			count++;
		}
		if (count == 4) {
			//SubClient.updateDetails(new User(),new User());
		}
	}
    /**
     * Update User with the result
    */  
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		SaveChangeMSG.setText((String) msg);

	}
	@Override
	public void setConnection() {
		SubClient = new SubscriberHandler(this);
		
	}
	@Override
	public void closeConnection() {
		SubClient.quit();
	}

}
