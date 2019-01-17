package Client;

import Client.SideMenu;
import Users.IGeneralData;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	static BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		try {
	        //remove window decoration
	        primaryStage.initStyle(StageStyle.UNDECORATED);
			SideMenu sideMenu = new SideMenu(IGeneralData.MenuType.MainMenu);
			root = new BorderPane();
			root.setLeft(SideMenu.getVBox());
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("../FXML/WelcomeScreen.fxml"));
			pane.setStyle("-fx-border-width: 2;");
			pane.setStyle("-fx-border-color: grey;");
			root.setRight(pane);		
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../CSS/WelcomeScreen.css").toExternalForm());
	        ToolBar toolBar = new ToolBar();
	        int height = 25;
	        toolBar.setPrefHeight(height);
	        toolBar.setMinHeight(height);
	        toolBar.setMaxHeight(height);
	        new WindowButtons(toolBar, primaryStage);
	        root.setTop(toolBar);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent t) {
					t.consume();
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation Dialog");
					alert.setHeaderText("You sure you want to Exit?");
					alert.setContentText("Click OK to EXIT.");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
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
