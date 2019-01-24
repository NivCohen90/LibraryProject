package OBLFX;

import java.util.ArrayList;

import Client.CommonHandler;
import SystemObjects.Book;
import SystemObjects.IGeneralData;
import SystemObjects.IGeneralData.operationsReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML controller for search book 
 * @author ofir
 *
 */
public class SearchBookController implements IGUIcontroller {

	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
	private CommonHandler commonClient;
	@FXML
	public void initialize() {
		setLabelsSearchBook();
		tblResults.setItems(ObservableColumnData);
        tblResults.setRowFactory(tv -> {
            TableRow<Object> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {

                	Object clickedRow = row.getItem();
                	openResultDetails(clickedRow);
                }
            });
            return (TableRow<Object>) row ;
        });
	}

	@FXML
	private TextField txtInput;

	@FXML
	private Button btnSearch;

	@FXML
	private ToggleGroup searchType;

	@FXML
	private RadioButton type1;

	@FXML
	private RadioButton type2;

	@FXML
	private RadioButton type3;

	@FXML
	private RadioButton type4;

	@FXML
	private Label title;

	@FXML
	private TableView<Object> tblResults;

	@FXML
	private TableColumn<?, ?> col1;

	@FXML
	private TableColumn<?, ?> col2;

	@FXML
	private TableColumn<?, ?> col3;

	@FXML
	private TableColumn<?, ?> col4;

	@FXML
	private Label lblResults;

	@FXML
	private Label emptyMsg;
	
    @FXML
    private Label lblNoResult;

	@SuppressWarnings("unused")
	private TableView<Book> tblResultsBook = new TableView<>();

    /**
     * set labels for book search in FXML
     */
	public void setLabelsSearchBook() {
		title.setText("Search Book");
		type1.setText("Name");
		type2.setText("Author");
		type3.setText("Subject");
		type4.setText("Description");
		col1.setText("Book Name");
		col2.setText("Author Name");
		col3.setText("Subject");
		col4.setText("Description");
		tblResults.setVisible(false);
		lblResults.setVisible(false);

		col1.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		col2.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
		col3.setCellValueFactory(new PropertyValueFactory<>("Subject"));
		col4.setCellValueFactory(new PropertyValueFactory<>("Description"));
	}
	
    /**
     * request search book from client
     * @param event
     */
	@FXML
	void searchInLibrary(ActionEvent event) {

		String searchInput = txtInput.getText();
		if (searchInput.isEmpty()) {
			emptyMsg.setVisible(true);
		}
		else {
			emptyMsg.setVisible(false);
			if (type1.isSelected())
				commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookName);
			if (type2.isSelected())
				commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookAuthor);
			if (type3.isSelected())
				commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookSubject);
			if (type4.isSelected())
				commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookDescription);
		}

	}

    /**
     * set data in FXML result table
     * @param list ArrayList<T> with object list to add to table
     */
	private <T> void displayResults(ArrayList<T> list) {
		ObservableColumnData.clear();
		if(!list.isEmpty())
		{
			lblNoResult.setVisible(false);
			for (T Ti : list)
				ObservableColumnData.add(Ti);
		}
		else
		{
			lblNoResult.setVisible(true);
			tblResults.setPlaceholder(new Label(""));
		}
		tblResults.setVisible(true);
		lblResults.setVisible(true);
	}
	
	/**
	 * display error in FXML
	 * @param msg error message
	 */
	@SuppressWarnings("unused")
	private void displayError(Error msg) {

		lblResults.setText(msg.getMessage());
		lblResults.setVisible(true);
		
	}

	/**
	 * open window with book details
	 * @param choosenResult chosen book to display details
	 */
	private void openResultDetails(Object choosenResult)
	{
    	Stage primaryStage = new Stage();
    	FXMLLoader fxmlLoader = new FXMLLoader();
    	AnchorPane root = null;
    	Scene scene = null;
		try {
			
			if(choosenResult instanceof Book)
			{
				root = (AnchorPane) fxmlLoader.load(getClass().getResource("../FXML/BookDetails.fxml").openStream());
				scene = new Scene(root);
				BookDetailsController Controller = (BookDetailsController) fxmlLoader.getController();
				Controller.setBookToDisplay((Book)choosenResult);
				primaryStage.setTitle(((Book)choosenResult).getBookName());
			}		
		
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		displayResults((ArrayList<T>) msg);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void setConnection() {
		commonClient = new CommonHandler(this);	
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public void closeConnection() {
		if(commonClient!=null)
			commonClient.quit();	
	}

}
