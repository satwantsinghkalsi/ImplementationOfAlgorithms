import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAGShortestPaths {
	public static final int Infinity=Integer.MAX_VALUE;
	/**
     * Method for ordering the nodes of the graph topologically using DFS 
     * @param Graph g
     * @return Stack<Vertex> or null if graph not DAG
     */
    public static List<Vertex> toplogicalOrder(Graph g) {
    	boolean isGraphDAG=true;
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
    	for(Vertex v:g){
    		if(v.degree!=0){
    			isGraphDAG=false;
    			break;
    		}
	    }
    	if(!isGraphDAG)
    		System.out.println("Graph has cycle");
    	System.out.println(lst);
		return lst;
       
     }
    public static void Initialize(Graph g,Vertex s){
    	for(Vertex v:g){
    		v.distance=Infinity;
    		v.parent=null;
    		v.seen=false;
    	}
    	s.distance=0;
    }
    
	public static boolean relax(Vertex u,Vertex v,Edge e){
		boolean flag=false;
		if(v.distance>(u.distance+e.Weight) && u.distance!=Infinity){
			v.distance=u.distance+e.Weight;
			v.parent=u;
			flag=true; 
		}
		return flag;
	}
	public static void shrtstPathDAG(Graph g, Vertex s){
		List<Vertex> topSortOrder= toplogicalOrder(g);
		Initialize(g,s);
		for(Vertex u:topSortOrder){
			for(Edge e:u.Adj){
				relax(u,e.otherEnd(u),e);
			}
		}
	}
	public int sumOfShrtstPaths(Graph g)
	{
		int sum=0;
		for(Vertex v:g){
			if(v.distance==Infinity)
				continue;
			sum+=v.distance;
		}
		return sum;
	}
	public void printOutput(Graph g){
		System.out.println(sumOfShrtstPaths(g));
		for(Vertex u:g){
			if(u.distance==Infinity)
				System.out.println(u+" "+u.parent+"INF");
			else
				System.out.println(u+" "+u.parent+" "+u.distance);
		}
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		DAGShortestPaths t = new DAGShortestPaths();
		Graph g=Graph.readGraph(sc, true);
		shrtstPathDAG(g, g.verts.get(1));
		t.printOutput(g);
	}

}
