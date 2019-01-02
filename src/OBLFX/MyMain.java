package OBLFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyMain extends Application {

	public static void main(String[] args) throws Exception {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/OBLFX/WelcomeScreen.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/OBLFX/WelcomeScreen.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}
//hello
}
