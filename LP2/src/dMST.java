import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

//import javax.swing.text.html.HTMLDocument.Iterator;

public class dMST {
	private static final int Infinity = Integer.MAX_VALUE;
	boolean checkCycle = false ;
	public  void transform (Graph g, Vertex r)
	{
		List<Vertex> mst = new ArrayList<>();
		Graph g1 = g ;
		List<Vertex> cycle = new ArrayList<>();
		HashMap<Integer, Vertex> hm = new HashMap<>();
	//	Vertex notReached = null;
		//Graph subGraph = new Graph() ;
		Iterator<Vertex> it = g1.iterator();
		 Vertex v = it.next();
		  
		 while (v != null && it.hasNext() )
		 { 
			 if ( v == r)
				 v = it.next();
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
			 Iterator<Edge> itE = v.revAdj.iterator();
			 
			 while (itE.hasNext())
			 {
				 Edge e = itE.next();
				 e.Weight = e.Weight - tmp.Weight ;
				 if (e.Weight != 0)
					 itE.remove();
			 }
			 /*for ( Edge e : v.revAdj)
			 {
				 e.Weight = e.Weight - tmp.Weight ;
			//	 if (e.Weight != 0)					 					 
			 }*/			 
			 v = it.next();
		 }
		mst = bfs(g1, r);
		System.out.println(mst);
		int count = 0 ;
		if (checkCycle )
		{
			Vertex start ,end ;
			start = mst.get(0);
			while(true){
				if(start.index==0){
					cycle.add(start);
					start.index = count++ ;
				}
				else{
					
				}
				//start=start.revAdj.get()
				
				
			}
			
			/*Vertex tmp1,tmp2 ;
			for (Vertex c : c1)
			{
				if (c)
			}*/
		//	System.out.println("Cycle exists");
			
		}
		 
		 
	}
	private List<Vertex> bfs(Graph g,Vertex r)
	{
		List<Vertex> l = new ArrayList<>();
		l.add(r);
	//	Vertex vBfs2 = r ;
		Queue<Vertex> q = new LinkedList<>();
		Vertex current = r ;
		if (g.numNodes == 0)
			System.out.println("Input is not a graph");			
		else
		{
			for (Vertex i : g )
			{	if (i.distance == 0)
			{
				i.distance = Infinity ;
				i.parent = null ;
			}
			}
			r.distance = 0;
			q.add(r);
		
			while (!q.isEmpty())
			{
				current = q.poll();
				for (Vertex v : current.AdjV)
				{
					if (v.distance == Infinity)
					{
						v.distance = current.distance + 1 ;
						l.add(v);
			//			check = null ;
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
				checkCycle = true ;
				l.clear();				
			//	check = i ;
				l.add(i);
				System.out.println("Graph is not connected");
				return l ;
			}
		}
		
		return l ;
		/*Iterator<Vertex> it1 = g.iterator();
		Vertex v1 = it1.next();
		vBfs2 = v1 ;
		while (it1.hasNext())
		{
			if (v1.distance >= vBfs2.distance)
				vBfs2 = v1 ;
			v1 = it1.next();
		
		}*/
		
	//	return bfs2 (g,vBfs2);		
	}
	
	public static void main(String args[])
    {
			dMST d = new dMST();
           Scanner sc= new Scanner(System.in);
           Graph g=Graph.readGraph(sc,true);
           d.transform(g,g.verts.get(1));          
    }
}
