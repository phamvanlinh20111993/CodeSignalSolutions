package CompanyChallenge;

import java.util.ArrayList;

public class Asana_MultiSelection {

	static float isCutLine(int y, int[][] mouseCoordinates) {
		int uY = mouseCoordinates[0][0] - mouseCoordinates[1][0],
				uX = -(mouseCoordinates[0][1] - mouseCoordinates[1][1]);

		return -(uY * (y - mouseCoordinates[0][1]) / (float) uX) + mouseCoordinates[0][0];
	}

	static boolean isBelongGroup(float myPoint, float a, float b) {
		return (myPoint >= a && myPoint <= b) || (myPoint >= b && myPoint <= a);
	}

	static String[] multiSelection(int[] dimensions, String[] tasks, int[][] mouseCoordinates) {
		ArrayList<String> res = new ArrayList<>();
		int startX = 0, endX = 0, lengthY = dimensions[0];
		for (int i = 0; i < tasks.length; i++) {
			startX += (i > 0 ? dimensions[1] : 0) + (i > 0 ? dimensions[2] : 0);
			endX = startX + dimensions[1];

			// System.out.println("(0, " + startX + ") --- (" + lengthY + ", " + endX +
			// ")");
			float isBelong1 = isCutLine(endX, mouseCoordinates), isBelong = isCutLine(startX, mouseCoordinates);

			// System.out.println("coordinate " + isBelong + " "
			// + isBelongGroup(startX, mouseCoordinates[1][1], mouseCoordinates[0][1]) + " "
			// + isBelongGroup(endX, mouseCoordinates[1][1], mouseCoordinates[0][1]) + " "
			// + isBelongGroup(isBelong, mouseCoordinates[1][0], mouseCoordinates[0][0]));

			if ((isBelongGroup(startX, mouseCoordinates[1][1], mouseCoordinates[0][1])
					&& isBelongGroup(isBelong, mouseCoordinates[1][0], mouseCoordinates[0][0]))
					|| (isBelongGroup(endX, mouseCoordinates[1][1], mouseCoordinates[0][1])
							&& isBelongGroup(isBelong1, mouseCoordinates[1][0], mouseCoordinates[0][0]))) {
				res.add(tasks[i]);
			} else if (isBelongGroup(mouseCoordinates[1][1], startX, endX)
					&& isBelongGroup(mouseCoordinates[0][1], startX, endX)
					&& (isBelongGroup(mouseCoordinates[1][0], 0, lengthY)
							|| isBelongGroup(mouseCoordinates[0][0], 0, lengthY))) {
				res.add(tasks[i]);
			}
		}

		System.out.println("result: ");
		for (String r : res) {
			System.out.println(r);
		}

		return res.toArray(new String[res.size()]);
	}

	public static void main(String[] args) {
		// ["Task 2", "Task 3", "Very Important Task", "Not So Important Task"]
		System.out.println("############## test 1 #############");
		int[] dimensions = { 135, 9, 1 };
		String[] tasks = { "Task 1", "Task 2", "Task 3", "Very Important Task", "Not So Important Task",
				"Yet Another Task", "The last task" };
		int[][] mouseCoordinates = { { 132, 42 }, { 35, 13 } };
		multiSelection(dimensions, tasks, mouseCoordinates);

		// ["one", "twoe", "three"]
		System.out.println("############## test 2 #############");
		int[] dimensions1 = { 200, 4, 2 };
		String[] tasks1 = { "one", "twoe", "three", "foure", "five", "sixe", "sevene" };
		int[][] mouseCoordinates1 = { { 170, 4 }, { 120, 12 } };
		multiSelection(dimensions1, tasks1, mouseCoordinates1);

		// []
		System.out.println("############## test 3 #############");
		int[] dimensions2 = { 10, 4, 4 };
		String[] tasks2 = { "empty", "test" };
		int[][] mouseCoordinates2 = { { 3, 5 }, { 8, 7 } };
		multiSelection(dimensions2, tasks2, mouseCoordinates2);

		// ["1", "2"]
		System.out.println("############## test 4 #############");
		int[] dimensions3 = { 200, 4, 8 };
		String[] tasks3 = { "1", "2", "3", "4", "5" };
		int[][] mouseCoordinates3 = { { 0, 12 }, { 199, 4 } };
		multiSelection(dimensions3, tasks3, mouseCoordinates3);

		// ["dlkfj", "dlfkje", "eitjd"]
		System.out.println("############## test 5 #############");
		int[] dimensions4 = { 30, 12, 3 };
		String[] tasks4 = { "dlkfj", "dlfkje", "eitjd", "lolol" };
		int[][] mouseCoordinates4 = { { 1, 0 }, { 15, 30 } };
		multiSelection(dimensions4, tasks4, mouseCoordinates4);

		// ["singletask"]
		System.out.println("############## test 6 #############");
		int[] dimensions5 = { 100500, 5, 1 };
		String[] tasks5 = { "singletask" };
		int[][] mouseCoordinates5 = { { 100, 4 }, { 300, 4 } };
		multiSelection(dimensions5, tasks5, mouseCoordinates5);
	}

}
