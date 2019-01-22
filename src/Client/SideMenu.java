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
import java.util.ArrayList;

import OBLFX.IFXMLpathAndStyle;
import OBLFX.IGUIcontroller;
import OBLFX.LoginFormController;
import OBLFX.SearchPagesController;
import OBLFX.SubscriberCardController;
import OBLFX.AddBookController;
import OBLFX.AddNewSubscriberController;
import OBLFX.CreateReportController;
import OBLFX.IAlert;

public class SideMenu {
	private VBox vbox;
	public static Menuicons clicked;

	/**
	 * each AnchorPane described a page.
	 * 
	 * @author nivco
	 * @param each Object from this type will satrt with 'AP'
	 */
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
	public static ArrayList<IGUIcontroller> controllerArray;
	
	/**
	 * Create new menu inorder to menuType.
	 * 
	 * @param menuType
	 */
	public SideMenu(MenuType menuType) {
		vbox = new VBox();
		controllerArray = new ArrayList<IGUIcontroller>();
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

	/**
	 * set a new Menu Button into HBox.
	 * 
	 * @param icon
	 * @return Hbox
	 */
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

	/**
	 * load all of the FXML pages into the AP Objects.
	 * 
	 * @author nivco
	 */
	private void loadAllFXMLAnchorPanes() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Pane p = fxmlLoader.load(getClass().getResource("foo.fxml"));
			//FooController fooController = (FooController) fxmlLoader.getController();
			APSearchFXML = (AnchorPane) fxmlLoader.load(getClass().getResource(IFXMLpathAndStyle.SearchFXML).openStream());
			controllerArray.add((SearchPagesController)fxmlLoader.getController());
			APLoginFXML = (AnchorPane) fxmlLoader.load(getClass().getResource(IFXMLpathAndStyle.LoginFXML));
			controllerArray.add((LoginFormController)fxmlLoader.getController());
			APCreateNewSubscriberFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateNewSubscriberFXML));
			controllerArray.add((AddNewSubscriberController)fxmlLoader.getController());
			APSubscriberHistoryFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SubscriberHistoryFXML));
			APReaderCardFXML = (AnchorPane) fxmlLoader.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML));
			controllerArray.add((SubscriberCardController)fxmlLoader.getController());
			APSearchSubscriberFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchSubscriberFXML));
			controllerArray.add((SearchPagesController)fxmlLoader.getController());
			APSearchLibrarianFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchLibrarianFXML));
			controllerArray.add((SearchPagesController)fxmlLoader.getController());
			APCreateReportFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateReportFXML));
			controllerArray.add((CreateReportController)fxmlLoader.getController());
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

	/**
	 * set style to a button when Enter or Exit.
	 * 
	 * @author nivco
	 * @param btn
	 * @param pane
	 */
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

	/**
	 * set power button Alert Handler.
	 * 
	 * @author nivco
	 * @param btn
	 */
	private void PowerBtnHandler(Button btn) {
		btn.setOnMouseClicked(power -> {
			IAlert.showExitAlert();
		});

	}

	/**
	 * set handler to menu button which page to show when clicked (Pop Error Alert
	 * when failed).
	 * 
	 * @author nivco
	 * @param btn
	 * @param anchorPane
	 * @param IconName
	 */
	private void RightSideBtnHandler(Button btn, AnchorPane anchorPane, Menuicons IconName) {
		btn.setOnMouseClicked(search -> {
			try {
				setConnection(IconName);
				anchorPane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
				Main.root.setRight(anchorPane);
			} catch (Exception e) {
				e.printStackTrace();
				IAlert.setandShowAlert(AlertType.ERROR, IAlert.ExceptionErrorTitle, e.getClass().getName(),
						e.getMessage());
			}
		});
	}

	private void setConnection(Menuicons IconName) {
		switch (IconName) {
		case Exit:
			break;
		case Login:

			break;
		case History:

			break;
		case SearchBook:

			break;
		case SearchLibrarian:

			break;
		case SearchSubscriber:

			break;
		case SubscriberCard:

			break;
		case LibrarianCard:

			break;
		case ManagerCard:

			break;
		case Report:

			break;
		case catalog:

			break;
		case CreateSubscriber:

			break;
		case ReturnBook:

			break;
		case CreateLoan:

			break;
		case ChangeSubscriberStatus:

			break;
		case Statistics:

			break;
		case Connection:

			break;
		default:
			break;
		}

	}

	/**
	 * inorder to the IconName send an AP object to the RightSideBtnHandler method.
	 * 
	 * @author nivco
	 * @param IconName
	 * @param btn
	 */
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

	/**
	 * @author nivco return the sideMenu.
	 * @return
	 */
	public VBox getVBox() {
		return vbox;
	}
}
