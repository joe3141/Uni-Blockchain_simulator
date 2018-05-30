package blockchain_simulation;

import java.util.concurrent.CopyOnWriteArrayList;

public class NodeInterface {
	private volatile CopyOnWriteArrayList<Transaction> txPool;
	private volatile CopyOnWriteArrayList<Block> blockBuffer;
	
	public NodeInterface(CopyOnWriteArrayList<Transaction> txPool, CopyOnWriteArrayList<Block> blockBuffer) {
		super();
		this.txPool = txPool;
		this.blockBuffer = blockBuffer;
	}
	
	public void addTx(Transaction tx) {
		txPool.add(tx);
	}
	
	public void addBlock(Block b) {
		blockBuffer.add(b);
	}
}
