package blockchain_simulation;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Seed {
	
	volatile static ConcurrentHashMap<UUID, Node> allNodes;
	static Random ran = new Random();
	
	 private Seed() {
	        // TODO Auto-generated constructor stub
	    allNodes = new ConcurrentHashMap<>();

	    }

	public static PublicKey getRandomAddress() {
//		Node[] tmp = (Node[]) allNodes.values().toArray();
//		return tmp[num].wallet.pb;
        ArrayList<UUID> keysAsArray = new ArrayList<UUID>(allNodes.keySet());
		int num = ran.nextInt(allNodes.size());
        UUID id = keysAsArray.get(num);
        Node newNode = allNodes.get(id);
		return newNode.wallet.pb;
	}

	public static ArrayList<Node> init() {
		ran = new Random();
		allNodes = new ConcurrentHashMap<>();
		ArrayList<Node>n=new ArrayList<>();
		for(int i=0;i<10;i++) {
			UUID x=UUID.randomUUID();
			Node s = new Node(x, false);
			s.blockChain = new LinkedHashMap<>();
			s.blockChain.put("Genesis", new Block());
			s.neighbors = new CopyOnWriteArrayList<>();
			s.prevHash = "Genesis";
			n.add((s));
			allNodes.put(x, s);
		}
		

	for(int i=0;i<n.size();i++) {
		
        NodeInterface myStuff = new NodeInterface(n.get(i).txPool, n.get(i).blockBuffer);

		for(int j=0;j<n.size();j++) {
			NodeInterface hisStuff = new NodeInterface(n.get(j).txPool, n.get(j).blockBuffer);
			if(i!=j) {
				n.get(i).neighbors.add(hisStuff);
				n.get(j).neighbors.add(myStuff);
			}
		}
		}
	return n;
	}
	 public static  ArrayList<Object> addNeighbors(Node n){
		    
	        int neighbors = ran.nextInt(6) + 5;

	        CopyOnWriteArrayList<NodeInterface> returnedNeighbors = new CopyOnWriteArrayList<NodeInterface>();
	        ArrayList<UUID> keysAsArray = new ArrayList<UUID>(allNodes.keySet());
	        NodeInterface myStuff = new NodeInterface(n.txPool, n.blockBuffer);
	        LinkedHashMap<String, Block> _blockChain = null;
	        int maxLength = -1;
	        
	        for (int i = 0; i < neighbors; i++) {
	            int nodeNumber = ran.nextInt(allNodes.size());
	            UUID id = keysAsArray.get(nodeNumber);
	            Node newNode = allNodes.get(id);
	            NodeInterface p = new NodeInterface(newNode.txPool,newNode.blockBuffer);
	            returnedNeighbors.add(p);
	            newNode.neighbors.add(myStuff); //rustom 7asses en fy 7aga ghalat xD
	            if(newNode.blockChain.size() > maxLength) {
	            	_blockChain = newNode.blockChain;
	            	maxLength = newNode.blockChain.size();
	            }
	        }
	        
	        ArrayList<Object> res = new ArrayList<>();
	        res.add(returnedNeighbors); res.add(_blockChain);
	        return res;
	    } 

}
