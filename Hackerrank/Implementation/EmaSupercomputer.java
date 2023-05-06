package Hackerrank.Implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Tuple<K, V> {
    private K k;
    private V v;

    public Tuple() {

    }

    public Tuple(K k, V v) {
	super();
	this.k = k;
	this.v = v;
    }

    public K getK() {
	return k;
    }

    public void setK(K k) {
	this.k = k;
    }

    public V getV() {
	return v;
    }

    public void setV(V v) {
	this.v = v;
    }
}

public class EmaSupercomputer {

    public static boolean isInGrid(int x, int y, List<String> grid) {
	return x > -1 && y > -1 && x < grid.get(0).length() && y < grid.size();
    }

    public static boolean isValidPlus(int x, int y, List<String> grid) {
	return isInGrid(x, y, grid) && grid.get(y).charAt(x) == 'G';
    }

    public static List<Tuple<Integer, Set<String>>> findMax(int px, int py, List<String> grid) {
	int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	int res = 1;
	Set<String> coords = new HashSet<String>();
	coords.add(px + "-" + py);
	List<Tuple<Integer, Set<String>>> resp = new ArrayList<>();

	while (true) {
	    int count = 0;
	    for (int[] c : directions) {
		count += isValidPlus(c[0] + px, c[1] + py, grid) ? 1 : 0;
	    }
	    if (count < 4) {
		break;
	    }
	    for (int[] c : directions) {
		coords.add((c[0] + px) + "-" + (c[1] + py));
	    }
	    res += 4;
	    resp.add(new Tuple<Integer, Set<String>>(res, coords.stream().map(v -> v).collect(Collectors.toSet())));
	    int[][] nDirections = new int[4][2];
	    for (int ind = 0; ind < 4; ind++) {
		nDirections[ind] = directions[ind];
		nDirections[ind][0] += nDirections[ind][0] > 0 ? 1 : nDirections[ind][0] < 0 ? -1 : 0;
		nDirections[ind][1] += nDirections[ind][1] > 0 ? 1 : nDirections[ind][1] < 0 ? -1 : 0;
	    }
	    directions = nDirections;
	}

	return resp;
    }

    public static int getTwoMaxPluses(List<Tuple<Integer, Set<String>>> maxPlusList) {
	int max = 1;
	for (int ind = 0; ind < maxPlusList.size(); ind++) {
	    Tuple<Integer, Set<String>> data = maxPlusList.get(ind);
	    if (max < data.getK()) {
		max = data.getK();
	    }
	    for (int p = ind + 1; p < maxPlusList.size(); p++) {
		Set<String> temp = new HashSet<>();
		temp.addAll(data.getV());
		temp.addAll(maxPlusList.get(p).getV());
		if (temp.size() == data.getV().size() + maxPlusList.get(p).getV().size()) {
		    if (max < data.getK() * maxPlusList.get(p).getK()) {
			max = data.getK() * maxPlusList.get(p).getK();
		    }
		}
	    }
	}

	return max;
    }

    public static int twoPluses(List<String> grid) {

	List<Tuple<Integer, Set<String>>> maxPlusList = new ArrayList<>();

	for (int y = 0; y < grid.size(); y++) {
	    for (int x = 0; x < grid.get(y).length(); x++) {
		if (grid.get(y).charAt(x) == 'G') {
		    List<Tuple<Integer, Set<String>>> resp = findMax(x, y, grid);
		    maxPlusList.addAll(resp);
		}
	    }
	}

	maxPlusList = maxPlusList.stream().sorted(new Comparator<Tuple<Integer, Set<String>>>() {
	    @Override
	    public int compare(Tuple<Integer, Set<String>> o1, Tuple<Integer, Set<String>> o2) {
		return o2.getK() - o1.getK();
	    }
	}).map(v -> {
	    System.out.println(v.getK() + " " + v.getV());
	    return v;
	}).collect(Collectors.toList());

	return getTwoMaxPluses(maxPlusList);
    }

    public static void main(String[] args) {
	System.out.println("############################ test 1 ################################");
	List<String> grid = new ArrayList<>();
	grid.add("GGGGGG");
	grid.add("GBBBGB");
	grid.add("GGGGGG");
	grid.add("GGBBGB");
	grid.add("GGGGGG");
	System.out.println(twoPluses(grid));

	System.out.println("############################ test 2 ################################");
	List<String> grid1 = new ArrayList<>();
	grid1.add("BGBBGB");
	grid1.add("GGGGGG");
	grid1.add("BGBBGB");
	grid1.add("GGGGGG");
	grid1.add("BGBBGB");
	grid1.add("BGBBGB");
	System.out.println(twoPluses(grid1));

	System.out.println("############################ test 3 ################################");
	List<String> grid2 = new ArrayList<>();
	grid2.add("GGGGGGG");
	grid2.add("BGBBBBG");
	grid2.add("BGBBBBG");
	grid2.add("GGGGGGG");
	grid2.add("GGGGGGG");
	grid2.add("BGBBBBG");
	System.out.println(twoPluses(grid2));

    }

}
