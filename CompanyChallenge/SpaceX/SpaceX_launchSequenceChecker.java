package CompanyChallenge.SpaceX;

import java.util.HashMap;

public class SpaceX_launchSequenceChecker {

	static boolean launchSequenceChecker(String [] systemNames , int [] stepNumbers ) {
		
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < systemNames.length; i++) {
			if(map.get(systemNames[i])== null) {
				map.put(systemNames[i], stepNumbers[i]);
			}else {
				if(stepNumbers[i] < map.get(systemNames[i]))
					return false;
				else 
					map.put(systemNames[i], stepNumbers[i]);
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		//test
		String [] systemNames = {"stage_1", "stage_2", "dragon", "stage_1", "stage_2", "dragon"} ;
		int [] stepNumbers = {1, 10, 11, 2, 12, 111};
		
		System.out.println(launchSequenceChecker(systemNames , stepNumbers ));
		
		String [] systemNames1 = {"stage_1", "stage_1", "stage_2", "dragon"} ;
		int [] stepNumbers1 = {2, 1, 12, 111};
		
		System.out.println(launchSequenceChecker(systemNames1 , stepNumbers1 ));
		
//		String [] systemNames2 = {"stage_1", "stage_2", "dragon", "stage_1", "stage_2", "dragon"} ;
//		int [] stepNumbers2 = {1, 10, 11, 2, 12, 111};
//		
//		System.out.println(launchSequenceChecker(systemNames2 , stepNumbers2 ));
	}

}
