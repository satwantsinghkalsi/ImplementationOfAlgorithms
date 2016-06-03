import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
/**
* 
* @author G32:Himanshu Parashar
* 			   Satwant Singh	
*	Class created to implement finding the most frequently appearing element using HashMap
*	
*/
public class SP0h_Using_HashMap {
	
	public static int mostFrequent(int[] arr){
		HashMap<Integer, Integer> map= new HashMap<>();
		int frequency=0;
		int frequent=0;
		for(int i=0;i<arr.length;i++){
			if(map.containsKey(arr[i])){
				int value=map.get(arr[i]);
				value++;
				if(value>frequency){
					frequent=arr[i];
					frequency=value;
				}
				map.put(arr[i],value );
			}
			else
				map.put(arr[i], 1);
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
        for(int i=0;i<25;i++)
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
 * Sample Input:21 12 7 19 3 3 25 3 13 23 14 9 22 17 18 6 10 3 16 8 20 15 24 3 11
 * 		  Output:Most frequent element:3	 
 */