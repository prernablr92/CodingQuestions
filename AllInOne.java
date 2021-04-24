import java.util.*;
class AllInOne {
    
    /** Initialize your data structure here. */
    static Map<String, ListNode> keymap;
    static Map<Integer, ListNode> valuemap;
    static ListNode head;
    static ListNode end;
    public AllInOne() {
        keymap = new HashMap<String, ListNode>();
        valuemap = new HashMap<Integer, ListNode>();
        head=null;
        end=null;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public static void inc(String key) {
        if(keymap.containsKey(key)) {
            ListNode node = keymap.get(key);
            ListNode nextnode;
            if(valuemap.containsKey(node.value+1)) {
                nextnode = valuemap.get(node.value+1);
                nextnode.keys.add(key);
            } else {
                nextnode = new ListNode(node.value+1);
                updateListInc(nextnode, node);
                nextnode.keys.add(key);
                valuemap.put(node.value+1, nextnode);
            }
            keymap.put(key, nextnode);
            node.keys.remove(key);
            if(node.keys.size() == 0) {
            	valuemap.remove(node.value);
                deleteNode(node);
            }
        } else {
            ListNode node;
            if(valuemap.containsKey(1)) {
            	node = valuemap.get(1);
            	node.keys.add(key);
            } else {
            	node = new ListNode(1);
            	node.keys.add(key);
            	valuemap.put(1, node);
            }

            keymap.put(key, node);
            
            if(head==null) {
                head=node;
                end=node;
            } else if(head != node){
                node.next = head;
                head.prev=node;
                head=node;
            }
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public static void dec(String key) {
        if(keymap.containsKey(key)) {
            ListNode node = keymap.get(key);
            if(node.value != 1) {
                ListNode decnode;
                if(valuemap.containsKey(node.value-1)) {
                    decnode= valuemap.get(node.value -1);
                    decnode.keys.add(key);
                    
                } else {
                    //insert dec node
                    decnode = new ListNode(node.value-1);
                    decnode.keys.add(key);
                    updateListDec(decnode, node);
                    valuemap.put(node.value-1, decnode);
                }
                keymap.put(key, decnode);
            } else {
            	 keymap.remove(key);
            }
            node.keys.remove(key);
           

            if(node.keys.size() == 0) {
                deleteNode(node);
                valuemap.remove(node.value);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public static String getMaxKey() {
        if(end != null) {
            return end.keys.iterator().next();
        }
        return "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public static String getMinKey() {
        if(head != null) {
            return head.keys.iterator().next();
        }
        return "";
    }
    
    private static void deleteNode(ListNode node) {
        if(node.prev != null) {
            node.prev.next = node.next;
        }
        if(node.next != null) {
            node.next.prev = node.prev;
        }
        if(head == node) {
            head = node.next;
        }
        if(end ==node) {
            end = node.prev;
        }
    }
    
    private static void updateListInc(ListNode curr, ListNode node) {
        curr.next = node.next;
        if(node.next != null) {
            node.next.prev = curr;
        }
        curr.prev = node;
        node.next = curr;
        if(end == node) {
            end = curr;
        }
    }
    
    private static void updateListDec(ListNode curr, ListNode node) {
        curr.prev = node.prev;
        if(node.prev != null) {
            node.prev.next = curr;
        }
        curr.next = node;
        node.prev = curr;
        if(head == node) {
            head = curr;
        }
    }
    
    public static void main(String args[]) {
    	AllInOne obj = new AllInOne();
//    	["AllOne","inc","inc","inc","inc","inc","dec","dec","getMaxKey","getMinKey"]
//    			[[],["a"],["b"],["b"],["b"],["b"],["b"],["b"],[],[]]
    	obj.inc("a");
    	obj.inc("b");
    	obj.inc("b");
    	obj.inc("b");
    	obj.inc("b");
    	obj.dec("b");
    	obj.dec("b");
    	System.out.println(obj.getMaxKey());
    	System.out.println(obj.getMinKey());
//    	obj.inc("leet");
//    	obj.inc("code");
//    	obj.inc("leet");
//    	obj.dec("hello");
//    	obj.inc("leet");
//    	obj.inc("code");
//    	obj.inc("code");
//    	System.out.println(obj.getMaxKey());
//    	obj.dec("goodbye");
//    	System.out.println(obj.getMaxKey());
//    	System.out.println(obj.getMinKey());
    	
    }
}
//
//class ListNode {
//    int value;
//    Set<String> keys;
//    ListNode prev;
//    ListNode next;
//    
//    ListNode(int value) {
//        this.value = value;
//        this.keys = new HashSet<String>();
//    }
//}


/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */