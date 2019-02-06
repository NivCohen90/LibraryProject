package OBLFX;

import java.util.ArrayList;

import Interfaces.IGUIcontroller;
import SystemObjects.ReportData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.GeneralData.reportReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class loanReportDisplayController implements IGUIcontroller {

	@FXML
	private Label ReportTypeLable;

	@FXML
	private TextField DemendedAVG;

	@FXML
	private TextField DemendedMedian;

	@FXML
	private TextField RegularAVG;

	@FXML
	private TextField RegularMedian;

	@FXML
	private BarChart<?, ?> DemandedHistogramChart;

	@FXML
	private CategoryAxis DemandedX;

	@FXML
	private NumberAxis DemandedY;

	@FXML
	private BarChart<?, ?> regularHistogramChart;

	@FXML
	private CategoryAxis regularX;

	@FXML
	private NumberAxis regularY;

	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
//	static String[] ranges1 = new String[10];
//	static String[] ranges2 = new String[10];
//	static String ranger1 = "0-1";
//	static String ranger2 = "2-3";
//	static String ranger3 = "4-5";
//	static String ranger4 = "6-7";
//	static String ranger5 = "8-9";
//	static String ranger6 = "10-11";
//	static String ranger7 = "12-13";
//	static String ranger8 = "14-15";
//	static String ranger9 = "16-17";
//	static String ranger10 = "18-19";
//	static String ranged1 = "0";
//	static String ranged2 = "1";
//	static String ranged3 = "2";
//	static String ranged4 = "3";
//	static String ranged5 = "4";
//	static String ranged6 = "5";
//	static String ranged7 = "6";
//	static String ranged8 = "7";
//	static String ranged9 = "8";
//	static String ranged10= "9";

//	regularHistogramChart = new BarChart<String, Number>(regularX, regularY);
//	DemandedHistogramChart= new BarChart<String, Number>(DemandedX, DemandedY);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {
		XYChart.Series seriesr=new XYChart.Series<>();
		XYChart.Series seriesd=new XYChart.Series<>();
		regularHistogramChart.setTitle("Regular Books Histogram:");
		DemandedHistogramChart.setTitle("Demanded Books Histogram:");
		DemandedX.setLabel("Range(in days):");
		regularX.setLabel("Range(in days):");
		DemandedY.setLabel("Number Of Loaners");
		regularY.setLabel("Number Of Loaners");
		seriesr.setName("Regular Books Amount");
		seriesd.setName("Demanded Books Amount");
		
		seriesd.getData().add(new XYChart.Data("0", 0));
		seriesd.getData().add(new XYChart.Data("1", 1));
		seriesd.getData().add(new XYChart.Data("2", 1));
		seriesd.getData().add(new XYChart.Data("3", 2));
		seriesd.getData().add(new XYChart.Data("4", 0));
		seriesd.getData().add(new XYChart.Data("5", 0));
		seriesd.getData().add(new XYChart.Data("6", 0));
		seriesd.getData().add(new XYChart.Data("7", 0));
		seriesd.getData().add(new XYChart.Data("8", 0));
		seriesd.getData().add(new XYChart.Data("9", 0));

		seriesr.getData().add(new XYChart.Data("0-1", 0));
		seriesr.getData().add(new XYChart.Data("2-3", 1));
		seriesr.getData().add(new XYChart.Data("4-5", 0));
		seriesr.getData().add(new XYChart.Data("6-7", 1));
		seriesr.getData().add(new XYChart.Data("8-9", 0));
		seriesr.getData().add(new XYChart.Data("10-11", 0));
		seriesr.getData().add(new XYChart.Data("12-13", 0));
		seriesr.getData().add(new XYChart.Data("14-15", 2));
		seriesr.getData().add(new XYChart.Data("16-17", 1));
		seriesr.getData().add(new XYChart.Data("18-19", 0));
		
		regularHistogramChart.getData().addAll(seriesr);
		DemandedHistogramChart.getData().addAll(seriesd);
	}

	public void setReportDataToDisplay(ArrayList<Object> reportData) {
		int prevValue;
		ReportData rd = null;
		reportReference reference;
		ArrayList<Integer> values = null;
		int[] dist = null;
		int rangeSize = 0;

		for (int i = 0; i < reportData.size(); i++) {
			rd = (ReportData) reportData.get(i);
			prevValue = -1;
			reference = rd.getReference();
			if (rd.getAvg() != 0) {
				values = (ArrayList<Integer>) rd.getDistribution().get(0);
				rangeSize = (int) rd.getDistribution().get(1);
				dist = (int[]) rd.getDistribution().get(2);
			}

			switch (reference) {

			case Demanded:
				DemendedAVG.setText(rd.getAvg() + "");
				DemendedMedian.setText(rd.getMedian() + "");
//				if (rd.getAvg() != 0) {
//					prevValue += values.get(0);
//					for (int j = 0; j < 10; j++) {
//						rangesd[i] = (prevValue + 1) + "-" + (prevValue + rangeSize);
//						prevValue += rangeSize;
//						seriesd.getData().add(new XYChart.Data(ranges2[i], dist[i]));

				continue;

			case Regular:
				RegularAVG.setText(rd.getAvg() + "");
				RegularMedian.setText(rd.getMedian() + "");
//				if(rd.getAvg()!=0) {
//					prevValue+=values.get(0);
//				for(int j=0;j<10;j++) {
//					ranges1[i]=(prevValue+1)+"-"+(prevValue+rangeSize);
//					prevValue+=rangeSize;
//					series1.getData().add(new XYChart.Data(ranges1[i], dist[i]));


				continue;
			default:
				break;
			}
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
