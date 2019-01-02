package Users;
import java.util.ArrayList;

public class ServerData implements IGeneralData{

	int operation; 
	private ArrayList<Object> user;
	
	public ServerData(ArrayList<Object> user, int num) {
		this.user = user;
		this.operation=num;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}

	public ArrayList<Object> getUser() {
		return user;
	}

	public void setUser(ArrayList<Object> user) {
		this.user = user;
	}
	
}
