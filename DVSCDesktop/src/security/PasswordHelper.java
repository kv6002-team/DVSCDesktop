package security;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHelper {
	private static final int ITERATION_COUNT = 10000;
	
	private static byte[] getSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	private static String toHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for(byte b : bytes) {
			sb.append(String.format("02%x",b));
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unused")
	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		
		for(int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2* i + 2));
		}
		return bytes;
	}
	
	public static String generatePassword(String password) {
		
		int iterations = ITERATION_COUNT;
		char[] charArr = password.toCharArray();
		byte[] salt = getSalt();
		
		PBEKeySpec spec = new PBEKeySpec(charArr, salt, iterations , 256);
		SecretKeyFactory keyFac = null;
		try {
			keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacsSHA256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = null;
		try {
			hash = keyFac.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
	
	public static String generateRandomPasswordString() {
		SecureRandom sr = new SecureRandom();
		int passLength = 16;		

		int leftLimit = 33;
		int rightLimit = 122;
		
		StringBuilder symbols = new StringBuilder(rightLimit - leftLimit);
		for(int i = leftLimit; i <= rightLimit; i++) {
			symbols.append((char)i);
		}
	    while(true) {
	        char[] generatedString = new char[passLength];
	        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
	        for(int i=0; i<generatedString.length; i++) {
	            char character = symbols.toString().charAt(sr.nextInt(symbols.length()));
	            if(Character.isUpperCase(character)) hasUpper = true;
	            else if(Character.isLowerCase(character)) hasLower = true;
	            else if(Character.isDigit(character)) hasDigit = true;
	            else hasSpecial = true;
	            generatedString[i] = character;
	        }
	        if(hasUpper && hasLower && hasDigit && hasSpecial) {
	            return new String(generatedString);
	        }
	    }
	}
	
	public static boolean validatePassword(String password, String storedPassword) {
		
		return false;
	}
	
}
