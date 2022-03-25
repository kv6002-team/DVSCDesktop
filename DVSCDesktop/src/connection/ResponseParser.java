package connection;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author Scrub
 *
 */
public class ResponseParser {
	private String response;
	
	public ResponseParser(String input) {
		this.response = input;
	}
	
	public JSONObject getObject() {
		return new JSONObject(response);
	}
	public JSONArray getArray() {
		return new JSONArray(response);
	}
}
