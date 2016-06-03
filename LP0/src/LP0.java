import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.Other;
/**
 * Class to implement Hierholzer's algorithm
 * @Author G32
 */
public class LP0 {
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
		if(oddDegreeVertex!=2 && !hasEulerCircuit ||!isGraphConnected)
		{
			System.out.println("Graph is not Eulerian");
			return eulerTrail;
		}
		else
		{
			{
				Vertex start=g.verts.get(1);
				vertices.add(g.verts.get(1));
				while(!checkTour(g,eulerTrail,start))
				{
					List<Edge> S= new LinkedList<>();
					while(!vertices.isEmpty())
					{
						System.out.println(vertices);
						System.out.println(S);
						Vertex v=vertices.pop();
						if(v.degree!=0)
							GetTheNextEdge(v, S,vertices);
					}
					eulerTrail=mergeEulerTour(eulerTrail, S);
					vertices=getVertexForEulerTour(eulerTrail, vertices);
					start=vertices.peek();
				}
			}
			if(!verifyTour(g, eulerTrail))
				System.out.println("Not Euler Tour");
			return eulerTrail;	
		}
			
		
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
	static void GetTheNextEdge(Vertex u,List<Edge> S, ArrayDeque<Vertex> Q){
    	u.seen=true;
    
			while(u.itr.hasNext())
    		{	
				Edge e=u.itr.next();
				if(!e.Visited){
					e.timesVisited++;
	    			Vertex v= e.otherEnd(u);
	    			u.degree--;
	    			e.Visited=true; 
	    			v.degree--;
	    			S.add(e);
	    			Q.add(v);
	    			return;
    			}
				else 
					continue;
    			
    		}
  
    }
	/**
	 * Method to verify the euler tour
	 * @param g:Input Graph g
	 * @param tour: Euler tour
	 * @param start: Starting vertex
	 * @return True or False
	 */
	static boolean checkTour(Graph g, List<Edge> tour, Vertex start){
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
         
         return isGraphConnected;
    }
	public static boolean verifyTour(Graph g, List<Edge> tour){
		boolean isEulerTour=true;
		Edge start=tour.get(0);
		for(Edge e:tour){
			if(e.From==start.From || e.From==start.To){
				start=e;
				continue;
			}
			else if(e.To==start.From || e.To==start.To){
				start=e;
				continue;
			}
			else{
				System.out.println(e+":"+e.Visited+":"+e.timesVisited);
				isEulerTour=false;
				break;
			}
		}
		return isEulerTour;
	}
	public static void main(String args[]) throws UnsupportedEncodingException, IOException
    {
		Timer time= new Timer();
		Scanner sc= new Scanner(args[0]);
    	Graph g=Graph.readGraph(sc,false);
    	time.start();
		List<Edge> eulerTour=findEulerTour(g);
	    time.end();
	    System.out.println(time);
	    for(Edge e: eulerTour)
	    	 System.out.println(e);
	  
    }

}
/**
 * Sample Input:
 * 	8 12
	1 2 1
	1 7 1
	2 7 1
	2 3 1
	2 5 1
	3 5 1
	4 5 1
	4 6 1
	5 6 1
	5 7 1
	5 8 1
	7 8 1
	
	Sample Output:
	(1,2)
	(2,3)
	(3,5)
	(4,5)
	(4,6)
	(5,6)
	(5,7)
	(7,8)
	(5,8)
	(2,5)
	(2,7)
	(1,7)
 * 
 **/ 
