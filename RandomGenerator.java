import java.util.*;

public class RandomGenerator {
	
	
	    
	static Map<Integer, List<Integer>> map;
    static List<Integer> list;
    static int currindex;
    static Random random;
	    /** Initialize your data structure here. */
	    public RandomGenerator() {
	    	 map = new HashMap<Integer, List<Integer>>();
	         list = new ArrayList<Integer>();
	         currindex=0;
	         random = new Random();
	    }
	    
	    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	    public static boolean insert(int val) {
	    	Set<Integer> indexlist = map.get(val);
	        boolean present = false;
	        if(indexlist == null) {
	            present = true;
	            indexlist = new HashSet<Integer>();
	        }
	        indexlist.add(currindex);
	        map.put(val,  indexlist);
	        list.add(currindex, val);
	        currindex++;
	        return present;
	    }
	    
	    /** Removes a value from the set. Returns true if the set contained the specified element. */
	    public static boolean remove(int val) {
	    	if(map.containsKey(val)) {
	            Set<Integer> currindexlist = map.get(val);
	            int index = currindexlist.iterator.next();
	            currindexlist.remove(index);
	            if(currindexlist.size() == 0) {
	                map.remove(val);
	            }
	            int lastelement = list.get(currindex-1);
	            if(index != currindex-1) {
	                list.set(index, lastelement);
	                List<Integer> maplist = map.get(lastelement);
	                maplist.remove(currindex-1);
                    maplist.add(index);
	            }
	            currindex--;
	            return true;
	        }
	        return false;
	    }
	    
	    /** Get a random element from the set. */
	    public static int getRandom() {
	    	int index = random.nextInt(currindex);
            return list.get(index);
	    }
	    
	    public static void main(String args[]) {
	    	RandomGenerator gen = new RandomGenerator();
	    	insert(1);
	    	insert(1);
	    	insert(2);
	    	insert(1);
	    	insert(2);
	    	insert(2);
	    	remove(1);
	    	remove(2);
	    	remove(2);
	    	remove(2);
	    	
	    	getRandom();
	    	
	    	getRandom();
	    	getRandom();
	    	getRandom();
	    	
	    	Set set = new HashSet<Integer>();
	    	set.add(1);
	    	set.remove(1);
	    	set.iterator().next()
	    	
	    	
	    	//["RandomizedCollection","insert","insert","insert","insert","insert","insert","remove","remove","remove","remove","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
	//    	[[],[1],[1],[2],[1],[2],[2],[1],[2],[2],[2],[],[],[],[],[],[],[],[],[],[]]
	    }
}


//class Node {
//    int data;
//    Node next;
//    Node prev;
//    
//    Node(int data) {
//        this.data = data;
//        this.prev = null;
//        this.next=null;
//    }
//
//}


