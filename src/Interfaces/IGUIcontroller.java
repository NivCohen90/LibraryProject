package Interfaces;

import java.time.LocalDate;

import Client.CommonHandler;
import SystemObjects.GeneralData.operationsReturn;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * interface for all FXLM controllers, instances will be set to client and it will call to controller to display result from sever
 * @author Matan
 *
 */
public interface IGUIcontroller {

	final static String PhoneNumberErrorDigits = "PhoneNumber contains 10 digits.";
	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	final static String OnlyLetters = "^[a-zA-Z' ']*$";
	final static String OnlyLetterError = "Only Letters allowed";
	final static String OnlyThisLetterError = "Only Letters(a-Z) and (,.) allowed";
	final static String fillThisArea = " Fill this Area";
	final static String OnlyThisLetters = "^[a-zA-Z,.' ']*$";
	final static String ChooseDate = "Select a future Date";
	public static CommonHandler commonClient = null;
	
	/**
	 * how client send message that he got from server to GUI, each GUI implementing this method will display message according to GUI structure
	 * @param msg message received from server in client
	 * @param op what kind of object returned
	 */
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
	
	public static boolean CheckIfUserPutInput(TextArea ID, Label Alert) {
		if (ID.getText().length() == 0){
			Alert.setText(fillThisArea);
			return false;
		} else {
			Alert.setText("");		
			return true;
		}
	}
	
	public static boolean CheckIfUserPutInput(String ID, Label Alert) {
		if (ID.length() == 0){
			Alert.setText(fillThisArea);
			return false;
		} else {
			Alert.setText("");		
			return true;
		}
	}
	public static boolean CheckIfDateIsValid(LocalDate OrignalDate ,LocalDate setDate,Label Alert) {
		if(setDate.compareTo(OrignalDate) == 0 || setDate.compareTo(OrignalDate) == 1) {
			Alert.setText("");
			return true;
		}
		Alert.setText(ChooseDate);
		return false;
	}
	/**
	 * open client connection
	 */
	public void setConnection();
	/**
	 * close client connection
	 */
	public void closeConnection();


}
