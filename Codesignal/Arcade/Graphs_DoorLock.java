package Codesignal.Arcade;

import java.util.Comparator;
import java.util.PriorityQueue;

// 13/1/2024: Not pass: 18/20 :((((, using Prim algorithm
//13/1/2024:  passed: 20/20. add key array, PriorityQueue<E> with solution1()
//           refer: https://www.geeksforgeeks.org/prims-mst-for-adjacency-list-representation-greedy-algo-6/
/**
 * 
 * Url:
 * https://app.codesignal.com/arcade/graphs-arcade/office-structures/9RNbcwe9XWrNKK6qw
 * 
 * In order to protect your office from intruders, your boss decided to install
 * a high-tech lock on the door. The lock represents a large cube with some
 * points floating inside. When the correct pin is entered, the points start to
 * connect to each other by rays of light until they form a single connected
 * structure with rays of the minimum possible total length. When this happens,
 * the lock opens.
 * 
 * Your boss likes interesting challenges, but is not very fond of solving them
 * himself. This is why he asked you, his most (or least?) favorite employee, to
 * solve the challenge he came up with. Given the set of points, he wants you to
 * find the optimal structure that opens the lock. Since there can be several
 * optimal structures, your task is to return the minimum total length of all
 * the rays in one of such structures.
 * 
 * Example
 * 
 * For points = [[0, 0, 0], [1, 1, 1], [1, -1, 1], [-1, 1, 1], [-1, -1, -1]],
 * the output should be solution(points) = 6.9282032303.
 * 
 * The best way is to connect point [0, 0, 0] with all other points.
 * 
 * Input/Output
 * 
 * [execution time limit] 9.5 seconds (js)
 * 
 * [input] array.array.integer points
 * 
 * An array of points floating in the cube. The ith point is represented by its
 * coordinates in 3D space: points[i][0] for x, points[i][1] for y and
 * points[i][2] for z.
 * 
 * Guaranteed constraints: 1 ≤ points.length ≤ 2500, points[i].length = 3, -5000 ≤
 * points[i][j] ≤ 5000.
 * 
 * [output] float
 * 
 * The total length of the rays in an optimal structure. Your answer will be
 * considered correct if its absolute error doesn't exceed 10-5.
 * 
 * 
 */

class NodeDis {
    int start;
    int end;
    double distance;
    
    public NodeDis(int start, int end, double distance) {
	super();
	this.start = start;
	this.end = end;
	this.distance = distance;
    }

    public int getStart() {
	return start;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public double getDistance() {
	return distance;
    }

    public void setDistance(double distance) {
	this.distance = distance;
    }

    @Override
    public String toString() {
	return "start=" + start + ", end=" + end + ", distance=" + distance;
    }
    
}

public class Graphs_DoorLock {

    public static double distance(int[] p1, int[] p2) {
	return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2) + Math.pow(p1[2] - p2[2], 2));
    }

    public static double solution(int[][] points) {
	double total = 0.0;
	int[] isVisited = new int[points.length];
	MinHeap<NodeDis> minHeap = new MinHeap<>(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return o1.getDistance() > o2.getDistance() ? 1 : -1;
	    }
	});
	int start = 0;
	while (isVisited[start] != 1) {
	    isVisited[start] = 1;
	    for (int e = 0; e < points.length; e++) {
		if (isVisited[e] != 1) {
		    minHeap.add(new NodeDis(start, e, distance(points[start], points[e])));
		}
	    }
	    while (minHeap.size() > 0) {
		NodeDis min = minHeap.poll();
	//	System.out.println("Min = " + min);
		if(isVisited[min.getEnd()] == 1) continue;
		total += min.getDistance();
		start = min.getEnd();
		break;
	    }
	}

	return total;
    }
    
    public static double solution1(int[][] points) {
	double total = 0.0;
	int[] isVisited = new int[points.length];
	double[] key = new double[points.length];
	for (int i = 0; i < points.length; i++) {
	    key[i] = Integer.MAX_VALUE;
	}
	
	PriorityQueue<NodeDis> minHeap = new PriorityQueue<>(new Comparator<NodeDis>() {
	    @Override
	    public int compare(NodeDis o1, NodeDis o2) {
		return o1.getDistance() > o2.getDistance() ? 1 : -1;
	    }
	});

	int start = 0;
	while (isVisited[start] != 1) {
	    isVisited[start] = 1;
	    for (int e = 0; e < points.length; e++) {
		double dist = distance(points[start], points[e]);
		if (isVisited[e] != 1  && key[e] > dist) {
		    minHeap.add(new NodeDis(start, e, dist));
		}
	    }
	    while (minHeap.size() > 0) {
		NodeDis min = minHeap.poll();
	//	System.out.println("Min = " + min);
		if(isVisited[min.getEnd()] == 1) continue;
		total += min.getDistance();
		key[min.getEnd()] = min.getDistance();
		start = min.getEnd();
		break;
	    }
	}

	return total;
    }

    public static void main(String[] args) {
	System.out.println("############### test 1 ################");
	int [][] points = {{0,0,0}, 
	                   {1,1,1}, 
	                   {1,-1,1}, 
	                   {-1,1,1}, 
	                   {-1,-1,-1}};
	
	System.out.println("Out = " + solution(points));
	System.out.println("Out1 = " + solution1(points));
	
	System.out.println("############### test 2 ################");
	int [][] points1 = {{-2352,310,823}, 
	                    {-1796,4975,1244}, 
	                    {-1976,4452,-2645}, 
	                    {625,-3072,1421}, 
	                    {-1785,-91,3188}, 
	                    {-2519,-4328,2397}, 
	                    {4406,1370,3569}, 
	                    {1638,3787,-3290}, 
	                    {-4063,-4351,-3077}, 
	                    {-1270,3155,2752}};
	
	System.out.println("Out = " + solution(points1));
	System.out.println("Out1 = " + solution1(points1));
    }

}
