package Client;

import Interfaces.IAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WindowButtons extends HBox {

	final static String CloseIcon2 = "/MenuIcons/close4.png";
	final static String minimizeIcon2 = "/MenuIcons/minimize2.png";
	final static String CloseIcon3 = "/MenuIcons/close2.png";
	final static String minimizeIcon3 = "/MenuIcons/minimize3.png";
	final static String CloseIcon = "/MenuIcons/Close.png";
	final static String minimizeIcon = "/MenuIcons/Minimize.png";
	final static String BackgroundStyle = "-fx-background-color:#F0F8FF";
	final static String ClickedBackgroundStyle = "-fx-background-color:#F0FFFF";
	public final static int height = 25;

	public WindowButtons(ToolBar toolbar, Stage primaryStage) {
		toolbar.setPrefHeight(height);
		toolbar.setMinHeight(height);
		toolbar.setMaxHeight(height);
		toolbar.setStyle("-fx-background-color:#F0F8FF");
		Button closeBtn = new Button();
		createNewButton(closeBtn, CloseIcon2, toolbar);
		setCloseOnAction(closeBtn, toolbar, primaryStage);
		closeBtn.setOnMouseEntered(value -> {
			Image image = new Image(getClass().getResource(CloseIcon3).toExternalForm());
			ImageView imageView = new ImageView(image);
			closeBtn.setGraphic(imageView);
		});
		closeBtn.setOnMouseExited(value -> {
			Image image = new Image(getClass().getResource(CloseIcon2).toExternalForm());
			ImageView imageView = new ImageView(image);
			closeBtn.setGraphic(imageView);
		});

		Button minimizeBtn = new Button();
		createNewButton(minimizeBtn, minimizeIcon2, toolbar);
		setMinimizeOnAction(minimizeBtn, primaryStage);
		minimizeBtn.setOnMouseEntered(value -> {
			Image image = new Image(getClass().getResource(minimizeIcon3).toExternalForm());
			ImageView imageView = new ImageView(image);
			minimizeBtn.setGraphic(imageView);
		});
		minimizeBtn.setOnMouseExited(value -> {
			Image image = new Image(getClass().getResource(minimizeIcon2).toExternalForm());
			ImageView imageView = new ImageView(image);
			minimizeBtn.setGraphic(imageView);
		});
	}

	private void createNewButton(Button btn, String IconPath, ToolBar toolbar) {
		btn.setStyle(BackgroundStyle);
		btn.setAlignment(Pos.CENTER_LEFT);
		// btndecoration(btn);
		Image image = new Image(getClass().getResource(IconPath).toExternalForm());
		ImageView imageView = new ImageView(image);
		btn.setGraphic(imageView);
		toolbar.getItems().add(btn);
	}

	private void setCloseOnAction(Button closeBtn, ToolBar toolbar, Stage primaryStage) {
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				actionEvent.consume();
				IAlert.showExitAlert();
			}
		});

		this.getChildren().add(closeBtn);
		final Delta dragDelta = new Delta();
		toolbar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					dragDelta.setX(me.getSceneX());
					dragDelta.setY(me.getSceneY());
				}
			}
		});

		toolbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					toolbar.getScene().getWindow().setX(me.getScreenX() - dragDelta.getX());
					toolbar.getScene().getWindow().setY(me.getScreenY() - dragDelta.getY());
				}
			}
		});
	}

	private void setMinimizeOnAction(Button minimizeBtn, Stage primaryStage) {
		minimizeBtn.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
				stage.setIconified(true);
			}
		});

	}
	/*
	 * private void btndecoration(Button btn) { btn.setOnMouseEntered(value -> {
	 * btn.setStyle(ClickedBackgroundStyle); }); btn.setOnMouseExited(value -> {
	 * btn.setStyle(BackgroundStyle); }); }
	 */
}