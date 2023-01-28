package Codesignal.Arcade;

public class TheCore_SpringOf_Integratetion_96_ChristmasTree {
	/**
	 * 
	 * @param numberOfSpace
	 * @return
	 */
	static String createSpace(int numberOfSpace) {
		String space = "";
		for (int i = 0; i < numberOfSpace; i++) {
			space = space.concat(" ");
		}
		return space;
	}

	/**
	 * 
	 * @param numberOfStar
	 * @return
	 */
	static String createStar(int numberOfStar) {
		String star = "";
		for (int i = 0; i < numberOfStar; i++) {
			star = star.concat("*");
		}
		return star;
	}

	/**
	 * 
	 * @param levelNum
	 * @param levelHeight
	 * @return
	 */
	static int calcMaxLineInTree(int levelNum, int levelHeight) {
		return 1 + 2 * (levelNum + levelHeight);
	}

	/**
	 * 
	 * @param maxLineInTree
	 * @return
	 */
	static String[] buildingCrown(int maxLineInTree) {
		String[] crown = new String[3];
		crown[0] = createSpace(maxLineInTree / 2).concat(createStar(1));
		crown[1] = createSpace(maxLineInTree / 2).concat(createStar(1));
		crown[2] = createSpace(maxLineInTree / 2 - 1).concat(createStar(3));
		return crown;
	}

	/**
	 * 
	 * @param maxLineInTree
	 * @param levelNum
	 * @param levelHeight
	 * @return
	 */
	static String[] buldingFoot(int maxLineInTree, int levelNum, int levelHeight) {
		String[] foot = new String[levelNum];
		int numberOfStarEachLine = levelHeight % 2 == 0 ? levelHeight + 1 : levelHeight,
				space = (maxLineInTree - numberOfStarEachLine) / 2, i = 0;

		for (i = 0; i < levelNum; i++) {
			foot[i] = createSpace(space).concat(createStar(numberOfStarEachLine));
		}
		return foot;
	}

	/**
	 * 
	 * @param level
	 * @param maxLineInTree
	 * @return
	 */
	static String[] buildingLevel(int maxLineInTree, int levelNum, int levelHeight) {
		String[] level = new String[levelNum * levelHeight];

		int i, j, k = 0, baseStar = 5, numberOfSpace, levelStar;

		for (i = 0; i < levelNum; i++) {
			levelStar = baseStar;
			for (j = 0; j < levelHeight; j++) {
				numberOfSpace = (maxLineInTree - levelStar) / 2;
				level[k++] = createSpace(numberOfSpace).concat(createStar(levelStar));
				levelStar += 2;
			}
			baseStar += 2;
		}

		return level;
	}

	/**
	 * 
	 * @param levelNum
	 * @param levelHeight
	 * @return
	 */
	static String[] christmasTree(int levelNum, int levelHeight) {
		String[] response = new String[3 + levelNum * levelHeight + levelNum];
		int i, j = 0, maxLineInTree = calcMaxLineInTree(levelNum, levelHeight);

		// get crown of tree
		String[] crown = buildingCrown(maxLineInTree);
		for (i = 0; i < 3; i++) {
			response[j++] = crown[i];
		}

		// get body(level) of tree
		String[] level = buildingLevel(maxLineInTree, levelNum, levelHeight);
		for (i = 0; i < level.length; i++) {
			response[j++] = level[i];
		}

		// get foot of tree
		String[] foot = buldingFoot(maxLineInTree, levelNum, levelHeight);
		for (i = 0; i < foot.length; i++) {
			response[j++] = foot[i];
		}

		for (i = 0; i < response.length; i++) {
			System.out.println(response[i]);
		}

		return response;
	}

	public static void main(String[] args) {
		System.out.println("####################### Test 1 ##########################");
		int levelNum = 1, levelHeight = 3;
		christmasTree(levelNum, levelHeight);

		System.out.println("####################### Test 2 ##########################");
		int levelNum1 = 2, levelHeight1 = 4;
		christmasTree(levelNum1, levelHeight1);

		System.out.println("####################### Test 3 ##########################");
		int levelNum2 = 4, levelHeight2 = 8;
		christmasTree(levelNum2, levelHeight2);
	}

}
