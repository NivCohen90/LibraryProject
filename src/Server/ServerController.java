package Server;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Server.Log;

public class ServerController implements Initializable {

	/*************************************************************
	 * 
	 * Description: Variables
	 *
	 *
	 ************************************************************/

	final public static int DEFAULT_PORT = 5555;
	public static EchoServer sv;
	// public static String Log = new String();
	public static String ServerErrorConnection = "You Have to Connect to the Server.";
	public static String DatabaseErrorConnection = "You Have to Connect to the Database.";
	public static String GREEN_COLOR = "-fx-control-inner-background: #1AFE01";
	public static String RED_COLOR = "-fx-control-inner-background: RED";
	static ObservableList<Log> ObservableLogList;
	public static int ServerStopListening = 0;
	@SuppressWarnings("deprecation")
	public static Time time = new Time(0, 0, 0);

	Timeline NumberofClients = new Timeline();
	Timeline ServerRunTimeLine = new Timeline();
	Timeline CloackTimeLine = new Timeline();

	/*************************************************************
	 * 
	 * Description: FXML Variables
	 *
	 *
	 ************************************************************/

	@FXML
	private TextField ServerPortTextField;

	@FXML
	private Button ConnectToServerBtn;

	@FXML
	private Button DissconnectFromServerBtn;

	@FXML
	private Label ConnectionMessage;

	@FXML
	private TextField DataBaseSHostName;

	@FXML
	private TextField DataBaseSchemeName;

	@FXML
	private TextField DataBaseUserNameTextField;

	@FXML
	private TextField DatabsePasswordTextField;

	@FXML
	private Button ConnectToDatabaseBtn;

	@FXML
	private Label ConnectionMessage1;

	@FXML
	private TextField DatabaseStatus;

	@FXML
	private TextField NumberOfClients;

	@FXML
	private TextField ServerRunTime;

	@FXML
	private TextField ServerStatus;

	@FXML
	private TextField Cloack;

	@FXML
	private TableView<Log> ServerLogTable;

	@FXML
	private TableColumn<Log, String> LogTime;

	@FXML
	private TableColumn<Log, String> LogMsg;

	@FXML
	private Button DataBaseDisconnectionBtn;

	/*************************************************************
	 * 
	 * Description: Data base Connection Methods. Methods: SetConnectionToDB,
	 * StopConnectionWithDatabase
	 *
	 ************************************************************/

	@FXML
	void SetConnectionToDB(ActionEvent event) {
		String Result = mysqlConnection.SetmysqlConnection(DataBaseSHostName.getText(), DataBaseSchemeName.getText(),
				DataBaseUserNameTextField.getText(), DatabsePasswordTextField.getText());
		if (Result.contains("succeed")) {
			ConnectToDatabaseBtn.setDisable(true);
			DataBaseDisconnectionBtn.setDisable(false);
			DataBaseSHostName.setEditable(false);
			DataBaseSchemeName.setEditable(false);
			DataBaseUserNameTextField.setEditable(false);
			DatabsePasswordTextField.setEditable(false);
			ConnectionMessage1.setText("");
			DatabaseStatus.setText("Connected");
			DatabaseStatus.setStyle(GREEN_COLOR);
		} else {
			ConnectionMessage1.setText("One or more fields are wrong.");
		}
	}

	@FXML
	void StopConnectionWithDatabase(ActionEvent event) {
		if (mysqlConnection.CloseDBConnection()) {
			DataBaseDisconnectionBtn.setDisable(true);
			ConnectToDatabaseBtn.setDisable(false);
			DataBaseSHostName.setEditable(true);
			DataBaseSchemeName.setEditable(true);
			DataBaseUserNameTextField.setEditable(true);
			DatabsePasswordTextField.setEditable(true);
			ConnectionMessage1.setText("You Have to Connect to the Database.");
			DatabaseStatus.setText("Disconnected");
			DatabaseStatus.setStyle(RED_COLOR);
		} else {
			ConnectionMessage1.setText("Can not disconnected from the database");
		}
	}

	/*************************************************************
	 * 
	 * Description: Server Connection Methods. Methods: SetConnectionToServer,
	 * StopConnectionWithServer
	 *
	 ************************************************************/

