package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	
    	DisjointSets comps = new DisjointSets(g.getNbNodes());
		WGraph graph = new WGraph();
		for (Edge e : g.listOfEdgesSorted()) {

			if (IsSafe(comps, e)) {

				comps.union(e.nodes[0], e.nodes[1]);
				graph.addEdge(e);
			}
		}
		return graph;
	}    

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Fill this method (The statement return 0 is here only to compile) */
    	
    	if (p.find(e.nodes[0]) != p.find(e.nodes[1])) {
    		
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
