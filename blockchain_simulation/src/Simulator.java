import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import blockchain_simulation.Node;
import blockchain_simulation.Seed;


public class Simulator {

	public static void main(String[] args) throws Exception {
		int nodeNum = 10;
		ExecutorService es = Executors.newFixedThreadPool(nodeNum);
		ArrayList<Node> initNodes = Seed.init(); // initially has 10 nodes
		
		for(Node n: initNodes) {
			es.execute(n);
		}
		
		for(int i = 0; i < nodeNum - 10; ++i) 
			es.execute(new Node(UUID.randomUUID()));
	}

}
