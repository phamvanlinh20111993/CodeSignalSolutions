package CompanyChallenge;

public class Mz_questEfficiencyItem {

	static int pointRes = 0;
	static int [] mark; 
	static void countSum(int start, int[] h, int[] points, int timeForQuests, int sum, int point) {

		for (int i = start; i < h.length; i++) {
			if (mark[i] == 0 && sum + h[i] <= timeForQuests) {
				sum += h[i];
				point += points[i];
				mark[i] = 1;
				//System.out.println("point " + point);
				if (pointRes < point) {
					pointRes = point;
				}
				countSum(start + 1, h, points, timeForQuests, sum, point);
				sum -= h[i];
				mark[i] = 0;
				point -= points[i];
			}
		}
	}

	static int questEfficiencyItem(int[] h, int[] points, int timeForQuests) {

		pointRes = 0;
		mark = new int[h.length];
		
//		int i, j, sum = 0;
//		for(i = 0; i < h.length; i++) {
//			mark[i] = 0;
//			for(j = i+1; j < h.length; j++ ) {
//				if(h[i] > h[j]) {
//					int tmp = h[i];
//					h[i] = h[j];
//					h[j] = tmp;
//					
//					tmp = points[i];
//					points[i] = points[j];
//					points[j] = tmp;
//				}
//			}
//		}
//		
//		for(i = 0; i < h.length; i++) {
//			sum += points[i];
//			System.out.print(h[i] + " ");
//		}
//		System.out.println("sum " + sum);
//		for(i = 0; i < points.length; i++) {
//			System.out.print(points[i] + " ");
//		}
//		System.out.println();
//	
		//backtracking...
		countSum(0, h, points, timeForQuests, 0, 0);
		System.out.println("Point is: " + pointRes);

		return pointRes;
	}

	public static void main(String[] args) {

		System.out.println("####################test 1 #####################");
		int[] h = { 1, 4, 2 }, points = { 2, 3, 2 };
		int timeForQuests = 4;
		questEfficiencyItem(h, points, timeForQuests);

		System.out.println("####################test 2 #####################");
		int[] h1 = { 1, 4, 2 }, points1 = { 2, 5, 2 };
		int timeForQuests1 = 4;
		questEfficiencyItem(h1, points1, timeForQuests1);

		System.out.println("####################test 3 #####################");
		int[] h2 = { 1, 4, 2, 6, 3, 2, 2, 4, 3, 4, 1 }, 
			  points2 = { 2, 5, 2, 3, 1, 5, 4, 7, 2, 3, 3 };
		int timeForQuests2 = 90;
		questEfficiencyItem(h2, points2, timeForQuests2);

		System.out.println("####################test 4 #####################");
		int[] h3 =      { 2, 5, 3, 8, 12, 2, 3, 1, 5, 4, 2, 10, 9, 6 },
			  points3 = { 12, 24, 32, 1, 2, 21, 18, 17, 5, 6, 8, 10, 11, 14 };
		int timeForQuests3 = 41;
		questEfficiencyItem(h3, points3, timeForQuests3);

		System.out.println("####################test 4 #####################");
		int[] h4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
				points4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		int timeForQuests4 = 30;
		questEfficiencyItem(h4, points4, timeForQuests4);
		
		System.out.println("####################test 5 #####################");
		int[] h5 = { 5 },
				points5 = { 100 };
		int timeForQuests5 = 4;
		questEfficiencyItem(h5, points5, timeForQuests5);

	}

}
