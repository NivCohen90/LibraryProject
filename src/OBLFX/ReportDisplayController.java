package OBLFX;

import java.util.ArrayList;

import Interfaces.IGUIcontroller;
<<<<<<< HEAD
import Interfaces.IGeneralData.operationsReturn;
=======
>>>>>>> branch 'master' of https://github.com/NivCohen90/LibraryProject.git
import SystemObjects.ReportData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.GeneralData.reportsType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import SystemObjects.ActivityReportData;
import SystemObjects.LateReturnsReportBookData;

public class ReportDisplayController implements IGUIcontroller {

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
    private TableView<Object> BookData;

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

	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();

	public void initialize() {
		BookData.setItems(ObservableColumnData);
		
		BookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		BookAmountAVG.setCellValueFactory(new PropertyValueFactory<>("AmountAvg"));
		BookAmountDistribution.setCellValueFactory(new PropertyValueFactory<>("AmountDistribution"));
		BookAmountMedian.setCellValueFactory(new PropertyValueFactory<>("AmountMedian"));
		BookDurationAVG.setCellValueFactory(new PropertyValueFactory<>("DurationAvg"));
		BookDurationDistribution.setCellValueFactory(new PropertyValueFactory<>("DurationDistribution"));
		BookDurationMedian.setCellValueFactory(new PropertyValueFactory<>("DurationMedian"));
	}

	
	public void setReportDataToDisplay(ArrayList<Object> reportData, operationsReturn reportType) {

		String reference;
		
		switch (reportType) {
		case returnLoanReportData: {
			for (int i = 0; i < reportData.size(); i++) {
				reference = ((ReportData) reportData.get(i)).getReference();
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

		case returnLateReturnsReportData: {
			for (int i = 0; i < reportData.size(); i++) {
				
				if (reportData.get(i) instanceof ReportData) {
					reference = ((ReportData) reportData.get(i)).getReference();
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

					default:
						break;
					}
				}
				
				else ObservableColumnData.add(((LateReturnsReportBookData) reportData.get(i)));
			}
			break;
		}

		case returnActivityReportData: {
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
