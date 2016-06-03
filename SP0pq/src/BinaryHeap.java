// Ver 1.0:  Wec, Feb 3.  Initial description.

import java.security.PermissionCollection;
import java.util.Comparator;

public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    static int size;
    Comparator<T> c;
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
    size=q.length-1;
	pq = q;
	c = comp;
	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
    	pq = (T[ ]) new Object[n];
    	c=comp;
    	size=0;
    }

    public void insert(T x) {
	add(x);
    }

    public T deleteMin() {
	return remove();
    }

    public T min() { 
	return peek();
    }
    public void assign(int i,T element)
    {
    	pq[i]=element;
    }
    int size(){
    	return size;
    }
    void print()
    {
    	for(int i=1;i<=size;i++)
    		System.out.print(pq[i]+" ");
    }
    boolean isEmpty()
    {
    	if(size==0)
    		return true;
    	else
    		return false;
    }
    void resize()
    {
	      T [] old = pq;
	      pq = (T []) new Object[ size*2 ];
	      for( int i = 0; i < old.length; i++ )
	    	  pq[ i ] = old[ i ];        
    }
    public void add(T x) { /* to be implemented */
    	
    	if(size==pq.length-1)
    	{
    		resize();
    	}
    	size++;
    	assign(size,x);
      	percolateUp(size);
    }

    public T remove() { /* to be implemented */
    	T min =pq[1];
    	assign(1,pq[size--]);
    	percolateDown(1);
    	return min;
    }

    public T peek() { /* to be implemented */
	return null;
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { /* to be implemented */
    	assign(0,pq[i]);
    	while(c.compare(pq[i/2],pq[0])>0)
    	{
    		assign(i,pq[i/2]);
    		i=i/2;
    	}
    	assign(i,pq[0]);
    	
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) { /* to be implemented */
    	int child=0;
    	T tmp=pq[i];
    	for(;i*2<=size;i=child)
    	{
    		child=i*2;
    		if(child!=size && c.compare(pq[child+1], pq[child])<0)
    			child++;	//if right child exists and greater than left child then use right child
    		if(c.compare(pq[child], tmp)<0)
    			assign(i,pq[child]);
     		else
    			break;
    		
    	}
    	assign(i,tmp);				//finally put value in the hole;
    	
    }

    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	
    	for(int i=size/2;i>1;i--)
    		percolateDown(i);
    	updateIndex(pq);
    	
    }
    void updateIndex(T a[]){
    	
    }

    /* sort array A[1..n].  A[0] is not used. 
       Sorted order depends on comparator used to build heap.
       min heap ==> descending order
       max heap ==> ascending order
     */
    public static<T> void heapSort(T[] A, Comparator<T> comp) { /* to be implemented */
    	
    	BinaryHeap<T> heap = new BinaryHeap<>(A, comp);
    	for(int i=size;i>0;i--){
    		T tmp=heap.pq[1];
    		heap.pq[1]=heap.pq[i];
    		heap.pq[i]=tmp;
    		size--;
    		heap.percolateDown(1);
    	}
    	for(int i=1;i<=heap.pq.length-1;i++)
    		System.out.print(heap.pq[i]+"   ");
    		
    }
    public static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return x.intValue() - y.intValue();
        }
    }
    public static void main(String args[])
    {
    	Integer a[]=new Integer[11];
    	for(int i=1;i<=10;i++)
    		a[i]=10-i;
    	Comparator<Integer> comp = new IntegerComparator();
    	heapSort(a,  comp);
    	BinaryHeap<Integer> list= new BinaryHeap<>(11, comp);
    	for(int i=1;i<=10;i++)
    		list.add(10-i);
    	for(int i=1;i<=10;i++)
    		System.out.println(list.remove());
    	
    	
    }
}