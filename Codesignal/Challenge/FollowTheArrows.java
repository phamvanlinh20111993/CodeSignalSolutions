package Codesignal.Challenge;

public class FollowTheArrows {

	static String[] followTheArrows(String[] directionMap, int[] start) {
		int x = directionMap[0].length(), y = directionMap.length, i = start[0], j = start[1];

		while (true) {
			char s = directionMap[i].charAt(j);
			directionMap[i] = directionMap[i].substring(0,j) + '.' + directionMap[i].substring(j+1);
			
			if (s == '>') {
				j++;
			} else if (s == '<') {
				j--;
			} else if (s == '^') {
				i--;
			} else {
				i++;
			}
			 
			if (i < 0 || j < 0 || i == y || j == x || directionMap[i].charAt(j) == '.')
				break;
		}

		System.out.println("ressult: ");
		for (i = 0; i < y; i++) {
			System.out.println(directionMap[i]);
		}

		return directionMap;
	}

	public static void main(String[] args) {

		System.out.println("################# test 1 ####################");
		String[] directionMap = { ">>", ">v", "^<" };
		int[] start = { 0, 0 };
		followTheArrows(directionMap, start);

		System.out.println("################# test 2 #####################");
		String[] directionMap1 = { ">>", ">v", "^<" };
		int[] start1 = { 1, 1 };
		followTheArrows(directionMap1, start1);

		System.out.println("################# test 3 ####################");
		String[] directionMap2 = { "<><<v<<<", "v<<<>>>^", ">v<v^^>>", "^>v<^<^<", "vv>>>^^v" };
		int[] start2 = { 1, 2 };
		followTheArrows(directionMap2, start2);
	}

}
