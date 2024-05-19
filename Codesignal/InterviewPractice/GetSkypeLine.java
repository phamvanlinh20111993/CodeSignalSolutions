package Codesignal.InterviewPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author PhamLinh
 * @url https://app.codesignal.com/interview-practice/question/T4jSiqQWd7CTy75DP/description
 * 
 * The city ordinance in BoxCity requires that all buildings be rectangles that lie flat along the ground. All the information about a particular building is contained in 3 numbers:

    left: the x coordinate of the left side of the building,
    width: the width of the building,
    height: the height of the building.

The image below shows a building with (left, width, height) = (2,3,6).
Building

The skyline is a list of adjacent rectangular strips, and represents the collective outline of all the buildings in BoxCity.

Each strip is represented as (left, height) as defined above. We don't need the width of the strip, as this is determined by the left side of the next strip. At the right edge of the city, there will be an infinitely small strip (x, 0). Strips should be the minimum height possible.

Given the list of buildings buildings, where each building is represented by an array of three elements [left, width, height], return the skyline as a list of strips, where each strip is represented by an array of two elements [left, height].

Example

    For buildings = [[2, 3, 6]], the output should be
    solution(buildings) = [[2, 6], [5, 0]].

    Example 1

    For buildings = [[2, 3, 6], [3, 4, 6]], the output should be
    solution(buildings) = [[2, 6], [7, 0]].

    Example 2

    For buildings = [[1, 4, 4], [2, 5, 3], [3, 3, 6], [5.5, 3, 5], [10, 2, 2], [11, 2, 3]], the output should be
    solution(buildings) = [[1, 4], [3, 6], [6, 5], [8.5, 0], [10, 2], [11, 3], [13, 0]].

    Example 3

Input/Output

    [execution time limit] 3 seconds (java)

    [memory limit] 1 GB

    [input] array.array.float buildings

    An array of buildings, sorted in ascending order by their x coordinate.

    Guaranteed constraints:
    1 ≤ buildings.length ≤ 105,
    buildings[i].length = 3,
    0 ≤ buildings[i][0] ≤ 106,
    1 ≤ buildings[i][1] ≤ 106,
    1 ≤ buildings[i][2] ≤ 106,
    buildings[i][0] < buildings[i + 1][0].

    [output] array.array.float

    The skyline represented as a list of strips, where each strip is represented by an array of two elements [left, height].


 */
class BuildingCoord {

    private Double left;
    private Double width;
    private Double height;

    public BuildingCoord(Double l, Double w, Double h) {
	this.left = l;
	this.width = w;
	this.height = h;
    }

    public Double getLeft() {
	return this.left;
    }

    public Double getHeight() {
	return this.height;
    }

    public Double getWidth() {
	return this.width;
    }

    public Double maxRigthCoord() {
	return this.left + this.width;
    }
}

public class GetSkypeLine {

    static double[][] solution(double[][] buildings) {
	List<BuildingCoord> buildingCoords = new ArrayList<>(buildings.length);
	for (int ind = 0; ind < buildings.length; ind++) {
	    buildingCoords.add(new BuildingCoord(buildings[ind][0], buildings[ind][1], buildings[ind][2]));
	}

	PriorityQueue<BuildingCoord> pMaxHeightQueue = new PriorityQueue<>(buildings.length,
		new Comparator<BuildingCoord>() {
		    public int compare(BuildingCoord b1, BuildingCoord b2) {
			Double d = b2.getHeight() - b1.getHeight();
			return d > 0 ? 1 : -1;
		    }
		});

	// get left side building structure
	BuildingCoord heightestBC = null;
	List<Double[]> res = new ArrayList<>();
	for (BuildingCoord currentBC : buildingCoords) {
	    heightestBC = null;
	    while (pMaxHeightQueue.size() > 0) {
		heightestBC = pMaxHeightQueue.peek();
		if (heightestBC.maxRigthCoord() >= currentBC.getLeft()) {
		    break;
		}
		heightestBC = null;
		pMaxHeightQueue.poll();
	    }
	    if (heightestBC == null || currentBC.getHeight() > heightestBC.getHeight()) {
		int size = res.size();
		if (size > 0 && res.get(size - 1)[0] == currentBC.getLeft()) {
		    res.set(size - 1, new Double[] { currentBC.getLeft(), currentBC.getHeight() });
		} else {
		    res.add(new Double[] { currentBC.getLeft(), currentBC.getHeight() });
		}
	    }

	    pMaxHeightQueue.add(currentBC);
	}
	heightestBC = null;
	pMaxHeightQueue = new PriorityQueue<>(buildings.length, new Comparator<BuildingCoord>() {
	    public int compare(BuildingCoord b1, BuildingCoord b2) {
		Double d = b2.getHeight() - b1.getHeight();
		return d > 0 ? 1 : -1;
	    }
	});

	// check reserve from the end to the start to get right side structure
	Set<Double> checkDup = new HashSet<>();
	Collections.sort(buildingCoords, new Comparator<BuildingCoord>() {
	    public int compare(BuildingCoord b1, BuildingCoord b2) {
		Double d = b1.maxRigthCoord() - b2.maxRigthCoord();
		return d > 0 ? 1 : -1;
	    }
	});

	for (int ind = buildingCoords.size() - 1; ind >= 0; ind--) {
	    BuildingCoord currentBC = buildingCoords.get(ind);
	    heightestBC = null;
	    while (pMaxHeightQueue.size() > 0) {
		heightestBC = pMaxHeightQueue.peek();
		if (currentBC.maxRigthCoord() >= heightestBC.getLeft()) {
		    break;
		}
		heightestBC = null;
		pMaxHeightQueue.poll();
	    }
	    if ((heightestBC == null || currentBC.getHeight() > heightestBC.getHeight())
		    && !checkDup.contains(currentBC.maxRigthCoord())) {
		res.add(new Double[] { currentBC.maxRigthCoord(),
			heightestBC == null ? 0.0 : heightestBC.getHeight() });
		checkDup.add(currentBC.maxRigthCoord());

	    }
	    pMaxHeightQueue.add(currentBC);
	}

	Collections.sort(res, new Comparator<Double[]>() {
	    public int compare(Double[] d1, Double[] d2) {
		Double d = d1[0] - d2[0];
		return d > 0 ? 1 : -1;
	    }
	});

	return res.stream().map(row -> Arrays.stream(row)
		.mapToDouble(Double::doubleValue).toArray())
		.toArray(double[][]::new);
    }

    public static void main(String[] args) {
	System.out.println("############################### Test 1 ###################################");
	double[][] buildings = new double[][] { { 1, 4, 4 }, { 2, 5, 3 }, { 3, 3, 6 }, { 5.5, 3, 5 }, { 10, 2, 2 },
		{ 11, 2, 3 } };
	// System.out.println(solution(buildings));

	System.out.println("############################### Test 2 ###################################");
	double[][] buildings1 = new double[][] { { 0, 1000, 10 }, { 1, 1, 1 }, { 2, 3, 42 }, { 2.01, 10.5, 11 },
		{ 8, 5, 5 }, { 900, 10, 1000.123 } };
	System.out.println(solution(buildings1));
    }

}
