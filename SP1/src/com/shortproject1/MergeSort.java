package com.shortproject1;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;
public class MergeSort<T>
{
    public static <T extends Comparable<? super T>> void mergeSort(T a[],T tmp[],int first, int last )
    {
       	ArrayDeque<int []> stack= new ArrayDeque<>();
       	int index[]= new int[3];
    	int last2=last;
    	int center2=((first+last)/2)+1;
    	while(center2<last2)
    	{
    		int center =(center2+last2)/2;
    	//	System.out.println("center2="+center2+":center="+center+":last2="+last2);
    		index[0]=center2;
    		index[1]=center;
    		index[2]=last2;
    		stack.add(index);
            center2=center+1;
    	}
    	while(first<last)
        {
    		//System.out.println("******************");
            int center =(first+last)/2;
            index[0]=first;
    		index[1]=center;
    		index[2]=last;
    		stack.add(index);
            last=center;
        /*    mergeSort(a,tmp,first,center);
            mergeSort(a,tmp,center+1,last);
            merge(a,tmp,first,last,center+1);*/
        }
    	
    	 merge(a,tmp,stack);
    }
    public static <T extends Comparable<? super T>> void merge(T arr[],T tmp[],ArrayDeque<int []> stack)
    {
    	
    	
    	while(!stack.isEmpty())
    	{
    		int a[]=stack.pop();
    	System.out.println(a[0]+" "+a[1]+" "+a[2]);
    	int first = a[0];
    	int last = a[2];
    	int rightend=a[1];
        int tmpPos=first;
        int leftend=rightend-1;
        int numOfElements=last-first+1;
        while(first<=leftend && rightend<=last)
        {
            if(arr[first].compareTo(arr[rightend])<=0)
                tmp[tmpPos++]=arr[first++];
            else
                tmp[tmpPos++]=arr[rightend++];
        }
        while(first<=leftend)
            tmp[tmpPos++]=arr[first++];
        while(rightend<=last)
            tmp[tmpPos++]=arr[rightend++];
        for(int i=0;i<numOfElements;i++,last--)
            arr[last]=tmp[last];
    	}


    }
    static<T> void firstTen(T[] A) {
    int n = Math.min(A.length, 10);
    for(int i=0; i<n; i++) {
        System.out.print(A[i] + " ");
    }
    System.out.println();
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Integer[] A = new Integer[n];
        Integer[] tmp = new Integer[n];
      
        for(int i=0; i<n; i++) {
            A[i] = new Integer(n-i);
        }

        firstTen(A);
        long startTime = System.currentTimeMillis();
        mergeSort(A,tmp,0, n-1);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        firstTen(A);
    }
}
