package OBLFX;

import java.net.URL;
import java.util.ResourceBundle;

import Users.Subscriber;
import Users.IGeneralData.operationsReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

public class AddNewSubscriberController implements IGUIcontroller, Initializable {

	
	final static String AreaCodeError = "Please choose area phone code.";
	final static String fillThisArea = " Fill this Area";
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

	@FXML
	private void CheckChoose(ActionEvent event) {
		AreaCodeFlag = true;
	}
    @FXML
    void CheckFirstName(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(FirstNameTextFiled,FirstNameLabel);
    }


    @FXML
    void CheckLastName(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(LastNameTextFiled,LastNameLabel);
    }
	@FXML
	void CheckIDInput(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(IDTextField, IDAlertLabel,9,UserNameErrorDigits);
	}

	@FXML
	private void CheckPhoneNumber(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextFiled,PhoneNumberLabel,7,PhoneNumberErrorDigits);
	}

	@FXML
	private void CreateNewSubscriberBtn(ActionEvent event) {
		int counter = 0;
		if (IGUIcontroller.CheckOnlyNumbers(IDTextField, IDAlertLabel,9,UserNameErrorDigits)) {
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
		if (IGUIcontroller.CheckOnlyNumbers(PhoneNumberTextFiled,PhoneNumberLabel,7,PhoneNumberErrorDigits)) {
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
				Subscriber sub = new Subscriber(FirstNameTextFiled.getText(), LastNameTextFiled.getText(),
						EmailTextField.getText(), IDTextField.getText(), PasswordTextFiled.getText(), "Active",
						PhoneNum, "0");
//createNewSubscriber(Subscriber sub,Librarian Me);

			}
		} else
			PhoneNumberLabel.setText(AreaCodeError);

	}


	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch (op) {
		case returnError:
			ErrorAtCreatSubscriberLabel.setText((String) msg);
			break;
		default:

		}

	}

	/*************************************************************
	 * 
	 * Description: Initialize.
	 * 
	 *
	 ************************************************************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List = FXCollections.observableArrayList();
		List.add("050");
		List.add("052");
		List.add("054");
		List.add("055");
		List.add("058");
		AreaCodeTextFiled.setItems(List);
	}
}
