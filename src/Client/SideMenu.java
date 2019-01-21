package Client;

import Users.IGeneralData;
import Users.IGeneralData.MenuType;
import Users.IGeneralData.Menuicons;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import OBLFX.FXMLpathAndStyle;
import OBLFX.IAlert;

public class SideMenu {
	private VBox vbox;
	private Menuicons clicked;

	public SideMenu(MenuType menuType) {
		clicked = Menuicons.Nothing;
		vbox = new VBox();
		vbox.setStyle(FXMLpathAndStyle.BackgroundStyle);
		vbox.setPrefWidth(200);
		switch (menuType) {
		case MainMenu:
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Login));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Connection));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Exit));
			break;
		case SubscriberMenu:
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SubscriberCard));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.History));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Exit));
			break;
		case LibrarianMenu:
			vbox.getChildren().add(Item(IGeneralData.Menuicons.LibrarianCard));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.CreateLoan));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.ReturnBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Report));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.catalog));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Exit));
			break;
		case LibrarianManagerMenu:
			vbox.getChildren().add(Item(IGeneralData.Menuicons.ManagerCard));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchLibrarian));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.SearchBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.CreateLoan));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.ReturnBook));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Report));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.catalog));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.ChangeSubscriberStatus));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Statistics));
			vbox.getChildren().add(Item(IGeneralData.Menuicons.Exit));
			break;
		}

	}

	private HBox Item(IGeneralData.Menuicons icon) {
		Image image = new Image(getClass().getResource("/MenuIcons/" + icon.toString() + ".png").toExternalForm());
		ImageView imageView = new ImageView(image);
		Button btn = new Button();
		btn.setGraphic(imageView);
		btn.setAlignment(Pos.CENTER_LEFT);
		btn.setPrefSize(195, 50);
		btn.setStyle(FXMLpathAndStyle.BackgroundStyle);
		buttonHandler(icon, btn);
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5, 50);
		paneIndicator.setStyle(FXMLpathAndStyle.BackgroundStyle);
		menuDecorator(btn, paneIndicator);
		HBox hbox = new HBox(paneIndicator, btn);
		return hbox;
	}

	private void menuDecorator(Button btn, Pane pane) {
		btn.setOnMouseEntered(value -> {
			btn.setStyle(FXMLpathAndStyle.ClickedBackgroundStyle);
			pane.setStyle(FXMLpathAndStyle.BlueBackgroundStyle);
		});
		btn.setOnMouseExited(value -> {
			btn.setStyle(FXMLpathAndStyle.BackgroundStyle);
			pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
		});
	}

	private void PowerBtnHandler(Button btn) {
		btn.setOnMouseClicked(power -> {
			IAlert.showExitAlert();
		});

	}

	private void RightSideBtnHandler(Button btn, String FXMLpath, Menuicons IconName) {
		btn.setOnMouseClicked(search -> {
			try {
				if (!(clicked.equals(IconName))) {
					AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpath));
					pane.setStyle(FXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(pane);
					clicked = IconName;
				}

			} catch (Exception e) {
				IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(),
						e.getMessage());
			}
		});
	}

	private void buttonHandler(Menuicons IconName, Button btn) {
		switch (IconName) {
		case Exit:
			btn.setText("Exit Program");
			PowerBtnHandler(btn);
			break;
		case Login:
			btn.setText("Login");
			RightSideBtnHandler(btn, FXMLpathAndStyle.LoginFXML, IconName);
			break;
		case History:
			btn.setText("History");
			RightSideBtnHandler(btn, FXMLpathAndStyle.SubscriberHistoryFXML, IconName);
			break;
		case SearchBook:
			btn.setText("Search Book");
			RightSideBtnHandler(btn, FXMLpathAndStyle.SearchFXML, IconName);
			break;
		case SearchLibrarian:
			btn.setText("Search Librarian");
			RightSideBtnHandler(btn, FXMLpathAndStyle.SearchLibrarianFXML, IconName);

			break;
		case SearchSubscriber:
			btn.setText("Search Subscriber");
			RightSideBtnHandler(btn, FXMLpathAndStyle.SearchSubscriberFXML, IconName);
			break;
		case SubscriberCard:
			btn.setText("Reader Card");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ReaderCardFXML, IconName);
			break;
		case LibrarianCard:
			btn.setText("Librarian Details");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ReaderCardFXML, IconName);
			break;
		case ManagerCard:
			btn.setText("Manager Details");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ReaderCardFXML, IconName);
			break;
		case Report:
			btn.setText("Report Fault");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ReportFaultFXML, IconName);
			break;
		case catalog:
			btn.setText("Manage Catalog");
			RightSideBtnHandler(btn, FXMLpathAndStyle.CreateReportFXML, IconName);
			break;
		case CreateSubscriber:
			btn.setText("Create New Subscriber");
			RightSideBtnHandler(btn, FXMLpathAndStyle.CreateNewSubscriberFXML, IconName);
			break;
		case ReturnBook:
			btn.setText("Return Book");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ReturnBookFXML, IconName);
			break;
		case CreateLoan:
			btn.setText("Create Loan Book");
			RightSideBtnHandler(btn, FXMLpathAndStyle.NewLoanFXML, IconName);
			break;
		case ChangeSubscriberStatus:
			btn.setText("Update Subscriber Status");
			RightSideBtnHandler(btn, FXMLpathAndStyle.UpdateSubscriberStatusFXML, IconName);
			break;
		case Statistics:
			btn.setText("Create Report");
			RightSideBtnHandler(btn, FXMLpathAndStyle.StatisticsFXML, IconName);
			break;
		case Connection:
			btn.setText("Connection Settings");
			RightSideBtnHandler(btn, FXMLpathAndStyle.ConnectionSettingsFXML, IconName);
			break;
		default:
			break;
		}

	}

	public VBox getVBox() {
		return vbox;
	}

	public Menuicons getclicked() {
		return clicked;
	}

	public void setclicked(Menuicons status) {
		clicked = status;
	}
}
