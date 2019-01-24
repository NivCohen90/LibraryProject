package OBLFX;

import Client.CommonHandler;
import Client.LibrarianHandler;
import Users.Book;
import Users.IGeneralData;
<<<<<<< Upstream, based on branch 'master' of https://github.com/NivCohen90/LibraryProject.git
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * AddBookCopyController controls AddnewcopyFXML
 */
public class AddBookCopyController implements IGUIcontroller {
	@SuppressWarnings("unused")
	private LibrarianHandler librarianClient;
	private CommonHandler commonClient;
	@SuppressWarnings("unused")
	private static Book book;

	@FXML
	private TextField CatalogTextField;

	@FXML
	private TextField BookNameTextField;

	@FXML
	private Label NumberOfCopiesTitle;

	@FXML
	private TextField NumberOfCopiesTextField;

	@FXML
	private Label CatalogLabel;

	@FXML
	private Label RetriveMSG;

	@FXML
	private Label NumberOfCopiesTitle1;

	@FXML
	private TextField AddcopiesTextField;

	@FXML
	private Label AddCopiesLabel;

	@FXML
	void AddCopiesAction(ActionEvent event) {
		if (IGUIcontroller.CheckOnlyLetter(AddcopiesTextField, AddCopiesLabel, OnlyNumbers, UserNameErrorNumebrs)
				&& IGUIcontroller.CheckIfUserPutInput(AddcopiesTextField, AddCopiesLabel)) {
			if (IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs)
					&& IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogLabel)) {
				//librarianClient.addBookCopyToCatalog(CatalogTextField.getText(), AddcopiesTextField.getText(),
				//		new Librarian());
			}
		}

	}

	/**
	 * CatalogNumberCheck is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9].
	 */
	@FXML
	void CatalogNumberCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogLabel);
	}

	/**
	 * GetDetailsAction is a method that check if the user put input.,if he didn't
	 * gave input the method will alert the user. this method also check that all
	 * this field only contains this letters[0-9] and it send request to the server.
	 */
	@FXML
	void GetDetailsAction(ActionEvent event) {
		if (IGUIcontroller.CheckIfUserPutInput(CatalogTextField, CatalogLabel)
				&& IGUIcontroller.CheckOnlyLetter(CatalogTextField, CatalogLabel, OnlyNumbers, UserNameErrorNumebrs)) {
			commonClient.searchInServer(CatalogTextField.getText(), IGeneralData.operations.searchByCatalogNumber);
		}
	}

	/**
	 * NumberOfaddedCopiesCheck is a method that check if the user put input.,if he
	 * didn't gave input the method will alert the user. this method also check that
	 * all this field only contains this letters[0-9] and it send request to the
	 * server.
	 */
	@FXML
	void NumberOfaddedCopiesCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(AddcopiesTextField, AddCopiesLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(AddcopiesTextField, AddCopiesLabel);
	}

	/**
	 * Update User with the result
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
		switch (op) {
		case returnBook:
			// BookNameTextField.setText(msg.getBookName());
			// NumberOfCopiesTextField.setText(msg.getNumberOfLibraryCopies());

			break;

		case returnError:
			RetriveMSG.setText((String) msg);
			break;
		}
	}

	@Override
	public void setConnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

=======
import Users.Librarian;
import Users.IGeneralData.operationsReturn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * AddBookCopyController controls AddnewcopyFXML
 */
public class AddBookCopyController implements IGUIcontroller {
	private LibrarianHandler librarianClient;
	private CommonHandler commonClient;
	private static Book book;
	
	@FXML
    private TextField CatalogTextField;

    @FXML
    private TextField BookNameTextField;

    @FXML
    private Label NumberOfCopiesTitle;

    @FXML
    private TextField NumberOfCopiesTextField;

    @FXML
    private Label CatalogLabel;

    @FXML
    private Label RetriveMSG;

    @FXML
    private Label NumberOfCopiesTitle1;

    @FXML
    private TextField AddcopiesTextField;

    @FXML
    private Label AddCopiesLabel;

    @FXML
    void AddCopiesAction(ActionEvent event) {
    if(IGUIcontroller.CheckOnlyLetter(AddcopiesTextField, AddCopiesLabel, OnlyNumbers, UserNameErrorNumebrs) &&	IGUIcontroller.CheckIfUserPutInput(AddcopiesTextField, AddCopiesLabel)) {
    if(IGUIcontroller.CheckOnlyLetter(CatalogTextField,CatalogLabel,OnlyNumbers,UserNameErrorNumebrs) && IGUIcontroller.CheckIfUserPutInput(CatalogTextField,CatalogLabel)){
    	librarianClient.addBookCopyToCatalog(CatalogTextField.getText(),AddcopiesTextField.getText() ,new Librarian());	
    }
    }

    }
    /**
     * CatalogNumberCheck is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9].
    */
    @FXML
    void CatalogNumberCheck(KeyEvent event) {
    	IGUIcontroller.CheckOnlyLetter(CatalogTextField,CatalogLabel,OnlyNumbers,UserNameErrorNumebrs);
    	IGUIcontroller.CheckIfUserPutInput(CatalogTextField,CatalogLabel); 
    }
    /**
     * GetDetailsAction is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9] and it send request to the server.
    */
    @FXML
    void GetDetailsAction(ActionEvent event) {
if(IGUIcontroller.CheckIfUserPutInput(CatalogTextField,CatalogLabel) && IGUIcontroller.CheckOnlyLetter(CatalogTextField,CatalogLabel,OnlyNumbers,UserNameErrorNumebrs)) {
	commonClient.searchInServer(CatalogTextField.getText(), IGeneralData.operations.searchByCatalogNumber);
}
    }
    /**
     * NumberOfaddedCopiesCheck is a method that check if the user put input.,if he didn't gave input the method will alert the user.
     * this method also check that all this field only contains this letters[0-9] and it send request to the server.
    */
    @FXML
    void NumberOfaddedCopiesCheck(KeyEvent event) {
		IGUIcontroller.CheckOnlyLetter(AddcopiesTextField, AddCopiesLabel, OnlyNumbers, UserNameErrorNumebrs);
		IGUIcontroller.CheckIfUserPutInput(AddcopiesTextField, AddCopiesLabel);
    }
    /**
     * Update User with the result
    */  
	@Override
	public void receiveMassageFromServer(Object msg, operationsReturn op) {
	switch(op) {
	case returnBook:
		// BookNameTextField.setText(msg.getBookName());
		// NumberOfCopiesTextField.setText(msg.getNumberOfLibraryCopies());

	break;
	
	case returnError:
		RetriveMSG.setText((String) msg);
		break;
	}
>>>>>>> 5487fbb AAA
	}

}
