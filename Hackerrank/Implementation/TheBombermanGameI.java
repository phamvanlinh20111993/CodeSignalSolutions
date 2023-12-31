package Hackerrank.Implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
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

    @Override
    public int compareTo(Point o) {
	if (o.x == this.x) {
	    return this.y - o.y;
	}
	return this.x - o.x;
    }

    public boolean isInRange(int Gx, int Gy) {
	return this.x > -1 && this.x < Gx && this.y > -1 && this.y < Gy;
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

    public boolean isEqual(Point o) {
	return this.x == o.getX() && this.y == o.getY();
    }

    public boolean isEqual(int x, int y) {
	return this.x == x && this.y == y;
    }

    public String toStr() {
	return this.x + "-" + this.y;
    }

    @Override
    public String toString() {
	return "Point [x=" + x + ", y=" + y + "]";
    }
}

public class TheBombermanGameI {

    public static String pointToStr(List<Point> bombPositions) {
	bombPositions = bombPositions.stream().sorted().collect(Collectors.toList());

	String res = "";

	for (Point p : bombPositions) {
	    res += p.toStr() + ";";
	}

	return res;
    }

    public static List<Point> strToPoint(String str) {
	List<Point> points = new ArrayList<>();

	String[] spl = str.split(";");

	for (String s : spl) {
	    String[] xyStr = s.split("-");
	    points.add(new Point(Integer.parseInt(xyStr[0]), Integer.parseInt(xyStr[1])));
	}

	return points;
    }

    public static List<String> recoverBomdInGrid(List<Point> bombPositions, int Gx, int Gy) {
	List<String> grid = new ArrayList<>();

	Set<Point> bombPos = new HashSet<>(bombPositions);

	for (int y = 0; y < Gy; y++) {
	    String line = "";
	    for (int x = 0; x < Gx; x++) {
		if (bombPos.contains(new Point(x, y))) {
		    line += "O";
		} else {
		    line += ".";
		}
	    }
	    grid.add(line);
	}

	return grid;
    }

    public static List<Point> detonateBomb(List<Point> oldPositions, int Gx, int Gy) {
	List<Point> newPositionBombs = new ArrayList<>();

	Set<Point> detonateRanges = new HashSet<>();

	for (Point p : oldPositions) {
	    detonateRanges.add(p);

	    Point newXLeft = new Point(p.getX() - 1, p.getY());
	    Point newXright = new Point(p.getX() + 1, p.getY());
	    Point newYLeft = new Point(p.getX(), p.getY() - 1);
	    Point newYRight = new Point(p.getX(), p.getY() + 1);

	    if (newXLeft.isInRange(Gx, Gy))
		detonateRanges.add(newXLeft);
	    if (newXright.isInRange(Gx, Gy))
		detonateRanges.add(newXright);
	    if (newYLeft.isInRange(Gx, Gy))
		detonateRanges.add(newYLeft);
	    if (newYRight.isInRange(Gx, Gy))
		detonateRanges.add(newYRight);
	}

	for (int y = 0; y < Gy; y++) {
	    for (int x = 0; x < Gx; x++) {
		if (!detonateRanges.contains(new Point(x, y))) {
		    newPositionBombs.add(new Point(x, y));
		}
	    }
	}

	return newPositionBombs;
    }

    public static List<String> plantBombs(int Gx, int Gy) {
	List<String> grid = new ArrayList<>();

	for (int ind = 0; ind < Gy; ind++) {
	    String line = "";
	    for (int pos = 0; pos < Gx; pos++) {
		line += "O";
	    }
	    grid.add(line);
	}

	return grid;
    }

