package Hackerrank.Implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class KeyPairValue<K, V> {
    private K key;
    private V value;

    public KeyPairValue(K key, V value) {
	super();
	this.key = key;
	this.value = value;
    }

    public K getKey() {
	return key;
    }

    public void setKey(K key) {
	this.key = key;
    }

    public V getValue() {
	return value;
    }

    public void setValue(V value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return "[" + key + ", " + value + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	KeyPairValue other = (KeyPairValue) obj;
	return Objects.equals(key, other.key) && Objects.equals(value, other.value);
    }
}

public class TheBombermanGame {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY. The function accepts
     * following parameters: 1. INTEGER n 2. STRING_ARRAY grid
     */

    public static int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static boolean isInBoard(int x, int y, int bX, int bY) {
	return x > -1 && y > -1 && x < bX && y < bY;
    }

    public static Set<KeyPairValue<Integer, Integer>> getInitialBombCellState(List<String> grid) {
	Set<KeyPairValue<Integer, Integer>> bomCoords = new HashSet<>();
	for (int y = 0; y < grid.size(); y++) {
	    String r = grid.get(y);
	    for (int x = 0; x < r.length(); x++) {
		if (r.charAt(x) == 'O') {
		    bomCoords.add(new KeyPairValue<Integer, Integer>(x, y));
		}
	    }
	}

	return bomCoords;
    }

    public static Set<KeyPairValue<Integer, Integer>> getEmptyCellAfterDetonate(
	    Set<KeyPairValue<Integer, Integer>> currentBombCoords, int gridX, int gridY) {

	Set<KeyPairValue<Integer, Integer>> emptyCells = new HashSet<>();

	for (KeyPairValue<Integer, Integer> coord : currentBombCoords) {
	    emptyCells.add(coord);
	    for (int[] direct : DIRECTIONS) {
		int nextX = direct[0] + coord.getKey();
		int nextY = direct[1] + coord.getValue();
		if (isInBoard(nextX, nextY, gridX, gridY)) {
		    emptyCells.add(new KeyPairValue<Integer, Integer>(nextX, nextY));
		}
	    }
	}

	return emptyCells;
    }

    public static Set<KeyPairValue<Integer, Integer>> getBombCell(Set<KeyPairValue<Integer, Integer>> emptyCells,
	    int gridX, int gridY) {

	Set<KeyPairValue<Integer, Integer>> bombCells = new HashSet<>();
	for (int y = 0; y < gridY; y++) {
	    for (int x = 0; x < gridX; x++) {
		KeyPairValue<Integer, Integer> point = new KeyPairValue<Integer, Integer>(x, y);
		if (!emptyCells.contains(point)) {
		    bombCells.add(point);
		}
	    }
	}

	return bombCells;
    }

    public static List<String> fillAllBomb(List<String> grid) {
	return grid.stream().map(bombs -> bombs.replaceAll("\\.", "O")).collect(Collectors.toList());
    }

    public static List<String> fillListWithEmptyCell(List<String> grid,
	    Set<KeyPairValue<Integer, Integer>> emptyCells) {

	List<String[]> gridM = fillAllBomb(grid).stream().map(v -> v.split("")).collect(Collectors.toList());

	for (KeyPairValue<Integer, Integer> coords : emptyCells) {
	    gridM.get(coords.getValue())[coords.getKey()] = ".";
	}

	return gridM.stream().map(v -> String.join("", v)).collect(Collectors.toList());
    }

    public static List<String> bomberMan(int n, List<String> grid) {

	if (n % 2 == 0) {
	    return fillAllBomb(grid);
	}

	if (n < 3) {
	    return grid;
	}

	Set<KeyPairValue<Integer, Integer>> bombCells = getInitialBombCellState(grid);
	Set<KeyPairValue<Integer, Integer>> emptyCells = new HashSet<>();

	int gridX = grid.get(0).length(), gridY = grid.size();
	for (int index = 3; index <= n; index++) {
	    // plants bombs in all cells without bombs
	    if (index % 2 == 0) {
		bombCells = getBombCell(emptyCells, gridX, gridY);
	    }
	    // bombs detonate
	    if (index % 2 != 0) {
		emptyCells = getEmptyCellAfterDetonate(bombCells, gridX, gridY);
	    }
	}

	return fillListWithEmptyCell(grid, emptyCells);
    }

    public static void main(String[] args) {
	System.out.println("################# test 1 ###################");
	List<String> grid = new ArrayList<>();
	grid.add(".......");
	grid.add("...O...");
	grid.add("....O..");
	grid.add(".......");
	grid.add("OO.....");
	grid.add("OO.....");

	List<String> res = bomberMan(3, grid);
	res.forEach(v -> System.out.println(v));

	System.out.println("################# test 2 ###################");
	List<String> grid1 = new ArrayList<>();
	grid1.add(".......");
	grid1.add("...O.O.");
	grid1.add("....O..");
	grid1.add("..O....");
	grid1.add("OO...OO");
	grid1.add("OO.O...");

	res = bomberMan(5, grid1);
	res.forEach(v -> System.out.println(v));

	System.out.println("################# test 3 ###################");
	res = bomberMan(181054341, grid1);
	res.forEach(v -> System.out.println(v));
    }

}
