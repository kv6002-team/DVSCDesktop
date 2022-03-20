package connection;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author Scrub
 *
 */
public class ResponseParser {
	String response;
	JSONObject objectResults;
	ArrayList<String> arrayResults = new ArrayList<>();
	ArrayList<JSONObject> JSONArrayResults = new ArrayList<>();
	
	
	public ResponseParser(String input) {
		response = input;
	}
	
	public ResponseParser parseArray() {
		JSONArray array = new JSONArray(response);
		if(array != null) {
			for(int i = 0; i < array.length(); i++) {
				arrayResults.add(array.getString(i));
			}
		}
		return this;
	}
	
	public ResponseParser thatContainsJSONObjects() {
		arrayResults.forEach(t -> {
			JSONArrayResults.add(new JSONObject(t));
		});
		return this;
	}
	
	public ResponseParser parseJSON() {
		objectResults = new JSONObject(response);
		return this;
	}
	public JSONObject getResultsAsJSON() {
		return objectResults;
	}
	
	public ArrayList<String> getResultsAsArrayList() {
		return arrayResults;
	}
	public ArrayList<JSONObject> getResultsAsArrayListOfObjects() {
		return JSONArrayResults;
	}
}
