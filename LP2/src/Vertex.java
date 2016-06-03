/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Index <Vertex>,Comparator<Vertex> {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public List<Vertex> AdjV ;	// adjacency list for adjacent vertices 
    public int degree;	// indegree of the vertices
    public int index;
    
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	parent = null;
	degree = 0;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	AdjV = new ArrayList<Vertex>();		/* implemented to help in BFS*/
	index = 0;
    }
    public void putIndex(int index){
    	this.index=index;
    }
    public int getIndex(){
    	return index;
    }
    public int compare(Vertex u,Vertex v){
    	return v.distance-u.distance;
    }
    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
}