package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/crossword-puzzle/problem?isFullScreen=true
 * 
 *      Many flaws but this problem is just medium
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

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}

public class CrosswordPuzzle {

	public static List<List<String>> res;

	public static List<Point> getMaxStrictIncreasingList(List<Point> points) {

		int max = 0, fromInd = 0, endInd = points.size();
		for (int ind = 0; ind < points.size();) {
			int start = ind;

			while (start + 1 < points.size() && points.get(start).distance(points.get(start + 1)) == 1) {
				start++;
			}
			if (start - ind == 0) {
				ind++;
			} else {
				if (max < start - ind) {
					max = start - ind;
					fromInd = ind;
					endInd = start + 1;
				}
				ind = start + 1;
			}
		}

		if (max == 0) {
			return new ArrayList<>();
		}

		return points.subList(fromInd, endInd);
	}

	public static List<List<Point>> findPoints(List<List<String>> crosswordArr) {

		Map<String, Set<Point>> mapPoints = new HashMap<>();
		for (int r = 0; r < crosswordArr.size(); r++) {
			for (int c = 0; c < crosswordArr.get(r).size(); c++) {
				String data = crosswordArr.get(r).get(c);
				if (data.equals("-")) {
					Set<Point> groupPointsX = mapPoints.containsKey("x-" + r) ? mapPoints.get("x-" + r)
							: new TreeSet<>(new Comparator<Point>() {
								@Override
								public int compare(Point o1, Point o2) {
									return o1.getX() == o2.getX() ? o1.getY() - o2.getY() : o1.getX() - o2.getX();
								}
							});
					groupPointsX.add(new Point(r, c));
					Set<Point> groupPointsY = mapPoints.containsKey("y-" + c) ? mapPoints.get("y-" + c)
							: new TreeSet<>(new Comparator<Point>() {
								@Override
								public int compare(Point o1, Point o2) {
									return o1.getX() == o2.getX() ? o1.getY() - o2.getY() : o1.getX() - o2.getX();
								}
							});
					groupPointsY.add(new Point(r, c));

					mapPoints.put("x-" + r, groupPointsX);
					mapPoints.put("y-" + c, groupPointsY);
				}
			}
		}

		List<List<Point>> points = new ArrayList<>();
		for (Map.Entry<String, Set<Point>> entry : mapPoints.entrySet()) {
			List<Point> pointSets = getMaxStrictIncreasingList(new ArrayList<>(entry.getValue()));
			if (pointSets.size() > 1) {
				points.add(pointSets);
			}
		}

		return points;
	}

	public static List<String> crosswordPuzzle(List<String> crossword, String words) {

		List<List<String>> crosswordArr = crossword.stream().map(word -> {
			return Arrays.asList(word.split(""));
		}).collect(Collectors.toList());

		res = new ArrayList<>();

		List<String> wordList = Arrays.asList(words.split(";"));
		List<List<Point>> points = findPoints(crosswordArr);

		// points.forEach(point -> System.out.println(point));

		recursion(wordList, points, 0, crosswordArr);

		res.forEach(crossWord -> {
			crossWord.forEach(v -> System.out.print(v));
			System.out.println();
		});

		return res.stream().map(data -> {
			return String.join("", data);
		}).collect(Collectors.toList());
	}

	public static void recursion(List<String> wordList, List<List<Point>> points, int ind,
			List<List<String>> crosswordArr) {

		if (ind == points.size()) {
			res = crosswordArr;
			return;
		}

		List<Point> currentPoints = points.get(ind);
		List<String> wordFitLength = wordList.stream().filter(word -> word.length() == currentPoints.size())
				.collect(Collectors.toList());

		for (int pos = 0; pos < wordFitLength.size(); pos++) {
			String wordS = wordFitLength.get(pos);
			boolean isWrong = false;
			for (int id = 0; id < currentPoints.size(); id++) {
				Point p = currentPoints.get(id);
				String val = crosswordArr.get(p.getX()).get(p.getY());
				String fill = String.valueOf(wordS.charAt(id));
				if (!val.equals("-") && !val.equals(fill)) {
					isWrong = true;
					break;
				}
			}

			if (!isWrong) {

				List<List<String>> crosswordArrTmp = crosswordArr.stream()
						.map(s -> s.stream().map(t -> t).collect(Collectors.toList())).collect(Collectors.toList());

				for (int id = 0; id < currentPoints.size(); id++) {
					Point p = currentPoints.get(id);
					crosswordArrTmp.get(p.getX()).set(p.getY(), String.valueOf(wordS.charAt(id)));
				}

				recursion(wordList.stream().filter(w -> !w.equals(wordS)).collect(Collectors.toList()), points, ind + 1,
						crosswordArrTmp.stream().map(s -> s.stream().map(t -> t).collect(Collectors.toList()))
								.collect(Collectors.toList()));
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("######################### test 1 #########################");
		List<String> crossword = new ArrayList<>();
		crossword.add("++++++++++");
		crossword.add("+------+++");
		crossword.add("+++-++++++");
		crossword.add("+++-++++++");
		crossword.add("+++-----++");
		crossword.add("+++-++-+++");
		crossword.add("++++++-+++");
		crossword.add("++++++-+++");
		crossword.add("++++++-+++");
		crossword.add("++++++++++");
		String words = "POLAND;LHASA;SPAIN;INDIA";

		crosswordPuzzle(crossword, words);

		System.out.println("######################### test 2 #########################");
		List<String> crossword1 = new ArrayList<>();
		crossword1.add("++++++-+++");
		crossword1.add("++------++");
		crossword1.add("++++++-+++");
		crossword1.add("++++++-+++");
		crossword1.add("+++------+");
		crossword1.add("++++++-+-+");
		crossword1.add("++++++-+-+");
		crossword1.add("++++++++-+");
		crossword1.add("++++++++-+");
		crossword1.add("++++++++-+");
		String words1 = "ICELAND;MEXICO;PANAMA;ALMATY";

		crosswordPuzzle(crossword1, words1);

		System.out.println("######################### test 3 #########################");
		List<String> crossword2 = new ArrayList<>();
		crossword2.add("+-++++++++");
		crossword2.add("+-++++++++");
		crossword2.add("+-------++");
		crossword2.add("+-++++++++");
		crossword2.add("+-++++++++");
		crossword2.add("+------+++");
		crossword2.add("+-+++-++++");
		crossword2.add("+++++-++++");
		crossword2.add("+++++-++++");
		crossword2.add("++++++++++");
		String words2 = "AGRA;NORWAY;ENGLAND;GWALIOR";

		crosswordPuzzle(crossword2, words2);

	}

}
