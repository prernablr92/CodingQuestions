
import java.util.*;
class RangeModule {

    TreeNode root;
    public RangeModule() {
        root = null;
    }
    
    public void addRange(int left, int right) {
        root = add(root, left, right);
    }
    
    public boolean queryRange(int left, int right) {
        return query(root, left, right);
    }
    
    public void removeRange(int left, int right) {
        root = remove(root, left, right);
    }
    
    private TreeNode remove(TreeNode root, int start, int end) {
        if(root == null) {
            return null;
        } 
        if(end <=root.start) {
            root.left = remove(root.left, start, end);
        } else if(start >=root.end) {
            root.right = remove(root.right, start, end);
        } else {
            int presentstart = start, presentend = end;
            //if some part overlaps
            if(start<root.start && end > root.end) {
                root.left = remove(root.left, start, root.start);
                root.right = remove(root.right, root.end, end);
                presentstart = root.start;
                presentend = root.end;
            } else if(start <root.start && end <=root.end) {
                root.left = remove(root.left, start, root.start);
                presentstart = root.start;
                presentend = end;
            } else if(start > root.start && end > root.end) {
                root.right = remove(root.right, root.end, end);
                presentstart = start;
                presentend = root.end;
            }
            start = presentstart;
            end = presentend;
            if(root.start <= presentstart && root.end>=presentend) {
            //divide this root into two part
                if(root.start == start && root.end == end) {
                	
                    if(root.left == null) {
                        root = root.right;
                        return root;
                    } else {
                        //calculate max value in left subtree
                        TreeNode leftnode = root.left;
                        int max=0;
                        while(leftnode != null) {
                            max = leftnode.end;
                            leftnode = leftnode.right;
                        }
                        root.start = max;
                        root.end=max;
                        return root;
                    }
                } else {
                    if(root.start<start && root.end >end) {
                        TreeNode node = new TreeNode(root.start, start);
                        node.left = root.left;
                        TreeNode temp = root.right;
                        node.right = new TreeNode(end, root.end);
                        node.right.right = temp;
                        return node;
                    } else if(root.start==start) {
                        root.start = end;
                        return root;
                    } else {
                        root.end =start;
                        return root;
                    }
                }
            } else {
            	root.left = remove(root.left, start, root.start);
            	root.right = remove(root.right, root.end, end);
            	if(root.left == null) {
                    root = root.right;
                    return root;
                } else {
                    //calculate max value in left subtree
                    TreeNode leftnode = root.left;
                    int max=0;
                    while(leftnode != null) {
                        max = leftnode.end;
                        leftnode = leftnode.right;
                    }
                    root.start = max;
                    root.end=max;
                    return root;
                }
            }
        }
        return root;
    }
    
    private boolean query(TreeNode curr, int start, int end) {
        if(curr == null) {
        	return false;
        }
    	if(curr.start <=start && curr.end >=end) {
            return true;
        }
        if(curr.start >=end) {
            return query(curr.left, start, end);
        } else if(curr.end<=start) {
            return query(curr.right, start, end);
        } else {
            boolean currleft = true, currright = true;
            if(start < curr.start) {
                currleft = query(curr.left, start, curr.start);
            }
            if(curr.end < end) {
                currright = query(curr.right, curr.end, end);
            }
            return currleft&&currright;
        }
    }
    private TreeNode add(TreeNode curr, int start, int end) {
        if(curr == null) {
            return new TreeNode(start, end);
        }
        if(curr.start<=start && curr.end>=end) {
            return curr;
        } else if(end <= curr.start) {
            curr.left = add(curr.left, start, end); 
        } else if(start>= curr.end) {
            curr.right = add(curr.right, start, end);
        } else {
            if(start < curr.start) {
                curr.left = add(curr.left, start, curr.start);
            }
            if(curr.end < end) {
                curr.right = add(curr.right, curr.end, end);
            }
        }
        return curr;
    }
    
    public static void main(String args[])  {
    	
    	//["RangeModule","addRange","addRange","addRange","queryRange","queryRange","queryRange","removeRange","queryRange"]
//    	[[],[10,180],[150,200],[250,500],[50,100],[180,300],[600,1000],[50,150],[50,100]]
    	RangeModule range = new RangeModule();
//    	range.addRange(10, 20);
//    	range.removeRange(14, 16);
//    	System.out.println(range.queryRange(10, 14));
//    	System.out.println(range.queryRange(13, 15));
//    	System.out.println(range.queryRange(16, 17));
    	
//    	range.addRange(10, 180);
//    	range.addRange(150, 200);
//    	range.addRange(250, 500);
//    	System.out.println(range.queryRange(50,100));
//    	System.out.println(range.queryRange(180,300));
//    	System.out.println(range.queryRange(600,1000));
//    	range.removeRange(50,150);
//    	System.out.println(range.queryRange(50,100));
    	
    	//["RangeModule","addRange","addRange","removeRange","addRange","removeRange","addRange","removeRange","removeRange","queryRange"]
    	//[[],[44,53],[69,89],[86,91],[87,94],[34,52],[1,59],[62,96],[34,83],[11,59]]
//    	range.addRange(44,53);
//    	range.addRange(69,89);
//    	range.removeRange(86,91);
//    	range.addRange(87,94);
//    	range.removeRange(34,52);
//    	range.addRange(1,59);
//    	range.removeRange(62,96);
//    	range.removeRange(34,83);
//    	System.out.println(range.queryRange(11,59));
    	
    	//["RangeModule","addRange","addRange","addRange","removeRange","removeRange","removeRange","addRange","addRange","removeRange","removeRange","queryRange"]
    	// [[],[4,83],[9,100],[10,31],[15,45],[52,70],[26,42],[10,94],[2,89],[42,48],[3,97],[16,34]]
    			
    	range.addRange(4,83);
    	range.addRange(9,100);
    	range.addRange(10,31);
    	range.removeRange(15,45);
    	range.removeRange(52,70);
    	range.removeRange(26,42);
    	range.addRange(10,94);
    	range.addRange(2,89);
    	range.removeRange(42,48);
    	range.removeRange(3,97);
    	System.out.println(range.queryRange(16,34));
    }
}

class TreeNode {
    int start;
    int end;
    
    TreeNode left;
    TreeNode right;
    TreeNode(int start, int end) {
        this.left = null;
        this.right = null;
        this.start = start;
        this.end = end;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */



/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */