
public class DuplicateSubsequence {
	
	
	    static Node head = null;
	    static Node end = null;
	    public static String smallestSubsequence(String text) {
	        int len = text.length(), index=0, currindex=0;
	        Node [] chars = new Node[26];
	        String result = "";
	        char c = ' ';
	        Node node, currnode;
	        for(index=0;index<len;index++) {
	            c = text.charAt(index);
	            node = chars[c-'a'];
	            if(node == null) {
	                node = new Node(c);
	                if(head == null) {
	                    head = node;
	                    end = node;
	                } else {
	                    end.next = node;
	                    node.prev = end;
	                    end = node;
	                }
	            } else {
	                if(node != end) {
	                    if((node.next.c - 'a') <  (c-'a')) {
	                        if(head == node) {
	                            head = node.next;
	                        } else {
	                            node.prev.next = node.next;
	                            node.next.prev = node.prev;
	                        }
	                        
	                        end.next = node;
	                        node.prev = end;
	                        node.next = null;
	                        end = node;
	                    }
	                }
	            }
	            chars[c-'a'] = node;
	        }
	        
	        node = head;
	        while(node != null) {
	            result = result + ;
	            node = node.next;
	        }
	        return result;
	    }
	    
	    public static void main(String args[]) {
	    	System.out.println(smallestSubsequence("cdadabcc"));
	    }
	}

//	class Node{
//	    char c;
//	    Node next;
//	    Node prev;
//	    
//	    Node(char c) {
//	        this.c = c;
//	        this.next = null;
//	        this.prev = null;
//	    }
//	}


