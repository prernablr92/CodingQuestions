import java.util.Arrays;

public class RangeQuery {
	int elmentindex=0;
    int seg[];
    int nums[];
    int len;
    public RangeQuery(int[] nums) {
        this.nums = nums;
        elmentindex=0;
        len = nums.length;
        if(len > 0) {
        	int x = (int) (Math.ceil(Math.log(len) / Math.log(2))); 
        	  
            //Maximum size of segment tree 
            int max_size = 2 * (int) Math.pow(2, x) - 1; 
      
            seg = new int[max_size];
            createSegTree(0, 0, len-1,nums);
        }
       
    }
    
    public void update(int i, int val) {
        
        updatevalue(0, len-1, 0, i, val-nums[i]);
        nums[i] = val;
    }
    
    public void updatevalue(int start, int end, int index, int reqindex, int val) {
        if(start==end) {
            seg[index] += val;
            return;
        }
        int mid = (start+end)/2;
        seg[index]+=val;
        if(start <= reqindex && mid>=reqindex) {
            updatevalue(start, mid, 2*index+1, reqindex, val);
        } else {
            updatevalue(mid+1, end, 2*index+2, reqindex, val);
        }
    }
    
    public int sumRange(int i, int j) {
        return calculatesum(i, j, 0, len-1, 0);
    }
    
    private int createSegTree(int index, int start , int end, int nums[]) {
        if(start==end) {
            seg[index] = nums[elmentindex];
            elmentindex++;
            return seg[index];
        }
        int mid = (start+end)/2;
        seg[index] = createSegTree(2*index+1, start, mid, nums) + createSegTree(2*index+2, mid+1, end, nums);
        return seg[index];
    }
    
    private int calculatesum(int qs, int qe, int start, int end, int index) {
        if(qs > end || qe < start) {
            return 0;
        }
        if(qs <=start && qe >=end) {
            return seg[index];
        }
        int mid = (start+end)/2;
        return calculatesum(qs, qe, start, mid, 2*index+1) + calculatesum(qs, qe, mid+1, end , 2*index+2); 
        
    }
    
    public static void main(String args[]) {
    	RangeQuery rg = new RangeQuery(new int[] {-28,-39,53,65,11,-56,-65,-39,-43,97});
//    	rg.update(4,6);
//    	rg.update(0,2);
//    	rg.update(0,9);
//    	System.out.println(rg.sumRange(4,4));
//    	rg.update(3,8);
//    	System.out.println(rg.sumRange(0,4));
//    	rg.update(4,1);
//    	System.out.println(rg.sumRange(0,3));
//    	System.out.println(rg.sumRange(0,4));
//    	rg.update(0,4);
    	int [] arr = {4,0,0,0,0};
    	int se = Arrays.binarySearch(arr, 0, 1, 7);
    	System.out.println(se);
    	//["NumArray","update","update","update","sumRange","update","sumRange","update","sumRange","sumRange","update"]
    	//[[[7,2,7,2,0]],[4,6],[0,2],[0,9],[4,4],[3,8],[0,4],[4,1],[0,3],[0,4],[0,4]]
    	
    	
    	//["NumArray","sumRange","update","sumRange","sumRange","update","update","sumRange","sumRange","update","update"]
    			//[[[-28,-39,53,65,11,-56,-65,-39,-43,97]],[5,6],[9,27],[2,3],[6,7],[1,-82],[3,-72],[3,7],[1,8],[5,13],[4,-67]]
    }
}
