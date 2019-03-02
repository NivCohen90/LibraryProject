package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Client.CommonHandler;
import javafx.embed.swing.JFXPanel;

public class CommonHandlerTest {
	
	GUIStub guiStub;
	AbstractClientStub clientStub;
	ConnectionToClientStub connectionClientStub;
	EchoServerStub serverStub;
	LoginQuerisStub loginQueriesStub;
	CommonHandler testCommonHandler;
	
	@Before
	public void setUp() throws Exception {
		guiStub = new GUIStub();
		connectionClientStub = new ConnectionToClientStub();
		serverStub = new EchoServerStub();
		clientStub = new AbstractClientStub(serverStub, connectionClientStub);
		testCommonHandler = new CommonHandler(guiStub, clientStub);
		serverStub.setCommonHandler(testCommonHandler);
		connectionClientStub.setClient(testCommonHandler);
		JFXPanel fxPanel = new JFXPanel();		//for static ConnectionSettingsController, IAlert,...
	}

	@Test
	public final void loginSubscriberSuccessfulTest() {
		testCommonHandler.loginUser("999999996", "Gate5");
		//after login method, if user logged in, user data in guiStub.loggedinSubscriber
		String actualResult = guiStub.loggedinSubscriber.getID()+guiStub.loggedinSubscriber.getPassword();
		String expectedResult = "999999996Gate5";
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
    public void loginLibrarianSuccessfulTest() {
		testCommonHandler.loginUser("999999993", "mynameisnotmimer");
		String actualResult = guiStub.loggedinLibrarian.getID()+guiStub.loggedinLibrarian.getPassword();
        String expectedResult = "999999993mynameisnotmimer";
        
		assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void loginLibrarianManagerSuccessfulTest() {
		testCommonHandler.loginUser("999999992", "Eihhemakzooti");
		String actualResult = guiStub.loggedinLibrarian.getID()+guiStub.loggedinLibrarian.getPassword();
        String expectedResult = "999999992Eihhemakzooti";
        
		assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void loginRightIdWrongPasswordTest() {
		testCommonHandler.loginUser("999999992", "1");
		String actualResult = guiStub.errorMessage;
        String expectedResult = "Can't Login - the Username/Password are wrong.";
        
        assertNull(guiStub.loggedinLibrarian); 		//didn't add info for librarian
		assertEquals(expectedResult, actualResult);
    }
	@Test
    public void loginWrongIdRightPasswordTest() {

		testCommonHandler.loginUser("1", "Eihhemakzooti");
		String actualResult = guiStub.errorMessage;
        String expectedResult = "Can't Login - the Username/Password are wrong.";
        
        assertNull(guiStub.loggedinLibrarian); 		//didn't add info for librarian
		assertEquals(expectedResult, actualResult);
    }

}
