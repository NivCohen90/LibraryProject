package OBLFX;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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
		alert.showAndWait();
		//Optional<ButtonType> result = alert.showAndWait();
	}
	
	/**
	 * Convert Exception Stacktrace into String.
	 * @param e
	 * @return
	 */
	public static String getExceptionStacktraceString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		return sStackTrace;
	}
	
	/**
	 * Show Exception Alert Dialog.
	 * @param ex
	 */
	public static void ExceptionAlert(Exception ex){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText(ex.getClass().getName());
		alert.setContentText(ex.getMessage());
		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception Stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
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
