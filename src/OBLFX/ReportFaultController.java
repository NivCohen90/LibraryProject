package OBLFX;

import Interfaces.IGUIcontroller;
import Interfaces.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReportFaultController implements IGUIcontroller {

    @FXML
    private TextField IDTextField;

    @FXML
    private TextArea FaultDescription;

    @FXML
    private TextField CatalogNumberTextField;

    @FXML
    private DatePicker DatePicker;

    @FXML
    private ComboBox<?> FaultType;

    @FXML
    void CommitReport(ActionEvent event) {

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

