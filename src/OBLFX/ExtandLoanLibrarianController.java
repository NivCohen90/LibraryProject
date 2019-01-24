package OBLFX;

import java.sql.Date;
import java.util.ArrayList;

import Client.LibrarianHandler;
import Client.SideMenu;
import Users.Book;
import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;
import Users.Loan;
import Users.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class ExtandLoanLibrarianController implements IGUIcontroller {

	private LibrarianHandler librarianClient;
	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();

	
    @FXML
    private TableView<?> curentLoansTable;

    @FXML
    private TableColumn<?, ?> bookCatalogNumberCol;

    @FXML
    private TableColumn<?, ?> startLoanDateCol;

    @FXML
    private TableColumn<?, ?> returnedDateCol;

    @FXML
    private TextField wantedBookCtalog;

    @FXML
    private DatePicker newDatePicker;

    @FXML
    private Button applayButton;

    private TableView<Loan> tblLoans = new TableView<>();
	private <T> void displayLoans(ArrayList<T> list) {
		bookCatalogNumberCol.setCellValueFactory(new PropertyValueFactory<>("BookCatalogNumber"));
		startLoanDateCol.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		returnedDateCol.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
		ObservableColumnData.clear();
		if(!list.isEmpty())
		{			
			for (T Ti : list)
				ObservableColumnData.add(Ti);
		}
		else
		{			
			tblLoans.setPlaceholder(new Label(""));
		}
		tblLoans.setItems(ObservableColumnData);
		tblLoans.setVisible(true);
	}//
	
	public void setSubscriberCard(ArrayList<Loan> Loans)
	{
		bookCatalogNumberCol.setCellValueFactory(new PropertyValueFactory<>("BookCatalogNumber"));
		startLoanDateCol.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		returnedDateCol.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
		ObservableColumnData.clear();
		if(!Loans.isEmpty())
		{			
			for (Loan loansi : Loans)
				ObservableColumnData.add(loansi);
		}
		else
		{			
			tblLoans.setPlaceholder(new Label(""));
		}
		tblLoans.setItems(ObservableColumnData);
		tblLoans.setVisible(true);
	}
	
	@FXML
	void extendLoan(ActionEvent event){
		String bookCtalogNumber = wantedBookCtalog.getText();
		Date newReturnDate = java.sql.Date.valueOf(newDatePicker.getValue());
		librarianClient.extendLoanByLibrarian(bookCtalogNumber, newReturnDate);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		displayLoans((ArrayList<T>) msg);
	}

	
	@Override
	public void setConnection() {
		// TODO Auto-generated method stub
		librarianClient = new LibrarianHandler(this);	

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		librarianClient.quit();

	}
	

}
