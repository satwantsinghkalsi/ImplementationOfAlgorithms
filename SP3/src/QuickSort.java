import java.util.Scanner;
/**
 * @author G32
 *	Class created to implement and compare QuickSort
 *  using traditional and multipivot partitions(2 and/or 3 pivots).
 */
public class QuickSort<T extends Comparable< ? super T>> {
	/**
	 * Method for partition of array for quicksort using single pivot
	 * @param a: Input array 
	 * @param p: start index of array 
	 * @param r: ending index of array
	 * @return pivot index in array
	 */
	int singlePartition(T a[],int p, int r)
	{
		int k=getRandom(a,p,r);
		T temp=a[r];
		a[r]=a[k];
		a[k]=temp;
		T x= a[r];//pivot;
		int i=p-1;
		for(int j=p;j<=r-1;j++){
			if(a[j].compareTo(x)<=0){
				i++; 
				swap(a, i, j);
			}
		}
		swap(a, i+1, r);
		return i+1;
	}
	/**
	 * Method for getting random pivot
	 * @param array:input array
	 * @param p
	 * @param r 
	 * @return random index
	 */
	int getRandom(T[] array,int p ,int r) {
		return (p+r)/2;
	}
	/**
	 * Helper function for swapping two elements in an array
	 * @param a: input array
	 * @param i:index to be swapped
	 * @param j: index to be wapped
	 */
	private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
	/**
	 * Method implementing the quicksort calling partition recursively.
	 * @param a
	 * @param p
	 * @param r
	 */
	void quickSort(T a[], int p, int r){
		if(p<r)
		{
			int q=singlePartition(a, p, r);
			quickSort(a, p, q-1);
			quickSort(a, q+1, r);
		}
	}
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		sc.close();
		Integer a[]= new Integer[n];
		Timer time = new Timer();
		for(int i=0;i<n;i++)
			a[i]=i+1;
		Shuffle.shuffle(a, 0, a.length-1);
		for(int i=0;i<20;i++)
			System.out.print(" "+a[i]);
		System.out.println();
		QuickSort<Integer> sort= new QuickSort<>();
		time.start();
		sort.quickSort(a,0, a.length-1);
		time.end();
		System.out.println(time);
		for(int i=0;i<20;i++)
			System.out.print(" "+a[i]);
	}
}
/**
 * Sample Input:
 * 14 1 5 10 11 16 19 12 13 9 18 2 17 15 7 3 8 20 6 4
 * Output:
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
**/