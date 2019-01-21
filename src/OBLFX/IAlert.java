package OBLFX;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public interface IAlert {

	/**
	 * For Example = IAlert.setandShowAlert(AlertType.ERROR,
	 * IAlert.ExceptionErrorTitle, e.getClass().getName(), e.getMessage());
	 */
	final static String ExceptionErrorTitle = "Exception ERROR";

	public static void setandShowAlert(AlertType Alerttype, String Title, String HeaderText, String ContentText) {
		Alert alert = new Alert(Alerttype);
		alert.setTitle(Title);
		alert.setHeaderText(HeaderText);
		alert.setContentText(ContentText);
		Optional<ButtonType> result = alert.showAndWait();
	}

	public static void showExitAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Dialog");
		alert.setHeaderText("You sure you want to Exit?");
		alert.setContentText("Click OK to EXIT.");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
			System.exit(0);
		}
	}

	public default void show() {
	}
}
