package OBLFX;

import Client.LibrarianHandler;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Subscriber;
import Users.Librarian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author Matan
 * AddNewSubscriberController controls CreateNewSubscriberFXML
 * @param AreaCodeFlag is a flag that show if the user pushed this Button
 */
public class AddNewSubscriberController implements IGUIcontroller {

	final static String AreaCodeError = "Please choose area phone code.";
	private LibrarianHandler librarianClient;
	private String PhoneNum;
	boolean AreaCodeFlag = false;
	private static ObservableList<String> List;

	@FXML
	private TextField EmailTextField;

	@FXML
	private TextField IDTextField;

	@FXML
	private TextField FirstNameTextFiled;

	@FXML
	private TextField LastNameTextFiled;

	@FXML
	private TextField PhoneNumberTextFiled;

	@FXML
	private TextField PasswordTextFiled;

	@FXML
	private Button CreateNewSubscriberBTN;

	@FXML
	private ComboBox<String> AreaCodeTextFiled;

	@FXML
	private Label IDAlertLabel;

	@FXML
	private Label FirstNameLabel;

	@FXML
	private Label LastNameLabel;

	@FXML
	private Label EmailLabel;

	@FXML
	private Label PhoneNumberLabel;

	@FXML
	private Label PasswordLabel;

	@FXML
	private Label ErrorAtCreatSubscriberLabel;
    
    /**
     * this method update flag to notice that areacode was choose
    */
	@FXML
	private void CheckChoose(ActionEvent event) {
		AreaCodeFlag = true;
	}
    /**
     * CheckEmail is a method that check if the user put input.,if he didn't gave input the method will alert the user.
    */
    @FXML
    void CheckEmail(KeyEvent event) {
    	IGUIcontroller.CheckIfUserPutInput(EmailTextField,EmailLabel);
    }
    /**
     * CheckFirstName is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z].
    */
	@FXML
	void CheckFirstName(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(FirstNameTextFiled, FirstNameLabel);
		IGUIcontroller.CheckOnlyLetter(FirstNameTextFiled, FirstNameLabel, OnlyLetters, OnlyLetterError);
		
	}
    /**
     * CheckLastName is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z].
    */
	@FXML
	void CheckLastName(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(LastNameTextFiled, LastNameLabel);
		IGUIcontroller.CheckOnlyLetter(LastNameTextFiled, LastNameLabel, OnlyLetters, OnlyLetterError);
		
	}
    /**
     * CheckEmail is a method that check if the user put input.,if he didn't gave input the method will alert the user.
    */
    @FXML
    void CheckPassword(KeyEvent event) {
    	IGUIcontroller.CheckIfUserPutInput(PasswordTextFiled,PasswordLabel);
    }
    /**
     * CheckIDInput is a method that check if the user put input and if that the input length is 9.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
	@FXML
	void CheckIDInput(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(IDTextField, IDAlertLabel, 9, UserNameErrorDigits);
	}
    /**
     * CheckPhoneNumber is a method that check if the user put input and if that the input length is 7.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
	@FXML
	private void CheckPhoneNumber(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextFiled, PhoneNumberLabel, 7, PhoneNumberErrorDigits);
	}
    /**
     * CreateNewSubscriberBtn is a method that check if all of the field is filled 
    */
	@FXML
	private void CreateNewSubscriberBtn(ActionEvent event) {
		int counter = 0;
		if (IGUIcontroller.CheckOnlyNumbers(IDTextField, IDAlertLabel, 9, UserNameErrorDigits)) {
			counter++;
		} else {
			IDAlertLabel.setText(fillThisArea);
		}
		if (FirstNameTextFiled.getText().length() > 0) {
			counter++;
			FirstNameLabel.setText("");
		} else {
			FirstNameLabel.setText(fillThisArea);
		}
		if (LastNameTextFiled.getText().length() > 0) {
			counter++;
			LastNameLabel.setText("");
		} else {
			LastNameLabel.setText(fillThisArea);
		}
		if (EmailTextField.getText().length() > 0) {
			if (EmailTextField.getText().contains("@") && EmailTextField.getText().contains(".")) {
				counter++;
				EmailLabel.setText("");
			} else
				EmailLabel.setText("Email wrong input");
		} else {
			EmailLabel.setText(fillThisArea);
		}
		if (IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextFiled, PhoneNumberLabel, 7, PhoneNumberErrorDigits)) {
			counter++;
		} else {
			PhoneNumberLabel.setText(fillThisArea);
		}
		if (PasswordTextFiled.getText().length() > 0) {
			counter++;
			PasswordLabel.setText("");
		} else {
			PasswordLabel.setText(fillThisArea);
		}
		if (AreaCodeFlag == true) {
			if (counter == 6) {
				PhoneNum = "" + AreaCodeTextFiled.getPromptText() + PhoneNumberTextFiled.getText();
				Subscriber sub = new Subscriber(IDTextField.getText(), FirstNameTextFiled.getText(), LastNameTextFiled.getText(), EmailTextField.getText(), PhoneNum, PasswordTextFiled.getText(),null, "Active", 0);
				librarianClient.createNewSubscriber(sub,GeneralData.userLibrarian); 

			}
		} else
			PhoneNumberLabel.setText(AreaCodeError);

	}
    /**
     * Update User with the result
    */ 
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
	switch(op) {
	case returnError:
		ErrorAtCreatSubscriberLabel.setText((String) msg);
		break;
	case returnException:
		IAlert.ExceptionAlert((Exception)msg);
		default:
		ErrorAtCreatSubscriberLabel.setText("New subscriber was added successfully");
		break;
	}

	}

	/*************************************************************
	 * 
	 * Description: Initialize.
	 * 
	 *
	 ************************************************************/
	/**
	 * Add the code area to the list
	 */
	@FXML
	public void initialize() {
		List = FXCollections.observableArrayList();
		List.add("050");
		List.add("052");
		List.add("054");
		List.add("055");
		List.add("058");
		AreaCodeTextFiled.setItems(List);
	}
	@Override
	public void setConnection() {
		librarianClient = new LibrarianHandler(this);
	}
	@Override
	public void closeConnection() {
		if(librarianClient!=null)
			librarianClient.quit();	
	}
}
