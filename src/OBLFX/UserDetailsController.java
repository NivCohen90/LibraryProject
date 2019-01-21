package OBLFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Client.Main;
import Client.SideMenu;
import Users.*;

public class UserDetailsController {

	private enum UserOption {
		SUBSCRIBER, LIBRARIAN
	};
	
	private User displayedUser;
	static ObservableList<Object> ObservableColumnDataTable1 = FXCollections.observableArrayList();
	static ObservableList<Object> ObservableColumnDataTable2 = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		switch(SideMenu.clicked)
		{
			case SubscriberCard:
				setLabelsSubscriber();
				setUserToDisplay(Main.userSubscriber);
				break;
			case LibrarianCard:
			case ManagerCard:
				setLabelsLibrarian();
				setUserToDisplay(Main.userLibrarian);
				break;
			default :
				setLabelsSubscriber();
		}
		tbl1.setItems(ObservableColumnDataTable1);
		tbl2.setItems(ObservableColumnDataTable2);
		
		/*try {
			commonClient = new CommonHandler(this);
			librarianClient = new LibrarianHandler(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
        */
		/*tbl1.setRowFactory(tv -> {
            TableRow<Object> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {

                	Object clickedRow = row.getItem();
                	//openResultDetails(clickedRow);
                }
            });
            return (TableRow<Object>) row ;
        });*/
	}
	

    @FXML
    private TextField txtfldLbl1;

    @FXML
    private TextField txtfldLbl5;

    @FXML
    private TextField txtfldLbl4;

    @FXML
    private TextField txtfldLbl2;

    @FXML
    private TextField txtfldLbl6;

    @FXML
    private TextField txtfldLbl3;
	
    @FXML
    private Text lbl1;

    @FXML
    private Text lbl2;

    @FXML
    private Text lbl3;

    @FXML
    private Text lbl4;

    @FXML
    private Text lbl5;

    @FXML
    private Text lbl6;
    
    @FXML
    private TableView<Object> tbl1;
    
    @FXML
    private TableView<Object> tbl2;

    @FXML
    private TableColumn<?, ?> tbl1col1;

    @FXML
    private TableColumn<?, ?> tbl1col2;

    @FXML
    private TableColumn<?, ?> tbl1col3;

    @FXML
    private TableColumn<?, ?> tbl1col4;

    @FXML
    private Text tableTitle1;

    @FXML
    private Text title;

    @FXML
    private TableColumn<?, ?> tbl2col1;

    @FXML
    private TableColumn<?, ?> tbl2col2;

    @FXML
    private TableColumn<?, ?> tbl2col3;

    @FXML
    private TableColumn<?, ?> tbl2col4;

    @FXML
    private Text tableTitle2;
    
    public void setLabelsSubscriber()
    {
    	this.title.setText("Reader Card");
    	this.lbl1.setText("Full Name:");
    	this.lbl2.setText("Student ID:");
    	this.lbl3.setText("Email:");
    	this.lbl4.setText("Subscriber ID:");
    	this.lbl5.setText("Phone Number :");
    	this.lbl6.setText("Status :");
    	this.tableTitle1.setText("Active Loans");
    	this.tbl1.setPlaceholder(new Label("No active loans"));
    	this.tbl1col1.setText("Book Name");
    	this.tbl1col2.setText("Author");
    	this.tbl1col3.setText("Start Loan Date");
    	this.tbl1col4.setText("End Loan Date");
    	tbl1col1.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tbl1col2.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tbl1col3.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
    	tbl1col4.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	
    	
    	this.tableTitle2.setText("Active Orders");
    	this.tbl2.setPlaceholder(new Label("No active orders"));
    	this.tbl2col1.setText("Book Name");
    	this.tbl2col2.setText("Author");
    	this.tbl2col3.setText("Order Date");
    	this.tbl2col4.setText("Book Arrived Date");
    	tbl2col1.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tbl2col2.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tbl2col3.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));
    	tbl2col4.setCellValueFactory(new PropertyValueFactory<>("BookArrivedTime"));
    }
    
    public void setLabelsLibrarian()
    {
    	this.title.setText("Librarian Card");
    	this.lbl1.setText("Full Name:");
    	this.lbl2.setText("Librarian ID:");
    	this.lbl3.setText("Affiliation:");
    	this.lbl4.setVisible(false);
    	this.lbl5.setVisible(false);
    	this.lbl6.setVisible(false);
    	this.tableTitle1.setText("Messages");
    	this.tbl1.setPlaceholder(new Label("No messages"));
    	this.tbl1col1.setText("Date");
    	this.tbl1col2.setText("Message");
    	tbl1col1.setPrefWidth(150);
    	tbl1col1.setMaxWidth(150);
    	tbl1col2.setMaxWidth(606);
    	tbl1col2.setPrefWidth(606);
    	this.tbl1col3.setVisible(false);
    	this.tbl1col4.setVisible(false);
    	tbl1col1.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tbl1col2.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tbl1col3.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
    	tbl1col4.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	
    	
    	this.tableTitle2.setVisible(false);
    	this.tbl2.setVisible(false);
    	this.tbl2col1.setVisible(false);
    	this.tbl2col2.setVisible(false);
    	this.tbl2col3.setVisible(false);
    	this.tbl2col4.setVisible(false);
    }
    
    public void setUserToDisplay(User UserToDisplay)
    {
    	this.displayedUser = UserToDisplay;
    	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		txtfldLbl1.setText(UserToDisplay.getFullName());
		txtfldLbl2.setText(UserToDisplay.getID());
		if(UserToDisplay instanceof Subscriber)
		{
			Subscriber sub = (Subscriber)UserToDisplay;
			txtfldLbl3.setText(sub.getEmail());
			txtfldLbl4.setText(sub.getSubscriberNumber());
			txtfldLbl5.setText(sub.getPhoneNumber());
			txtfldLbl6.setText(sub.getStatus());
			setDataInTable(sub.getActiveLoans(), ObservableColumnDataTable1, tbl1);
			setDataInTable(sub.getActiveOrders(), ObservableColumnDataTable2, tbl2);
		}
		if(UserToDisplay instanceof Librarian)
		{
			Librarian sub = (Librarian)UserToDisplay;
			txtfldLbl3.setText(sub.getAffiliation());
			txtfldLbl4.setVisible(false);
			txtfldLbl5.setVisible(false);
			txtfldLbl6.setVisible(false);
		}
		
    }

	private <T> void setDataInTable(ArrayList<T> list, ObservableList<Object> observablelist, TableView<Object> table) {
		observablelist.clear();
		if(!list.isEmpty())
		{
			for (T Ti : list)
				observablelist.add(Ti);
		}
		table.setVisible(true);
	}
}
