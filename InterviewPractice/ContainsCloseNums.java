package InterviewPractice;

import java.util.HashMap;

public class ContainsCloseNums {

	static boolean containsCloseNums(int[] nums, int k) {

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(nums[i]) == null) {
				map.put(nums[i], i);
			} else {
				if (Math.abs(i - map.get(nums[i])) > k)
					return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {

		int[] nums = { 0, 1, 2, 3, 5, 2 };
		int k = 3;
		System.out.println(containsCloseNums(nums, k));

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		int result = 0, i = 0, j = 0;
		String s = "ABC", t = "DEF";
		
		for (; i < s.length(); i++) {
			if (map.get(s.charAt(i)) == null) {
				map.put(s.charAt(i), 1);
			}

			map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
		}

		for (i = 0; i < t.length(); i++) {
			if (map.get(t.charAt(i)) == null) {
				map.put(t.charAt(i), 1);
			} else {
				map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
			}
		}

		for (Character key : map.keySet()) {
			if (map.get(key) > 0)
				result += map.get(key);
		}


	}

}
