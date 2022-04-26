package security;
/**
 * Provides small methods for sanitising data
 * @author Scrub
 *
 */
public class Sanitiser {
	public static boolean containsColon(String input) {
		return input.contains(":");
	}
	public static String trim (String input) {
		return input.trim();
	}
}
