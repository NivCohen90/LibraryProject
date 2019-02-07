package OBLFX;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Client.SubscriberHandler;
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
import javafx.scene.paint.Color;

public class ExtendLoanSubscriberController implements IGUIcontroller {

	private SubscriberHandler subscriberClient;
	private LibrarianHandler librarianClient;
	private Loan displayedLoan;
	
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

    @FXML
    private Label lblMsgExtend;
	
    /**
     * set fields in FXML with loan details
     * @param loanToDisplay which loan to display details of
     */
	public void setLoanDetails(Loan loanToDisplay) {
		displayedLoan = loanToDisplay;
		
		txtBookName.setText(loanToDisplay.getBookName());
		txtAuthor.setText(loanToDisplay.getBookAuthors());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			dateLoanStart.setValue(dateFormat.parse(loanToDisplay.getStartDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			dateReturn.setValue(dateFormat.parse(loanToDisplay.getReturnDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

			LocalDateTime current = dateFormat.parse(loanToDisplay.getReturnDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();//LocalDateTime.now(); // now
			LocalDateTime weekBefore = current.minusDays(7); // week before
			Date checkDate = dateFormat.parse(loanToDisplay.getReturnDate().toString());

			LocalDate from = weekBefore.toLocalDate();
			LocalDate to = checkDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (ChronoUnit.DAYS.between(from, to) <= 7 && ChronoUnit.DAYS.between(from, to) >= 0) {
				//if (GeneralData.userSubscriber.getStatus().equals("Active")) 
					ExtendBtn.setDisable(false);
					ExtendBtn.setOpacity(1);
				
//				else
//					RetriveMSG.setText("Your subscriber user is frozen, can not extend loan. Please contect the libararian.");
			}
		} catch (ParseException e) {
			IAlert.ExceptionAlert(e);
		}


	}

	public void setLibrarianExtend()
	{
	   lblMsgExtend.setText("");
	   dateReturn.setDisable(false);
	   dateReturn.setOpacity(1);
	}
	
	@FXML
	void ExtandLoan(ActionEvent event) {
		setConnection();
		if(GeneralData.userSubscriber != null)
			subscriberClient.extendLoan(displayedLoan.getSubscriberID(), displayedLoan.getLoanID());
		if(GeneralData.userLibrarian != null)
		{
			Date returnExtend = Date.from(dateReturn.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); 
			librarianClient.extendLoanByLibrarian(displayedLoan.getSubscriberID(), displayedLoan.getLoanID(), returnExtend, GeneralData.userLibrarian.getID());
		}
	}
	
	

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		switch(op)
		{
		/*case returnSuccessMsg:
			RetriveMSG.setVisible(true);
			RetriveMSG.setText((String) msg);
			RetriveMSG.setTextFill(Color.GREEN);
			break;*/
		case returnLoanArray:
			GeneralData.userSubscriber.setActiveLoans((ArrayList<Loan>) msg);
			SubscriberCardController subCon = new SubscriberCardController();
			subCon.setSubscriberCard(GeneralData.userSubscriber);
			for(Loan iLoan : (ArrayList<Loan>) msg)
			{
				if(iLoan.getLoanID().equals(displayedLoan.getLoanID()))
					displayedLoan.setReturnDate(iLoan.getReturnDate());
			}
			setLoanDetails(displayedLoan);
			RetriveMSG.setVisible(true);
			RetriveMSG.setText("Extension was Approved");
			RetriveMSG.setTextFill(Color.GREEN);
			break;
		case returnError:
			RetriveMSG.setVisible(true);
			RetriveMSG.setText((String) msg);
			RetriveMSG.setTextFill(Color.RED);
			break;
		case returnException:
			IAlert.ExceptionAlert((Exception) msg);
			break;
		}
		closeConnection();
		
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setConnection() {
		subscriberClient = new SubscriberHandler(this);
		librarianClient = new LibrarianHandler(this);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void closeConnection() {
		if (subscriberClient != null)
			subscriberClient.quit();
		if (librarianClient != null)
			librarianClient.quit();
	}

}
