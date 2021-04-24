import java.util.*;

public class Autocomplete {
    static PrefixNode root;
    static String currsentence;
    static Map<String, Integer> sentenncesMap;

    public Autocomplete(String[] sentences, int[] times) {
        int len = sentences.length, index=0;
        sentenncesMap = new HashMap<String , Integer>();
        root = new PrefixNode();
        currsentence = null;
        for(index=0;index<len;index++) {
        	sentenncesMap.put(sentences[index], times[index]);
        	insertSentence(sentences[index], times[index]);
        }
    }
    
    public static List<String> input(char c) {
        PrefixNode currnode = root;
        List<String> result= new ArrayList<String>();
        List<Node> nodelist= new ArrayList<Node>();
        
        
        if(c == '#') {
        	if(sentenncesMap.containsKey(currsentence)) {
        		sentenncesMap.put(currsentence, sentenncesMap.get(currsentence) + 1);
        		updateSentence(currsentence, sentenncesMap.get(currsentence));
        	} else {
        		sentenncesMap.put(currsentence, 1);
        		insertSentence(currsentence, 1);
        	}
        	
        	currsentence=null;
        	return result;
        }
        
        if(currsentence != null) {
            currsentence = currsentence +c;
        } else {
        	currsentence = "" +c;
        }
        int len = currsentence.length(), index=0;
        char curr;
        for(index=0;index<len;index++) {
            curr = currsentence.charAt(index);
            currnode  = currnode.get(curr);
            if(currnode == null) {
                return new ArrayList<String>();
            }
        }
        nodelist.addAll(currnode.getSentences());
        Collections.sort(nodelist);
        int currcount=0;
        for(Node node : nodelist) {
        	currcount++;
        	if(currcount>3) {
        		break;
        	}
        	result.add(node.sentence);
        }
        return result;
    }
    
    private static void updateSentence(String sentence, int time) {
    	PrefixNode currnode = root;
    	char c= ' ';
    	int currlen = sentence.length(), currindex=0;
        for(currindex=0;currindex<currlen;currindex++) {
            c = sentence.charAt(currindex);
            if(c == '#') {
            	break;
            }
            currnode =  currnode.get(c);
            currnode.updatecount(time, sentence);
        }
    }
    
    private static void insertSentence(String sentence, int time) {
    	PrefixNode currnode = root;
    	char c= ' ';
    	int currlen = sentence.length(), currindex=0;
        for(currindex=0;currindex<currlen;currindex++) {
            c = sentence.charAt(currindex);
            if(c == '#') {
            	break;
            }
            
            if(currnode.get(c)==null) {
                currnode.put(c);
                currnode = currnode.get(c);
            }  else {
            	currnode = currnode.get(c);
            }
            currnode.addSentence(new Node(sentence, time, currindex));
        }
    	
    }
    
    public static void main(String args[]) {
    	Autocomplete auto = new Autocomplete(new String[] {"i love ", "ironman"}, new int[] {3,3});
    	List<String> result;
    	result = input('i');
    	System.out.println(result);
//    	result = input('c');
//    	System.out.println(result);
//    	result = input('#');
//    	System.out.println(result);
//    	result = input('b');
//    	System.out.println(result);
//    	result = input('c');
//    	System.out.println(result);
//    	result = input('#');
//    	System.out.println(result);
//    	result = input('a');
//    	System.out.println(result);
//    	result = input('b');
//    	System.out.println(result);
//    	result = input('c');
//    	System.out.println(result);
//    	result = input('#');
//    	System.out.println(result);
//    	result = input('a');
//    	System.out.println(result);
//    	result = input('b');
//    	System.out.println(result);
//    	result = input('c');
//    	System.out.println(result);
//    	result = input('#');
//    	System.out.println(result);
    	

    	
    	//[[["abc","abbc","a"],[3,3,3]],["b"],["c"],["#"],["b"],["c"],["#"],["a"],["b"],["c"],["#"],["a"],["b"],["c"],["#"]]
    }
}

//class Node implements Comparable<Node> {
//        int  index;
//        String sentence;
//        int count;
//        
//        Node(String sentence, int count, int index) {
//            this.sentence = sentence;
//            this.count = count;
//            this.index=index;
//        }
//        
//        @Override
//        public int compareTo(Node node) {
//            if(this.count > node.count) {
//                return -1;
//            } else if(this.count < node.count) {
//                return 1;
//            } else {
//                int currchar = (int)(sentence.charAt(index));
//                int nodechar = (int)(node.sentence.charAt(index));
//                while(currchar == nodechar) {
//                	index++;
//                	currchar = (int)(sentence.charAt(index));
//                    nodechar = (int)(node.sentence.charAt(index));
//                	
//                }
//                if(currchar < nodechar) {
//                    return -1;
//                } else {
//                    return 1;
//                }
//            }
//        }
//    }

class PrefixNode {
        PrefixNode []chars;
        List<Node> sentencelist;
        
        PrefixNode() {
            chars = new PrefixNode[27];
            sentencelist = new ArrayList<Node>();
        }
        
        public void addSentence(Node node) {
            sentencelist.add(node);
        }
        
        public List<Node> getSentences() {
            List<Node> result = new ArrayList<Node>();
            result.addAll(this.sentencelist);
            return result;
        }
        public void  put(char c) {
            if(c=='#') {
                return;
            } else if(c == ' ') {
                chars[26] = new PrefixNode();
            } else {
                chars[c-'a'] = new PrefixNode();
            }
        }
        
        public void updatecount(int count, String senetence) {
        	for(Node node : sentencelist) {
        		if(node.sentence.equals(senetence)) {
        			node.count = count;
        		}
        	}
        }
        
        public PrefixNode get(char c) {
            if(c == '#') {
                return null;
            } else if(c == ' ') {
                return chars[26];
            } else {
                return chars[c-'a'];
            }
        }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

//[null,[],[],[],["bc"],["bc"],[],["a","abbc","abc"],["abbc","abc"],["abc"],[],["abc","a","abbc"],["abc","abbc"],["abc"],[]]
