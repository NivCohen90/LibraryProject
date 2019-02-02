package Server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import Interfaces.IAlert;
import OBLFX.NewLoanController;
import Server.LoginQueris;
import Server.SearchUserQueries.searchtype;
import SystemObjects.Book;
import SystemObjects.GeneralData;
import SystemObjects.Order;
import SystemObjects.ReportData;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.GeneralData.reportReference;
import SystemObjects.LateReturnsReportBookData;
import SystemObjects.Loan;
import Users.Subscriber;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 * @param <Student>
 */

public class EchoServer extends AbstractServer {

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
	public EchoServer(int port) {
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
		ServerController.updateLog("Request from:\n" + client.getInetAddress().getHostName() + "\nCommand: "
				+ ((ServerData) msg).getOperation());
		ServerData msgToClient;
		Statement s;
		switch (((ServerData) msg).getOperation()) {
		case Login:
			try {
				LoginQueris login = new LoginQueris(((String) ((ServerData) msg).getDataMsg().get(0)),
						((String) ((ServerData) msg).getDataMsg().get(1)));
				ServerData result = LoginQueris.Login(login);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchByBookName:
			try {
				ServerData result = SearchBooksQueries.SearchBookByColName(
						(((String) ((ServerData) msg).getDataMsg().get(0))), SearchBooksQueries.Cols.BookName);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchByBookAuthor:
			try {
				ServerData result = SearchBooksQueries.SearchBookByColName(
						(((String) ((ServerData) msg).getDataMsg().get(0))), SearchBooksQueries.Cols.AuthorName);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchByBookSubject:
			try {
				ServerData result = SearchBooksQueries.SearchBookByColName(
						(((String) ((ServerData) msg).getDataMsg().get(0))), SearchBooksQueries.Cols.Subject);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchByBookDescription:
			try {
				ServerData result = SearchBooksQueries.SearchBookByColName(
						(((String) ((ServerData) msg).getDataMsg().get(0))), SearchBooksQueries.Cols.Description);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchByCatalogNumber:
			try {
				ServerData result = SearchBooksQueries.SearchBookByColName(
						(((String) ((ServerData) msg).getDataMsg().get(0))), SearchBooksQueries.Cols.CatalogNumber);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case updateSubscriberDetails:
			try {
				ServerData result = SubscriberQueries.UpdateSubscriberInformation((((Subscriber) ((ServerData) msg).getDataMsg().get(0))));
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			} catch (SQLException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			
			break;
		case orderBook:
			try {
				ArrayList<Object> getOrder = ((ServerData) msg).getDataMsg();
				msgToClient = SubscriberQueries.addOrderToDB((Order) getOrder.get(0));
				client.sendToClient(msgToClient);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}

			break;

		case extandLoan:
			String loanID = ((ServerData) msg).getDataMsg().get(1).toString();
			String subID = ((ServerData) msg).getDataMsg().get(0).toString();
			try {
				String subStatus = SubscriberQueries.getSubscriberStatus(subID);
				if (subStatus.equals("Active")) {
					if (!BookQueries.checkOrdersForBook(loanID)) {
						if (!BookQueries.isDemandedByLoanID(loanID)) {
							LoanQueries.updateLoanReturnDate(subID, loanID);
							
							ArrayList<Object> loans = LoanQueries.getSubscriberActiveLoans(subID);
							msgToClient = new ServerData(loans, operationsReturn.returnLoanArray);
							//msgToClient = new ServerData(operationsReturn.returnSuccessMsg, "Extension was Approved");
							
						} else
							msgToClient = new ServerData(operationsReturn.returnError,
									"Book is demanded. Extension was declined");
					}
					else msgToClient = new ServerData(operationsReturn.returnError, "This book has been ordered. Extension was declined");
				} else
					msgToClient = new ServerData(operationsReturn.returnError,
							"Subscriber status isn't 'Active'. Extension was declined");
			} catch (SQLException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
				msgToClient = new ServerData(operationsReturn.returnException, e);
			}
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e2) {
				IAlert.ExceptionAlert(e2);
				e2.printStackTrace();
			}
			
			break;

		case viewActiveLoans:
			break;
		case viewActivityHistory:
			break;
		case updateReturnDateManualy:
			break;
		case returnBook:
			ArrayList<Object> arr =  ((ServerData) msg).getDataMsg();
			String catalog = (String) arr.get(0);
			String subscriberID = (String) arr.get(1);
			//LocalDate returnDate = (LocalDate) arr.get(2);
			
			msgToClient = BookQueries.returnBook(subscriberID, catalog);
			
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e2) {
				IAlert.ExceptionAlert(e2);
				e2.printStackTrace();
			}
			
			break;

		case watchReadersCard:
			break;
		case CreateNewSubscriber:
			Subscriber sub = (Subscriber) ((ServerData) msg).getDataMsg().get(0);
			String userSQL = String.format(
					"INSERT INTO `obl`.`user`\n" + "(`ID`,\n" + "`FirstName`,\n" + "`LastName`,\n" + "`Email`,\n"
							+ "`PhoneNumber`,\n" + "`Password`,\n" + "`Level`)\n" + "VALUES\n"
							+ "('%s','%s','%s','%s','%s','%s','%s');",
					sub.getID(), sub.getFirstName(), sub.getLastName(), sub.getEmail(), sub.getPhoneNumber(),
					sub.getPassword(), sub.getLevel());
			String subscriberSQL = String.format("INSERT INTO `obl`.`subscriber`\n" + "(`ID`,\n" + "`Status`,\n"
					+ "`FelonyNumber`)\n" + "VALUES\n" + "('%s','%s','%s');", sub.getID(), sub.getStatus(),
					sub.getFellonyNumber());

			try {
				s = mysqlConnection.conn.createStatement();
				s.executeUpdate(userSQL);
				s.executeUpdate(subscriberSQL);
				msgToClient = new ServerData(operationsReturn.returnSuccessMsg, "");
			} catch (SQLException e) {
				SQLException  ex = new SQLException("Failed to create New Subscriber with ID: " + sub.getID()); 
				msgToClient = new ServerData(operationsReturn.returnException, ex);
			}
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case ManageCatalog:
			break;
		case watchEmployeesData:
			break;
		case createActivityReport:

			break;
		case createLoansReport:

			try {
				ReportData demandedBooksStat = ReportQueries.calculateLoanReportStatistic(ReportQueries.demandedBooksSQL(), reportReference.Demanded);
				ReportData regularBooksStat = ReportQueries.calculateLoanReportStatistic(ReportQueries.regularBookSQL(), reportReference.Regular);
				msgToClient = new ServerData(operationsReturn.returnLoanReportData, demandedBooksStat, regularBooksStat);
			} catch (SQLException e) {
				msgToClient = new ServerData(operationsReturn.returnException, e);
			}
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
			
		case createLateReturnsReport:
			ArrayList<LateReturnsReportBookData> booksData=ReportQueries.allBooksLateReturnData();
			ReportData generalBooksCount= ReportQueries.generalAmountLateReturnsReportStat(reportReference.GeneralLatesAmount);
			ReportData generalBooksDuration= ReportQueries.generalDurationLateReturnsReportStat(reportReference.GeneralLatesDuration);
			msgToClient = new ServerData(operationsReturn.returnLateReturnsReportData, generalBooksCount, generalBooksDuration, booksData);
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
			
		case AddBook:
			try {
				ArrayList<Object> getBook = ((ServerData) msg).getDataMsg();
				msgToClient = CatalogQueries.addBookToDB((Book) (getBook.get(0)));
				client.sendToClient(msgToClient);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case AddBookCopy:
			try {
				ArrayList<Object> getBook = ((ServerData) msg).getDataMsg();
				msgToClient = CatalogQueries.addBookCopyToDB((String)(getBook.get(0)),(String)(getBook.get(1)));
				client.sendToClient(msgToClient);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case updateBook:
			try {
				ArrayList<Object> getBook = ((ServerData) msg).getDataMsg();
				msgToClient = CatalogQueries.updateBookInDB((Book) (getBook.get(0)));
				client.sendToClient(msgToClient);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case CreateNewLoan:
			ArrayList<Object> newLoan = ((ServerData) msg).getDataMsg();
			try {
				String subStatus = SubscriberQueries.getSubscriberStatus(((Loan) newLoan.get(0)).getSubscriberID());
				if (subStatus.equals("Active")) {
					LoanQueries.createNewLoan((Loan) newLoan.get(0));
					msgToClient = new ServerData(operationsReturn.returnSuccessMsg);
				} else
					msgToClient = new ServerData(operationsReturn.returnError);
			} catch (SQLException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
				msgToClient = new ServerData(operationsReturn.returnException);
			}
			try {
				client.sendToClient(msgToClient);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		case deleteBook:
			break;
		case getBookDetails:
			break;
		case searchByFreeText:
			try {
				ServerData result = SearchBooksQueries
						.FreeTextSearch((((String) ((ServerData) msg).getDataMsg().get(0))));
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
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
			try {
				ServerData result = SearchUserQueries.searchUser((((String) ((ServerData) msg).getDataMsg().get(0))), searchtype.SubscriberbyName);
				client.sendToClient(result);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
			break;
		case searchBySubscriberStudentID:
			break;

		case calcReturnDate:
			ArrayList<Object> data = ((ServerData) msg).getDataMsg();
			boolean answer = BookQueries.isDemanded((String) data.get(1));
			LocalDate returnDate = LoanQueries.calcNewReturnDate((LocalDate) data.get(0), answer);
			try {
				client.sendToClient(returnDate);
			} catch (IOException e) {
				IAlert.ExceptionAlert(e);
				e.printStackTrace();
			}
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
