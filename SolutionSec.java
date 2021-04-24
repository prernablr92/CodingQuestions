import java.util.*;
public class SolutionSec {
	public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        generate(word, 0, word.length(), result, new StringBuilder());
        return result;
    }
    
    private void generate(String word, int index, int len, List<String> result, StringBuilder abs) {
        if(index==len) {
            result.add(abs.toString());
            return;
        }
        
        //append as it is
        abs.append(word.charAt(index));
        generate(word, index+1, len, result, abs);
        abs.deleteCharAt(abs.length()-1);
        
        //append number
        if(isNum(abs)) {
            int []values = getNum(abs);  //0->start index 1->total num
            int currlen = abs.length();
            for(int currindex= values[0];currindex<currlen;currindex++) {
                abs.deleteCharAt(values[0]);
            }
            abs.append(Integer.toString(values[1]+1));
            generate(word, index+1, len, result, abs);
            
            currlen = abs.length();
            System.out.println(" abs is " +abs + " value 0 is " + values[0] + " value 1 is " +values[1]);
            for(int currindex=values[0];currindex<currlen;currindex++) {
                abs.deleteCharAt(currindex);
            }
            abs.append(Integer.toString(values[1]));
        } else {
            abs.append("1");
            generate(word, index+1, len, result, abs);
            abs.deleteCharAt(abs.length()-1);
        }
        
    }
    
    private boolean isNum(StringBuilder abs) {
        int len = abs.length();
        if(len > 0) {
            return (abs.charAt(len-1) >='0' && abs.charAt(len-1) <='9');
        }
        return false;
    }
    
    private int[] getNum(StringBuilder abs) {
        int len = abs.length();
        int num=0, count=1, index=0;
        for(index=len-1;index>=0;index--) {
            if(abs.charAt(index)>='0' && abs.charAt(index) <='9') {
                num += (abs.charAt(index)-'0')*count;
                count*=10;
            } else {
                break;
            }
        }
        return new int[]{index+1, num};
    }
    
    public static void main(String args[]) {
    	SolutionSec s = new SolutionSec();
    	s.generateAbbreviations("dictionary");
    }
    
}
