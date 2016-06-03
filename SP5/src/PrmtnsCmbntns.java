import java.util.Scanner;
/**
 * 
 * @author Satwant Singh
 * 			Himanshu Parashar
 * Class created for short project 5
 *
 */
public class PrmtnsCmbntns {
	static int cntPrmtns;
	static int cntCmbntns;
	/**
	 * Method to visit and print the Combinations.
	 * @param boolean a[]
	 */
	void visitCmbntn(boolean a[]){
		for(int i=1;i<a.length;i++)
			if(a[i]){
				System.out.print(i+" ");
			}
	}
	/**
	 * Method to visit and print permutations
	 * @param int a[]
	 */
	void visitPrmtn(int a[]){
		for(int i=1;i<a.length;i++)
			System.out.print(a[i]+" ");
	}
	/**
	 * SP5-a
	 * Method to get integer array from boolean array to get the permutations. 
	 * @param boolean a[]
	 * @return int[]
	 */
	int[] visitkPrmtnsOfn(boolean a[]){
		int len=0;
		for(int i=1;i<a.length;i++)
			if(a[i]){
				len++;
			}
		int b[]= new int[len+1];
		len=1;
		for(int i=1;i<a.length;i++){
			if(a[i]){
				b[len++]=i;
			}
		}
		return b;
	}
	/**
	 * SP5-a
	 * Method to get combinations C(A,n,k)
	 * @param boolean a[]
	 * @param n
	 * @param k
	 */
	public void combinations(boolean a[],int n, int k){
		if(k>n)
			return;
		else if(k==0){
			if(n<=18)
				visitCmbntn(a);
			cntCmbntns++;
		}
		else{
			combinations(a, n-1, k);
			a[n]=true;
			combinations(a, n-1, k-1);
			a[n]=false;
		}
		System.out.println();
	}
	/**
	 * SP5-a
	 * Method to get permutations P(A,n)
	 * @param int a[]
	 * @param n
	 */
	void permute(int a[],int n){
		if(n==0){
			if(n<=18)
				visitPrmtn(a);
			cntPrmtns++;
		}
		else{
			for(int i=1;i<=n;i++){
				swap(a, i, n);
				permute(a, n-1);
				swap(a, i, n);
			}
		}
		System.out.println();
	}
	/**
	 * Helper functions to swap two elements of an array
	 * @param int b[]
	 * @param i
	 * @param j
	 * @return int[]
	 */
	int[] swap(int b[],int i, int j){
		int temp= b[i];
		b[i]=b[j];
		b[j]=temp;
		return b;
	}
	/**
	 * SP5-a
	 * Method implementing the Heap's algorithm for permutations 
	 * @param a
	 * @param n
	 */
	public void heapPermute(int a[], int n){
		if(n==1){
			if(n<=18)
				visitPrmtn(a);
			cntPrmtns++;
		}
		else{
			for(int i=0;i<n-1;i++){
				heapPermute(a, n-1);
				if(n%2==0)
					swap(a, i, n-1);
				else
					swap(a, 1, n-1);
			}
			heapPermute(a, n-1);
		}
		System.out.println();
	}
	/**
	 * SP5-b
	 * Method implemented to get P(A,n,k)ordered sets of cardinality k from a set of size n.
	 * @param bbolean a[]
	 * @param n
	 * @param k
	 */
	public void kPrmtntnsOfn(boolean a[], int n, int k){
		if(k>n)
			return;
		if(k==0){
			int t[]=visitkPrmtnsOfn(a);
			permute(t, t.length-1);
		}
		else{
			kPrmtntnsOfn(a, n-1, k);
			a[n]=true;
			kPrmtntnsOfn(a, n-1, k-1);
			a[n]=false;
		}
	}
	
	public static void main(String args[]){
		PrmtnsCmbntns tst = new PrmtnsCmbntns();
		Scanner sc = new Scanner(System.in);
		Timer time= new Timer();
		Timer t= new Timer();
		int n=sc.nextInt();
		int k= sc.nextInt();
		int b[]= new int[n+1];
		int c[]= new int[n];
		boolean a[]= new boolean[n+1];
		for(int i=1;i<a.length;i++){
			a[i]=false;
			b[i]=i;
		}
		for(int i=0;i<c.length;i++)
			c[i]=i+1;
		//No of combinations
		tst.combinations(a, n, k);
		if(n<=18)
			System.out.println(cntCmbntns);
		t.start();
		//No of permutations
		tst.permute(b, n);
		if(n<=18)
			System.out.println(cntPrmtns);
		t.end();
		time.start();
		// No of permutations using heap's algorithm
		tst.heapPermute(c, n);
		if(n<=18)
			System.out.println(cntPrmtns);
		time.end();
		System.out.println("Heap's:"+time);
		System.out.println("Take 2:"+t);
		//Value of P(A,n,k)
		tst.kPrmtntnsOfn(a, n, k);
		if(n<=18)
			System.out.println(cntPrmtns);
		
	}
}