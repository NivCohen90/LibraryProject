package Users;
import java.io.Serializable;
import java.util.ArrayList;

public class ServerData implements IGeneralData, Serializable{

	operations operation; 
	operationsReturn operationReturn;
	private ArrayList<Object> dataMsg;
	
	/*
	 * serverData for sending to server
	 */
	public ServerData(ArrayList<Object> dataMsg, operations op) {
		this.dataMsg = dataMsg;
		this.operation=op;
	}
	public ServerData(operations op, Object ... argMsg) {
		this.dataMsg = new ArrayList<>();
		for(Object argMsgi : argMsg)
			dataMsg.add(argMsgi);
		this.operation=op;
	}
	
	/*
	 * serverData for sending to client
	 */
	public ServerData(ArrayList<Object> dataMsg, operationsReturn op) {
		this.dataMsg = dataMsg;
		this.operationReturn=op;
	}
	public ServerData(operationsReturn op, Object ... argMsg) {
		this.dataMsg = new ArrayList<>();
		for(Object argMsgi : argMsg)
			dataMsg.add(argMsgi);
		this.operationReturn=op;
	}
	

	public operations getOperation() {
		return operation;
	}

	public void setOperation(operations operation) {
		this.operation = operation;
	}

	public ArrayList<Object> getDataMsg() {
		return dataMsg;
	}

	public void setDataMsg(ArrayList<Object> dataMsg) {
		this.dataMsg = dataMsg;
	}

	public operationsReturn getOperationReturn() {
		return operationReturn;
	}

	public void setOperationReturn(operationsReturn operationReturn) {
		this.operationReturn = operationReturn;
	}
	
}
