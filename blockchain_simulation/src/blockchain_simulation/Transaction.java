package blockchain_simulation;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.atomic.AtomicLong;

import utils.Utils;

public class Transaction {
	String txId;
	PublicKey sender;
	PublicKey reciepient;
//	double value;
	byte[] signature;
	String data;
	
	private static AtomicLong sequence = new AtomicLong(0);
	// TODO: inputs, outputs, value.

	public Transaction(PublicKey sender, PublicKey reciepient, PrivateKey pr) throws Exception {
		this.sender = sender;
		this.reciepient = reciepient;
		txId = Utils.hash(Utils.getStringFromKey(sender) + Utils.getStringFromKey(reciepient)
		+ sequence.getAndIncrement());
		data = Utils.getStringFromKey(sender) + Utils.getStringFromKey(reciepient);
		genSignature(pr);
	}
	
	private static boolean verifySignature(Transaction tx) throws Exception{
		return Utils.verifyECDSASig(tx.sender, tx.data, tx.signature);
	}
	
	public static boolean verify(Transaction tx) throws Exception {
		// TODO: verify sender's available funds.
		return verifySignature(tx);
	}
	
	// Private key is not saved in the transaction.
	// The transaction creator passes it to calculate their signature and then sends it
	// to the network, thus no one knows their private key.
	private void genSignature(PrivateKey pr) throws Exception { 
		signature = Utils.applyECDSASig(pr, data);
	}
}
