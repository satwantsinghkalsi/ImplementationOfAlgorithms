import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author  Satwant Singh,Himanshu Parashar
 *	Class created to handle arithmetic of large numbers of arbitrary size
 */
public class BigNumbers {
	public static final int B=10;
	ArrayList<Long> longNumber;
	boolean isNegative =false;
	/**
	 * Constructor for class; takes a string s as parameter, that stores a number in decimal
	 * @param s
	 */
		public BigNumbers(String s) {
		longNumber = new ArrayList<>();
		for(int i=s.length()-1;i>=0;i--){
			longNumber.add(Long.parseLong(s.substring(i,i+1)));
		}
	}
	/**
	 * Constructor for class; takes a long as parameter, that stores a number in decimal
	 * @param num
	 */
	public BigNumbers(long num){
		longNumber = new ArrayList<>();
		while(num!=0){
			longNumber.add(num%10);
			num=num/10;
		}
	}
	/**
	 * Overridden  to convert class object into its equivalent string (in decimal).
	 */
	public String toString(){
		StringBuilder s = new StringBuilder();
		if(isNegative)
			s.append("-");
		for(int i=longNumber.size()-1;i>=0;i--){
			s.append(longNumber.get(i));
		}
		String s1=new String(s);
		return s1;
	}
	/**
	 * Method to return size of the BigNumbers
	 * @return
	 */
	public int size(){
		return longNumber.size();
	}
	/**
	 * Utility method to add the value at the end of BigNumbers ArrayList
	 * @param num
	 */
	public void addIndex(long num){
		longNumber.add(num);
	}
	/**
	 * Utility method to get the value from given index in BigNumbers ArrayList
	 * @param index
	 * @return: long value at the index
	 */
	public long getIndex(int index){
		return longNumber.get(index);
	}
	/**
	 * Utility method to put the value at a given index in BigNumbers ArrayList
	 * @param index
	 * @param element
	 */
	public void putIndex(int index,long element){
		longNumber.set(index, element);
	}
	/**
	 * Utility method to remove the value from given index in BigNumbers ArrayList.
	 * @param index
	 */
	public void remove(int index){
		longNumber.remove(index);
	}
	/**
	 * Utility method to perform subList of BigNumbers ArrayList
	 * @param a:BigNumbers
	 * @param fromIndex
	 * @param toIndex
	 * @return:BigNumbers: of size toIndex-fromIndex+1
	 */
	public BigNumbers subList(BigNumbers a,int fromIndex, int toIndex){
		BigNumbers tmp= new BigNumbers("");
		for(int i=fromIndex;i<toIndex;i++)
			tmp.addIndex(a.getIndex(i));
		return tmp;
	}
	/**
	 * Method to implement addition of two numbers of type BigNumbers
	 * @param a:BigNumbers
	 * @param b:BigNumbers
	 * @return :sum of a & b:BigNumbers	
	 **/
	public static BigNumbers add(BigNumbers a, BigNumbers b){
		BigNumbers x=a;
		BigNumbers y=b;
		int sizeOfNum1=x.size();
		int sizeOfNum2=y.size();
		long carry=0;
		if(sizeOfNum1==sizeOfNum2){
			for(int i=0;i<sizeOfNum1;i++){
				long sum=y.getIndex(i)+x.getIndex(i)+carry;
				carry=0;
				if(sum>=B){
					carry=sum/B;
				}
				y.putIndex(i, sum%B);
			}
			if(carry!=0)
				y.addIndex(carry);
			return y;
		}
		else if(sizeOfNum1>sizeOfNum2){
			int i;
			for(i=0;i<sizeOfNum2;i++){
				long sum=y.getIndex(i)+x.getIndex(i)+carry;
				carry=0;
				if(sum>=B){
					carry=sum/B;
				}
				x.putIndex(i, sum%B);
			}
			while(i<sizeOfNum1 && carry!=0){
				long sum=x.getIndex(i)+carry;
				carry=0;
				if(sum>=10){
					carry=sum/B;
				}
				x.putIndex(i, sum%B);
				i++;
			}
			if(carry!=0)
				x.addIndex(carry);
			return x;
		}
		else{
			int i;
			for(i=0;i<sizeOfNum1;i++){
				long sum=y.getIndex(i)+x.getIndex(i)+carry;
				carry=0;
				if(sum>=B){
					carry=sum/10;
				}
				y.putIndex(i, sum%B);
			}
			while(i<sizeOfNum2 && carry!=0){
				long sum=y.getIndex(i)+carry;
				carry=0;
				if(sum>=B){
					carry=sum/B;
				}
				y.putIndex(i, sum%B);
				i++;
			}
			if(carry!=0)
				y.addIndex(carry);
			return y;
		}
	}
	/**
	 * Method to implement subtraction of BigNumbers 
	 * @param a BigNumbersz
	 * @param b BigNumbers
	 * @return a-b
	 */
	public static BigNumbers subtract(BigNumbers a, BigNumbers b){
		BigNumbers x=a;
		BigNumbers y=b;
		int sizeOfNum1 =x.size();
		int sizeOfNum2=y.size();
		long carry=0;
		long result=0;
		if(sizeOfNum1>sizeOfNum2){
			int i=0;
			for(i=0;i<sizeOfNum2;i++){
				x.putIndex(i, x.getIndex(i)-carry);
				if(x.getIndex(i)<y.getIndex(i)){
					carry=1;
					result=(B+x.getIndex(i))-y.getIndex(i);
					}
				else{
					result=x.getIndex(i)-y.getIndex(i);
					carry=0;
				}
				x.putIndex(i, result);
			}
			if(carry!=0 ){
				while(i<sizeOfNum1 && (x.getIndex(i)-carry)<0){
					x.putIndex(i, B+x.getIndex(i)-carry);
					i++;
				}
				x.putIndex(i, x.getIndex(i)-carry);
			}
			i=sizeOfNum1-1;
			while(x.getIndex(i)==0){
				x.remove(i);
				i--;
			}
			return x;
		}
		else if(sizeOfNum2>sizeOfNum1){
			int i=0;
			for(i=0;i<sizeOfNum1;i++){
				y.putIndex(i, y.getIndex(i)-carry);
				if(y.getIndex(i)<x.getIndex(i)){
					carry=1;
					result=(B+y.getIndex(i))-x.getIndex(i);
					}
				else{
					result=y.getIndex(i)-x.getIndex(i);
					carry=0;
				}
				y.putIndex(i, result);
			}
			if(carry!=0){
				while(i<sizeOfNum2 && (y.getIndex(i)-carry)<0){
					y.putIndex(i, B+y.getIndex(i)-carry);
					i++;
				}
				y.putIndex(i, y.getIndex(i)-carry);
			}
			i=sizeOfNum2-1;
			while(y.getIndex(i)==0){
				y.remove(i);
				i--;
			}
			y.isNegative=true;
			return y;
			
		}
		else{
			int i=0;
			for(i=0;i<sizeOfNum2;i++){
				x.putIndex(i, x.getIndex(i)-carry);
				if(x.getIndex(i)<y.getIndex(i)){
					carry=1;
					result=(B+x.getIndex(i))-y.getIndex(i);
					}
				else{
					result=x.getIndex(i)-y.getIndex(i);
					carry=0;
				}
				x.putIndex(i, result);
			}
			if(carry!=0){
				while(i<sizeOfNum2 && (y.getIndex(i)-carry)<0){
					x.putIndex(i, x.getIndex(i)-carry);
					i++;
				}
			}
			i=sizeOfNum2-1;
			while(x.getIndex(i)==0 && i>0){
				x.remove(i);
				i--;
			}
			return x;
		}
	}
	/**
	 * Utility method to perform base shift 
	 * @param x:BigNumbers
	 * @param y:long
	 * @return:BigNumbers:shifted to the left by length addition of y zeros. 
	 */
	private static BigNumbers multiplyTwoNumbers(BigNumbers x, long y){
		BigNumbers tmp=new BigNumbers("");
		int sizeOfNum1=x.size();
		for(int i=1;i<=y;i++){
			tmp.addIndex(B%10);
		}
		for(int i=0;i<sizeOfNum1;i++){
			tmp.addIndex(x.getIndex(i));
		}
		
		return tmp;
	}
	
