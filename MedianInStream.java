import java.util.*;

public class MedianInStream {
	
	public static double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length, start=0, end=k-1, minsize=0, maxsize=0, index=k;
        int[] sizes;
        double[] result = new double[len-k+1];
        if(k==1) {
        	index=0;
        	while(index<len) {
        		result[index] = (double)(nums[index]);
        		index++;
        	}
        	return result;
        }
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(len, new MaxComparator());
        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(len, new MinComparator());
        boolean even = k%2==0? true : false;
        HashSet<Integer> minset = new HashSet<Integer>();
        HashSet<Integer> maxset = new HashSet<Integer>();
        sizes = initializeHeap(minHeap, maxHeap, even, nums, k, minset, maxset);
        minsize = sizes[0];
        maxsize = sizes[1];
        
        result[0] = calculatemedian(minHeap, maxHeap, even, minsize, maxsize);
        while(index<len) {
            start++;
            end++;
            if(minset.contains(start-1)) {
            	minsize--;
            } else if(maxset.contains(start-1)) {
            	maxsize--;
            }
            if(!minHeap.isEmpty() && nums[end] <=minHeap.peek().value) {
                minHeap.add(new Node(end, nums[end]));
                minset.add(end);
                minsize++;
            } else {
                maxHeap.add(new Node(end, nums[end]));
                maxset.add(end);
                maxsize++;
            }
            sizes = rebalanceHeap(minHeap, maxHeap, even, minsize, maxsize, start, minset, maxset);
            minsize = sizes[0];
            maxsize = sizes[1];
            result[index-k+1] = calculatemedian(minHeap, maxHeap, even, minsize, maxsize); 
            index++;
        }
        return result;
    }
    
    private static int[] rebalanceHeap(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, boolean even , int minsize, int maxsize, int start, HashSet<Integer> minset, HashSet<Integer> maxset) {
        int[] sizes;
        while(!minHeap.isEmpty() && minHeap.peek().index<start) {
        	minset.remove(minHeap.peek().index);
            minHeap.poll();
        }
        while(!maxHeap.isEmpty() && maxHeap.peek().index<start) {
        	maxset.remove(maxHeap.peek().index);
            maxHeap.poll();
        }
        if(even) {
            if(minsize != maxsize) {
                sizes = balanceTree(minsize, maxsize, minHeap, maxHeap, minset, maxset);
                minsize = sizes[0];
                maxsize=sizes[1];
            }
        } else {
            if(Math.abs(minsize-maxsize)>1) {
                sizes = balanceTree(minsize, maxsize, minHeap, maxHeap, minset, maxset);
                minsize=sizes[0];
                maxsize=sizes[1];
            }
        }
        
        while(!minHeap.isEmpty() && minHeap.peek().index<start) {
        	minset.remove(minHeap.peek().index);
            minHeap.poll();
        }
        while(!maxHeap.isEmpty() && maxHeap.peek().index<start) {
        	maxset.remove(maxHeap.peek().index);
            maxHeap.poll();
        }
        return new int[]{minsize, maxsize};
    }
    
    private static int[] initializeHeap(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, boolean even, int nums[],
    		int k, HashSet<Integer> minset, HashSet<Integer> maxset) {
        int minsize=1, maxsize=1, index=2, curr=2;
        int sizes[];
        if(nums[0] > nums[1]) {
            maxHeap.add(new Node(0, nums[0]));
            minHeap.add(new Node(1, nums[1]));
            minset.add(1);
            maxset.add(0);
        } else {
            maxHeap.add(new Node(1, nums[1]));
            minHeap.add(new Node(0, nums[0]));
            maxset.add(1);
            minset.add(0);
        }
        while(index<k) {
            curr++;
            if(nums[index]<=minHeap.peek().value) {
                minHeap.add(new Node(index, nums[index]));
                minset.add(index);
                minsize++;
            } else {
                maxHeap.add(new Node(index, nums[index]));
                maxset.add(index);
                maxsize++;
            }
            if(curr%2 ==0) {
                if(minsize != maxsize) {
                    sizes = balanceTree(minsize, maxsize, minHeap, maxHeap, minset, maxset);
                    minsize = sizes[0];
                    maxsize=sizes[1];
                }
            } else {
                if(Math.abs(minsize-maxsize)>1) {
                    sizes = balanceTree(minsize, maxsize, minHeap, maxHeap, minset, maxset);
                    minsize=sizes[0];
                    maxsize=sizes[1];
                }
            }
            index++;
        }
        sizes = new int[2];
        sizes[0] = minsize;
        sizes[1] = maxsize;
        return sizes;
    }
    
    private static int[] balanceTree(int minsize, int maxsize, PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, HashSet<Integer> minset, HashSet<Integer> maxset) {
        int[] sizes = new int[2];
        sizes[0] = minsize;
        sizes[1] = maxsize;
        if(minsize>maxsize) {
        	Node node = minHeap.poll();
            maxHeap.add(node);
            minset.remove(node.index);
            maxset.add(node.index);
            sizes[0]--;
            sizes[1]++;
        } else {
        	Node node = maxHeap.poll();
            minHeap.add(node);
            maxset.remove(node.index);
            minset.add(node.index);
            sizes[0]++;
            sizes[1]--;
        }
        return sizes;
    }
    
    private static double calculatemedian(PriorityQueue<Node> minHeap, PriorityQueue<Node> maxHeap, boolean even, int minsize, int maxsize) {
        if(even) {
            return ((double)minHeap.peek().value + (double)maxHeap.peek().value)/2;
        }
        return (minsize>maxsize) ? (double)minHeap.peek().value : (double)maxHeap.peek().value;
    }
	public static void main(String args[]) {
		double result[] = medianSlidingWindow(new int[] 
				{1,1,1,1,1,1},1);

		//,,,,-2147483648]
		//		6	
		//[-1.0737418235E9, -1.0737418235E9, -49.5, -49.5, 2.0, 5.5, -46.0, -46.0, 12.5, 19.5,
		// 1.0737418345E9, 2.147483647E9, 2.147483647E9, 2.147483647E9, 2.147483647E9]
		
		//[-1073741823.50000,-1073741823.50000,-49.50000,-49.50000,2.00000,5.50000,-46.00000,-46.00000,12.50000,19.50000,1073741834.50000,
		// 2147483647.00000,2147483647.00000,2147483647.00000,2147483647.00000]
		System.out.println("here");
	}

}


//class Node {
//    int index;
//    int value;
//    Node(int index, int value) {
//        this.index= index;
//        this.value = value;
//    }
//}

class MinComparator implements Comparator<Node>{
    public int compare(Node n1, Node n2) {
        if(n1.value > n2.value) {
            return 1;
        } else {
            return -1;
        }
    }
}

class MaxComparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if(n1.value>n2.value) {
            return -1;
        } else {
            return 1;
        }
    }
}
