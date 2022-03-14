package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class ConnectionManager {
	private ConnectionManager instance;
	private String url;
	
	ConnectionManager(String url){
		this.url = url;
	}
	
	/**
	 * Singleton
	 * @param url
	 * @return
	 */
	public ConnectionManager getInstance(String url) {
		if(instance == null) instance = new ConnectionManager(url);
		return instance;
	}
	
	/**
	 * Creates a URL
	 * @param url
	 * @return
	 */
	private HttpsURLConnection getConnection(String url) {
		HttpsURLConnection connection = null;
		URL https_url = null;
		try {
			https_url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			connection = (HttpsURLConnection) https_url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * Sets the connection properties of the connection
	 * @param con
	 * @param query
	 */
	private void setConnectionProperties(HttpsURLConnection con, String query) {
		con.setRequestProperty("Content-length", String.valueOf(query.length()));
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
		con.setRequestProperty("Accept", "application/json");

		
		try {
			con.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		
		con.setDoInput(true);
		con.setDoOutput(true);
	}
	
	/**
	 * Takes an endpoint and appends it to {URL}/api/.
	 * Takes a parameterList object to send to endpoint
	 * @param endpoint
	 * @param queryList
	 * @return {String} Response from server
	 */
	public String sendQuery(String endpoint, ParameterList queryList) {
		boolean DEV_MODE = true;
		
		String httpsURL = "https://" + url;
		String fullURL = httpsURL + "/api/" + endpoint;
		
		String queryString = queryList.generateString();
		HttpsURLConnection con = getConnection(fullURL);
		
		setConnectionProperties(con, queryString);
		DataOutputStream output = null;
		DataInputStream input = null;
		
		String dataInputString = "";
		
		try {
			output = new DataOutputStream(con.getOutputStream());
			
			if(queryList != null) output.writeBytes(queryString);
			
			output.close();
			
			input = new DataInputStream(con.getInputStream());
			for(int c = input.read(); c != -1; c = input.read()) {
				dataInputString += (char) c;
			}
			
			if(DEV_MODE) {
				System.out.println(dataInputString);
				System.out.println(con.getResponseMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		con.disconnect();
		return dataInputString;
	}	
}
