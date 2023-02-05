package Hackerrank.Recursion;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/simplified-chess-engine/problem?isFullScreen=true
 * 
 */
public class SimplifiedChessEngine {

	static final int[][] QUEEN_MOVES = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 }, { -1, 0 } };

	static final int[][] KNIGHT_MOVES = new int[][] { { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 },
			{ -1, 2 }, { -2, 1 } };

	static final int[][] ROOK_MOVES = new int[][] { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	static final int[][] BISHOP_MOVES = new int[][] { { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 } };

	static boolean isInBoard(int x, int y, int bX, int bY) {
		return x >= 0 && y >= 0 && x < bX && y < bY;
	}

	static String simplifiedChessEngine(char[][] whites, char[][] blacks, int moves) {
		return "true";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
