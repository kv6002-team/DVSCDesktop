package connection;

import org.json.JSONArray;
import org.json.JSONObject;

import security.JWT;
import security.SecurityManager;
import utils.Console;
/**
 * 
 * @author Scrub
 *
 */
public class Connection {
	private ConnectionManager connectionManager = new ConnectionManager(SecurityManager.getHostname());
	
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
	
	public void getPing() {
		try {
			ParameterList l = new ParameterList();
			l.add("X","y");
			String response = connectionManager.sendGetRequest("ping", l);
			ResponseParser responseParser = new ResponseParser(response);
			JSONArray resArr = responseParser.getArray();
			String res = resArr.getString(0);
			Console.log(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean authenticate(String username, String password) {
		boolean flag = false;
		String authString = SecurityManager.encode(username + ":" + password);
		try {
			String response = connectionManager.sendPostRequest("authenticate", null, ConnectionManager.AUTH_TYPE.BASIC, authString);
			ResponseParser responseParser = new ResponseParser(response);
			JSONObject resObj = responseParser.getObject();
			if(resObj.getString("token_type") == "bearer") {
				JWT.getInstance().setToken(resObj.getString("token"));
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
