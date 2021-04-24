import java.util.*;
public class Fancy {
    
    Map<Integer, Integer> numTime;
    Map<Integer, int[]> numIndx;
    List<int[]> addlist;
    List<int[]> multlist;
    List<Integer> nums;
    int time;
    int index;
    public Fancy() {
        numTime = new HashMap<>();
        numIndx = new HashMap<>();
        addlist = new ArrayList<>();
        multlist = new ArrayList<>();
        nums = new ArrayList<>();
        time=0;
        index=0;
    }
    
    public void append(int val) {
        time++;
        numTime.put(index,time);
        nums.add(val);
        index++;
    }
    
    public void addAll(int inc) {
        time++;
        addlist.add(new int[]{time, inc});
    }
    
    public void multAll(int m) {
        time++;
        multlist.add(new int[]{time, m});
    }
    
    public int getIndex(int idx) {
        int currnum;
        int currindex;
       if(!numIndx.containsKey(idx)) {
            currindex = numTime.get(idx);
            currnum = nums.get(idx);
        } else {
            currnum = numIndx.get(idx)[1];
            currindex = numIndx.get(idx)[0]+1;
        }
        int []searchindex = search(currindex);
        
        int index1=searchindex[0], index2= searchindex[1], len1 = addlist.size(), len2 = multlist.size();
        int []curradd;
        int []currmult;

        while(index1<len1 && index2<len2) {
            curradd = addlist.get(index1);
            currmult = multlist.get(index2);
            if(curradd[0] < currmult[0]) {
                currnum+=curradd[1];
                index1++;
            } else {
                currnum*=currmult[1];
                index2++;
            }
        }
        if(index1<len1) {
            curradd = addlist.get(index1);
            currnum+=curradd[1];
            index1++;
        }
        if(index2<len2) {
            currmult = multlist.get(index2);
            currnum*=currmult[1];
            index2++;
        }
        numIndx.put(idx, new int[]{time, currnum});
        return currnum;
    }
    
    private int[] search(int value) {
        int []searchindex = new int[2];
        searchindex[0] = searchlist(value, addlist);
        searchindex[1] = searchlist(value, multlist);
        return searchindex;
        
    }
    
    private int searchlist(int value,  List<int[]> list) {
        int start=0, end = list.size()-1, mid=0;
        int []values;
        while(start<=end) {
            mid = start+(end-start)/2;
            values = list.get(mid);
            if(value == values[0]) {
                return mid;
            } else if(value > values[0]) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return start;
    }
    
    public static void main(String args[]) {
    
    	Fancy fancy = new Fancy();
    	fancy.append(2);   // fancy sequence: [2]
    	fancy.append(5);   // fancy sequence: [2+3] -> [5]
    	fancy.append(7);   // fancy sequence: [5, 7]
    	fancy.append(6); 
    	fancy.addAll(8); 
    	fancy.addAll(5);
    	System.out.println(fancy.getIndex(0)); // return 10
    	fancy.addAll(5);   // fancy sequence: [10+3, 14+3] -> [13, 17]
    	System.out.println(fancy.getIndex(3));
//    	fancy.append(10);  // fancy sequence: [13, 17, 10]
//    	fancy.multAll(2);  // fancy sequence: [13*2, 17*2, 10*2] -> [26, 34, 20]
//    	 // return 26
//    	System.out.println(fancy.getIndex(1)); // return 34
//    	System.out.println(fancy.getIndex(2)); // return 20
    }
}

