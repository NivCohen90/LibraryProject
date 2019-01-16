package OBLFX;

import Users.IGeneralData.operationsReturn;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public interface IGUIcontroller {
	
	final static String UserNameErrorNumebrs = "Only numbers allowed.";
	final static String UserNameErrorDigits = "ID contains 9 digits.";
	final static String OnlyNumbers = "^[0-9]*$";
	
	public void receiveMassageFromServer(Object msg, operationsReturn op);
	public static boolean CheckID(TextField ID, Label Alert) {
		if (ID.getText().length() > 9 || ID.getText().length() == 0) {

			Alert.setText(UserNameErrorDigits);

		} else if (!ID.getText().matches(OnlyNumbers)) {

			Alert.setText(UserNameErrorNumebrs);

		} else {

			Alert.setText("");
			return true;
		}
		return false;
	}
}
