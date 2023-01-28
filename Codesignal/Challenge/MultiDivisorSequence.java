package Codesignal.Challenge;

public class MultiDivisorSequence {
	
	
	static int multiDivisorSequence(int[] nums) {
		for(int i = 1; i < nums.length; i++) {
			if(nums[i-1] % nums[i] != 0 && (i > 1 && nums[i] % nums[i-2] != 0))
					return nums[i];
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		System.out.println("################ Test 1 ################");
		int [] nums = {3, 1, 6, 2, 42, 21, 7};
		System.out.println(multiDivisorSequence(nums));
		
		System.out.println("################ Test 2 ################");
		int [] nums1 = {1, 1, 11, 11, 1, 75, 15, 5, 5, 93, 31, 87, 310, 97, 47};
		System.out.println(multiDivisorSequence(nums1));
	}

}
