import java.util.*;
class WordDictionary {

    /** Initialize your data structure here. */
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode currnode = root;
        int index=0, len = word.length();
        for(index=0;index<len;index++) {
            currnode = currnode.add(word.charAt(index));
        }
        currnode.setWord();
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    private boolean search(TrieNode currnode, String word, int index) {
        int len = word.length();
        while(index<len) {
             if(word.charAt(index) == '.') {
                for(int currindex=0;currindex<26;currindex++) {
                    if(currnode.children[currindex] != null) {
                        if(search(currnode.children[index], word, index+1)) {
                        	return true;
                        }
                    }
                }
                 return false;
             } else {
                 currnode = currnode.search(word.charAt(index));
                 if(currnode == null) {
                     return false;
                 }
             }  
        }
        return currnode.isWord;
    }
    
    

      static int[][] findPairsWithGivenDifference(int[] arr, int k) {
        if(k==0) {
          return new int[0][0];
        }
        System.out.println(" here " + k);
        int index=0,len = arr.length, currnum=0, currlen=0, res=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(index=0;index<len;index++) {
          map.put(arr[index], index);
        }
        int [][]result = new int[len][2];
        int []sub;
        for(index=0;index<len;index++) {
          currnum = arr[index] - k;
          if(map.containsKey(currnum)) {
            System.out.println(" present here");
            result[map.get(currnum)] = new int[]{arr[index], currnum};
            currlen++;
          }
        }
        int[][] ans = new int[currlen][2];
        for(index=0;index<len;index++) {
          if(result[index]) {
            ans[res] = result[index];
            res++;
          }
        }
       return ans;
        
        
      }

      public static void main(String[] args) {
    	  findPairsWithGivenDifference(new int[] {4,1}, 3);
      }

    
}

//class TrieNode {
//    TrieNode children[];
//    boolean isWord;
//    
//    TrieNode() {
//        children = new TrieNode[26];
//        isWord = false;
//    }
//    
//    public TrieNode add(char c) {
//        if(children[c-'a']==null) {
//            children[c-'a'] = new TrieNode();
//        }
//        return children[c-'a'];
//    }
//    
//    public TrieNode get(char c) {
//        return children[c-'a'];
//    }
//    
//    public void setWord() {
//        isWord = true;
//    }
//    
//    public TrieNode search(char c) {
//        return children[c-'a'];
//    }
//}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */