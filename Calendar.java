import java.util.*;
import java.util.regex.Pattern;
public class Calendar {
	List<int[]> events;
    public Calendar() {
        events = new ArrayList<int[]>();
    }
    
    public boolean book(int start, int end) {
        boolean conflictstart = search(start, true);
        boolean conflictend = search(end, false);
        if(conflictstart || conflictend) {
            return false;
        } else {
            events.add(new int[]{start, end});
            Collections.sort(events, (a,b)->a[0]-b[0]);
            return true;
        }
    }
    
    private boolean search(int target, boolean start) {
    	int currstart=0, currend=events.size()-1, mid=0;
        int[] event;
        while(currstart<=currend) {
            mid = (currstart+currend)/2;
            event = events.get(mid);
            if((start && event[0] <= target && event[1] > target) || (!start && event[0] <= target && event[1] >= target)) {
                return true;
            } else if(event[0] > target) {
                currend = mid-1;
            } else {
                currstart= mid+1;
            }
        }
        return false;
        
    }
    
    public static void main(String args[]) {
    	final Pattern regex = Pattern.compile(".*id", Pattern.CASE_INSENSITIVE);

        final Matcher matcher = regex.matcher("test-id");

        final Boolean isMacEmbeddedBrowser = matcher.find();
        System.out.println(isMacEmbeddedBrowser);
    }
}
