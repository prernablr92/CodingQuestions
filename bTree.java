import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class bTree {
	Node root;
//	static public class Node {
//        Node left;
//        Node right;
//        int val;
//
//        Node () {}
//        Node (int val){
//            this.val=val;
//        }
//    }

    public void insert(Node currnode, int val){
    	if(currnode == null) {
    		root = new Node(val);
    		return;
    	} 
    	if(val <= currnode.val) {
    		if(currnode.left == null) {
    			currnode.left = new Node(val);
    		} else {
    			insert(currnode.left, val);
    		}
    		
    	} else {
    		if(currnode.right == null) {
    			currnode.right = new Node(val);
    		} else {
    			insert(currnode.right, val);
    		}
    	}
    }

    public void displayTree(Node root){
   
        if (root.left != null){
            displayTree(root.left);
        }
        System.out.print(root.val + " - "); 
        if (root.right != null){
            displayTree(root.right);
        }
    }

    public static void main(String[] args) {
        String str = "a 123\nb 234\nd 987\n";
        String names[] = str.split("\\n");
        String index = search(names, "b");
        if(index==null) {
        	System.out.println("name not present");
        } else {
        	System.out.println(index);
        }
        Node root = new Node(5);
        //root.left = new Node(3);
        root.right = new Node(7);
        //root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(9);
        
       
        List<String> result = levelorderTraversal(root);
        System.out.println(result.toString());
        
    }
    
    
    private static List<String> levelorderTraversal(Node root) {
    	Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        Node node;
        int count=1;
        int levelnode=1, currlevelnode=0;
        List<String> result = new ArrayList<String>();
        while(count>0) {  //count represents number of non null nodes in queue
            node = queue.poll();
            if(levelnode == 0) { //if we have reached to next level
               levelnode=currlevelnode;
               currlevelnode = 0;
            }
            if(node==null) { //if parent node is null
                result.add(null);
                queue.add(null); //add its left child as null
                queue.add(null); //add its right child as null
            } else {
                count--;  //since we have popped out non null node decrement count
                result.add(Integer.toString(node.val));
                if(node.left != null) { //increment count if any of the left / right child is non null
                    count++;
                }
                if(node.right != null) { 
                    count++;
                }
                queue.add(node.left); //add left child of parent node
                queue.add(node.right); // add right child of parent node
            }
            currlevelnode+=2;
            levelnode--;
        }
        while(levelnode>0) { //if at last level still nodes are left to be added
           result.add(null);
           levelnode--;
        }
        return result;
    }
    private static String search(String []names, String value) {
    	int start = 0, end = names.length, mid=0;
    	while(start<=end) {
    		mid = (start+end)/2;
    		if(names[mid].split(" ")[0].equals(value)) {
    			return names[mid].split(" ")[1];
    		} else if(names[mid].split(" ")[0].compareTo(value) > 0) {
    			start = mid+1;
    		} else {
    			end=mid-1;
    		}
    	}
    	return  null;
    }
}