	/**
	 * Method to implement multiplication of two BigNumbers 
	 * @param a:BigNumbers
	 * @param b:BigNumbers
	 * @return product of a & b
	 */
	public static BigNumbers product(BigNumbers a, BigNumbers b){
		
		int sizeOfNum1 = a.size();
		int sizeOfNum2 = b.size();
		int n = 0;
		if(sizeOfNum1<sizeOfNum2){
			n=sizeOfNum1;
		}
		else{
			n=sizeOfNum2;
		}
		if(sizeOfNum1==1 || sizeOfNum2==1){
			if(sizeOfNum1==1){
				return multiplyBaseCase(a,b);
			}
			else{
				return multiplyBaseCase(b,a);
			}
		}
			int l=0;
			l=n/2;
		
			
		BigNumbers aRight = a.subList(a, 0, l);
		BigNumbers aLeft = a.subList(a, l, sizeOfNum1);
		BigNumbers bRight = b.subList(b, 0, l);
		BigNumbers bLeft = b.subList(b, l, sizeOfNum2);
		
		BigNumbers b1 = product(aLeft,bLeft);
		BigNumbers b2 = product(aLeft, bRight);
		BigNumbers b3 = product(aRight, bLeft);
		BigNumbers b4 = product(aRight, bRight);
		
		long BaseN = 2*l;
		long BaseN2 =l;
		BigNumbers x1= multiplyTwoNumbers(b1, BaseN);
		BigNumbers x2= multiplyTwoNumbers(add(b2,b3),BaseN2 );
		return	add( add(x1,x2),b4);
		
		
	
	}
	/**
	 * Utility function to multiply base case for DAC multiplication
	 * @param x:BigNumbers(size =1)
	 * @param y:Bignumbers(size>=1)
	 * @return BigNumbers:produxt of x & y
	 */
	private static BigNumbers multiplyBaseCase(BigNumbers x, BigNumbers y){
		long carry=0;
		BigNumbers temp= new BigNumbers("");
		for(int i=0;i<y.size();i++){
				long pr=(x.getIndex(0)*y.getIndex(i)+carry)%10;
				temp.addIndex(pr);
				carry=(x.getIndex(0)*y.getIndex(i)+carry)/10;
		}
		if(carry>0)
			temp.addIndex(carry);
		return temp;
	}
	
