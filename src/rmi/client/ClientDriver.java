package rmi.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.SwingUtilities;

import rmi.common.IServer;

public class ClientDriver {

	/**
	 * Starts the client.  Server must be running and registered in the RMI
	 * registry before any client starts.
	 * 
	 * usage: ClientDriver <hostname> <portnumber>
	 * 
	 * <hostname> is the name of the host on which the RMI registry and the
	 * server are running.  For example, 'timberlake.cse.buffalo.edu'.
	 * 
	 * <portnumber> is the port number assigned to your team.  If you are on
	 * team cse-tNNN then your port number is 50NNN.
	 * 
	 * Since your team is given just one port number to use each of your clients
	 * must run on distinct hosts, all different from the host of the RMI registry
	 * and server.  Here are some you can use:
	 * 
	 *     dragonforce.cse.buffalo.edu
	 *     styx.cse.buffalo.edu
	 *     timberlake.cse.buffalo.edu
	 *     
	 * You can also try running a client on your own laptop - just make sure your
	 * firewall will allow incoming and outgoing connections for Java.
	 * 
	 * Be sure that <hostname> and <portnumber> are supplied as program arguments
	 * in the run configuration of the client, and that '-Djava.security.policy=java.policy'
	 * is entered as a VM argument.
	 */
	public static void main(String args[]) {
		// GET <hostname> AND <portnumber> FROM THE COMMAND LINE ARGUMENTS.
		// If incorrect number of arguments are supplied, exit.
		if (args.length != 2) {
			System.err.println("usage: ClientDriver <hostname> <portnumber>");
			System.exit(1);
		}
		String hostName = args[0];
		int portNumber = 0;
		// Convert "50NNN" (a String) to 50NNN (an int)
		try {
			portNumber =  Integer.parseInt(args[1]);
		}
		// If format is improper for a number, exit.
		catch (NumberFormatException e) {
			System.err.println("[CLIENT] Port number must consist only of decimal digits '0' through '9'.");
			e.printStackTrace();
			System.exit(1);
		}

		// If there is no security manager running, create one.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		// Try to find the RMI registry running on the server's host
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(hostName, portNumber);
			System.out.println("[CLIENT] Registry object created.");
		}
		// If connection cannot be made, exit.
		catch (RemoteException e) {
			System.err.println("[CLIENT] Cannot connect to registry.");
			e.printStackTrace();
			System.exit(1);
		}
		// Try to get reference to remote server object 
		IServer server = null;
		try {
			System.out.println("[CLIENT] Trying to retrieve remote server from registry.");
			server = (IServer) registry.lookup(IServer.SERVER_REGISTRY_KEY);
			System.out.println("[CLIENT] Received reference to remote server.");
		}
		
		catch (AccessException e) {
			System.err.println("[CLIENT] Cannot access server.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (RemoteException e) {
			System.err.println("[CLIENT] Problem contacting remote server.");
			e.printStackTrace();
			System.exit(1);
		}
		catch (NotBoundException e) {
			System.err.println("[CLIENT] Server not bound with name \""+IServer.SERVER_REGISTRY_KEY+"\"");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Connection ready - start up the client!
		SwingUtilities.invokeLater(new ClientUI(server, hostName, portNumber));
	}  
}
