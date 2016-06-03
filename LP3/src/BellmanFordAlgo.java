import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BellmanFordAlgo {
	static final int Infinity= Integer.MAX_VALUE;
	public static void Initialize(Graph g,Vertex s){
    	for(Vertex v:g){
    		v.distance=Infinity;
    		v.parent=null;
    		v.seen=false;
    		v.count=0;
    	}
    	s.distance=0;
    	s.seen=true;
    }
	public static boolean shortestPathBellmanFord(Graph g, Vertex s){
		Queue<Vertex> vertices = new LinkedList<>();
		Initialize(g, s);
		vertices.add(s);
		while(!vertices.isEmpty()){
			Vertex u=vertices.remove();
			u.seen=false;
			u.count+=1;
			if(u.count>=g.numNodes){
				Vertex w=u.parent;
				//Initialize(g, s);
				
			}
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight)){
					v.distance=u.distance+e.Weight;
					v.parent=u;
					if(!v.seen){
						vertices.add(v);
						v.seen=true;
						
					}
				}
			}
			
		}
		
		/*for(Vertex v:g){
			for(Edge e:v.Adj){
				if(v.distance>(e.otherEnd(v).distance+e.Weight)){
					if(v.distance!=Infinity && e.otherEnd(v).distance!=Infinity)
						return false;
				}
			}
		}*/
		return true;
	}
	public static List<Edge> findCycle(Graph g, Vertex s){
		List<Edge> negativeCycle= new LinkedList<>();
		for(int i=1;i<g.numNodes-1;i++){
			Vertex u=g.verts.get(1);
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight)){
					v.distance=u.distance+e.Weight;
					v.parent=u;
				}
			}
		}
		for(int i=1;i<g.numNodes;i++){
			Vertex u=g.verts.get(1);
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight)){
					v.distance=u.distance+e.Weight;
					v.parent=u;
				}
			}
		}
		return negativeCycle;
	}
	public static int sumOfShrtstPaths(Graph g)
	{
		int sum=0;
		for(Vertex v:g){
			if(v.distance==Infinity)
				continue;
			sum+=v.distance;
		}
		return sum;
	}
	public static void printResult(Graph g,boolean flag){
		if(flag){
			System.out.println("B-F "+sumOfShrtstPaths(g));
			if(g.verts.size()<=100){
				for(Vertex v:g){
					if(v.distance==Infinity)
						System.out.println(v+" INF -"+" "+v.count);
					else{
						if(v.parent!=null)
							System.out.println(v+" "+v.distance+" "+v.parent+" "+v.count);
						else
							System.out.println(v+" "+v.distance+" -"+" "+v.count);
					}
				}
			}
		}
		else
			System.out.println("Non-positive cycle in graph. DAC is not applicable");
	}
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		Timer time = new Timer();
		Graph g = Graph.readGraph(sc, true);
		time.start();
		boolean flag=shortestPathBellmanFord(g, g.verts.get(1));
		time.end();
		if(!flag)
			findCycle(g,g.verts.get(1));
		else
			printResult(g, flag);
		System.out.println(time);
	}
}
