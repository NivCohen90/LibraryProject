package OBLFX;

import java.util.ArrayList;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Interfaces.IAlert;
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
 * DeleteController controls DeleteBookFXML
*/
public class DeleteController implements IGUIcontroller {
	private LibrarianHandler librarianClient;
	private CommonHandler commonClient;
	@SuppressWarnings("unused")
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
	
    @FXML
    private Label RetriveMSGLabel;
    /**
     * CheckCatalogNumber is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
	@FXML
	void CheckCatalogNumber(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel);
		IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		
	}
    /**
     * CheckCopy is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[a-zA-Z,].
    */
	@FXML
	void CheckCopy(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField, CopyNumberLabel);
		IGUIcontroller.CheckOnlyLetter(CopyNumberTextField, CopyNumberLabel, OnlyNumbers, UserNameErrorNumebrs);
		
		if (book.getNumberOfLibraryCopies() < Integer.parseInt(CopyNumberTextField.getText())) {
			CopyNumberLabel.setText("No such copy");
		}

	}
    /**
     * DeleteAction is a method that check if all of the field is filled. if all fields filled its send to  the server a delete book request
    */
	@FXML
	void DeleteAction(ActionEvent event) {
		CopyNumberLabel.setText("");
		if ( IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)
				&& IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,UserNameErrorNumebrs))
				  {
			if (IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField, CatalogNumberLabel) && IGUIcontroller.CheckOnlyLetter(CopyNumberTextField, CatalogNumberLabel, OnlyNumbers,
					UserNameErrorNumebrs)
					 ) {
				if (book.getNumberOfLibraryCopies() < Integer.parseInt(CopyNumberTextField.getText())) {
					CopyNumberLabel.setText("No such copy");
				}
				else {
					librarianClient.removeBookFromCatalog(catalogNumberSearch, CopyNumberTextField.getText(),GeneralData.userLibrarian);
				}
			}

			
		}
	}
    /**
     * GetBookDetailsAction is a method that check if all of the field is filled. if all fields filled its send to  the server a request 
    */
	@FXML
	void GetBookDetailsAction(ActionEvent event) {
		RetriveMSGLabel.setText("");
		CopyNumberLabel.setText("");
		if (IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField, CatalogNumberLabel, OnlyNumbers,
				UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField, CatalogNumberLabel)) {
			catalogNumberSearch = CatalogNumberTextField.getText();
			commonClient.searchInServer(catalogNumberSearch, GeneralData.operations.searchByCatalogNumber);
		}
	}
    /**
     * ResetAction is a method that clear page
    */
    @FXML
    void ResetAction(ActionEvent event) {
    	Reset();
    	
    }
    private void Reset() {
	   	CatalogNumberTextField.setDisable(false);
    	CatalogNumberTextField.setText("");
    	BookNameTextField.setText("");
    	AuthorTextField.setText("");
    	SubjectTextField.setText("");
    	DescripitionAreaField.setText("");
    	CopiesTextField.setText("");
    	AvailiableCopiesTextField.setText("");
    	CopyNumberTextField.setText(""); 
   }
    /**
     * Update User with the result
    */ 
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch(op) {
		case returnBookArray:
			CatalogNumberTextField.setDisable(true);
			book = ((ArrayList<Book>) msg).get(0);
			CopiesTextField.setText(Integer.toString(book.getNumberOfLibraryCopies()));
		    BookNameTextField.setText(book.getBookName());
			AuthorTextField.setText(book.getAuthorName());
			SubjectTextField.setText(book.getSubject());
			AvailiableCopiesTextField.setText(Integer.toString(book.getAvailableCopies()));
			DescripitionAreaField.setText(book.getDescription());
			break;
		case returnError:
			Reset();
			RetriveMSGLabel.setStyle("-fx-text-fill: red;");
			RetriveMSGLabel.setText((String) msg);
			break;
		case returnException:
			Reset();
			RetriveMSGLabel.setText(((Exception) msg).getMessage());
			IAlert.ExceptionAlert((Exception) msg);
			break;

		case returnSuccessMsg:
			RetriveMSGLabel.setStyle("-fx-text-fill: green;");
			RetriveMSGLabel.setText((String) msg);
			break;
		default:
			break;
		}
		


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
