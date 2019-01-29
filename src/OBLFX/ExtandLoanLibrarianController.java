package OBLFX;

import java.sql.Date;
import java.util.ArrayList;

import Client.LibrarianHandler;
import Interfaces.IGUIcontroller;
import SystemObjects.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExtandLoanLibrarianController implements IGUIcontroller {

	private LibrarianHandler librarianClient;
	
	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
	//boolean NewReturnFlag = false;
	
	@FXML
	public void initialize() {
	//	tblLoans.setItems(ObservableLoansList);
	}
    @FXML
    private TableView<?> curentLoansTable;

    @FXML
    private TableColumn<Loan,String> bookCatalogNumberCol;

    @FXML
    private TableColumn<Loan,Date> startLoanDateCol;

    @FXML
    private TableColumn<Loan,Date> returnedDateCol;

    @FXML
    private TextField wantedBookCtalog;

    @FXML
    private DatePicker newDatePicker;

    @FXML
    private Button applayButton;
    
    @FXML
    private Label BookCtatalogLabel;

    @FXML
    private Label NewDateLabel;
    
    @FXML
    private Label RetriveMSG;

	private <T> void displayLoans(ArrayList<T> list) {
		ObservableColumnData.clear();
		if(!list.isEmpty())
		{
			for (T Ti : list)
				ObservableColumnData.add((Loan) Ti);
		}
		else
		{
			curentLoansTable.setPlaceholder(new Label(""));
		}
		curentLoansTable.setVisible(true);
		curentLoansTable.setVisible(true);
	}	
	@FXML
	void extendLoan(ActionEvent event){
	if(	IGUIcontroller.CheckOnlyLetter(wantedBookCtalog, BookCtatalogLabel, OnlyNumbers, UserNameErrorNumebrs)&&
		IGUIcontroller.CheckIfUserPutInput(wantedBookCtalog, BookCtatalogLabel))
	   {
		String bookCtalogNumber = wantedBookCtalog.getText();
		Date newReturnDate = java.sql.Date.valueOf(newDatePicker.getValue());
		librarianClient.extendLoanByLibrarian(bookCtalogNumber, newReturnDate);
	   }
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

	@Override
	public <T> void receiveMassageFromServer(T msg, SystemObjects.GeneralData.operationsReturn op) {
		// TODO Auto-generated method stub
		
	}
	

}
