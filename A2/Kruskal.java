package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* TO DO Fill this method (The statement return null is here only to compile) */
    	WGraph mst = new WGraph();
    	DisjointSets set = new DisjointSets(g.getNbNodes());
    	
    	// sort edges
    	ArrayList<Edge> gSorted = g.listOfEdgesSorted();
    	
    	// determine if edge connects vertices in diff. components
    	for (Edge e : gSorted)
    	{
    		if (IsSafe(set, e))
    		{
    			mst.addEdge(e);
    			set.union(e.nodes[0], e.nodes[1]);
    		}
    	}
        return mst;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* TO DO Fill this method (The statement return 0 is here only to compile) */
       
    	if (p.find(e.nodes[0]) != p.find(e.nodes[1]))
    	{
    		return true;
    	}
    	return false;
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
