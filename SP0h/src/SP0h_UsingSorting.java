import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * @author G32:Himanshu Parashar
 * 			   Satwant Singh	
 *	Class created to implement finding the most frequently appearing element using Sorting
 *	and then finding the element in O(nlogn) 
 */
public class SP0h_UsingSorting {
	
	public static int mostFrequent(int[] arr){
		Arrays.sort(arr);
		int count1=0;
		int count2=0;
		int frequent=0;
		int tmp=arr[0];
		for(int i=0;i<arr.length;i++){
			if(arr[i]==tmp){
				count1++;
			}
			else {
					if(count1>=count2){
						count2=count1;
						frequent=tmp;
					}
					tmp=arr[i];
					count1=0;
					i--;
				}
		}
		return frequent;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[sc.nextInt()];
		sc.close();
        for (int i =0;i<arr.length;i++)
        {
                      arr[i] = i+1;                                   
        }
        Random rnd = new Random();
        int random=rnd.nextInt(arr.length);
        for (int i =0;i<rnd.nextInt(arr.length);i++)
        {
                      arr[i] = random ;
        }
        Shuffle.shuffle(arr, 0, arr.length-1);
        for(int i=0;i<25;i++)	//prints first 20 values of the array
        	System.out.print(" "+arr[i]);
        System.out.println();
		Timer time = new Timer();
		time.start();
		int most=mostFrequent(arr);
		time.end();
		System.out.println("Most frequent element:"+most);
		System.out.println(time);
	}
	
}
/**
 * Sample Input:20 9 14 24 25 17 15 5 20 13 20 4 8 6 18 11 7 19 10 12 20 23 16 21 22
 * 		  Output:Most frequent element:20	 
 */
