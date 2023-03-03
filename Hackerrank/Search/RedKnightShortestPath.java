package Hackerrank.Search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/red-knights-shortest-path/problem
 * 
 * 		In ordinary chess, the pieces are only of two colors, black and white.
 *      In our version of chess, we are including new pieces with unique
 *      movements. One of the most powerful pieces in this version is the red
 *      knight.
 * 
 *      The red knight can move to six different positions based on its current
 *      position (UpperLeft, UpperRight, Right, LowerRight, LowerLeft, Left) as
 *      shown in the figure below.
 * 
 *      image
 * 
 *      The board is a grid of size . Each cell is identified with a pair of
 *      coordinates , where is the row number and is the column number, both
 *      zero-indexed. Thus, is the upper-left corner and is the bottom-right
 *      corner.
 * 
 *      Complete the function printShortestPath, which takes as input the grid
 *      size , and the coordinates of the starting and ending position and
 *      respectively, as input. The function does not return anything.
 * 
 *      Given the coordinates of the starting position of the red knight and the
 *      coordinates of the destination, print the minimum number of moves that
 *      the red knight has to make in order to reach the destination and after
 *      that, print the order of the moves that must be followed to reach the
 *      destination in the shortest way. If the destination cannot be reached,
 *      print only the word "Impossible".
 * 
 *      Note: There may be multiple shortest paths leading to the destination.
 *      Hence, assume that the red knight considers its possible neighbor
 *      locations in the following order of priority: UL, UR, R, LR, LL, L. In
 *      other words, if there are multiple possible options, the red knight
 *      prioritizes the first move in this list, as long as the shortest path is
 *      still achievable. Check sample input for an illustration.
 * 
 *      Input Format
 * 
 *      The first line of input contains a single integer . The second line
 *      contains four space-separated integers . denotes the coordinates of the
 *      starting position and denotes the coordinates of the final position.
 * 
 *      Constraints
 * 
 *      the starting and the ending positions are different Output Format
 * 
 *      If the destination can be reached, print two lines. In the first line,
 *      print a single integer denoting the minimum number of moves that the red
 *      knight has to make in order to reach the destination. In the second
 *      line, print the space-separated sequence of moves.
 * 
 *      If the destination cannot be reached, print a single line containing
 *      only the word Impossible.
 * 
 *      Sample Input 0
 * 
 *      7 6 6 0 1 Sample Output 0
 * 
 *      4 UL UL UL L Explanation 0
 * 
 *      image
 * 
 *      Sample Input 1
 * 
 *      6 5 1 0 5 Sample Output 1
 * 
 *      Impossible Explanation 1
 * 
 *      image
 * 
 *      Sample Input 2
 * 
 *      7 0 3 4 3 Sample Output 2
 * 
 *      2 LR LL Explanation 2
 * 
 *      image
 */
class Point {

	private int x;
	private int y;
	private String name;

	public Point() {
		super();
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, String name) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x - p.getX(), 2) + Math.pow(this.y - p.getY(), 2));
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}

class QueueData {
	List<String> paths;
	Point point;
	int count;

	public QueueData(List<String> paths, Point point, int count) {
		super();
		this.paths = paths;
		this.point = point;
		this.count = count;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "QueueData [paths=" + paths + ", point=" + point + ", count=" + count + "]";
	}

}

public class RedKnightShortestPath {

	static int[][] isVisited;
	static List<String> paths;

	static Map<String, Integer> orders = new HashMap<>();
	static {
		orders.put("UL", 0);
		orders.put("UR", 1);
		orders.put("R", 2);
		orders.put("LR", 3);
		orders.put("LL", 4);
		orders.put("L", 5);
	};

	static final Point[] KNIGHT_MOVES = new Point[] { new Point(-1, -2, "UL"), new Point(1, -2, "UR"),
			new Point(2, 0, "R"), new Point(1, 2, "LR"), new Point(-1, 2, "LL"), new Point(-2, 0, "L") };

	static boolean isInBoard(Point point, int n) {
		return point.getX() >= 0 && point.getY() >= 0 && point.getX() < n && point.getY() < n;
	}

