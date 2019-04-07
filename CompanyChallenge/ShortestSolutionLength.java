package CompanyChallenge;

public class ShortestSolutionLength {

	static int shortestSolutionLength(String[] source) {
		int res = 0, i, j;
		String tmp;
		boolean commentMultiLine = false;

		for (i = 0; i < source.length; i++) {
			tmp = source[i];
			for (j = 0; j < tmp.length(); j++) {
				if (!commentMultiLine && j < tmp.length() - 1 && tmp.charAt(j) == '/') {
					if (tmp.charAt(j + 1) == '/') {
						break;
					}
					if (tmp.charAt(j + 1) == '*') {
						commentMultiLine = true;
						j += 2;
					}
				}
				if (commentMultiLine && j < tmp.length() - 1 && tmp.charAt(j) == '*' && tmp.charAt(j + 1) == '/') {
					commentMultiLine = false;
					j += 1;
					continue;
				}
				if (j < tmp.length() && !commentMultiLine && tmp.charAt(j) != ' ') {
					res++;
				}
			}
		}
		System.out.println(res);
		return res;
	}

	public static void main(String[] args) {
		System.out.println("###################### test 1 #######################");
		String[] source = { "int a = 2;", "int b = 47;/*37;*///41;", "int c = 3/*4//5*/;",
				"return a / b * c/*a /* b / c*/;" };
		shortestSolutionLength(source);

		System.out.println("###################### test 2 #######################");
		String[] source1 = { "var a = 2;", "/*", "var b = 2;", "if (a === b) {", "  b = a + 1;", "  //b = a * 2 - 1;",
				"}", "*/", "var b = 3;", "return a * b;" };
		shortestSolutionLength(source1);

		System.out.println("###################### test 3 #######################");
		String[] source2 = { "//1//1", "/*2*/", "x = 2;;;;;;;;;;;;;//     tetre           */" };
		shortestSolutionLength(source2);

		System.out.println("###################### test 4 #######################");
		String[] source3 = { "/*** ffdfdgfdg fuck ***//** hello world ***/int /*****.////....***/ b = 5;//hhahah",
							"int a=5;/*/*//*//***//*/*//*/*//******************//******* dấddasdad", 
							"int b = adsad; // wtf",
							"x = 2  5t5t   tetre  *//****** ****/ a = 7/******//*****//******//*****///;" };
		shortestSolutionLength(source3);
		
		System.out.println("###################### test 5 #######################");
		String[] source4 = {"/*** ffdfdgfdg fuck ***/",
				"/** hello world ***/",
				"int /***** .////.... ***/",
				"b = 5;// hhahah",
				"int a = 5;/* / *//* / *//******************/",
				"/*******",
				 "* dấddasdad int b = adsad; // wtf x = 2 5t5t tetre",
				 "*/",
				"/****** ****/",
				"a = 7;/******/",
				"/****t*/",
				"/******//*****/// ;",
				"/* / *//* // ***/"};
		shortestSolutionLength(source4);
		
		

		
	}

}
