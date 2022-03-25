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
			Response response = connectionManager.sendPostRequest("ping", null, ConnectionManager.AUTH_TYPE.NONE, null);
			JSONArray responseArray = response.getArray();
			String res = responseArray.getString(0);
			
			if(res.contentEquals("pong")) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void getPing() {
		try {
			Response response = connectionManager.sendGetRequest("ping", null);
			JSONArray resArr = response.getArray();
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
			Response response = connectionManager.sendPostRequest("authenticate", null, ConnectionManager.AUTH_TYPE.BASIC, authString);
			JSONObject resObj = response.getObject();
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
