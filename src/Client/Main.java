package Client;

import java.util.Optional;

import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import SystemObjects.GeneralData;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import static com.sun.jna.platform.win32.WinUser.GWL_STYLE;

public class Main extends Application {

	public static BorderPane root;
	public static BorderPane topMenu;
	public static Stage PrimaryStage;
	public static SideMenu sideMenu;
	public static ToolBar toolBar;
	AnchorPane topPane;

	@Override
	public void start(Stage primaryStage) {
		try {
			PrimaryStage = primaryStage;
			PrimaryStage.initStyle(StageStyle.UNDECORATED);
			sideMenu = new SideMenu(GeneralData.MenuType.MainMenu);
			root = new BorderPane();
			topMenu = new BorderPane();
			root.setLeft(sideMenu.getVBox());
			root.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			topPane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.UserWelcomeFXML));
			AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen));
			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			root.setRight(pane);
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);
			fadeIn.play();
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});
			fadeOut.setOnFinished((e) -> {
			});
			Scene scene = new Scene(root);
			toolBar = new ToolBar();
			new WindowButtons(toolBar, PrimaryStage);
			topMenu.setTop(toolBar);
			topMenu.setBottom(topPane);
			root.setTop(topMenu);
			Image image = new Image(getClass().getResource("/MenuIcons/LibraryAppIcon.png").toExternalForm());
			PrimaryStage.getIcons().add(image);
			PrimaryStage.setScene(scene);
			PrimaryStage.setResizable(false);
			PrimaryStage.show();
			
			/*
			 * Configure the taskBar Icon to minimize and Maximize the Application. 
			 */
	        long lhwnd = com.sun.glass.ui.Window.getWindows().get(0).getNativeWindow();
	        Pointer lpVoid = new Pointer(lhwnd);
	        HWND hwnd = new HWND(lpVoid);
	        final User32 user32 = User32.INSTANCE;
	        int oldStyle = user32.GetWindowLong(hwnd, GWL_STYLE);
	        int newStyle = oldStyle | 0x00020000;//WS_MINIMIZEBOX
	        user32.SetWindowLong(hwnd, GWL_STYLE, newStyle);
	        
	        /*
	         *  Set the Close Request.
	         */
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
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
