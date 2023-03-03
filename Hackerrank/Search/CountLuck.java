package Hackerrank.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 
 * @author PhamLinh
 * @url: https://www.hackerrank.com/challenges/count-luck/problem?isFullScreen=true
 * 
 * Ron and Hermione are deep in the Forbidden Forest collecting potion ingredients, and they've managed to lose their way. The path out of the forest is blocked, so they must make their way to a portkey that will transport them back to Hogwarts.

Consider the forest as an  grid. Each cell is either empty (represented by .) or blocked by a tree (represented by ). Ron and Hermione can move (together inside a single cell) LEFT, RIGHT, UP, and DOWN through empty cells, but they cannot travel through a tree cell. Their starting cell is marked with the character , and the cell with the portkey is marked with a . The upper-left corner is indexed as .

.X.X......X
.X*.X.XXX.X
.XX.X.XM...
......XXXX.
In example above, Ron and Hermione are located at index  and the portkey is at . Each cell is indexed according to Matrix Conventions.

Hermione decides it's time to find the portkey and leave. They start along the path and each time they have to choose a direction, she waves her wand and it points to the correct direction. Ron is betting that she will have to wave her wand exactly  times. Can you determine if Ron's guesses are correct?

The map from above has been redrawn with the path indicated as a series where  is the starting point (no decision in this case),  indicates a decision point and  is just a step on the path:

.X.X.10000X
.X*0X0XXX0X
.XX0X0XM01.
...100XXXX.
There are three instances marked with  where Hermione must use her wand.

Note: It is guaranteed that there is only one path from the starting location to the portkey.

Function Description

Complete the countLuck function in the editor below. It should return a string, either  if Ron is correct or  if he is not.

countLuck has the following parameters:

matrix: a list of strings, each one represents a row of the matrix
k: an integer that represents Ron's guess
Input Format

The first line contains an integer , the number of test cases.

Each test case is described as follows:
The first line contains  space-separated integers  and , the number of forest matrix rows and columns.
Each of the next  lines contains a string of length  describing a row of the forest matrix.
The last line contains an integer , Ron's guess as to how many times Hermione will wave her wand.

Constraints

There will be exactly one  and one  in the forest.
Exactly one path exists between  and .
Output Format

On a new line for each test case, print  if Ron impresses Hermione by guessing correctly. Otherwise, print .

Sample Input

3
2 3
*.M
.X.
1
4 11
.X.X......X
.X*.X.XXX.X
.XX.X.XM...
......XXXX.
3
4 11
.X.X......X
.X*.X.XXX.X
.XX.X.XM...
......XXXX.
4
Sample Output

Impressed
Impressed
Oops!
Explanation

For each test case,  denotes the number of times Hermione waves her wand.

Case 0: Hermione waves her wand at , giving us . Because , we print  on a new line.
Case 1: Hermione waves her wand at , , and , giving us . Because , we print  on a new line.
Case 2: Hermione waves her wand at , , and , giving us . Because  and ,  and we print  on a new line.
 */
public class CountLuck {

	public static int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	public static Set<String> isVisited = new HashSet<>();
	public static int canFindPath = -2;

	public static int[] findStartPoint(List<String> matrix) {

		int[] startPoint = { -1, -1 };
		for (int p = 0; p < matrix.size(); p++) {
			String row = matrix.get(p);
			for (int ind = 0; ind < row.length(); ind++) {
				if (row.charAt(ind) == 'M') {
					return new int[] { ind, p };
				}
			}
		}

		return startPoint;
	}

	public static boolean isInMatrix(int y, int x, int mX, int mY) {
		return x > -1 && y > -1 && x < mX && y < mY;
	}

	static void findPath(List<List<String>> matrix, int[] startPoint, int k) {

		if (matrix.get(startPoint[1]).get(startPoint[0]).equals("*")) {
			canFindPath = k;
			return;
		}

		if (k < 0 || canFindPath > -1)
			return;

		List<List<Integer>> nextDirection = new ArrayList<List<Integer>>();
		for (int[] d : DIRECTIONS) {
			int nextX = startPoint[0] + d[0];
			int nextY = startPoint[1] + d[1];
			String key = nextX + "-" + nextY;
			if (!isVisited.contains(key) && isInMatrix(nextY, nextX, matrix.get(0).size(), matrix.size())) {

				// if(matrix.get(nextY).get(nextX).equals("*")){
				// canFindPath = k;
				// return;
				// }

				if (!matrix.get(nextY).get(nextX).equals("X")) {
					nextDirection.add(Arrays.asList(new Integer[] { nextX, nextY }));
					isVisited.add(key);
				}
			}
		}

		if (nextDirection.size() > 1)
			k--;

		for (List<Integer> nextPoint : nextDirection) {
			findPath(matrix, new int[] { nextPoint.get(0), nextPoint.get(1) }, k);
		}

	}

	public static String countLuck(List<String> matrix, int k) {
		// Write your code here
		int[] startPoint = findStartPoint(matrix);

		List<List<String>> matrixM = matrix.stream().map(row -> Arrays.asList(row.split("")))
				.collect(Collectors.toList());
		canFindPath = -2;
		isVisited = new HashSet<>();
		isVisited.add(startPoint[0] + "-" + startPoint[1]);
		findPath(matrixM, startPoint, k);

		System.out.println(canFindPath);

		return canFindPath == 0 ? "Impressed" : "Oops!";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
