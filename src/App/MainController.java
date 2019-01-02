package App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import App.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	static ChatClient client;

	final public static int DEFAULT_PORT = 5555;

	boolean ConnectedFlag = false;
	
	static ObservableList<Student> ObservableStudentList = FXCollections.observableArrayList();
	static Student staticStudent;
	public static String updateStudent = "";
	
	@FXML
	private Button GetStudentsBtn;

	@FXML
	private TableView<Student> StudentsTable;

	@FXML
	private TableColumn<Student, String> StudentID;

	@FXML
	private TableColumn<Student, String> StudentName;

	@FXML
	private TableColumn<Student, String> StatusMembership;

	@FXML
	private TableColumn<Student, String> Operation;

	@FXML
	private TableColumn<Student, Boolean> Freeze;

	@FXML
	private TextField StudentIDTextField;

	@FXML
	private TextField StudentNameTextField;

	@FXML
	private ComboBox<String> StatusMembershipComboBox;

	@FXML
	private TextField OperationTextField;

	@FXML
	private ComboBox<String> FreezeComboBox;

	@FXML
	private RadioButton ExtendBookRequestRadioBtn;

	@FXML
	private RadioButton ReturnBookRequestRadioBtn;

	@FXML
	private RadioButton LendingReqestRadioBtn;

	@FXML
	private TextField StudentIDTextField1;

	@FXML
	private TextField StudentNameTextField1;

	@FXML
	private ComboBox<String> StatusMembershipComboBox1;

	@FXML
	private ComboBox<String> FreezeComboBox1;

	@FXML
	private RadioButton ExtendBookRequestRadioBtn1;

	@FXML
	private RadioButton ReturnBookRequestRadioBtn1;

	@FXML
	private RadioButton LendingReqestRadioBtn1;

	@FXML
	private TextField ServerIPTextField;

	@FXML
	private Button ConnectToServerBtn;

	@FXML
	private Label ConnectionMessage;

	@FXML
	private Label ErrorMsgLabel;

	@FXML
	private Button UpdateStudentBtn;

	@FXML
	private Button AddStudentBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		FreezeComboBox.getItems().add("true");
		FreezeComboBox.getItems().add("false");
		
		StatusMembershipComboBox.getItems().add("Locked");
		StatusMembershipComboBox.getItems().add("Frozen");
		StatusMembershipComboBox.getItems().add("Active");
		StatusMembershipComboBox.getItems().add("NotRegistered");
		
		FreezeComboBox1.getItems().add("true");
		FreezeComboBox1.getItems().add("false");

		StatusMembershipComboBox1.getItems().add("Locked");
		StatusMembershipComboBox1.getItems().add("Frozen");
		StatusMembershipComboBox1.getItems().add("Active");
		StatusMembershipComboBox1.getItems().add("NotRegistered");
		
		StudentsTable.setItems(ObservableStudentList);
		
		StudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
		StudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
		StatusMembership.setCellValueFactory(new PropertyValueFactory<>("StatusMembership"));
		Operation.setCellValueFactory(new PropertyValueFactory<>("Operation"));
		Freeze.setCellValueFactory(new PropertyValueFactory<>("Freeze"));

	}

	@FXML
	void AddStudent(ActionEvent event) {
		if (ConnectedFlag) {
			Boolean Flag = true;
			int counter = 0;
			String Operations = "";
			if (ExtendBookRequestRadioBtn.isSelected()) {
				Operations = Operations + "ExtendBookRequest";
				counter++;
			}
			if (ReturnBookRequestRadioBtn.isSelected()) {
				if (!Operations.equals("")) {
					Operations = Operations + ", ";
				}
				Operations = Operations + "ReturnBookRequest";
				counter++;
			}
			if (LendingReqestRadioBtn.isSelected()) {
				if (!Operations.equals("")) {
					Operations = Operations + ", ";
				}
				Operations = Operations + "LendingReqest";
				counter++;
			}
			if (counter == 0) {
				Flag = false;
			}
			if (StudentIDTextField.getText().length() == 0) {
				Flag = false;
			}
			if (StudentNameTextField.getText().length() == 0) {
				Flag = false;
			}
			if (StatusMembershipComboBox.getValue() == null) {
				Flag = false;
			}
			if (FreezeComboBox.getValue() == null) {
				Flag = false;
			}
			if (Flag) {
				staticStudent = new Student(StudentIDTextField.getText(), StudentNameTextField.getText(),
						StatusMembershipComboBox.getValue(), Operations, FreezeComboBox.getValue());
				try {
					staticStudent.commend = 1;
					client.sendToServer(staticStudent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StudentIDTextField.clear();
				StudentNameTextField.clear();
				StatusMembershipComboBox.setValue("Choose Status");
				ExtendBookRequestRadioBtn.setSelected(false);
				ReturnBookRequestRadioBtn.setSelected(false);
				LendingReqestRadioBtn.setSelected(false);
				FreezeComboBox.setValue(null);
				ErrorMsgLabel.setText("");
				
			} else {
				ErrorMsgLabel.setTextFill(Color.RED);
				ErrorMsgLabel.setText(
						"Error: Some of the fields are Empty." + "\n" + "Please Fill all of the fields correctly.");
			}

		} else {
			ErrorMsgLabel.setTextFill(Color.RED);
			ErrorMsgLabel.setText("Error: you Are not Connected to the Server.");
		}

	}

	@FXML
	void UpdateStudent(ActionEvent event) {
		if (ConnectedFlag) {
			Boolean Flag = true;
			int counter = 0;
			String Operations = "";
			if (ExtendBookRequestRadioBtn1.isSelected()) {
				Operations = Operations + "ExtendBookRequest";
				counter++;
			}
			if (ReturnBookRequestRadioBtn1.isSelected()) {
				if (!Operations.equals("")) {
					Operations = Operations + ", ";
				}
				Operations = Operations + "ReturnBookRequest";
				counter++;
			}
			if (LendingReqestRadioBtn1.isSelected()) {
				if (!Operations.equals("")) {
					Operations = Operations + ", ";
				}
				Operations = Operations + "LendingReqest";
				counter++;
			}
			if (counter == 0) {
				Flag = false;
			}
			if (StudentIDTextField1.getText().length() == 0) {
				Flag = false;
			}
			if (StudentNameTextField1.getText().length() == 0) {
				Flag = false;
			}
			if (StatusMembershipComboBox1.getValue() == null) {
				Flag = false;
			}
			if (FreezeComboBox1.getValue() == null) {
				Flag = false;
			}
			if (Flag) {
				staticStudent = new Student(StudentIDTextField1.getText(), StudentNameTextField1.getText(),
						StatusMembershipComboBox1.getValue(), Operations, FreezeComboBox1.getValue());
				try {
					staticStudent.commend = 2;
					client.sendToServer(staticStudent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//ObservableStudentList.add(student);
				// StudentsTable.setItems(studentsObservableList);
				StudentIDTextField1.clear();
				StudentNameTextField1.clear();
				StatusMembershipComboBox1.setValue("Choose Status");
				ExtendBookRequestRadioBtn1.setSelected(false);
				ReturnBookRequestRadioBtn1.setSelected(false);
				LendingReqestRadioBtn1.setSelected(false);
				FreezeComboBox1.setValue(null);
				ErrorMsgLabel.setText("");
			} else {
				ErrorMsgLabel.setTextFill(Color.RED);
				ErrorMsgLabel.setText(
						"Error: Some of the fields are Empty." + "\n" + "Please Fill all of the fields correctly.");
			}

		} else {
			ErrorMsgLabel.setTextFill(Color.RED);
			ErrorMsgLabel.setText("Error: you Are not Connected to the Server.");
		}

	}

	@FXML
	void getAllStudents(ActionEvent event) {
		if (ConnectedFlag) {
			try {
				client.sendToServer("getAllStudents");
				//GetStudentsBtn.setDisable(true);
			} catch (IOException e) {

			}
		}else {
			ErrorMsgLabel.setTextFill(Color.RED);
			ErrorMsgLabel.setText("Error: you Are not Connected to the Server.");
		}
	}

	@FXML
	void SetConnectionToServer(ActionEvent event) {
		String host = "";

		try {
			host = ServerIPTextField.getText();
		} catch (Exception e) {
			host = "localhost";
		}
		try {
			client = new ChatClient(host, DEFAULT_PORT);
			ConnectionMessage.setTextFill(Color.BLUE);
			ConnectionMessage.setText("You Are Connected to the Server.");
			ServerIPTextField.setEditable(false);
			ConnectToServerBtn.setDisable(true);
			ConnectedFlag = true;
			ErrorMsgLabel.setText("");
		} catch (IOException exception) {
			ConnectionMessage.setTextFill(Color.RED);
			ConnectionMessage.setText("Error: Can't setup connection!");
		}
	}

}
