package security;
/**
 * JWT Singleton for storing a JWT Token provided by the serber
 * @author Scrub
 *
 */
public class JWT {
	private static JWT instance;
	private String token;
	private boolean tokenSet = false;
	
	public static JWT getInstance() {
		if(instance == null) instance = new JWT();
		return instance;
	}
	
	public String getToken() {
		return token;
	}
	
	public boolean isTokenSet() {
		return tokenSet;
	}
	
	public void setToken(String token) {
		tokenSet = true;
		this.token = token;
	}
	
	public void clearToken() {
		tokenSet = false;
		this.token = "";
	}
	
}
