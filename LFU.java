import java.util.*;
class LFU {

    private CountNode headcount;
    private Map<Integer, Node> keyNodeMap;
    private Map<Integer, Integer> keyCountMap;
    private Map<Integer, Integer> keyvaluemap;
    private Map<Integer, CountNode> countnodemap;
    int curr;
    int capacity;
    public LFU(int capacity) {
        this.capacity = capacity;
        this.curr = 0;
        this.headcount=null;
        keyNodeMap = new HashMap<>();
        keyCountMap = new HashMap<>();
        keyvaluemap = new HashMap<>();
        countnodemap = new HashMap<>();
    }
    
    public int get(int key) {
        int  value = -1;
        if(this.capacity == 0) {
    		return -1;
    	}
        if(keyvaluemap.containsKey(key)) {
            value = keyvaluemap.get(key);
            update(key);
        }
        return value;
    }
    
    public void put(int key, int value) {
    	if(this.capacity == 0) {
    		return;
    	}
        if(keyvaluemap.containsKey(key)) {
            keyvaluemap.put(key, value);
            update(key);
        } else {
            if(this.curr == this.capacity) {
            	keyvaluemap.remove(headcount.head.val);
            	keyCountMap.remove(headcount.head.val);
            	keyNodeMap.remove(headcount.head.val);
            	
                if(headcount.head.fwd != null) {
                    headcount.head.fwd.bwd = null;
                    headcount.head = headcount.head.fwd;
                } else {
                	countnodemap.remove(headcount.count);
                    headcount = headcount.fwd;
                    if(headcount != null) {
                    	headcount.bwd = null;
                    }
                    
                }
                
                this.curr--;
            }
            this.curr++;
            int count=1;
            CountNode countnode;
            Node node = new Node(key);
            if(countnodemap.containsKey(count)) {
                countnode = countnodemap.get(count);
                countnode.end.fwd = node;
                node.bwd = countnode.end;
                countnode.end = node;
            } else {
                countnodemap.put(count, new CountNode(count));
                countnode = countnodemap.get(count);
                countnode.head = node;
                countnode.end = node;
                
                if(headcount == null) {
                	headcount = countnode;
                } else {
                	countnode.fwd = headcount;
                	headcount.bwd = countnode;
                	headcount = countnode;
                }
                
            }
            keyNodeMap.put(key, node);
            keyvaluemap.put(key, value);
            keyCountMap.put(key, count);
        }
    }
    
    private void update(int key) {
        int currcount = keyCountMap.get(key);
        CountNode currcountnode = countnodemap.get(currcount);
        Node currnode = keyNodeMap.get(key);
        
        deleteNode(currcountnode, currnode);
        
        currcount++;
        
        CountNode updatedcountnode;
        if(countnodemap.containsKey(currcount)) {
            updatedcountnode = countnodemap.get(currcount);
            updatedcountnode.end.fwd = currnode;
            currnode.bwd = updatedcountnode.end;
            updatedcountnode.end = currnode;
        } else {
           updatedcountnode = new CountNode(currcount);
           updatedcountnode.head =  currnode;
           updatedcountnode.end = currnode;
           
            updatedcountnode.fwd = currcountnode.fwd;
            currcountnode.fwd = updatedcountnode;
            updatedcountnode.bwd = currcountnode;
            countnodemap.put(currcount,updatedcountnode);
        }
        
        if(currcountnode.head == null) {
            countnodemap.remove(currcount-1);
            if(headcount == currcountnode) {
                headcount = currcountnode.fwd;
                headcount.bwd = null;
            } else {
            	if(currcountnode.bwd != null) {
                    currcountnode.bwd.fwd = currcountnode.fwd;
                }
                if(currcountnode.fwd != null) {
                    currcountnode.fwd.bwd = currcountnode.bwd;
                }
            }
            
        }
        keyCountMap.put(key, currcount);
        
    }
    
    private void deleteNode(CountNode currcountnode, Node currnode) {
        if(currcountnode.head==currnode) {
            currcountnode.head = currnode.fwd;
        }
        if(currcountnode.end==currnode) {
            currcountnode.end = currcountnode.end.bwd;
        }
        if(currnode.bwd != null) {
            currnode.bwd.fwd = currnode.fwd;
        }
        if(currnode.fwd != null) {
            currnode.fwd.bwd = currnode.bwd;
        }
        currnode.fwd=null;
        currnode.bwd=null;
    }
    
    public static void main(String args[]) {
    	LFU lfu = new LFU(10);
    	lfu.put(10, 13);
    	lfu.put(6,11);
    	lfu.put(10,5);
    	lfu.put(9,10);
    	lfu.get(13);  
    	lfu.put(2,19);
    	lfu.put(5,25);
    	lfu.put(9,22);
    	lfu.put(5, 5);
    	lfu.put(1, 30);
    	lfu.put(4,30);
    	lfu.put(9,3);
    	lfu.put(6, 14);
    	lfu.put(3, 1);// return 1
    	//lfu.put(3, 2);   // evicts key 2
    	lfu.get(3);
    	lfu.put(10, 11);
    	lfu.put(2, 14);
    	lfu.get(1); 
    	lfu.put(3, 27);
    	// return -1 (not found)
//    	lfu.get(3);      // return 3
    	//lfu.put(4, 3);   // evicts key 1.
    	//lfu.get(2); 
    	lfu.put(2, 12);
//    	lfu.put(2, 1);
//    	lfu.put(2, 1);
//    	lfu.put(2, 1);
//    	lfu.put(2, 1);
    	// return -1 (not found)
    	lfu.get(5); 
    	lfu.put(8,18);
    	lfu.put(1, 7);
    	lfu.get(6); 
    	lfu.put(8, 21);
    	lfu.put(4, 15);
    	lfu.put(11, 26);// return 3
    	//lfu.get(4);
    	lfu.put(8,17);
    	lfu.put(11,30);
    	lfu.get(12); 
    	lfu.put(4,29);
    	lfu.put(3,29);
    	lfu.put(10,28);
    	lfu.put(1,20);
    	lfu.put(11,13);
    	
    	
//    	["LFUCache","put","get","put","get","get"]
//    			[[1],[2,1],[2],[3,2],[2],[3]]
    	
    	
//    	["LFUCache","put","put","get","get","put","get","get","get"]
//    			[[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
    	
//    	["LFUCache","put","put","put","put","get","put","put","put","put","put","put","put","put","put","get","put","put","get","put","put","get","put",
//    	 "put","get","put","put","put","put","put","get","put","put","put","put","put"]
//    			[[10],[10,13],[6,11],[10,5],[9,10],[13],[2,19],[5,25],[9,22],[5,5],[1,30]
//    					,[4,30],[9,3],[6,14],[3,1],[3],[10,11],[2,14],[1],[3,27],[2,12],[5],[8,18],[1,7],
//    					[6],[8,21],[4,15],[11,26],[8,17],[11,30],[12],[4,29],[3,29],[10,28],[1,20],[11,13]]
    }
}

class CountNode {
    int  count;
    Node head;
    Node end;
    CountNode fwd;
    CountNode bwd;
    
    
    CountNode(int count) {
        this.count = count;
        head=null;
        end = null;
        fwd = null;
        bwd = null;
    }
}

//class Node {
//    int val;
//    Node fwd;
//    Node bwd;
//    
//    Node(int curr) {
//        this.val = curr;
//        this.fwd = null;
//        this.bwd = null;
//    }
//}


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */