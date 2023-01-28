package Codesignal.Challenge;

import java.util.ArrayList;

public class Guno {

	static int Score(String s) {
		int score = 0;
		switch (s) {
		case "s1":
			score = 20;
			break;
		case "s2":
			score = 20;
			break;
		case "s3":
			score = 20;
			break;
		case "s4":
			score = 50;
			break;
		case "s5":
			score = 50;
			break;
		}

		return score;

	}

	static int[] guno(int playercount, String[] moves) {
		int[] result = new int[playercount];
		int i = 0, j = 0;
		boolean flag = true;

		for (; i < playercount; i++) {
			result[i] = 0;
		}

		for (i = 0; i < moves.length; i++) {
			if (j < 0)
				j = playercount + j;
			j %= playercount;

			result[j] += Score(moves[i]);
			if (moves[i].equals("s1")) {
				if (flag)
					j++;
				else
					j--;
				System.out.println("bug1 " + j);
			}

			if (moves[i].equals("s3")) {
				System.out.println("bug " + j);
				flag = !flag;
			}

			if (flag)
				j++;
			else
				j--;
		}

		for (i = 0; i < result.length; i++)
			System.out.println(result[i]);

		return result;
	}

	public static void main(String[] args) {

		/**
		 * test 1
		 */
		System.out.println("################################################");
		int playercount = 3;
		String[] moves = { "s2", "s3", "s3", "s1", "s4" };// 1 20, 2 20, 1 20, 2 20
		guno(playercount, moves);

		System.out.println("################################################");
		int playercount1 = 4;
		String[] moves1 = { "c", "s5", "c", "s4", "s1", "c", "s3", "s2" };
		guno(playercount1, moves1);

		System.out.println("$##################################################");
		int playercount2 = 3;
		String[] moves2 = { "s3", "s4" };
		guno(playercount2, moves2);

		System.out.println("###################################################");
		int playercount3 = 4;
		String[] moves3 = { "c", "s3", "s1", "s4" }; // 0 0, 1 20, 0 20
		guno(playercount3, moves3);

		ArrayList<Integer> boundaries = new ArrayList<Integer>();
		boundaries.add(0);
		boundaries.add(10);
		boundaries.add(50);
		boundaries.add(100);
		boundaries.add(120);
		System.out.println("######################################");
		final int INF = Integer.MAX_VALUE;
		boundaries.add(0, -INF);

		for (int i = 0; i < boundaries.size(); i++)
			System.out.print(boundaries.get(i) + "  ");

		int l = 0, y = 45;
		int r = boundaries.size() - 1;
		while (l + 1 < r) { // 0+1 <  5
			int mid = (l + r) / 2;//mid = 2;
			if ((boundaries.get(mid) + boundaries.get(mid+1)) < 2*y) {
				l = mid;//
			} else {
				r = mid;//
			}
		}
		
		System.out.println("\nresult " + l);

	}

}
