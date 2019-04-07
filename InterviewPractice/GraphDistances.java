package InterviewPractice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphDistances {

	static int[] isVisited;

	/**
	 * 
	 * @param g
	 * @param s
	 * 
	 * DFS stack
	 */
	static void DFSS(int[][] g, int s) {
		Stack<Integer> storePoint = new Stack<>();
		storePoint.add(s);

		while (!storePoint.isEmpty()) {
			int point = storePoint.pop();
			if(isVisited[point] == 0) {
				System.out.print(point + " ");
				isVisited[point] = 1;
			}
			
			for (int i = 0; i < g[point].length; i++) {
				if (g[point][i] >= 0 && isVisited[i] != 1) {
					storePoint.add(i);
				}
			}
		}
		System.out.println();
	}

	/**
	 * 
	 * @param g
	 * @param s
	 * DFS recursive
	 */
	static void DFSR(int[][] g, int s) {
		int i;
		System.out.print(s + " " );
		isVisited[s] = 1;
		for(i = 0; i < g[s].length; i++) {
			if(isVisited[i] != 1 && g[s][i] >= 0) {
				DFSR(g, i);
			}
		}
	}

	/**
	 * 
	 * @param g
	 * @param s
	 * 
	 */
	static void BFS(int[][] g, int s) {
		Queue<Integer> storePoint = new LinkedList<>();
		storePoint.add(s);

		while (!storePoint.isEmpty()) {
			int point = storePoint.poll();
			
			if(isVisited[point] == 0) {
				System.out.print(point + " ");
				isVisited[point] = 1;
			}
			for (int i = 0; i < g[point].length; i++) {
				if (g[point][i] >= 0 && isVisited[i] != 1) {
					storePoint.add(i);
				}
			}
		}

		System.out.println();
	}

	/**
	 * 
	 * @param g
	 * @param s
	 * @return
	 */
	static Integer[] graphDistances(int[][] g, int s) {

		ArrayList<Integer> res = new ArrayList<>(g.length);

		isVisited = new int[g.length];

		for (int i = 0; i < isVisited.length; i++) {
			isVisited[i] = 0;
		}
		System.out.println("---------- BFS --------------");
		BFS(g, s);
		
		for (int i = 0; i < isVisited.length; i++) {
			isVisited[i] = 0;
		}	
		System.out.println("---------- DFS using stack --------------");
		DFSS(g, s);
		
		for (int i = 0; i < isVisited.length; i++) {
			isVisited[i] = 0;
		}
		System.out.println("---------- DFS using recursive --------------");
		DFSR(g, s);
		System.out.println();

		return res.toArray(new Integer[res.size()]);
	}

	public static void main(String[] args) {

		// [0, 2, 2]
		System.out.println("############# test 1 ###############");
		int[][] g = { { -1, 3, 2 }, 
				      { 2, -1, 0 }, 
				      { -1, 0, -1 } };
		int s = 0;
		graphDistances(g, s);

		// [0, 0, 2]
		System.out.println("############# test 2 ###############");
		int[][] g1 = { { -1, 1, 2 }, { 0, -1, 3 }, { 0, 0, -1 } };
		int s1 = 1;
		graphDistances(g1, s1);

		// [1, 1, 0, 0]
		System.out.println("############# test 3 ###############");
		int[][] g2 = { { -1, 0, 0, 0 }, 
				       { -1, -1, -1, 30 }, { 1, 1, -1, 1 }, { 2, 2, 0, -1 } };
		int s2 = 3;
		graphDistances(g2, s2);

		// [0, 3, 2]
		System.out.println("############# test 4 ###############");
		int[][] g3 = { { -1, -1, 2 }, { 1, -1, 0 }, { -1, 1, -1 } };
		int s3 = 0;
		graphDistances(g3, s3);

		// [0, 1, 4]
		System.out.println("############# test 5 ###############");
		int[][] g4 = { { -1, 1, 4 }, { 1, -1, 4 }, { 4, 4, -1 } };
		int s4 = 0;
		graphDistances(g4, s4);

		// [4, 1, 3, 0]
		System.out.println("############# test 6 ###############");
		int[][] g5 = { { -1, 3, 2, -1 }, { 3, -1, -1, 1 }, { 2, -1, -1, 3 }, { -1, 1, 3, -1 } };
		int s5 = 3;
		graphDistances(g5, s5);

		// [0, 5, 15]
		System.out.println("############# test 7 ###############");
		int[][] g6 = { { -1, 5, 20 }, { 21, -1, 10 }, { -1, 1, -1 } };
		int s6 = 0;
		graphDistances(g6, s6);

		// [1, 6, 0, 9]
		System.out.println("############# test 8 ###############");
		int[][] g7 = { { -1, 5, 2, 15 }, 
				       { 2, -1, 0, 3 }, 
				       { 1, -1, -1, 9 }, 
				       { 0, 0, 0, -1 } };
		int s7 = 2;
		graphDistances(g7, s7);

		//
		System.out.println("############# test Custom ###############");
		int[][] g8 = { { -1, 5, 2, 15 }, 
				       { 2, -1, 0, 3 }, 
				       { 1, -1, -1, 9 }, 
				       { 0, 0, 0, -1 } };
		int s8 = 2;
		graphDistances(g8, s8);
	}

}
