import java.util.Scanner;
import java.util.Stack;

/**
 * SP2_c:Strongly connected components of a directed graph
 * @author satwant
 *
 */
public class SP2_c {
	/**
	 * Method to finding the strongly connected components in a directed graphs.
	 * @param Graph g
	 * @return Number of Strongly connected components
	 */
	
	static int stronglyConnectedComponents(Graph g) {
		Stack<Vertex> lst = SP2_a.toplogicalOrder2(g); //returns topological order by DFS on Graph g
		int numberOfComponents=0;
		int cno=0;
		Stack<Vertex> stack = new Stack<>();
		for(Vertex u:g)
		{
			u.seen=false;
			u.parent=null;
		}
		while(!lst.isEmpty())
		{
			Vertex v= lst.pop();
		
			cno=assignComponents(v,++v.comp_no);
		}
		
		return cno;
		
	}
	/**
     * Method used recursively Assigning components.
     * @param u: Vertex
     * @param S: Stack of Vertices
     */
    static int assignComponents(Vertex v, int cno){
    	if(v.comp_no==0)
    	{ 
    		for(Edge e:v.revAdj)
			{
				
				Vertex u =e.otherEnd(v);
				v.comp_no=cno;
				assignComponents(u,cno);
			}
    	}
    		return cno;
    }
    public static void main(String args[])
    {
    	Scanner sc = new Scanner(System.in);
    	Graph g=Graph.readGraph(sc, true);
    	System.out.println(stronglyConnectedComponents(g));
    	
    }
	   

}