	public static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
		isVisited = new int[n][n];
		paths = new LinkedList<String>();

		Point start = new Point(j_start, i_start);
		Point end = new Point(j_end, i_end);
		QueueData res = bfs(n, start, end, isVisited, 0, paths);
		if (res == null) {
			System.out.println("Impossible");
			return;
		}

		System.out.println(res.count);
		res.paths.stream().forEach(v -> System.out.print(v + " "));
		System.out.println();
	}

	public static QueueData bfs(int n, Point start, Point end, int[][] isVisited, int count, List<String> paths) {
		Queue<QueueData> queue = new LinkedList<QueueData>();
		queue.add(new QueueData(new LinkedList<>(), start, 0));
		isVisited[start.getX()][start.getY()] = 1;

		QueueData minPath = null;
		while (queue.size() > 0) {
			QueueData getOutData = queue.poll();

			Point outP = getOutData.getPoint();

			if (outP.equals(end)) {
				if (minPath == null) {
					minPath = getOutData;
				} else {
					String comp = String.join("", minPath.getPaths().stream().map(v -> orders.get(v).toString())
							.collect(Collectors.toList()));

					String comp1 = String.join("", getOutData.getPaths().stream().map(v -> orders.get(v).toString())
							.collect(Collectors.toList()));

					if (minPath.count >= getOutData.count && comp.compareTo(comp1) > 0) {
						minPath = getOutData;
					}
				}
			}

			for (int ind = 0; ind < KNIGHT_MOVES.length; ind++) {
				Point p = KNIGHT_MOVES[ind];
				Point next = new Point(outP.getX() + p.getX(), outP.getY() + p.getY(), p.getName());

				if (isInBoard(next, n) && isVisited[next.getX()][next.getY()] == 0) {
					if (!next.equals(end)) {
						isVisited[next.getX()][next.getY()] = 1;
					}

					List<String> tmpPaths = getOutData.getPaths().stream().collect(Collectors.toList());
					tmpPaths.add(next.getName());

					queue.add(new QueueData(tmpPaths, next, getOutData.getCount() + 1));
				}
			}
		}

		return minPath;
	}

	public static void main(String[] args) {
		System.out.println("######################## test 1 ##################################");
		int n = 7;
		int i_start = 6, j_start = 6, i_end = 0, j_end = 1;
		printShortestPath(n, i_start, j_start, i_end, j_end);

		System.out.println("######################## test 2 ##################################");
		int n1 = 7;
		int i_start1 = 0, j_start1 = 3, i_end1 = 4, j_end1 = 3;
		printShortestPath(n1, i_start1, j_start1, i_end1, j_end1);

		System.out.println("######################## test 3 ##################################");
		int n2 = 70;
		int i_start2 = 7, j_start2 = 15, i_end2 = 67, j_end2 = 3;
		printShortestPath(n2, i_start2, j_start2, i_end2, j_end2);

		System.out.println("######################## test 4 ##################################");
		int n3 = 150;
		int i_start3 = 24, j_start3 = 15, i_end3 = 46, j_end3 = 102;
		printShortestPath(n3, i_start3, j_start3, i_end3, j_end3);

		System.out.println("######################## test 5 ##################################");
		int n4 = 100;
		int i_start4 = 2, j_start4 = 24, i_end4 = 92, j_end4 = 45;
		printShortestPath(n4, i_start4, j_start4, i_end4, j_end4);

		System.out.println("######################## test 6 ##################################");
		int n5 = 7;
		int i_start5 = 0, j_start5 = 3, i_end5 = 4, j_end5 = 3;
		printShortestPath(n5, i_start5, j_start5, i_end5, j_end5);

		System.out.println("######################## test 7 ##################################");
		int n6 = 10;
		int i_start6 = 9, j_start6 = 9, i_end6 = 5, j_end6 = 3;
		printShortestPath(n6, i_start6, j_start6, i_end6, j_end6);

		System.out.println("######################## test 8 ##################################");
		int n7 = 30;
		int i_start7 = 25, j_start7 = 2, i_end7 = 23, j_end7 = 29;
		printShortestPath(n7, i_start7, j_start7, i_end7, j_end7);
	}

}
