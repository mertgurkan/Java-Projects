import java.io.*;
import java.util.*;




public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		//YOUR CODE GOES HERE
		
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> visited = new ArrayList<>();

        visited.add(source);
        path.add(source);

        
        while(!path.isEmpty()){
        	
        	
        	int sizePath = path.size() - 1;
            Integer x = path.get(sizePath);
            path.remove(sizePath);
            visited.add(x);
            
            
            while(!Stack.isEmpty()){
            	int stackSize = Stack.size() - 1;
                Integer y = Stack.get(stackSize);
                Edge edge = graph.getEdge(y, x);
                
                if(edge == null || edge.weight == 0) {
                    Stack.remove(stackSize);
                } else {
                    break;
                }
            }
            
            
            Stack.add(x);
            
            for(Edge edge: graph.getEdges()) {
            	
                if((edge.nodes[0] == x && edge.weight > 0) && (!visited.contains(edge.nodes[1]))){
                	
                    if(edge.nodes[1] == destination){
                        Stack.add(destination);
                        path.clear();
                    } else {
                        path.add(edge.nodes[1]);
                    }
                }
            }
        }

        return Stack;
    }
	
	
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260716883"; 
		int maxFlow = 0;
		
				// YOUR CODE GOES HERE
		
		
				WGraph residual = new WGraph(graph);
				WGraph capacity = new WGraph(graph);
				
				
				for(Edge edge: graph.getEdges()){
					graph.setEdge(edge.nodes[0], edge.nodes[1], 0);
				}
				
				
				while(pathDFS(source, destination, residual).contains(source) && pathDFS(source, residual.getDestination(), residual).contains(destination)){
					ArrayList<Integer> dfs = pathDFS(source, destination, residual);
					int bottleneck = Integer.MAX_VALUE;
					
					
					for(int x = 0; x < dfs.size()-1; x++){
						Edge edge = residual.getEdge(dfs.get(x), dfs.get(x+1));
						if(edge != null && edge.weight < bottleneck){
							bottleneck = edge.weight;
						}
					}
					
					for(int x = 0; x < dfs.size() - 1; x++){
						Integer a1 = dfs.get(x);
						Integer a2 = dfs.get(x + 1);
						Edge edge = graph.getEdge(a1, a2);
						if(edge != null){
							graph.setEdge(a1, a2, edge.weight + bottleneck);
						}
						else{
							graph.setEdge(a1, a2, edge.weight - bottleneck);
						}
					}
					
					
					for(int x=0; x<dfs.size()-1; x++){
						Integer b1 = dfs.get(x);
						Integer b2 = dfs.get(x + 1);
						Edge edge = graph.getEdge(b1, b2);
						Edge capEdge = capacity.getEdge(b1, b2);
						
						if(edge.weight <= capEdge.weight){
							residual.setEdge(b1, b2, capEdge.weight - edge.weight);
						} else if (edge.weight > 0) {
							Edge residualEdge = residual.getEdge(b1, b2);
							if(residualEdge == null){
								Edge backEdge = new Edge(b1, b2, edge.weight);
								residual.addEdge(backEdge);
							}
							else{
								residual.setEdge(b2, b1, edge.weight);
							}
						}
						
					}
					
					maxFlow += bottleneck;
					bottleneck = Integer.MAX_VALUE;
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
