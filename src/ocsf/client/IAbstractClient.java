package ocsf.client;

import java.io.IOException;

public interface IAbstractClient {
	
	public void sendToServer(Object msg) throws IOException;
}
