
/** @author Satwant
 *  SP2_a: Topological ordering of a DAG
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SP2_a {
	
	 /**
     * 
     * Method for ordering the nodes of the graph topologically
     * @input Graph g
     * @return List of Vertices or null if graph not DAG
     */
	public static List<Vertex> toplogicalOrder1(Graph g) {
    	ArrayDeque<Vertex> stack = new ArrayDeque<>();
    	List<Vertex> lst = new ArrayList<>();
    	for(Vertex u:g)
    	{
    		if(u.degree==0)
    		{
    			System.out.println(u.degree);
    			stack.add(u);
    		}
    			
    	}
    	while(!stack.isEmpty())
    	{
    		Vertex v= stack.remove();
    		lst.add(v);
    		Vertex f;
    		for(Edge e:v.Adj)
    		{
    			f=e.otherEnd(v);
    			f.degree--;	//reducing degree of the vertices on other end.
    			if(f.degree==0)
        			stack.add(f);
    		}
    		
    	}
    	return lst; 
       
     }
    /**
     * Method for ordering the nodes of the graph topologically using DFS 
     * @param Graph g
     * @return Stack<Vertex> or null if graph not DAG
     */
    public static Stack<Vertex> toplogicalOrder2(Graph g) {
    	boolean isGraphDAG=false;
    	for(Vertex u:g)
    	{
    		u.seen=false;
    		u.parent=null;
    		if(u.degree==0){
    			isGraphDAG=true;
     		}
    	}
    	Stack<Vertex> newStack= new Stack<>();
    	if(isGraphDAG)
	    	for(Vertex u:g)
	    	{
	    		if(!u.seen)
	    		{
	    			DFSVisit(u,newStack);
	    		}
	    	}
		return newStack;
       
     }
    /**
     * Method used recursively for Depth First Search.
     * @param u: Vertex
     * @param S: Stack of Vertices
     */
    static void DFSVisit(Vertex u,Stack<Vertex> S){
    	u.seen=true;
    	for(Edge e:u.Adj)
    	{
    		Vertex v= e.otherEnd(u);
    		if(!v.seen)
    		{
    			v.parent=u;
    			DFSVisit(v,S);
    		}
    	}
    	S.push(u);
    }
    public static void main(String args[]) throws FileNotFoundException
    {
    	/*String name="lp0-big.txt";
		FileInputStream file1 = new FileInputStream(name);*/
    	Scanner sc= new Scanner(System.in);
    	Graph g=Graph.readGraph(sc,false);
    //	System.out.println(toplogicalOrder1(g));
    	 long startTime = System.currentTimeMillis();
    	// System.out.println(toplogicalOrder1(g));
    	 System.out.println(toplogicalOrder2(g));
         long stopTime = System.currentTimeMillis();
         long elapsedTime = stopTime - startTime;
         System.out.println(elapsedTime);
    	System.out.println();
    	
    }


}
