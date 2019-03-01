package ocsf.server;

public interface IAbstractServer {
	public abstract void handleMessageFromClient(Object msg, ConnectionToClient client);
}
