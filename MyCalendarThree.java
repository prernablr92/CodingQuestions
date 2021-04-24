class MyCalendarThree {

    Node root;
    int max;
    public MyCalendarThree() {
        root = null;
        max =1 ;
    }
    
    public int book(int start, int end) {
        root = insert(root, start, end);
        return max;
    }
    
    private Node insert(Node root, int start, int end) {
        
        if(root == null) {
            return new Node(start, end, 1);
        }
        if(root.end <= start) {
            root.right = insert(root.right, start, end);
        } else if(root.start>=end) {
            root.left = insert(root.left, start, end);
        } else {
            if(root.start == start && root.end == end) {
                root.count++;
            } else {
                //root interval biiger than curr interval
                if(root.start<=start && root.end>=end) {
                    Node node;
                    if(root.start != start) {
                        node = new Node(root.start, start, root.count);
                        node.left = root.left;
                        root.left = node;
                    }
                    if(root.end != end) {
                        node = new Node(end, root.end, root.count);
                        node.right = root.right;
                        root.right = node;
                    }
                    
                    root.start = start;
                    root.end = end;
                    root.count++;
                    
                    
                } else if(root.start >=start && root.end <= end){
                    //root interval smaller than curr interval
                    root.count++;
                    if(root.start != start) {
                        root.left = insert(root.left, start, root.start);
                    }
                    if(root.end != end) {
                        root.right = insert(root.right, root.end, end);
                    }
                } else if(start < root.start && end <=root.end) {
                    root.left = insert(root.left,start, root.start);
                    if(end != root.end) {
                        Node node = new Node(end, root.end, root.count);
                        node.right = root.right;
                        root.right = node;
                    }
                    root.end = end;
                    root.count++;
                } else {
                    if(root.start != start) {
                        Node node = new Node(root.start, start, root.count);
                        node.left = root.left;
                        root.left = node;
                    }
                    root.right = insert(root.right, root.end, end);
                    root.start = start;
                    root.count++;
                    
                }
            }
             max = Math.max(max,root.count);
        }
        return root;
    }
    
    public static void main(String args[]) {
    	MyCalendarThree ny = new MyCalendarThree();
    	//["MyCalendarThree","book","book","book","book","book","book","book","book","book","book","book","book","book"]
    	//[[],[15,28],[14,31],[26,37],[24,43],[6,23],[6,20],[4,17],[13,31],[9,25],[4,20],[2,17],[26,38],[18,35]]
    	
    	ny.book(15,28);
    	ny.book(14,31);
    	ny.book(26,37);
    	ny.book(24,43);
    	ny.book(6,23);
    	ny.book(6,20);
    	ny.book(4,17);
    	ny.book(13,31);
    	ny.book(9,25);
    	ny.book(4,20);
    	ny.book(2,17);
    	ny.book(26,38);
    	ny.book(18,35);
    }
}

//class Node {
//    int start;
//    int end;
//    int count;
//    Node left;
//    Node right;
//    
//    Node(int start, int end, int count) {
//        this.start = start;
//        this.end = end;
//        this.left = null;
//        this.right = null;
//        this.count=count;
//    }
//    
//}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */