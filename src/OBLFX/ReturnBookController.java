package OBLFX;

import Client.LibrarianHandler;
import SystemObjects.IGeneralData.operationsReturn;
import Users.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
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
		IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel);
	}

	/**
	 * CheckReturnBook is a method that check if all off the fields are filled
	 */
	@FXML
	void CheckReturnBook(ActionEvent event) {
		if (IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel, 9, UserNameErrorDigits)
				&& IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,
						UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			librarianClient.returnBook(CatalogNumberTextField.getText(), SubscriberIDTextField.getText(),
					new Librarian());
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
