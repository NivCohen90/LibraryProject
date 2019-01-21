package OBLFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import Client.CommonHandler;
import Users.Loan;
import Users.LoansTable;
import Users.Order;
import Users.OrdersTable;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class SubscriberCardController {//implements Initializable {

	static ObservableList<LoansTable> ObservableLoansList;
	static ObservableList<OrdersTable> ObservableOrdersList;

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

	public void setSubscriberCard(String FullName, String SubID, String Email, String Status, String SubNumber,
			ArrayList<Loan> Loans, ArrayList<Order> Orders) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				FullNameField.setText(FullName);
				IDField.setText(SubID);
				EmailField.setText(Email);
				StatusField.setText(Status);
				SubscriberNumberField.setText(SubNumber);
				for (Loan iloan : Loans) {
					LoansTable loan = new LoansTable("missing", "missing", iloan.getStartDate(), iloan.getReturnDate());
					ObservableLoansList.add(loan);
				}
			}
		});
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
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//
//
//	}

}
