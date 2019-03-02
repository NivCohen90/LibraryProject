package Interfaces;

import java.io.IOException;

public interface IConnectionToClient {

	public void sendToClient(Object msg) throws Exception;
}
