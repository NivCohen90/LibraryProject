package OBLFX;

import Client.LibrarianHandler;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
////
public class NewLoanController implements IGUIcontroller {
boolean StartFlag=false;
boolean ReturnFlag=false;
private final static String ChooseDate= "Select Date";
private LibrarianHandler librarianClient;
    @FXML
    private TextField CatalogNumberTextField;

    @FXML
    private DatePicker StartDateDatePicker;

    @FXML
    private DatePicker ReturnDateDatePicker;

    @FXML
    private TextField SubscriberIDTextField;

    @FXML
    private Button CreateLoanBTN;

    @FXML
    private Label CatalogNumberLabel;

    @FXML
    private Label StartDateLabel;

    @FXML
    private Label SubscriberIDLabel;

    @FXML
    private Label ReturnDateLabel;

    @FXML
    private Label RetriveMSG;

    @FXML
    void CheckCatalog(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel); 
    }

    @FXML
    void CheckLoan(ActionEvent event) {
    if(IGUIcontroller.CheckOnlyNumbers(SubscriberIDTextField, SubscriberIDLabel,9,UserNameErrorDigits)&&IGUIcontroller.CheckOnlyLetter(CatalogNumberTextField,CatalogNumberLabel,OnlyNumbers,UserNameErrorNumebrs) &&IGUIcontroller.CheckIfUserPutInput(CatalogNumberTextField,CatalogNumberLabel)) {
    	if(StartFlag&&ReturnFlag) {
    		librarianClient.createNewLoan(CatalogNumberTextField.getText(), SubscriberIDTextField.getText(), java.sql.Date.valueOf(ReturnDateDatePicker.getValue()), java.sql.Date.valueOf(StartDateDatePicker.getValue()));
    	}
    	else {
    		if(StartFlag) {
    			StartDateLabel.setText(ChooseDate);
    		}
if(ReturnFlag) {
	ReturnDateLabel.setText(ChooseDate);
    		}
    	}
    }

    }

    @FXML
    void CheckReturnDate(ActionEvent event) {
    	ReturnFlag=true;
    }

    @FXML
    void CheckStartDate(ActionEvent event) {
    	StartFlag=true;
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
