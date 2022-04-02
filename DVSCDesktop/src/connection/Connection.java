package connection;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.Garage;
import domain.GarageInfo;
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
			ParameterList pl = new ParameterList();
			pl.add("types", "garage-consultant");
			Response response = connectionManager.sendPostRequest("auth", pl, ConnectionManager.AUTH_TYPE.BASIC, authString);
			JSONObject resObj = response.getObject();
			if(resObj.getString("token_type").equals("bearer")) {
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
	
	public ArrayList<Garage> getAllGarages() {
		ArrayList<Garage> allGarages = new ArrayList<Garage>();
		try {
			Response response = connectionManager.sendGetRequest("garages", null);
			if(response.getResponseCode() == 200) {
				JSONArray arr = response.getArray();
				for(int i=0; i<arr.length(); i++) {
					allGarages.add(new Garage(arr.getJSONObject(i).getString("name"), Integer.parseInt(arr.getJSONObject(i).getString("id"))));
				}
				
			} 
		} catch (Exception e){
			e.printStackTrace();
		}
		return allGarages;		
	}
	
	public GarageInfo getGarage(int id) {
		GarageInfo gi = null;
		try{
			Response response = connectionManager.sendGetRequest("garages/"+id, null);
			if(response.getResponseCode() == 200) {
				JSONObject obj = response.getObject();
				System.out.println(obj);
			}
		} catch	(Exception e) {
			e.printStackTrace();
		}
		return gi;
		
	}
}
