package connection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Response {
	String response;
	int responseCode;
	String url;
	ParameterList parameters;
	
	/**
	 * @param response
	 * @param responseCode
	 * @param url
	 * @param parameters
	 */
	public Response(String response, int responseCode, String url, ParameterList parameters) {
		this.response = response;
		this.responseCode = responseCode;
		this.url = url;
		this.parameters = parameters;
	}
	
	public String getResponse() {
		return response;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public String getURL() {
		return url;
	}
	
	public ParameterList getParameters() {
		return parameters;
	}
	
	public JSONObject getObject() {
		return new JSONObject(response);
	}
	
	public JSONArray getArray() {
		return new JSONArray(response);
	}
}
