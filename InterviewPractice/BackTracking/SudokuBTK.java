package InterviewPractice.BackTracking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SudokuBTK {

	// static int[][] sudoku_Quiz = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
	// { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
	// { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
	// { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
	// { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
	// { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
	// { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
	// { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
	// { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
	//
	// static int[][] sudoku_Mark = { { -1, -1, 0, 0, -1, 0, 0, 0, 0 },
	// { -1, 0, 0, -1, -1, -1, 0, 0, 0 },
	// { 0, -1, -1, 0, 0, 0, 0, -1, 0 },
	// { -1, 0, 0, 0, -1, 0, 0, 0, -1 },
	// { -1, 0, 0, -1, 0, -1, 0, 0, -1 },
	// { -1, 0, 0, 0, -1, 0, 0, 0, -1 },
	// { 0, -1, 0, 0, 0, 0, -1, -1, 0 },
	// { 0, 0, 0, -1, -1, -1, 0, 0, -1 },
	// { 0, 0, 0, 0, -1, 0, 0, -1, -1 } };

	static int[][] sudoku_Quiz = { { 0, 0, 5, 3, 0, 0, 0, 0, 0 }, { 8, 0, 0, 0, 0, 0, 0, 2, 0 },
			{ 0, 7, 0, 0, 1, 0, 5, 0, 0 }, { 4, 0, 0, 0, 0, 5, 3, 0, 0 }, { 0, 1, 0, 0, 7, 0, 0, 0, 6 },
			{ 0, 0, 3, 2, 0, 0, 0, 8, 0 }, { 0, 6, 0, 5, 0, 0, 0, 0, 9 }, { 0, 0, 4, 0, 0, 0, 0, 3, 0 },
			{ 0, 0, 0, 0, 0, 9, 7, 0, 0 } };

	static int[][] sudoku_Mark = { { 0, 0, -1, -1, 0, 0, 0, 0, 0 }, { -1, 0, 0, 0, 0, 0, 0, -1, 0 },
			{ 0, -1, 0, 0, -1, 0, -1, 0, 0 }, { -1, 0, 0, 0, 0, -1, -1, 0, 0 }, { 0, -1, 0, 0, -1, 0, 0, 0, -1 },
			{ 0, 0, -1, -1, 0, 0, 0, -1, 0 }, { 0, -1, 0, -1, 0, 0, 0, 0, -1 }, { 0, 0, -1, 0, 0, 0, 0, -1, 0 },
			{ 0, 0, 0, 0, 0, -1, -1, 0, 0 } };

	public static void sudokuSolution(int i, int j) {

		for (int k = 1; k <= 9; k++) {// cac gia tri co the nhan tu 1 den 9

			boolean flag = false;
			// kiem tra theo hang
			for (int l = 0; l < 9; l++) {
				// if(l == i ) continue;
				if (sudoku_Quiz[i][l] == k) {
					flag = true;
					break;
				}
			}
			// kiem tra theo cot
			for (int l = 0; l < 9; l++) {
				// if(l == j) continue;
				if (sudoku_Quiz[l][j] == k) {
					flag = true;
					break;
				}
			}
			// kiem tra theo 9 o vuong
			int pos_x = 3 * (i / 3), pos_y = 3 * (j / 3);
			for (int x = pos_x; x <= pos_x + 2; x++) {
				for (int y = pos_y; y <= pos_y + 2; y++) {
					if (sudoku_Quiz[x][y] == k) {
						flag = true;
						break;
					}
				}
			}

			if (flag || sudoku_Mark[i][j] == -2)
				continue;
			else {
				sudoku_Quiz[i][j] = k;
				sudoku_Mark[i][j] = -2;

				int m, n;
				m = j + 1;
				n = i;
				while (n < 8) {
					if (m > 8) {
						m = 0;
						n++;
					}
					if (sudoku_Mark[n][m] == -1 || sudoku_Mark[n][m] == -2) {
						m++;
					} else
						break;
				}

				if (n == 8 && m <= 8 && (sudoku_Mark[n][m] == -1 || sudoku_Mark[n][m] == -2)) {
					while (m <= 8 && (sudoku_Mark[n][m] == -1 || sudoku_Mark[n][m] == -2))
						m++;
				}

				if ((n == 8 && m > 8) || sudoku_Quiz[n][m] != 0) {
					for (int t = 0; t < 9; t++) {
						for (int l = 0; l < 9; l++)
							System.out.print(sudoku_Quiz[t][l] + "  ");
						System.out.println();
					}
				} else {
					sudokuSolution(n, m);
				}
			}

			sudoku_Mark[i][j] = 0;// recover state
			sudoku_Quiz[i][j] = 0;// recover state

		}
	}

	public static void main(String[] args) {
		
		File currentDirFile = new File("");
		String helper = currentDirFile.getAbsolutePath();
		FileReader inputFil;

		int i, j, col = 0, row = 0;
		boolean flag = true;

		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				sudoku_Quiz[i][j] = 0;
				sudoku_Mark[i][j] = 0;
			}
		}

		try {
			inputFil = new FileReader(helper + "\\src\\BackTracking\\sudoku_input.txt");
			BufferedReader in = new BufferedReader(inputFil);
			String s = in.readLine();
			i = 0;
			while (s != null) {
				String[] tmp = s.split(" ");
				for (j = 0; j < tmp.length; j++) {
					sudoku_Quiz[i][j] = Integer.parseInt(tmp[j]);
					if (sudoku_Quiz[i][j] != 0) {
						sudoku_Mark[i][j] = -1;
					}

					if (flag && sudoku_Quiz[i][j] == 0) {
						flag = false;
						col = j;
						row = i;
					}
				}
				i++;
				s = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("####################### Input #######################");
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++)
				System.out.print(sudoku_Quiz[i][j] + "  ");
			System.out.println();
		}
		
		System.out.println("\n####################### Output ########################");
		sudokuSolution(row, col);
	}

}
