package Challenge;

import java.util.Arrays;

public class ArrayTriplets {

	static int arrayTriplets(int[] arr) {
		int count = 0;
		Arrays.sort(arr);
		for(int i = 0; i < arr.length-2; i++) {
			for(int j = i + 1; j < arr.length - 1; j++) {
				for(int k = j + 1; k < arr.length; k++) {
					if(arr[i] + arr[j] > arr[k]) {
						count++;
					}else break;
				}
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		
		System.out.println("############## test 1 #############");
		int[] arr = { 35, 37, 61, 100, 72, 58, 80, 69, 88, 81, 47, 75, 44, 93, 51, 49, 50, 70, 77, 28, 60, 43 };
		System.out.println(arrayTriplets(arr));
		
		System.out.println("############## test 2 #############");
		int[] arr1 = { 1, 2, 10, 5, 12, 8, 2 };
		System.out.println(arrayTriplets(arr1));
	}

}
