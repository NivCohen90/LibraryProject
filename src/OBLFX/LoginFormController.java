package OBLFX;

import Users.IGeneralData.operationsReturn;
import OBLFX.IGUIcontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginFormController implements IGUIcontroller{

    @FXML
    private Button LoginBTN;

    @FXML
    void Login(ActionEvent event) {

    }

	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		// TODO Auto-generated method stub
		
	}
}
