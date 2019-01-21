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

public class DeleteController implements IGUIcontroller {
private LibrarianHandler librarianClient;
private CommonHandler commonClient;
private final static String Search ="Search for a book first";
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
    void CheckCatalogNumber(KeyEvent event) {
    	CopyNumberLabel.setText("");
    	IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel); 
    }

    @FXML
    void CheckCopy(KeyEvent event) {
    	CopyNumberLabel.setText("");
    	IGUIcontroller.CheckOnlyLetter(CopyNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField,CatalogNumberLabel); 
		if(book.getNumberOfLibraryCopies()<Integer.parseInt(CopyNumberTextField.getText())){
			CopyNumberLabel.setText("No such copy");
		}

    }

    @FXML
    void DeleteAction(ActionEvent event) {
    	if(IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs) && IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel)) {
    		if(IGUIcontroller.CheckOnlyLetter(CopyNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs)&&IGUIcontroller.CheckIfUserPutInput(CopyNumberTextField,CatalogNumberLabel)) {
    		if(book.getNumberOfLibraryCopies()<=Integer.parseInt(CopyNumberTextField.getText())){
    			
    		}
    		else {
    			CopyNumberLabel.setText("No such copy");
    		}
    		}
    		
      		librarianClient.removeBookFromCatalog(catalogNumberSearch,CopyNumberTextField.getText(),new Librarian());
      	}
    }

    @FXML
    void GetBookDetailsAction(ActionEvent event) {
    	CopyNumberLabel.setText("");
    	if(IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs) && IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel)) {
   		 catalogNumberSearch=CatalogNumberTextField.getText();
   		commonClient.searchBookInServer(catalogNumberSearch,IGeneralData.operations.searchByCatalogNumber);   
   		}
    }

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
			CopyNumberLabel.setText((String) msg);
		//	BookNameTextField.setText(book.getBookName());
		  //  AuthorTextField.setText(book.getAuthorName());
		  //  SubjectTextField.setText(book.getSubject());
		 //   CopiesTextField.setText(Integer.toString(book.getNumberOfLibraryCopies()));
		//    AvailiableCopiesTextField.setText(Integer.toString(book.getAvailableCopies()));
		//	DescripitionAreaField.setText(book.getDescription());
			
			

	}

}
