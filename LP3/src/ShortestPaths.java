import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPaths {
	public static final int Infinity=Integer.MAX_VALUE;
	String algoType;
	boolean negCycle;
	public ShortestPaths() {
		algoType="";
		negCycle=true;
	}
	public boolean fingShrtstPaths(Graph g, Vertex s){
		List<Vertex> topOrdrlst = new LinkedList<>();
		Graph tmp = g;
		topOrdrlst=toplogicalOrder(tmp);
		if(g.hasUniformEdgeWeights()){
			BFS(g, s);
			algoType="BFS";
			negCycle=true;
		}
		else if(topOrdrlst.size()==g.verts.size()-1){
			shrtstPathDAG(g, s,topOrdrlst);
			algoType="DAG";
		}
		else if(!g.hasNegativeWeights()){
			dijkstra(g, s);
			algoType="Dij";
		}
		else{
			negCycle=shortestPathBellmanFord(g, s);
			algoType="B-F";
		}
		return negCycle;
	}
	public void Initialize(Graph g,Vertex s){
    	for(Vertex v:g){
    		v.distance=Infinity;
    		v.parent=null;
    		v.seen=false;
    		v.count=0;
    	}
    	s.distance=0;
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
          q.add(s);
			
          while (!q.isEmpty())
          {
            current = q.poll();
            current.seen = true;
            if (current.distance != Infinity)
	//    		wMST = wMST+current.distance;
            
            for (Vertex v : current.AdjV)
            {
                 if (v.distance == Infinity)
                 {
                   v.distance = current.distance +g.getuniformWeight() ;
                   v.parent = current;
                   q.add(v);
                 }
            }
          }                                       
		 }
        
	}
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
    	/*for(Vertex v:g){
    		if(v.degree!=0){
    			isGraphDAG=false;
    			break;
    		}
	    }*/
    	/*if(!isGraphDAG)
    		System.out.println("Graph has cycle");
    	System.out.println(lst);*/
		return lst;
       
     }
     public boolean relax(Vertex u,Vertex v,Edge e){
		boolean flag=false;
		if(v.distance>(u.distance+e.Weight) && u.distance!=Infinity){
			v.distance=u.distance+e.Weight;
			v.parent=u;
			flag=true; 
		}
		return flag;
	 }
     public void shrtstPathDAG(Graph g, Vertex s, List<Vertex> lst){
 		List<Vertex> topSortOrder= lst;
 		Initialize(g,s);
 		for(Vertex u:topSortOrder){
 			for(Edge e:u.Adj){
 				relax(u,e.otherEnd(u),e);
 			}
 		}
 	}
     public class Comp implements Comparator<Vertex>{
		 public int compare(Vertex u, Vertex v)
		 {
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
 	public void printResult(Graph g,boolean flag){
 		if(flag){
 			System.out.println(algoType+" "+sumOfShrtstPaths(g));
 			if(g.verts.size()<=100){
 				for(Vertex v:g){
 					if(v.distance==Infinity)
 						System.out.println(v+" INF -"+" ");
 					else{
 						if(v.parent!=null)
 							System.out.println(v+" "+v.distance+" "+v.parent+" ");
 						else
 							System.out.println(v+" "+v.distance+" -"+" ");
 					}
 				}
 			}
 		}
 		else{
 			System.out.println("Non-positive cycle in graph. DAC is not applicable");
 		//	findCycle(g, g.verts.get(1));
 		}
 	}
 	public boolean shortestPathBellmanFord(Graph g, Vertex s){
		Queue<Vertex> vertices = new LinkedList<>();
		Initialize(g, s);
		s.seen=true;
		vertices.add(s);
		/*for(int i=1;i<g.verts.size();i++){
			Vertex u=g.verts.get(i);
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
		}*/
		while(!vertices.isEmpty()){
			Vertex u=vertices.remove();
		//	System.out.println(vertices);
			u.seen=false;
			u.count+=1;
		//	System.out.println(u.count);
			if(u.count>g.verts.size()-1)
				return false;
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight)){
					v.distance=u.distance+e.Weight;
					v.parent=u;
					if(!vertices.contains(v)){
						vertices.add(v);
						v.seen=true;
					}
				}
			}
			
		}
		/*for(Vertex u:g){
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight))
					return false;
			}
		}*/
		return true;
	}
 	boolean checkCycle = false ;
	public  void findCycle (Graph g, Vertex r)
	{
		List<Vertex> mst = new ArrayList<>();
		Graph g1 = g ;
		List<Vertex> cycle = new ArrayList<>();
		List<Vertex> c = new ArrayList<>();
		 for (Vertex v : g1)
		 { 
			 if ( v == r)
				 continue ;
			//	 v = it.next();
			// System.out.println(v.revAdj);
			 if(v.revAdj.size()>=1){
			 Edge tmp = v.revAdj.get(0) ;
			 for ( Edge e : v.revAdj)
			 {				 
				 if (e.Weight <= tmp.Weight)
				 {
					 tmp = e ;
					 
				 }				 
				 else
					 continue ;				 
			 }
			 
			 for ( Edge e : v.revAdj)
			 {
				 e.Weight = e.Weight - tmp.Weight ;
				 e.Visited = true;
								 					 
			 }
			 }
			 
		 }
		 	
	 
		mst = bfs(g1, r);
		System.out.println(mst);
		int count = 0 ;
		Queue<Vertex> q = new LinkedList<>();
		q.add(mst.get(0));
		if (checkCycle )
		{System.out.println("Cycle exists");
			while (!q.isEmpty())
			{
			Vertex start ;
			start = q.poll();
			if (start.index != 0)
			{
				c= cycle.subList(start.index, count);
				break;
			}
			start.index = count++ ;
			cycle.add(start);
			q.add(start.parent);
						
			}
		}
		System.out.println(c);
		 
		 
	}
	private List<Vertex> bfs(Graph g,Vertex r)
	{
	
		List<Vertex> lMst = new ArrayList<>();
		List<Vertex> lCycle = new ArrayList<>();
		lMst.add(r);
	
		Queue<Vertex> q = new LinkedList<>();
		Vertex current = r ;
		if (g.numNodes == 0)
			System.out.println("Input is not a graph");			
		else
		{
			for (Vertex i : g )
			{	
					i.distance = Infinity ;
		//			i.parent = null ;
			}
			r.distance = 0;
			r.parent = null;
			q.add(r);
		
			while (!q.isEmpty())
			{
				current = q.poll();
				for (Vertex v : current.AdjV)
				{
					
					if (g.EdgeWeight(current, v) == 0)
					{
						
						v.distance = current.distance+g.EdgeWeight(current, v) ;
						lMst.add(v);
						v.parent = current;
						q.add(v);
						v.seen = true;
					}
				
				}
			}			
		}
		for (Vertex i : g)
		{			
		
			if (i.parent != null && g.EdgeWeight(i.parent, i)== 0 && !i.seen )
			{
				checkCycle = true ;
				lCycle.add(i);
			
			//	System.out.println("Graph is not connected");
				
			}
		
		}
		if (checkCycle)
			return lCycle ;
		else
			return lMst;
				
	}
	
 	public static void main(String args[]){
 		ShortestPaths shPaths = new ShortestPaths();
 		Scanner sc = new Scanner(System.in);
 		Graph g=Graph.readGraph(sc, true);
 		boolean flag=shPaths.fingShrtstPaths(g, g.verts.get(1));
 		shPaths.printResult(g, flag);
 		
 	}

}
