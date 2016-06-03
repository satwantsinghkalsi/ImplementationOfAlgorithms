import java.util.Scanner;
/**
 * 
 * @author G32
 * Class created for the comparison between O(n) and O(logn)algorithms
 * for computing f(n) nth Fibonacci number.  
 *
 */
public class SP3_a {
	/**
	 * Method implemented for the O(n) algorithm for computing nth Fibonacci number  
	 * @param n:input n
	 * @param p:using:999953
	 * @return nth fibonacci number
	 */
	public static long linearFibonacci(long n, long p)
	{
		long fib0 =0 ;
		long fib1 = 1;
		long Fib =0 ;
		if (n == 0)
			return fib0;
		else if (n == 1)
			return fib1;
		else 
		{
			for (int i = 2 ; i <= n ; i ++)
			{
				Fib = (fib0%p + fib1%p)%p ;
				fib0 = fib1 ;
				fib1 = Fib;
			}
			if ( n <= 80)
				return Fib;
			else
			{
				return Fib%p ;
			}
		}
		}
	/**
	 * Method implemented for the O(logn) algorithm for computing nth Fibonacci number  
	 * @param n:input n
	 * @param p:to prevent the value of long to not overflow = 999953
	 * @return nth fibonacci number
	 */
	public static long logFibonacci(long n, long p)
	{
		long[][] A = new long[2][2];
		A[0][0] = 1;
		A[0][1] = 1;
		A[1][0] = 1;
		A[1][1] = 0;
		
		long[] Fib = new long [2];
		Fib[0] = 1 ;
		Fib[1] = 0;
		if (n == 0)
			return 1;
		else if (n ==1)
			return 1;
		
		else
		{
		
			A = power(A,n-1,p);
			Fib[0] = (A[0][0]*Fib[0])+(A[0][1]*Fib[1]);
			Fib[1] = (A[1][0]*Fib[0])+(A[1][1]*Fib[1]);
		if (n <= 80)
			return Fib[0];
		else
			return Fib[0]%p;
		}
	}
	/**
	 * Method to calculate power of the matrix A
	 * @param x: Matrix A
	 * @param n: Power of the Matrix A
	 * @param p: to prevent the value of long to not overflow = 999953
	 * @return: matrix A with power n
	 */
	
	public static long[][] power (long[][] x,long n,long p){
		long[][] A = new long[2][2];
		if (n == 0)
		{
			A[0][0] =1;
			A[0][1] =1;
			A[1][0] =1;
			A[1][1] =1;
			return A;
		}
		else if (n ==1)
			return x;
		else
		{
			long[][] res = power(matrixMultiply(x,x,p),n/2,p);
			if(n%2 == 0)
				return res;
			else
			{
				return (matrixMultiply(res,x,p));
			}
		}
	}
	/**
	 * Method to calculate multiplication of matrix1 and matrix2
	 * @param x: matrix1
	 * @param y: matrix2
	 * @param p:to prevent the value of long to not overflow = 999953
	 * @return product of matrix1 and matrix2
	 */
	public static long[][] matrixMultiply(long[][]x , long[][] y, long p)
	{
		long[][]A = new long[2][2];
		A[0][0] = ((x[0][0]%p)*(y[0][0]%p))%p+((x[0][1]%p)*(y[1][0]%p))%p;
		A[0][1] = ((x[0][0]%p)*(y[0][1]%p))%p+((x[0][1]%p)*(y[1][1]%p))%p;
		A[1][0] = ((x[1][0]%p)*(y[0][0]%p))%p+((x[1][1]%p)*(y[1][0]%p))%p;
		A[1][1] = ((x[1][0]%p)*(y[0][1]%p))%p+((x[1][1]%p)*(y[1][1]%p))%p;	
		
		return A ;
	}
	
	public static void main (String args[])
	{
		Scanner s = new Scanner(System.in);
		Timer time = new Timer();
		long l = s.nextLong();
		 time.start();
		System.out.println("linearFibonacci:"+linearFibonacci(l, 999953));
		 time.end();
		 System.out.println(time);
		 time.start();
         System.out.println("longFibonacci:"+logFibonacci(l,999953));
         time.end();
         System.out.println(time);
	}
}
