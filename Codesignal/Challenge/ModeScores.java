package Codesignal.Challenge;

import java.util.HashMap;

public class ModeScores {

	static int[] modeScores(int[] scores) {
		int[] res = new int[scores.length], duplicate = {scores[0], 0};
		res[0] = scores[0];
		int max = res[0], i;
		
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(res[0], 0);
		for(i = 1; i < res.length; i++) {
			if(map.get(scores[i]) != null) map.put(scores[i], map.get(scores[i])+1);
			else  map.put(scores[i], 0);
			
			if(map.get(scores[i]) != null) {
				//System.out.println("val "+ map.get(scores[i]) + "  " + scores[i]);
				if(map.get(scores[i]) > duplicate[1]) {
					duplicate[1] = map.get(scores[i]);
					duplicate[0] = scores[i];
					res[i] = scores[i];
				}else if(map.get(scores[i]) == duplicate[1]) {
				//	System.out.println("val "+ i + "   " +scores[i] +"  " + duplicate[0]);
					if(scores[i] > duplicate[0]) {
						res[i] = scores[i];
						duplicate[0] = scores[i];
					}else {
						res[i] = duplicate[0];
					}
				}else {
					res[i] = duplicate[0];
				}
			}else {
				if(max < scores[i]) { 
					res[i] = scores[i];
					max = scores[i];
				}else res[i] = max;
			}
		}
		
		System.out.println(duplicate[0] + "   " + duplicate[1]);
		for(i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
		
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] scores = { 67, 68, 69, 70, 73, 72, 71, 74, 78, 76, 77, 75, 79, 80, 81, 82, 83, 84, 89, 86, 87, 88, 92, 90,
				85, 91, 93, 94, 95, 96, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 79 };
		
		System.out.println("#################### test 1 ########################");
		modeScores(scores);
		
		System.out.println("#################### test 2 #############################");
		int[] scores1 =  {76, 75, 77, 78, 79, 80, 81, 87, 93, 84, 88, 83, 82};
		modeScores(scores1);
	}

}
