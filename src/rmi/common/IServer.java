package rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The IServer interface specifies method(s) that
 * are available remotely.
 * 
 * In this example there are three methods
 * that a client can call on the server.
 * 
 */
public interface IServer extends Remote {
	
	public static final String SERVER_REGISTRY_KEY = "Vote";

	/**
	 * Registers IClient c as an observer of changes on the server.
	 * @param c
	 * @throws RemoteException
	 */
	public void addIClient(IClient c) throws RemoteException;
	
	/**
	 * The server keeps track of votes cast.  A client can cast
	 * a vote by calling the vote() method. 
	 * @throws RemoteException
	 */
	public void vote() throws RemoteException;
	
	/**
	 * An accessor method that a client can call to retrieve the
	 * number of votes cast in total.
	 * @return the total number of votes cast (by all clients)
	 * @throws RemoteException
	 */
	public int getVotes() throws RemoteException;
}
