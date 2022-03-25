package security;

import connection.ConnectionManager;
import connection.ParameterList;
import gui.AlertDialog;

/**
 * 
 * @author Scrub
 *
 */
public class AuthenticationManager {
	
	private JWT JWTToken= JWT.getInstance();
	
	public static boolean authenticate(String username, String password) {
		if(username.length() == 0 || password.length() == 0) {
			AlertDialog ad = new AlertDialog();
			ad.run("One or more fields are missing");
			return false;
		}
		ConnectionManager connectionManager = ConnectionManager.getInstance("dvsc.services");
		ParameterList pl = new ParameterList();
		try {
			String response = connectionManager.sendPostRequest("ping", pl);
			System.out.println(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
