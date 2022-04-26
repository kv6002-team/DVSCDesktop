package connection;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * If the response needs to be in a different format, use this for converting from JSONObject to json array and vice versa
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
