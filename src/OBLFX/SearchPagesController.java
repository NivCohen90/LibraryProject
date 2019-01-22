package OBLFX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Client.SideMenu;
import Users.Book;
import Users.IGeneralData;
import Users.IGeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchPagesController implements IGUIcontroller {

	private enum SearchOption {
		BOOK, SUBSCRIBER, LIBRARIAN
	};

	private SearchOption currentSearch;
	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
	private CommonHandler commonClient;
	@FXML
	public void initialize() {
		switch(SideMenu.clicked)
		{
			case SearchSubscriber:
				setLabelsSearchSubscriber();
				break;
			case SearchLibrarian:
				setLabelsSearchLibrarian();
				break;
			default :
				setLabelsSearchBook();
		}
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

	private TableView<Book> tblResultsBook = new TableView<>();
	private TableView<Subscriber> tblResultsSubscriber = new TableView<>();
	private TableView<Librarian> tblResultsLibrarian = new TableView<>();

	public void setLabelsSearchBook() {
		currentSearch = SearchOption.BOOK;
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

	public void setLabelsSearchSubscriber() {
		currentSearch = SearchOption.SUBSCRIBER;
		title.setText("Search Subscriber");
		type1.setText("Student ID");
		type2.setText("Subscriber ID");
		type3.setText("Name");
		type4.setText("Email");
		col1.setText("Student ID");
		col2.setText("Subscriber ID");
		col3.setText("Full Name");
		col4.setText("Email");
		tblResults.setVisible(false);
		lblResults.setVisible(false);

		col1.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col2.setCellValueFactory(new PropertyValueFactory<>("SubscriberNumber"));
		col3.setCellValueFactory(new PropertyValueFactory<>("FullName"));
		col4.setCellValueFactory(new PropertyValueFactory<>("Email"));
	}

	public void setLabelsSearchLibrarian() {
		currentSearch = SearchOption.LIBRARIAN;
		title.setText("Search Librarian");
		type1.setText("Librarian ID");
		type2.setText("Affiliation");
		type3.setText("Name");
		type4.setText("Email");
		col1.setText("Librarian ID");
		col2.setText("Affiliation");
		col3.setText("Full Name");
		col4.setText("Email");
		tblResults.setVisible(false);
		lblResults.setVisible(false);

		col1.setCellValueFactory(new PropertyValueFactory<>("ID"));
		col2.setCellValueFactory(new PropertyValueFactory<>("Affiliation"));
		col3.setCellValueFactory(new PropertyValueFactory<>("FullName"));
		col4.setCellValueFactory(new PropertyValueFactory<>("Email"));
	}

	@FXML
	void searchInLibrary(ActionEvent event) {

		String searchInput = txtInput.getText();
		if (searchInput.isEmpty()) {
			emptyMsg.setVisible(true);
		}
		else {
			emptyMsg.setVisible(false);
			switch (currentSearch) {
			case BOOK: {
				if (type1.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookName);
				if (type2.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookAuthor);
				if (type3.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookSubject);
				if (type4.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByBookDescription);
				break;
			}
			case SUBSCRIBER: {
				if (type1.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchBySubscriberStudentID);
				if (type2.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchBySubscriberID);
				if (type3.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchBySubscriberName);
				if (type4.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchBySubscriberEmail);
				break;
			}
			case LIBRARIAN: {
				if (type1.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByLibrarianID);
				if (type2.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByLibrarianAffiliation);
				if (type3.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByLibrarianName);
				if (type4.isSelected())
					commonClient.searchInServer(searchInput, IGeneralData.operations.searchByLibrarianEmail);
				break;
			}
			}
		}

	}

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
	
	private void displayError(Error msg) {

		lblResults.setText(msg.getMessage());
		lblResults.setVisible(true);
		
	}

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
			if(choosenResult instanceof Librarian)
			{
				root = (AnchorPane) fxmlLoader.load(getClass().getResource("../FXML/BookDetails.fxml").openStream());
				BookDetailsController Controller = (BookDetailsController) fxmlLoader.getController();
				Controller.setBookToDisplay((Book)choosenResult);
			}
			
			
		
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		displayResults((ArrayList<T>) msg);
	}

	@Override
	public void setConnection() {
		commonClient = new CommonHandler(this);	
	}

	@Override
	public void closeConnection() {
		commonClient.quit();	
	}

}
