// Ver 1.0:  Wed, Feb 3.  Initial description.
// Ver 1.1:  Thu, Feb 11.  Simplified Index interface

import java.util.Comparator;

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
	for(int i=1;i<pq.length;i++){
		pq[i].putIndex(i);
		}
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(x.getIndex());
    }
    
    public void assign(int i,T element)
    {
    	pq[i]=element;
    	element.putIndex(i);
    }
   /* void updateIndex(T a[]){
    	for(int i=0;i<a.length;i++){
    		a[i].putIndex(i);
    		System.out.println(a[i]+":"+i);
    	}
    }*/
}
