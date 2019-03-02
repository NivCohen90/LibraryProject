package Testing;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import Interfaces.IAlert;
import Interfaces.IConnectionToClient;
import Server.BookQueries;
import Server.CatalogQueries;
import Server.EchoServer;
import Server.LoanQueries;
import Server.LoginQueris;
import Server.ReportQueries;
import Server.SearchBooksQueries;
import Server.SearchUserQueries;
import Server.ServerController;
import Server.SubscriberQueries;
import Server.mysqlConnection;
import SystemObjects.Book;
import SystemObjects.LateReturnReportData;
import SystemObjects.LateReturnsReportBookData;
import SystemObjects.Loan;
import SystemObjects.Order;
import SystemObjects.ReportData;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.GeneralData.reportReference;
import Users.Librarian;
import Users.Subscriber;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class StubEchoServer extends AbstractServer {

	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public StubEchoServer(int port) {
		super(port);

	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @throws SQLException
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		switch (((ServerData) msg).getOperation()) {
		case Login:
			ServerData result;
			String id=(String)(((ServerData) msg).getDataMsg()).get(0);
			String password=(String)(((ServerData) msg).getDataMsg()).get(1);
			switch (id) {
			case "999999992"://manager
				try {
					if(password=="Eihhemakzooti")
					{					
						Librarian lib = new Librarian("", "","",id,password,"","",2);
						ArrayList<Object> librarianManagers = new ArrayList<Object>();
						librarianManagers.add(lib);
						 result = new ServerData(librarianManagers, operationsReturn.returnLibrarianManager);
					}
					else {
						ArrayList<Object> Error = new ArrayList<Object>();
						Error.add("Can't Login - the Username/Password are wrong.");
						 result = new ServerData(Error, operationsReturn.returnError);
					}
					client.sendToClient(result);
				} catch (IOException e) {
					IAlert.ExceptionAlert(e);
					e.printStackTrace();
				}				
				break;
			case "999999993"://librarian
				try {
					if(password=="mynameisnotmimer")
					{
						Librarian lib = new Librarian("", "","",id,password,"","",1);
						ArrayList<Object> librarians = new ArrayList<Object>();
						librarians.add(lib);
					    result = new ServerData(librarians, operationsReturn.returnLibrarian);
					}
					else {
						ArrayList<Object> Error = new ArrayList<Object>();
						Error.add("Can't Login - the Username/Password are wrong.");
						 result = new ServerData(Error, operationsReturn.returnError);
					}
					client.sendToClient(result);
			    } catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			    }
				break;
			case "999999995"://subscriber
				try {
					if(password=="kirankadosh!")
					{
						Subscriber Sub = new Subscriber(id,"","","","", password);
						ArrayList<Object> subs = new ArrayList<Object>();
						subs.add(Sub);
						 result = new ServerData(subs, operationsReturn.returnSubscriber);
					}
					else {
						ArrayList<Object> Error = new ArrayList<Object>();
						Error.add("Can't Login - the Username/Password are wrong.");
						 result = new ServerData(Error, operationsReturn.returnError);
					}
					client.sendToClient(result);
			    } catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			    }
				break;
			default:
				try {					
					ArrayList<Object> Error = new ArrayList<Object>();
					Error.add("Can't Login - the Username/Password are wrong.");
				    result = new ServerData(Error, operationsReturn.returnError);					
					client.sendToClient(result);
			    } catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			    }
				break;
			
			}


			break;
		case searchByBookName:

			break;
		case searchByBookAuthor:

			break;
		case searchByBookSubject:

			break;
		case searchByBookDescription:

			break;
		case searchByCatalogNumber:

			break;
		case updateSubscriberDetails:

			break;
		case orderBook:

			break;

		case extandLoan:
			
			break;

		case viewActiveLoans:
			break;
		case viewActivityHistory:
			break;
		case updateReturnDateManualy:
			

			break;
		case returnBook:
			
			break;

		case watchReadersCard:
			break;
		case CreateNewSubscriber:
			
			break;
		case ManageCatalog:
			break;
		case watchEmployeesData:
			break;
		case createActivityReport:

			break;
		case createLoansReport:

			
			break;

		case createLateReturnsReport:

			
			break;
		case AddBook:
			
			break;
		case AddBookCopy:
			
			break;
		case updateBook:

			
			break;
		case CreateNewLoan:
		
			break;

		
		case getBookDetails:
			break;
		case searchByFreeText:
			
			break;
		case searchByLibrarianAffiliation:
			
			break;
		case searchByLibrarianEmail:
			
			break;
		case searchByLibrarianID:
			
			break;
		case searchByLibrarianName:
			
			break;
		case searchBySubscriberEmail:
			
			break;
		case searchBySubscriberID:
			
			break;
		case searchBySubscriberName:

			break;
		case searchBySubscriberStudentID:
			
			break;

		case calcReturnDate:
			
			break;

		default:
			break;

		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class