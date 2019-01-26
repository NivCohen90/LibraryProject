package OBLFX;

import java.util.ArrayList;

import Client.Main;
import Client.SideMenu;
import Interfaces.IGUIcontroller;
import Interfaces.IGeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.Order;
import Users.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML controller for subscriber history
 * @author ofir
 *
 */
public class SubscriberHistoryController implements IGUIcontroller{

	static ObservableList<Object> ObservableColumnDataLoans = FXCollections.observableArrayList();
	static ObservableList<Object> ObservableColumnDataOrder = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		tblLoanHistory.setItems(ObservableColumnDataLoans);
		tblLoanHistory.setPlaceholder(new Label("No loan history"));
		//no book name and author in Loan object
		tblLoanColBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		tblLoanColAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
		tblLoanColStartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		tblLoanColReturnedDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));

		tblOrderHistory.setItems(ObservableColumnDataOrder);
		tblOrderHistory.setPlaceholder(new Label("No order history"));
		//no book name and author in Order object
		tblOrderColBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		tblOrderColAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
		tblOrderColOrderDate.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
		tblOrderColOrderStatus.setCellValueFactory(new PropertyValueFactory<>("BookArrivedTime"));
		if(Main.userSubscriber!=null)
				setSubscriberHistory(Main.userSubscriber);
		
		tblFaultHistory.setPlaceholder(new Label("No fault history"));
		
		
	}
	
    @FXML
    private TableView<Object> tblLoanHistory;

    @FXML
    private TableColumn<?, ?> tblLoanColBookName;

    @FXML
    private TableColumn<?, ?> tblLoanColAuthor;

    @FXML
    private TableColumn<?, ?> tblLoanColStartDate;

    @FXML
    private TableColumn<?, ?> tblLoanColReturnedDate;

    @FXML
    private TableView<Object> tblOrderHistory;

    @FXML
    private TableColumn<?, ?> tblOrderColBookName;

    @FXML
    private TableColumn<?, ?> tblOrderColAuthor;

    @FXML
    private TableColumn<?, ?> tblOrderColOrderDate;

    @FXML
    private TableColumn<?, ?> tblOrderColOrderStatus;

    @FXML
    private TableView<?> tblFaultHistory;

    @FXML
    private TableColumn<?, ?> tblFaultColBookName;

    @FXML
    private TableColumn<?, ?> tblFaultColFaultInfo;
    
    /**
     * set subscriber history data
     * @param subscriber subscriber to get data from
     */
    @SuppressWarnings("unchecked")
	public void setSubscriberHistory(Subscriber subscriber)
    {
    	ArrayList<Loan> listLoan = subscriber.getHistoryLoans();
    	ArrayList<Order> listOrder = subscriber.getHistoryOrders();
    	setDataInTable(listLoan, ObservableColumnDataLoans, (TableView<Object>) SideMenu.APSubscriberHistoryFXML.lookup("#tblLoanHistory"));
    	setDataInTable(listOrder, ObservableColumnDataOrder, (TableView<Object>) SideMenu.APSubscriberHistoryFXML.lookup("#tblOrderHistory"));
    }
    
    /**
     * set data in FXML tables
     * @param list ArrayList<T> with object list to add to table
     * @param observablelist to add to, that is set to the table
     * @param table	which table to add to
     */
	private <T> void setDataInTable(ArrayList<T> list, ObservableList<Object> observablelist, TableView<Object> table) {
		observablelist.clear();
		if(!list.isEmpty())
		{
			for (T Ti : list)
				observablelist.add(Ti);
		}
		table.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setConnection() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}
