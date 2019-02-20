package OBLFX;

import java.util.ArrayList;
import Client.CommonHandler;
import Interfaces.IAlert;
import Interfaces.IFXMLpathAndStyle;
import Interfaces.IGUIcontroller;
import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Subscriber;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML controller for search subscriber
 * @author ofir
 *
 */
public class SearchSubscriberController implements IGUIcontroller {

	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
	private CommonHandler commonClient;
	@FXML
	public void initialize() {
		setLabelsSearchSubscriber();
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
	private TableView<Subscriber> tblResultsSubscriber = new TableView<>();
	@FXML
	void CheckSearch(KeyEvent event) {
		if (IGUIcontroller.CheckIfUserPutInput(txtInput, emptyMsg)) {
			if (type1.isSelected()) {
				IGUIcontroller.CheckOnlyNumbers(txtInput, emptyMsg, 9, UserNameErrorDigits);
			}
			if (type2.isSelected()) {
				IGUIcontroller.CheckOnlyLetter(txtInput, emptyMsg, OnlyNumbers, UserNameErrorNumebrs);
			}

			if (type3.isSelected()) {
				IGUIcontroller.CheckOnlyLetter(txtInput, emptyMsg, OnlyLetters, OnlyLetterError);

			}
		}
	}
	/**
	 * clear fields in search
	 * @param event
	 */
    @FXML
    void clearFields(MouseEvent event) {
    	ObservableColumnData.clear();
    	txtInput.setText("");
    	emptyMsg.setText("");
    	lblNoResult.setVisible(false);
    }
    
	/**
     * set labels for book subscriber in FXML
     */
	public void setLabelsSearchSubscriber() {
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

    /**
     * request search subscriber from client
     * @param event
     */
	@FXML
	void searchInLibrary(ActionEvent event) {

		String searchInput = txtInput.getText();
		boolean flag = false;
		
		if(IGUIcontroller.CheckIfUserPutInput(txtInput, emptyMsg)) {
			if (type1.isSelected()) {
				if(IGUIcontroller.CheckOnlyNumbers(txtInput, emptyMsg, 9, UserNameErrorDigits)) {
					commonClient.searchInServer(searchInput, GeneralData.operations.searchBySubscriberStudentID);
					flag = true;
				}}
			if (type2.isSelected()) {
				if(IGUIcontroller.CheckOnlyLetter(txtInput, emptyMsg, OnlyNumbers, UserNameErrorNumebrs)) {
					commonClient.searchInServer(searchInput, GeneralData.operations.searchBySubscriberID);
					flag = true;
				}
			}

			if (type3.isSelected()) {
				if(IGUIcontroller.CheckOnlyLetter(txtInput, emptyMsg, OnlyLetters, OnlyLetterError)) {
					commonClient.searchInServer(searchInput, GeneralData.operations.searchBySubscriberName);
					flag = true;
				}
			}			
			if (type4.isSelected()) {
				if(txtInput.getText().contains("@") && txtInput.getText().contains(".")) {
					commonClient.searchInServer(searchInput, GeneralData.operations.searchBySubscriberEmail);
					flag = true;
				}
				else {
					emptyMsg.setText("Invalid Email");
				}
				
			}
			
			if(flag)
			{
				Image image = new Image(getClass().getResource("/MenuIcons/loading.gif").toExternalForm());
				ImageView imageView = new ImageView(image);
				emptyMsg.setText("");
				emptyMsg.setVisible(true);
				emptyMsg.setGraphic(imageView);
			}
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
			
			if(choosenResult instanceof Subscriber)
			{
				GeneralData.searchedSubscriber = (Subscriber) choosenResult;
				root = (AnchorPane) fxmlLoader.load(getClass().getResource("/FXML/ReaderCard.fxml").openStream());
				scene = new Scene(root);
				SubscriberCardController Controller = (SubscriberCardController) fxmlLoader.getController();
				Controller.setSubscriberCard((Subscriber)choosenResult);
				root.setStyle(IFXMLpathAndStyle.BackgroundStyle);
				primaryStage.setTitle(((Subscriber)choosenResult).getFullName());
			}		
		
			primaryStage.setOnCloseRequest(e -> {
				GeneralData.searchedSubscriber=null;
		    });
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			IAlert.ExceptionAlert(e);
		}
	}
	
	/**
	 * {@inheritDoc}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		emptyMsg.setGraphic(null);
		switch(op) {
		case returnError:
			IAlert.setandShowAlert(AlertType.ERROR, "Cannot Find User" , (String)msg, (String)msg);
			break;
		case returnException:
			IAlert.ExceptionAlert((Exception)msg); 
			break;
		case returnLibrarianArray:
			displayResults((ArrayList<T>) msg);
			break;
		case returnSubscriberArray:
			displayResults((ArrayList<T>) msg);
			break;
		default:
			break;
		
		}
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
