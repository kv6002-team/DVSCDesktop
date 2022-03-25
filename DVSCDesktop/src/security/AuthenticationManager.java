package security;
import connection.Connection;
import connection.ConnectionManager;
import connection.ParameterList;
import gui.AlertDialog;

/**
 * 
 * @author Scrub
 *
 */
public class AuthenticationManager {
	
	private static Connection con = new Connection();
	public static boolean authenticate(String username, String password) {
		
		
		//Sanity check if user has populated password and username field
		if(username.length() == 0 || password.length() == 0) {
			AlertDialog ad = new AlertDialog();
			ad.run("One or more fields are missing");
			return false;
		}
		
		return true;
	}
	
	public static boolean resetPassword(String username) {
		return con.reset(username);
	}
}
