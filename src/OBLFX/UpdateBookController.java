package OBLFX;

import java.util.ArrayList;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Interfaces.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * @author Matan
 * UpdateBookController controls UpdateBookFXML
 * 
 * @param all the booleans parameters are flags to notice if the user trying to
 *            change some data in his side.
 */
public class UpdateBookController implements IGUIcontroller {
	boolean catalog = false, bookName = false, author = false, subject = false, placeOnShelf = false,
			editionNumber = false, allFlagStatus = false;
	private LibrarianHandler librarianClient;
	private CommonHandler commonClient;
	private final static String Search = "Search for a book first";
	private static String catalogNumberSearch;
	private static Book book = new Book();

	@FXML
	private TextField BookNameTextField;

	@FXML
	private TextField AuthorTextField;

	@FXML
	private TextField SubjectTextField;

	@FXML
	private TextField PlaceOnShelfTextField;

	@FXML
	private TextField EditionNumberTextField;

	@FXML
	private TextArea DescriptionTextField;

	@FXML
	private TextField CatalogTextField;

	@FXML
	private Button GetBookDetailsBTN;

	@FXML
	private Label CatalogNumberLabel;

	@FXML
	private Label PlaceOnShelfLabel;

	@FXML
	private Button UpdateBookBTN;

	@FXML
	private Label BookNameLabel;

	@FXML
	private Label AuthorLabel;

	@FXML
	private Label EditionNumberLabel;

	@FXML
	private Label SubjectLabel;

	@FXML
	private Label RetriveMSGLabel;

	/**
	 * CheckAuthorName is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[a-zA-Z,]. and notice his flag
	 */
	@FXML
	void CheckAuthorName(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel);
		IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError);
		author = true;
	}

	/**
	 * CheckCatalogNumber is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9]. and notice his flag
	 */
	@FXML
	void CheckCatalogNumber(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogNumberLabel);
		IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		catalog = true;
	}

	/**
	 * CheckEditionNumber method notice his flag
	 */
	@FXML
	void CheckEditionNumber(KeyEvent event) {
		editionNumber = true;
	}

	/**
	 * CheckSubject is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[a-zA-Z,].
	 */
	@FXML
	void CheckSubject(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel);
		IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError);
		subject = true;
	}

	/**
	 * FlagBookName method notice his flag
	 */
	@FXML
	void FlagBookName(KeyEvent event) {
		bookName = true;
	}

	/**
	 * FlagShelf method notice his flag
	 */
	@FXML
	void FlagShelf(KeyEvent event) {
		placeOnShelf = true;
	}

	/**
	 * GetBookDetailsAction is a method that check if all off the fields are filled
	 * and if it filled right its send the server a book details request
	 */
	@FXML
	void GetBookDetailsAction(ActionEvent event) {
		if ( IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogNumberLabel) && IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs))
				 {
			catalogNumberSearch = CatalogTextField.getText();

			commonClient.searchInServer(catalogNumberSearch, GeneralData.operations.searchByCatalogNumber);
		}
	}

	/**
	 * UpdateBookAction is a method that check if all off the fields are filled and
	 * if it filled right its send the server a update request
	 */
	@FXML
	void UpdateBookAction(ActionEvent event) {
		if (catalog && !allFlagStatus) {
			RetriveMSGLabel.setText(Search);
		} else {
			int counter = 0;
			if (IGUIcontroller.CheckIfUserPutInput(BookNameTextField, BookNameLabel)) {
				counter++;
				if (bookName) {
					book.setBookName(BookNameTextField.getText());

				}
			}

			if (IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel) && IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError))
					 {
				counter++;
				if (author) {
					book.setAuthorName(AuthorTextField.getText());
				}
			}

			if (IGUIcontroller.CheckIfUserPutInput(PlaceOnShelfTextField, PlaceOnShelfLabel)) {
				counter++;
				if (placeOnShelf) {
					book.setShelfLoaction(PlaceOnShelfTextField.getText());

				}
			}
			if (IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel) &&  IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError))
					 {
				counter++;
				if (subject) {
					book.setSubject(SubjectTextField.getText());

				}
			}
			if (IGUIcontroller.CheckIfUserPutInput(EditionNumberTextField, EditionNumberLabel)) {
				counter++;
				if (editionNumber) {
					book.setEditionNumber(EditionNumberTextField.getText());
				}

			}
			if (!DescriptionTextField.getText().trim().isEmpty()){
				counter++;
				book.setDescription(DescriptionTextField.getText());

			}
			if (counter == 6) {
				librarianClient.updateBookinCatalog(book,GeneralData.userLibrarian);
			}

		}
	}

	/**
	 * Update User with the result
	 */
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch (op) {
		case returnBookArray:
			
			book = ((ArrayList<Book>)msg).get(0);
			SetAllEditable();
			SetAllFlagFalse();

			 BookNameTextField.setText(book.getBookName());
			 AuthorTextField.setText(book.getAuthorName());
			 SubjectTextField.setText(book.getSubject());
			 PlaceOnShelfTextField.setText(book.getShelfLoaction());
			 EditionNumberTextField.setText(book.getEditionNumber());
			 DescriptionTextField.setText(book.getDescription());
			 allFlagStatus=true;

			break;
		default:
			RetriveMSGLabel.setText((String) msg);
			SetAllUnEditable();
		}

	}

	/**
	 * SetAllEditable is method that unlock all the text fields
	 */
	private void SetAllEditable() {
		SetAllFlagFalse();
		CatalogTextField.setEditable(false);
		BookNameTextField.setEditable(true);
		AuthorTextField.setEditable(true);
		SubjectTextField.setEditable(true);
		PlaceOnShelfTextField.setEditable(true);
		EditionNumberTextField.setEditable(true);
		DescriptionTextField.setEditable(true);
		allFlagStatus = true;

	}

	/**
	 * SetAllUnEditable is method that lock all the text fields
	 */
	private void SetAllUnEditable() {
		SetAllFlagFalse();
		BookNameTextField.setEditable(false);
		AuthorTextField.setEditable(false);
		SubjectTextField.setEditable(false);
		PlaceOnShelfTextField.setEditable(false);
		EditionNumberTextField.setEditable(false);
		allFlagStatus = false;

	}

	/**
	 * SetAllFlagFalse is method that notice all the flags.
	 */
	private void SetAllFlagFalse() {
		catalog = false;
		bookName = false;
		author = false;
		subject = false;
		placeOnShelf = false;
		editionNumber = false;
	}

	@Override
	public void setConnection() {
		librarianClient = new LibrarianHandler(this);
		commonClient = new CommonHandler(this);
	}

	@Override
	public void closeConnection() {
		if(librarianClient!=null)
			librarianClient.quit();
	}

}
