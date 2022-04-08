package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;

import security.JWT;
import utils.Console;
import connection.Response;

public class ConnectionManager {
	private static final boolean DEV_MODE = true;
	private String url;
	
	public ConnectionManager(String url){
		this.url = url;
	}
	
	public static enum AUTH_TYPE{
		JWT("bearer"),
		BASIC("basic"),
		NONE(null);

		public final String type;
		
		private AUTH_TYPE(String type) {
			this.type = type;
		}
	}
	public static enum REQ_TYPE{
		GET,
		POST,
		DELETE,
		PATCH,
		PUT;
	}
	
	/**
	 * Singleton
	 * @param url
	 * @return ConnectionManager
	 */
//	public static ConnectionManager getInstance(String url) {
//		if(instance == null) instance = new ConnectionManager(url);
//		return instance;
//	}
	
	/**
	 * Creates a URL
	 * @param url
	 * @return HttpsURLConnection
	 * @throws Exception 
	 */
	private HttpsURLConnection getConnection(String url) throws Exception {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, CertManager.getTrustManager(), new SecureRandom());
		
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		
		return connection;
	}
	
	private void setConnectionProperties(HttpsURLConnection con, int querySize, REQ_TYPE method) throws Exception {
		
		con.setRequestProperty("Content-length", Integer.toString(querySize));
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
        con.setRequestProperty("charset", "utf-8");
		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)");
		con.setRequestProperty("Accept", "application/json");
        con.setInstanceFollowRedirects(false);
        
        int timeout = 1000 * 10 * 6; // One minute
        con.setReadTimeout(timeout); 
        con.setConnectTimeout(timeout);
        
        con.setUseCaches(false);
		con.setRequestMethod(method.toString());
		con.setDoInput(true);
		con.setDoOutput(true);
	}
	
	private void setAuthenticationType(HttpsURLConnection con, AUTH_TYPE authType, String authorisationString) {
		if(authorisationString == null && authType == AUTH_TYPE.JWT && JWT.getInstance().isTokenSet()) authorisationString = JWT.getInstance().getToken();
		con.setRequestProperty("Authorization", authType + " " + authorisationString);
	}
	
	public Response sendPostRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception{
		
		if(queryList == null) queryList = new ParameterList();
		
		String httpsURL = "https://" + url;
		String fullURL = httpsURL + "/api/" + endpoint;
        
        byte[] postData = queryList.generateString().getBytes(StandardCharsets.UTF_8);

        int postDataLength = postData.length;
 
        HttpsURLConnection connection = getConnection(fullURL);
        setConnectionProperties(connection, postDataLength, REQ_TYPE.POST);

        if(authType != AUTH_TYPE.NONE) {
        	setAuthenticationType(connection, authType, authorisationString);
        }
        
        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData);
        }
        
        if(DEV_MODE) {
            int responseCode = connection.getResponseCode();
            System.out.println("POST request to URL: " + fullURL);
            System.out.println("POST Parameters    : " + queryList.generateString());
            System.out.println("Response Code      : " + responseCode);
        }
 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
 
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            connection.disconnect();
            return new Response(response.toString(), connection.getResponseCode(), fullURL, queryList);
        }
	}
	
	public Response sendGetRequest(String endpoint, ParameterList queryList) throws Exception {
		
		if(queryList == null) queryList = new ParameterList();

		String httpsURL = "https://" + url;
		String fullURL = httpsURL +  "/api/" + endpoint;
		
		HttpsURLConnection connection = getConnection(fullURL + queryList.generateString());
		
		if(DEV_MODE) {
	        System.out.println("GET request to URL: " + fullURL);
	        System.out.println("GET Parameters    : " + queryList.generateString());
	        System.out.println("Response Code      : " + connection.getResponseCode());
		}
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
			StringBuilder response = new StringBuilder();
			String line;
			
			while((line = in.readLine()) != null) {
				response.append(line).append("\n");
			}
			connection.disconnect();
			return new Response(response.toString(), connection.getResponseCode(), fullURL, queryList);
		}
	}
	
	public Response sendDeleteRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception {
		if(queryList == null) queryList = new ParameterList();
		
		String httpsURL = "https://" + url;
		String fullURL = httpsURL + "/api/" + endpoint;
        
        byte[] postData = queryList.generateString().getBytes(StandardCharsets.UTF_8);

        int postDataLength = postData.length;
 
        HttpsURLConnection connection = getConnection(fullURL);
        setConnectionProperties(connection, postDataLength, REQ_TYPE.DELETE);

        if(authType != AUTH_TYPE.NONE) {
        	setAuthenticationType(connection, authType, authorisationString);
        }
        
        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData);
        }
        
        if(DEV_MODE) {
            int responseCode = connection.getResponseCode();
            System.out.println("DELETE request to URL: " + fullURL);
            System.out.println("DELETE Parameters    : " + queryList.generateString());
            System.out.println("Response Code      : " + responseCode);
        }
 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
 
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            connection.disconnect();
            return new Response(response.toString(), connection.getResponseCode(), fullURL, queryList);
        }
	}
	public Response sendPatchRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception {
		if(queryList == null) queryList = new ParameterList();
		
		String httpsURL = "https://" + url;
		String fullURL = httpsURL + "/api/" + endpoint;
        
        byte[] postData = queryList.generateString().getBytes(StandardCharsets.UTF_8);

        int postDataLength = postData.length;
 
        HttpsURLConnection connection = getConnection(fullURL);
        setConnectionProperties(connection, postDataLength, REQ_TYPE.PATCH);

        if(authType != AUTH_TYPE.NONE) {
        	setAuthenticationType(connection, authType, authorisationString);
        }
        
        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData);
        }
        
        if(DEV_MODE) {
            int responseCode = connection.getResponseCode();
            System.out.println("PATCH request to URL: " + fullURL);
            System.out.println("PATCH Parameters    : " + queryList.generateString());
            System.out.println("Response Code      : " + responseCode);
        }
 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
 
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            connection.disconnect();
            return new Response(response.toString(), connection.getResponseCode(), fullURL, queryList);
        }
	}
	public Response sendPutRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception {
		if(queryList == null) queryList = new ParameterList();
		
		String httpsURL = "https://" + url;
		String fullURL = httpsURL + "/api/" + endpoint;
        
        byte[] postData = queryList.generateString().getBytes(StandardCharsets.UTF_8);

        int postDataLength = postData.length;
 
        HttpsURLConnection connection = getConnection(fullURL);
        setConnectionProperties(connection, postDataLength, REQ_TYPE.PUT);

        if(authType != AUTH_TYPE.NONE) {
        	setAuthenticationType(connection, authType, authorisationString);
        }
        
        try (OutputStream os = connection.getOutputStream()) {
            os.write(postData);
        }
        
        if(DEV_MODE) {
            int responseCode = connection.getResponseCode();
            System.out.println("PUT request to URL: " + fullURL);
            System.out.println("PUT Parameters    : " + queryList.generateString());
            System.out.println("Response Code      : " + responseCode);
        }
 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
 
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }
            connection.disconnect();
            return new Response(response.toString(), connection.getResponseCode(), fullURL, queryList);
        }
	}
	public static Response test() throws Exception {
		URL url = new URL("https://dvsc.services/api/ping");
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setConnectTimeout(5000);
		con.setReadTimeout(5000);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		Console.log(in);
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		Console.log(content.toString());

		return new Response("xx", 100, "xx", new ParameterList());
	}
}
