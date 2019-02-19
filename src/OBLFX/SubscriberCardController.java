package OBLFX;

import java.util.ArrayList;
import java.util.Date;

import Client.CommonHandler;
import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;

import SystemObjects.Loan;
import SystemObjects.LoansTable;
import SystemObjects.Order;
import SystemObjects.OrdersTable;
import Users.Subscriber;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SubscriberCardController implements IGUIcontroller {

	static ObservableList<Loan> ObservableLoansList;
	static ObservableList<OrdersTable> ObservableOrdersList;
	private CommonHandler commonClient;
	// private static
	private static ObservableList<String> List;
	private ObservableList<String> ListStatus;

	@FXML
	private Text TitleLabel;

	@FXML
	private Button UpdateDetailsbutton;

	@FXML
	private TextField PhoneNumberField;

	@FXML
	private TextField SubscriberNumberField;

	@FXML
	private TextField IDField;

	@FXML
	private TextField EmailField;

	@FXML
	private TableView<Loan> ActiveLoansTable;

	@FXML
	private TableColumn<Loan, String> ALoansBookName;

	@FXML
	private TableColumn<Loan, String> ALoansAuthor;

	@FXML
	private TableColumn<Loan, Date> ALoansStartLoanDate;

	@FXML
	private TableColumn<Loan, Date> ALoansEndLoanDate;

	@FXML
	private TableColumn<Loan, Date> ALoansStatus;

	@FXML
	private TableView<OrdersTable> ActiveOrdersTable;

	@FXML
	private TableColumn<OrdersTable, String> AOrdersBookName;

	@FXML
	private TableColumn<OrdersTable, String> AOrdersAuthor;

	@FXML
	private TableColumn<OrdersTable, Date> AOrdersOderDate;

	@FXML
	private TableColumn<OrdersTable, Date> AOrdersArrivedDate;

	@FXML
	private TextField StatusField;

	@FXML
	private ComboBox<String> StatusCombo;

	@FXML
	private TextField FirstNameField;

	@FXML
	private Button UpdateChangesButton;

	@FXML
	private TextField LastNameField;

	@FXML
	private Text ActiveLoansTXT;

	@FXML
	private Text ActiveOrdersTXT;

	@FXML
	private Button SaveButton;

	@FXML
	private ComboBox<String> AreaCodeCombo;

	@FXML
	private TextField AreaCodeTXT;

	@FXML
	private Label TextMSG;

	@FXML
	private Button CancelBTN;

	@FXML
	private Label FirstNameLabel;

	@FXML
	private Label LastNameLabel;

	@FXML
	private Label EmailLabel;

	@FXML
	private Label PhoneNumber;

	@FXML
	void CheckEmail(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(EmailField, EmailLabel);
	}

	@FXML
	void CheckFirstName(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(FirstNameField, FirstNameLabel);
	}

	@FXML
	void CheckLastName(KeyEvent event) {
		IGUIcontroller.CheckIfUserPutInput(LastNameField, LastNameLabel);
	}

	@FXML
	void CheckPhone(KeyEvent event) {
		IGUIcontroller.CheckOnlyNumbers(PhoneNumberField, PhoneNumber, 7, PhoneNumberErrorDigits);
	}

	@FXML
	void CancelAllChanges(ActionEvent event) {
		CancelOperation();
	}

	public void CancelOperation() {
		((Text) SideMenu.APReaderCardFXML.lookup("#TitleLabel")).setText("Reader Card");
		FirstNameLabel.setText("");
		LastNameLabel.setText("");
		PhoneNumber.setText("");
		EmailLabel.setText("");

		ActiveLoansTable.setVisible(true);
		ActiveLoansTXT.setVisible(true);
		ActiveOrdersTXT.setVisible(true);
		ActiveOrdersTable.setVisible(true);
		UpdateDetailsbutton.setVisible(true);
		SaveButton.setVisible(false);
		AreaCodeCombo.setVisible(false);
		AreaCodeTXT.setVisible(true);
		CancelBTN.setVisible(false);

		FirstNameField.setEditable(false);
		LastNameField.setEditable(false);
		PhoneNumberField.setEditable(false);
		AreaCodeCombo.setEditable(false);
		EmailField.setEditable(false);

		FirstNameField.setDisable(true);
		LastNameField.setDisable(true);
		PhoneNumberField.setDisable(true);
		EmailField.setDisable(true);
		AreaCodeTXT.setDisable(true);
		StatusField.setDisable(true);

		FirstNameField.setStyle("-fx-opacity:0.5;");
		LastNameField.setStyle("-fx-opacity:0.5;");
		PhoneNumberField.setStyle("-fx-opacity:0.5;");
		EmailField.setStyle("-fx-opacity:0.5;");
		AreaCodeTXT.setStyle("-fx-opacity:0.5;");
		StatusField.setStyle("-fx-opacity:0.5;");
		IDField.setStyle("-fx-opacity:0.5;");

		if (GeneralData.userLibrarian != null) {

			String phoneNumber = GeneralData.userSubscriber.getPhoneNumber().substring(3, 10);
			String areaCode = GeneralData.userSubscriber.getPhoneNumber().substring(0, 3);
			((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField"))
					.setText(GeneralData.userSubscriber.getFirstName());
			((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField"))
					.setText(GeneralData.userSubscriber.getLastName());

			((TextField) SideMenu.APReaderCardFXML.lookup("#AreaCodeTXT")).setText(areaCode);
			((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).setText(phoneNumber);
			((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField"))
					.setText(GeneralData.userSubscriber.getEmail());

			StatusCombo.setVisible(false);
			StatusField.setVisible(true);
			IDField.setEditable(false);
			StatusCombo.setEditable(false);
			IDField.setDisable(true);

		} else if (GeneralData.userSubscriber != null) {
			String phoneNumber = GeneralData.userSubscriber.getPhoneNumber().substring(3, 10);
			String areaCode = GeneralData.userSubscriber.getPhoneNumber().substring(0, 3);
			((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField"))
					.setText(GeneralData.userSubscriber.getFirstName());
			((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField"))
					.setText(GeneralData.userSubscriber.getLastName());

			((TextField) SideMenu.APReaderCardFXML.lookup("#AreaCodeTXT")).setText(areaCode);
			((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).setText(phoneNumber);
			((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField"))
					.setText(GeneralData.userSubscriber.getEmail());
			StatusField.setVisible(true);
			StatusCombo.setVisible(false);
			IDField.setEditable(false);
			StatusField.setEditable(false);

		}
	}

	public void setSubscriberCard(Subscriber sub) {
		String phoneNumber = sub.getPhoneNumber().substring(3, sub.getPhoneNumber().length());
		String areaCode = sub.getPhoneNumber().substring(0, 3);
		((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField")).setText(sub.getFirstName());
		((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField")).setText(sub.getLastName());
		((TextField) SideMenu.APReaderCardFXML.lookup("#AreaCodeTXT")).setText(areaCode);
		((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).setText(phoneNumber);
		((TextField) SideMenu.APReaderCardFXML.lookup("#IDField")).setText(sub.getID());
		((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField")).setText(sub.getEmail());
		((TextField) SideMenu.APReaderCardFXML.lookup("#StatusField")).setText(sub.getStatus());
		((TextField) SideMenu.APReaderCardFXML.lookup("#SubscriberNumberField")).setText(sub.getSubscriberNumber());

		// set details when opening FXML on search
		if (FirstNameField != null) {
			FirstNameField.setText(sub.getFirstName());
			LastNameField.setText(sub.getLastName());
			AreaCodeTXT.setText(areaCode);
			PhoneNumberField.setText(phoneNumber);
			IDField.setText(sub.getID());
			EmailField.setText(sub.getEmail());
			StatusField.setText(sub.getStatus());
			SubscriberNumberField.setText(sub.getSubscriberNumber());
		}

		ObservableLoansList.clear();
		for (Loan iloan : sub.getActiveLoans()) {
			LoansTable loan = new LoansTable(iloan.getBookName(), iloan.getBookAuthors(),
			iloan.getStartDate(), iloan.getReturnDate());
			ObservableLoansList.add(iloan);
		}
		ObservableOrdersList.clear();
		for (Order iorder : sub.getActiveOrders()) {
			OrdersTable Order = new OrdersTable(iorder.getBookName(), iorder.getBookAuthors(), iorder.getOrderDate(),
					iorder.getBookArrivedTime());
			ObservableOrdersList.add(Order);
		}
		if (GeneralData.userSubscriber != null && !GeneralData.userSubscriber.getStatus().equals("Active")) {
			((Button) SideMenu.APReaderCardFXML.lookup("#UpdateDetailsbutton")).setDisable(true);
			String msg = String.format("Your subscriber status is Frozen.\nBecause you have %d books in late return.\n\nSome options will be disabled.\nFor more information, Please contect the libararian.",GeneralData.userSubscriber.getFellonyNumber());
			IAlert.setandShowAlert(AlertType.ERROR, GeneralData.userSubscriber.getStatus() + " Status",
					msg, "Click ok to close message");
		} else {
			((Button) SideMenu.APReaderCardFXML.lookup("#UpdateDetailsbutton")).setDisable(false);
		}
	}

	@FXML
	public void updateSubscriberDetails(ActionEvent event) {
		TextMSG.setText("");
		FirstNameLabel.setText("");
		LastNameLabel.setText("");
		PhoneNumber.setText("");
		EmailLabel.setText("");

		CancelBTN.setVisible(true);
		((Text) SideMenu.APReaderCardFXML.lookup("#TitleLabel")).setText("Update Details");
		ActiveLoansTable.setVisible(false);
		ActiveLoansTXT.setVisible(false);
		ActiveOrdersTXT.setVisible(false);
		ActiveOrdersTable.setVisible(false);
		UpdateDetailsbutton.setVisible(false);
		SaveButton.setVisible(true);
		AreaCodeCombo.setVisible(true);
		AreaCodeTXT.setVisible(false);

		FirstNameField.setDisable(false);
		LastNameField.setDisable(false);
		PhoneNumberField.setDisable(false);
		EmailField.setDisable(false);

		FirstNameField.setStyle("-fx-opacity:1.0;");
		LastNameField.setStyle("-fx-opacity:1.0;");
		PhoneNumberField.setStyle("-fx-opacity:1.0;");
		EmailField.setStyle("-fx-opacity:1.0;");

		FirstNameField.setEditable(true);
		LastNameField.setEditable(true);
		PhoneNumberField.setEditable(true);
		AreaCodeCombo.setEditable(true);
		EmailField.setEditable(true);

		if (GeneralData.userLibrarian != null) {

			StatusCombo.setVisible(true);
			StatusField.setVisible(false);
			IDField.setEditable(true);
			StatusCombo.setEditable(true);
			IDField.setDisable(false);
			//IDField.setStyle("-fx-opacity: 1.0;");

		} else if (GeneralData.userSubscriber != null) {

			StatusField.setVisible(true);
			StatusCombo.setVisible(false);
			IDField.setEditable(false);
			StatusField.setEditable(false);

		}

		AreaCodeCombo.setValue(AreaCodeTXT.getText());
	}

	@FXML
	public void saveSubscriberUpdate(ActionEvent event) {
		Subscriber Sub = new Subscriber();
		int counter = 0;
		if (IGUIcontroller.CheckIfUserPutInput(FirstNameField, FirstNameLabel)) {
			counter++;
		}
		if (IGUIcontroller.CheckIfUserPutInput(LastNameField, LastNameLabel)) {
			counter++;
		}
		if (IGUIcontroller.CheckOnlyNumbers(PhoneNumberField, PhoneNumber, 7, PhoneNumberErrorDigits)) {
			counter++;
		}
		if (IGUIcontroller.CheckIfUserPutInput(EmailField, EmailLabel)) {
			if (EmailField.getText().contains("@") && EmailField.getText().contains(".")) {
				counter++;
			}

		}

		if (counter == 4) {
			if (GeneralData.userLibrarian != null) {
				Sub.setID(((TextField) SideMenu.APReaderCardFXML.lookup("#IDField")).getText());
				Sub.setFirstName(((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField")).getText());
				Sub.setLastName(((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField")).getText());
				Sub.setPhoneNumber(((ComboBox) SideMenu.APReaderCardFXML.lookup("#AreaCodeCombo")).getSelectionModel()
						.getSelectedItem().toString()
						+ ((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).getText());
				//Sub.setID(((TextField) SideMenu.APReaderCardFXML.lookup("#IDField")).getText());
				Sub.setEmail(((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField")).getText());
				Sub.setStatus(((ComboBox) SideMenu.APReaderCardFXML.lookup("#StatusCombo")).getSelectionModel()
						.getSelectedItem().toString());
				commonClient.changeSubscriberDetails(Sub);
			}

			else if (GeneralData.userSubscriber != null) {
				Sub.setID(((TextField) SideMenu.APReaderCardFXML.lookup("#IDField")).getText());
				Sub.setFirstName(((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField")).getText());
				Sub.setLastName(((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField")).getText());
				Sub.setPhoneNumber(((ComboBox) SideMenu.APReaderCardFXML.lookup("#AreaCodeCombo")).getSelectionModel()
						.getSelectedItem().toString()
						+ ((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).getText());
				Sub.setEmail(((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField")).getText());
				Sub.setStatus(((TextField) SideMenu.APReaderCardFXML.lookup("#StatusField")).getText());
				System.out.println(Sub.getFirstName());
				System.out.println(Sub.getLastName());
				System.out.println(Sub.getPhoneNumber());
				System.out.println(Sub.getEmail());
				System.out.println(Sub.getStatus());
				commonClient.changeSubscriberDetails(Sub);
			}

		}
	}

	/**
	 * open window with loan details with option to extend loan
	 * 
	 * @param choosenResult chosen loan to display details
	 */
	private void openLoanDetails(Loan choosenResult) {
		Stage primaryStage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader();
		AnchorPane root = null;
		Scene scene = null;
		try {

			root = (AnchorPane) fxmlLoader
					.load(getClass().getResource("../FXML/ExtendLoanSubscriber.fxml").openStream());
			scene = new Scene(root);
			ExtendLoanSubscriberController Controller = (ExtendLoanSubscriberController) fxmlLoader.getController();
			Controller.setLoanDetails(choosenResult);
			root.setStyle(IFXMLpathAndStyle.BackgroundStyle);
			
			primaryStage.setTitle(choosenResult.getBookName());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			IAlert.ExceptionAlert(e);
		}
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		Subscriber Sub = new Subscriber();
		TextMSG.setVisible(true);

		switch (op) {
		case returnSuccessMsg: {
			TextMSG.setTextFill(Color.GREEN);
			if (GeneralData.userLibrarian != null) {
				TextMSG.setText((String) msg);
				Sub.setFirstName(FirstNameField.getText());
				Sub.setLastName(LastNameField.getText());
				Sub.setPhoneNumber("" + AreaCodeCombo.getSelectionModel().getSelectedItem().toString()
						+ PhoneNumberField.getText());
				Sub.setID(IDField.getText());
				Sub.setEmail(EmailField.getText());
				Sub.setStatus(StatusField.getPromptText());
			}

			else if (GeneralData.userSubscriber != null) {
				GeneralData.userSubscriber.setFirstName(FirstNameField.getText());
				GeneralData.userSubscriber.setLastName(LastNameField.getText());
				GeneralData.userSubscriber.setPhoneNumber(
						AreaCodeCombo.getSelectionModel().getSelectedItem().toString() + PhoneNumberField.getText());
				GeneralData.userSubscriber.setEmail(EmailField.getText());
				TextMSG.setText((String) msg);
				CancelOperation();
				Sub.setFirstName(FirstNameField.getText());
				Sub.setLastName(LastNameField.getText());
				Sub.setPhoneNumber(
						AreaCodeCombo.getSelectionModel().getSelectedItem().toString() + PhoneNumberField.getText());
				Sub.setEmail(EmailField.getText());
			}
			break;
		}

		case returnError:
			TextMSG.setTextFill(Color.RED);
			TextMSG.setText((String) msg);
			break;
		case returnException:
			IAlert.ExceptionAlert((Exception) msg);
		default:
			break;

		}

	}

	@Override
	public void setConnection() {
		commonClient = new CommonHandler(this);
	}

	@Override
	public void closeConnection() {
		if (commonClient != null)
			commonClient.quit();
	}

	/**
	 * Initialized subscraiber card.
	 */
	@FXML
	public void initialize() {
		
		ObservableLoansList = FXCollections.observableArrayList();
		ObservableOrdersList = FXCollections.observableArrayList();
		ActiveLoansTable.setItems(ObservableLoansList);
		ActiveLoansTable.setFixedCellSize(Region.USE_COMPUTED_SIZE);
		ALoansBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		ALoansAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthors"));
		ALoansStartLoanDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		ALoansEndLoanDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
		ALoansStatus.setCellValueFactory(new PropertyValueFactory<>("LoanStatus"));
		ActiveOrdersTable.setItems(ObservableOrdersList);
		ActiveOrdersTable.setFixedCellSize(Region.USE_COMPUTED_SIZE);
		AOrdersBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		AOrdersAuthor.setCellValueFactory(new PropertyValueFactory<>("Authors"));
		AOrdersOderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		AOrdersArrivedDate.setCellValueFactory(new PropertyValueFactory<>("ArrivedDate"));

		ListStatus = FXCollections.observableArrayList();
		ListStatus.clear();
		ListStatus.add("Active");
		ListStatus.add("Freezed");
		ListStatus.add("Locked");
		StatusCombo.setItems(ListStatus);

		List = FXCollections.observableArrayList();
		List.clear();
		List.add("050");
		List.add("052");
		List.add("054");
		List.add("055");
		List.add("058");
		AreaCodeCombo.setItems(List);
		if (GeneralData.userSubscriber != null && !GeneralData.userSubscriber.getStatus().equals("Active")) {
			UpdateDetailsbutton.setDisable(true);
		} else {
			UpdateDetailsbutton.setDisable(false);
		}

		ActiveLoansTable.setRowFactory(tv -> {
			TableRow<Loan> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Loan clickedRow = row.getItem();
					openLoanDetails(clickedRow);
				}
			});
			return (TableRow<Loan>) row;
		});

	}

}
