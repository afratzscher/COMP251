package A3;
import java.io.*;
import java.util.*;

public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE */
		ArrayList<Integer> discovered = new ArrayList<Integer>();
		discovered.add(source);
		Stack.add(source);
		
		// continue until all nodes discovered and finished 
		while(discovered.size() > 0) {
			Integer v = discovered.remove(0);
			if (v == destination) {
				break;
			}
			Integer u;
			for (Edge e : graph.listOfEdgesSorted()) {
				if (v == e.nodes[0]) {
					u = e.nodes[1];
					if (!Stack.contains(u) && e.weight != 0) {
						discovered.add(u);
						Stack.add(u);
						break;
					}
				}
			}
		}
		return Stack;
	}
	
	public static boolean augmenting(ArrayList<Integer> Stack, Integer source, WGraph graph) {
		boolean augmenting = true; // initially true
		if (Stack.contains(graph.getSource()) && Stack.size() == 1) {
			augmenting = false;
		}
		return augmenting;
	}
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260705446"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		/* YOUR CODE GOES HERE */
		WGraph residualGraph = new WGraph(graph);
		
		// set edge flows to 0
		for (Edge e: graph.getEdges()) {
			e.weight = 0;
		}
		ArrayList<Integer> paths = pathDFS(source, destination, residualGraph);
		
		//while can augment path
		while (augmenting(paths, residualGraph.getSource(), residualGraph)) {
			if (paths.contains(residualGraph.getDestination())){
				int bottleneck = residualGraph.getEdge(paths.get(0), paths.get(1)).weight;
				for (int i = 0; i < paths.size() - 1; i++) {
					if (residualGraph.getEdge(paths.get(i), paths.get(i+1)).weight < bottleneck) {
						bottleneck = residualGraph.getEdge(paths.get(i), paths.get(i+1)).weight;
					}
				}
				maxFlow += bottleneck;
				for (int j = 0; j < paths.size() - 1; j++) {
					graph.getEdge(paths.get(j), paths.get(j+1)).weight += bottleneck;
					residualGraph.getEdge(paths.get(j), paths.get(j+1)).weight -= bottleneck;
				}
			}
			if (!paths.contains(residualGraph.getDestination()) && augmenting(paths, residualGraph.getSource(), residualGraph)) {
				// if path doesnt include destination AND have augmenting path, change all nodes to 0
				for (int k = 0; k < paths.size() - 1; k++) {
					residualGraph.getEdge(paths.get(k), paths.get(k+1)).weight = 0;
				}
			}
			paths = pathDFS(source, destination, residualGraph);
		}
		
		
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
