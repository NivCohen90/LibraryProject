package OBLFX;
import Users.Librarian;
import Users.Subscriber;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddNewSubscriberController  implements IGUIcontroller {

	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String PhoneNumberErrorDigits = "PhoneNumber contains 10 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	final static String fillThisArea = " Fill this Area";
	private  String PhoneNum;
    boolean AreaCodeFlag = false;
    

    @FXML
    private TextField EmailTextFiled;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField FirstNameTextFiled;

    @FXML
    private TextField LastNameTextFiled;

    @FXML
    private TextField PhoneNumberTextFiled;

    @FXML
    private TextField PasswordTextFiled;

    @FXML
    private Button CreateNewSubscriberBTN;

    @FXML
    private ComboBox<?> AreaCodeTextFiled;

    @FXML
    private Label IDAlertLabel;

    @FXML
    private Label FirstNameLabel;

    @FXML
    private Label LastNameLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label PhoneNumberLabel;

    @FXML
    private Label PasswordLabel;
    
    @FXML
    private Label ErrorAtCreatSubscriberLabel;

    @FXML
    void CheckChoose(ActionEvent event) {
    	AreaCodeFlag=true;
    }



    @FXML
    void CheckIDInput(ActionEvent event) {
     CheckID();
    }




    @FXML
    void CheckPhoneNumber(KeyEvent event) {
     CheckPhone();
    }

    @FXML
    void CreateNewSubscriberBtn(ActionEvent event) {
    	 int counter=0;
     	if (CheckID()==1) {
     	     counter++;
    }
if(FirstNameTextFiled.getText().length() > 0) {
	counter++;
	FirstNameLabel.setText("");
}
else FirstNameLabel.setText(fillThisArea);
if(LastNameTextFiled.getText().length() > 0) {
	counter++;
	LastNameLabel.setText("");
}
else LastNameLabel.setText(fillThisArea);
if(EmailTextFiled.getText().length() > 0) {
if(EmailTextFiled.getText().contains("@")) {
	counter++;
	EmailLabel.setText("");	
}
else EmailLabel.setText("Email should contains a @");
}
else EmailLabel.setText(fillThisArea);
if (CheckPhone()==1) {
	counter++;
}
if(PasswordTextFiled.getText().length() > 0) {
	counter++;
	PasswordLabel.setText("");
}
else PasswordLabel.setText(fillThisArea);
if (AreaCodeFlag==true) {
	if(counter==6) {
		PhoneNum=""+AreaCodeTextFiled.getPromptText()+PhoneNumberTextFiled.getText();
Subscriber sub = new Subscriber(FirstNameTextFiled.getText(),LastNameTextFiled.getText(),EmailTextFiled.getText(),IDTextField.getText(),PasswordTextFiled.getText(),"Active",PhoneNum,"0");
//createNewSubscriber(Subscriber sub,Librarian Me);
		
	}	
}
else PhoneNumberLabel.setText(PhoneNumberErrorDigits);

    }
    
    int CheckID() {
    	if (IDTextField.getText().length() > 9) {
    		
    		IDAlertLabel.setText(UserNameErrorDigits);
    		
    	} else if (!IDTextField.getText().matches(OnlyNumbers)) {
    		
    		IDAlertLabel.setText(UserNameErrorNumebrs);

    	} else 
    	{
    		
    		IDAlertLabel.setText("");
    		return 1;
    	}	
    	return 0;
    	
    	
    }
 int CheckPhone(){
    	if (PhoneNumberTextFiled.getText().length() > 7) {
    		
    		PhoneNumberLabel.setText(PhoneNumberErrorDigits);
    		
    	} else if (!PhoneNumberTextFiled.getText().matches(OnlyNumbers)) {
    		
    		PhoneNumberLabel.setText(UserNameErrorNumebrs);

    	} else 
    	{
    		PhoneNumberLabel.setText("");
    		return 1;
    	}
    	return 0;
    }



@Override
public void receiveMassageFromServer(Object msg, operationsReturn op) {
	switch (op) {
	case returnError:
		ErrorAtCreatSubscriberLabel.setText((String) msg);
		break;
	default:		
	
	}
	
}


}

