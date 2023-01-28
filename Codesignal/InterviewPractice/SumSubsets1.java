package Codesignal.InterviewPractice;

import java.util.ArrayList;
import java.util.HashMap;

public class SumSubsets1 {

	static int count = 0;
	static int[] res;
	static HashMap<String, String> checkResult;
	static ArrayList<String> totalRes;

	static void Try(int[] arr, int num, int sum, int pos) {
		for (int j = pos; j < arr.length; j++) {
			if (sum + arr[j] < num) {
				res[j] = arr[j];
				Try(arr, num, sum + arr[j], j + 1);
				res[j] = 0;
			} else if (sum + arr[j] == num) {
				count++;
				res[j] = arr[j];
				String s = "";
				for (int i = 0; i < arr.length; i++)
					if (res[i] > 0) {
						// System.out.print(res[i]+" ");
						s += res[i] + "-";
					}
				// System.out.println();
				checkResult.put(s, s);
				totalRes.add(s);
				
				res[j] = 0;
			}

		}
	}

	static int[][] sumSubsets(int[] arr, int num) {
		int[][] result;
		int i, j;
		res = new int[arr.length];
		checkResult = new HashMap<String, String>();
		totalRes = new ArrayList<>();
		count = 0;

		for (i = 0; i < arr.length; i++) {
			for (j = 0; j < arr.length; j++)
				res[j] = 0;
			res[i] = arr[i];
			if (arr[i] == num) {
				checkResult.put(Integer.toString(arr[i]), Integer.toString(arr[i]));
				totalRes.add(Integer.toString(arr[i]));
				count++;
			} else
				Try(arr, num, arr[i], i + 1);
		}

		result = new int[checkResult.size()][0];

		i = 0;
		for (String key : totalRes) {
			if (checkResult.get(key) != null) {
				System.out.println(key + "  ");			
				String[] tmp = key.split("-");
				result[i] = new int[tmp.length - 1];
				for (j = 0; j < tmp.length - 1; j++)
					result[i][j] = Integer.parseInt(tmp[j]);
				i++;
				checkResult.remove(key);
			}
		}
		System.out.println("total case " + count + ", real  " + checkResult.size());

		return result;
	}

	public static void main(String[] args) {

		System.out.println("############# test 0, num = 5 #############");
		int[] arr0 = { 1, 2, 2, 3, 4, 5 };
		int num0 = 5;
		sumSubsets(arr0, num0);

		System.out.println("############# test 1, num = 9 #############");
		int[] arr = { 1, 1, 2, 2, 2, 5, 5, 6, 8, 8 };
		int num = 9;
		sumSubsets(arr, num);

		System.out.println("############# test 2, num = 36 #############");
		int[] arr1 = { 1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20 };
		int num1 = 36;
		sumSubsets(arr1, num1);

		System.out.println("############# test 3, num = 5 #############");
		int[] arr2 = { 1, 2, 3, 4, 5 };
		int num2 = 5;
		sumSubsets(arr2, num2);

		// System.out.println("############# test 4, num = 120 #############");
		// int[] arr3 = { 1, 2, 3, 4, 5, 1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15,
		// 16, 16, 16, 19, 19, 20, 45, 56, 77,
		// 22, 43, 53, 23, 34, 23, 23, 21, 44, 46 };
		// int num3 = 120;
		// sumSubsets(arr3, num3);

	}

}
