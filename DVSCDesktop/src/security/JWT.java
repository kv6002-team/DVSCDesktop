package security;

public class JWT {
	private JWT instance;
	private String token;
	
	public JWT getInstance() {
		if(instance == null) instance = new JWT();
		return instance;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
