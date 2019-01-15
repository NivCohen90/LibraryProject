package Client;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public WindowButtons(ToolBar toolbar, Stage primaryStage) {
        Button closeBtn = new Button();
		Image image = new Image(getClass().getResource("/MenuIcons/Log-Out-icon.png").toExternalForm());
		ImageView imageView = new ImageView(image);
        closeBtn.setGraphic(imageView);
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
}