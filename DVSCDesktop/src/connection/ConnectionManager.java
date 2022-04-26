package connection;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPatch;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import utils.Console;

/**
 * 
 * @author Scrub
 *
 */
public class ConnectionManager {
	private static final boolean DEV_MODE = true;
	private String url;
	
	public ConnectionManager(String url){
		this.url = url;
	}
	
	/**
	 * Authentication enum for what type of authentication the HTTPS request should use
	 */
	public static enum AUTH_TYPE{
		JWT("bearer"),
		BASIC("basic"),
		NONE(null);

		public final String type;
		
		private AUTH_TYPE(String type) {
			this.type = type;
		}
	}
	/**
	 * Converts parameterlist object to BasicNameValuePair object for use in the request to properly encode the data
	 * @param pl
	 * @return UrlEncodedFormEntity
	 */
	@SuppressWarnings("unchecked")
	private UrlEncodedFormEntity parseParameterList(ParameterList pl){
		List<NameValuePair> parameterList = new ArrayList<>();
		pl.getList().forEach((k, v) -> {
			parameterList.add(new BasicNameValuePair((String) k,(String) v));
		});
		return new UrlEncodedFormEntity(parameterList);
	} 
	/**
	 * Sends a post request to an endpoint, if there is data to send then querylist is used as a parameterlist
	 * If the request requires authorisation then the method will require the AUTH_TYPE enum and then the next param is the string that will be sent as the auth header
	 * Returns a response object which holds data, response code and response message
	 * @param endpoint
	 * @param queryList
	 * @param authType
	 * @param authorisationString
	 * @return Response
	 * @throws Exception
	 */
	public Response sendPostRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception{
		if(queryList == null) queryList = new ParameterList();
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpPost httpPost = new HttpPost("https://" + this.url + "/api/" + endpoint);
			UrlEncodedFormEntity data = this.parseParameterList(queryList);
			httpPost.setEntity(data);
			if(authType != AUTH_TYPE.NONE) httpPost.setHeader("Authorization",authType.type + " " + authorisationString);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			try(CloseableHttpResponse response = httpClient.execute(httpPost)){
				HttpEntity entity = null;
				String output = "";
				if(response.getEntity() != null) {
					entity = response.getEntity();
					output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				} 
				if(DEV_MODE) {
					Console.log("POST | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("ARGS | " + new String(data.getContent().readAllBytes(), StandardCharsets.UTF_8));
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
					Console.log("AUTH | " + httpPost.getHeader("Authorization"));
				}
				Response res = new Response(output, response.getCode(),this.url + endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
			
		}
	}

	/**
	 * Sends a get request to an endpoint, if there is data to send then querylist is used as a parameterlist
	 * If the request requires authorisation then the method will require the AUTH_TYPE enum and then the next param is the string that will be sent as the auth header
	 * Returns a response object which holds data, response code and response message
	 * @param endpoint
	 * @param queryList
	 * @return Response
	 * @throws Exception
	 */
	public Response sendGetRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			if(queryList == null) queryList = new ParameterList();
			HttpGet httpGet = new HttpGet("https://" + this.url + "/api/" + endpoint + queryList.generateString());
			if(authType != AUTH_TYPE.NONE) httpGet.setHeader("Authorization",authType.type + " " + authorisationString);
			try(CloseableHttpResponse response = httpClient.execute(httpGet)){
				HttpEntity entity = null;
				String output = "";
				if(response.getEntity() != null) {
					entity = response.getEntity();
					 output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				} 
				if(DEV_MODE) {
					Console.log("GET  | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("ARGS | " + queryList.generateString());
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(),this.url + endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
		}
	}
	/**
	 * Sends a delete request to an endpoint, if there is data to send then querylist is used as a parameterlist
	 * If the request requires authorisation then the method will require the AUTH_TYPE enum and then the next param is the string that will be sent as the auth header
	 * Returns a response object which holds data, response code and response message
	 * @param endpoint
	 * @param queryList
	 * @param authType
	 * @param authorisationString
	 * @return Response
	 * @throws Exception
	 */
	public Response sendDeleteRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception {
		if(queryList == null) queryList = new ParameterList();
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpDelete httpDelete = new HttpDelete("https://" + this.url + "/api/" + endpoint);
			UrlEncodedFormEntity data = this.parseParameterList(queryList);
			httpDelete.setEntity(data);
			if(authType != AUTH_TYPE.NONE) httpDelete.setHeader("Authorization",authType.type + " " + authorisationString);
			httpDelete.setHeader("Accept", "application/json");
			httpDelete.setHeader("Content-type", "application/x-www-form-urlencoded");
			try(CloseableHttpResponse response = httpClient.execute(httpDelete)){
				HttpEntity entity = null;
				String output = "";
				if(response.getEntity() != null) {
					entity = response.getEntity();
					output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				} 
				if(DEV_MODE) {
					Console.log("DEL  | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("ARGS | " + queryList.generateString());
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(), this.url+endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
		}
	}
	/**
	 * Sends a patch request to an endpoint, if there is data to send then querylist is used as a parameterlist
	 * If the request requires authorisation then the method will require the AUTH_TYPE enum and then the next param is the string that will be sent as the auth header
	 * Returns a response object which holds data, response code and response message
	 * @param endpoint
	 * @param queryList
	 * @param authType
	 * @param authorisationString
	 * @return Response
	 * @throws Exception
	 */
	public Response sendPatchRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception{
		if(queryList == null) queryList = new ParameterList();
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpPatch httpPatch = new HttpPatch("https://" + this.url + "/api/" + endpoint);
			UrlEncodedFormEntity data = this.parseParameterList(queryList);
			httpPatch.setEntity(data);
			if(authType != AUTH_TYPE.NONE) httpPatch.setHeader("Authorization",authType.type + " " + authorisationString);
			httpPatch.setHeader("Accept", "application/json");
			httpPatch.setHeader("Content-type", "application/x-www-form-urlencoded");
			try(CloseableHttpResponse response = httpClient.execute(httpPatch)){
				HttpEntity entity = null;
				String output = "";
				if(response.getEntity() != null) {
					entity = response.getEntity();
					output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				} 
				if(DEV_MODE) {
					Console.log("DEL  | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("ARGS | " + queryList.generateString());
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(), this.url+endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
		}	
	}
	/**
	 * Sends a put request to an endpoint, if there is data to send then querylist is used as a parameterlist
	 * If the request requires authorisation then the method will require the AUTH_TYPE enum and then the next param is the string that will be sent as the auth header
	 * Returns a response object which holds data, response code and response message
	 * @param endpoint
	 * @param queryList
	 * @param authType
	 * @param authorisationString
	 * @return Response
	 * @throws Exception
	 */
	public Response sendPutRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception{
		if(queryList == null) queryList = new ParameterList();
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpPut httpPut = new HttpPut("https://" + this.url + "/api/" + endpoint);
			UrlEncodedFormEntity data = this.parseParameterList(queryList);
			httpPut.setEntity(data);
			if(authType != AUTH_TYPE.NONE) httpPut.setHeader("Authorization",authType.type + " " + authorisationString);
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-type", "application/x-www-form-urlencoded");
			try(CloseableHttpResponse response = httpClient.execute(httpPut)){
				HttpEntity entity = null;
				String output = "";
				if(response.getEntity() != null) {
					entity = response.getEntity();
					output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				} 
				/*
				 *  /\_/\
				 * ( o.o )
 				 *  > ^ <
 				 *   Cat
				 */
				if(DEV_MODE) {
					Console.log("DEL  | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("ARGS | " + queryList.generateString());
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(), this.url+endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
		}	
	}
}