	@SuppressWarnings("deprecation")
	@FXML
	void SetConnectionToServer(ActionEvent event) {
		if (ServerStopListening == 0) {
			setTimeLines();
			int port = 0;
			if ((Integer.parseInt(ServerPortTextField.getText()) / 1000) < 10
					&& (Integer.parseInt(ServerPortTextField.getText()) / 1000) >= 0) {
				port = Integer.parseInt(ServerPortTextField.getText());
			}

			else {
				port = DEFAULT_PORT; // Set port to 5555
			}

			sv = new EchoServer(port);
		}

		try {
			if (ServerStopListening == 1) {
				sv.serverStarted();
				ServerRunTimeLine.play();
				ServerStopListening = 0;
			} else {
				sv.listen(); // Start listening for connections
			}
			ServerStatus.setText("Connected");
			ServerStatus.setStyle(GREEN_COLOR);
			updateLog("Server listening for connections on port: " + sv.getPort() + ".");
			ConnectionMessage.setText("");
			ConnectToServerBtn.setDisable(true);
			DissconnectFromServerBtn.setDisable(false);

		} catch (Exception ex) {
			ServerRunTimeLine.pause();
			time.setHours(0);
			time.setMinutes(0);
			time.setSeconds(0);
			updateLog("ERROR - Could not listen for clients!");
		}
	}

	@SuppressWarnings("deprecation")
	@FXML
	void StopConnectionWithServer(ActionEvent event) throws IOException {
		ServerStatus.setText("Dissconnected");
		ServerStatus.setStyle(RED_COLOR);
		sv.close();
		ServerStopListening = 1;
		updateLog("Server has stopped listening for connections.");
		ConnectionMessage.setText(ServerErrorConnection);
		ConnectToServerBtn.setDisable(false);
		DissconnectFromServerBtn.setDisable(true);
		ServerRunTimeLine.pause();
		time.setHours(0);
		time.setMinutes(0);
		time.setSeconds(0);
	}

	/*************************************************************
	 * 
	 * Description: TimeLines Threads. Threads: ServerRunTimeLine, CloackTimeLine.
	 *
	 ************************************************************/

	@SuppressWarnings("deprecation")
	private void setTimeLines() {

		NumberofClients.setCycleCount(Timeline.INDEFINITE);
		NumberofClients.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (EventHandler<ActionEvent>) LogEvent -> {
			NumberOfClients.setText(Integer.toString(sv.getNumberOfClients()));
		}));
		NumberofClients.playFromStart();

		ServerRunTimeLine.setCycleCount(Timeline.INDEFINITE);
		ServerRunTimeLine.getKeyFrames()
				.add(new KeyFrame(Duration.seconds(1), (EventHandler<ActionEvent>) RunTimeEvent -> {
					time.setSeconds(time.getSeconds() + 1);
					if (time.getSeconds() > 59) {
						time.setSeconds(0);
						time.setMinutes(time.getMinutes() + 1);
						if (time.getMinutes() > 59) {
							time.setMinutes(0);
							time.setHours(time.getHours() + 1);
						}
					}

					ServerRunTime.setText(
							String.format("%02d", time.getHours()) + ":" + String.format("%02d", time.getMinutes())
									+ ":" + String.format("%02d", time.getSeconds()));
				}));
		ServerRunTimeLine.playFromStart();

		CloackTimeLine.setCycleCount(Timeline.INDEFINITE);
		CloackTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (EventHandler<ActionEvent>) CloackEvent -> {
			Cloack.setText(getCurrentTimeUsingCalendar());
		}));
		CloackTimeLine.playFromStart();

	}

	/*************************************************************
	 * 
	 * Description: Cloak method.
	 * 
	 *
	 ************************************************************/

	public static String getCurrentTimeUsingCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}

	/*************************************************************
	 * 
	 * Description: Log Method.
	 * 
	 *
	 ************************************************************/

	public static void updateLog(String log) {
		ObservableLogList.add(new Log(getCurrentTimeUsingCalendar(), log));
	}

	/*************************************************************
	 * 
	 * Description: Initialize.
	 * 
	 *
	 ************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableLogList = FXCollections.observableArrayList();
		ServerLogTable.setItems(ObservableLogList);
		LogTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
		LogMsg.setCellValueFactory(new PropertyValueFactory<>("Msg"));

	}

}
