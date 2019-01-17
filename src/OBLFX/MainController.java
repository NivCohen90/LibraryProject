package OBLFX;

import java.io.IOException;
import java.util.ArrayList;

import Client.CommonHandler;
import Users.Book;
import Users.IGeneralData;
import Users.ServerData;
import Users.IGeneralData.operationsReturn;
import Users.IGeneralData.bookSearchFields;
import OBLFX.IGUIcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements IGUIcontroller {

	// create CommonHandler object to use in controller
	private CommonHandler commonClient;

	@FXML
	public void initialize() {
		try {
			commonClient = new CommonHandler(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button LoginButton;

	@FXML
	private Button btnSreach;

	@FXML
	private TextArea searchResult;

    @FXML
    private TableView<?> resultValueTable;

	@FXML
	private Label errotMsg;

	@FXML
	private ToggleGroup searchType;

	@FXML
	private TextField txtInput;

	@FXML
	private RadioButton toggleByName;

	@FXML
	private RadioButton toggleByAuthor;

	@FXML
	private RadioButton toggleBySubject;

	@FXML
	private RadioButton toggleByDescription;

	@FXML
	void LoginScreen(ActionEvent event) throws Exception {
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("../FXML/LoginForm.fxml").openStream());
		// LoginFormController loginFormController = loader.getController();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../CSS/LoginForm.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@FXML
	void searchBook(ActionEvent event) {
		String searchInput = txtInput.getText();
		String selectedType = ((RadioButton) searchType.getSelectedToggle()).getText();
		ArrayList<Object> List = new ArrayList<Object>();
		List.add(searchInput);
		switch (selectedType) {
		case "By Name":
			commonClient.searchBookInServer(searchInput, IGeneralData.operations.searchByBookName);
			break;
		case "By Author":
			commonClient.searchBookInServer(searchInput, IGeneralData.operations.searchByBookAuthor);
			break;
		case "By Subject":
			commonClient.searchBookInServer(searchInput, IGeneralData.operations.searchByBookSubject);
			break;
		case "By Description":
			commonClient.searchBookInServer(searchInput, IGeneralData.operations.searchByBookDescription);
			break;
		default:
			commonClient.searchBookInServer(searchInput, IGeneralData.operations.searchByBookName);
			break;
		}

		/*
		 * // get input from GUI String searchInput = txtInput.getText(); String
		 * selectedType = ((RadioButton) searchType.getSelectedToggle()).getText();
		 * 
		 * // check what kind of search was selected // call to method from handler,
		 * handler will send input to server if (selectedType.contains("Name")) {
		 * commonClient.searchBookInServer(searchInput, bookSearchFields.bookNameField);
		 * } // more search types...
		 * 
		 */
	}

	// showing book results in GUI
	private void displayBookResults(ArrayList<Book> bookList) {
		String results = "";
	//	int colomn=0,row=0;
		if (!bookList.isEmpty())
			for (Book booki : bookList)
				results += booki.getBookName() + " : " + booki.getAuthorName() + " : " + booki.getSubject() + " : " + booki.getDescription() +"\n";
		else
			results = "no books found";
		searchResult.setVisible(true);
		searchResult.setText(results);
	}

	// calling to this method in IHandler class
	// how to display message from server in GUI
	@SuppressWarnings("unchecked")
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {

		switch (op) // switch between different return massages from server
		{
		// each case will need to cast msg to different kind, and call display method to
		// show the data
		case returnBookArray:
			if (msg != null)
				displayBookResults((ArrayList<Book>) msg);
			else
				errotMsg.setVisible(true);
			break;
		default:
			;
		}
	}
}
