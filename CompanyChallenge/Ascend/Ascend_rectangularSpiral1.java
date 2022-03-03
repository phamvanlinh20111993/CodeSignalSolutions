package CompanyChallenge.Ascend;

import java.util.ArrayList;
import java.util.List;

public class Ascend_rectangularSpiral1 {

	static List<Point> paintEdgeOfSpiral(Point firstPoint, Point secondPoint, String type, boolean isChild) {
		List<Point> res = new ArrayList<Point>();

		if (type == "left") 
		{
			System.out.println("left");
			if (firstPoint.getX() == secondPoint.getX() || firstPoint.getY() == secondPoint.getY()) {
				res.add(new Point(firstPoint.getX(), firstPoint.getY() + 1));
				if(firstPoint.getX() != secondPoint.getX()) {
					res.add(new Point(firstPoint.getX(), firstPoint.getY()));
				}
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
			} else {
				res.add(new Point(firstPoint.getX(), firstPoint.getY() + (isChild ? 1 : 0)));
				res.add(new Point(firstPoint.getX(), secondPoint.getY()));
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
				res.add(new Point(secondPoint.getX(), firstPoint.getY()));
			}
		} else if (type == "right") 
		{
			System.out.println("right");
			if (firstPoint.getX() == secondPoint.getX() || firstPoint.getY() == secondPoint.getY()) {
				res.add(new Point(firstPoint.getX(), firstPoint.getY() - 1));
				if(firstPoint.getX() != secondPoint.getX()) {
					res.add(new Point(firstPoint.getX(), firstPoint.getY()));
				}
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
			} else {
				res.add(new Point(firstPoint.getX(), firstPoint.getY() + (isChild ? -1 : 0)));
				res.add(new Point(firstPoint.getX(), secondPoint.getY()));
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
				res.add(new Point(secondPoint.getX(), firstPoint.getY()));
			}
		} else if (type == "top") 
		{
			System.out.println("top");
			if (firstPoint.getX() == secondPoint.getX() || firstPoint.getY() == secondPoint.getY()) {
				res.add(new Point(firstPoint.getX() - 1, firstPoint.getY()));
				if(firstPoint.getY() != secondPoint.getY()) {
					res.add(new Point(firstPoint.getX(), firstPoint.getY()));
				}
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
			} else {
				res.add(new Point(firstPoint.getX() + (isChild ? -1 : 0), firstPoint.getY()));
				res.add(new Point(secondPoint.getX(), firstPoint.getY()));
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
				res.add(new Point(firstPoint.getX(), secondPoint.getY()));
			}
		} else {
			System.out.println("bottom");
			if (firstPoint.getX() == secondPoint.getX() || firstPoint.getY() == secondPoint.getY()) {
				res.add(new Point(firstPoint.getX() + 1, firstPoint.getY()));
				if(firstPoint.getY() != secondPoint.getY()) {
					res.add(new Point(firstPoint.getX(), firstPoint.getY()));
				}
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
			} else {
				res.add(new Point(firstPoint.getX() + (isChild ? 1 : 0), firstPoint.getY()));
				res.add(new Point(secondPoint.getX(), firstPoint.getY()));
				res.add(new Point(secondPoint.getX(), secondPoint.getY()));
				res.add(new Point(firstPoint.getX(), secondPoint.getY()));
			}
		}

		return res;
	}

