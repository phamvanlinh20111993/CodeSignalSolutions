package Codesignal.CompanyChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Author Linh Pham Van
public class EstimateTime {
	
	public static int estimateTime(int workingHours, int [] tasks) {
		int count = 0, temp = 0, sum = 0, i = 0, n1;
		
		List<Integer> list = new ArrayList<>(tasks.length);
		for (int j : tasks) { 
			if(j > workingHours) return -1;
			list.add(Integer.valueOf(j));
			sum += j;
		}
		
		System.out.println("tong " + sum + " So ngay " + ((float)sum/workingHours));
		Collections.sort(list);	
		
		return count;
	}
	public static void main(String[] args) {
		
		//tests
		int [] tasks = {2,2,2};
		System.out.println(estimateTime(3, tasks));
		
		int [] tasks1 = {1,2,1};
		System.out.println("kq " + estimateTime(2, tasks1));
		
		int [] tasks2 = {1,1,3};
		System.out.println("kq " +estimateTime(3, tasks2));
		
		int [] tasks3 = {1,1,3};
		System.out.println("kq " +estimateTime(2, tasks2));
		
		int [] tasks4 = {4, 15, 6, 3, 13, 4, 7, 14, 8, 15, 5, 8};
		System.out.println("kq " +estimateTime(15, tasks4));
		
		int [] tasks5 = {1,4,5,3};
		System.out.println("kq " +estimateTime(7, tasks5));
		
		int [] tasks6 = {2, 3, 3, 3, 3, 5, 7};
		System.out.println("kq " +estimateTime(13, tasks6));
		
		
		
	}

}
