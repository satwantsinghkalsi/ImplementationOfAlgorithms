import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class LP0_2 {
	
	static List<Edge> findEulerTour(Graph g){
		boolean hasEulerCircuit=true;
		int oddDegreeVertex=0;
		List<Edge> eulerTrail = new LinkedList<>();
		ArrayDeque<Vertex> vertices = new ArrayDeque<>();
		ArrayDeque<Vertex> empty = new ArrayDeque<>();
		for(Vertex v:g)
		{
			v.seen=false;
			if(v.degree%2!=0)
				hasEulerCircuit=false;
			else
				oddDegreeVertex++;
		}
		if(oddDegreeVertex>2 && !hasEulerCircuit)
			return eulerTrail;
		else
		{
			vertices.add(g.verts.get(1));
			do
			{
				List<Edge> S= new LinkedList<>();
				while(!vertices.isEmpty())
				{
					
					Vertex v=vertices.pop();
					if(v.degree!=0)
					{
						DFSVisit(v, S,vertices);
						
					}
				}
				if(eulerTrail.isEmpty()){
					eulerTrail=S;
				}
				else
				{
					if(S.isEmpty()==false){
						Vertex w=S.get(0).From;
						int i=1;
						for(Edge e:eulerTrail){
							if(e.From==w ||e.To==w){
								break;
							}
							else
							{
								i++;
							}
							
						}
						eulerTrail.addAll(i,S);
					}
					
					
				}
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
				
			}while(verifyTour(g,eulerTrail,g.verts.get(1))==false);
			 
		}

       
		return eulerTrail;
		// Return an Euler tour of g
	}
	/**
     * Method used recursively for Depth First Search.
     * @param u: Vertex
     * @param S: Stack of Vertices
     */
    static Vertex DFSVisit(Vertex u,List<Edge> S,ArrayDeque<Vertex> Q){
    	u.seen=true;
    	Iterator <Edge> itr= u.Adj.iterator();
    	while(itr.hasNext()){
			Edge e=itr.next();
			if(!e. Visited)
    		{
    			Vertex v= e.otherEnd(u);
    			u.degree--;
    			e.Visited=true;
    			v.degree--;
    			Q.add(v);
    			S.add(e);
    			itr.remove();
    			v.Adj.remove(e);
    			return v;
    		}
    		else
    			itr.remove();
    		
    	}
    	return u;
    	
    }
	static boolean verifyTour(Graph g, List<Edge> tour, Vertex start){
		
		for (Vertex v : g)
		{
			if(v.degree!=0)
				return false;
		}
		
		return true;
	}
	public static void main(String args[]) throws FileNotFoundException
    {
		String name="lp0-big.txt";
		FileInputStream file1 = new FileInputStream(name);
    	Scanner sc= new Scanner(System.in);
    	Graph g=Graph.readGraph(sc,false);
    	 long startTime = System.currentTimeMillis();
    	 findEulerTour(g);
         long stopTime = System.currentTimeMillis();
         long elapsedTime = stopTime - startTime; 
         System.out.println(elapsedTime);
         
          	
    }

}
