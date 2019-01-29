package OBLFX;

import java.util.ArrayList;
import java.util.Date;

import Client.LibraryManagerHandler;
import Client.SideMenu;
import Interfaces.IGUIcontroller;
import Interfaces.IGeneralData;
import Interfaces.IGeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.LoansTable;
import SystemObjects.Order;
import SystemObjects.OrdersTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class SubscriberCardController implements IGUIcontroller {

	static ObservableList<LoansTable> ObservableLoansList;
	static ObservableList<OrdersTable> ObservableOrdersList;
	private LibraryManagerHandler commonClient;


	@FXML
	private TextField FullNameField;

	@FXML
	private TextField PhoneNumberField;

	@FXML
	private TextField SubscriberNumberField;

	@FXML
	private TextField IDField;

	@FXML
	private TextField StatusField;

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

	public void setSubscriberCard(String FullName, String PhoneNumber, String ID, String Email, String Status, String SubNumber,
			ArrayList<Loan> Loans, ArrayList<Order> Orders) {
		TextField FullNameField = (TextField) SideMenu.APReaderCardFXML.lookup("#FullNameField");
		TextField PhoneNumberField = (TextField) SideMenu.APReaderCardFXML.lookup("#PhoneNumberField");
		TextField IDField = (TextField) SideMenu.APReaderCardFXML.lookup("#IDField");
		TextField EmailField = (TextField) SideMenu.APReaderCardFXML.lookup("#EmailField");
		TextField StatusField = (TextField) SideMenu.APReaderCardFXML.lookup("#StatusField");
		TextField SubscriberNumberField = (TextField) SideMenu.APReaderCardFXML.lookup("#SubscriberNumberField");
		FullNameField.setText(FullName);
		PhoneNumberField.setText(PhoneNumber);
		IDField.setText(ID);
		EmailField.setText(Email);
		StatusField.setText(Status);
		SubscriberNumberField.setText(SubNumber);
		for (Loan iloan : Loans) {
			LoansTable loan = new LoansTable("missing", "missing", iloan.getStartDate(), iloan.getReturnDate());
			ObservableLoansList.add(loan);
		}
		for (Order iorder : Orders) {
			OrdersTable Order = new OrdersTable("missing", "missing", iorder.getOrderDate(), iorder.getBookArrivedTime());
			ObservableOrdersList.add(Order);
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
	}

//	@FXML
//	public void changeStatus(ActionEvent event) {
//		
//		commonClient.changeSubscriberStatus();
	//}
	
	
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
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
