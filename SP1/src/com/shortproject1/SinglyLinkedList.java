package com.shortproject1;

/** @author rbk
 *  Singly linked list: for instructional purposes only
 */

import java.util.ArrayDeque;

public class SinglyLinkedList<T> {
    public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    Entry<T> header, tail;
    int size;

    SinglyLinkedList() {
        header = new Entry<>(null, null);
        tail = null;
        size = 0;
    }

    void add(T x) {
        if(tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
	size++;
    }

    void printList() {
        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }

    void unzip() {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> tail0 = header.next;
	Entry<T> head1 = tail0.next;
	Entry<T> tail1 = head1;
	Entry<T> c = tail1.next;
	int state = 0;

	// Invariant: tail0 is the tail of the chain of elements with even index.
	// tail1 is the tail of odd index chain.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state = i indicates that the current element is added after taili (i=0,1).
	while(c != null) {
	    if(state == 0) {
		tail0.next = c;
		tail0 = c;
		c = c.next;
	    } else {
		tail1.next = c;
		tail1 = c;
		c = c.next;
	    }
	    state = 1 - state;
	}
	tail0.next = head1;
	tail1.next = null;
    }

/** @author G32
 *  Operations added on Singly linked list:
 */
    
/*
* i)Reverse a  linked list non recursively  
*/
// Invariant: header is the leading node.
// p is the current node to be processed.
// tmpNode is the previous node to the current node p.
    
    void reverseWithOutRecur()
    {
    	Entry<T> tmpNode = null;
    	Entry<T> nextNode=null;
    	Entry<T> p = header.next;
    	while (p != null) {
    		nextNode = p.next;
    	    p.next = tmpNode;
    	    tmpNode = p;
    	    p = nextNode;
    	}
    	header.next = tmpNode;
    }
/*
* ii)print a  linked list non recursively  
*/
    void printReverseWithOutRecur()
    {
    	Entry<T> p = header.next;
    	ArrayDeque<T> list =new ArrayDeque<>();
    	while (p != null) {
    		list.push(p.element);
    		p=p.next;
    	}
    	while(!list.isEmpty())
    	{
    		System.out.print(list.pop()+" ");
    	}
    	System.out.println();
    }
/*
* iii)print a  linked list recursively  
*/
 // private method called from a public method to pass the header to the function.
 // Invariant: header is the leading node.
 // p is the current node to be processed.
 // p.next.next is the node next to p.
    private void reverseWithRecur(Entry<T> p) {
    	if(p.next==null)
    	 {
    		 header.next=p;
    		 return;
    	 }
    	 reverseWithRecur(p.next);
    	 p=p.next.next=p;
    	 p.next=null;
    	}
    void reverseWithRecur()
    {
    	reverseWithRecur(header.next);
    }
/*
* iv)print a  linked list recursively  
*/
// private method called from a public method to pass the header to the function.
    private void printWithRecur(Entry<T> p)
    {
    	p=p.next;
    	if(p.next!=null)
    		printWithRecur(p);
    	System.out.print(p.element+" ");
    }
    void printWithRecur()
    {
    	printWithRecur(header);
    }
    public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
       // lst.printList();
        long startTime = System.currentTimeMillis();
        lst.reverseWithOutRecur();
	    long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println(elapsedTime);
        
        //lst.unzip();
        lst.printList();
    }
}

/* Sample output:
   1 2 3 4 5 6 7 8 9 10 
   1 3 5 7 9 2 4 6 8 10
*/

