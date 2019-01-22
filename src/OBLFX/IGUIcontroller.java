package OBLFX;

import Client.CommonHandler;
import Users.IGeneralData.operationsReturn;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface IGUIcontroller {
	final static String PhoneNumberErrorDigits = "PhoneNumber contains 10 digits.";
	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	final static String OnlyLetters = "^[a-zA-Z]*$";
	final static String OnlyLetterError = "Only Letters allowed";
	final static String OnlyThisLetterError = "Only Letters and , allowed";
	final static String fillThisArea = " Fill this Area";
	final static String OnlyThisLetters = "^[a-zA-Z,]*$";
	public static CommonHandler commonClient = null;
	
	
	public <T> void receiveMassageFromServer(T msg, operationsReturn op);
	public static boolean CheckOnlyNumbers(TextField ID, Label Alert,int size,String error) {
		if (ID.getText().length() > size || ID.getText().length() == 0) {

			Alert.setText(error);

		} else if (!ID.getText().matches(OnlyNumbers)) {

			Alert.setText(error);

		} else {

			Alert.setText("");
			return true;
		}
		return false;
	}

	public static boolean CheckOnlyLetter(TextField ID, Label Alert,String LettersAllowed,String Error) {
		

		if (!ID.getText().matches(LettersAllowed)){
			Alert.setText(Error);
				return false;
		} else {
			Alert.setText("");
			return true;
		}
	}
	public static boolean CheckIfUserPutInput(TextField ID, Label Alert) {
		if (ID.getText().length() == 0){
			Alert.setText(fillThisArea);
			return false;
		} else {
			Alert.setText("");		
			return true;
		}
	}
	
	public void setConnection();
	public void closeConnection();
}
