package Client;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.HashMap;

import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;
import OBLFX.CreatesReportController;
import OBLFX.LoginFormController;
import OBLFX.NewLoanController;
import OBLFX.ReportFaultController;
import OBLFX.ReturnBookController;
import OBLFX.SearchBookController;
import OBLFX.SearchLibrarianController;
import OBLFX.SearchSubscriberController;
import OBLFX.SubscriberCardController;
import OBLFX.SubscriberHistoryController;
import OBLFX.UpdateBookController;
import OBLFX.UserWelcomeController;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.MenuType;
import SystemObjects.GeneralData.Menuicons;
import OBLFX.AddBookController;
import OBLFX.AddBookCopyController;
import OBLFX.AddNewSubscriberController;
import OBLFX.CardLibrarianController;
import OBLFX.CardLibrarianManagerController;
import OBLFX.ConnectionSettingsController;
import OBLFX.DeleteController;

public class SideMenu {

	private VBox vbox;
	public static Menuicons clicked = Menuicons.Nothing; // not in use right now.
	public static HashMap<Menuicons, IGUIcontroller> controllerMap;
	public static HashMap<Menuicons, Button> sideMenuButtons;
	public static boolean refuseConnection = false;
	private static boolean SubMenu = false;

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
	public static AnchorPane APCardLibrarianFXML;
	public static AnchorPane APCardLibrarianManagerFXML;
	public static AnchorPane APSearchSubscriberFXML;
	public static AnchorPane APSearchLibrarianFXML;
	public static AnchorPane APCreateReportFXML;
	public static AnchorPane APReportFaultFXML;
	public static AnchorPane APManageCatalogFXML;
	public static AnchorPane APAddBookFXML;
	public static AnchorPane APAddBookCopyFXML;
	public static AnchorPane APUpdateBookFXML;
	public static AnchorPane APDeleteBookFXML;
	public static AnchorPane APReturnBookFXML;
	public static AnchorPane APNewLoanFXML;
	public static AnchorPane APUpdateSubscriberStatusFXML;
	public static AnchorPane APStatisticsFXML;
	public static AnchorPane APConnectionSettingsFXML;
	public static AnchorPane APWelcomeScreenFXML;
	public static AnchorPane APUserWelcomeFXML;

