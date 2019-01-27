package Client;

import java.util.Optional;

import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import SystemObjects.GeneralData;
import Users.Librarian;
import Users.Subscriber;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	public static BorderPane root;
	public static Stage PrimaryStage;
	public static SideMenu sideMenu;
	public static ToolBar toolBar;


	
	@Override
	public void start(Stage primaryStage) {
		try {
			PrimaryStage = primaryStage;
			PrimaryStage.initStyle(StageStyle.UNDECORATED);
			sideMenu = new SideMenu(GeneralData.MenuType.MainMenu);
			root = new BorderPane();
			root.setLeft(sideMenu.getVBox());
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen));
			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			root.setRight(pane);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(IFXMLpathAndStyle.WelcomeScreenCSS).toExternalForm());
			toolBar = new ToolBar();
			new WindowButtons(toolBar, PrimaryStage);
			root.setTop(toolBar);
			PrimaryStage.setScene(scene);
			PrimaryStage.setResizable(false);
			PrimaryStage.show();
			PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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
		} catch (Exception e) {
			e.printStackTrace();
			IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(), e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
