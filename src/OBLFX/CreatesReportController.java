package OBLFX;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import Client.LibraryManagerHandler;

import SystemObjects.IGeneralData;
import SystemObjects.IGeneralData.operationsReturn;
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
    void ActivityReport(ActionEvent event) {
    	
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
		
		LocalDate currentDate= new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		if (ActivityReport.isSelected())
		{			
			LocalDate sDate=startDateCombo.getValue();
			LocalDate eDate=EndDateCombo.getValue();
				
			commonClient.createReport(sDate, eDate, IGeneralData.operations.createActivityReport);
		}
		
		else if (loansReport.isSelected())
				commonClient.createReport(currentDate, null,  IGeneralData.operations.createLoansReport);
		
		else if (lateReturnReport.isSelected())
			commonClient.createReport(currentDate, null,  IGeneralData.operations.createLateReturnsReport);
			
	}
	
//    private void openResultDetails(Object choosenReport)
//	{
//    	Stage primaryStage = new Stage();
//    	FXMLLoader fxmlLoader = new FXMLLoader();
//    	AnchorPane root = null;
//    	Scene scene = null;
//		try {
//			
//			if(choosenReport instanceof reportData)
//			{
//				root = (AnchorPane) fxmlLoader.load(getClass().getResource("../FXML/BookDetails.fxml").openStream());
//				scene = new Scene(root);
//				BookDetailsController Controller = (BookDetailsController) fxmlLoader.getController();
//				Controller.setBookToDisplay((Book)choosenResult);
//				primaryStage.setTitle(((Book)choosenResult).getBookName());
//			}		
//		
//			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
    
	@Override
	public void setConnection() {	
		commonClient = new LibraryManagerHandler(this);
	}
	
	@Override
	public void closeConnection() {	
		if(commonClient!=null)
			commonClient.quit();
	}

	@Override
	public <T> void receiveMassageFromServer(T msg, operationsReturn op) {
		
		
	}
}

