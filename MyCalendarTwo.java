class MyCalendarTwo {
	Node root;
    public MyCalendarTwo() {
        root = null;
    }
    
    public boolean book(int start, int end) {
        if(search(root, start, end) < 2) {
            root = add(root, start, end);
            return true;
        }
        return false;
    }
    
    private Node add(Node root, int start, int end) {
        if(root == null) {
            return new Node(start, end, 1);
        }
        
        if(root.end <=start) {
            root.right = add(root.right, start, end);
        } else if(root.start>=end) {
            root.left = add(root.left, start, end);
        } else {
            if(root.start==start && root.end ==end) {
                root.count++;
            } else if(root.start <= start && root.end >=end) {
                if(root.start != start) {
                    Node left = new Node(root.start, start, root.count);
                    left.left = root.left;
                    root.left = left;
                }
                if(root.end != end) {
                    Node right = new Node(end, root.end, root.count);
                    right.right = root.right;
                    root.right = right;
                }
                root.start = start;
                root.end = end;
                root.count++;
            } else if(start < root.start && end > root.end) {
                root.count++;
                root.left = add(root.left, start, root.start);
                root.right = add(root.right, root.end, end);
            } else if(start < root.start){
                root.left = add(root.left, start, root.start);
                if(end != root.end) {
                    Node right = new Node(end, root.end, root.count);
                    right.right = root.right;
                    root.right = right;
                }
                root.end = end;
                root.count++;
            } else {
                if(start != root.start) {
                    Node left = new Node(root.start, start, root.count);
                    left.left = root.left;
                    root.left = left;
                }
                root.right = add(root.right, root.end, end);
                root.start = start;
                root.count++;
            }
        }
        return root;
        
    }
    
    private int search(Node root, int start, int end) {
        if(root == null) {
            return 0;
        } 
        
        if(root.end <= start) {
            return search(root.right, start, end);
        } else if(root.start>=end) {
            return search(root.left, start, end);
        } else {
            if(root.start <= start && root.end >= end) {
                return root.count;
            } else if(start < root.start && end > root.end) {
                int left = search(root.left, start, root.start);
                int right = search(root.right, root.end, end);
                return Math.max(root.count, Math.max(left, right));
            } else if(start < root.start) {
                return Math.max(root.count, search(root.left, start, root.start));
            } else {
                return Math.max(root.count, search(root.right, root.end, end));
            }
        }
    }
    
    public static void main(String args[]) {
    	MyCalendarTwo my = new MyCalendarTwo();
    	//[[],[28,46],[9,21],[21,39],[37,48],[38,50],[22,39],[45,50]]
    	//
    	//["MyCalendarTwo","book","book","book","book","book","book"]
    	//		[[],[33,41],[38,43],[49,50],[39,44],[28,36],[28,37]]
    	System.out.println(my.book(33,41));
    	System.out.println(my.book(38,43));
    	System.out.println(my.book(49,50));
    	System.out.println(my.book(39,44));
    	System.out.println(my.book(28,36));
    	System.out.println(my.book(28,37));
    	//System.out.println(my.book(45,50));
    	
    }
}

//class Node{
//    int start;
//    int end;
//    Node left;
//    Node right;
//    int count;
//    
//    Node(int start, int end, int count) {
//        this.start = start;
//        this.end = end;
//        this.count = count;
//        left = null;
//        right = null;
//    }
//}
/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */