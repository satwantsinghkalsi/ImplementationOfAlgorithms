import java.util.PriorityQueue;
import java.util.Scanner;

public class Kruskals  {
	public void kruskal(Graph g)
	{
	    DisjntSets ds = new DisjntSets(g.verts.size());//Creates the disjoint sets for the vertices
	    PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	    for(Vertex v: g){
	    	for(Edge e:v.Adj){
	    		if(!pq.contains(e))
	    			pq.add(e);
	    	}//Adding edges to the priority queue
	    }
	    int total=0;
	    while (!pq.isEmpty()) 
	    {
	    	
	    	Edge e = pq.poll();  // get minimum edge
	    	if (ds.find(g.verts.indexOf(e.From)) != ds.find(g.verts.indexOf(e.To)))   // if not same set (not yet connected)
	        {
	             // accept the edge
	    		 total+=e.Weight; //Adding total distance;
	             ds.union(ds.find(g.verts.indexOf(e.From)), ds.find(g.verts.indexOf(e.To))); // connect them
	         }
	   }
	    System.out.println("Sum of distances:"+total);
	}
	public static void main(String args[])
	{
		Kruskals l= new Kruskals();
		Scanner sc = new Scanner(System.in);
		Graph g = Graph.readGraph(sc, false);
		l.kruskal(g);
	}	

}