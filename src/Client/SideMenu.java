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

public class SideMenu {
	private VBox vbox;
	private Menuicons clicked;
	final static String SearchFXML = "../FXML/SearchBook.fxml";
	final static String LoginFXML = "../FXML/LoginForm.fxml";
	final static String CreateNewSubscriberFXML = "../FXML/CreateNewSubscriber.fxml";
	final static String SubscriberHistoryFXML = "../FXML/SubscriberHistory.fxml";
	final static String ReaderCardFXML = "../FXML/ReaderCard.fxml";
	final static String SearchSubscriberFXML = "../FXML/SearchSubscriber.fxml";
	final static String SearchLibrarianFXML = "../FXML/SearchLibrarian.fxml";
	final static String CreateReportFXML = "../FXML/CreateReport.fxml";
	final static String ReportFaultFXML = "../FXML/ReportFault.fxml";
	final static String ManageCatalogFXML = "../FXML/ManageCatalog.fxml";
	final static String ReturnBookFXML = "../FXML/ReturnBook.fxml";
	final static String NewLoanFXML = "../FXML/NewLoan.fxml";
	final static String UpdateSubscriberStatusFXML = "../FXML/UpdateSubscriberStatus.fxml";
	final static String StatisticsFXML = "../FXML/CreateReport.fxml";
	final static String ConnectionSettingsFXML = "../FXML/ConnectionSettings.fxml";

	final static String BackgroundStyle = "-fx-background-color:#F0F8FF";
	final static String ClickedBackgroundStyle = "-fx-background-color:#F0FFFF";
	final static String BlueBackgroundStyle = "-fx-background-color:#00FFFF";

	public SideMenu(MenuType menuType) {
		clicked = Menuicons.Nothing;
		vbox = new VBox();
		vbox.setStyle("-fx-background-color:#F0F8FF");
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
		btn.setStyle(BackgroundStyle);
		buttonHandler(icon, btn);
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5, 50);
		paneIndicator.setStyle(BackgroundStyle);
		menuDecorator(btn, paneIndicator);
		HBox hbox = new HBox(paneIndicator, btn);
		return hbox;
	}

	private void menuDecorator(Button btn, Pane pane) {
		btn.setOnMouseEntered(value -> {
			btn.setStyle(ClickedBackgroundStyle);
			pane.setStyle(BlueBackgroundStyle);
		});
		btn.setOnMouseExited(value -> {
			btn.setStyle(BackgroundStyle);
			pane.setStyle(BackgroundStyle);
		});
	}

	private void PowerBtnHandler(Button btn) {
		btn.setOnMouseClicked(power -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("You sure you want to Exit?");
			alert.setContentText("Click OK to EXIT.");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	private void RightSideBtnHandler(Button btn, String FXMLpath, Menuicons IconName) {
		btn.setOnMouseClicked(search -> {
			try {
				if (!(clicked.equals(IconName))) {
				AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource(FXMLpath));
				pane.setStyle("-fx-border-width: 2;");
				pane.setStyle("-fx-border-color: grey;");
				Main.root.setRight(pane);
				clicked = IconName;
			}
				
			} catch (IOException e) {
				e.printStackTrace();
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
				RightSideBtnHandler(btn, LoginFXML, IconName);
			break;
		case History:
				btn.setText("History");
				RightSideBtnHandler(btn, SubscriberHistoryFXML, IconName);
			break;
		case SearchBook:
				btn.setText("Search Book");
				RightSideBtnHandler(btn, SearchFXML, IconName);
			break;
		case SearchLibrarian:
				btn.setText("Search Librarian");
				RightSideBtnHandler(btn, SearchLibrarianFXML, IconName);

			break;
		case SearchSubscriber:
				btn.setText("Search Subscriber");
				RightSideBtnHandler(btn, SearchSubscriberFXML, IconName);
			break;
		case SubscriberCard:
				btn.setText("Reader Card");
				RightSideBtnHandler(btn, ReaderCardFXML, IconName);
				break;
		case LibrarianCard:
				btn.setText("Librarian Details");
				RightSideBtnHandler(btn, ReaderCardFXML, IconName);
				break;
		case ManagerCard:
				btn.setText("Manager Details");
				RightSideBtnHandler(btn, ReaderCardFXML, IconName);
			break;
		case Report:
			btn.setText("Report Fault");
			RightSideBtnHandler(btn, ReportFaultFXML, IconName);
			break;
		case catalog:
			btn.setText("Manage Catalog");
			RightSideBtnHandler(btn, CreateReportFXML, IconName);
			break;
		case CreateSubscriber:
			btn.setText("Create New Subscriber");
			RightSideBtnHandler(btn, CreateNewSubscriberFXML, IconName);
			break;
		case ReturnBook:
			btn.setText("Return Book");
			RightSideBtnHandler(btn, ReturnBookFXML, IconName);
			break;
		case CreateLoan:
			btn.setText("Create Loan Book");
			RightSideBtnHandler(btn, NewLoanFXML, IconName);
			break;
		case ChangeSubscriberStatus:
			btn.setText("Update Subscriber Status");
			RightSideBtnHandler(btn, UpdateSubscriberStatusFXML, IconName);
			break;
		case Statistics:
			btn.setText("Create Report");
			RightSideBtnHandler(btn, StatisticsFXML, IconName);
			break;
		case Connection:
			btn.setText("Connection Settings");
			RightSideBtnHandler(btn, ConnectionSettingsFXML, IconName);
			break;
		default:
			break;
		}

	}

	public VBox getVBox() {
		return vbox;
	}
}
