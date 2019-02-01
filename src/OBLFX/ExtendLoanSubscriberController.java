package OBLFX;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import Client.CommonHandler;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.LoansTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ExtendLoanSubscriberController implements IGUIcontroller {

	private CommonHandler commanClient;
	private LoansTable displayedLoan;
	
	@FXML
	private Button ExtendBtn;

	@FXML
	private Label RetriveMSG;

	@FXML
	private Button CancelBTN;

	@FXML
	private DatePicker dateLoanStart;

	@FXML
	private DatePicker dateReturn;

	@FXML
	private TextField txtBookName;

	@FXML
	private TextField txtAuthor;

    /**
     * set fields in FXML with loan details
     * @param loanToDisplay which loan to display details of
     */
	public void setLoanDetails(LoansTable loanToDisplay) {
		displayedLoan = loanToDisplay;
		
		txtBookName.setText(loanToDisplay.getBookName());
		txtAuthor.setText(loanToDisplay.getAuthors());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			dateLoanStart.setValue(dateFormat.parse(loanToDisplay.getStartDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			dateReturn.setValue(dateFormat.parse(loanToDisplay.getEndDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

			LocalDateTime current = LocalDateTime.now(); // now
			LocalDateTime weekBefore = current.minusDays(7); // week before
			Date checkDate = dateFormat.parse(loanToDisplay.getEndDate().toString());

			LocalDate from = weekBefore.toLocalDate();
			LocalDate to = checkDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (ChronoUnit.DAYS.between(from, to) <= 7 && ChronoUnit.DAYS.between(from, to) >= 0) {
				if (GeneralData.userSubscriber.getStatus().equals("Active")) {
					ExtendBtn.setDisable(false);
					dateReturn.setDisable(false);
					ExtendBtn.setOpacity(1);
					dateReturn.setOpacity(1);
				}
				else
					RetriveMSG.setText("Your subscriber user is frozen, can not extend loan. Please contect the libararian.");
			}
		} catch (ParseException e) {
			IAlert.ExceptionAlert(e);
		}


	}

	@FXML
	void CancelAction(ActionEvent event) {

	}

	
	@FXML
	void ExtandLoan(ActionEvent event) {
		//Loan loanData = new Loan(loanID, subscriberID, bookCatalogNumber, copyID, startDate, returnDate, loanStatus)
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setConnection() {
		//commanClient = new CommonHandler(this);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void closeConnection() {
		//if (commanClient != null)
			//commanClient.quit();
	}

}
