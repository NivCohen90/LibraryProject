package OBLFX;

import java.time.LocalDate;
import java.util.ArrayList;

import SystemObjects.ReportData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import SystemObjects.ActivityReportData;
import SystemObjects.IGeneralData.operationsReturn;
import SystemObjects.IGeneralData.reportsType;

public class ReportDiaplayController implements IGUIcontroller {

	@FXML
	private Label ReportTypeLable;

	@FXML
	private TextField DemendedAVG;

	@FXML
	private TextField DemendedDistribution;

	@FXML
	private TextField DemendedMedian;

	@FXML
	private TextField RegularAVG;

	@FXML
	private TextField RegularDistribution;

	@FXML
	private TextField RegularMedian;

	@FXML
	private TextField GeneralAmountAVG;

	@FXML
	private TextField GeneralAmountDistribution;

	@FXML
	private TextField GeneralAmountMedian;

	@FXML
	private TextField GeneralDurationAVG;

	@FXML
	private TextField GeneralDurationDistribution;

	@FXML
	private TextField GeneralDurationMedian;

	@FXML
	private TableColumn<?, ?> BookName;

	@FXML
	private TableColumn<?, ?> BookAmountAVG;

	@FXML
	private TableColumn<?, ?> BookAmountDistribution;

	@FXML
	private TableColumn<?, ?> BookAmountMedian;

	@FXML
	private TableColumn<?, ?> BookDurationAVG;

	@FXML
	private TableColumn<?, ?> BookDurationDistribution;

	@FXML
	private TableColumn<?, ?> BookDurationMedian;

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

	public void setReportDataToDisplay(ArrayList<Object> reportData, reportsType reportType) {

		switch (reportType) {

		case loansReport: {
			for (int i = 0; i < reportData.size(); i++) {
				String reference = ((ReportData) reportData.get(i)).getReference();
				switch (reference) {

				case "Demended": {
					DemendedAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					DemendedDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					DemendedMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				case "Regular": {
					RegularAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					RegularDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					RegularMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				default:
					break;
				}

				break;
			}
		}

		case lateReturnsReport: {
			for (int i = 0; i < reportData.size(); i++) {
				String reference = ((ReportData) reportData.get(i)).getReference();
				switch (reference) {

				case "GeneralLatesAmount": {
					GeneralAmountAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					GeneralAmountDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					GeneralAmountMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				case "GeneralLatesDuration": {
					GeneralDurationAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					GeneralDurationDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					GeneralDurationMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				case "BookLatesAmount": {
					BookName.setText(((ReportData) reportData.get(i)).getBookName());
					BookAmountAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					BookAmountDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					BookAmountMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				case "BookLatesDuration": {
					BookDurationAVG.setText((((ReportData) reportData.get(i)).getAvg()) + "");
					BookDurationDistribution.setText((((ReportData) reportData.get(i)).getDistribution()) + "");
					BookDurationMedian.setText((((ReportData) reportData.get(i)).getMedian()) + "");
					break;
				}

				default:
					break;

				}
			}
			break;
		}

		case activityReport: {
			PeriodDates.setText(((((ActivityReportData) reportData.get(0)).getStartDate()) + "")
					+ ((((ActivityReportData) reportData.get(0)).getEndDate()) + ""));
			ActiveSubAmount.setText((((ActivityReportData) reportData.get(0)).getActiveSubscribersAmount()) + "");
			FreezedSubAmount.setText((((ActivityReportData) reportData.get(0)).getFreezedSubscribersAmount()) + "");
			UnActiveSubAmount.setText((((ActivityReportData) reportData.get(0)).getNotActiveSubscribersAmount()) + "");
			LoanedBooksAmount.setText((((ActivityReportData) reportData.get(0)).getLoanedBooksAmount()) + "");
			LateReturnsSubAmount.setText((((ActivityReportData) reportData.get(0)).getLateReturnsSubAmount()) + "");
			break;
		}
		
		default:
			break;
		}
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		// TODO Auto-generated method stub

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
