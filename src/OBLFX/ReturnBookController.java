package OBLFX;

import Client.LibrarianHandler;
import Users.Librarian;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ReturnBookController implements IGUIcontroller{
	
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

    @FXML
    void CheckCatalog(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel); 
    }

    @FXML
    void CheckReturnBook(ActionEvent event) {
    if(IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel,9,UserNameErrorDigits)&& IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs)
 &&IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel)) {
    	librarianClient.returnBook(CatalogNumberTextField.getText(),SubscriberIDTextField.getText(),new Librarian());
    }
    }
  
    @FXML
    void CheckSubscriberID(KeyEvent event) {
    	IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel,9,UserNameErrorDigits);
    }

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
	     RetriveMSG.setText((String) msg);
	}

}
