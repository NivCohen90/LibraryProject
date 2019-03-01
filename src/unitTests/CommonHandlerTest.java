package unitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Client.CommonHandler;
import javafx.embed.swing.JFXPanel;

public class CommonHandlerTest {
	
	GUIStub guiStub;
	AbstractClientStub clientStub;
	EchoServerStub serverStub;
	LoginQuerisStub loginQueriesStub;
	CommonHandler testCommonHandler;
	
	@Before
	public void setUp() throws Exception {
		guiStub = new GUIStub();
		serverStub = new EchoServerStub();
		clientStub = new AbstractClientStub(serverStub);
		testCommonHandler = new CommonHandler(guiStub, clientStub);
		serverStub.setCommonHandler(testCommonHandler);
		JFXPanel fxPanel = new JFXPanel();		//for static ConnectionSettingsController, IAlert,...
	}

	@Test
	public final void LoginUserSubscriberSuccess() {
		testCommonHandler.loginUser("999999996", "Gate5");
		//after login method, if user logged in, user data in guiStub.loggedinSubscriber
		String actualResult = guiStub.loggedinSubscriber.getID()+guiStub.loggedinSubscriber.getPassword();
		String expectedResult = "999999996Gate5";
		assertEquals(expectedResult, actualResult);
	}

}
