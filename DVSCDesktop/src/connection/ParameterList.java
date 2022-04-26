package connection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides a method for storing KV pairs for sending to the server
 * @author Scrub
 *
 */
public class ParameterList {

	// Main Map variable
	private Map<String,String> parameterList = new HashMap<String,String>();
	
	/**
	 * Adds a new entry to the parameter map
	 * @param key
	 * @param value
	 */
	public void add(String key, String value) {
		parameterList.put(key, value);
	}
	/**
	 * Adds a Map to the parameter map
	 * @param map
	 */
	public void add(Map<String, String> map) {
		for(Map.Entry<String, String> entry: map.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * removes an entry from the parameter map using its key
	 * @param key
	 */
	public void remove(String key) {
		parameterList.remove(key);
	}
	public String getParam(String Key) {
		return parameterList.get(Key);
	}
	
	/**
	 * clears the parameter map
	 */
	public void clear() {
		parameterList.clear();
	}
	
	/**
	 * generates a URL query string from the map
	 * @return String
	 */
	public String generateString() {
		ArrayList<String> outputArray = new ArrayList<>();
		String output = "";
		for(Map.Entry<String, String> entry : parameterList.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			try {
				outputArray.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		output = String.join("&", outputArray);
		return (outputArray.size() > 0) ? "?" + output : "";
	}
	public Map getList() {
		return parameterList;
	}
}
