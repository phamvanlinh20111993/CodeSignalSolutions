package Codesignal.InterviewPractice.BackTracking;
import java.util.TreeMap;

public class WordBoggle {

	static int[] pX = { -1, -1, 0, 1, 1, 1, 0, -1 }, pY = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static TreeMap<String, String> result = new TreeMap<>();
	static int[][] mark;

	public static void wordBoggle(String word, String[][] board, int pos, int i, int j) {

		for (int k = 0; k < 8; k++) {
			int tempX = i + pX[k], tempY = j + pY[k];

			if (tempX >= 0 && tempX < board.length && tempY >= 0 && tempY < board[0].length) {
				System.out.println(pos + "  " + tempX + "  " + tempY);
				if (pos == word.length()) {
					result.put(word, word);
					return;
				} else if (Character.toString(word.charAt(pos)).equals(board[tempX][tempY])
						&& mark[tempX][tempY] == 0) {
					mark[tempX][tempY] = 1;
					wordBoggle(word, board, pos + 1, tempX, tempY);
					mark[tempX][tempY] = 0;
				}
			}

		}
	}

	public static void main(String[] args) {
		// test case
		// String[][] board = { { "R", "L", "D" }, { "U", "O", "E" }, { "C", "S", "O" }
		// };
		// String[] words = { "CODE", "SOLO", "RULES", "COOL" };

		// String[][] board = { { "S", "A" }, { "M", "O" }, { "W", "E" }, { "H", "R" }
		// };
		// String[] words = { "SOME", "DRONE", "WHERE", "SOMEWHERE", "WORD", "WE",
		// "MORE" };

		String[][] board = { { "A", "X", "V", "W" }, { "A", "L", "T", "I" }, { "T", "T", "J", "R" } };
		String[] words = { "AXOLOTL", "TAXA", "ABA", "VITA", "VITTA", "GO", "AXAL", "LATTE", "TALA", "RJ" };

		int i, j, k, l;

		mark = new int[board.length][board[0].length];
		for (i = 0; i < board.length; i++)
			for (j = 0; j < board[0].length; j++)
				mark[i][j] = 0;

		for (i = 0; i < words.length; i++) {
			for (j = 0; j < board.length; j++) {
				for (k = 0; k < board[0].length; k++) {
					if ( !result.containsKey(words[i])){ 
						if(Character.toString(words[i].charAt(0)).equals(board[j][k]))
							wordBoggle(words[i], board, 1, j, k);
					} else  break;
				}

				for (k = 0; k < board.length; k++)
					for (l = 0; l < board[0].length; l++)
						mark[k][l] = 0;

			}
		}

		System.out.println("############# result ##############");
		
		for (String key : result.keySet()) {
			System.out.println(key);
		}
		
		System.out.println(result.keySet().toArray());
	}

}
