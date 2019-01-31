package OBLFX;

import java.util.ArrayList;
import java.util.Date;

import Client.CommonHandler;
import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;

import SystemObjects.Loan;
import SystemObjects.LoansTable;
import SystemObjects.Order;
import SystemObjects.OrdersTable;
import Users.Subscriber;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class SubscriberCardController implements IGUIcontroller {

	static ObservableList<LoansTable> ObservableLoansList;
	static ObservableList<OrdersTable> ObservableOrdersList;
	private CommonHandler commonClient;
	private Subscriber Sub = new Subscriber();
	private static ObservableList<String> List;
    
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
	private TableView<LoansTable> ActiveLoansTable;

	@FXML
	private TableColumn<LoansTable, String> ALoansBookName;

	@FXML
	private TableColumn<LoansTable, String> ALoansAuthor;

	@FXML
	private TableColumn<LoansTable, Date> ALoansStartLoanDate;

	@FXML
	private TableColumn<LoansTable, Date> ALoansEndLoanDate;

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
    private Text TextMSG;
    
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
    void CancelAllChanges(ActionEvent event) {
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
	

		if (GeneralData.userLibrarian != null) {

			StatusCombo.setVisible(false);
			StatusField.setVisible(true);
			IDField.setEditable(false);
			StatusCombo.setEditable(false);
			IDField.setDisable(true);
			IDField.setStyle("-fx-opacity: 1.0;");

			
		} 
		else if (GeneralData.userSubscriber != null) {
			String phoneNumber = GeneralData.userSubscriber.getPhoneNumber().substring(3, 10); 
			String areaCode= GeneralData.userSubscriber.getPhoneNumber().substring(0, 3);
			((TextField) SideMenu.APReaderCardFXML.lookup("#FirstNameField")).setText(GeneralData.userSubscriber.getFirstName());
			((TextField) SideMenu.APReaderCardFXML.lookup("#LastNameField")).setText(GeneralData.userSubscriber.getLastName());
			((TextField) SideMenu.APReaderCardFXML.lookup("#AreaCodeTXT")).setText(areaCode);
			((TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField")).setText(phoneNumber);
			((TextField) SideMenu.APReaderCardFXML.lookup("#EmailField")).setText(GeneralData.userSubscriber.getEmail());
			StatusField.setVisible(true);
			StatusCombo.setVisible(false);
			IDField.setEditable(false);
			StatusField.setEditable(false);

		}
    }


	public void setSubscriberCard(Subscriber sub) {
		String phoneNumber = sub.getPhoneNumber().substring(3, 10); 
		String areaCode= sub.getPhoneNumber().substring(0, 3);
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
			PhoneNumberField.setText(sub.getPhoneNumber());
			IDField.setText(sub.getID());
			EmailField.setText(sub.getEmail());
			StatusField.setText(sub.getStatus());
			SubscriberNumberField.setText(sub.getSubscriberNumber());
		}

		ObservableOrdersList.clear();
		for (Loan iloan : sub.getActiveLoans()) {
			LoansTable loan = new LoansTable("missing", "missing", iloan.getStartDate(), iloan.getReturnDate());
			ObservableLoansList.add(loan);
		}
		ObservableOrdersList.clear();
		for (Order iorder : sub.getActiveOrders()) {
			OrdersTable Order = new OrdersTable("missing", "missing", iorder.getOrderDate(),
					iorder.getBookArrivedTime());
			ObservableOrdersList.add(Order);
		}
	}

	@FXML
	public void updateSubscriberDetails(ActionEvent event) {
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
			IDField.setStyle("-fx-opacity: 1.0;");

			
		} 
		else if (GeneralData.userSubscriber != null) {
			
			StatusField.setVisible(true);
			StatusCombo.setVisible(false);
			IDField.setEditable(false);
			StatusField.setEditable(false);

		}
		
		AreaCodeCombo.setValue(AreaCodeTXT.getText());
	}
	
	@FXML
	public void saveSubscriberUpdate(ActionEvent event)
	{
		int counter=0;
		if(IGUIcontroller.CheckIfUserPutInput(FirstNameField,FirstNameLabel)) {
			counter++;
		}
		if(IGUIcontroller.CheckIfUserPutInput(LastNameField,LastNameLabel)) {
			counter++;
		}
		if(IGUIcontroller.CheckOnlyNumbers(PhoneNumberField,PhoneNumber,7,PhoneNumberErrorDigits)) {
			counter++;
		}
		if(IGUIcontroller.CheckIfUserPutInput(EmailField, EmailLabel)){
			if(EmailField.getText().contains("@") && EmailField.getText().contains(".")){
				counter++;
			}
			
		}
		if(counter==4) {
		if (GeneralData.userLibrarian != null) {
			Sub.setFirstName(FirstNameField.getText());
			Sub.setLastName(LastNameField.getText());
			Sub.setPhoneNumber("" + AreaCodeCombo.getPromptText() + PhoneNumberField.getText());
			Sub.setID(IDField.getText());
			Sub.setEmail(EmailField.getText());
			Sub.setStatus(StatusField.getPromptText());
		}
		
		else if (GeneralData.userSubscriber != null) {
			Sub.setFirstName(FirstNameField.getText());
			Sub.setLastName(LastNameField.getText());
			Sub.setPhoneNumber("" + AreaCodeCombo.getPromptText() + PhoneNumberField.getText());
			Sub.setEmail(EmailField.getText());
		}

		commonClient.changeSubscriberDetails(Sub);
		}
	}

	@FXML
	public void initialize() {
		ObservableLoansList = FXCollections.observableArrayList();
		ObservableOrdersList = FXCollections.observableArrayList();
		ActiveLoansTable.setItems(ObservableLoansList);
		ActiveLoansTable.setFixedCellSize(Region.USE_COMPUTED_SIZE);
		ALoansBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		ALoansAuthor.setCellValueFactory(new PropertyValueFactory<>("Authors"));
		ALoansStartLoanDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		ALoansEndLoanDate.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
		ActiveOrdersTable.setItems(ObservableOrdersList);
		ActiveOrdersTable.setFixedCellSize(Region.USE_COMPUTED_SIZE);
		AOrdersBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		AOrdersAuthor.setCellValueFactory(new PropertyValueFactory<>("Authors"));
		AOrdersOderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		AOrdersArrivedDate.setCellValueFactory(new PropertyValueFactory<>("ArrivedDate"));

		List = FXCollections.observableArrayList();
		List.add("Active");
		List.add("Freezed");
		List.add("Locked");
		StatusCombo.setItems(List);
		List.clear();
		
		List.add("050");
		List.add("052");
		List.add("054");
		List.add("055");
		List.add("058");
		AreaCodeCombo.setItems(List);
		if(GeneralData.userSubscriber != null && GeneralData.userSubscriber.getStatus()!= "Active") {
			UpdateDetailsbutton.setDisable(true);
			IAlert.setandShowAlert(AlertType.ERROR, "Wrong Status","Please contect the libararian","Click ok to close message");
		}

	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		TextMSG.setVisible(true);

		switch(op)
		{
		case returnSuccessMsg:{
			
			if (GeneralData.userLibrarian != null) {
				TextMSG.setText("Subscriber successfully updated");
				Sub.setFirstName(FirstNameField.getText());
				Sub.setLastName(LastNameField.getText());
				Sub.setPhoneNumber("" + AreaCodeCombo.getPromptText() + PhoneNumberField.getText());
				Sub.setID(IDField.getText());
				Sub.setEmail(EmailField.getText());
				Sub.setStatus(StatusField.getPromptText());
			}
			
			else if (GeneralData.userSubscriber != null) {
				GeneralData.userSubscriber.setFirstName(FirstNameField.getText());
				GeneralData.userSubscriber.setLastName(LastNameField.getText());
				GeneralData.userSubscriber.setPhoneNumber("" + AreaCodeCombo.getPromptText() + PhoneNumberField.getText());
				GeneralData.userSubscriber.setEmail(EmailField.getText());
				TextMSG.setText("Successfully updated");
				Sub.setFirstName(FirstNameField.getText());
				Sub.setLastName(LastNameField.getText());
				Sub.setPhoneNumber("" + AreaCodeCombo.getPromptText() + PhoneNumberField.getText());
				Sub.setEmail(EmailField.getText());
			}
			break;}
			
		case returnError:{
			TextMSG.setText("User is locked");
			
			break;
			}
		
		default:

			break;
			
		}
		
	}

	@Override
	public void setConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

}
