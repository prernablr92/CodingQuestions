import java.util.*;
public class Ksubsarry {
	
	private static  int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length, start=0, end=0, currstart=0, currlen = 0, total=0;
        Set<Integer> count[] = new HashSet[len];
        Set<Integer> set;
        for(start=0;start<len;start++) {
            end = (start+K-1);
            if(end >=len) {
                break;
            }
            currstart=start;
            set = new HashSet<Integer>();
            while(currstart <=end) {
                set.add(A[currstart]);
                if(set.size() > K) {
                    break;
                }
                currstart++;
            }
            if(set.size()==K) {
                total++;
            }
            count[end] = set;
        }
        
        for(currlen=K+1;currlen<=len;currlen++) {
            for(start=0;start<len;start++) {
                end = start+currlen-1;
                if(end>=len) {
                    break;
                }
                set = count[end];
                if(set.size() <= K) {
                    set.add(A[start]);
                    if(set.size()==K) {
                        total++;
                    }
                }
                count[end] = set;
            }
        }
        return total;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(subarraysWithKDistinct(new int[] {1,2,1,2,3}, 2));
	}

}
