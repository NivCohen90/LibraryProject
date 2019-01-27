package OBLFX;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Client.Main;
import Client.SubscriberHandler;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import Interfaces.IGeneralData;
import Interfaces.IGeneralData.operationsReturn;
import SystemObjects.Book;
import Users.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML controller for displaying book details
 * @author ofir
 *
 */
public class BookDetailsController implements IGUIcontroller{

	private Book displayedBook;
	
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
    
    /**
     * set fields in FXML with book details
     * @param BookToDisplay which book to display details of
     */
    public void setBookToDisplay(Book BookToDisplay)
    {
    	this.displayedBook = BookToDisplay;
    	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		BookNameTextField.setText(BookToDisplay.getBookName());
		AuthorTextField.setText(BookToDisplay.getAuthorName().toString());
	    SubjectTextField.setText(BookToDisplay.getSubject());
	    PlaceOnShelfTextField.setText(BookToDisplay.getShelfLoaction());
	    CatalogTextField.setText(BookToDisplay.getCatalogNumber());
	    PurchaseDateTextField.setText(dateFormat.format(BookToDisplay.getPurchesDate()));
	    AvailibaleCopiesTextField.setText(String.valueOf(BookToDisplay.getAvailableCopies()));
	    NumberOfCopiesTextField.setText(String.valueOf(BookToDisplay.getNumberOfLibraryCopies()));
	    if(BookToDisplay.getAvailableCopies()==0 && IGeneralData.userSubscriber!=null)
	    	OrderBookBTN.setVisible(true);
	    else
	    	OrderBookBTN.setVisible(false);
	    EditionNumberTextField.setText(BookToDisplay.getEditionNumber());
	    DescriptionTextField.setText(BookToDisplay.getDescription());
    }

    /**
     * request order book from client
     * @param event
     */
    @FXML
    void orderBookFromLibrary(ActionEvent event) {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	subscriberClient.orderBook(IGeneralData.userSubscriber, this.displayedBook, dateFormat.format(new Date()));
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
				OrderMsgText.setVisible(true);
				break;
			case returnError:
				OrderMsgText.setText(((Exception)msg).getMessage());
				OrderMsgText.setVisible(true);
				((Exception)msg).printStackTrace();
		default:
			break;
		}
		
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
