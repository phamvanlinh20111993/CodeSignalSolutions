package CompanyChallenge.Asana;

import java.util.HashMap;

public class TaskType {

	public static int [] taskTypes(int [] deadlines, int day) {
		
		int [] Out = {0, 0, 0};
		int i, max = deadlines[0], min =  deadlines[0];
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(i = 0; i < deadlines.length; i++) {
			if(max < deadlines[i]) 
				max = deadlines[i];
			if(min > deadlines[i]) 
				min = deadlines[i];
			map.put(deadlines[i],
					map.get(deadlines[i]) == null? 1 : map.get(deadlines[i])+1);
		}

		for(i = min; i <= max; i++) {
			if(i <= day) {
			    System.out.println(i+ " " + map.get(i));
				Out[0] += map.get(i) == null ? 0 :  map.get(i);
			}else if(i <= day + 7)
				Out[1] += map.get(i) == null ? 0 :  map.get(i);
			else
				Out[2] += map.get(i) == null ? 0 :  map.get(i);
		}
		System.out.println(map.toString());
		System.out.print(Out[0] + "  " + Out[1] + "  " + Out[2] + "\n");
		return Out;
	}
	
	static String smartAssigning(String[] names, boolean [] statuses, int [] projects, int [] tasks) {
		
		
		if(names.length == 0)
			return null;
		
		int i = 0, min = Integer.MAX_VALUE, count = 0, pos = 0;
		
		for( ; i < statuses.length; i++) {
			if(!statuses[i] && min > tasks[i]) {
				min = tasks[i];
				pos = i;
			}
		}
		
		//check min task
	   for(i = 0; i < statuses.length; i++) {
		   if(!statuses[i] && min == tasks[i])
			   count++;
	   }
	   
	   if(count != 1) {
		   int min1 = Integer.MAX_VALUE;
		   for(i = 0; i < statuses.length; i++) {
			   if(!statuses[i] && min == tasks[i] && min1 > projects[i]) {
				   min1 = projects[i];
				   pos = i;
			   }
		   }
	   }
		
	   return names[pos];
	}
	
	public static void main(String[] args) {
		
		int [] deadlines = {1,2,4,2,10,3,1,4,5,4,9,8};
		int day = 1;
		taskTypes(deadlines, day);
		
		//
		int [] deadlines1 = {1, 2, 3, 4, 5};
		int day1 = 2;
		taskTypes(deadlines1, day1);
		
		
		//test case
		String [] names =  {"John", "Martin"};
		boolean [] statuses = {false, false};
		int [] projects = {2, 1}; 
		int [] tasks = {16, 5};
		System.out.println(smartAssigning(names, statuses, projects, tasks));
		
		//test case
		String [] names1 =  {"John", "Martin"};
		boolean [] statuses1 = {false, true};
		int [] projects1 = {2, 1}; 
		int [] tasks1 = {6, 5};
		System.out.println(smartAssigning(names1, statuses1, projects1, tasks1));
		
		//test case
		String [] names2 =  {"John", "Martin"};
		boolean [] statuses2 = {false, true};
		int [] projects2 = {1, 2}; 
		int [] tasks2 = {6, 6};
		System.out.println(smartAssigning(names2, statuses2, projects2, tasks2));
		
		//test case
		String [] names3 =  {"John", "Martin", "Luke"};
		boolean [] statuses3 = {false, true, false};
		int [] projects3 = {1, 0, 2}; 
		int [] tasks3 = {2, 0, 1};
		System.out.println(smartAssigning(names3, statuses3, projects3, tasks3));
		
		//test case
		String [] names4 =  {};
		boolean [] statuses4 = {};
		int [] projects4 = {}; 
		int [] tasks4 = {};
		System.out.println(smartAssigning(names4, statuses4, projects4, tasks4));
	}

}
