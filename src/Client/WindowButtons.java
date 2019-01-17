package Client;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class WindowButtons extends HBox {

	final static String CloseIcon = "/MenuIcons/Close.png";
	final static String minimizeIcon = "/MenuIcons/Minimize.png";
	final static String BackgroundStyle = "-fx-background-color:#F0F8FF";
	final static String ClickedBackgroundStyle = "-fx-background-color:#F0FFFF";
    public WindowButtons(ToolBar toolbar, Stage primaryStage) {
    	toolbar.setStyle("-fx-background-color:#F0F8FF");
        Button closeBtn = new Button();
        createNewButton(closeBtn, CloseIcon, toolbar);
        setCloseOnAction(closeBtn, toolbar, primaryStage);
        Button minimizeBtn = new Button();
        createNewButton(minimizeBtn, minimizeIcon, toolbar);
        setMinimizeOnAction(minimizeBtn, primaryStage);
    }
    private void createNewButton(Button btn, String IconPath, ToolBar toolbar) {
    	btn.setStyle(BackgroundStyle);
    	btn.setAlignment(Pos.CENTER_LEFT);
        btndecoration(btn);
		Image image = new Image(getClass().getResource(IconPath).toExternalForm());
		ImageView imageView = new ImageView(image);
		btn.setGraphic(imageView);
		toolbar.getItems().add(btn);
    }
    private void setCloseOnAction(Button closeBtn,ToolBar toolbar, Stage primaryStage) {
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
            	actionEvent.consume();
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

        this.getChildren().add(closeBtn);
        final Delta dragDelta = new Delta();
        toolbar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                	dragDelta.x = me.getSceneX();
                	dragDelta.y = me.getSceneY();
                }
            }
        });

        toolbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                	toolbar.getScene().getWindow().setX(me.getScreenX() - dragDelta.x);
                	toolbar.getScene().getWindow().setY(me.getScreenY() - dragDelta.y);
                }
            }
        });
    }
    
    private void setMinimizeOnAction(Button minimizeBtn, Stage primaryStage) {
    	minimizeBtn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                stage.setIconified(true);
            }
        });
   
    }
	private void btndecoration(Button btn) {
		btn.setOnMouseEntered(value -> {
			btn.setStyle(ClickedBackgroundStyle);
		});
		btn.setOnMouseExited(value -> {
			btn.setStyle(BackgroundStyle);
		});
	}
}