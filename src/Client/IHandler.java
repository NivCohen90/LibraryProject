package Client;

import java.io.IOException;
import java.util.ArrayList;

import OBLFX.ConnectionSettingsController;
import OBLFX.IAlert;
import OBLFX.IGUIcontroller;
import Users.Book;
import Users.BookCopy;
import Users.ServerData;
import Users.Subscriber;
import javafx.application.Platform;
import ocsf.client.AbstractClient;
import Users.Librarian;
import Users.Loan;
import Users.Order;

/**
 * interface class for clients, has method to parse massage from server to objects
 * @author ofir
 *
 */

public abstract class IHandler extends AbstractClient {

	/*
	 * Variables IGUIcontroller - controller input was sent from
	 */
	protected IGUIcontroller currentControllerGUIobj;
	public static ConnectionSettingsController conn = new ConnectionSettingsController();
	
	public IHandler(String IPAddress, int port){
		super(IPAddress, port);
		try {
			openConnection();
			conn.ConnectedFLAG = true;
			conn.setConnection();
		} catch (IOException e) {
			conn.ConnectedFLAG = false;
			conn.setConnection();
			IAlert.ExceptionAlert(e.getClass().getName(), e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * 	how this handler respond to massage from server
	 *	will convert massage from server and pass it to the GUI controller that called.
	 *	@author nivco
	 *	@param
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		ServerData serverMsg = (ServerData) msg;
		ArrayList<Object> arrayMsg = serverMsg.getDataMsg();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				// switch between different return massages from server
				// convert data from server and send to GUI controller
				switch (serverMsg.getOperationReturn()) {
				case returnSuccessMsg:
					String successMsg = convertMsgFromServer(arrayMsg.get(0), String.class);
					currentControllerGUIobj.receiveMassageFromServer(successMsg, serverMsg.getOperationReturn());
					break;
				case returnSubscriber: {
					Subscriber subscriberData = convertMsgFromServer(arrayMsg.get(0), Subscriber.class);
					currentControllerGUIobj.receiveMassageFromServer(subscriberData, serverMsg.getOperationReturn());
					break;
				}
				case returnLibrarian: {
					Librarian librarianData = convertMsgFromServer(arrayMsg.get(0), Librarian.class);
					currentControllerGUIobj.receiveMassageFromServer(librarianData, serverMsg.getOperationReturn());
					break;
				}
				case returnBook: {
					Book bookData = convertMsgFromServer(arrayMsg.get(0), Book.class);
					currentControllerGUIobj.receiveMassageFromServer(bookData, serverMsg.getOperationReturn());
					break;
				}
				case returnBookCopy: {
					BookCopy bookCopyData = convertMsgFromServer(arrayMsg.get(0), BookCopy.class);
					currentControllerGUIobj.receiveMassageFromServer(bookCopyData, serverMsg.getOperationReturn());
					break;
				}
				case returnLoan: {
					Loan loanData = convertMsgFromServer(arrayMsg.get(0), Loan.class);
					currentControllerGUIobj.receiveMassageFromServer(loanData, serverMsg.getOperationReturn());
					break;
				}
				case returnOrder: {
					Order orderData = convertMsgFromServer(arrayMsg.get(0), Order.class);
					currentControllerGUIobj.receiveMassageFromServer(orderData, serverMsg.getOperationReturn());
					break;
				}
				case returnSubscriberArray: {
					ArrayList<Subscriber> subscriberList = convertArrayMsgFromServer(arrayMsg, Subscriber.class);
					currentControllerGUIobj.receiveMassageFromServer(subscriberList, serverMsg.getOperationReturn());
					break;
				}
				case returnLibrarianArray: {
					ArrayList<Librarian> librarianList = convertArrayMsgFromServer(arrayMsg, Librarian.class);
					currentControllerGUIobj.receiveMassageFromServer(librarianList, serverMsg.getOperationReturn());
					break;
				}
				case returnBookArray: {
					ArrayList<Book> bookList = convertArrayMsgFromServer(arrayMsg, Book.class);
					currentControllerGUIobj.receiveMassageFromServer(bookList, serverMsg.getOperationReturn());
					break;
				}
				case returnBookCopyArray: {
					ArrayList<BookCopy> bookcopyList = convertArrayMsgFromServer(arrayMsg, BookCopy.class);
					currentControllerGUIobj.receiveMassageFromServer(bookcopyList, serverMsg.getOperationReturn());
					break;
				}
				case returnLoanArray: {
					ArrayList<Loan> loanList = convertArrayMsgFromServer(arrayMsg, Loan.class);
					currentControllerGUIobj.receiveMassageFromServer(loanList, serverMsg.getOperationReturn());
					break;
				}
				case returnOrderArray: {
					ArrayList<Order> orderList = convertArrayMsgFromServer(arrayMsg, Order.class);
					currentControllerGUIobj.receiveMassageFromServer(orderList, serverMsg.getOperationReturn());
					break;
				}
				default:
					;
				}
			}
		});
	}

	/**
	 * convert ArrayList<T> to ArrayList<classType>, will be used to convert message of ArrayList<object> to different array list types
	 * @param arrayMsg array list to convert form
	 * @param classType class to convert objects to
	 * @return array list of classType
	 */
	// return arrayMsg as classType array list
	private <T> ArrayList<T> convertArrayMsgFromServer(ArrayList<Object> arrayMsg, Class<T> classType) {
		ArrayList<T> array = new ArrayList<>();
		T temp;
		for (Object dataMsgi : arrayMsg) {
			temp = convertMsgFromServer(dataMsgi, classType);
			if (temp != null)
				array.add(temp);
			else
				return null;
		}
		return array;
	}

	/**
	 * convert Object to classType, will be used to convert single object message to different class
	 * will return null if can't cast object to class
	 * @param msg object to convert
	 * @param classType class to convert object to
	 * @return object as instance of classType
	 */
	// return msg as classType object
	public <T> T convertMsgFromServer(Object msg, Class<T> classType) {
		if (classType.isInstance(msg))
			return classType.cast(msg);
		return null;
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			IAlert.ExceptionAlert(e.getClass().getName(), e.getMessage());
		}
	}

}
