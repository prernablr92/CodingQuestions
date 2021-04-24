import java.util.Arrays;

public class MutableRange {
    Node []seg;
    int[][] matrix;
    public MutableRange(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
         int x = (int) (Math.ceil(Math.log(rows) / Math.log(2))); 
  
        //Maximum size of segment tree 
        int max_size = 2 * (int) Math.pow(2, x) - 1; 
        seg = new Node[max_size];
        this.matrix = matrix;
        createSegmentTree(0, rows-1, 0);
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        updatevalue(0, matrix.length-1, row, col, diff, 0);
        matrix[row][col] = val;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return searchseg(row1, row2, 0, matrix.length-1, 0, col1, col2);
    }
    
    private void updatevalue(int start, int end, int row, int col, int diff, int index) {
    	if(row < start || row >end) {
    		return;
    	}
    	int mid = (start+end)/2, currindex=0, len=0;
    	if(start != end) {
    		updatevalue(start, mid, row, col, diff, 2*index+1);
        	updatevalue(mid+1, end, row, col, diff, 2*index+2);
    	}
    	
    	len = seg[index].nums.length;
    	for(currindex=col;currindex<len;currindex++) {
    		seg[index].nums[currindex] +=  diff;
    	}
    }
    
    private int searchseg(int qs, int qe, int start, int end, int index, int col1, int col2) {
        if(qs > end || qe < start) {
            return 0;
        }
        if(qs<=start && qe >=end) {
            Node node = seg[index];
            return node.nums[col2] - ((col1==0) ? 0 : node.nums[col1-1]);
        }
        int mid = (start+end)/2;
        int left = searchseg(qs, qe, start, mid, 2*index+1, col1, col2);
        int right = searchseg(qs, qe, mid+1 , end, 2*index+2, col1, col2);
        return left + right;
    }
    
    private void createSegmentTree(int start, int end, int index) {
        Node node;
        if(start == end) {
            int currindex=0, len = matrix[start].length, currsum=0;
            int sum[] = new int[len];
            for(currindex=0;currindex<len;currindex++) {
                sum[currindex] = (currindex-1>=0) ? sum[currindex-1] + matrix[start][currindex] : matrix[start][currindex];
                currsum+=matrix[start][currindex];
            }
            node = new Node(sum, currsum);
            seg[index] = node;
            return;
        }
        int mid = (start + end)/2;
        createSegmentTree(start, mid, 2*index+1);
        createSegmentTree(mid+1, end, 2*index+2);
        Node left = seg[2*index+1];
        Node right = seg[2*index+2];
        int len = left.nums.length, currindex=0, currsum=0;
        int sum[] = new int[len];
        for(currindex = 0;currindex<len;currindex++) {
            sum[currindex] = left.nums[currindex] + right.nums[currindex];
            currsum+=sum[currindex];
        }
        seg[index] = new Node(sum, currsum);
    }
    
    public static void main(String args[]) {
    	MutableRange mr = new MutableRange(new int[][] {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}});
    	System.out.println(mr.sumRegion(2, 1, 4, 3));
    	int[] nums = new int[] {2,3,0};
    	int index = Arrays.binarySearch(nums, 0, 0, 4);
    	mr.update(3, 2, 2);
    	System.out.println(mr.sumRegion(2, 1, 4, 3));
    }
}
//class Node {
//    int []nums;
//    int sum;
//    
//    Node(int nums[], int sum) {
//        this.nums = nums;
//        this.sum = sum;
//    }
//}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */