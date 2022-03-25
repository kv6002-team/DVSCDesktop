package connection;

import org.json.JSONArray;

/**
 * 
 * @author Scrub
 *
 */
public class Connection {
	private ConnectionManager connectionManager = ConnectionManager.getInstance("dvsc.services");
	
	/**
	 * Returns true if the correct and a valid response is received from the server
	 * @return boolean
	 */
	public boolean ping() {
		boolean flag = false;
		try {
			String response = connectionManager.sendPostRequest("ping", null, ConnectionManager.AUTH_TYPE.NONE, null);
			
			ResponseParser responseParser = new ResponseParser(response);
			JSONArray responseArray = responseParser.getArray();
			String res = responseArray.getString(0);
			
			if(res.contentEquals("pong")) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
//	public String getHashedPassword(String username) {
//		ParameterList pm = new ParameterList();
//		pm.add("username", username);
//		
//		String response = connectionManager.sendPostRequest("");
//		
//	}
}
