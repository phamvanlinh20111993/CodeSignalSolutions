package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/crossword-puzzle/problem?isFullScreen=true
 *
 */

class Point {

	private int x;
	private int y;
	
	public Point() {
		super();
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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

	public int distance(Point A) {
		return Math.abs(A.getX() - this.x) + Math.abs(A.getY() - this.y);
	}
}

class WordsInCrosswordGrid {
	private Point start;
	private Point end;
	private List<Point> intersections = new ArrayList<Point>();

	public WordsInCrosswordGrid(Point start, Point end, List<Point> intersections) {
		super();
		this.start = start;
		this.end = end;
		this.intersections = intersections;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public List<Point> getIntersections() {
		return intersections;
	}

	public void setIntersections(List<Point> intersections) {
		this.intersections = intersections;
	}

	public Point getStart() {
		return start;
	}

	public int wordlength() {
		return this.start.distance(end);
	}
}

public class CrosswordPuzzle {

	public static List<String> findWordStartWith(String start, List<String> wordList, int length) {
		return wordList.stream().filter(data -> {
			return (start != null ? data.startsWith(start) : true) && data.length() == length;
		}).collect(Collectors.toList());
	}

	public static List<WordsInCrosswordGrid> find(List<List<String>> crosswordArr) {
		List<WordsInCrosswordGrid> wordsGrid = new ArrayList<>();
		List<List<Boolean>> isVisited = crosswordArr.stream().map(row -> {
			return row.stream().map(v -> false).collect(Collectors.toList());
		}).collect(Collectors.toList());

		for (int r = 0; r < crosswordArr.size(); r++) {
			for (int c = 0; c < crosswordArr.get(r).size(); c++) {
				String data = crosswordArr.get(r).get(c);
				if (data.equals("-") && !isVisited.get(r).get(c)) {
					isVisited.get(r).set(c, true);
					recursion(new Point(r, c), crosswordArr, wordsGrid, isVisited);
				}
			}
		}

		return wordsGrid;
	}

	public static void recursion(Point start, List<List<String>> crosswordArr, List<WordsInCrosswordGrid> wordsGrid,
			List<List<Boolean>> isVisited) {
		int left, right, top, down, x = start.getX(), y = start.getY();

		left = y - 1;
		while (left >= 0 && crosswordArr.get(x).get(left).equals("-")) {
			isVisited.get(x).set(left, true);
			left--;
		}
		
		Point entry = new Point();
		if(left == y-1) {
			
		}

		right = y + 1;
		while (right < crosswordArr.get(x).size() && crosswordArr.get(x).get(right).equals("-")) {
			isVisited.get(x).set(right, true);
			right++;
		}

		top = x - 1;
		while (top >= 0 && crosswordArr.get(top).get(y).equals("-")) {
			top--;
		}

		down = x + 1;
		while (down < crosswordArr.size() && crosswordArr.get(down).get(y).equals("-")) {
			down++;
		}

	}

	public static List<String> crosswordPuzzle(List<String> crossword, String words) {

		List<List<String>> crosswordArr = crossword.stream().map(word -> {
			return Arrays.asList(word.split(""));
		}).collect(Collectors.toList());

		List<String> wordList = Arrays.asList(words.split(";"));

		return crossword;
	}

	public static void main(String[] args) {

	}

}
