package rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The IClient interface specifies method(s) that
 * are available remotely.
 * 
 * In this example there is only one method, update(),
 * that the server can call on the client.
 * 
 */
public interface IClient extends Remote {
	public void update() throws RemoteException;
}
