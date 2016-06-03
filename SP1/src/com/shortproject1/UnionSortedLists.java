package com.shortproject1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UnionSortedLists {
	
	 public static<T extends Comparable<? super T>>
     void union(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1= l1.iterator();
		 Iterator<T> it2= l2.iterator();
		 T X=it1.next();
		 T Y=it2.next();
		 while(X!=null && Y!= null)
		 {
			 if(X.compareTo(Y)<0)
			 {
				 outList.add(X);
				 X=next(it1);
			 }
			 if(X.compareTo(Y)>0)
			 {
				 outList.add(Y);
				 Y=next(it2);
			 }
			 else
			 {
				 outList.add(X);
				 X=next(it1);
				 Y=next(it2);
			 }
		 }
		 if(X!=null)
		 {
			 while(X!=null)
			 {
				 outList.add(X);
				 X=next(it1);
			 }
				 
		 }

		 if(Y!=null)
		 {
			 while(Y!=null)
			 {
				 outList.add(Y);
				 Y=next(it2);
			 }
				 
		 }
	   
}
	 public static<T extends Comparable<? super T>>
     void difference(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1= l1.iterator();
		 Iterator<T> it2= l2.iterator();
		 T X=it1.next();
		 T Y=it2.next();
		 while(X!=null && Y!= null)
		 {
			 if(X.compareTo(Y)<0)
			 {
				 outList.add(X);
				 X=next(it1);
			 }
			 else if(X.compareTo(Y)>0)
			 {
				Y=next(it2);
			 }
			 else
			 {
				 X=next(it1);
				 Y=next(it2);
			 }
		 }
		 if(X!=null)
		 {
			 while(X!=null)
			 {
				 outList.add(X);
				 X=next(it1);
			 }
				 
		 }

	}
	 public static<T extends Comparable<? super T>>
     void intersect(List<T> l1, List<T> l2, List<T> outList) {
		 Iterator<T> it1= l1.iterator();
		 Iterator<T> it2= l2.iterator();
		 T X=it1.next();
		 T Y=it2.next();
		 while(X!=null && Y!= null)
		 {
			 if(X.compareTo(Y)<0)
			 {
				 X=next(it1);
			 }
			 else if(X.compareTo(Y)>0)
			 {
				Y=next(it2);
			 }
			 else
			 {
				 outList.add(X);
				 X=next(it1);
				 Y=next(it2);
			 }
		 }
		
		
		 

		
	}


	 public static <T> T next(Iterator<T> itr)
	 {
		 return itr.hasNext()?itr.next():null;
	 }
	 public static void main(String args[])
	 {
		 List<Integer> list1 = new LinkedList<>();
		 List<Integer> list2 = new LinkedList<>();
		 List<Integer> unionList = new LinkedList<>();
		 for(int i=0;i<10;i++)
		 {
			 list1.add(i+20);
			 list2.add(i);
		 }
		 intersect(list1, list2, unionList);
		 System.out.println(list1);
		 System.out.println(list2);
		 System.out.println(unionList);
		 
	 }

}
