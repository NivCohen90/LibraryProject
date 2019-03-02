package unitTests;

import java.util.ArrayList;

import Interfaces.ILoginQueris;
import Server.LoginQueris;
import SystemObjects.GeneralData.operationsReturn;
import SystemObjects.ServerData;
import Users.Librarian;
import Users.Subscriber;
import Users.User;

public class LoginQuerisStub implements ILoginQueris {

	private String username;
	private String password;

	public LoginQuerisStub(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//set array with variables for subscriber and librarian login tests
	@Override
	public ServerData Login(ILoginQueris Login) {
		ArrayList<Object> dataArray = new ArrayList<Object>();
		ServerData returnData=null;
		dataArray.add(new Librarian("Sharon", "Mimar", "Sharonmimer@gmail.com", "999999993", "mynameisnotmimer",
				"0541313326", "Staff", 1));
		dataArray.add(new Librarian("Yankale", "Shahar", "FCMH1917@gmail.com", "999999992", "Eihhemakzooti",
				"0500003031", "HR", 2));
		dataArray.add(new Subscriber("999999996", "Arnst", "Setcus", "Sharkattack@gmail.com", "0549986757", "Gate5",
				"2", "Freeze", 1));

		for (Object obj : dataArray)
		{
			if (obj instanceof User)
			{
				if (((User)obj).getID().equals(((LoginQuerisStub)Login).getUsername())&&((User)obj).getPassword().equals(((LoginQuerisStub)Login).getPassword()))
				{
					if (obj instanceof Subscriber)
						returnData = new ServerData(operationsReturn.returnSubscriber, obj);
					if (obj instanceof Librarian)
					{
						if(((User)obj).getID()=="999999993")
							returnData = new ServerData(operationsReturn.returnLibrarian, obj);
						if(((User)obj).getID()=="999999992")
							returnData = new ServerData(operationsReturn.returnLibrarian, obj);
					}
				}
			}
		}
		
		if(returnData!=null)
			return returnData;
		
		dataArray.clear();
		dataArray.add("Can't Login - the Username/Password are wrong.");
		returnData = new ServerData(dataArray,operationsReturn.returnError);
		return returnData;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
