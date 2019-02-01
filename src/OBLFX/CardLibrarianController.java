package OBLFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Client.SideMenu;
import Interfaces.IAlert;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import Users.*;

/**
 * FXML controller for librarian card
 * @author ofir
 *
 */
public class CardLibrarianController implements IGUIcontroller {
	
	@SuppressWarnings("unused")
	private User displayedLibrarian;
	static ObservableList<Object> ObservableColumnDataTable1 = FXCollections.observableArrayList();
	static ObservableList<Object> ObservableColumnDataTable2 = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		setLabelsLibrarian();
		tbl1.setItems(ObservableColumnDataTable1);
		tbl2.setItems(ObservableColumnDataTable2);
		
		if(GeneralData.userLibrarian!=null)
			setLibrarianToDisplay(GeneralData.userLibrarian);
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
    
    /**
     * set labels for librarian card in FXML
     */
    private void setLabelsLibrarian()
    {
    	this.title.setText("Librarian Card");
    	this.lbl1.setText("First Name:");
    	this.lbl4.setText("Last Name:");
    	this.lbl2.setText("Librarian ID:");
    	this.lbl5.setText("Affiliation:");
    	this.lbl3.setVisible(false);
    	this.lbl6.setVisible(false);
		txtfldLbl3.setVisible(false);
		txtfldLbl6.setVisible(false);
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
    
    /**
     * set which {@link Librarian} object to display details of
     * @param LibrarianToDisplay object to display
     */
    public void setLibrarianToDisplay(Librarian LibrarianToDisplay)
    {
    	this.displayedLibrarian = LibrarianToDisplay;
    	
		@SuppressWarnings("unused")
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		((TextField) SideMenu.APCardLibrarianFXML.lookup("#txtfldLbl1")).setText(LibrarianToDisplay.getFirstName());
		((TextField) SideMenu.APCardLibrarianFXML.lookup("#txtfldLbl2")).setText(LibrarianToDisplay.getID());
		((TextField) SideMenu.APCardLibrarianFXML.lookup("#txtfldLbl4")).setText(LibrarianToDisplay.getLastName());
		((TextField) SideMenu.APCardLibrarianFXML.lookup("#txtfldLbl5")).setText(LibrarianToDisplay.getAffiliation());
		
		//set details when opening FXML on search
		if (txtfldLbl1 != null) {
			txtfldLbl1.setText(LibrarianToDisplay.getFirstName());
			txtfldLbl2.setText(LibrarianToDisplay.getID());
			txtfldLbl4.setText(LibrarianToDisplay.getLastName());
			txtfldLbl5.setText(LibrarianToDisplay.getAffiliation());
		}
    }

    /**
     * set data in FXML tables
     * @param list ArrayList<T> with object list to add to table
     * @param observablelist to add to, that is set to the table
     * @param table	which table to add to
     */
	@SuppressWarnings("unused")
	private <T> void setDataInTable(ArrayList<T> list, ObservableList<Object> observablelist, TableView<Object> table) {
		observablelist.clear();
		if(!list.isEmpty())
		{
			for (T Ti : list)
				observablelist.add(Ti);
		}
		table.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setConnection() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}
}
