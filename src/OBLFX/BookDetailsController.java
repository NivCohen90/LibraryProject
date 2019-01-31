package OBLFX;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Client.Main;
import Client.SubscriberHandler;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.Order;
import Users.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML controller for displaying book details
 * @author ofir
 *
 */
public class BookDetailsController implements IGUIcontroller{

	private Book displayedBook;
	private Order sentOrder;
	
	private SubscriberHandler subscriberClient;
	
    @FXML
    private TextField BookNameTextField;

    @FXML
    private TextField AuthorTextField;

    @FXML
    private TextField SubjectTextField;

    @FXML
    private TextField PlaceOnShelfTextField;

    @FXML
    private TextField CatalogTextField;

    @FXML
    private TextField PurchaseDateTextField;

    @FXML
    private TextField AvailibaleCopiesTextField;

    @FXML
    private TextField NumberOfCopiesTextField;

    @FXML
    private TextField EditionNumberTextField;

    @FXML
    private Button OpenFileBTN;

    @FXML
    private Button OrderBookBTN;

    @FXML
    private TextArea DescriptionTextField;

    @FXML
    private Label txtWhenAvailableDate;
    
    @FXML
    private Label OrderMsgText;
    
    @FXML
    private Label lblIsWanted;
    
    /**
     * set fields in FXML with book details
     * @param BookToDisplay which book to display details of
     */
    public void setBookToDisplay(Book BookToDisplay)
    {
    	this.displayedBook = BookToDisplay;
    	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	    if(BookToDisplay.getIsWanted())
	    	lblIsWanted.setVisible(true);
		BookNameTextField.setText(BookToDisplay.getBookName());
		AuthorTextField.setText(BookToDisplay.getAuthorName().toString());
	    SubjectTextField.setText(BookToDisplay.getSubject());
	    PlaceOnShelfTextField.setText(BookToDisplay.getShelfLoaction());
	    CatalogTextField.setText(BookToDisplay.getCatalogNumber());
	    PurchaseDateTextField.setText(dateFormat.format(BookToDisplay.getPurchesDate()));
	    AvailibaleCopiesTextField.setText(String.valueOf(BookToDisplay.getAvailableCopies()));
	    NumberOfCopiesTextField.setText(String.valueOf(BookToDisplay.getNumberOfLibraryCopies()));
	    if(BookToDisplay.getAvailableCopies()==0 && GeneralData.userSubscriber!=null)
	    	if(!GeneralData.userSubscriber.getStatus().equals("Freeze"))
	    		OrderBookBTN.setVisible(true);
	    else
	    	OrderBookBTN.setVisible(false);
	    EditionNumberTextField.setText(BookToDisplay.getEditionNumber());
	    if(EditionNumberTextField.getText().isEmpty())
	    	EditionNumberTextField.setText("no info");
	    DescriptionTextField.setText(BookToDisplay.getDescription());

    }

    /**
     * request order book from client
     * @param event
     */
    @FXML
    void orderBookFromLibrary(ActionEvent event) {
    	setConnection();
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String stringToday = dateFormat.format(new Date());
    	java.sql.Date sqlDate = java.sql.Date.valueOf(stringToday);
    	sentOrder = new Order(sqlDate, null, GeneralData.userSubscriber.getSubscriberNumber(), this.displayedBook.getCatalogNumber());
    	subscriberClient.orderBook(sentOrder);
    	//subscriberClient.orderBook(GeneralData.userSubscriber, this.displayedBook, new Date());
    }
    
    /**
     * open context table pdf file of book
     * @param event
     */
    @FXML
    void openPdfFile(ActionEvent event) {
    	File pdfoutFile;
		FileOutputStream fos;			//input from file
		BufferedOutputStream bos;		//buffer input		  
		  
		try {
			pdfoutFile = File.createTempFile("BookCN-"+displayedBook.getCatalogNumber()+"_temp", ".pdf");//File.createTempFile("outTemp", ".pdf");
			fos = new FileOutputStream(pdfoutFile);
			bos = new BufferedOutputStream(fos);
			bos.write(displayedBook.getContextTableByteArray(),0,displayedBook.getContextTableByteArray().length);				//read from file to byte array
			bos.flush();
			fos.flush();
			fos.close();
	    	if (Desktop.isDesktopSupported()) {
    	        Desktop.getDesktop().open(pdfoutFile);
	    	}
		} catch (IOException e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
    }
    
    /**
     * {@inheritDoc}}
     */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		switch(op)
		{
			case returnSuccessMsg:
				OrderMsgText.setText((String)msg);
				OrderMsgText.setTextFill(Color.GREEN);
				OrderMsgText.setVisible(true);
				GeneralData.userSubscriber.ActiveOrders.add(sentOrder);
				SubscriberCardController subCon = new SubscriberCardController();
				subCon.setSubscriberCard(GeneralData.userSubscriber);
				break;
			case returnError:
				OrderMsgText.setText((String)msg);
				OrderMsgText.setTextFill(Color.RED);
				OrderMsgText.setVisible(true);
				break;	
			case returnException:
				OrderMsgText.setText(((Exception)msg).getMessage());
				OrderMsgText.setTextFill(Color.RED);
				OrderMsgText.setVisible(true);
				IAlert.ExceptionAlert((Exception)msg);
		default:
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
		
	}

    /**
     * {@inheritDoc}}
     */
	@Override
	public void closeConnection() {
		if(subscriberClient!=null)
			subscriberClient.quit();
		
	}

}
