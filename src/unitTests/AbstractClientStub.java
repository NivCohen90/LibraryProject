package unitTests;

import java.io.IOException;

import ocsf.client.IAbstractClient;

public class AbstractClientStub implements IAbstractClient {

	EchoServerStub serverStub;

	// Constructor injection
	public AbstractClientStub(EchoServerStub serverStub) {
		this.serverStub = serverStub;
	}

	//handle message in stub server
	@Override
	public void sendToServer(Object msg) throws IOException {
		serverStub.handleMessageFromClient(msg, null);
	}

}
