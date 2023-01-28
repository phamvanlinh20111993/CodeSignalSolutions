package Codesignal.InterviewPractice;

public class NQueens {

	static int[][] mark, fillResult;

	static void Try(int n, int len) {
		for (int j = 0; j < len; j++) {
			if (mark[n][j] == 0) {
			//	System.out.println("next " + n + "   " + j);
				fillResult[n][0] = n+1;
				fillResult[n][1] = j+1;

				//hang ngang, hang doc
				for (int i = 0; i < len; i++) {
					if(i != j) mark[n][i]++;
					if(i != n) mark[i][j]++;
				}
				//duong cheo phai
				int p = n + 1;
				for (int k = j + 1; k < len; k++) {
					if (p < len)
						mark[p][k]++;
					else
						break;
					p++;
				}
				//duong cheo trai
				p = n + 1;
				for (int k = j - 1; k >= 0; k--) {
					if (p < len)
						mark[p][k]++;
					else
						break;
					p++;
				}
				
				//if(n == 0 && j == 0) {
//					for(int t = 0; t < len; t++) {
//						for(int m = 0; m < len; m++)
//							System.out.print(mark[t][m] + "   ");
//						System.out.println();
//					}
						
				//}
				

				if (n == len - 1) {
					for (int i = 0; i < len; i++) {
						System.out.print("(" + fillResult[i][0] + "  " + fillResult[i][1] + "), ");
					}
					System.out.println();
					
					//hang ngang, hang doc
					for (int i = 0; i < len; i++) {
						if(i != j) mark[n][i]--;
						if(i != n) mark[i][j]--;
					}
					//duong cheo phai
					p = n + 1;
					for (int k = j + 1; k < len; k++) {
						if (p < len)
							mark[p][k]--;
						else
							break;
						p++;
					}
					//duong cheo trai
					p = n + 1;
					for (int k = j - 1; k >= 0; k--) {
						if (p < len)
							mark[p][k]--;
						else
							break;
						p++;
					}
				}

				if (n < len - 1) {
					Try(n + 1, len);
					//quay lai trang thai truoc do
					//hang ngang, hang doc
					for (int i = 0; i < len; i++) {
						if(i != j) mark[n][i]--;
						if(i != n) mark[i][j]--;
					}
					//duong cheo phai
					p = n + 1;
					for (int k = j + 1; k < len; k++) {
						if (p < len)
							mark[p][k]--;
						else
							break;
						p++;
					}
					//duong cheo trai
					p = n + 1;
					for (int k = j - 1; k >= 0; k--) {
						if (p < len)
							mark[p][k]--;
						else
							break;
						p++;
					}
				}
			}
		}
	}

	static int[][] nQueens(int n) {
		mark = new int[n][n];
		fillResult = new int[n][2];
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				mark[i][j] = 0;
		}
		Try(0, n);
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("############## Test 1 ##############");
		nQueens(6);
	}

}
