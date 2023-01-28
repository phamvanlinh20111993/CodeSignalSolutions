package Codesignal.InterviewPractice;

import java.util.ArrayList;

public class ClimbingStaircase {
	
	
	static int [] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	static ArrayList<Integer []> arr1;
	
	static void Try(int n, int k, int sum, int pos) {
		
		for(int i = 1; i <= k; i++ ) {
			sum += i;
			arr[pos] = i;
			
			if(sum < n) {
				Try(n, k, sum, pos+1);
				sum -= i;
			}else if(sum == n) {
				Integer [] tmp = new Integer[pos+1];
				for(int j = 0; j <= pos; j++) {
					tmp[j] = arr[j];
					System.out.print(arr[j] + "  ");
				}
				arr1.add(tmp);
				System.out.println();
			}
		}
	}
	
	static int[][] climbingStaircase(int n, int k) {
		Try( n, k, 0, 0);
		
		int[][] result = new int[arr1.size()][];
		
		for(int i = 0; i < arr1.size(); i++) {
			result[i] = new int[arr1.get(i).length];
			for(int j = 0; j < arr1.get(i).length; j++) {
				result[i][j] = arr1.get(i)[j];
			}
		}
		
		return result;
	}

	
	public static void main(String[] args) {
		arr1 = new ArrayList<>();
		
		System.out.println("test 1");
		climbingStaircase(7, 3);
		System.out.println("test 2");
		climbingStaircase(4, 2);
		System.out.println("test 3");
		climbingStaircase(1, 1);
		System.out.println("test 4");
		climbingStaircase(10, 10);
		System.out.println("test 5");
		climbingStaircase(0, 0);
	}

}
