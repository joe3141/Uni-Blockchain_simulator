package utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class Utils {
	
	// Function referred from:
	// http://www.baeldung.com/sha-256-hashing-java
	public static String hash(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
			
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	public static boolean verifyECDSASig(PublicKey pb, String data, byte[] signature) throws Exception {
		Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
		ecdsaVerify.initVerify(pb);
		ecdsaVerify.update(data.getBytes());
		return ecdsaVerify.verify(signature);
	}

	public static byte[] applyECDSASig(PrivateKey pr, String data) throws Exception {
		Signature dsa = Signature.getInstance("ECDSA", "BC");
		dsa.initSign(pr);
		byte[] strByte = data.getBytes();
		dsa.update(strByte);
		return dsa.sign();
	}
}

