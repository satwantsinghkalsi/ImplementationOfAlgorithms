import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim1 {
	public static List<Edge> mstList = new LinkedList<>();
	 public static class EdgeComparator implements Comparator<Edge> {
	        public int compare(Edge x, Edge y) {
	            return x.Weight - y.Weight;
	        }
	    }
	public static int PrimMST(Graph g){
		int wmst = 0;
        for(Vertex v:g)
        {
        	v.seen=false;
        	v.parent=null;
        }
        Vertex src = g.verts.get(1);
        src.seen=true;
        Comparator<Edge> comp = new EdgeComparator();
        PriorityQueue<Edge> edgePQ = new PriorityQueue<>(comp);
        for(Edge e:src.Adj){
        	edgePQ.add(e);
        }
        while(!edgePQ.isEmpty()){
        	Edge e=edgePQ.remove();
        	Vertex u=e.From;
        	Vertex v=e.To;
        	Vertex w= null;
        	if(u.seen && v.seen)
        		continue;
        	if(!u.seen)
        		w=u;
        	else if(!v.seen)
        		w=v;
        	u.seen=true;
        	v.seen=true;
        	v.parent=u;
        	wmst=wmst+e.Weight;
        	mstList.add(e);
        	for(Edge f:w.Adj){
        		if(!(f.From.seen && f.To.seen) && !edgePQ.contains(f)){
        			edgePQ.add(f);
        		}
        	}
        }
        return wmst;
	}
	/* public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        System.out.println(PrimMST(g));
    }*/
}
