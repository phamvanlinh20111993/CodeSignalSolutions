package Codesignal.Challenge;

import java.util.TreeMap;

public class ChristmasSquareBoxs {
	static int christmasSquareBoxes(int[] boxes) {
		System.out.println(boxes.length);
		int total = 0, i = 0;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (i = 0; i < boxes.length; i++) {
			if (map.get(boxes[i]) == null) {
				map.put(boxes[i], 1);
			} else {
				map.put(boxes[i], map.get(boxes[i]) + 1);
			}
		}

		while (true) {

			for (Integer key : map.keySet()) {
				if (map.get(key) > 0) {
					map.put(key, map.get(key) - 1);
				}
			}
			total++;
			boolean isBreak = true;
			for (Integer key : map.keySet()) {
				if (map.get(key) > 0) {
					isBreak = false;
				}
			}
			if (isBreak)
				break;

		}

		System.out.println(total);

		return total;
	}

	public static void main(String[] args) {
		System.out.println("############## Test 1 ############");
		int[] boxes = { 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15,
				20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15,
				180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1,
				6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15,
				7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4,
				20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15,
				180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1,
				6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15,
				7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4,
				20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15,
				20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20,
				2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15,
				20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15,
				180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1,
				6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15,
				7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4,
				20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20, 2, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15,
				180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1,
				6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15,
				7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4,
				20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15,
				20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000,
				12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10,
				5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7,
				17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202,
				16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17,
				11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15,
				19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8,
				2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7,
				1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14,
				1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1,
				15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16,
				4, 20, 13, 202, 16, 3, 15, 20, 15, 10, 9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9,
				15, 20, 18, 17, 11, 12, 2, 2, 5, 13, 10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2,
				11000000, 7, 15, 19, 9, 2, 18, 15, 2, 3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6,
				17, 13, 18, 11, 8, 2, 15, 14, 16, 17, 18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 202, 16, 3, 15, 20, 15, 10,
				9, 7, 10, 130000, 7, 1, 15, 180000000, 12, 10, 4, 19, 11, 11, 9, 15, 20, 18, 17, 11, 12, 2, 2, 5, 13,
				10, 2, 10, 17, 12, 14, 1, 1, 6, 15, 10, 5, 7, 17, 19, 1, 15, 2, 2, 11000000, 7, 15, 19, 9, 2, 18, 15, 2,
				3, 10, 9, 20, 2, 3, 1, 15, 7, 7, 9, 7, 17, 4, 10, 1, 16, 19, 6, 17, 13, 18, 11, 8, 2, 15, 14, 16, 17,
				18, 20, 7, 7, 12, 20, 16, 4, 20, 13, 20};
		christmasSquareBoxes(boxes);

		System.out.println("############## Test 2 ############");
		int[] boxes1 = { 4, 5, 7, 5, 10, 9, 10, 6, 2, 9 };
		christmasSquareBoxes(boxes1);
	}

}