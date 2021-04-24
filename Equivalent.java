import java.util.*;
public class Equivalent {
	
	public static List<List<String>> generatequivalenet(List<String> list ) {
		List<List<String>> result = new ArrayList<>();
		int len = list.size(), index=0, currlen=0;
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		String str = "";
		List<String> currlist;
		for(index=0;index<len;index++) {
			str = list.get(index);
			currlen = str.length();
			if(map.containsKey(currlen)) {
				currlist = map.get(currlen);
			} else {
				currlist = new ArrayList<String>();
			}
			currlist.add(str);
			map.put(currlen,  currlist);
		}
			for(Map.Entry<Integer, List<String>> entry: map.entrySet()) {
				if(entry.getKey() == 1) {
					result.add(entry.getValue());
				} else {
					List<List<String>> curresult = generateperm(entry.getValue());
					if(curresult.size() != 0) {
						result.addAll(curresult);
					}
				}
			}
		return result;
	}
	
	private static List<List<String>> generateperm(List<String> list) {
		int len = list.size(), index=0, currindex=0;
		Set<String> set;
		List<List<String>> currlist = new ArrayList<List<String>>();
		List<Integer> diff;
		String currstr="", str="";
		Set<Integer> indexset = new HashSet<Integer>();
		for(index=0;index<len;index++) {
			if(!indexset.contains(index)) {
				str = list.get(index);
				set = new HashSet<String>();
				set.add(str);
				indexset.add(index);
				for(currindex=index+1;currindex<len;currindex++) {
					currstr = list.get(currindex);
					if(!indexset.contains(currindex)) {
						if(issame(str, currstr)) {
							set.add(currstr);
							indexset.add(currindex);
						}
					}
				}
				if(set.size() >=2) {
					List<String> result=  new ArrayList<String>();
					result.addAll(set);
					currlist.add(result);
				}
			}
		}
		return currlist;
	}
	
	private static boolean issame(String str, String currstr) {
		int currdiff=Integer.MAX_VALUE, diff=0,len = str.length(), index=0;
		char c1=' ', c2= ' ';
		for(index=0;index<len;index++) {
			c1= str.charAt(index);
			c2 = currstr.charAt(index);
			if(c2 > c1) {
				System.out.println((122-c2) + "   sec" + (c1-65));
				diff = (122-c2) + (c1-97) +1;
			} else {
				diff = c1-c2;
			}
			if(currdiff == Integer.MAX_VALUE) {
				currdiff = diff;
			} else {
				
				if(currdiff != diff) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		List<String> list = new ArrayList<String>();
		list.add("ab");
		list.add("za");
		list.add("no");
		list.add("test");
		list.add("yes");
		list.add("z");
		list.add("a");
		list.add("q");
		list.add("aaa");
		list.add("zzz");
		list.add("zaz");
		System.out.println(generatequivalenet(list));
		
	}
}
