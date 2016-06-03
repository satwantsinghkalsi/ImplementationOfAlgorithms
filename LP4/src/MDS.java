import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Himanshu Parashar
 * 			Satwant Singh
 * Class implementing multidimensional search
 */
public class MDS {
	public int smeSmeCount=1;
	 class priceDesc{
		 double price;
		 long[] descrption ;
		 
		 priceDesc(double prc, long[] desc)
		 {
			 this.price = prc;
			 this.descrption = desc;
		 }
	 }
	 class idCount{
		 long id;
		 int count;
		 public idCount(long id,int count) {
			this.id=id;
			this.count=count;
		}
	 }
	 TreeMap<Long, priceDesc> storageItems;
	 TreeMap<Long,TreeMap<Double,Integer>> rfrncMap;
	 HashSet<Long> idL = new HashSet<>();
     HashSet<Long> hs = new HashSet<Long>();
     TreeMap<Long,Integer> newMap= new TreeMap<>(); 
     int count = 0 ;
	 public MDS(){
		 storageItems= new TreeMap<>();
		 rfrncMap= new TreeMap<>();
	 }
    int insert(long id, double price, long[] description, int size) {
	// Description of item is in description[0..size-1].
    	long desc[]=new long[size];
    	idCount idc = new idCount(id, 0);
    	for(int i=0;i<size;i++)
    		desc[i]=description[i];
    	int value=0;
   	 	priceDesc pD = new priceDesc(price, desc);
		   	 long sum=0;
		     for (int i =0 ; i<size;i++)
		     {
                 if (size >= 8)
                 {
                  sum+=description[i];
                 }
		     }
		     if(size>=8){
		    	 if(newMap.get(sum)==null){
		    		 newMap.put(sum, 1);
		    	 }
		    	 else{
		    		 int c=newMap.get(sum);
		    		 newMap.put(sum,++c);
		    	 }
		     }
		     if (find(id)!=0 && size >0)
             {
                   idc.count ++;
             }
		     if (!idL.contains(id) && hs.contains(sum))
		     {
               smeSmeCount++ ;
              }
		     if(idL.contains(id) && !hs.contains(sum))
		     {
               smeSmeCount-- ;
		     }
		     if(idL.contains(id) && hs.contains(sum))
		     {
	    	   if(idc.count ==0)
                   smeSmeCount= smeSmeCount+2;
               else
            	   smeSmeCount = smeSmeCount + idc.count;
                   //count++ ;
		     }
		     if (size >=8){
               idL.add(id);
               hs.add(sum);
		     }
    	if(find(id)==0){
    		storageItems.put(id, pD);
    		
    		for(int i=0;i<size;i++){
    			if(rfrncMap.get(desc[i])==null){
    				TreeMap<Double,Integer> tmp= new TreeMap<>();
    				tmp.put(price, 1);
    				rfrncMap.put(desc[i], tmp);
    			}
    			else{
    				TreeMap<Double,Integer> tmp= new TreeMap<>();
    				tmp=rfrncMap.get(desc[i]);
    				if(tmp.get(price)==null){
    					tmp.put(price, 1);
    				}
    				else{
    					value=tmp.get(price);
    					tmp.put(price, ++value);
    				}
    				rfrncMap.put(desc[i], tmp);
    			}
    			
    		}
    	
    		return 1;
    	}
    	else{
    		priceDesc oldpD=storageItems.get(id);
    		storageItems.put(id, pD);
    		TreeMap<Double,Integer> tmp= new TreeMap<>();
    		for(int i=0;i<size;i++){
    			if(rfrncMap.get(desc[i])==null){
    				tmp.put(price, 1);
    			}
    			else{
    				tmp=rfrncMap.get(desc[i]);
    				if(tmp.get(oldpD.price)==null){
        				tmp.put(price, 1);
        			}
        			else{
        				if(oldpD.price!=price && price>0){
        					tmp.put(price, 1);
        				}
        				else{
        					value=tmp.get(oldpD.price);
        					tmp.put(oldpD.price, --value);
        				}
        					
        			}
    			}
    			
    			rfrncMap.put(desc[i],tmp);
    		}
    	}
    	return 0;
    }

