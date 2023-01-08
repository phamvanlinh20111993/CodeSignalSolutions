package codesignal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// "(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"
// (lambda (x y) (* x y)) 5 7
// "(if(< (* 4 6) (+ 7 9)) 5 6)"
// "((lambda (x y z) (* (* x 778) (+ y (+ z 2434324)))) 5 7 9)"
// "((lambda (x y z) (+ (* x (+ z 1324)) (* y 546))) 1000 2 567)"
// "((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x x) 1)) ))) 1000 2 567)"

public class SchemeInterpreter {

	final static String ADD = "+";

	final static String MULTI = "*";

	final static String LESS_THAN = "<";

	final static String IF = "if";

	final static String BOOL_NOT = "not";

	final static String LAMBDA = "lambda";

	final static List<String> RESERVED_EXPR = Arrays.asList(ADD, MULTI, LESS_THAN, IF, BOOL_NOT, LAMBDA);

	/**
	 * (* (+ 5 6) (* 7 (if(< 1 2) 1 0 ))) => [(, *, (, +, 5, 6, (, *, 7, (, if, (,
	 * <, 1, 2, ), 1, 0, ), ), )]
	 * 
	 * @param expression ex: "(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"
	 * @return ex: [ (, *, (, +, 5, 6, (, *, 7, (, if, (, <, 1, 2, ), 1, 0, ), ), )]
	 */
	public static List<String> transformToArray(String expression) {

		List<String> transformFinal = new ArrayList<String>();
		List<String> transformStep1 = Arrays.asList(expression.split("")).stream().collect(Collectors.toList());

		// Check start with characters: i <=> if, n <=> not, l <=> lambda, or constant
		// numbers (123123 or 12 or just 1)...
		Predicate<String> strContinuity = strCheck -> strCheck.equals("i") || strCheck.equals("n")
				|| strCheck.equals("l") || strCheck.matches("\\d");

		int transformStep1Size = transformStep1.size();
		for (int ind = 0; ind < transformStep1Size;) {
			String sign = transformStep1.get(ind);
			int pos = ind + 1;

			while (strContinuity.test(transformStep1.get(ind)) && pos < transformStep1Size
					&& transformStep1.get(pos).matches("[a-zA-Z0-9\\.]")) {
				sign += transformStep1.get(pos++);
			}

			if (!sign.matches("\\s+")) {
				transformFinal.add(sign);
			}
			ind = pos;
		}

		return transformFinal;
	}

	/**
	 * TODO need to convert to bigDecimal or bigInteger
	 * 
	 * @param operator
	 * @param operandListStr
	 * @return
	 */
	public static Long calculate(String operator, List<String> operandListStr) {

		List<Long> operandListNum = operandListStr.stream().map(numStr -> Long.valueOf(numStr))
				.collect(Collectors.toList());
		Long number = null;
		switch (operator) {
		case ADD:
			number = operandListNum.stream().reduce(0L, ((subtotal, element) -> subtotal + element));
			break;

		case MULTI:
			number = operandListNum.stream().reduce(0L, ((subtotal, element) -> subtotal * element));
			break;

		default:
			break;
		}

		return number;
	}

	static String solution(String expr) {
		List<StackHandleObject> stack = new ArrayList<StackHandleObject>();

		int ind;
		List<String> exprArr = transformToArray(expr);
		for (ind = 0; ind < exprArr.size();) {
			
			if(exprArr.get(ind).equals(")")) {
				System.out.println(stack);
			} else {
				stack.add(new StackHandleObject(exprArr.get(ind)));
			}
			
			ind++;
		}

		return "";
	}

	public static void main(String[] args) {

		System.out.println("################################## test 1 ##########################################");
		System.out.println(
				solution("((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x x) 1)) ))) 1000 2 567)"));

		System.out.println("################################## test 2 ##########################################");
		System.out.println(solution("(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"));

		System.out.println("################################## test 3 ##########################################");
		System.out.println(solution("(lambda (x y) (* x y)) 5 7"));

		System.out.println("################################## test 4 ##########################################");
		System.out.println(solution("((lambda (x y z) (* (* x 778) (+ y (+ z 2434324)))) 5 7 9)"));

		System.out.println("################################## test 5 ##########################################");
		System.out.println(solution("((lambda (x y z) (+ (* x (+ z 1324)) (* y 546))) 1000 2 567)"));

		System.out.println("################################## test 6 ##########################################");
		System.out.println(solution("(if(< (* 4 6) (+ 7 9)) 5 6)"));
	}

}
