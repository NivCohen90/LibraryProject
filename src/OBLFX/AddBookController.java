package OBLFX;

/**
 * 
 */
import Users.Librarian;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Client.LibrarianHandler;
import Interfaces.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Matan
 * AddBookController controls AddBookFXML
 * 
 * @param Dates and OpenFile is a flag that show if the user pushed their
 *              Buttons
 */
public class AddBookController implements IGUIcontroller {
	private boolean Dates = false;
	private boolean OpenFile = false;
	final static String Datepicker = "Pick a Date";
	final static String addPDF = "Add PDf File ";
	private byte [] contexTableByteArray;
	
	private LibrarianHandler librarianClient;
	@FXML
	private TextField BookNameTextField;

	@FXML
	private TextField AuthorTextField;

	@FXML
	private TextField SubjectTextField;

	@FXML
	private TextField PlaceOnShelfTextField;

	@FXML
	private TextField CatalogTextField;

	@FXML
	private TextField EditionNumberTextField;

	@FXML
	private TextArea DescriptionTextField;

	@FXML
	private TextField ContextTabeTextField;

	@FXML
	private Button OpenFileBTN;

	@FXML
	private Label BookNameLabel;

	@FXML
	private Label AuthorLabel;

	@FXML
	private Label SubjectLabel;

	@FXML
	private Label EditionNumberLabel;

	@FXML
	private Label ContextTabelLabel;

	@FXML
	private Label PlaceOnShelfLabel;

	@FXML
	private Label CatalogLabel;

	@FXML
	private Label PurchaseDateLabel;

	@FXML
	private Label DescriptionLabel;

	@FXML
	private DatePicker PurchaseDatePicker;

	@FXML
	private Label RetriveMSG;
	@FXML
	private Label NumberOfCopiesTitle;

	@FXML
	private TextField NumberOfCopiesTextField;

	@FXML
	private Label AvailibaleCopiesLabel;

	@FXML
	private Label NumberOfCopiesLabel;

	/**
	 * AddBookAction is a method that check if all off the fields are filled
	 */
	@FXML
	void AddBookAction(ActionEvent event) {
		int counter = 0;
		if (BookNameTextField.getText().length() == 0) {
			BookNameLabel.setText(fillThisArea);
		} else {
			counter++;
		}
		if (IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError)) {
			if (AuthorTextField.getText().length() == 0) {
				AuthorLabel.setText(fillThisArea);
			} else {
				counter++;
			}
		}
		if (IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError)) {
			if (SubjectTextField.getText().length() == 0) {
				SubjectLabel.setText(fillThisArea);
			} else {
				counter++;
			}
		}
		if (PlaceOnShelfTextField.getText().length() == 0) {
			PlaceOnShelfLabel.setText(fillThisArea);
		} else {
			counter++;
		}
		if (EditionNumberTextField.getText().length() == 0) {
			EditionNumberLabel.setText(fillThisArea);
		} else {
			counter++;
		}
		if (IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs)) {
			if (CatalogTextField.getText().length() == 0) {
				CatalogLabel.setText(fillThisArea);
			} else {
				counter++;
			}
		}

		if (DescriptionTextField.getText().length() == 0) {
			DescriptionLabel.setText(fillThisArea);
		} else {
			counter++;
		}
		if (Dates == false) {
			PurchaseDateLabel.setText(Datepicker);
		}
		if (OpenFile == false) {
			ContextTabelLabel.setText(addPDF);
		}
		if (OpenFile && Dates && counter == 7) {
			String catalog, bookname, author, subject, Shelf, EditionNumber, Description, Context;
			catalog = CatalogTextField.getText();
			bookname = BookNameTextField.getText();
			author = AuthorTextField.getText();
			subject = SubjectTextField.getText();
			Shelf = PlaceOnShelfTextField.getText();
			EditionNumber = EditionNumberTextField.getText();
			Description = DescriptionTextField.getText();
			Context = ContextTabeTextField.getText();
			Book book = new Book(catalog, bookname, author, subject, 1, 1, 0, Shelf, EditionNumber,
					java.sql.Date.valueOf(PurchaseDatePicker.getValue()), false, Description, Context);
			book.setContextTableByteArray(contexTableByteArray);
			librarianClient.addBookToCatalog(book,GeneralData.userLibrarian);
		}

	}

	@FXML
	/**
	 * CheckBook is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user.
	 */
	void CheckBook(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(BookNameTextField, BookNameLabel);
	}

	/**
	 * AuthorCheck is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[a-zA-Z,].
	 */
	@FXML
	void AuthorCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError);
		IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel);
	}

	/**
	 * CatalogNumberCheck is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9].
	 */
	@FXML
	void CatalogNumberCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogLabel);
	}

	/**
	 * CheckPlaceOnShelf is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user.
	 */
	@FXML
	void CheckPlaceOnShelf(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(PlaceOnShelfTextField, PlaceOnShelfLabel);
	}

	/**
	 * NumberOfCopiesCheck is a method that check if the user put input.,if he
	 * didn't gave input the method will alert the user. this method also check that
	 * all this field only contains this letters[0-9].
	 */
	@FXML
	void NumberOfCopiesCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(NumberOfCopiesTextField, NumberOfCopiesLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(NumberOfCopiesTextField, NumberOfCopiesLabel);
	}

	/**
	 * this method assign a flag to notice that this button was pushed.
	 */

	@FXML
	void OpenFileAction(ActionEvent event) {
    	Stage stage = new Stage();
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose Book Context Table");
    	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.pdf pdf file", "*.pdf"));
    	File pdfFile = fileChooser.showOpenDialog(stage);
		byte [] mybytearray  = new byte [(int)pdfFile.length()];	//byte array of file
		FileInputStream fis;			//input from file
		BufferedInputStream bis;		//buffer input		  
		  
		try {
			fis = new FileInputStream(pdfFile);
			bis = new BufferedInputStream(fis);
			bis.read(mybytearray,0,mybytearray.length);				//read from file to byte array
			fis.close();
			bis.close();
			contexTableByteArray = mybytearray;
		} catch (IOException e) {
			e.printStackTrace();
		}					
		  
        if (pdfFile != null) {
        	ContextTabeTextField.setText(pdfFile.getName());

        }
		OpenFile = true;
	}

	/**
	 * this method assign a flag to notice that this button was pushed.
	 */
	@FXML
	void PurchaceDateAction(ActionEvent event) {
		Dates = true;
	}

	/**
	 * SubjectCheck is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[a-zA-Z,].
	 */
	@FXML
	void SubjectCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError);
		IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel);
	}

	/**
	 * Update User with the result
	 */

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		if(op!=operationsReturn.returnError)
			RetriveMSG.setText((String) msg);
		else
			RetriveMSG.setText(((Exception) msg).getMessage());
	}

	@Override
	public void setConnection() {
		librarianClient = new LibrarianHandler(this);
	}

	@Override
	public void closeConnection() {
		if (librarianClient != null)
			librarianClient.quit();
	}

}