package Codesignal.InterviewPractice;

import java.util.ArrayList;
import java.util.HashMap;

public class SumSubsets {

	static int sum = 0, count = 0;
	static int[] mark, res;
	static HashMap<String, String> checkResult;
	static ArrayList<String> totalRes;

	static void Try(int[] arr, int num, int pos) {

		for (int j = pos; j < arr.length; j++) {
			if (mark[j] == 0 && sum + arr[j] <= num) {
				sum += arr[j];
				mark[j] = 1;
				res[j] = arr[j];

				if (sum == num) {
					count++;
					String s = "";
					for (int i = 0; i < arr.length; i++)
						if (res[i] > 0)
							s += res[i] + "-";
					
					checkResult.put(s, s);
					totalRes.add(s);
				} else if (sum < num) {
					Try(arr, num, j+1);
				}

				sum -= arr[j];
				mark[j] = 0;
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
		mark = new int[arr.length];
		res = new int[arr.length];
	
		for (i = 0; i < arr.length; i++)
			mark[i] = res[i] = 0;

		Try(arr, num, 0);
		
		
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
		System.out.println("count " + count);

		return result;
	}

	public static void main(String[] args) {
		System.out.println("############# test 1, num = 5 #############");
		int[] arr = { 1, 2, 2, 3, 4, 5 };
		int num = 5;
		sumSubsets(arr, num);
		
		System.out.println("############# test 2, num = 9 #############");
		int[] arr1 = { 1, 1, 2, 2, 2, 5, 5, 6, 8, 8};
		int num1 = 9;
		sumSubsets(arr1, num1);
		
		System.out.println("############# test 3, num = 36 #############");
		int[] arr2 = { 1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20};
		int num2 = 36;
		sumSubsets(arr2, num2);
		
		System.out.println("############# test 4, num = 5 #############");
		int[] arr3 = { 1, 2, 3, 4, 5 };
		int num3 = 5;
		sumSubsets(arr3, num3);
		
		System.out.println("############# test 4, num = 120 #############");
		int[] arr4 = { 1, 2, 3, 4, 5, 1, 1, 2, 4, 4, 4, 7, 9, 9, 13, 13, 13, 15, 15, 16, 16, 16, 19, 19, 20, 45, 56, 77,
				22, 43, 53, 23, 34, 23, 23, 21, 44, 46 };
		int num4 = 120;
		sumSubsets(arr4, num4);
	}

}
