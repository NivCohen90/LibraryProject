package OBLFX;

import Client.LibrarianHandler;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author Matan
 * ReturnBookController controls ReturnBookFXML
 */
public class ReturnBookController implements IGUIcontroller {

	private LibrarianHandler librarianClient;

	@FXML
	private TextField CatalogNumberTextField;

	@FXML
	private TextField SubscriberIDTextField;

	@FXML
	private Button ReturnBookBTN;

	@FXML
	private Label RetriveMSG;

	@FXML
	private Label CatalogNumberLabel;

	@FXML
	private Label SubscriberIDLabel;

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
	 * CheckReturnBook is a method that check if all off the fields are filled
	 */
	@FXML
	void CheckReturnBook(ActionEvent event) {
		int counter=0;
		if (IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel, 9, UserNameErrorDigits)) {
			counter++;	
		}
		if(IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,UserNameErrorNumebrs)){
			counter++;	
		}
				
		if(IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			counter++;
		}	
		if(counter==3) {
			librarianClient.returnBook(CatalogNumberTextField.getText(), SubscriberIDTextField.getText(),GeneralData.userLibrarian);	
		}
	}	
	

	/**
	 * CheckSubscriberID is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9].
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
