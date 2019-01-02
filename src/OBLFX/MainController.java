package OBLFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainController {


	  @FXML
	  private Button LoginButton;

	  @FXML
	  void LoginScreen(ActionEvent event) throws Exception{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/OBLFX/LoginForm.fxml").openStream());
			
		LoginFormController loginFormController = loader.getController();
			
		Scene scene = new Scene(root);			
		//scene.getStylesheets().add(getClass().getResource("/OBLFX/LoginForm.css").toExternalForm());
			
		primaryStage.setScene(scene);		
		primaryStage.show();
		}
	}
