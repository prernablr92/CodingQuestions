import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Skyline {
	
//	public static List<List<Integer>> getSkyline(int[][] buildings) {
//        int len = buildings.length, index=0, start=0, end=0, startvalue=0, endvalue=0;
//        final String test=
//        int[] dim;
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        while(index<len) {
//            start=index;
//            dim = buildings[index];
//            end = index;
//            index++;
//            while(index<len) {
//                dim = buildings[index];
//                if(dim[0] > buildings[end][1]) {
//                    break;
//                }
//                if(dim[1] > buildings[end][1]) {
//                    end = index;
//                }
//                index++;
//            }
//            List<List<Integer>> subresult = calculateSegment(start, index-1, buildings); 
//            result.addAll(subresult);
//        }
//        return result;
//    }
//    
//    private static List<List<Integer>> calculateSegment(int start, int end, int[][] buildings) {
//        List<List<Integer>> result = new ArrayList<List<Integer>>();
//        Stack<Node> stk = new Stack<Node>();
//        int []dim;
//        Node node=null;
//        while(start<=end) {
//            dim = buildings[start];
//            if(stk.isEmpty()) {
//                stk.push(new Node(dim[0], dim[1], dim[2]));
//            } else {
//                Node topdim = stk.peek();
//                if(topdim.height == dim[2]) {
//                	stk.pop();
//                	stk.push(new Node(topdim.start, Math.max(topdim.end, dim[1]), dim[2]));
//                } else if(topdim.start == dim[0] && topdim.end == dim[1]) {
//                   stk.pop();
//                   stk.push(new Node(dim[0], dim[1], Math.max(dim[2], topdim.height)));
//                } else if(topdim.start == dim[0]) {
//                    if(topdim.height >= dim[2]) {
//                        if(dim[1] > topdim.end) {
//                        	
//                            stk.push(new Node(topdim.start, dim[1], dim[2]));
//                        }
//                    }
//                } else if(topdim.end == dim[1]) {
//                    if(topdim.height < dim[2]) {
//                        stk.pop();
//                        stk.push(new Node(topdim.start, dim[1], topdim.height));
//                        stk.push(new Node(dim[0], dim[1], dim[2]));
//                    }
//                }  else {
//                    if(topdim.end <= dim[1]) {
//                    	while(!stk.isEmpty() && stk.peek().end >= dim[0]) {
//                    		topdim = stk.peek();
//                    		stk.pop();
//                    	}
//                        if(dim[2] > topdim.height) {
//                            stk.push(new Node(topdim.start, dim[0], topdim.height));
//                            stk.push(new Node(dim[0], dim[1], dim[2]));
//                        } else {
//                        	stk.push(topdim);
//                            stk.push(new Node(topdim.end, dim[1], dim[2]));
//                        }
//                    } else {
//                        stk.pop();
//                        stk.push(new Node(topdim.start, dim[0], topdim.height));
//                        stk.push(new Node(dim[0], dim[1], dim[2]));
//                        stk.push(new Node(dim[1], topdim.end, topdim.height));
//                    }
//                }
//            }
//            start++;
//        }
//        List<Integer> subresult;
//        if(!stk.isEmpty()) {
//            node=stk.peek();
//            subresult = new ArrayList<Integer>();
//            subresult.add(node.end);
//            subresult.add(0);
//            result.add(0, subresult);
//            //System.out.println(node.end);
//        }
//        while(!stk.isEmpty()) {
//            subresult = new ArrayList<Integer>();
//            node = stk.peek();
//            stk.pop();
//            subresult.add(node.start);
//            subresult.add(node.height);
//            result.add(0, subresult);
//        }
//        return result;
//        
//        
//    }final String str = "abc
	public static void main(String args[]) {
		
		//int[][] input = {{2, 9 ,10}, {3, 7, 15}, {5, 12 ,12}, {15, 20, 10}, {19, 24, 8}};
		//int[][] input = {{2, 9 ,10}, {3, 7, 15}};
		//int[][] input = {{1, 2 ,1}, {1, 2, 2}, {1,2,3}};
		//int[][] input = {{0,2,3},{2,5,3}};
		//int[][] input = {{2,9,10},{9,12,15}};
		final Set<String> set = new HashSet<String>();
		set.add("test");
		//str = new String("test1");
		//int [][] input = {{2,13,10},{10,17,25}, {12,20,14}};
		int [] input = new int[] {};
		System.out.println(Arrays.binarySearch(input, 8));
		//getSkyline(input);
	}
}

//class Node {
//	int start;
//    int end;
//    int height;
//    
//    Node(int start, int end, int height) {
//        this.start = start;
//        this.end = end;
//        this.height = height;
//    }
//}


