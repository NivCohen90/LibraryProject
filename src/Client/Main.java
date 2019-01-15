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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.setLeft(sidePane());
			root.setRight((AnchorPane) FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml")));
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
	
	private VBox sidePane() {
		VBox vbox = new VBox();
		vbox.setPrefWidth(50);
		vbox.getChildren().add(Item("1"));
		vbox.getChildren().add(Item("2"));
		vbox.setStyle("-fx-background-color:#F0F8FF");
		return vbox;
	}
	
	private HBox Item(String icon) {
		Image image = new Image(getClass().getResource("/MenuIcons/" + icon + ".png").toExternalForm());
		ImageView imageView = new ImageView(image);
		Button btn = new Button();
		btn.setGraphic(imageView);
		btn.setPrefSize(45, 50);
		btn.setStyle("-fx-background-color:#F0F8FF");
		if(icon.equals("2")) {
			PowerBtnHandler(btn);
		}
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5,50);
		paneIndicator.setStyle("-fx-background-color:#F0F8FF");
		menuDecorator(btn, paneIndicator);
		HBox hbox = new HBox(paneIndicator, btn);
		return hbox;
	}
	
	private void menuDecorator(Button btn, Pane pane) {
		btn.setOnMouseEntered(value->{
			btn.setStyle("-fx-background-color:#F0FFFF");
			pane.setStyle("-fx-background-color:#00FFFF");
		});
		btn.setOnMouseExited(value->{
			btn.setStyle("-fx-background-color:#F0F8FF");
			pane.setStyle("-fx-background-color:#F0F8FF");
		});
	}
	
	private void PowerBtnHandler(Button btn) {
		btn.setOnMouseClicked(value->{
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("You sure you want to Exit?");
	    	alert.setContentText("Click OK to EXIT.");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
		        Platform.exit();
		        System.exit(0);
	    	}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
