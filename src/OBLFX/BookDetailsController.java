package OBLFX;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import Client.SubscriberHandler;
import Users.Book;
import Users.IGeneralData.operationsReturn;
import Users.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML controller for displying book details
 * @author ofir
 *
 */
public class BookDetailsController implements IGUIcontroller{

	private Book displayedBook;
	private Subscriber subscriberThatSearched = null; 
	
	private SubscriberHandler subscriberClient;

	@FXML
	public void initialize() {
	}
	
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
     * if there is logged in {@link Subscriber} save object data
     * @param subscriber save which subscriber search
     */
    public void setSubscriberThatSearched(Subscriber subscriber)
    {
    	this.subscriberThatSearched = subscriber;
    }
    
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
	    if(BookToDisplay.getAvailableCopies()==0)
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
    	subscriberClient.orderBook(this.subscriberThatSearched, this.displayedBook, dateFormat.format(new Date()));
    }
    
    @FXML
    void openPdfFile(ActionEvent event) {
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
				//orderError, too many orders for book
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
