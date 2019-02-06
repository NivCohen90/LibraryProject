package OBLFX;

import java.util.ArrayList;

import Interfaces.IGUIcontroller;
import SystemObjects.LateReturnsReportBookData;
import SystemObjects.ReportData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.GeneralData.reportReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class lateReturnReportDisplayController implements IGUIcontroller{

	@FXML
	private Label ReportTypeLable;

	@FXML
	private TextField GeneralAmountAVG;

	@FXML
	private TextField GeneralAmountMedian;

	@FXML
	private TextField GeneralDurationAVG;

	@FXML
	private TextField GeneralDurationMedian;

	@FXML
	private TableView<Object> BookData;

	@FXML
	private TableColumn<?, ?> BookName;

	@FXML
	private TableColumn<?, ?> BookAmountAVG;

	@FXML
	private TableColumn<?, ?> BookAmountMedian;

	@FXML
	private TableColumn<?, ?> BookDurationAVG;

	@FXML
	private TableColumn<?, ?> BookDurationMedian;

	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();

	public void initialize() {
		BookData.setItems(ObservableColumnData);

		BookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		BookAmountAVG.setCellValueFactory(new PropertyValueFactory<>("AmountAvg"));
		BookAmountMedian.setCellValueFactory(new PropertyValueFactory<>("AmountMedian"));
		BookDurationAVG.setCellValueFactory(new PropertyValueFactory<>("DurationAvg"));
		BookDurationMedian.setCellValueFactory(new PropertyValueFactory<>("DurationMedian"));
	}

	public void setReportDataToDisplay(ArrayList<Object> reportData) {
//		ReportData rd = null;
//		reportReference reference;
//		ArrayList<Integer> values = null;
//		int[] dist = null;
//		int rangeSize = 0;
//		int prevValue;

		//for (int i = 0; i < reportData.size(); i++) {
////			prevValue = -1;
//			rd = (ReportData) reportData.get(i);
//			reference = rd.getReference();
////			rangeSize = (int) rd.getDistribution().get(1);
////			dist = (int[]) rd.getDistribution().get(2);
//			if (rd instanceof ReportData) {
//				reference = rd.getReference();
//				
//				switch (reference) {
//
//				case GeneralLatesAmount: {
					GeneralAmountAVG.setText("5");
					GeneralAmountMedian.setText("1");
//					break;
//				}

//				case GeneralLatesDuration: {
					GeneralDurationAVG.setText("5.6");
					GeneralDurationMedian.setText("3");
//					break;
//				}
//
//				default:
//					break;
//				}
		//	}

	//		else
					ReportData amount1= new ReportData(0.5,1, null, reportReference.BookLatesAmount);
					ReportData duration1=new ReportData(3,1,null, reportReference.BookLatesDuration);
					LateReturnsReportBookData book1=new LateReturnsReportBookData(amount1, duration1, "Introduction to discrete mathematics");
					ObservableColumnData.add(book1);
					ReportData amount2= new ReportData(6,2, null, reportReference.BookLatesAmount);
					ReportData duration2=new ReportData(16,10,null, reportReference.BookLatesDuration);
					LateReturnsReportBookData book2=new LateReturnsReportBookData(amount2, duration2, "Fundamental university physics");
					ObservableColumnData.add(book2);
					ReportData amount3= new ReportData(3,1, null, reportReference.BookLatesAmount);
					ReportData duration3=new ReportData(20,17,null, reportReference.BookLatesDuration);
					LateReturnsReportBookData book3=new LateReturnsReportBookData(amount3, duration3, "Elementary differential equations");
					ObservableColumnData.add(book3);
					ReportData amount5= new ReportData(3,2, null, reportReference.BookLatesAmount);
					ReportData duration5=new ReportData(13,15,null, reportReference.BookLatesDuration);
					LateReturnsReportBookData book5=new LateReturnsReportBookData(amount5, duration5, "Software engineering");
					ObservableColumnData.add(book5);
					ReportData amount6= new ReportData(10,12, null, reportReference.BookLatesAmount);
					ReportData duration6=new ReportData(7,6,null, reportReference.BookLatesDuration);
					LateReturnsReportBookData book6=new LateReturnsReportBookData(amount6, duration6, "Digital design");
					ObservableColumnData.add(book6);
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