    public static Map<String, Integer> findLoopStep(List<Point> bomPositions, int Gx, int Gy) {

	int s = 2;

	Map<String, Integer> rememberS = new LinkedHashMap<>();
	rememberS.put(pointToStr(bomPositions), 1);

	while (true) {
	    // plants bombs
	    if (s % 2 == 0) {
	    }
	    // detonate bombs
	    if (s % 2 != 0) {
		bomPositions = detonateBomb(bomPositions, Gx, Gy);
		String str = pointToStr(bomPositions);
		if (!rememberS.containsKey(str)) {
		    rememberS.put(str, s);
		} else {
		    rememberS.put(str + "-loop", s);
		    break;
		}
	    }
	    s++;
	}

	return rememberS;
    }

    /**
     * 1, initially 2, do nothing 3, plants bombs 4, do nothing 5, repeats step 3
     * and 4 indefinitely
     * 
     */
    public static List<String> bomberMan(int n, List<String> grid) {
	if (n < 2)
	    return grid;

	List<Point> bomPositions = new ArrayList<>();
	int Gy = grid.size();
	int Gx = grid.get(0).length();

	if (n % 2 == 0)
	    return plantBombs(Gx, Gy);

	for (int y = 0; y < Gy; y++) {
	    for (int x = 0; x < Gx; x++) {
		if (grid.get(y).charAt(x) == 'O')
		    bomPositions.add(new Point(x, y));
	    }
	}

	Map<String, Integer> loopSteps = findLoopStep(bomPositions, Gx, Gy);

	String key = "";
	Integer atTimeStart = -1, atTimeEnd = -1;
	for (Entry<String, Integer> dt : loopSteps.entrySet()) {
	    if (dt.getKey().endsWith("-loop")) {
		key = dt.getKey().substring(0, dt.getKey().length() - 5);
		atTimeEnd = dt.getValue();
		atTimeStart = loopSteps.get(key);
		break;
	    }
	}

	if (n <= atTimeEnd) {
	    for (Entry<String, Integer> dt : loopSteps.entrySet()) {
		if (n == dt.getValue()) {
		    List<Point> bombPos = strToPoint(dt.getKey());
		    return recoverBomdInGrid(bombPos, Gx, Gy);
		}
	    }
	}

	int finalTime = (n - atTimeStart + 1) % (atTimeEnd - atTimeStart);

	if (finalTime % 2 == 0)
	    return plantBombs(Gx, Gy);

	System.out.println("data " + finalTime);

	List<String> keys = new ArrayList<>();

	for (Entry<String, Integer> dt : loopSteps.entrySet()) {
	    if (dt.getValue() >= atTimeStart) {
		keys.add(dt.getKey());
		keys.add("");
	    }
	}

	String lastE = keys.get(keys.size() - 2);
	keys.set(keys.size() - 2, lastE.substring(0, lastE.length() - 5));

	List<Point> bombPos = strToPoint(keys.get(finalTime - 1));
	return recoverBomdInGrid(bombPos, Gx, Gy);
    }

    public static void main(String[] args) {
	List<String> inp = List.of(".......",
                "...O...",
                "....O..",
                ".......",
                "OO.....",
                "OO.....");

        System.out.println("##################test 1##################");
        
        List<String> out = bomberMan(3, inp);
        out.forEach(v -> System.out.println(v));
        
        List<String> inp1 = List.of(".......",
                         "...O.O.",
                         "....O..",
                         "..O....",
                         "OO...OO",
                         "OO.O...");
        
        System.out.println("##################test 2##################");
        
        List<String> out1 = bomberMan(5, inp1);
        out1.forEach(v -> System.out.println(v));
        
        List<String> inp2 = List.of("O..OO........O..O........OO.O.OO.OO...O.....OOO...OO.O..OOOOO...O.O..O..O.O..OOO..O..O..O....O...O....O...O..O..O....O.O.O.O.....O.....OOOO..O......O.O.....OOO....OO....OO....O.O...O..OO....OO..O...O");
        
        System.out.println("##################test 3##################");
        
        List<String> out2 = bomberMan(5, inp2);
        out2.forEach(v -> System.out.println(v));

    }

}
