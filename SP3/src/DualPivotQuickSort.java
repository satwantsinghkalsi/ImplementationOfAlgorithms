import java.util.Scanner;
/**
* @author G32
*	Class created to implement Dual Pivot QuickSort
*  
*/
public class DualPivotQuickSort<T extends Comparable<? super T>>{
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
	 * Method implementing the dual pivot quicksort
	 * @param a:input array
	 * @param p: start index
	 * @param r: end index
	 */
	void multipivotQuickSort(T [] a, int p, int r){
		if(p>=r)return;
		int mid=getRandom(a, p, r);
		int m1 = getRandom(a, p, mid);
        int m2 = getRandom(a, mid, r);
        //Comparing which is greater and swapping
        if (a[m1].compareTo(a[m2])<0){
            swap(a, m1, p);
            swap(a, m2, r);
        }
        else {
            swap(a, m1, r);
            swap(a, m2, p);
        }
		if(a[p].compareTo(a[r])>0){
			swap(a, p, r);
		}
		int l=p+1;
		int j=r-1;
		int i=l;
		while(i<=j){
			if(a[i].compareTo(a[p])<0){
				swap(a, l, i);
				l++;
				i++;
			}
			else if(a[r].compareTo(a[i])<0){
				swap(a, i, j);
				j--;
			}
			else
				i++;
			
		}
		swap(a, p, --l);
		swap(a, r, ++j);
		multipivotQuickSort(a, p, l-1);
		if(a[l].compareTo(a[j])<0)
			multipivotQuickSort(a, l+1, j-1);
		multipivotQuickSort(a, j+1, r);
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
			DualPivotQuickSort<Integer> sort= new DualPivotQuickSort<>();
			time.start();
			sort.multipivotQuickSort(a, 0, a.length-1);
			time.end();
			System.out.println(time);
			for(int i=0;i<20;i++)
				System.out.print(" "+a[i]);
		}
	/**
	 * Sample Input:
	 * 14 1 5 10 11 16 19 12 13 9 18 2 17 15 7 3 8 20 6 4
	 * Output:
	 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	**/
}
