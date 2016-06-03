import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Dijkstra {
	
	List<Edge> listEdges = new ArrayList<>();
	long wMST  ;

	private static final int Infinity = Integer.MAX_VALUE;
	
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
	void dijkstra(Graph g, Vertex s )
	{
		long dist = 0;
		
		Initialize(g, s);
	    /*for ( Vertex v : g)
	    {  v.distance = Infinity; v.seen = false; }
	    
	    s.distance = 0;*/  // start source vertex at distance zero
	    
	    int count = 0 ;
	    Vertex[] vArr = new Vertex[g.verts.size()] ;
		 for (Vertex x : g)
		 {
			 if (x == null){continue;}
			 vArr[++count] = x ;
		 }
		 
		 Comparator<Vertex> c = new Comp();
	    
	    IndexedHeap<Vertex> Heap = new IndexedHeap<>(vArr, c);
	    
	    while (!Heap.isEmpty())
	    {
	    	Vertex u = Heap.remove();
	    	u.seen = true ;
	    	if (u.distance != Infinity)
	    		dist = dist+u.distance;
	    	
	    	for (Edge e : u.Adj)
	    	{
	    		Vertex v = e.otherEnd(u);
	    		if ( v.distance> u.distance + e.Weight && !v.seen)
	    		{
	    			v.distance = u.distance + e.Weight;
	    			v.parent = u ;
	    			Heap.decreaseKey(v);
	    		}
	    	}
	    }
	    System.out.println("Dij "+dist);
	    for (Vertex u : g)
	    {
	    	if (u.distance != Infinity)
	    	{
	    		System.out.println(u+" "+ u.distance+" "+u.parent);
	    	}
	    	else 
	    		System.out.println(u+" INF");
	    	
	    }	    
	         
	}
	
	void BFS(Graph g, Vertex s)
	{
		Queue<Vertex> q = new LinkedList<>();
        Vertex current = s ;
        if (g.numNodes == 0){
       	 System.out.println("Input is not a Graph");
        }
        else
        {
        	Initialize(g, s);
          /*for (Vertex i : g )
          {                                       
                i.distance = Infinity ;
                i.parent = null ;
          }
          r.distance = 0;*/
          q.add(s);
			
          while (!q.isEmpty())
          {
            current = q.poll();
            current.seen = true;
            if (current.distance != Infinity)
	    		wMST = wMST+current.distance;
            
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
            System.out.println("Graph is not connected");
          }
        }
        
        System.out.println("Bfs "+wMST);
	    for (Vertex u : g)
	    {
	    	if (u.distance != Infinity)
	    	{
	    		System.out.println(u+" "+ u.distance+" "+u.parent);
	    	}
	    	else 
	    		System.out.println(u+" INF");
	    	
	    }
        /*Iterator<Vertex> it1 = g.iterator();
        Vertex v1 = it1.next();
        vBfs2 = v1 ;
        while (it1.hasNext())
        {
	           if (v1.distance >= vBfs2.distance)
	                         vBfs2 = v1 ;
	           v1 = it1.next();
        }*/
	}
	public static void main(String args[])
	 {
		 Dijkstra d = new Dijkstra() ;
		 Scanner sc= new Scanner(System.in);
        Graph g=Graph.readGraph(sc,true);
        Vertex s = g.verts.get(1);
     //    d.dijkstra(g, s);
         d.BFS(g, s);
	 }

}
