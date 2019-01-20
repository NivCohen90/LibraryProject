package OBLFX;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Client.CommonHandler;
import Client.SubscriberHandler;
import Users.Book;
import Users.IGeneralData.operationsReturn;
import Users.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookDetailsController implements IGUIcontroller{

	private Book displayedBook;
	private Subscriber subscriberThatSearched = null; 
	
	private SubscriberHandler subscriberClient;

	@FXML
	public void initialize() {
		try {
			subscriberClient = new SubscriberHandler(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
    
    public void setSubscriberThatSearched(Subscriber subscriber)
    {
    	this.subscriberThatSearched = subscriber;
    }
    
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

    @FXML
    void orderBookFromLibrary(ActionEvent event) {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	subscriberClient.orderBook(this.subscriberThatSearched, this.displayedBook, dateFormat.format(new Date()));
    }
    
    @FXML
    void openPdfFile(ActionEvent event) {
    }
    
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
		}
		
	}

}
