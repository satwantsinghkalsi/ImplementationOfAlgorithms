/** @author rbk
 * 	Edited by: Himanshu Parashar, Satwant Singh
 * 				
 *  Binary search tree (nonrecursive version)
 **/

import java.util.*;

public class BST<T extends Comparable<? super T>> {
	int count =0;
    class Entry<T> {
        T element;
        Entry<T> left, right, parent;

        Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
            element = x;
	    left = l;
	    right = r;
	    parent = p;
        }
    }
    
    Entry<T> root;
    int size;

    BST() {
	root = null;
	size = 0;
    }
    /**
     * SP4 b)
     * Build a balanced BST using elements of sorted array.
     * @param arr sorted array
     */
    BST(T[] arr) {
    	ArrayDeque<Entry<T>> lst = new ArrayDeque<>();
    	int mid=arr.length/2;
    	int i=mid-1,j=mid+1;
    	root = new Entry<>(arr[mid], null, null, null);
		lst.add(root);
		while(i>=0 || j<arr.length){
			Entry<T> temp= lst.pop();
			System.out.println(temp.element);
			if(i>=0){
				Entry<T> left=new Entry<T>(arr[i--],null, null, temp);
				temp.left=left;
				lst.add(left);
			}
			if(j<arr.length){
				Entry<T> right=new Entry<T>(arr[j++],null, null, temp);
				temp.right=right;
				lst.add(right);
			}
		}
		size=arr.length;	
	}
    BST(List<T> lst){
    	
    }
    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
	Entry<T> pre = t;
	while(t != null) {
	    pre = t;
	    int cmp = x.compareTo(t.element);
	    if(cmp == 0) {
		return t;
	    } else if(cmp < 0) {
		t = t.left;
	    } else {
		t = t.right;
	    }
	}
	return pre;
    }

    // Is x contained in tree?
    public boolean contains(T x) {
	Entry<T> node = find(root, x);
	return node == null ? false : x.equals(node.element);
    }

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new Entry<>(x, null, null, null);
	} else {
	    Entry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    Entry<T> newNode = new Entry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++; 
	return true;
    }

    // Remove x from tree.  Return x if found, otherwise return null
    public T remove(T x) {
	T rv = null;
	if(size > 0) {
	    Entry<T> node = find(root, x);
	    if(x.equals(node.element)) {
		rv = node.element;
		remove(node);
		size--;
	    }
	}
	return rv;
    }

    // Called when node has at most one child.  Returns that child.
    Entry<T> oneChild(Entry<T> node) {
	return node.left == null? node.right : node.left;
    }

    // Remove a node from tree
    void remove(Entry<T> node) {
	if(node.left != null && node.right != null) {
	    removeTwo(node);
	} else {
	    removeOne(node);
	}
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
	if(node == root) {
	    root = oneChild(root);
	} else {
	    Entry<T> p = node.parent;
	    if(p.left == node) {
		p.left = oneChild(node);
	    } else {
		p.right = oneChild(node);
	    }
	}
    }

    // remove node that has two children
    /*void removeTwo(Entry<T> node) {
	Entry<T> minRight = node.right;
	while(minRight.left != null) {
	    minRight = minRight.left;
	}
	node.element = minRight.element;
	removeOne(minRight);
    }*/
    /**
     * SP4-c
     * Modified the remove in the BST Class
     * @param x
     */
    void removeTwo(Entry<T> x)
    {
    	Entry<T> maxLeft = x.left;
    	Entry<T> minRight = x.right;
    	if (count%2 != 0)
    	{
    		count ++;
    		while (maxLeft.right !=null)
    		{
    			maxLeft = maxLeft.right;
    		}
    		x.element = maxLeft.element;
    		removeOne(maxLeft);
    	}
    	else
    	{
    		count ++ ;
    		while(minRight.left != null) {
    		    minRight = minRight.left;
    		}
    		x.element = minRight.element;
    		removeOne(minRight);
    	}
    }
    /**
     * //SP A
     * Level Order of the Binary Search Tree
     * @return
     */
    Comparable[] levelOrderArray() {
    	Comparable[] a = new Comparable[size];
    	a = levelOrder(root, a ,0);    	
    	return a ;
    }
   
    Comparable[] levelOrder(Entry<T> node,Comparable[] a , int i ){
    	Queue<Entry> q = new LinkedList<>();
        	q.add(node);
        	while (!q.isEmpty())
        	{
        		Entry<T> tmp = q.poll();
        		a[i++] = tmp.element; 
        		System.out.println(tmp.element );
        		if (tmp.left != null)
        		{
        			q.add(tmp.left);
        		}
        		if (tmp.right != null)
        		{
        			q.add(tmp.right);
        		}
        			
        	}
        	return a ;
    }
    public static void main(String[] args) {
    	Integer a[]={10,9,8,7,6,5,4,3,2,1};
	BST<Integer> t = new BST<>(a);
	Comparable[] arr = t.levelOrderArray();
	System.out.print("Final: ");
	for(int i=0; i<t.size; i++) {
	    System.out.print(arr[i] + " ");
	}
	System.out.println();
	/*Scanner in = new Scanner(System.in);
	while(in.hasNext()) {
	    int x = in.nextInt();
	    if(x > 0) {
		System.out.print("Add " + x + " : ");
		t.add(x);
		t.printTree();
	    } else if(x < 0) {
		System.out.print("Remove " + x + " : ");
		t.remove(-x);
		t.printTree();
	    } else {
		Comparable[] arr = t.levelOrderArray();
		System.out.print("Final: ");
		for(int i=0; i<t.size; i++) {
		    System.out.print(arr[i] + " ");
		}
		System.out.println();
		return;
	    }		
	}*/
    }

    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
	Comparable[] arr = new Comparable[size];
	inOrder(root, arr, 0);
	return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
	if(node != null) {
	    index = inOrder(node.left, arr, index);
	    arr[index++] = node.element;
	    index = inOrder(node.right, arr, index);
	}
	return index;
    }

    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }
}
/*
Sample input:
	1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

Extending to AVL tree:

    class AVLEntry<T> extends Entry<T> {
	int height;
	AVLEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
	    super(x,l,r,p);
	    height = 0;
	}
    }

Extending to Red-Black tree:

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    class RBEntry<T> extends Entry<T> {
	boolean color;
	RBEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
	    super(x,l,r,p);
	    color = RED;
	}
    }

*/