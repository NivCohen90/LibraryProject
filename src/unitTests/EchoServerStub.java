package unitTests;

import java.io.IOException;

import Client.CommonHandler;
import Interfaces.IAlert;
import Interfaces.IHandler;
import Server.LoginQueris;
import SystemObjects.ServerData;
import ocsf.server.ConnectionToClient;
import ocsf.server.IAbstractServer;

public class EchoServerStub implements IAbstractServer {

	CommonHandler commonHandler;
	
	public EchoServerStub() {};
	
	@Override
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		LoginQuerisStub login = new LoginQuerisStub(((String) ((ServerData) msg).getDataMsg().get(0)),
				((String) ((ServerData) msg).getDataMsg().get(1)));
		ServerData result = login.Login(login);
		commonHandler.handleMessageFromServer(result);
	}

	//Property injection for testing class dependency
	public CommonHandler getCommonHandler() {
		return commonHandler;
	}

	public void setCommonHandler(CommonHandler commonHandler) {
		this.commonHandler = commonHandler;
	}
	
	

}
