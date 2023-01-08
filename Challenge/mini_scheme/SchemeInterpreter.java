package Challenge.mini_scheme;

/**
 * ref: https://app.codesignal.com/challenge/8o8mhuLi3kCZFpxBj
 * 
 * Wearing your language designer cap, you decide to create a mini Scheme interpreter. For starters, you will implement:

	addition and multiplication: +, *
	less than: <
	if
	Boolean not
	lambda
	Your interpreter will take a string as input (valid Scheme) and return a string as output (in this challenge, all results will be integers and should be converted to a string).
	
	Examples:
	"(+ 1 2 3)" => "6"
	
	"(if (< 1 2) 1 0)" => "1"
	
	"((lambda (x y) (+ x y)) 1 2)" => "3"
	
	[execution time limit] 3 seconds (java)
	
	[input] string expr
	
	Valid Scheme input
	
	[output] string

The result, converted to a string
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// "(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"
// (lambda (x y) (* x y)) 5 7
// "(if(< (* 4 6) (+ 7 9)) 5 6)"
// "((lambda (x y z) (* (* x 778) (+ y (+ z 2434324)))) 5 7 9)"
// "((lambda (x y z) (+ (* x (+ z 1324)) (* y 546))) 1000 2 567)"
// "((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x x) 1)) ))) 1000 2 567)"
// "(if (not (not (< 2 1))) 1 0)"

public class SchemeInterpreter {

	final static String ADD = "+";

	final static String MULTI = "*";

	final static String LESS_THAN = "<";

	final static String IF = "if";

	final static String BOOL_NOT = "not";

	final static String LAMBDA = "lambda";

	/**
	 * (* (+ 5 6) (* 7 (if(< 1 2) 1 0 ))) => [(, *, (, +, 5, 6, (, *, 7, (, if, (,
	 * <, 1, 2, ), 1, 0, ), ), )]
	 * 
	 * @param expression
	 *            ex: "(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"
	 * @return ex: [ (, *, (, +, 5, 6, (, *, 7, (, if, (, <, 1, 2, ), 1, 0, ), ), )]
	 */
	public static List<String> transformToArray(String expression) {

		List<String> transformFinal = new LinkedList<String>();
		List<String> transformStep1 = Arrays.asList(expression.split("")).stream().collect(Collectors.toList());

		// Check start with characters: i <=> if, n <=> not, l <=> lambda, or constant
		// numbers (123123 or 12 or just 1)...
		Predicate<String> strContinuity = strCheck -> strCheck.equals("i") || strCheck.equals("n")
				|| strCheck.equals("l") || strCheck.matches("-") || strCheck.matches("[+_a-z0-9A-Z]");

		int transformStep1Size = transformStep1.size();
		for (int ind = 0; ind < transformStep1Size;) {
			String sign = transformStep1.get(ind);
			int pos = ind + 1;

			while (strContinuity.test(transformStep1.get(ind)) && pos < transformStep1Size
					&& transformStep1.get(pos).matches("[+!$a-zA-Z0-9\\.\\-_]")) {
				sign += transformStep1.get(pos++);
			}

			if (!sign.matches("\\s+")) {
				transformFinal.add(sign);
			}
			ind = pos;
		}

		System.out.println("transformFinal" + transformFinal);

		return transformFinal;
	}

	/**
	 * Mapping value to parameters and expression in lambda expression
	 * 
	 * @param ind
	 * @param exprArr
	 * @return
	 */
	static Map<String, String> mappingLambdaVar(int ind, List<String> exprArr) {
		int pos = ind + 1;
		// get variable
		Stack<String> tempStack = new Stack<String>();
		tempStack.push("(");
		tempStack.push("(");
		Queue<String> variableQ = new LinkedList<String>();

		boolean isFirst = true;
		while (tempStack.size() > 1 && pos < exprArr.size()) {
			if (exprArr.get(pos).equals("(")) {
				tempStack.push("(");
				if (isFirst) {
					int posi = pos + 1;
					while (posi < exprArr.size() && !exprArr.get(posi).equals(")")) {
						variableQ.add(exprArr.get(posi));
						posi++;
					}
					isFirst = false;
				}
			}

			if (exprArr.get(pos).equals(")")) {
				tempStack.pop();
			}
			pos++;
		}

		Map<String, String> res = new HashMap<String, String>();
		while (tempStack.size() > 0 && pos < exprArr.size()) {
			if (exprArr.get(pos).equals(")")) {
				tempStack.pop();
				break;
			}
			res.put(variableQ.poll(), exprArr.get(pos));
			pos++;
		}

		return res;

	}

	/**
	 * Calculate return value and then remove lambda expression
	 * 
	 * @param ind
	 * @param exprArr
	 * @return
	 */
	static List<String> removeLambdaFunc(int ind, List<String> exprArr) {
		int pos = ind + 1;
		// get variable
		Stack<String> tempStack = new Stack<String>();
		tempStack.push("(");
		tempStack.push("(");

		List<Integer> removeInd = new ArrayList<Integer>();
		removeInd.add(ind - 2);
		removeInd.add(ind - 1);
		removeInd.add(ind);

		boolean isFirst = true;
		// variable
		while (tempStack.size() > 1 && pos < exprArr.size()) {
			if (exprArr.get(pos).equals("(")) {
				tempStack.push("(");
				if (isFirst) {
					removeInd.add(pos);
					int posi = pos + 1;
					while (posi < exprArr.size() && !exprArr.get(posi).equals(")")) {
						removeInd.add(posi);
						posi++;
					}
					isFirst = false;
					removeInd.add(posi);
				}
			}

			if (exprArr.get(pos).equals(")")) {
				tempStack.pop();
			}
			pos++;
		}

		// VALUE
		removeInd.add(pos - 1);
		while (tempStack.size() > 0 && pos < exprArr.size()) {
			removeInd.add(pos);
			if (exprArr.get(pos).equals(")")) {
				tempStack.pop();
				break;
			}
			pos++;
		}

		for (int index = removeInd.size() - 1; index >= 0; index--) {
			exprArr.remove((int) removeInd.get(index));
		}

		return exprArr;
	}

	/**
	 * Remove mathematics expression like + and * in exprArr
	 * 
	 * @param ind
	 * @param exprArr
	 * @return
	 */
	static List<String> removeMathCal(int ind, List<String> exprArr) {
		Stack<String> tempStack = new Stack<String>();
		tempStack.push("(");
		String operator = exprArr.get(ind + 1);
		List<String> operands = new ArrayList<>();

		List<Integer> removeInds = new ArrayList<>();
		removeInds.add(ind);
		removeInds.add(ind + 1);
		int pos = ind + 2;

		while (pos < exprArr.size() && isNumber(exprArr.get(pos))) {
			operands.add(exprArr.get(pos));
			removeInds.add(pos);
			pos++;
		}

		System.out.println("operands" + exprArr);
		BigDecimal res = new BigDecimal(operands.get(0));

		if (operator.equals(ADD)) {
			for (int index = 1; index < operands.size(); index++) {
				res = res.add(new BigDecimal(operands.get(index)));
			}
		}

		if (operator.equals(MULTI)) {
			for (int index = 1; index < operands.size(); index++) {
				res = res.multiply(new BigDecimal(operands.get(index)));
			}
		}

		exprArr.set(pos, res.toString());
		for (int index = removeInds.size() - 1; index >= 0; index--) {
			exprArr.remove((int) removeInds.get(index));
		}

		return exprArr;
	}

	/**
	 * Get matcher by pattern
	 * 
	 * @param PATTERN
	 * @param macherStr
	 * @return
	 */
	static List<String> getMatcher(String PATTERN, String macherStr) {
		Pattern r = Pattern.compile(PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher m = r.matcher(macherStr);

		List<String> matches = new ArrayList<String>();
		while (m.find()) {
			matches.add(m.group(0));
		}

		return matches;
	}

	/**
	 * Remove if condition in exprArr
	 * 
	 * @param ind
	 * @param exprArr
	 * @return
	 */
	static List<String> removeIf(int ind, List<String> exprArr) {
		Stack<String> tempStack = new Stack<String>();

		List<Integer> removeInds = new ArrayList<>();
		removeInds.add(ind);

		tempStack.push("(");
		String express = "(";
		ind++;
		while (tempStack.size() > 0) {
			if (exprArr.get(ind).equals("(")) {
				tempStack.push("(");
			}
			if (exprArr.get(ind).equals(")")) {
				tempStack.pop();
			}
			removeInds.add(ind);
			express += exprArr.get(ind) + " ";
			ind++;
		}
		List<String> matches = getMatcher("[-+]?\\d+(\\.\\d+)?", express);
		List<String> nots = getMatcher(BOOL_NOT, express);

		BigInteger res;

		if (!nots.isEmpty() && nots.size() % 2 != 0) {
			if (new BigInteger(matches.get(1)).compareTo(new BigInteger(matches.get(0))) > 0) {
				res = new BigInteger(matches.get(3));
			} else {
				res = new BigInteger(matches.get(2));
			}
		} else {
			if (new BigInteger(matches.get(1)).compareTo(new BigInteger(matches.get(0))) > 0) {
				res = new BigInteger(matches.get(2));
			} else {
				res = new BigInteger(matches.get(3));
			}
		}

		exprArr.set(removeInds.get(removeInds.size() - 1), res.toString());
		removeInds.remove(removeInds.size() - 1);
		for (int index = removeInds.size() - 1; index >= 0; index--) {
			exprArr.remove((int) removeInds.get(index));
		}

		return exprArr;
	}

	/**
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num) {
		if (num == null) {
			return false;
		}
		return num.matches("[-+]?\\d+(?:\\.\\d+)?");
	}

	static String solution(String expr) {

		List<Map<String, String>> storageLambda = new LinkedList<>();
		List<String> exprArr = transformToArray(expr);

		int ind;
		// mapping lambda variable to value
		for (ind = 0; ind < exprArr.size(); ind++) {
			if (exprArr.get(ind).equals(LAMBDA)) {
				storageLambda.add(mappingLambdaVar(ind, exprArr));
			}
		}

		if (storageLambda.size() > 0) {
			Set<String> variables = storageLambda.stream().map(m -> m.keySet()).flatMap(Collection::stream)
					.collect(Collectors.toSet());

			for (ind = exprArr.size() - 1; ind >= 0; ind--) {
				if (variables.contains(exprArr.get(ind))) {
					for (int p = storageLambda.size() - 1; p >= 0; p--) {
						if (storageLambda.get(p).containsKey(exprArr.get(ind))) {
							exprArr.set(ind, storageLambda.get(p).get(exprArr.get(ind)));
							break;
						}
					}
				}
				if (exprArr.get(ind).equals(LAMBDA)) {
					storageLambda.remove(storageLambda.size() - 1);
				}
			}
		}
		// remove lambda
		for (ind = 0; ind < exprArr.size(); ind++) {
			if (exprArr.get(ind).equals(LAMBDA)) {
				exprArr = removeLambdaFunc(ind, exprArr);
			}
		}

		ind = exprArr.size() - 1;
		while (ind > 0) {

			if (ind > exprArr.size() - 1) {
				ind = exprArr.size() - 1;
			}

			if (exprArr.get(ind).equals(IF)) {
				exprArr = removeIf(ind - 1, exprArr);
				ind--;
				continue;
			}
			if (exprArr.get(ind).equals(ADD) || exprArr.get(ind).equals(MULTI)) {
				exprArr = removeMathCal(ind - 1, exprArr);
				ind--;
				continue;
			}

			ind--;
		}

		for (ind = exprArr.size() - 1; ind >= 0; ind--) {

			if (exprArr.get(ind).equals(IF)) {
				exprArr = removeIf(ind - 1, exprArr);
			}

			if (exprArr.get(ind).equals(ADD) || exprArr.get(ind).equals(MULTI)) {
				exprArr = removeMathCal(ind - 1, exprArr);
			}
		}

		return new BigDecimal(exprArr.get(0)).toString();
	}

	public static void main(String[] args) {

		// expected: "1891002"
		System.out.println("################################## test 1 ##########################################");
		System.out.println(
				solution("((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x x)) 1) ))) 1000 2 567)"));

		// expected: "77"
		System.out.println("################################## test 2 ##########################################");
		System.out.println(solution("(* (+ 5 6) (* 7 (if(< 1 2) 1 0 )))"));

		// expected: "35"
		System.out.println("################################## test 3 ##########################################");
		System.out.println(solution("((lambda (x y) (* x y)) 5 7)"));

		// expected: "9469582600"
		System.out.println("################################## test 4 ##########################################");
		System.out.println(solution("((lambda (x y z) (* (* x 778) (+ y (+ z 2434324)))) 5 7 9)"));

		// expected: "1892092"
		System.out.println("################################## test 5 ##########################################");
		System.out.println(solution("((lambda (x y z) (+ (* x (+ z 1324)) (* y 546))) 1000 2 567)"));

		// expected: "6"
		System.out.println("################################## test 6 ##########################################");
		System.out.println(solution("(if(< (* 4 6) (+ 7 9)) 5 6)"));

		// expected: "1893268"
		System.out.println("################################## test 7 ##########################################");
		System.out.println(
				solution("((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x x z y)) 1) ))) 1000 2 567)"));

		// expected: "1893000"
		System.out.println("################################## test 8 ##########################################");
		System.out.println(solution(
				"((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x (if (< 2 x) 100 2))) 10)))) 1000 2 567)"));

		// expected: "1"
		System.out.println("################################## test 9 ##########################################");
		System.out.println(solution(" ( ( lambda ( x ) x ) 1)"));

		// expected: "1"
		System.out.println("################################## test 10 ##########################################");
		System.out.println(solution("(if (not (< 2 1)) 1 0)"));

		// expected: "1"
		System.out.println("################################## test 11 ##########################################");
		System.out.println(
				solution("((lambda (x y) (+ ((lambda (x123 y) (* x123 y)) 1 -2) ((lambda (x y) (+ x y)) 1 2))) 1 2)"));

		// expected: "821"
		System.out.println("################################## test 12 ##########################################");
		System.out.println(
				solution("((lambda (x y) (+ ((lambda (c m) (* c y x m)) 1 2) ((lambda (x t) (+ x t)) 1 20))) 2 200)"));

		// expected: "0"
		System.out.println("################################## test 13 ##########################################");
		System.out.println(solution("(if (not (not (not (< 2 10)))) -131232131 -0)"));

		// expected: "9"
		System.out.println("################################## test 14 ##########################################");
		System.out.println(solution("((lambda (x y) (+ x y ((lambda (x y) (+ x y))) 1 2)) 1 2)"));

		// expected: "2498981680"
		System.out.println("################################## test 15 ##########################################");
		System.out.println(solution(
				"((lambda (x y z) (+ (* x (+ z 1324)) (* y ((lambda (x) (* x (if (not (not (not (< 2 1)))) 110101 -0) z y)) 10) ))) 1000 2 567)"));

		// expected: "2498981680"
		System.out.println("################################## test 16 ##########################################");
		System.out.println(solution("(+ 1.3232 2 3)"));

		// expected: "3911113610168096"
		System.out.println("################################## test 17 ##########################################");
		System.out.println(solution(
				"(+ 12 (* 34 44 33) 32 21 665 (if (< 21 -22) 475663 20002) ((lambda (x y z) (+ (* x (+ z 1324 1 1 1    1    1)) (* y ((lambda (x) (* x (if (not (not (not (< 2 1)))) 110101 -0) z y)) 10) ))) 1000 2 567) (+ 212 (* 122222222222222 32)))"));

		// expected: "1"
		System.out.println("################################## test 16 ##########################################");
		System.out.println(solution("(if (< -00000000000000000 -10) 1 2)"));

		// expected: "143512762315055"
		System.out.println("################################## test 17 ##########################################");
		System.out.println(solution(
				"((lambda (x y z2 tk12 _21_ddcd) (+ x y z2 ((lambda (x y z) (* (* _21_ddcd x 778 ((lambda (x y) (* x y)) 5 7)) (+ y (+ z 2434324 tk12)))) 5 7 9) (if(< (* 4 6) (+ 7 9 12 -7)) 5666 6))) 100 2 -3 21 433)"));

		// expected: "-143508399796545"
		System.out.println("################################## test 18 ##########################################");
		System.out.println(solution(
				"((lambda (x y _!$z2 tk12 _21_ddcd) (+ x y _!$z2 ((lambda (x y z) (* (* _21_ddcd x 778 ((lambda (x y) (* x y)) +5 7)) (+ y (+ z -2434324 tk12)))) 5 7 9) (if(< (* 4 6) (+ 7 9 12 -7)) 5666 6))) 100 2 -3 21 433)"));

		// expected: "785722734734"
		System.out.println("################################## test 19 ##########################################");
		System.out.println(
				solution("((lambda (x y z t k m l o p q) (+ x y z (* t k m l o p q))) 1 2 11 22 33 44 55 66 77 88)"));

		// expected: "785722734734"
		System.out.println("################################## test 20 ##########################################");
		System.out.println(solution(
				"(* 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 333 ((lambda (x y) (+ x y)) 1 2) ((lambda (x y) (+ x y)) 1 2) 5345 65645 867876 (if (< 1 2) 1 0) 00  22 44 235)"));

		// expected:
		// "-1204272910456814585296359144285727895288587888854263669509766635647610"
		System.out.println("################################## test 21 ##########################################");
		System.out.println(solution("((lambda (x) (* x x x x x x (if (< 1 x) -10 -1451))) 222222222211)"));

		// expected: "4" => fail
		System.out.println("################################## test 22 ##########################################");
		System.out.println(solution("((lambda (x y) (+ x y)) 1 ((lambda (x y) (+ x y) ) 1 2))"));

	}

}
