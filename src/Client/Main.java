package Client;

import Client.SideMenu;
import OBLFX.FXMLpathAndStyle;
import OBLFX.IAlert;
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

	public static BorderPane root;
	public static Stage PrimaryStage;
	public static SideMenu sideMenu;
	public static ToolBar toolBar;

	@Override
	public void start(Stage primaryStage) {
		try {
			PrimaryStage = primaryStage;
			PrimaryStage.initStyle(StageStyle.UNDECORATED);
			sideMenu = new SideMenu(IGeneralData.MenuType.MainMenu);
			root = new BorderPane();
			root.setLeft(sideMenu.getVBox());
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpathAndStyle.WelcomeScreen));
			pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
			root.setRight(pane);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(FXMLpathAndStyle.WelcomeScreenCSS).toExternalForm());
			toolBar = new ToolBar();
			new WindowButtons(toolBar, PrimaryStage);
			root.setTop(toolBar);
			PrimaryStage.setScene(scene);
			PrimaryStage.setResizable(false);
			PrimaryStage.show();
		} catch (Exception e) {
			IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(), e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
