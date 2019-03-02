package Testing;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.Socket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Client.CommonHandler;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;




public class LoginTesting {
	
	static StubEchoServer stubedServer;
	static StubLoginControler stubedControler;
	static CommonHandler hendler;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//preventing null pointer exception when a user logs in and should see his main screen.
		stubedServer = new StubEchoServer(StubEchoServer.DEFAULT_PORT);
        stubedControler=new StubLoginControler();
		hendler = new CommonHandler(stubedControler, InetAddress.getLocalHost().toString(),stubedServer.getPort());
		stubedControler.setConnection();
		StubConnectionToClient stubedConector= new StubConnectionToClient(hendler,stubedServer);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		//making sure no test depends on another. creating different stub client and server for each test

	}

	@After
	public void tearDown() throws Exception {}
	
	@Test
    public void loginSubscriberSuccessfulTest() {

        String username = "999999995";
        String password = "kirankadosh!";
        hendler.loginUser(username, password);
        String expected="Subscriber "+username+" "+password;
        
        String massage = stubedControler.GetMassage();

        if (massage != null) {
            assertEquals(expected,massage);
        }
    }
	@Test
    public void loginLibrarianSuccessfulTest() {

        String username = "999999993";
        String password = "mynameisnotmimer";
        hendler.loginUser(username, password);
        String expected="Librarian "+username+" "+password;
        
        String massage = stubedControler.GetMassage();

        if (massage != null) {
            assertEquals(expected,massage);
        }
    }
	@Test
    public void loginLibrarianManagerSuccessfulTest() {

        String username = "999999992";
        String password = "Eihhemakzooti";
        hendler.loginUser(username, password);
        String expected="Librarian Manager "+username+" "+password;
        
        String massage = stubedControler.GetMassage();

        if (massage != null) {
            assertEquals(expected,massage);
        }
    }
	@Test
    public void loginRightIdWrongPasswordTest() {

        String username = "999999995";
        String password = "Eihhemoti";
        hendler.loginUser(username, password);
        String expected="Can't Login - the Username/Password are wrong.";
        
        String massage = stubedControler.GetMassage();

        if (massage != null) {
            assertEquals(expected,massage);
        }
    }
	@Test
    public void loginWrongIdRightPasswordTest() {

        String username = "999998995";
        String password = "kirankadosh!";
        hendler.loginUser(username, password);
        String expected="Can't Login - the Username/Password are wrong.";
        
        String massage = stubedControler.GetMassage();

        if (massage != null) {
            assertEquals(expected,massage);
        }
    }
}
