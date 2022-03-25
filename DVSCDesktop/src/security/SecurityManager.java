package security;

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
		return ConnectionValidator.testConntectionHTTPS(hostname);
	}
	
	public static void testSystemSecurity() {
		AlertDialog alertDialog =  new AlertDialog();
		int maxKeySize;
		try {
			maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
			if(maxKeySize <= 128) {
				alertDialog.run("Invalid System Security Setup!");
				System.exit(0);
			}
		} catch (NoSuchAlgorithmException ex){
			alertDialog.run("Invalid System Security Setup!");
			System.exit(0);
		}
	}
	
	public static String getHostname() {
		return hostname;
	}
}
