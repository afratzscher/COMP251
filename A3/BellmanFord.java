package A3;
public class BellmanFord{

	
	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 * 
	 * Use this to specify a negative cycle has been found 
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();} 
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}
	
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
        
        /* YOUR CODE GOES HERE */
    	this.source = source;
    	int numNodes = g.getNbNodes();
    	distances = new int[numNodes];
    	predecessors = new int[numNodes];
    	
    	// set to -1 if no predecessor
    	for (int i = 1; i < numNodes; i++) {
    		distances[i] = Integer.MAX_VALUE;
    		predecessors[i] = -1;
    	}
    	
    	//distance of source to itself is ZERO
    	distances[source] = 0; 
    	
    	// relax each (u,v) in E[G]
    	for (int i = 0; i < g.getNbNodes()-1; i++) {
    		boolean not_updated = true;
    		for (int j = 0; j < g.getEdges().size(); j++) {
    			Edge e = g.getEdges().get(j);
    			Integer u = e.nodes[0];
    			Integer v = e.nodes[1];
    			
    			if (distances[v] > (distances[u]+e.weight)) {
    				not_updated = false;
    				distances[v] = (distances[u]+e.weight);
    				predecessors[v] = u;
    			}
    		}
    		if(not_updated) {
    			break;
    		}
    	}
    	
    	// look for negative cycle
    	boolean has_neg_cycle = false;
    	for (int i = 0; i < g.getEdges().size(); i++) {
    		Edge e = g.getEdges().get(i);
    		int u = e.nodes[0];
    		int v = e.nodes[1];
    		
    		if (distances[v] > (distances[u] + e.weight)) {
    			has_neg_cycle = true; 
    			break;
    		}
    		
    	}
    	
    	// if found negative cycle, throw exception
    	if(has_neg_cycle) {
			throw new NegativeWeightException("Negative cycle found!");
    	}
    }

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */

    	//using predecessor's array (since cant import arraylist)
    	int[] shortest_reverse = new int[predecessors.length];
    	shortest_reverse[0] = destination;
    	int i = 1; //next available slot
    	int num = 0; // length of path
    	int predecessor = this.predecessors[destination];
    	
    	while (predecessor != source) {
    		shortest_reverse[i] = predecessor;
    		i++;
    		num++;
    		predecessor = this.predecessors[predecessor];
    		if (predecessor == -1) {
    			throw new PathDoesNotExistException("Path does not exist!");
    		}
    	}
    	
    	// then reverse to get shortest path
    	int[] shortest = new int[num+2];
    	shortest[0] = source;
    	int j = 1;
    	for (int k = num; k>=0; k--) {
    		shortest[j] = shortest_reverse[k];
    		j++;
    	}
    	
        return shortest;
    }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
