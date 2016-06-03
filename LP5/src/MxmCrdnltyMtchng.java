import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Satwant Singh,Himanshu Parashar
 * 
 *	Class created to implement and maximum cardinality matching in a Bipartite graph
 *	LP5 Level1
 */
public class MxmCrdnltyMtchng {
	public static final int Infinity=Integer.MAX_VALUE;
	public static int mtchngSze;
	/**
	 * Method to initialize the graph for Breadth First Search
	 * @param 
	 * 			:Graph g
	 */
	public void initalize(Graph g){
		for(Vertex u:g){
			u.distance=Infinity;
			u.mate=null;
			u.seen=false;
			u.parent=null;
		}
	}
	/**
	 * Method to check if the given graph is bipartite.
	 * 
	 * @param 
	 * 			:Graph g
	 * @return
	 * 			:boolean- true if g is bipartite else false
	 */
	boolean checkBipartite(Graph g){
		
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
				//if(!v.seen){
					if((u.nodeType==1 && v.nodeType==1)|| (u.nodeType==2 && v.nodeType==2)){
						isBipartite=false;
						break;
				//	}
				}
			}
		}
		return isBipartite;
	}
	/**
	 * Method to find the maximum cardinality matching in bipartite graph
	 * @param g
	 * 			: Graph g
	 * @return
	 * 			:size of matching of maximum cardinality
	 */
	public int mxmCrdMtchng(Graph g){
		boolean isBipartite=true;
		boolean mtchn = true;
		mtchngSze=0;
		int count=0;
		isBipartite=checkBipartite(g);
		if(isBipartite){
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
			while(true){
				Queue<Vertex> outerNodes= new LinkedList<>();
				for(Vertex u:g){
					u.seen=false;
					u.parent=null;
					if(u.mate==null && u.nodeType==2 && !u.usedInAugmn){
						u.seen=true;
						outerNodes.add(u);
					}
				}
				boolean augPthFnd=false;
				while(!outerNodes.isEmpty()){
					Vertex u=outerNodes.remove();
					u.usedInAugmn=true;
					for(Edge e:u.Adj){
						Vertex v=e.otherEnd(u);
						if(!v.seen && !v.usedInAugmn){
							v.parent=u;
							v.usedInAugmn=true;
							v.seen=true;
							if(v.mate==null){
								augmntPath(v);
								augPthFnd=true;
								break;
							}
							else{
								 Vertex x=v.mate;
								 x.seen=true;
								 x.parent=v;
								 outerNodes.add(x);
							}
						}
					}
					/*if(augPthFnd)
						break;*/
				}
				if(outerNodes.isEmpty()){
						break;
				}
				
			}
			
			for(Vertex u:g){
				if(u.mate!=null)
					count++;
			}			
		}
		else{
			System.out.println("G is not bipartite");	
		}
		System.out.println(count/2+" "+mtchn);
		return mtchngSze ;
	}
	/**
	 * Helper method to increase size of matching using an augmenting path 
	 * @param 
	 * 			: Vertex u
	 */
	public void  augmntPath(Vertex u){
		Vertex p=u.parent;
		Vertex x=p.parent;
		u.mate=p;
		p.mate=u;
		while(x!=null){
			Vertex nmx=x.parent;
			Vertex y=nmx.parent;
			x.mate=nmx;
			nmx.mate=x;
			x=y;
		}
		mtchngSze++;
	}
}
