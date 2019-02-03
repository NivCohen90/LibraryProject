package OBLFX;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import Client.LibraryManagerHandler;
import Interfaces.IGUIcontroller;

import SystemObjects.GeneralData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreatesReportController implements IGUIcontroller {

	private LibraryManagerHandler commonClient;

	@FXML
	private Text StartDateLabale;

	@FXML
	private Button CreateReportButton;

	@FXML
	private Text EndDateLable;

	@FXML
	private DatePicker startDateCombo;

	@FXML
	private DatePicker EndDateCombo;

	@FXML
	private RadioButton ActivityReport;

	@FXML
	private ToggleGroup Reports;

	@FXML
	private RadioButton loansReport;

	@FXML
	private RadioButton lateReturnReport;

	@FXML
	void activityReport(ActionEvent event) {

		StartDateLabale.setVisible(true);
		EndDateLable.setVisible(true);
		startDateCombo.setVisible(true);
		EndDateCombo.setVisible(true);
	}

	@FXML
	void LoanLateReturnReport(ActionEvent event) {

		StartDateLabale.setVisible(false);
		EndDateLable.setVisible(false);
		startDateCombo.setVisible(false);
		EndDateCombo.setVisible(false);
	}

	@FXML
	void createReport(ActionEvent event) {

		LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Stage primaryStage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader();
		AnchorPane root = null;
		Scene scene = null;
		
		if (ActivityReport.isSelected()) {
			ArrayList<LocalDate> dates=new ArrayList<LocalDate>();

			dates.add(startDateCombo.getValue());
			dates.add(EndDateCombo.getValue());
			try {
				root = (AnchorPane) fxmlLoader
						.load(getClass().getResource("../FXML/ActivityReportDisplay.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scene = new Scene(root);
			activityReportDisplayController Controller = (activityReportDisplayController) fxmlLoader
					.getController();
			Controller.DataToDisplay(dates);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
//			commonClient.createReport(sDate, eDate, GeneralData.operations.createActivityReport);
		}

		else if (loansReport.isSelected())
			commonClient.createReport(currentDate, null, GeneralData.operations.createLoansReport);

		
		else if (lateReturnReport.isSelected()) {
			try {
				root = (AnchorPane) fxmlLoader
						.load(getClass().getResource("../FXML/LateReturnReportDisplay.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scene = new Scene(root);
			lateReturnReportDisplayController Controller = (lateReturnReportDisplayController) fxmlLoader
					.getController();
			Controller.setReportDataToDisplay(null);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
	}}

	private void openResultDetails(Object reportData, operationsReturn reportType) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader();
			AnchorPane root = null;
			Scene scene = null;
			System.out.println(reportType);
			switch (reportType) {

			case returnLoanReportData: {
				root = (AnchorPane) fxmlLoader
						.load(getClass().getResource("../FXML/LoanReportDisplay.fxml").openStream());
				scene = new Scene(root);
				loanReportDisplayController Controller = (loanReportDisplayController) fxmlLoader.getController();
				Controller.setReportDataToDisplay((ArrayList<Object>) reportData);
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();
				break;
			}
//			case returnLateReturnsReportData: {
//				System.out.println("I'm here you mother fucker!");
//				root = (AnchorPane) fxmlLoader
//						.load(getClass().getResource("../FXML/LateReturnReportDisplay.fxml").openStream());
//				scene = new Scene(root);
//				lateReturnReportDisplayController Controller = (lateReturnReportDisplayController) fxmlLoader
//						.getController();
//				Controller.setReportDataToDisplay((ArrayList<Object>) reportData);
//				primaryStage.setScene(scene);
//				primaryStage.setResizable(false);
//				primaryStage.show();
//				break;
//			}
//			case returnActivityReportData: {
//				root = (AnchorPane) fxmlLoader
//						.load(getClass().getResource("../FXML/ActivityReportDisplay.fxml").openStream());
//				scene = new Scene(root);
//				activityReportDisplayController Controller = (activityReportDisplayController) fxmlLoader
//						.getController();
//				Controller.DataToDisplay((ArrayList<Object>) reportData);
//				primaryStage.setScene(scene);
//				primaryStage.setResizable(false);
//				primaryStage.show();
//				break;
//			}
			default:
				break;
			}

		}

		catch (Exception e) {
			Interfaces.IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection() {
		commonClient = new LibraryManagerHandler(this);
	}

	@Override
	public void closeConnection() {
		if (commonClient != null)
			commonClient.quit();
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		System.out.println(op);
		openResultDetails(msg, op);

	}
}
