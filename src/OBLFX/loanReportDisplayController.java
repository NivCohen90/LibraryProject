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

public class loanReportDisplayController implements IGUIcontroller{

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
    private BarChart<?, ?> histogramChart;

    @FXML
    private CategoryAxis chartRange;

    @FXML
    private NumberAxis chartNumberOfValues;

    @FXML
    private BarChart<?, ?> histogramChart1;

    @FXML
    private CategoryAxis chartRange1;

    @FXML
    private NumberAxis chartNumberOfValues1;

	
	static ObservableList<Object> ObservableColumnData = FXCollections.observableArrayList();
	ArrayList<String> chartRanges;
	XYChart.Series series1, series2;
	 static String[] ranges1 = new String[10];
	 static String[] ranges2 = new String[10];

	public void initialize() {
	
		histogramChart1.setTitle("Regular Books Histogram:");
		histogramChart.setTitle("Demanded Books Histogram:");
		chartRange.setLabel("Range:");  
		chartRange1.setLabel("Range:");
		chartNumberOfValues.setLabel("Number Of Loaners");
		chartNumberOfValues1.setLabel("Number Of Loaners");
		series1 = new XYChart.Series();
        series1.setName("Amount");  
        series2 = new XYChart.Series();
        series2.setName("Amount");
        
	}

	public void setReportDataToDisplay(ArrayList<Object> reportData) {
		int prevValue;
		ReportData rd=null;
		reportReference reference;
		ArrayList<Integer> values=null;
		int[] dist=null;
		int rangeSize=0;
		
		for (int i = 0; i < reportData.size(); i++) {
			rd=(ReportData)reportData.get(i);
			prevValue=-1;
			reference = rd.getReference();
			if(rd.getAvg()!=0) {
			values=(ArrayList<Integer>)rd.getDistribution().get(0);
			rangeSize=(int)rd.getDistribution().get(1);
			dist=(int[])rd.getDistribution().get(2);}
			
			switch (reference) {

			case Demanded: 
				DemendedAVG.setText(rd.getAvg() + "");
				DemendedMedian.setText(rd.getMedian() + "");
				if(rd.getAvg()!=0) {
				prevValue+=values.get(0);
				for(int j=0;j<10;j++) {
					ranges2[i]=(prevValue+1)+"-"+(prevValue+rangeSize);
					prevValue+=rangeSize;
					series2.getData().add(new XYChart.Data(ranges2[i], dist[i]));
				}
				}
				continue;
			

			case Regular: 
				RegularAVG.setText(rd.getAvg() + "");
				RegularMedian.setText(rd.getMedian() + "");
				if(rd.getAvg()!=0) {
					prevValue+=values.get(0);
				for(int j=0;j<10;j++) {
					ranges1[i]=(prevValue+1)+"-"+(prevValue+rangeSize);
					prevValue+=rangeSize;
					series1.getData().add(new XYChart.Data(ranges1[i], dist[i]));
				}}
				continue;
}}}

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
		
	}}