    double find(long id) {
    	double rtrnValue=0;
    	priceDesc pd = storageItems.get(id);
    	if (pd == null)
    		rtrnValue=0;
    	else
    		rtrnValue=pd.price;
    	return rtrnValue;
    	
    }

    long delete(long id) {
    	long rtrnValue=0;
    	int count=0;
    	priceDesc pD= storageItems.get(id);
    	System.out.println(pD.price);
    	storageItems.remove(id);
    	long[]desc= pD.descrption;
    	for(int i=0;i<desc.length;i++){
    		rtrnValue+=desc[i];
    		TreeMap<Double,Integer> tmp = new TreeMap<>();
    		tmp=rfrncMap.get(desc[i]);
    		if(tmp!=null){
        	count=tmp.get(pD.price);
    		if(count==1){
    			tmp.remove(pD.price);
    			rfrncMap.put(desc[i], tmp);
    		}
    		else{
    			tmp.put(pD.price, --count);
    			rfrncMap.put(desc[i], tmp);
    		}
    		}
    	}
    	return rtrnValue;
	 }

    double findMinPrice(long des) {
    	double rtrnValue=0;
    	TreeMap<Double,Integer> tmp = new TreeMap<>();
    	tmp=rfrncMap.get(des);
    	rtrnValue=tmp.firstKey();
	return rtrnValue;
    }

    double findMaxPrice(long des) {
    	double rtrnValue=0;
    	TreeMap<Double,Integer> tmp = new TreeMap<>();
    	tmp=rfrncMap.get(des);
    	rtrnValue=tmp.lastKey();
	return rtrnValue;
    }

    int findPriceRange(long des, double lowPrice, double highPrice) {
    	int rtrnValue=0;
    	TreeMap<Double,Integer> tmp = new TreeMap<>();
    	tmp=rfrncMap.get(des);
    	for(Map.Entry<Double,Integer> entry : tmp.entrySet()) {
    		  Double key = entry.getKey();
    		  if(key>=lowPrice && key<=highPrice){
      			rtrnValue++;
    		  }
    		}
	return rtrnValue;
    }

    double priceHike(long minid, long maxid, double rate) {
    	double rtrnValue=0;
    	double tmpPrice=0;
    	double oldPrice=0;
    	TreeMap<Long, priceDesc> tmpStorage = new TreeMap<>();
    	for(Map.Entry<Long,priceDesc> entry : storageItems.entrySet()) {
    		  Long key = entry.getKey();
    		  priceDesc value = entry.getValue();
    		  if(key>=minid && key<=maxid){
    			  oldPrice=value.price;
    			  tmpPrice = ((rate/100))*value.price;
    			  rtrnValue+=tmpPrice;
    			  value.price+=tmpPrice;
    			  tmpStorage.put(key, value);
    			  
    			  long[]desc= value.descrption;
    			 	for(int i=0;i<desc.length;i++){
    		    		TreeMap<Double,Integer> tmp = new TreeMap<>();
    		    		int count=0;
    		    		tmp=rfrncMap.get(desc[i]);
    		    		if(tmp.get(oldPrice)!=null){
    		    		count=tmp.get(oldPrice);
    		    		if(--count==0)
    		    			tmp.remove(oldPrice);
    		    		}
    		    		if(tmp.get(value.price)==null){
    		    			tmp.put(value.price,1);
    		    		}
    		    		else{
    		    			count=tmp.get(value.price);
    		    			tmp.put(value.price, ++count);
    		    		}
    		    		rfrncMap.put(desc[i], tmp);
    		    	}
    			  
    		  }
    		  rtrnValue+=value.price-oldPrice;
    		}
    	storageItems.putAll(tmpStorage);
    	return rtrnValue;
    }

    int range(double lowPrice, double highPrice) {
    	int count =0 ;
    	for(Map.Entry<Long,priceDesc> entry : storageItems.entrySet()) {
    		  Long key = entry.getKey();
    		  priceDesc value = entry.getValue();
    		  if ( value.price >= lowPrice && value.price <= highPrice)
              {
                   count++ ;
              }
    		}
    	return count ;
	  }

    int samesame() {
    	return smeSmeCount;
	}
}