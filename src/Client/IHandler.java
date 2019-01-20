package Client;

import java.io.IOException;
import java.util.ArrayList;

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

public abstract class IHandler extends AbstractClient {

	/*
	 * Variables IGUIcontroller - controller input was sent from
	 */
	protected IGUIcontroller currentControllerGUIobj;

	public IHandler() throws IOException {
		super("localhost", 5555);
		openConnection();
	}

	// how this handler respond to massage from server
	// will convert massage from server and pass it to the GUI controller that
	// called
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
		}
		System.exit(0);
	}

}
