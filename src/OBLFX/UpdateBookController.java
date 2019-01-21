package OBLFX;

import java.awt.TextArea;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Users.Librarian;
import Users.Book;
import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class UpdateBookController implements IGUIcontroller {
	boolean catalog = false, bookName = false, author = false, subject = false, placeOnShelf = false,
			editionNumber = false, allFlagStatus = false;
	private LibrarianHandler librarianClient;
	private CommonHandler commonClient;
	private final static String Search = "Search for a book first";
	private static String catalogNumberSearch;
	private static Book book;

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

	@FXML
	void CheckAuthorName(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError);
		IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel);
		author = true;
	}

	@FXML
	void CheckCatalogNumber(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogNumberLabel);
		catalog = true;
	}

	@FXML
	void CheckEditionNumber(KeyEvent event) {
		editionNumber = true;
	}

	@FXML
	void CheckSubject(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError);
		IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel);
		subject = true;
	}

	@FXML
	void FlagBookName(KeyEvent event) {
		bookName = true;
	}

	@FXML
	void FlagShelf(KeyEvent event) {
		placeOnShelf = true;
	}

	@FXML
	void GetBookDetailsAction(ActionEvent event) {
		if (IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogNumberLabel)) {
			catalogNumberSearch = CatalogTextField.getText();

			//commonClient.searchBookInServer(catalogNumberSearch, IGeneralData.operations.searchByCatalogNumber);
		}
	}

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

			if (IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError)
					&& IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel)) {
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
			if (IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError)
					&& IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel)) {
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
			if (counter == 5) {
				librarianClient.updateBookinCatalog(book, new Librarian());
			}

		}
	}

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch (op) {
		case returnBook:
			SetAllEditable();
			SetAllFlagFalse();

			// BookNameTextField.setText(book.getBookName());
			// AuthorTextField.setText(book.getAuthorName());
			// SubjectTextField.setText(book.getSubject());
			// PlaceOnShelfTextField.setText(book.getShelfLoaction());
			// EditionNumberTextField.setText(book.getEditionNumber());
			// DescriptionTextField.setText(book.getDescription());
			// allFlagStatus=true;

			break;
		default:
			RetriveMSGLabel.setText((String) msg);
			SetAllUnEditable();
		}

	}

	private void SetAllEditable() {
		SetAllFlagFalse();
		BookNameTextField.setEditable(true);
		AuthorTextField.setEditable(true);
		SubjectTextField.setEditable(true);
		PlaceOnShelfTextField.setEditable(true);
		EditionNumberTextField.setEditable(true);
		allFlagStatus = true;

	}

	private void SetAllUnEditable() {
		SetAllFlagFalse();
		BookNameTextField.setEditable(false);
		AuthorTextField.setEditable(false);
		SubjectTextField.setEditable(false);
		PlaceOnShelfTextField.setEditable(false);
		EditionNumberTextField.setEditable(false);
		allFlagStatus = false;

	}

	private void SetAllFlagFalse() {
		catalog = false;
		bookName = false;
		author = false;
		subject = false;
		placeOnShelf = false;
		editionNumber = false;
	}

}
