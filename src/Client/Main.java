package Client;


import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../CSS/WelcomeScreen.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    public void handle(WindowEvent t) {
			    	Alert alert = new Alert(AlertType.CONFIRMATION);
			    	alert.setTitle("Confirmation Dialog");
			    	alert.setHeaderText("You sure you want to Exit?");
			    	alert.setContentText("Click OK to EXIT.");
			    	Optional<ButtonType> result = alert.showAndWait();
			    	if (result.get() == ButtonType.OK){
				        Platform.exit();
				        System.exit(0);
			    	}
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
