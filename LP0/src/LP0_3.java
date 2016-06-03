import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
/**
 * Class to implement Hierholzer's algorithm
 */
public class LP0_3 {
	static final int Infinity=Integer.MAX_VALUE;
	/**
	 * Method to find the Euler path in a given Graph
	 * @param g:Input Graph
	 * @return :Return an Euler tour of g
	 */
	static List<Edge> findEulerTour(Graph g){
		boolean hasEulerCircuit=true;
		boolean isGraphConnected=true;
		isGraphConnected=graphConnected(g, g.verts.get(1));// to check if graph is connected
		int oddDegreeVertex=0;
		List<Edge> eulerTrail = new LinkedList<>();
		ArrayDeque<Vertex> vertices = new ArrayDeque<>();
		for(Vertex v:g)		
		{
			v.seen=false;
			if(v.degree%2!=0){
				hasEulerCircuit=false;
				oddDegreeVertex++;
			}
		}
		if(oddDegreeVertex>2 && !hasEulerCircuit ||!isGraphConnected)
		{
			System.out.println("Graph is not Eulerian");
			return eulerTrail;
		}
		else
		{
			Vertex start=g.verts.get(1);
			vertices.add(g.verts.get(1));
			while(!verifyTour(g,eulerTrail,start))
			{
				List<Edge> S= new LinkedList<>();
				while(!vertices.isEmpty())
				{
					Vertex v=vertices.pop();
					if(v.degree!=0)
						GetTheNextEdge(v, S,vertices);
				}
				eulerTrail=mergeEulerTour(eulerTrail, S);
				vertices=getVertexForEulerTour(eulerTrail, vertices);
				start=vertices.peek();
			}
		}
		return eulerTrail;	
		
	}
	/**
	 * Method to get the Vertex in tour have unvisited Edges.
	 * @param eulerTrail
	 * @param vertices
	 * @return Stack of vertices 
	 */
	static ArrayDeque<Vertex> getVertexForEulerTour(List<Edge>eulerTrail,ArrayDeque<Vertex>vertices)
	{
		for(Edge e:eulerTrail)
		{
			if(e.To.degree!=0){
				vertices.add(e.To);
				break;
			}
			else if(e.From.degree!=0){
				vertices.add(e.From);
				break;
			}
		}
		return vertices;
	}
	/**
	 * Method to merge two Euler tours.
	 * @param eulerTrail
	 * @param S :Euler tour to be merged
	 * @return Euler Tour
	 */
	static List<Edge> mergeEulerTour(List<Edge>eulerTrail, List<Edge> S)
	{
		if(eulerTrail.isEmpty()){
			eulerTrail=S;
		}
		else
		{
			/***Merging***/
			if(S.isEmpty()==false){
				 Vertex w=S.get(0).From;
				 Vertex z=S.get(0).To;
				int i=1;
				for(Edge e:eulerTrail){
					if(e.From==w ||e.To==w || e.From==z || e.To==z	)
						break;
					else
						i++;
				}
				eulerTrail.addAll(i,S);
			}
		}
		return eulerTrail;
	}
	/**
     * Method used to get the next edge in the tour.
     * @param u: Vertex
     * @param S: Stack of Vertices not yet visited
     */
	static Vertex GetTheNextEdge(Vertex u,List<Edge> S,ArrayDeque<Vertex> Q){
    	u.seen=true;
    	int i=u.indexTillEdgesVisited;
    	while(i<u.Adj.size()){
			Edge e=u.Adj.get(i);
			i++;
			u.indexTillEdgesVisited=i;
			if(!e. Visited)
    		{
				e.timesVisited++;
    			Vertex v= e.otherEnd(u);
    			u.degree--;
    			e.Visited=true; 
    			v.degree--;
    			Q.add(v);
    			S.add(e);
    			return v;
    		}
    	}
    	return u;
    	
    }
	/**
	 * Method to verify the euler tour
	 * @param g:Input Graph g
	 * @param tour: Euler tour
	 * @param start: Starting vertex
	 * @return True or False
	 */
	static boolean verifyTour(Graph g, List<Edge> tour, Vertex start){
		boolean isTourEuler=true;
		for(Edge e:tour)
		{
			if(!e.Visited || e.timesVisited!=1)
			{
				isTourEuler=false;
				return isTourEuler;
			}
		}
		for (Vertex v : g)
		{
			if(v.degree!=0)
			{
				isTourEuler=false;
				break;
			}
		}
		return isTourEuler;
	}
	/**
	 * Method to find if graph is connected using Breadth First Search.
	 * If graph is not connected then no euler tour is present. 
	 * @param g:Input graph G
	 * @param r: Starting vertex 
	 * @return True or false
	 */
	private static boolean graphConnected(Graph g,Vertex r )
    {
		 boolean isGraphConnected=true;	
         Vertex vBfs2 = r ;
         Queue<Vertex> q = new LinkedList<>();
         Vertex current = r ;
         if (g.numNodes == 0){
        	 isGraphConnected=false;
         }
         else
         {
           for (Vertex i : g )
           {                                       
                 i.distance = Infinity ;
                 i.parent = null ;
           }
           r.distance = 0;
           q.add(r);
			
           while (!q.isEmpty())
           {
             current = q.poll();
             for (Vertex v : current.AdjV)
             {
                  if (v.distance == Infinity)
                  {
                    v.distance = current.distance +1 ;
                    v.parent = current;
                    q.add(v);
                  }
             }
           }                                       
 		 }
         for (Vertex i : g)
         {
           if (i != null &&i.distance == Infinity)
           {
             isGraphConnected=false;
           }
         }
         Iterator<Vertex> it1 = g.iterator();
         Vertex v1 = it1.next();
         vBfs2 = v1 ;
         while (it1.hasNext())
         {
	           if (v1.distance >= vBfs2.distance)
	                         vBfs2 = v1 ;
	           v1 = it1.next();

         }
         return isGraphConnected;
    }
	public static void main(String args[]) throws UnsupportedEncodingException, IOException
    {
		String name="lp0-big.txt";
		Timer time= new Timer();
		FileInputStream file1 = new FileInputStream(name);
		Scanner sc= new Scanner(file1);
    	Graph g=Graph.readGraph(sc,false);
    	time.start();
		List<Edge> eulerTour=findEulerTour(g);
	    time.end();
	    System.out.println(time);
	    for(Edge e: eulerTour)
	    	 System.out.println(e);
	  
    }

}
