package connection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.Garage;
import domain.GarageInfo;
import domain.Instrument;
import domain.Instrument.CheckStatus;
import logging.Logger;
import security.JWT;
import security.SecurityManager;
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
		if(flag) Logger.log(Logger.TYPE.CONNECTION_EVENT, Logger.LEVEL.INFO, "CONNECTION-SUCCESS-PING");
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
		if(flag) Logger.log(Logger.TYPE.LOGIN_EVENT, Logger.LEVEL.INFO, "AUTHENTICATION-SUCCESS-APP-" + username.toUpperCase());
		if(!flag) Logger.log(Logger.TYPE.LOGIN_EVENT, Logger.LEVEL.WARN, "AUTHENTICATION-FAILED-APP-" + username.toUpperCase());

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
		if(flag) Logger.log(Logger.TYPE.LOGIN_EVENT, Logger.LEVEL.INFO, "PASSWORD-RESET-SUCCESS-" + username.toUpperCase());
		if(!flag) Logger.log(Logger.TYPE.LOGIN_EVENT, Logger.LEVEL.ALERT, "PASSWORD-RESET-FAILED-" + username.toUpperCase());

		return flag;
	}
	
	public boolean log (Logger.TYPE type, Logger.LEVEL level, String message) {
		ParameterList pList = new ParameterList();
		pList.add("type", type.toString());
		pList.add("level", level.toString());
		pList.add("message", message);
		try {
			Response response = connectionManager.sendPostRequest("log", pList, ConnectionManager.AUTH_TYPE.NONE, null);
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
					allGarages.add(new Garage(
							arr.getJSONObject(i).getString("name"),
							arr.getJSONObject(i).getInt("id")
							));
				}
				
			} 
		} catch (Exception e){
			e.printStackTrace();
		}
		return allGarages;		
	}
	
	public GarageInfo getGarage(int id) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		GarageInfo gi = null;
		ArrayList<Instrument> instruments = new ArrayList<Instrument>();
		try{
			Response response = connectionManager.sendGetRequest("garages/"+id, null);
			if(response.getResponseCode() == 200) {
				JSONObject obj = response.getObject();
				
				JSONArray instArr = obj.getJSONArray("instruments");
				for(int i=0; i<instArr.length(); i++){
					CheckStatus status = CheckStatus.valueOf(instArr.getJSONObject(i).getString("ourCheckStatus"));
					
					instruments.add(new Instrument(
							instArr.getJSONObject(i).getInt("id"),
							instArr.getJSONObject(i).getString("name"),
							instArr.getJSONObject(i).getString("serialNumber"),
							formatter.parse(instArr.getJSONObject(i).getString("ourCheckDate")),
							formatter.parse(instArr.getJSONObject(i).getString("officialCheckExpiryDate")),
							status
							));
				}
				
				gi = new GarageInfo(
							obj.getString("vts"),
							obj.getString("ownerName"),
							obj.getString("emailAddress"),
							obj.getString("telephoneNumber"),
							formatter.parse(obj.getString("paidUntil")),
							instruments
						);
			}
		} catch	(Exception e) {
			e.printStackTrace();
		}
		return gi;
		
	}
}