	static int[][] rectangularSpiral(int[] firstPoint, int[] secondPoint) {

		if (firstPoint[1] == secondPoint[1] || firstPoint[0] == secondPoint[0]) {
			int[][] res = new int[2][2];
			res[0] = firstPoint;
			res[1] = secondPoint;
			System.out.println("( " + res[0][0] + " , " + res[0][1] + ")");
			System.out.println("( " + res[1][0] + " , " + res[1][1] + ")");

			return res;
		} else {
			List<Point> res = new ArrayList<>();
			Point A = new Point(firstPoint[0], firstPoint[1]), 
				  B = new Point(secondPoint[0], secondPoint[1]);
			boolean isChild = false;

			if (firstPoint[1] < secondPoint[1]) {
				if (firstPoint[0] > secondPoint[0]) {
					while (A.getX() >= B.getX() && A.getY() <= B.getY()) {
						res.addAll(paintEdgeOfSpiral(A, B, "right", isChild));
						A.setX(A.getX() - 1);
						A.setY(A.getY() + 1);
						B.setX(B.getX() + 1);
						B.setY(B.getY() - 1);
						isChild = true;
					}
				} else {
					while (A.getX() <= B.getX() && A.getY() <= B.getY()) {
						res.addAll(paintEdgeOfSpiral(A, B, "top", isChild));
						A.setX(A.getX() + 1);
						A.setY(A.getY() + 1);
						B.setX(B.getX() - 1);
						B.setY(B.getY() - 1);
						isChild = true;
					}
				}
			} else {
				if (firstPoint[0] > secondPoint[0]) {
					while (A.getX() >= B.getX() && A.getY() >= B.getY()) {
						res.addAll(paintEdgeOfSpiral(A, B, "bottom", isChild));
						A.setX(A.getX() - 1);
						A.setY(A.getY() - 1);
						B.setX(B.getX() + 1);
						B.setY(B.getY() + 1);
						isChild = true;
					}
				} else {
					while (A.getX() <= B.getX() && A.getY() >= B.getY()) {
						res.addAll(paintEdgeOfSpiral(A, B, "left", isChild));
						A.setX(A.getX() + 1);
						A.setY(A.getY() - 1);
						B.setX(B.getX() - 1);
						B.setY(B.getY() + 1);
						isChild = true;
					}
				}
			}

			int[][] r = new int[res.size()][2];
			int i = 0;
			for (Point p : res) {
				System.out.println("( " + res.get(i).getX() + " , " + res.get(i).getY() + ")");
				r[i][0] = p.getX();
				r[i++][1] = p.getY();
			}

			return r;
		}
	}

	public static void main(String[] args) {

		/**
		 * rectangularSpiral(firstPoint, secondPoint) = [[0, 0], [4, 0], [4, 3], [0, 3],
		 * [0, 1], [3, 1], [3, 2], [1, 2]]
		 */
		System.out.println("############## test 1 ##########");
		int[] firstPoint = { 0, 0 }, secondPoint = { 4, 3 };
		rectangularSpiral(firstPoint, secondPoint);

		/**
		 * rectangularSpiral(firstPoint, secondPoint) = [[1, 3], [1, 1], [3, 1], [3, 3],
		 * [2, 3], [2, 2]]
		 */
		System.out.println("############## test 2 ##########");
		int[] firstPoint1 = { 1, 3 }, secondPoint1 = { 3, 1 };
		rectangularSpiral(firstPoint1, secondPoint1);

		/**
		 * [[9,1], [0,1], [0,0], [9,0]]
		 */
		System.out.println("############## test 3 ##########");
		int[] firstPoint2 = { 9, 1 }, secondPoint2 = { 0, 0 };
		rectangularSpiral(firstPoint2, secondPoint2);
		/**
		 * [[7,3], [1,3], [1,2], [7,2]]
		 */
		System.out.println("############## test 4 ##########");
		int[] firstPoint3 = { 7, 3 }, secondPoint3 = { 1, 2 };
		rectangularSpiral(firstPoint3, secondPoint3);

		/**
		 * [[2,0], [2,5], [0,5], [0,0], [1,0], [1,4]]
		 */
		System.out.println("############## test 5 ##########");
		int[] firstPoint5 = { 2, 0 }, secondPoint5 = { 0, 5 };
		rectangularSpiral(firstPoint5, secondPoint5);

		/**
		 * 
		 */
		System.out.println("############## test 6 ##########");
		int[] firstPoint6 = { 0, 0 }, secondPoint6 = { 2, 3 };
		rectangularSpiral(firstPoint6, secondPoint6);
	}
}
