package OBLFX;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Users.Book;
import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;
import Users.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * DeleteController controls DeleteBookFXML
*/
public class DeleteController implements IGUIcontroller {
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
	private TextField CatalogNumberTextField;

	@FXML
	private Button GetBookDetailsBTN;

	@FXML
	private TextArea DescripitionAreaField;

	@FXML
	private Button DeleteBTN;

	@FXML
	private TextField CopiesTextField;

	@FXML
	private Label AvailiableCopiesLabel;

	@FXML
	private TextField AvailiableCopiesTextField;

	@FXML
	private TextField CopyNumberTextField;

	@FXML
	private Label CatalogNumberLabel;

	@FXML
	private Label CopyNumberLabel;
    /**
     * CheckCatalogNumber is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
	@FXML
	void CheckCatalogNumber(KeyEvent event) {
		CopyNumberLabel.setText("");
		IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel);
	}
    /**
     * CheckCopy is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z,].
    */
	@FXML
	void CheckCopy(KeyEvent event) {
		CopyNumberLabel.setText("");
		IGUIcontroller.CheckOnlyLetter(CopyNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField, CatalogNumberLabel);
		if (book.getNumberOfLibraryCopies() < Integer.parseInt(CopyNumberTextField.getText())) {
			CopyNumberLabel.setText("No such copy");
		}

	}
    /**
     * DeleteAction is a method that check if all of the field is filled. if all fields filled its send to  the server a delete book request
    */
	@FXML
	void DeleteAction(ActionEvent event) {
		if (IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,
				UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			if (IGUIcontroller.CheckOnlyLetter(CopyNumberTextField, CatalogNumberLabel, OnlyNumbers,
					UserNameErrorNumebrs)
					&& IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField, CatalogNumberLabel)) {
				if (book.getNumberOfLibraryCopies() <= Integer.parseInt(CopyNumberTextField.getText())) {

				} else {
					CopyNumberLabel.setText("No such copy");
				}
			}

			librarianClient.removeBookFromCatalog(catalogNumberSearch, CopyNumberTextField.getText(), new Librarian());
		}
	}
    /**
     * GetBookDetailsAction is a method that check if all of the field is filled. if all fields filled its send to  the server a request 
    */
	@FXML
	void GetBookDetailsAction(ActionEvent event) {
		CopyNumberLabel.setText("");
		if (IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,
				UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			catalogNumberSearch = CatalogNumberTextField.getText();
			commonClient.searchInServer(catalogNumberSearch, IGeneralData.operations.searchByCatalogNumber);
		}
	}
    /**
     * Update User with the result
    */ 
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		CopyNumberLabel.setText((String) msg);
		// BookNameTextField.setText(book.getBookName());
		// AuthorTextField.setText(book.getAuthorName());
		// SubjectTextField.setText(book.getSubject());
		// CopiesTextField.setText(Integer.toString(book.getNumberOfLibraryCopies()));
		// AvailiableCopiesTextField.setText(Integer.toString(book.getAvailableCopies()));
		// DescripitionAreaField.setText(book.getDescription());

	}

}
