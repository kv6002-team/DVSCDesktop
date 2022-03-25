package security;
/**
 * 
 * @author Scrub
 *
 */
public class JWT {
	private static JWT instance;
	private String token;
	
	public static JWT getInstance() {
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
