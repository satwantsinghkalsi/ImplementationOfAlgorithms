import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * @author G32
 *	Class created to implement MergeSort recursively.
 */
public class MergeSort {
	/**
	 * Method to implement MergeSort
	 * @param arr:input array
	 * @param tmp:temporary array
	 * @param p:starting index of array
	 * @param r:ending index of array
	 */
	public static <T extends Comparable<? super T>> void mergeSort( T arr[], T tmp[], int p, int r)
	{
		if (p<r)
		{
			int q= (p+r)/2 ;
			mergeSort(arr,tmp,p,q);
			mergeSort(arr,tmp,q+1,r);
			Merge(arr,tmp,p,q,r);
		}
	}
	/**
	 * Method for merging two sub arrays of the mergesort
	 * @param arr:input array
	 * @param tmp: temporary array
	 * @param left:begining index of array
	 * @param cen:middle index of array
	 * @param end:ending index of array
	 */
	 private static <T extends Comparable<? super T>> void Merge (T arr[], T tmp[], int left, int cen, int end)
	 {
		 int tmppos = left ;
		 int rightPos = cen+1 ;
		int n = end-left+1;
		 while (left <= cen && rightPos <= end)
		 {
			 if ((arr[left].compareTo(arr[rightPos])<= 0))
			 	 tmp[tmppos++] = arr[left++];			 
			 else
				 tmp[tmppos++] = arr[rightPos++];			 
		 }
		 while (left <= cen)
		 {
			 tmp[tmppos++] = arr[left++];			 
		 }
		 while (rightPos <= end)
		 {
			 tmp[tmppos++] = arr[rightPos++];
		 }
		 
		 for (int i = 0;i <n ; i++,end-- )
		 {
			 arr[end] = tmp[end];
			 
		 }			 
	 }
	 
	 public static void main(String[] args) throws IOException{
		 Scanner sc = new Scanner(System.in);
	        int n = Integer.parseInt(sc.nextLine());
	        Integer[] A = new Integer[n];
	        Integer[] tmp1 = new Integer[n];
	 
	    for(int i=0; i<n; i++) {
	        A[i] = new Integer(n-i);
	    }
	    firstTen(A);
	    
	    //calculating Running time for merge sort
	    Timer time= new Timer();
	    time.start();
	    mergeSort(A, tmp1 , 0, n-1);
	    time.end();
	    firstTen(A);
	    System.out.println(time);
	 }
	 
	  static<T> void firstTen(T[] A) {
	        int n = Math.min(A.length, 10);
	        for(int i=0; i<n; i++) {
	            System.out.print(A[i] + " ");
	        }
	        System.out.println();
	     }	 
}
