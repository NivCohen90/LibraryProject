package OBLFX;

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
	private final static String ChooseDate = "Select Date";
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
		IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel);
	}

	/**
	 * CheckLoan is a method that check if the user filled all the filed
	 */

	@FXML
	void CheckLoan(ActionEvent event) {
		if (IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel, 9, UserNameErrorDigits)
				&& IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,
						UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			if (StartFlag && ReturnFlag) {
				librarianClient.createNewLoan(CatalogNumberTextField.getText(), SubscriberIDTextField.getText(),
						java.sql.Date.valueOf(ReturnDateDatePicker.getValue()),
						java.sql.Date.valueOf(StartDateDatePicker.getValue()));
			} else {
				if (StartFlag) {
					StartDateLabel.setText(ChooseDate);
				}
				if (ReturnFlag) {
					ReturnDateLabel.setText(ChooseDate);
				}
			}
		}

	}

	/**
	 * CheckReturnDate is a method that check if the user choose a date an notice it
	 * by a flag
	 */
	@FXML
	void CheckReturnDate(ActionEvent event) {
		ReturnFlag = true;
	}

	/**
	 * CheckStartDate is a method that check if the user choose a date an notice it
	 * by a flag
	 */
	@FXML
	void CheckStartDate(ActionEvent event) {
		StartFlag = true;
	}

	/**
	 * CheckSubscriberID is a method that check if the user put input and if he put
	 * input that is length is exactly 9.,if he didn't gave input the method will
	 * alert the user. this method also check that all this field only contains this
	 * letters[0-9].
	 */
	@FXML
	void CheckSubscriberID(KeyEvent event) {
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
