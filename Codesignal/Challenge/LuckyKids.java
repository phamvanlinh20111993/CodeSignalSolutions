package Codesignal.Challenge;

import java.util.Arrays;
import java.util.Comparator;

public class LuckyKids {

	static int luckyKids(int[] behaviors) {
		int res = 1, i = 0;
		int [][] counts = new int [behaviors.length][2];
		
		for(i = 0; i < behaviors.length; i++) {
			counts[i][0] = behaviors[i];
			counts[i][1] = i;
		}
		
		Arrays.sort(counts, new Comparator<int []>() {
			public int compare(int[] o1, int[] o2) {
//				if(o1[0] == o2[0])
//					return o2[1] - o1[1];
//				else {
					return o1[0] - o2[0];
			//	}
			}
		});
		System.out.println("size " + counts.length);
		for(i = 0; i < counts.length; i++) {
			System.out.println(behaviors[i] + "  " + counts[i][0] + "  " + counts[i][1]);
		}
		
		
		for(i = 0; i < counts.length; i++) {
			
		}
		
		System.out.println("result " + res);

		return res;
	}

	public static void main(String[] args) {

		System.out.println("######### Test 1, output = 9 ##########");
		int[] behaviors = { 7, 11, 10, 9, 5, 7, 5, 7, 6, 6, 6, 2 };
		luckyKids(behaviors);

//		System.out.println("######### Test 2 ##########");
//		int[] behaviors1 = { 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1 };
//		luckyKids(behaviors1);

//		System.out.println("######### Test 3 ##########");
//		int[] behaviors2 = { 5, 8, 8, 17, 20, 2, 6, 4, 4, 15, 7, 9, 9, 9, 1, 6, 7, 2, 2, 8, 4, 1, 100, 33, 20, 8 };
//		luckyKids(behaviors2);
		
		System.out.println("######### Test 4, output = 2 ##########");
		int[] behaviors3 = { 3, 3, 4, 5, 2, 1, 5, 5 };
		luckyKids(behaviors3);
//		
//		System.out.println("######### Test 5 ##########");
//		int[] behaviors5 = { 7, 101, 100, 101, 101, 70, 70, 60, 5, 50, 40, 4, 40, 101, 100 };
//		luckyKids(behaviors5);
	}

}
