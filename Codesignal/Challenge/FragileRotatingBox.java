package Codesignal.Challenge;

public class FragileRotatingBox {
	
	static int fragileRotatingBox(String[] boxWeakness, String surfaceRoughness) {
		int sum = 0, n = 0, i;
		
		while (n < surfaceRoughness.length()) {
			
			for(i = 0; i < boxWeakness[0].length(); i++) {
				sum += Character.getNumericValue(surfaceRoughness.charAt(n++)) 
						* Character.getNumericValue(boxWeakness[boxWeakness.length-1].charAt(i));
				if(n == surfaceRoughness.length())
					return sum;
			}
			
			for(i = boxWeakness.length-1; i >= 0; i--) {
				sum += Character.getNumericValue(surfaceRoughness.charAt(n++)) 
						* Character.getNumericValue(boxWeakness[i].charAt(boxWeakness[0].length()-1));
				if(n == surfaceRoughness.length())
					return sum;
			}
			
			for(i = boxWeakness[0].length()-1; i >= 0; i--) {
				sum += Character.getNumericValue(surfaceRoughness.charAt(n++)) 
						* Character.getNumericValue(boxWeakness[0].charAt(i));
				if(n == surfaceRoughness.length())
					return sum;
			}
			
			for(i = 0; i < boxWeakness.length; i++) {
				sum += Character.getNumericValue(surfaceRoughness.charAt(n++)) 
						* Character.getNumericValue(boxWeakness[i].charAt(0));
				if(n == surfaceRoughness.length())
					return sum;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
//		String [] boxWeakness =  {"01",  "21", "10" };
//		String surfaceRoughness = "39513695380152438476";
		
		String [] boxWeakness =  {"213", "105" };
		String surfaceRoughness = "123012131013141296";
		
		System.out.println(fragileRotatingBox(boxWeakness, surfaceRoughness));
	}

}
