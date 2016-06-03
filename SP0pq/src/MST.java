import java.util.Comparator;
import java.util.Scanner;

public class MST {
    static final int Infinity = Integer.MAX_VALUE;
    public static class EdgeComparator implements Comparator<Edge> {
        public int compare(Edge x, Edge y) {
            return y.Weight - x.Weight;
        }
    }
    public class VertexComparator implements Comparator<Vertex>{
    	public int compare(Vertex u,Vertex v){
        	return v.distance-u.distance;
        }
    }
    static int PrimMST(Graph g)
    {
        int wmst = 0;
        Vertex src = g.verts.get(1);
        src.seen=true;
        for(Vertex v:g)
        {
        	v.seen=false;
        	v.parent=null;
        	
        }
        Comparator<Edge> comp= new EdgeComparator();
        BinaryHeap<Edge> edgePQueue = new BinaryHeap<>(10000, comp);
        for(Edge e:src.Adj){
        	edgePQueue.add(e);
        }
       while(!edgePQueue.isEmpty())
        {
        	edgePQueue.print();
        	System.out.println();
        	Edge e=edgePQueue.remove();
        	System.out.println(e+":"+e.Weight);
        	if(e.From.seen && e.To.seen)
        		continue;
        	//e.From.seen=true;
        	e.To.parent=e.From;
        	//System.out.println(wmst);
        	wmst=wmst+e.Weight;
        	e.To.seen=true;
        	for(Edge edge:e.To.Adj)
        	{
        		if((edge.From.seen && edge.To.seen))
        			edgePQueue.add(edge);
        		else{
        		Vertex w =edge.otherEnd(e.To);
        		if(!w.seen)
        			edgePQueue.add(edge);}
        		
        	}
        	
        }
        // Code for Prim's algorithm needs to be written

        return wmst;
    }

   /* public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        System.out.println(PrimMST(g));
    }*/
}
