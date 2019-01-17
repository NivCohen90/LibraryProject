package OBLFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ReportFaultController {

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

}

