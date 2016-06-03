import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Prim2 {
	private static final int Infinity = Integer.MAX_VALUE;
	List<Edge> listEdges = new ArrayList<>();
	long wMST ;
	 void Initialize (Graph g , Vertex s)
		{
			 //IndexedHeap<Vertex> pq = new IndexedHeap<>(g.verts.size(),Comparator c);
			for (Vertex x : g){
				x.distance = Infinity ;
				x.parent = null ;
				x.seen = false ;
			}
			s.distance = 0 ;
			wMST = 0;
		//	Initialize(s);
		}
	 public class Comp implements Comparator<Vertex>{
		 public int compare(Vertex u, Vertex v)
		 {
			/* if (u.distance > v.distance)
				 return -1 ;
			 else
				 return +1 ;*/
			 return u.distance - v.distance ; 
		 }
	 }
	 long Prim2IndexedHeap (Graph g)
	 {
		Vertex s = g.verts.get(1);
		Initialize(g, s);
		int count = 0 ;
		Vertex[] vArr = new Vertex[g.verts.size()] ;
		for (Vertex x : g)
			 vArr[++count] = x ;
		Comparator<Vertex> c = new Comp();
		//IndexedHeap
		IndexedHeap<Vertex> pq = new IndexedHeap<Vertex>(vArr,c);
		while (!pq.isEmpty())
		{
			Vertex u = pq.remove();
			u.seen = true ;
			wMST = wMST + u.distance ;
			for (Edge e : u.Adj)
			{
				Vertex v = e.otherEnd(u) ;
				if (!v.seen && e.Weight <= v.distance)
				{
					listEdges.add(e);
					v.distance = e.Weight;
					v.parent = u ;
					pq.decreaseKey(v);
					
				}
			}
		}
		/*for (Vertex v : g){
			if (!v.seen)
			{
				System.out.println("Graph is not connected");
				return -1 ;
			}
		}*/
		return wMST ;
		
	 }
	 
	 /*public static void main(String args[])
	 {
		 Prim2 p = new Prim2() ;
		 Scanner sc= new Scanner(System.in);
         Graph g=Graph.readGraph(sc,false);
         System.out.println("Weight of MST = "+p.Prim2IndexedHeap(g));
	 }*/
}
