package connection;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import utils.Console;

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
	
	@SuppressWarnings("unchecked")
	private UrlEncodedFormEntity parseParameterList(ParameterList pl){
		List<NameValuePair> parameterList = new ArrayList<>();
		pl.getList().forEach((k, v) -> {
			parameterList.add(new BasicNameValuePair((String) k,(String) v));
		});
		return new UrlEncodedFormEntity(parameterList);
	} 
	
	public Response sendPostRequest(String endpoint, ParameterList queryList, AUTH_TYPE authType, String authorisationString) throws Exception{
		if(queryList == null) queryList = new ParameterList();
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			HttpPost httpPost = new HttpPost("https://" + this.url + "/api/" + endpoint);
			httpPost.setEntity(this.parseParameterList(queryList));
			if(authType != AUTH_TYPE.NONE) httpPost.setHeader("Authorization",authType.type + " " + authorisationString);
			try(CloseableHttpResponse response = httpClient.execute(httpPost)){
				HttpEntity entity = response.getEntity();
				String output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				if(DEV_MODE) {
					Console.log("POST | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(),this.url + endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
			
		}
	}
	
	public Response sendGetRequest(String endpoint, ParameterList queryList) throws Exception {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()){
			if(queryList == null) queryList = new ParameterList();
			HttpGet httpGet = new HttpGet("https://" + this.url + "/api/" + endpoint + queryList.generateString());
			
			try(CloseableHttpResponse response = httpClient.execute(httpGet)){
				HttpEntity entity = response.getEntity();
				String output = new String(entity.getContent().readAllBytes(), StandardCharsets.UTF_8);
				if(DEV_MODE) {
					Console.log("GET  | " + "https://" + this.url + "/api/" + endpoint);
					Console.log("CODE | " + response.getCode());
					Console.log("RESP | " + output);
				}
				Response res = new Response(output, response.getCode(),this.url + endpoint, queryList);
				EntityUtils.consume(entity);
				return res;
			}
		}
	}
}
