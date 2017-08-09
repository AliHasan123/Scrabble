package rmi.server;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import rmi.common.IServer;

public class ServerDriver {

	/**
	 * Starts the server.  Server must be running and registered in the RMI
	 * registry before any client starts.
	 * 
	 * usage: ServerDriver <portnumber>
	 * 
	 * <portnumber> is the port number assigned to your team.  If you are on
	 * team cse-tNNN then your port number is 50NNN.
	 * 
	 * Be sure that <portnumber> is supplied as a program argument in the run
	 * configuration of the server, and that '-Djava.security.policy=java.policy'
	 * is entered as a VM argument.
	 */
	public static void main(String[] args) {
		// GET <hostname> AND <portnumber> FROM THE COMMAND LINE ARGUMENTS.
		// If incorrect number of arguments are supplied, exit.
		if (args.length != 1) {
			System.err.println("usage: ServerDriver <portnumber>");
			System.exit(1);
		}
		int portNumber = 0;
		// Convert "50NNN" (a String) to 50NNN (an int)
		try {
			portNumber =  Integer.parseInt(args[0]);
		}
		// If format is improper for a number, exit.
		catch (NumberFormatException e) {
			System.err.println("[SERVER] Port number must consist only of decimal digits '0' through '9'.");
			e.printStackTrace();
			System.exit(1);
		}

		// If there is no security manager running, create one.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
			System.out.println("Security manager started");
		}

		// Try to start the RMI registry running on the server's host
		Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(portNumber);
			System.out.println("Registry started normally on port "+portNumber);
		}
		// If this doesn't work, exit.
		catch (RemoteException e1) {
			System.err.println("Registry start-up failed.");
			e1.printStackTrace();
			System.exit(1);
		}

		// Start up the server
		System.out.println("Starting server...");
		IServer engine = new Server();
		System.out.println("Server started.");
		// Try to create remote server object, the 'stub'
		System.out.println("Creating remote server object...");
		IServer stub = null;
		try {
			stub = (IServer) UnicastRemoteObject.exportObject(engine, 50000);
			System.out.println("Server started normally.");
		}
		catch (RemoteException e) {
			System.err.println("Server start-up failed.");
			e.printStackTrace();
			// Try to shut down the rmi registry process before exiting
			try {
				UnicastRemoteObject.unexportObject(registry, true);
			}
			// Could not shut down rmi registry process
			catch (NoSuchObjectException e1) {
				System.err.println("RMI registry process could not be shut down.");
				System.err.println("Verify that the process is terminated prior to restarting server.");
				e1.printStackTrace();
				System.exit(1);
			}
		}
		System.out.println("Trying to register remote server object in RMI registry with name "+IServer.SERVER_REGISTRY_KEY);
		try {
			registry.rebind(IServer.SERVER_REGISTRY_KEY, stub);
			System.out.println("Server bound in registry normally.");
		}
		catch (RemoteException e) {
			System.err.println("Could not bind server in RMI registry.");
			e.printStackTrace();
			// Try to shut down the rmi registry process before exiting
			try {
				UnicastRemoteObject.unexportObject(registry, true);
			}
			// Could not shut down rmi registry process
			catch (NoSuchObjectException e1) {
				System.err.println("RMI registry process could not be shut down.");
				System.err.println("Verify that the process is terminated prior to restarting server.");
				e1.printStackTrace();
				System.exit(1);
			}
		}
	}
}
