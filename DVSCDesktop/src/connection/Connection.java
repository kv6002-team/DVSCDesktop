package connection;

import org.json.JSONArray;
import org.json.JSONObject;

import logging.Logger;
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
	
	public boolean reset(String username) {
		ParameterList pList = new ParameterList();
		pList.add("username",username);
		boolean flag = false;
		try {
			Response response = connectionManager.sendPostRequest("reset", pList, ConnectionManager.AUTH_TYPE.NONE, null);
			if(response.getResponseCode() == 200) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean log (Logger.TYPE type, Logger.LEVEL level, String message) {
		ParameterList pList = new ParameterList();
		pList.add("type", type.toString());
		pList.add("level", level.toString());
		pList.add("message", message);
		try {
			Response response = connectionManager.sendPostRequest(message, pList, ConnectionManager.AUTH_TYPE.NONE, null);
			if(response.getResponseCode() == 200) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
