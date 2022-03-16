package security;

import connection.ConnectionManager;
import connection.ParameterList;

/**
 * 
 * @author Scrub
 *
 */
public class AuthenticationManager {
	
	
	public static boolean authenticate(String username, String password) {
		ConnectionManager connectionManager = ConnectionManager.getInstance("dvsc.services");
		ParameterList pl = new ParameterList();
//		pl.add("test", "test");
//		pl.add("test2","test2");
		try {
			String response = connectionManager.sendPostRequest("ping", pl);
			System.out.println(response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
