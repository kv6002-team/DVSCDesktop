package connection;

import org.json.JSONArray;

public class Connection {
	private ConnectionManager connectionManager = ConnectionManager.getInstance("dvsc.services");
	
	public boolean ping() {
		boolean flag = false;
		try {
			String response = connectionManager.sendPostRequest("/ping", null);
			ResponseParser responseParser = new ResponseParser(response);
			JSONArray responseArray = responseParser.getArray();
			System.out.println(responseArray.get(0));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
