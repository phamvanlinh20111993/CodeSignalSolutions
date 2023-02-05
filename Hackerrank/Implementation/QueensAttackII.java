package Hackerrank.Implementation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/queens-attack-2/problem?isFullScreen=true
 *
 */
public class QueensAttackII {

	static final int[][] QUEEN_MOVES = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 }, { -1, 0 } };

	static boolean isInBoard(int x, int y, int bX, int bY) {
		return x >= 1 && y >= 1 && x <= bX && y <= bY;
	}

	public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
		int pos = 0;

		Set<String> obstaclesSet = obstacles.stream()
				.map(p -> String.valueOf(p.get(0)) + "-" + String.valueOf(p.get(1)))
				.collect(Collectors.toSet());

		for (int ind = 0; ind < QUEEN_MOVES.length; ind++) {
			int xT = QUEEN_MOVES[ind][0] + r_q;
			int yT = QUEEN_MOVES[ind][1] + c_q;

			while (isInBoard(xT, yT, n, n) && !obstaclesSet.contains(String.valueOf(xT) + "-" + String.valueOf(yT))) {
				xT += QUEEN_MOVES[ind][0];
				yT += QUEEN_MOVES[ind][1];
				pos++;
			}
		}

		return pos;

	}

	public static void main(String[] args) {

	}

}
