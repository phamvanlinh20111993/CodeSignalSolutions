package Challenge;

import java.util.ArrayList;

public class ChristmasLargeFamiliesGifts {

	static int christmasLargeFamiliesGifts(int[] family1, int[] family2) {
		int count = 0, i, countLeft = 0, countRight = 0, l = 0, r = 0;
		boolean isLess = false, notDup = true;
		ArrayList<Integer> left = new ArrayList<>(), right = new ArrayList<>();

		for (i = 0; i < family2.length; i++) {
			if (family1[i] > family2[i]) {
				countLeft++;
				if (countRight > 0 && notDup) {
					right.add(r++, countRight);
					countRight = 0;
					notDup = false;
				}
				isLess = true;
			}
			if (isLess && family1[i] < family2[i]) {
				if (countLeft > 0)
					left.add(l++, countLeft);
				countLeft = 0;
				countRight++;
				notDup = true;
			}
		}

		if (countRight > 0 && notDup)
			right.add(r++, countRight);
		
		int t = right.size() - 1 > 0 ? right.get(right.size() - 1) : 0;
		for(i = right.size() - 2; i >= 0; i--) {
			right.set(i, right.get(i) + t);
			t = right.get(i);
		}
		
		for (i = 0; i < right.size(); i++) {
			count += left.get(i) * right.get(i);
		}

		for (i = 0; i < left.size(); i++) {
			System.out.print(left.get(i) + " ");
		}
		System.out.println();
		for (i = 0; i < right.size(); i++) {
			System.out.print(right.get(i) + " ");
		}

		System.out.println("\n" + count);
		return count;
	}

	public static void main(String[] args) {

		System.out.println("############ test 0 ############");
		int[] family0 = { 1, 2, 2, 1, 3 }, family01 = { 2, 1, 1, 2, 3 };
		christmasLargeFamiliesGifts(family0, family01);

		System.out.println("############ test 1 ############");
		int[] family1 = { 17, 19, 8, 14, 5 }, family2 = { 15, 15, 20, 20, 3 };
		christmasLargeFamiliesGifts(family1, family2);

		System.out.println("############ test 2 ############");
		int[] family3 = { 17, 17, 17, 15, 15, 17, 17, 17, 15, 17, 17 },
				family4 = { 15, 15, 15, 17, 17, 15, 15, 15, 17, 15, 15 };
		christmasLargeFamiliesGifts(family3, family4);

		System.out.println("############ test 3 ############");
		int[] family5 = { 17, 15, 17, 15, 17, 15, 17, 15, 17, 15, 17 },
				family6 = { 15, 17, 15, 17, 15, 17, 15, 17, 15, 17, 15 };
		christmasLargeFamiliesGifts(family5, family6);

		System.out.println("############ test 4 ############");
		int[] family7 = { 6, 9, 8, 10, 3 }, family8 = { 1, 9, 4, 2, 4 };
		christmasLargeFamiliesGifts(family7, family8);

		System.out.println("############ test 5 ############");
		int[] family9 = { 17, 16, 13, 6, 18, 6 }, family10 = { 11, 1, 8, 7, 19, 19 };
		christmasLargeFamiliesGifts(family9, family10);

		System.out.println("############ test 6 ############");
		int[] family14 = { 9, 11, 5, 8, 9 }, family15 = { 1, 5, 17, 2, 16 };
		christmasLargeFamiliesGifts(family14, family15);
		
		System.out.println("############ test 7 ############");
//		int[] family17 = { 11, 11, 11, 15, 15, 11, 11, 15, 11, 11, 15, 15 }, 
//			  family16 = { 15, 15, 15, 11, 11, 15, 15, 11, 15, 15, 11, 11 };
		int[] family17 = { 11, 11, 11, 15, 15, 11, 11, 15, 11, 11}, 
 		      family16 = { 15, 15, 15, 11, 11, 15, 15, 11, 15, 15};
		christmasLargeFamiliesGifts(family16, family17);
		
		System.out.println("############ test 8 ############");
		int[] family18 = { 9, 11, 5, 8, 9 }, family19 = { 1, 5, 17, 2, 16};
		christmasLargeFamiliesGifts(family18, family19);

	}

}
