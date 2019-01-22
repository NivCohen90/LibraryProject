package Client;

import Users.IGeneralData.MenuType;
import Users.IGeneralData.Menuicons;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import OBLFX.IFXMLpathAndStyle;
import OBLFX.IAlert;

public class SideMenu {
	private VBox vbox;
	public static Menuicons clicked;

	public static AnchorPane APSearchFXML;
	public static AnchorPane APLoginFXML;
	public static AnchorPane APCreateNewSubscriberFXML;
	public static AnchorPane APSubscriberHistoryFXML;
	public static AnchorPane APReaderCardFXML;
	public static AnchorPane APSearchSubscriberFXML;
	public static AnchorPane APSearchLibrarianFXML;
	public static AnchorPane APCreateReportFXML;
	public static AnchorPane APReportFaultFXML;
	public static AnchorPane APManageCatalogFXML;
	public static AnchorPane APReturnBookFXML;
	public static AnchorPane APNewLoanFXML;
	public static AnchorPane APUpdateSubscriberStatusFXML;
	public static AnchorPane APStatisticsFXML;
	public static AnchorPane APConnectionSettingsFXML;
	public static AnchorPane APWelcomeScreen;

	public SideMenu(MenuType menuType) {
		vbox = new VBox();
		vbox.setStyle(IFXMLpathAndStyle.BackgroundStyle);
		vbox.setPrefWidth(200);
		loadAllFXMLAnchorPanes();
		switch (menuType) {
		case MainMenu:
			vbox.getChildren().add(Item(Menuicons.Login));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.Connection));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case SubscriberMenu:
			vbox.getChildren().add(Item(Menuicons.SubscriberCard));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.History));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case LibrarianMenu:
			vbox.getChildren().add(Item(Menuicons.LibrarianCard));
			vbox.getChildren().add(Item(Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.CreateLoan));
			vbox.getChildren().add(Item(Menuicons.ReturnBook));
			vbox.getChildren().add(Item(Menuicons.Report));
			vbox.getChildren().add(Item(Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(Menuicons.catalog));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case LibrarianManagerMenu:
			vbox.getChildren().add(Item(Menuicons.ManagerCard));
			vbox.getChildren().add(Item(Menuicons.SearchLibrarian));
			vbox.getChildren().add(Item(Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.CreateLoan));
			vbox.getChildren().add(Item(Menuicons.ReturnBook));
			vbox.getChildren().add(Item(Menuicons.Report));
			vbox.getChildren().add(Item(Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(Menuicons.catalog));
			vbox.getChildren().add(Item(Menuicons.ChangeSubscriberStatus));
			vbox.getChildren().add(Item(Menuicons.Statistics));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		}

	}

	private HBox Item(Menuicons icon) {
		Image image = new Image(getClass().getResource("/MenuIcons/" + icon.toString() + ".png").toExternalForm());
		ImageView imageView = new ImageView(image);
		Button btn = new Button();
		btn.setGraphic(imageView);
		btn.setAlignment(Pos.CENTER_LEFT);
		btn.setPrefSize(195, 50);
		btn.setStyle(IFXMLpathAndStyle.BackgroundStyle);
		buttonHandler(icon, btn);
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5, 50);
		paneIndicator.setStyle(IFXMLpathAndStyle.BackgroundStyle);
		menuDecorator(btn, paneIndicator);
		HBox hbox = new HBox(paneIndicator, btn);
		return hbox;
	}

	private void loadAllFXMLAnchorPanes() {
		try {
			APSearchFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.SearchFXML));
			APLoginFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.LoginFXML));
			APCreateNewSubscriberFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateNewSubscriberFXML));
			APSubscriberHistoryFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SubscriberHistoryFXML));
			APReaderCardFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML));
			APSearchSubscriberFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchSubscriberFXML));
			APSearchLibrarianFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchLibrarianFXML));
			APCreateReportFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateReportFXML));
			APReportFaultFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReportFaultFXML));
			// APManageCatalogFXML = (AnchorPane)
			// FXMLLoader.load(getClass().getResource(FXMLpathAndStyle.ManageCatalogFXML));
			APReturnBookFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.ReturnBookFXML));
			APNewLoanFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.NewLoanFXML));
			APUpdateSubscriberStatusFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.UpdateSubscriberStatusFXML));
			APStatisticsFXML = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.StatisticsFXML));
			APConnectionSettingsFXML = (AnchorPane) FXMLLoader
					.load(getClass().getResource(IFXMLpathAndStyle.ConnectionSettingsFXML));
			APWelcomeScreen = (AnchorPane) FXMLLoader.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void menuDecorator(Button btn, Pane pane) {
		btn.setOnMouseEntered(value -> {
			btn.setStyle(IFXMLpathAndStyle.ClickedBackgroundStyle);
			pane.setStyle(IFXMLpathAndStyle.BlueBackgroundStyle);
		});
		btn.setOnMouseExited(value -> {
			btn.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
		});
	}

	private void PowerBtnHandler(Button btn) {
		btn.setOnMouseClicked(power -> {
			IAlert.showExitAlert();
		});

	}

	private void RightSideBtnHandler(Button btn, AnchorPane anchorPane, Menuicons IconName) {
		btn.setOnMouseClicked(search -> {
			try {
				// if (!(clicked.equals(IconName))) {
				anchorPane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
				Main.root.setRight(anchorPane);
				// clicked = IconName;
				// }

			} catch (Exception e) {
				e.printStackTrace();
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
			RightSideBtnHandler(btn, APLoginFXML, IconName);
			break;
		case History:
			btn.setText("History");
			RightSideBtnHandler(btn, APSubscriberHistoryFXML, IconName);
			break;
		case SearchBook:
			btn.setText("Search Book");
			RightSideBtnHandler(btn, APSearchFXML, IconName);
			break;
		case SearchLibrarian:
			btn.setText("Search Librarian");
			RightSideBtnHandler(btn, APSearchLibrarianFXML, IconName);

			break;
		case SearchSubscriber:
			btn.setText("Search Subscriber");
			RightSideBtnHandler(btn, APSearchSubscriberFXML, IconName);
			break;
		case SubscriberCard:
			btn.setText("Reader Card");
			RightSideBtnHandler(btn, APReaderCardFXML, IconName);
			break;
		case LibrarianCard:
			btn.setText("Librarian Details");
			RightSideBtnHandler(btn, APReaderCardFXML, IconName);
			break;
		case ManagerCard:
			btn.setText("Manager Details");
			RightSideBtnHandler(btn, APReaderCardFXML, IconName);
			break;
		case Report:
			btn.setText("Report Fault");
			RightSideBtnHandler(btn, APReportFaultFXML, IconName);
			break;
		case catalog:
			btn.setText("Manage Catalog");
			// RightSideBtnHandler(btn, APCreateReportFXML, IconName);
			break;
		case CreateSubscriber:
			btn.setText("Create New Subscriber");
			RightSideBtnHandler(btn, APCreateNewSubscriberFXML, IconName);
			break;
		case ReturnBook:
			btn.setText("Return Book");
			RightSideBtnHandler(btn, APReturnBookFXML, IconName);
			break;
		case CreateLoan:
			btn.setText("Create Loan Book");
			RightSideBtnHandler(btn, APNewLoanFXML, IconName);
			break;
		case ChangeSubscriberStatus:
			btn.setText("Update Subscriber Status");
			RightSideBtnHandler(btn, APUpdateSubscriberStatusFXML, IconName);
			break;
		case Statistics:
			btn.setText("Create Report");
			RightSideBtnHandler(btn, APStatisticsFXML, IconName);
			break;
		case Connection:
			btn.setText("Connection Settings");
			RightSideBtnHandler(btn, APConnectionSettingsFXML, IconName);
			break;
		default:
			break;
		}

	}

	public VBox getVBox() {
		return vbox;
	}
}
