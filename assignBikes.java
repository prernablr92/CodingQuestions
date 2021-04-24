import java.util.*;

public class assignBikes {
	public static int[] assignBikesMethod(int[][] workers, int[][] bikes) {
        int toalworker = workers.length, totalbikes = bikes.length, currworker, currbike, dist;
        List<Worker> distancemap[] = new ArrayList[toalworker];
        int []wdim;
        int []bdim;
        Worker worker;
        int []result = new int[toalworker];
        PriorityQueue<Node> queue = new PriorityQueue(toalworker, new WorkerComaparator());
        HashSet<Integer> assignedBike = new HashSet<>();
        for(currworker = 0; currworker<toalworker; currworker++) {
            wdim = workers[currworker];
            for(currbike = 0;currbike<totalbikes; currbike++) {
                bdim = bikes[currbike];
                dist = Math.abs(wdim[0] - bdim[0]) + Math.abs(wdim[1] - bdim[1]);
                worker = new Worker(dist, currbike,currworker);
                if(distancemap[currworker] == null) {
                    distancemap[currworker] = new ArrayList<Worker>();
                }
                distancemap[currworker].add(worker);
            }
        }
        
        for(currworker = 0; currworker<toalworker; currworker++) {
            Collections.sort(distancemap[currworker], new BikeComaparator());
            queue.add(new Node(0, distancemap[currworker].get(0)));
        }
        int count=0;
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            if(!assignedBike.contains(node.worker.bikeindex)) {
                assignedBike.add(node.worker.bikeindex);
                result[node.worker.workerindex] = node.worker.bikeindex;
                count++;
                if(count==toalworker) {
                    break;
                }
            } else {
            	queue.add(new Node(node.index+1, distancemap[node.worker.workerindex].get(node.index+1)));
            }
            
            
        }
        return result;
        
    }
	
	public static void main(String args[]) {
		System.out.println(assignBikesMethod(new int[][]{{0,0},{2,1}},new int[][]{{1,2},{3,3}}));
	}
}

class BikeComaparator implements Comparator<Worker> {
    public int compare(Worker w1, Worker w2) {
        if(w1.dist < w2.dist) {
            return -1;
        } else if(w1.dist < w2.dist) {
        	return 1;
        	
        } else if(w1.bikeindex < w2.bikeindex) {
            return -1;
        }
        return 1;
    }
}

class WorkerComaparator implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if(n1.worker.dist < n2.worker.dist) {
            return -1;
        } else if(n1.worker.dist > n2.worker.dist) {
        	return 1;
        	
        } else if(n1.worker.workerindex < n2.worker.workerindex) {
            return -1;
        }
        return 1;
    }
}

//class Node{
//    int index;
//    Worker worker;
//    
//    Node(int index,  Worker worker) {
//        this.index= index;
//        this.worker = worker;
//    }
//}
class Worker {
    int dist;
    int workerindex;
    int bikeindex;
    
    Worker(int dist, int bikeindex, int workerindex) {
        this.dist = dist;
        this.workerindex = workerindex;
        this.bikeindex = bikeindex;
    }
}
