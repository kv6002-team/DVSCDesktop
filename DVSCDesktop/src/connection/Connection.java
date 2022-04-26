package connection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.Garage;
import domain.GarageInfo;
import domain.Instrument;
import domain.Instrument.CheckStatus;
import gui.AlertDialog;
import logging.Logger;
import security.JWT;
import security.SecurityManager;
/**
 * Connection class provides abstraction methods using the ConnectionManager to communicate with the server
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
	/**
	 * Takes a username and password then parses and encodes them into b64 and sends it as an authorisation header.
	 * Sets the JWT token singleton and returns true if login is valid
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public boolean authenticate(String username, String password) {
		boolean flag = false;
		String authString = SecurityManager.encode(username + ":" + password);
		try {
			ParameterList pl = new ParameterList();
			pl.add("types", "garage-consultant");
			Response response = connectionManager.sendPostRequest("auth", pl, ConnectionManager.AUTH_TYPE.BASIC, authString);
			JSONObject resObj = response.getObject();
			if(response.getResponseCode() == 200) {
				if(resObj.getString("token_type").equals("bearer")) {
					JWT.getInstance().setToken(resObj.getString("token"));
					flag = true;
				}	
			}else {
				AlertDialog x = new AlertDialog();
				x.run("Authentication Error: Invalid credentials. Please try logging in again!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * Sends a password reset request to the server with the username provided
	 * @param username
	 * @return boolean
	 */
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
	
	/**
	 * Allows the app to log app events to the server
	 * @param type
	 * @param level
	 * @param message
	 * @return boolean
	 */
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
	
	/**
	 * Gets a list of all garage IDs and Names
	 * @return ArrayList<Garage>
	 */
	public ArrayList<Garage> getAllGarages() {
		ArrayList<Garage> allGarages = new ArrayList<Garage>();
		try {
			Response response = connectionManager.sendGetRequest("garages", null, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
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
	
	/**
	 * Gets a garages info based on the ID provided
	 * @param id
	 * @return
	 */
	public GarageInfo getGarage(int id) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		GarageInfo gi = null;
		ArrayList<Instrument> instruments = new ArrayList<Instrument>();
		try{
			Response response = connectionManager.sendGetRequest("garages/"+id, null, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 200) {
				JSONObject obj = response.getObject();
				
				JSONArray instArr = obj.getJSONArray("instruments");
				for(int i=0; i<instArr.length(); i++){
					CheckStatus status = CheckStatus.of(instArr.getJSONObject(i).getString("ourCheckStatus"));
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
	
	public int addGarage(Garage garage) {
		ParameterList pl = new ParameterList();
		pl.add("vts", garage.getGarageInfo().getVts());
		pl.add("name", garage.getGarageName());
		pl.add("ownerName", garage.getGarageInfo().getOwnerName());
		pl.add("emailAddress", garage.getGarageInfo().getEmailAddress());
		pl.add("telephoneNumber", garage.getGarageInfo().getTelephoneNum());
		pl.add("paidUntil", garage.getGarageInfo().getPaidUntil().toString());
		pl.add("password", "password");
		try {
			Response response = connectionManager.sendPostRequest("garages", pl, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 201) {
				JSONObject obj = response.getObject();
				return obj.getInt("id");
			}
			else if(response.getResponseCode() == 204) {
				return -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	
	}
	
	public boolean removeGarage(int id) {
		try {	
			Response response = connectionManager.sendDeleteRequest("garages/"+String.valueOf(id), null, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 204) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int addInstrument(Garage garage, Instrument instrument) {
		ParameterList pl = new ParameterList();
		pl.add("garageID", garage.getGarageID().toString());
		pl.add("name", instrument.getInstrumentName());
		pl.add("serialNumber", instrument.getSerialNum());
		pl.add("officialCheckExpiryDate", instrument.getStatusExpiryDate().toString());
		pl.add("ourCheckStatus", instrument.getCheckStatus().value);
		pl.add("ourCheckDate", instrument.getCheckDate().toString());
		try {
			Response response = connectionManager.sendPostRequest("instruments", pl, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 201) {
				JSONObject obj = response.getObject();
				return obj.getInt("id");
			}
			else if(response.getResponseCode() == 204) {
				return -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean removeInstrument(int id) {
		try {
			Response response = connectionManager.sendDeleteRequest("instruments/"+String.valueOf(id), null, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 204) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean saveChanges(Garage garage) {
		JSONObject garageJSON = new JSONObject();
		garageJSON.put("vts", garage.getGarageInfo().getVts());
		garageJSON.put("name", garage.getGarageName());
		garageJSON.put("ownerName", garage.getGarageInfo().getOwnerName());
		garageJSON.put("emailAddress", garage.getGarageInfo().getEmailAddress());
		garageJSON.put("telephoneNumber", garage.getGarageInfo().getTelephoneNum());
		garageJSON.put("paidUntil", garage.getGarageInfo().getPaidUntil());
		JSONArray instrumentsJSON = new JSONArray();
		for(Instrument instrument : garage.getGarageInfo().getInstrumentList()) {
			JSONObject instrumentJSON = new JSONObject();
			instrumentJSON.put("id", instrument.getInstrumentID());
			instrumentJSON.put("name", instrument.getInstrumentName());
			instrumentJSON.put("officialCheckExpiryDate", instrument.getStatusExpiryDate());
			instrumentJSON.put("ourCheckStatus", instrument.getCheckStatus().toString().toLowerCase());
			instrumentJSON.put("ourCheckDate", instrument.getCheckDate());
			
			instrumentsJSON.put(instrumentJSON);
		}		
		garageJSON.put("instruments", instrumentsJSON);
		
		ParameterList pl = new ParameterList();
		pl.add("garage", garageJSON.toString());
		
		try {
			Response response = connectionManager.sendPatchRequest("garages/"+String.valueOf(garage.getGarageID()), pl, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
			if(response.getResponseCode() == 204) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Response sendEmailList(ArrayList<Integer> emailList) {
		ParameterList pl = new ParameterList();
		pl.add("garages", Arrays.toString(emailList.toArray()));
		Response response = null;
		try {
			response = connectionManager.sendPostRequest("send-emails", pl, ConnectionManager.AUTH_TYPE.JWT, JWT.getInstance().getToken());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}
}
