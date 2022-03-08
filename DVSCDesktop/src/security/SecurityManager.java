package security;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import gui.AlertDialog;

/**
 * 
 * @author Scrub
 * Provides methods to ensure security across the Java Application
 */
public class SecurityManager {
	private static SecurityManager instance;
	private static String hostname = "dvsc.services";
	
	public static SecurityManager getInstance() {
		if(instance == null) instance = new SecurityManager();
		return instance;
	}
	
	/**
	 * @author Scrub
	 * @return boolean
	 * 
	 * Checks the the connection to the Clients server, ensuring that the application has a connection.
	 */
	public static boolean checkConnection() {
		int timeout = 1000; // Time the connection waits before throwing an Error
		int port = 80; // Port that the Socket will try to connect to (80: HTTP / 443: HTTPS etc)
		
		try (Socket socket = new Socket()){
			socket.connect(new InetSocketAddress(hostname, port), timeout);
			return true;
		}catch(IOException ex) {
			System.out.println(ex.toString());
			return false;
		}	
	}
	
	public static void testSystemSecurity() {
		int maxKeySize;
		try {
			maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
			if(maxKeySize <= 128) {
				new AlertDialog("Invalid System Security Setup!");
				System.exit(0);
			}
		} catch (NoSuchAlgorithmException ex){
			new AlertDialog("Invalid System Security Setup!");
			System.exit(0);
		}
	}
	
	public static String getHostname() {
		return hostname;
	}
}
