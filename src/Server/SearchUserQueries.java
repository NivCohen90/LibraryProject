package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SystemObjects.ServerData;
import SystemObjects.GeneralData.operationsReturn;
import Users.Librarian;
import Users.Subscriber;
public class SearchUserQueries {
	/**
	 * Indicate the search type of the User.
	 * @author NivPC
	 *
	 */
	enum searchtype{SubscriberbyID, SubscriberbyNumber, SubscriberbyName, SubscriberbyEmail, LibrarianbyID, LibrarianbyAffiliation, LibrarianbyName, LibrarianbyEmail};
	
	public static ServerData searchUser(String search, searchtype type) {
		ServerData Result;
		ArrayList<Subscriber> SubscriberList = new ArrayList<Subscriber>();
		ArrayList<Librarian> LibrarianList = new ArrayList<Librarian>();
		try {
			Statement stmt;
			switch(type) {
			case LibrarianbyAffiliation:
				break;
			case LibrarianbyEmail:
				break;
			case LibrarianbyID:
				break;
			case LibrarianbyName:
				break;
			case SubscriberbyEmail:
				break;
			case SubscriberbyID:
				break;
			case SubscriberbyName:
				stmt = mysqlConnection.conn.createStatement();
				ResultSet Res = stmt.executeQuery(searchStudents(search, "FirstName"));
				if(!Res.next()) {
					Result = new ServerData(operationsReturn.returnError, "There is no Subscriber with that Name (" + search + ").");
					return Result;
				}
				do {
					Subscriber Sub = new Subscriber(Res.getString(1), Res.getString(2), Res.getString(3), Res.getString(4), Res.getString(5), Res.getString(6), Res.getString(7), Res.getString(8), Res.getInt(9));
					
				}while(Res.next());
				stmt.closeOnCompletion();
				break;
			case SubscriberbyNumber:
				break;
			default:
				break;
			
			}
			return null;
		} catch (SQLException e) {
			Result = new ServerData(operationsReturn.returnError, "Cant Communicate with Database.");
			return Result;
		}

	}
	
	public static String searchStudents(String Name, String seachtype) {
		String getSubscriberInformation = "SELECT obl.user.ID, obl.user.FirstName, obl.user.LastName, obl.user.Email, obl.user.PhoneNumber,obl.user.Password, obl.subscriber.SubscriberID, obl.user.Level, obl.subscriber.Status, obl.subscriber.FelonyNumber\r\n"
				+ "FROM obl.user\r\n" + "INNER JOIN obl.subscriber ON obl.user.ID=subscriber.ID\r\n"
				+ "WHERE $obl.user." + seachtype + " LIKE %'" + Name + "'%;";
		return getSubscriberInformation;
		
	}
}
