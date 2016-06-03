import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Satwant Singh,Himanshu Parashar
 * 
 */

public class SP0_a {
	static List<Integer> shift = new ArrayList<>();
	public static List<Integer> naive(char[] T, char[] P)
	{
		boolean flag = true ;
		int m = P.length;
		int n = T.length;
		for (int s=0;s<n-m;s++)
		{
			int count = 0;
			for (int i =0;i<m;i++){
			if (P[i]==T[s+i])
				count ++ ;
			}
			if (count == m)
			{
				System.out.println("Valid Shift :"+s);
				shift.add(s);
			}
				
		//	for (int i=1)if (P.equals(T.))
		//	if (P.substring(1,m ).equals(T.substring(s+1, s+m)));
				
				
		}
		return shift;
		
	}
	
	public static List<Integer> KMP(char[] T, char[] P, int[] pie)
	{
		int q =0;
		int m = P.length;
		int n = T.length;
		//System.out.println(n+" "+m);
		for (int i=0;i<n;i++)
		{
			
			while (q>0 && P[q] != T[i])
			{
				q = pie[q];
				
			}
			System.out.println("Before:"+q+" "+i+" "+m);
			if (P[q]==T[i])
				q++;
			if (q==m)
			{
				int s = i-(m-1);
				shift.add(s);
				q--;

			}
			System.out.println("After :"+q+" "+i+" "+m);
			
		}
		return shift;
	}
	public static int[] pieCalc(char []p){
		int pie[]= new int[p.length];
		pie[0]=0;
		int k=0;
		for(int i=1;i <p.length;i++){
			while(k>0 && p[k]!=p[i]){
				k=pie[k];
			}
			if(p[k]==p[i]){
				k++;
			}
			System.out.println(k);
			pie[i]=k;
		}
		return pie;
	}
	public static void rabinKarp(String T,String P){
		int n=T.length();
		int m=P.length();
		for(int i=0;i<=(n-m);i++){
			if(T.substring(i,i+m).hashCode()!=P.hashCode()){
			}
			else
			{
				System.out.println("Valid Shift :"+i);
				naive(T.substring(i,i+m).toCharArray(), P.toCharArray());
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner in= new Scanner(System.in);
		String s2 =in.nextLine() ;//"ababababbababab";
		String s1 = in.nextLine(); //"ab";
		Timer time = new Timer();
		char[] P =s1.toCharArray();
		char[] T = s2.toCharArray();
		time.start();
		naive(T,P);
		time.end();
		System.out.println("Naive:"+time);
		time.start();
		rabinKarp(s2, s1);
		time.end();
		System.out.println("Rabi Karp:"+time);
		System.out.println(shift);
		
	}
}
