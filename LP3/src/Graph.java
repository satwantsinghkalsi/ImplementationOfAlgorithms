/**
 * Class to represent a graph
 * 
 *
 */

import java.io.*;
import java.util.*;

class Graph implements Iterable<Vertex> {
    public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph
    public static int uniformWeight;	
    public static boolean hasUniformWeights;
    public static boolean hasNegativeEdges;
    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
	numNodes = size;
	verts = new ArrayList<>(size + 1);
	verts.add(0, null);
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++)
	    verts.add(i, new Vertex(i));
	hasUniformWeights=true;
	hasNegativeEdges=false;
	uniformWeight=0;
    }

    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(int a, int b, int weight) {
	Vertex u = verts.get(a);
	Vertex v = verts.get(b);
	Edge e = new Edge(u, v, weight);
	u.Adj.add(e);
	v.Adj.add(e);
	v.degree ++ ;
	u.degree ++ ;
	// adding below vertices to their adjacent vertices list ; added to help in BFS
	u.AdjV.add(v);  
	v.AdjV.add(u);
    }

    /**
     * Method to add an arc (directed edge) to the graph
     * 
     * @param a
     *            : int - the head of the arc
     * @param b
     *            : int - the tail of the arc
     * @param weight
     *            : int - the weight of the arc
     */
    void addDirectedEdge(int a, int b, int weight) {
	Vertex head = verts.get(a);
	Vertex tail = verts.get(b);
	Edge e = new Edge(head, tail, weight);
	tail.parent = head;
	//head.degree++;
	head.Adj.add(e);
	tail.revAdj.add(e);
	head.AdjV.add(tail);
	tail.degree++;
    }
    public int getuniformWeight(){
    	return uniformWeight;
    }
    public boolean hasNegativeWeights(){
    	return hasNegativeEdges;
    }
    public boolean hasUniformEdgeWeights(){
    	return hasUniformWeights;
    }

    /**
     * Method to create an instance of VertexIterator
     */
    public Iterator<Vertex> iterator() {
	return new VertexIterator();
    }

    /**
     * A Custom Iterator Class for iterating through the vertices in a graph
     * 
     *
     * @param <Vertex>
     */
    private class VertexIterator implements Iterator<Vertex> {
	private Iterator<Vertex> it;
	/**
	 * Constructor for VertexIterator
	 * 
	 * @param v
	 *            : Array of vertices
	 * @param n
	 *            : int - Size of the graph
	 */
	private VertexIterator() {
	    it = verts.iterator();
	    it.next();  // Index 0 is not used.  Skip it.
	}

	/**
	 * Method to check if there is any vertex left in the iteration
	 * Overrides the default hasNext() method of Iterator Class
	 */
	public boolean hasNext() {
	    return it.hasNext();
	}

	/**
	 * Method to return the next Vertex object in the iteration
	 * Overrides the default next() method of Iterator Class
	 */
	public Vertex next() {
	    return it.next();
	}

	/**
	 * Throws an error if a vertex is attempted to be removed
	 */
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
    
    public Edge ReturnEdge(Vertex v, Vertex u)
    {
    	Edge e = new Edge(v, u, 1);
    	return e ;
    }
    
    public int EdgeWeight(Vertex v, Vertex u)
    {
    	List<Edge> l1 = v.Adj;
    	for (Edge e : l1)
    	{
    		if (e.To == u)
    		{
    			return e.Weight;
    		}
    		else 
    			continue ;
   
    	}
    	return -1 ;
    	
    }
    
    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
	int n = in.nextInt(); // number of vertices in the graph
	int m = in.nextInt(); // number of edges in the graph
	//int weight =0;
	// create a graph instance
	Graph g = new Graph(n);
	for (int i = 0; i < m; i++) {
	    int u = in.nextInt();
	    int v = in.nextInt();
	    int w = in.nextInt();
	    if(w<0)
	    	hasNegativeEdges=true;
	    if(i>0)
	    	if(uniformWeight!=w){
	    		hasUniformWeights=false;
	    	}
	    	else{
	    		uniformWeight=w;
	    	}
	    else
	    	uniformWeight=w;
	    if(directed) {
		g.addDirectedEdge(u, v, w);
	    } else {
		g.addEdge(u, v, w);
	    }
	}
	in.close();
	return g;
    }
}