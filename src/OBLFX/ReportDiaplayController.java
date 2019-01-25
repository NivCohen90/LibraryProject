package OBLFX;

import java.util.ArrayList;

import SystemObjects.ReportData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
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

	public void setReportDataToDisplay(ArrayList<ReportData> reportData, reportsType reportType) {

		switch (reportType) {

		case loansReport: {
			for (int i = 0; i < reportData.size(); i++) {
				String reference = reportData.get(i).getReference();
				switch (reference) {

				case "Demended": {
					DemendedAVG.setText(toString(reportData.get(i).getAvg()));
					DemendedDistribution.setText(toString(reportData.get(i).getDistribution()));
					DemendedMedian.setText(toString(reportData.get(i).getMedian()));
					break;
				}

				case "Regular": {
					RegularAVG.setText(toString(reportData.get(i).getAvg()));
					RegularDistribution.setText(toString(reportData.get(i).getDistribution()));
					RegularMedian.setText(toString(reportData.get(i).getMedian()));
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
				String reference = reportData.get(i).getReference();
				switch (reference) {

				case "GeneralLatesAmount": {
					GeneralAmountAVG.setText(toString(reportData.get(i).getAvg()));
					GeneralAmountDistribution.setText(toString(reportData.get(i).getDistribution()));
					GeneralAmountMedian.setText(toString(reportData.get(i).getMedian()));
					break;
				}

				case "GeneralLatesDuration": {
					GeneralDurationAVG.setText(toString(reportData.get(i).getAvg()));
					GeneralDurationDistribution.setText(toString(reportData.get(i).getDistribution()));
					GeneralDurationMedian.setText(toString(reportData.get(i).getMedian()));
					break;
				}

				case "BookLatesAmount": {
					BookName.setText(reportData.get(i).getBookName());
					BookAmountAVG.setText(toString(reportData.get(i).getAvg()));
					BookAmountDistribution.setText(toString(reportData.get(i).getDistribution()));
					BookAmountMedian.setText(toString(reportData.get(i).getMedian()));
					break;
				}

				case "BookLatesDuration": {
					BookDurationAVG.setText(toString(reportData.get(i).getAvg()));
					BookDurationDistribution.setText(toString(reportData.get(i).getDistribution()));
					BookDurationMedian.setText(toString(reportData.get(i).getMedian()));
					break;
				}

				default:
					break;

				}
			}

			break;
		}
		
		case activityReport: {
			
		}
		}
	}

	private String toString(double num) {

		return toString((int) num);
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
