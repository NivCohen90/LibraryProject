package Server;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Server extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Server.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent t) {
					t.consume();
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("EXIT Program");
					alert.setHeaderText("You sure you want to Exit?");
					alert.setContentText("Click OK to EXIT.");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						Platform.exit();
						System.exit(0);
					}
				}
			});
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			String Error = "SQLException: " + e.getMessage();
			ServerController.updateLog(Error);
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
