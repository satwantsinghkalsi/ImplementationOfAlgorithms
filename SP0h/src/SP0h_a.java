import java.util.HashSet;
import java.util.Iterator;
/**
* 
* @author G32:Himanshu Parashar
* 			   Satwant Singh 
**/
public class SP0h_a<T>{
/**
 * This method is finding distinct elements in an array and arranging them at the beginning of the same array 
 * @param arr : array of type generic in which distinct objects have to be found
 * @return : returns number of distinct objects
 */
	static final int input = 1000000;
	public static<T> int findDistinct(T[] arr)
	{
		 HashSet<T> hs = new HashSet<>();
		for (int i=0;i<arr.length;i++ )
		{

			if (hs.contains(arr[i]))
			{
				hs.remove(arr[i]);
				continue ;
			}
			else
			hs.add(arr[i]);
		}
		
		
		 Iterator<T> it = hs.iterator();
		 
		 T tmp = it.next();

			for (int i= 0 ; i<hs.size();i++)
			{
				arr[i] = tmp ;
				if (!it.hasNext())
					break;
				else
				tmp = it.next();
			}
		return hs.size();
		
	}
	
	public static void main (String args[])
	{
		
		Integer[] arr = new Integer[input];
		int k = arr.length;
		for (int i =0;i<arr.length -500;i++)
		{
			arr[i] = k--;			
		}
		for (int i =arr.length -500;i<arr.length;i++)
		{
			arr[i] = i ;
		}
		for (int i = 0; i<20 ; i++)
		{
			System.out.print(" "+arr[i]);
		}
		System.out.println("");
		long start = System.currentTimeMillis();
		System.out.println(findDistinct(arr));
		long end = System.currentTimeMillis();
		
		
		for (int i = 0; i<20; i++)
		{
			System.out.print(" "+arr[i]);
		}
		long time = end - start;
		System.out.println("");
		System.out.println("time in milliseconds: "+time);
	}
}

/**
 * Sample output for input = 1 million. It has 500 duplicates hence 999000 distinct elements
 * 
 * 
    1000000 999999 999998 999997 999996 999995 999994 999993 999992 999991 999990 999989 999988 999987 999986 999985 999984 999983 999982 999981
	999000
 	501 502 503 504 505 506 507 508 509 510 511 512 513 514 515 516 517 518 519 520
	time in milliseconds: 193
  
 * prints first 20 elements.
 * change the value of static variable 'input' to change the size.
 * As per our code non distinct elements will be 1000.
 * 
 */ 
