package rmi.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rmi.common.IClient;
import rmi.common.IServer;

public class ClientUI implements Runnable, IClient {

	private JTextField _tf;
	private JFrame _window;
	private IServer _server;

	public ClientUI(IServer s, String hostName, int portNumber) {
		_server = s;
		IClient me = null;
		try {
			boolean retry = false;
			do {
				try {
					me = (IClient) UnicastRemoteObject.exportObject(this,portNumber);
					retry = false;
				}
				catch (ExportException e) {
					if (hostName.equals("localhost")) {
						System.out.print("Port "+portNumber+" is unavailable, trying ");
						portNumber++;
						System.out.println(portNumber);
						retry = true;
					}
					else {
						System.out.print("Port "+portNumber+" is unavailable.");
						e.printStackTrace();
						System.exit(1);
					}
				}
			} while (retry);

		} catch (RemoteException e) {
			System.err.println("[CLIENT] Could not export self.");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			_server.addIClient(me);
		} catch (RemoteException e) {
			System.err.println("[CLIENT] Could not register with remote server.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override public void update() {
		try {
			_tf.setText("# votes cast: "+_server.getVotes());
			_window.pack();
		} catch (RemoteException exc) {
			System.err.println("Couldn't connect to server.");
			exc.printStackTrace();
		}
	}

	@Override public void run() {
		_window = new JFrame("Client");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		JButton b = new JButton("Vote!");
		b.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				try {
					_server.vote();
				} catch (RemoteException exc) {
					System.err.println("Couldn't connect to server.");
					exc.printStackTrace();
				}
			}
		});
		panel.add(b);
		_tf = new JTextField("");
		panel.add(_tf);
		_window.add(panel);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_window.pack();
		_window.setVisible(true);
	}
}
