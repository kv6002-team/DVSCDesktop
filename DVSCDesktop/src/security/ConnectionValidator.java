package security;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;


public class ConnectionValidator {
		
	public static boolean testConntectionHTTPS(String https_url) {
		URL url;
		
		try {
			url = new URL("https://" + https_url);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.disconnect();

		} catch (MalformedURLException ex) {
			
		} catch (IOException ex) {
			return false;
		}
		return true;
	}
}