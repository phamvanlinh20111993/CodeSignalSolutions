package Codesignal.InterviewPractice;

import java.util.HashMap;

public class AreFollowingPattern {
	
	
	static boolean areFollowingPatterns(String[] strings, String[] patterns) {
		
		HashMap<String, String> map = new HashMap<>();
		int i = 0;
		
		for(; i < strings.length; i++) {
			if(map.get(strings[i]) == null) {
				map.put(strings[i], patterns[i]);
			}else {
				if(map.get(strings[i]).equals(patterns[i])) {
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	
	public static void main(String[] args) {
		

	}

}
