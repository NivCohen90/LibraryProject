package Client;
import Users.IGeneralData;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SideMenu {
	static VBox vbox;
	final static String SearchFXML = "../FXML/MainScreen.fxml";
	final static String LoginFXML = "../FXML/LoginForm.fxml";
	public SideMenu() {
		SideMenu.vbox = new VBox();
		vbox.setPrefWidth(100);
		vbox.getChildren().add(Item(IGeneralData.Menuicons.account));
		vbox.getChildren().add(Item(IGeneralData.Menuicons.search));
		vbox.getChildren().add(Item(IGeneralData.Menuicons.power));
		vbox.setStyle("-fx-background-color:#F0F8FF");

	}
	
	private HBox Item(IGeneralData.Menuicons icon) {
		Image image = new Image(getClass().getResource("/MenuIcons/" + icon.toString() + ".png").toExternalForm());
		ImageView imageView = new ImageView(image);
		Button btn = new Button();
		btn.setGraphic(imageView);
		btn.setText(icon.toString());
		btn.setPrefSize(95, 50);
		btn.setStyle("-fx-background-color:#F0F8FF");
		buttonHandler(icon, btn);
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
		btn.setOnMouseClicked(power->{
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
	
	private void RightSideBtnHandler(Button btn, String FXMLpath) {
		btn.setOnMouseClicked(search->{
			try {
				Main.root.setRight((AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpath)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	private void buttonHandler(IGeneralData.Menuicons IconName, Button btn) {
		switch(IconName) {
		case search:
			RightSideBtnHandler(btn, SearchFXML );
			break;
		case power:
			PowerBtnHandler(btn);
			break;
		case account:
			RightSideBtnHandler(btn, LoginFXML);
		break;
		default:
			break;
		}

	}
	
	public static VBox getVBox() {
		return SideMenu.vbox;
	}
}
