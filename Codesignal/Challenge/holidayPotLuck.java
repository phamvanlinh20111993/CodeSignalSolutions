package Codesignal.Challenge;

import java.util.Arrays;
import java.util.Comparator;

public class holidayPotLuck {
	
	static int holidayPotluck(int[][] dishes, int capacity) {
	    
		Arrays.sort(dishes, new Comparator<int []>() {
			public int compare(int[] o1, int[] o2) {
				
				return o1[0] -  o2[0];
			}
		});
		
		int total = 0, index = 0;
		for(index = dishes.length - 1; index >= 0; index--) {
			if(capacity - dishes[index][1] > 0) {
				total += dishes[index][1] * dishes[index][0];
				capacity -= dishes[index][1];
			}else {
				total += capacity * dishes[index][1];
				break;
			}
		}
		
		return total;
	}
	
	public static void main(String[] args) {
			

	}

}
