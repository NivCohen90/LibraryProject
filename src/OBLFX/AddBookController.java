package OBLFX;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Client.LibrarianHandler;
import Interfaces.IAlert;
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
		if(IGUIcontroller.CheckIfUserPutInput(BookNameTextField,BookNameLabel)) {
			counter++;
		}
		if(IGUIcontroller.CheckIfUserPutInput(AuthorTextField,AuthorLabel)) {
			if (IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError)) {
				counter++;
			}
		}
		if(IGUIcontroller.CheckIfUserPutInput(SubjectTextField,SubjectLabel)) {
			if (IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError)) {
				counter++;
			}
		}
		if(IGUIcontroller.CheckIfUserPutInput(PlaceOnShelfTextField,PlaceOnShelfLabel)) {
			counter++;
		}
		if(IGUIcontroller.CheckIfUserPutInput(EditionNumberTextField,EditionNumberLabel)) {
			counter++;
		}
		if(IGUIcontroller.CheckIfUserPutInput(CatalogTextField,CatalogLabel)) {
			if (IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs)) {
				counter++;
			}
		}
		if(IGUIcontroller.CheckIfUserPutInput(NumberOfCopiesTextField, NumberOfCopiesLabel)) {
			if(IGUIcontroller.CheckOnlyLetter(NumberOfCopiesTextField, NumberOfCopiesLabel, OnlyNumbers, UserNameErrorNumebrs)) {
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
		if (OpenFile && Dates && counter == 8) {
			String catalog, bookname, author, subject, Shelf, EditionNumber, Description, Context;
			catalog = CatalogTextField.getText();
			bookname = BookNameTextField.getText();
			author = AuthorTextField.getText();
			subject = SubjectTextField.getText();
			Shelf = PlaceOnShelfTextField.getText();
			EditionNumber = EditionNumberTextField.getText();
			Description = DescriptionTextField.getText();
			Context = ContextTabeTextField.getText();
			int numberOfCopies = Integer.parseInt(NumberOfCopiesTextField.getText());
			Book book = new Book(catalog, bookname, author, subject, numberOfCopies, numberOfCopies, 0, Shelf, EditionNumber,
					java.sql.Date.valueOf(PurchaseDatePicker.getValue()), false, Description, Context);
			book.setContextTableByteArray(contexTableByteArray);
			librarianClient.addBookToCatalog(book,GeneralData.userLibrarian);
			
		}
	}


	/**
	 * CheckBook is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user.
	 */
	@FXML
	void CheckBook(KeyEvent event) {
		RetriveMSG.setText("");
		IGUIcontroller.CheckIfUserPutInput(BookNameTextField, BookNameLabel);
	}
	/**
	 * CheckEditionNumber is a method that clear the Fill this area label at EditionNumber raw;
	 */
	@FXML
   void CheckEditionNumber(KeyEvent event) {
		RetriveMSG.setText("");
	   EditionNumberLabel.setText("");
   }
	/**
	 * CheckDescripition is a method that clear the Fill this area label at Descripition raw;
	 */
    @FXML
    void CheckDescripition(KeyEvent event) {
    	RetriveMSG.setText("");
    	DescriptionLabel.setText("");
    }
	/**
	 * AuthorCheck is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[a-zA-Z,].
	 */
	@FXML
	void AuthorCheck(KeyEvent event) {
		RetriveMSG.setText("");
		IGUIcontroller.CheckIfUserPutInput(AuthorTextField, AuthorLabel);
		IGUIcontroller.CheckOnlyLetter(AuthorTextField, AuthorLabel, OnlyThisLetters, OnlyThisLetterError);
		
	}

	/**
	 * CatalogNumberCheck is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9].
	 */
	@FXML
	void CatalogNumberCheck(KeyEvent event) {
		RetriveMSG.setText("");
		IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogLabel);
		IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs);
	
	}

	/**
	 * CheckPlaceOnShelf is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user.
	 */
	@FXML
	void CheckPlaceOnShelf(KeyEvent event) {
		RetriveMSG.setText("");
		IGUIcontroller.CheckIfUserPutInput(PlaceOnShelfTextField, PlaceOnShelfLabel);
	}

	/**
	 * NumberOfCopiesCheck is a method that check if the user put input.,if he
	 * didn't gave input the method will alert the user. this method also check that
	 * all this field only contains this letters[0-9].
	 */
	@FXML
	void NumberOfCopiesCheck(KeyEvent event) {
		RetriveMSG.setText("");
		IGUIcontroller.CheckIfUserPutInput(NumberOfCopiesTextField, NumberOfCopiesLabel);
		IGUIcontroller.CheckOnlyLetter(NumberOfCopiesTextField, NumberOfCopiesLabel, OnlyNumbers, UserNameErrorNumebrs);
		
	}

	/**
	 * this method assign a flag to notice that this button was pushed.
	 */

	@FXML
	void OpenFileAction(ActionEvent event) {
		RetriveMSG.setText("");
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
		ContextTabelLabel.setText("");
	}

	/**
	 * this method assign a flag to notice that this button was pushed.
	 */
	@FXML
	void PurchaceDateAction(ActionEvent event) {
		
		RetriveMSG.setText("");
		Dates = true;
		PurchaseDateLabel.setText("");
	}

	/**
	 * SubjectCheck is a method that check if the user put input.,if he didn't gave
	 * input the method will alert the user. this method also check that all this
	 * field only contains this letters[a-zA-Z,].
	 */
	@FXML
	void SubjectCheck(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(SubjectTextField, SubjectLabel);
		IGUIcontroller.CheckOnlyLetter(SubjectTextField, SubjectLabel, OnlyThisLetters, OnlyThisLetterError);
		
	}

	/**
	 * Update User with the result
	 */

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		BookNameTextField.setText("");
		AuthorTextField.setText("");
		SubjectTextField.setText("");
		PlaceOnShelfTextField.setText("");
		EditionNumberTextField.setText("");
		CatalogTextField.setText("");
		NumberOfCopiesTextField.setText("");
		DescriptionTextField.setText("");
		PurchaseDatePicker.setValue(null);
		ContextTabeTextField.setText("");
		Dates = false;
		OpenFile = false;
		switch(op)
		{
			case returnSuccessMsg:
				RetriveMSG.setText((String) msg);
				RetriveMSG.setStyle("-fx-text-fill: green;");
				break;
			case returnError:	
				RetriveMSG.setText((String) msg);
				RetriveMSG.setStyle("-fx-text-fill: red;");
				break;
			case returnException:
				//RetriveMSG.setText(((Exception) msg).getMessage());
				IAlert.ExceptionAlert((Exception)msg);
				break;
		}
			
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