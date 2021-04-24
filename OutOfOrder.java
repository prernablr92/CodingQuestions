import java.util.Arrays;
import java.util.*;
public class OutOfOrder {
	
	public static int countOutOfOrder(int []nums) {
		int len = nums.length, index=len-1, count=0, currindex=0, total=0;
		List<Integer>sorted = new ArrayList<Integer>();
		while(index>=0) {
			currindex = search(sorted, nums[index]);
			sorted.add(currindex, nums[index]);
			total+=(currindex);
			index--;
		}
		return total;
	}
	
	private static int search(List<Integer> sorted, int value) {
		int start=0, end = sorted.size()-1, mid=0;
		while(start<=end) {
			mid = (start+end)/2;
			if(sorted.get(mid) == value && (mid==start || sorted.get(mid-1) == value)) {
				return mid;
			} else if(sorted.get(mid) <= value) {
				start = mid+1;
			} else {
				end = mid-1;
			}
		}
		return start;
	}
	public static void main(String args[]) {
		System.out.println(countOutOfOrder(new int[] {1,5,3,2,4}));
	}

}
