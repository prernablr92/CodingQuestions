class Solution {
    public String encode(String s) {
        int len = s.length(), start=0, end=0, currlen=1,lenpart, minlen=0, currindex=0;
        Node[][] dp = new Node[len][len];
        String first="", sec="", minstr="", currstr="", source="";
        for(currlen=1;currlen<=len;currlen++)  {
            for(start=0;start<=len-currlen;start++) {
                end = start+currlen-1;
                if(end-start +1 <=4) {
                    currstr = s.substring(start, end+1);
                    dp[start][end] = new Node(currstr, currstr);
                } else {
                    //first partition into various parts so as to see i both parts are equal nad calculate min part
                    lenpart = currlen/2;
                    minlen = end-start+1;
                    minstr = s.substring(start, end+1);
                    while(lenpart>=1) {
                        if(currlen%lenpart==0) {
                            first = s.substring(start, start+lenpart);
                            sec = dp[start+lenpart][end].source;
                            
                            if(first.equals(sec)) {
                                currstr = convert(dp[start+lenpart][end].converted, currlen, lenpart);
                                if(currstr.length() < minlen) {
                                    minlen = currstr.length();
                                    minstr = currstr;
                                    source = sec;
                                }
                            }
                            
                        }
                        lenpart--;
                    }
                    currstr = s.substring(start, end+1);
                    for(currindex=start;currindex<=end-1;currindex++) {
                        currstr = dp[start][currindex].converted + dp[currindex+1][end].converted;
                        if(currstr.length() < minlen) {
                            minlen = currstr.length();
                            minstr = currstr;
                            source = s.substring(start, end+1);
                        }
                    }
                    
                    
                    //if all characters are same
                    if(same(s.substring(start, end))) {
                    	currstr = Integer.toString(currlen) + "[" + s.charAt(start) + "]";
                    	if(currstr.length() < minlen) {
                            minlen = currstr.length();
                            minstr = currstr;
                            source = Character.toString(s.charAt(start));
                        }
                    }
                    dp[start][end]  =new Node(source, minstr);
                }
            }
        }
        return dp[0][len-1].converted;
    }
    
    private boolean same(String s) {
    	int index=1, len=s.length();
    	while(index<len && s.charAt(index) == s.charAt(index-1)) {
    		index++;
    	}
    	return index==len;
    	
    }
    private String convert(String s, int currlen, int lenpart) {
        StringBuilder res = new StringBuilder(s);
        int index=0, len = s.length();
        int prev = (currlen/lenpart)-1;
        if(prev > 1) {
        	while(index<len && isnum(s.charAt(index))) {
                res.deleteCharAt(0);
                index++;
            }
        }
        res.insert(0, Integer.toString(prev+1) + "[");
        res.append("]");
        
        return res.toString();
    }
    
    private boolean isnum(char c) {
        if(c >='0' && c<='9') {
            return true;
        }
        return false;
    }
    
    public static void main(String args[]) {
    	Solution s = new Solution();
    	Long l = Long.parseLong(null);
    	System.out.println(s.encode("abcdabcdcabcdabcdcabcdabcdc"));
    }
}

//class Node {
//    String source;
//    String converted;
//    
//    Node(String source, String converted) {
//        this.source = source;
//        this.converted = converted;
//    }
//}