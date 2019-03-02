package unitTests;

import java.io.IOException;

import Interfaces.IHandler;
import ocsf.server.IConnectionToClient;

public class ConnectionToClientStub implements IConnectionToClient {

	IHandler client;
	
	@Override
	public void sendToClient(Object msg) throws IOException {
		client.handleMessageFromServer(msg);
	}

	//Property injection
	public IHandler getClient() {
		return client;
	}

	public void setClient(IHandler client) {
		this.client = client;
	}
	
	

}
