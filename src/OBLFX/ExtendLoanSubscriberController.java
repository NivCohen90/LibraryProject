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
import java.util.concurrent.TimeUnit;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Client.SubscriberHandler;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.Loan;
import SystemObjects.LoansTable;
import Users.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private Label lblMsgExtend1;
	
    /**
     * set fields in FXML with loan details
     * @param loanToDisplay which loan to display details of
     */
	public void setLoanDetails(Loan loanToDisplay) {
		
		displayedLoan = loanToDisplay;
		
		txtBookName.setText(loanToDisplay.getBookName());
		txtAuthor.setText(loanToDisplay.getBookAuthors());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//
		try {
			dateLoanStart.setValue(dateFormat.parse(loanToDisplay.getStartDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			dateReturn.setValue(dateFormat.parse(loanToDisplay.getReturnDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

			LocalDateTime current = dateFormat.parse(loanToDisplay.getReturnDate().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();//LocalDateTime.now(); // now
			LocalDateTime weekBefore = current.minusDays(7); // week before
			Date checkDate = new Date();//dateFormat.parse(loanToDisplay.getReturnDate().toString());

			LocalDate from = weekBefore.toLocalDate();
			LocalDate to = checkDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			/*
			System.out.println(ChronoUnit.DAYS.between(from, to));
			System.out.println(from);
			System.out.println(to);
			System.out.println(current);
			System.out.println(weekBefore);
			System.out.println(checkDate);
			System.out.println(loanToDisplay.getReturnDate().toString());
			*/
			if (ChronoUnit.DAYS.between(from, to) <= 7 && ChronoUnit.DAYS.between(from, to) >= 0) {
				//if (GeneralData.userSubscriber.getStatus().equals("Active")) 
				if (GeneralData.userSubscriber!=null) 
				{
					if(GeneralData.userSubscriber.getStatus().equals("Active"))
					{
						ExtendBtn.setDisable(false);
						ExtendBtn.setOpacity(1);
					}
					else
					{
						RetriveMSG.setVisible(true);
						RetriveMSG.setText("Subscriber status is 'Freeze', cannot use loan extension.");
						RetriveMSG.setTextFill(Color.RED);
					}
				}
				else if (GeneralData.userLibrarian!=null) 
				{
					if(GeneralData.searchedSubscriber != null && GeneralData.searchedSubscriber.getStatus().equals("Active"))
					{
						ExtendBtn.setDisable(false);
						ExtendBtn.setOpacity(1);
						dateReturn.setDisable(false);
						dateReturn.setOpacity(1);
						lblMsgExtend1.setVisible(false);
					}
					else
					{
						RetriveMSG.setVisible(true);
						RetriveMSG.setText("Subscriber status is 'Freeze', cannot use loan extension.");
						RetriveMSG.setTextFill(Color.RED);
					}
				}
				
//				else
//					RetriveMSG.setText("Your subscriber user is frozen, can not extend loan. Please contect the libararian.");
			}
		} catch (ParseException e) {
			IAlert.ExceptionAlert(e);
		}


	}
	
	/*
	public void setLibrarianExtend()
	{
	   lblMsgExtend.setText("");
	   dateReturn.setDisable(false);
	   dateReturn.setOpacity(1);
	   lblMsgExtend1.setVisible(false);
	}*/
	
	@FXML
	void ExtandLoan(ActionEvent event) {
		boolean flag = false;
		setConnection();
		if(GeneralData.userSubscriber != null)
		{
			subscriberClient.extendLoan(displayedLoan.getSubscriberID(), displayedLoan.getLoanID());
			flag = true;
		}
		if(GeneralData.userLibrarian != null)
		{
			Date returnExtend = Date.from(dateReturn.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()); 
			if(displayedLoan.getReturnDate().before(returnExtend)||displayedLoan.getReturnDate().equals(returnExtend))
			{
				librarianClient.extendLoanByLibrarian(displayedLoan.getSubscriberID(), displayedLoan.getLoanID(), returnExtend, GeneralData.userLibrarian.getEmail());
				flag = true;
			}
			else
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				RetriveMSG.setVisible(true);
				RetriveMSG.setText("new date : "+dateFormat.format(returnExtend)+" is before previous end date : "+displayedLoan.getReturnDate());
				RetriveMSG.setTextFill(Color.RED);
			}
		}

		if(flag)
		{
			Image image = new Image(getClass().getResource("/MenuIcons/loading.gif").toExternalForm());
			ImageView imageView = new ImageView(image);
			RetriveMSG.setText("");
			RetriveMSG.setVisible(true);
			RetriveMSG.setGraphic(imageView);
		}
	}
	
	

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		RetriveMSG.setGraphic(null);
		switch(op)
		{
		/*case returnSuccessMsg:
			RetriveMSG.setVisible(true);
			RetriveMSG.setText((String) msg);
			RetriveMSG.setTextFill(Color.GREEN);
			break;*/
		case returnLoanArray:
			Subscriber sub = null;
			if(GeneralData.userSubscriber!=null)
			{
				GeneralData.userSubscriber.setActiveLoans((ArrayList<Loan>) msg);
				sub = GeneralData.userSubscriber;
			}
			else if(GeneralData.searchedSubscriber!=null)
			{
				GeneralData.searchedSubscriber.setActiveLoans((ArrayList<Loan>) msg);
				sub = GeneralData.searchedSubscriber;
			}
			SubscriberCardController subCon = new SubscriberCardController();
			subCon.setSubscriberCard(sub);
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
