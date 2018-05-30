package blockchain_simulation;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


// TODO: Add unspent txs
public class Wallet {
	PublicKey pb;
	PrivateKey pr;
	
	public Wallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
//		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
		keyGen.initialize(ecSpec, random); 
		KeyPair keyPair = keyGen.generateKeyPair();
		
    	pr = (PrivateKey) keyPair.getPrivate();
    	pb = (PublicKey) keyPair.getPublic();
	}
}
