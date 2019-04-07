package InterviewPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class CombinationSum {

	static int mySum, count;
	static TreeMap<Integer, String> saveNum;
	static HashMap<String, String> preventDuplicate;
	static ArrayList<String> allCase;

	static void Try(int[] a, int sum, int pos) {

		for (int i = pos; i < a.length; i++) {
			if (mySum + a[i] <= sum) {
				mySum += a[i];
				if (!saveNum.containsKey(a[i]))
					saveNum.put(a[i], Integer.toString(a[i]));
				else
					saveNum.put(a[i], saveNum.get(a[i]) + "-" + Integer.toString(a[i]));

				if (mySum == sum) {
					count++;
					String saveVal = "(";
					for (int key : saveNum.keySet()) {
						String[] tmp = saveNum.get(key).split("-");
						for (int m = 0; m < tmp.length; m++) {
						//	System.out.print(tmp[m] + " ");
							saveVal += tmp[m] + " ";
						}
						
					}
					saveVal = saveVal.substring(0, saveVal.length() - 1)+ ")";
					allCase.add(saveVal);
					preventDuplicate.put(saveVal, saveVal);
					//System.out.println();
				}
				if (mySum < sum)
					Try(a, sum, i);
				mySum -= a[i];
				if (saveNum.get(a[i]).length() <= 2)
					saveNum.remove(a[i]);
				else {
					String tmp = saveNum.get(a[i]);
					saveNum.put(a[i], tmp.substring(0, tmp.length() - 2));

				}

			}
		}
	}

	static String combinationSum(int[] a, int sum) {
		String result = "";
		int i;
		
		mySum = 0;
		saveNum = new TreeMap<Integer, String>();
		allCase = new ArrayList<>();
		preventDuplicate = new HashMap<>();
		count = 0;
		Arrays.sort(a);
		
		Try(a, sum, 0);
		
		for(i = 0; i < allCase.size(); i++) {
			if(preventDuplicate.containsKey(allCase.get(i))) {
				System.out.println(allCase.get(i));
				result += allCase.get(i);
				preventDuplicate.remove(allCase.get(i));
			}
		}
		
		System.out.println("total " + count);
		return result;
	}

	public static void main(String[] args) {

		System.out.println("################ test 1, sum = 9 ################");
		int[] a = { 2, 3, 5, 9 };
		int sum = 9;
		combinationSum(a, sum);
		
		System.out.println("################ test 2, sum = 16 ################");
		int[] a1 = { 1, 8 };
		int sum1 = 16;
		combinationSum(a1, sum1);

		System.out.println("################ test 3, sum = 8 ################");
		int[] a2 = { 2, 4, 6, 8 };
		int sum2 = 8;
		combinationSum(a2, sum2);

		System.out.println("################ test 4, sum = 18 ################");
		int[] a3 = { 6, 3, 7 };
		int sum3 = 18;
		combinationSum(a3, sum3);

		System.out.println("################ test 5, sum = 12 ################");
		int[] a4 = { 8, 1, 8, 6, 8 };
		int sum4 = 12;
		combinationSum(a4, sum4);

		 System.out.println("################ test 6 ################");
		 int[] a5 = { 7, 1, 3, 1, 3, 4, 7, 3, 9, 6 };
		 int sum5 = 23;
		 combinationSum(a5, sum5);
		
		System.out.println("################ test 7, sum = 13 ################");
		int[] a6 = { 7, 1, 9, 5, 1, 8, 5, 3 };
		int sum6 = 13;
		combinationSum(a6, sum6);
		
		System.out.println("################ test 8, sum = 7 ################");
		int[] a7 = { 4, 7, 7, 1, 3, 2, 8, 8, 9, 3 };
		int sum7 = 7;
		combinationSum(a7, sum7);

	}

}
