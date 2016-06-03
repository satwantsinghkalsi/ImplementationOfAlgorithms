import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Satwant Singh,Himanshu Parashar
 * Class created to implement maximum cardinality matching in a arbitrary graph G=(V,E)
 * LP5 Level2	
 */
public class MaxCrdnltyMtchngArbtryGraph {
	public static int mtchngSze;
	public static final int Infinity=Integer.MAX_VALUE;
	/**
	 * Method to initialize the graph for Breadth First Search
	 * @param 
	 * 			:Graph g
	 */
	public void initalize(Graph g){
		for(Vertex u:g){
			u.distance=Infinity;
			u.seen=false;
		}
	}
	/**
	 * Method to divide the graph into inner and outer nodes
	 * 
	 * @param 
	 * 			:Graph g
	 */
	void divideGraph(Graph g){
		
		boolean isBipartite =true;
		int cnt=1;
		Queue<Vertex> Q= new LinkedList<>();
		initalize(g);
		Vertex root=g.verts.get(1);
		g.verts.get(1).nodeType=2;
		root.nodeType=2;//marking root as outer
		root.distance=0;
		root.seen=true;
		Q.add(root);
		while(!Q.isEmpty()){
			Vertex u= Q.remove();
			cnt++;
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(!v.seen){
					v.distance=u.distance+1;
					v.parent=u;
					v.seen=true;
					Q.add(v);
					if(v.distance%2==0)
						v.nodeType=2;// outer layer
					else
						v.nodeType=1;//inner layer
				
				}
			}
		}
	}
	/**
	 * 
	 * @param 
	 * 			:Input graph G=(V,E)
	 * @return
	 * 			: Maximum cardinality matching for the graph
	 */
	public int maxCrdnltyMtchng(Graph g){
		divideGraph(g);
		for(Vertex u :g){
			//Finding maximal matching of G
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(u.mate==null && v.mate==null){
					u.mate=v;
					v.mate=u;
					mtchngSze++;
				}
			}
		}
		
		return mtchngSze;
	}
}