	/**
	 * Create new menu inorder to menuType.
	 * 
	 * @param menuType
	 */
	public SideMenu(MenuType menuType) {
		vbox = new VBox();
		controllerMap = new HashMap<>();
		sideMenuButtons = new HashMap<>();
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
			selectMenu(sideMenuButtons.get(Menuicons.SubscriberCard));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.History));
			vbox.getChildren().add(Item(Menuicons.Connection));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case LibrarianMenu:
			vbox.getChildren().add(Item(Menuicons.LibrarianCard));
			selectMenu(sideMenuButtons.get(Menuicons.LibrarianCard));
			vbox.getChildren().add(Item(Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.CreateLoan));
			vbox.getChildren().add(Item(Menuicons.ReturnBook));
			vbox.getChildren().add(Item(Menuicons.Report));
			vbox.getChildren().add(Item(Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(Menuicons.catalog));
			vbox.getChildren().add(Item(Menuicons.Connection));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case LibrarianManagerMenu:
			vbox.getChildren().add(Item(Menuicons.ManagerCard));
			selectMenu(sideMenuButtons.get(Menuicons.ManagerCard));
			vbox.getChildren().add(Item(Menuicons.SearchLibrarian));
			vbox.getChildren().add(Item(Menuicons.SearchSubscriber));
			vbox.getChildren().add(Item(Menuicons.SearchBook));
			vbox.getChildren().add(Item(Menuicons.CreateLoan));
			vbox.getChildren().add(Item(Menuicons.ReturnBook));
			vbox.getChildren().add(Item(Menuicons.Report));
			vbox.getChildren().add(Item(Menuicons.CreateSubscriber));
			vbox.getChildren().add(Item(Menuicons.catalog));
			vbox.getChildren().add(Item(Menuicons.Statistics));
			vbox.getChildren().add(Item(Menuicons.Connection));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		case SubMenu:
			vbox.getChildren().add(Item(Menuicons.catalog));
			vbox.getChildren().add(Item(Menuicons.AddBook));
			vbox.getChildren().add(Item(Menuicons.AddBookCopy));
			vbox.getChildren().add(Item(Menuicons.UpdateBook));
			vbox.getChildren().add(Item(Menuicons.DeleteBook));
			vbox.getChildren().add(Item(Menuicons.Connection));
			vbox.getChildren().add(Item(Menuicons.Exit));
			break;
		default:
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
		sideMenuButtons.put(icon, btn);
		btn.setGraphic(imageView);
		btn.setAlignment(Pos.CENTER_LEFT);
		btn.setPrefSize(195, 50);
		btn.getStylesheets().add(getClass().getResource("/CSS/general.css").toExternalForm());
		buttonHandler(icon, btn);
		Pane paneIndicator = new Pane();
		paneIndicator.setPrefSize(5, 50);
		paneIndicator.setStyle(IFXMLpathAndStyle.BackgroundStyle);
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
			APSearchFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchFXML).openStream());
			controllerMap.put(Menuicons.SearchBook, (SearchBookController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APConnectionSettingsFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.ConnectionSettingsFXML).openStream());
			controllerMap.put(Menuicons.Connection, (ConnectionSettingsController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APLoginFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.LoginFXML).openStream());
			controllerMap.put(Menuicons.Login, (LoginFormController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APCreateNewSubscriberFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateNewSubscriberFXML).openStream());
			controllerMap.put(Menuicons.CreateSubscriber, (AddNewSubscriberController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APCardLibrarianFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CardLibrarianFXML).openStream());
			controllerMap.put(Menuicons.LibrarianCard, (CardLibrarianController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APCardLibrarianManagerFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CardLibrarianManagerFXML).openStream());
			controllerMap.put(Menuicons.ManagerCard, (CardLibrarianManagerController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APSearchSubscriberFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchSubscriberFXML).openStream());
			controllerMap.put(Menuicons.SearchSubscriber, (SearchSubscriberController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APSearchLibrarianFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SearchLibrarianFXML).openStream());
			controllerMap.put(Menuicons.SearchLibrarian, (SearchLibrarianController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APCreateReportFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.CreateReportFXML).openStream());
			controllerMap.put(Menuicons.Statistics, (CreatesReportController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APReportFaultFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.ReportFaultFXML).openStream());
			controllerMap.put(Menuicons.Report, (ReportFaultController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APReturnBookFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.ReturnBookFXML).openStream());
			controllerMap.put(Menuicons.ReturnBook, (ReturnBookController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APNewLoanFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.NewLoanFXML).openStream());
			controllerMap.put(Menuicons.CreateLoan, (NewLoanController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APAddBookFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.AddBookFXML).openStream());
			controllerMap.put(Menuicons.AddBook, (AddBookController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APAddBookCopyFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.AddBookCopyFXML).openStream());
			controllerMap.put(Menuicons.AddBookCopy, (AddBookCopyController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APUpdateBookFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.UpdateBookFXML).openStream());
			controllerMap.put(Menuicons.UpdateBook, (UpdateBookController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APDeleteBookFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.DeleteBookFXML).openStream());
			controllerMap.put(Menuicons.DeleteBook, (DeleteController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APSubscriberHistoryFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.SubscriberHistoryFXML).openStream());
			controllerMap.put(Menuicons.History, (SubscriberHistoryController) fxmlLoader.getController());

			fxmlLoader.setRoot(null);
			fxmlLoader.setController(null);
			APReaderCardFXML = (AnchorPane) fxmlLoader
					.load(getClass().getResource(IFXMLpathAndStyle.ReaderCardFXML).openStream());
			controllerMap.put(Menuicons.SubscriberCard, (SubscriberCardController) fxmlLoader.getController());

		} catch (

		IOException e) {
			IAlert.ExceptionAlert(e);
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
//	private void menuDecorator(Button btn, Pane pane) {
//
//		btn.setOnMouseEntered(value -> {
//			// btn.setStyle(IFXMLpathAndStyle.ClickedBackgroundStyle);
//			pane.setStyle(IFXMLpathAndStyle.BlueBackgroundStyle);
//		});
//		btn.setOnMouseExited(value -> {
//			// btn.setStyle(IFXMLpathAndStyle.BackgroundStyle);
//			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
//		});
//	}
//
//	private void catalogDecorator(Button btn, Pane pane) {
//		btn.setOnMouseEntered(value -> {
//			pane.setStyle(IFXMLpathAndStyle.BlueBackgroundStyle);
//		});
//		btn.setOnMouseExited(value -> {
//			pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
//		});
//	}

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

	private void selectMenu(Button btn) {
		for (Menuicons icon : Menuicons.values()) {
			if (sideMenuButtons.get(icon) != null)
				if (sideMenuButtons.get(icon).equals(btn))
					btn.setStyle(IFXMLpathAndStyle.ClickedBackgroundStyle);
				else {
					sideMenuButtons.get(icon).setStyle(IFXMLpathAndStyle.BackgroundStyle);
					sideMenuButtons.get(icon).setStyle("");
					sideMenuButtons.get(icon).getStylesheets()
							.add(getClass().getResource("/CSS/general.css").toExternalForm());
				}
		}
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
				if (sideMenuButtons.get(IconName) != null) {
					selectMenu(sideMenuButtons.get(IconName));
				}
				if (controllerMap.get(IconName) != null) {
					controllerMap.get(IconName).setConnection();
					for (Menuicons icon : Menuicons.values()) {
						if (IconName != icon && controllerMap.get(icon) != null)
							controllerMap.get(icon).closeConnection();
					}
				}
				if (!refuseConnection) {
					anchorPane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(anchorPane);
				} else {
					selectMenu(sideMenuButtons.get(Menuicons.Connection));
					APConnectionSettingsFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(APConnectionSettingsFXML);
				}

			} catch (Exception e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
		});
	}

	private void SubMenuBtnFalseHandler(Button btn) {
		btn.setOnMouseClicked(search -> {
			try {
				SubMenu = true;
				SideMenu sideMenu = new SideMenu(GeneralData.MenuType.SubMenu);
				Main.root.setLeft(sideMenu.getVBox());

				FXMLLoader fxmlLoader = new FXMLLoader();
				AnchorPane pane = (AnchorPane) fxmlLoader
						.load(getClass().getResource(IFXMLpathAndStyle.WelcomeScreen).openStream());
				((UserWelcomeController) fxmlLoader.getController()).setCatalog();
				;
				pane.setStyle(IFXMLpathAndStyle.BackgroundStyle);
				Main.root.setRight(pane);
			} catch (Exception e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
		});

	}

	private void SubMenuBtnTrueHandler(Button btn) {
		btn.setOnMouseClicked(search -> {
			try {
				SubMenu = false;
				if (SystemObjects.GeneralData.userLibrarian.getLevel() == 1) {
					SideMenu sideMenu = new SideMenu(GeneralData.MenuType.LibrarianMenu);
					Main.root.setLeft(sideMenu.getVBox());
					SideMenu.APCardLibrarianFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(SideMenu.APCardLibrarianFXML);
				} else {
					SideMenu sideMenu = new SideMenu(GeneralData.MenuType.LibrarianManagerMenu);
					Main.root.setLeft(sideMenu.getVBox());
					SideMenu.APCardLibrarianManagerFXML.setStyle(IFXMLpathAndStyle.BackgroundStyle);
					Main.root.setRight(SideMenu.APCardLibrarianManagerFXML);
				}
			} catch (Exception e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
		});

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
			RightSideBtnHandler(btn, APCardLibrarianFXML, IconName);
			break;
		case ManagerCard:
			btn.setText("Manager Details");
			RightSideBtnHandler(btn, APCardLibrarianManagerFXML, IconName);
			break;
		case Report:
			btn.setText("Report Fault");
			RightSideBtnHandler(btn, APReportFaultFXML, IconName);
			break;
		case catalog:
			if (SubMenu) {
				btn.setText("Back to Librarian Menu");
				SubMenuBtnTrueHandler(btn);
			} else {
				btn.setText("Manage Catalog Menu");
				SubMenuBtnFalseHandler(btn);
			}

			break;
		case AddBook:
			btn.setText("Add Book");
			RightSideBtnHandler(btn, APAddBookFXML, IconName);
			break;
		case AddBookCopy:
			btn.setText("Add Book Copy");
			RightSideBtnHandler(btn, APAddBookCopyFXML, IconName);
			break;
		case UpdateBook:
			btn.setText("Update Book");
			RightSideBtnHandler(btn, APUpdateBookFXML, IconName);
			break;
		case DeleteBook:
			btn.setText("Delete Book");
			RightSideBtnHandler(btn, APDeleteBookFXML, IconName);
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
			RightSideBtnHandler(btn, APCreateReportFXML, IconName);
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
