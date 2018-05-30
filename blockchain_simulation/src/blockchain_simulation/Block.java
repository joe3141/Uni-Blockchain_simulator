package blockchain_simulation;

import java.util.ArrayList;
import java.util.Date;

import utils.Utils;

public class Block {
	public String hash;
	public String prevHash;
	public ArrayList<Transaction> data;
	private long timestamp;
	public int nonce = 0;
	int ttl = 0; // Incremented every time a block is added to the block chain
	int index;
	
	public Block(ArrayList<Transaction> data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}
	
	public Block() {
		this.hash = "Genesis";
		index = 0;
	}
	
	public String calculateHash() {
		return Utils.hash(prevHash + Long.toString(timestamp) + Integer.toString(nonce) + data);
	}
}
