package OBLFX;

import Users.Librarian;
import Users.Subscriber;
import java.util.ArrayList;
import java.util.Date;

import Client.LibrarianHandler;
import Users.Book;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddBookController implements IGUIcontroller {
    private boolean Dates = false;
    private boolean OpenFile = false;	
	final static String Datepicker = "Pick a Date";
	final static String addPDF = "Add PDf File ";
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

    @FXML
    void AddBookAction(ActionEvent event) {
    	int counter = 0;
    	if(BookNameTextField.getText().length()==0) {
    		BookNameLabel.setText(fillThisArea);
    	}
    	else {
    		counter++;
    	}
 		if(IGUIcontroller.CheckOnlyLetter(AuthorTextField,AuthorLabel,OnlyThisLetters,OnlyThisLetterError)) {
    		if(AuthorTextField.getText().length()==0) {
    			AuthorLabel.setText(fillThisArea);
    		}
	    	}
    	else {
    		counter++;
    	} 
 		if(IGUIcontroller.CheckOnlyLetter(SubjectTextField,SubjectLabel,OnlyThisLetters,OnlyThisLetterError)) {
    		if(SubjectTextField.getText().length()==0) {
    			SubjectLabel.setText(fillThisArea);
    		}
	    	}
    	else {
    		counter++;
    	}		
		if(PlaceOnShelfTextField.getText().length()==0) {
			PlaceOnShelfLabel.setText(fillThisArea);
		}
    	else {
    		counter++;
    	}
		if(EditionNumberTextField.getText().length()==0) {
			EditionNumberLabel.setText(fillThisArea);
		}
    	else {
    		counter++;
    	}
 		if(IGUIcontroller.CheckOnlyLetter(CatalogTextField,CatalogLabel,OnlyNumbers,UserNameErrorNumebrs)){
    		if(CatalogTextField.getText().length()==0) {
    			CatalogLabel.setText(fillThisArea);
    		}
	    	}
    	else {
    		counter++;
    	}	
    	
		if(	DescriptionTextField.getText().length()==0) {
			DescriptionLabel.setText(fillThisArea);
		}
		else {
			counter++;
		}
		if(Dates==false) {
			PurchaseDateLabel.setText(Datepicker);	
		}
		if(OpenFile==false) {
			ContextTabelLabel.setText(addPDF);	
		}
		if(OpenFile && Dates && counter == 7) {
			String catalog,bookname,author,subject,Shelf,EditionNumber,Description,Context;
			catalog=CatalogTextField.getText();
			bookname=BookNameTextField.getText();
			author=AuthorTextField.getText();
			subject=SubjectTextField.getText();
			Shelf=PlaceOnShelfTextField.getText();
			EditionNumber=EditionNumberTextField.getText();
			Description=DescriptionTextField.getText();
			Context=ContextTabeTextField.getText();
			Book book = new Book(catalog,bookname,author,subject,1,1,0,Shelf,EditionNumber,java.sql.Date.valueOf(PurchaseDatePicker.getValue()),false,Description,Context);
			librarianClient.addBookToCatalog(book,new Librarian());
		}
		
    }

    @FXML
    void CheckBook(KeyEvent event) {
    	IGUIcontroller.CheckIfUserPutInput(BookNameTextField,BookNameLabel);  
    }
    @FXML
    void AuthorCheck(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(AuthorTextField,AuthorLabel,OnlyThisLetters,OnlyThisLetterError);
    	IGUIcontroller.CheckIfUserPutInput(AuthorTextField,AuthorLabel); 
    }

    @FXML
    void CatalogNumberCheck(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(CatalogTextField,CatalogLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CatalogTextField,CatalogLabel); 
    }

    @FXML
    void CheckPlaceOnShelf(KeyEvent event) {
  	IGUIcontroller.CheckIfUserPutInput(PlaceOnShelfTextField,PlaceOnShelfLabel); 
    }
    @FXML
    void NumberOfCopiesCheck(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(NumberOfCopiesTextField,NumberOfCopiesLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(NumberOfCopiesTextField,NumberOfCopiesLabel); 
    }
    

    @FXML
    void OpenFileAction(ActionEvent event) {
    	OpenFile = true;
    }

    @FXML
    void PurchaceDateAction(ActionEvent event) {
    	Dates = true;
    }

    @FXML
    void SubjectCheck(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(SubjectTextField,SubjectLabel,OnlyThisLetters,OnlyThisLetterError);
    	IGUIcontroller.CheckIfUserPutInput(SubjectTextField,SubjectLabel);
    }


	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		RetriveMSG.setText((String) msg);	
	}

	

}