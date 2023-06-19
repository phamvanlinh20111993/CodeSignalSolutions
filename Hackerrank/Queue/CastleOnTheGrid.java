package Hackerrank.Queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Point {
    private int x;
    private int y;
    private int level;

    public Point(int x, int y) {
	super();
	this.x = x;
	this.y = y;
	this.level = 0;
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

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }

    public boolean isInGrid(int gridLength) {
	return this.x > -1 && this.y > -1 && this.x < gridLength && this.y < gridLength;
    }

    public boolean isInGridEdge(int gridLength) {
	return isInGrid(gridLength)
		&& (this.x == 0 || this.y == 0 || this.x == gridLength - 1 || this.y == gridLength - 1);
    }

    public Point newPoint(Point t) {
	return new Point(this.x + t.getX(), this.y + t.getY());
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

    @Override
    public String toString() {
	return "Point [x=" + x + ", y=" + y + "]";
    }
}

/**
 * 
 * @author PhamLinh
 * @link https://www.hackerrank.com/challenges/castle-on-the-grid/problem?isFullScreen=true
 * 
 *       You are given a square grid with some cells open (.) and some blocked
 *       (X). Your playing piece can move along any row or column until it
 *       reaches the edge of the grid or a blocked cell. Given a grid, a start
 *       and a goal, determine the minmum number of moves to get to the goal.
 * 
 *       Example.
 * 
 * 
 * 
 * 
 * 
 * 
 *       The grid is shown below:
 * 
 *       ... .X. ... The starting position so start in the top left corner. The
 *       goal is . The path is . It takes moves to reach the goal.
 * 
 *       Function Description Complete the minimumMoves function in the editor.
 * 
 *       minimumMoves has the following parameter(s):
 * 
 *       string grid[n]: an array of strings that represent the rows of the grid
 *       int startX: starting X coordinate int startY: starting Y coordinate int
 *       goalX: ending X coordinate int goalY: ending Y coordinate Returns
 * 
 *       int: the minimum moves to reach the goal Input Format
 * 
 *       The first line contains an integer , the size of the array grid. Each
 *       of the next lines contains a string of length . The last line contains
 *       four space-separated integers,
 * 
 *       Constraints
 * 
 *       Sample Input
 * 
 *       STDIN FUNCTION ----- -------- 3 grid[] size n = 3 .X. grid =
 *       ['.X.','.X.', '...'] .X. ... 0 0 0 2 startX = 0, startY = 0, goalX = 0,
 *       goalY = 2 Sample Output
 * 
 *       3 Explanation
 * 
 *       Here is a path that one could follow in order to reach the destination
 *       in
 * 
 */
public class CastleOnTheGrid {

    /*
     * Complete the 'minimumMoves' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. STRING_ARRAY grid 2. INTEGER startX 3. INTEGER startY 4.
     * INTEGER goalX 5. INTEGER goalY
     */

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {

	Point[] DIRECTIONS = { new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1) };
	Point start = new Point(startX, startY);
	Point goal = new Point(goalX, goalY);

	Set<Point> isVisited = new HashSet<>();
	Queue<Point> queue = new LinkedList<>();
	queue.add(start);
	isVisited.add(start);

	int gridLength = grid.size();

	while (queue.size() > 0) {
	    Point temp = queue.poll();
	    if (temp.equals(goal)) {
		return temp.getLevel();
	    }

	    for (Point p : DIRECTIONS) {
		Point next = p.newPoint(temp);
		next.setLevel(temp.getLevel() + 1);
		while (next.isInGrid(gridLength) && grid.get(next.getX()).charAt(next.getY()) != 'X') {

		    if (next.equals(goal)) {
			return next.getLevel();
		    }

		    if (!isVisited.contains(next)) {
			isVisited.add(next);
			queue.add(next);
		    }

		    next = next.newPoint(p);
		    next.setLevel(temp.getLevel() + 1);
		}
	    }
	}

	return 0;
    }

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

}