	/**
	 * Method to implement the power of a BigNumbers
	 * @param b:BigNumbers
	 * @param n:long(n>=0)
	 * @return b^n
	 */
	public static BigNumbers power(BigNumbers b , long n)
	{
		if (n == 0)
		{
		 b.putIndex(0, 1);
		 return b;
		}
		else if (n ==1)
			return b;
		else
		{			
				BigNumbers res = power(product(b, b),n/2);
				if(n%2 == 0)
					return res;
				else
				{							
					return (product(res,b));
				}						
		}
	}
	/**
	 * Prints the list in the form
	 * The base + ":" + elements of the list, separated by spaces.
	 */
	public void printList(){
		System.out.print(B+" :");
		for(long i:longNumber){
			System.out.print(" "+i);
		}
		System.out.println();
	}
	public static void main(String args[]){
			BigNumbers a = new BigNumbers("1234567890123456789012345678901234567890");
			BigNumbers b = new BigNumbers(999);
			System.out.println("a = "+a);
			System.out.println("b = " + b);
			BigNumbers c= add(a, b);
			System.out.println("a+b = " +c);
			BigNumbers d= new BigNumbers("37482748178273981729837918273");
			BigNumbers e= new BigNumbers("2473873874823784723477");
			System.out.println("d-e = " +subtract(d, e));
			BigNumbers f= new BigNumbers("872837218728731");
			BigNumbers h= new BigNumbers("73262367126");
			System.out.println("f*h = " +product(f,h));
			BigNumbers two = new BigNumbers(2);
			BigNumbers g = BigNumbers.power(two, 1025);
			System.out.println("2^1025 = " + g);
			System.out.println("Internal representation:");
			g.printList();
	
	}
	
}
/**
 *Sample Input:
 *Sample Output:
 */
