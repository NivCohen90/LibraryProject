package OBLFX;

import java.sql.Date;

import Client.LibrarianHandler;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * NewLoanController controls NewloanFXML
 * 
 * @param StartFlag and ReturnFlag is a flag that show if the user pushed their
 *                  Buttons
 */
public class NewLoanController implements IGUIcontroller {
	boolean StartFlag = false;
	boolean ReturnFlag = false;

	private LibrarianHandler librarianClient;
	@FXML
	private TextField CatalogNumberTextField;

	@FXML
	private DatePicker StartDateDatePicker;

	@FXML
	private DatePicker ReturnDateDatePicker;

	@FXML
	private TextField SubscriberIDTextField;

	@FXML
	private Button CreateLoanBTN;

	@FXML
	private Label CatalogNumberLabel;

	@FXML
	private Label StartDateLabel;

	@FXML
	private Label SubscriberIDLabel;

	@FXML
	private Label ReturnDateLabel;

	@FXML
	private Label RetriveMSG;

	/**
	 * CheckCatalog is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[0-9].
	 */
	@FXML
	void CheckCatalog(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel);
		IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		
	}

	/**
	 * CheckLoan is a method that check if the user filled all the filed
	 */

	@FXML
	void CheckLoan(ActionEvent event) {
		int counter = 0;
		if(IGUIcontroller.CheckIfUserPutInput(SubscriberIDTextField, SubscriberIDLabel)) {
			if (IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel, 9, UserNameErrorDigits)) {
				counter++;
			}
		}
		if(IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			if(IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,UserNameErrorNumebrs)) {
				counter++;
			}
		}
				if (StartFlag) {
					counter++;
				}
				else {
					StartDateLabel.setText(ChooseDate);	
				}
				if (ReturnFlag) {
					counter++;
				}
				else {
					ReturnDateLabel.setText(ChooseDate);
			}
		if (counter==4) {
			librarianClient.createNewLoan(CatalogNumberTextField.getText(), SubscriberIDTextField.getText(),
					java.sql.Date.valueOf(ReturnDateDatePicker.getValue()),
					java.sql.Date.valueOf(StartDateDatePicker.getValue()));	
		}

	}

	/**
	 * CheckReturnDate is a method that check if the user choose a date an notice it
	 * by a flag
	 */
	@FXML
	void CheckReturnDate(ActionEvent event) {
		if(IGUIcontroller.CheckIfDateIsValid(java.time.LocalDate.now() ,ReturnDateDatePicker.getValue(),ReturnDateLabel)) {
			ReturnFlag = true;
		}	
	}

	/**
	 * CheckStartDate is a method that check if the user choose a date an notice it
	 * by a flag
	 */
	@FXML
	void CheckStartDate(ActionEvent event) {
		if(IGUIcontroller.CheckIfDateIsValid(java.time.LocalDate.now() ,StartDateDatePicker.getValue(),StartDateLabel)) {
			StartFlag = true;
		}
	}

	/**
	 * CheckSubscriberID is a method that check if the user put input and if he put
	 * input that is length is exactly 9.,if he didn't gave input the method will
	 * alert the user. this method also check that all this field only contains this
	 * letters[0-9].
	 */
	@FXML
	void CheckSubscriberID(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(SubscriberIDTextField, SubscriberIDLabel);
		IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel, 9, UserNameErrorDigits);
	}

	/**
	 * Update User with the result
	 */
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		RetriveMSG.setText((String) msg);
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
