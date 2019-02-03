package OBLFX;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Interfaces.IGUIcontroller;
import SystemObjects.ActivityReportData;
import SystemObjects.GeneralData.operationsReturn;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class activityReportDisplayController implements IGUIcontroller {

	@FXML
	private TextField ActiveSubAmount;

	@FXML
	private TextField FreezedSubAmount;

	@FXML
	private TextField UnActiveSubAmount;

	@FXML
	private TextField LoanedBooksAmount;

	@FXML
	private TextField LateReturnsSubAmount;

	@FXML
	private Text PeriodDates;

	public void DataToDisplay(ArrayList<LocalDate> reportData) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = reportData.get(0).format(formatter);
		String formattedString2 = reportData.get(1).format(formatter);
		PeriodDates.setText(formattedString+"-"+formattedString2);
//		PeriodDates.setText(((((ActivityReportData) reportData.get(0)).getStartDate()) + "")
//				+ ((((ActivityReportData) reportData.get(0)).getEndDate()) + ""));
//		ActiveSubAmount.setText((((ActivityReportData) reportData.get(0)).getActiveSubscribersAmount()) + "");
//		FreezedSubAmount.setText((((ActivityReportData) reportData.get(0)).getFreezedSubscribersAmount()) + "");
//		UnActiveSubAmount.setText((((ActivityReportData) reportData.get(0)).getNotActiveSubscribersAmount()) + "");
//		LoanedBooksAmount.setText((((ActivityReportData) reportData.get(0)).getLoanedBooksAmount()) + "");
//		LateReturnsSubAmount.setText((((ActivityReportData) reportData.get(0)).getLateReturnsSubAmount()) + "");
		
		ActiveSubAmount.setText("19");
		FreezedSubAmount.setText("4");
		UnActiveSubAmount.setText("2");
		LoanedBooksAmount.setText("16");
		LateReturnsSubAmount.setText("1");
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {

	}

	@Override
	public void setConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}
}
