package unitTests;

import java.io.IOException;

import ocsf.client.IAbstractClient;
import ocsf.server.IConnectionToClient;

public class AbstractClientStub implements IAbstractClient {

	EchoServerStub serverStub;
	IConnectionToClient clientStub;
	

	// Constructor injection
	public AbstractClientStub(EchoServerStub serverStub, IConnectionToClient clientStub) {
		this.serverStub = serverStub;
		this.clientStub = clientStub;
	}

	//handle message in stub server
	@Override
	public void sendToServer(Object msg) throws IOException {
		serverStub.handleMessageFromClient(msg, clientStub);
	}

}
