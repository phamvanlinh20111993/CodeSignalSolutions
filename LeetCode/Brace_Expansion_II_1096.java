package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * url: https://leetcode.com/problems/brace-expansion-ii/?envType=daily-question&envId=2026-09-25
 * Under the grammar given below, strings can represent a set of lowercase words. Let R(expr) denote the set of words the expression represents.

The grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma-delimited list of two or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the three rules for our grammar:

For every lowercase letter x, we have R(x) = {x}.
For expressions e1, e2, ... , ek with k >= 2, we have R({e1, e2, ...}) = R(e1) ∪ R(e2) ∪ ...
For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.

 

Example 1:

Input: expression = "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]
Example 2:

Input: expression = "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.
 

Constraints:

1 <= expression.length <= 60
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
 */
public class Brace_Expansion_II_1096 {
		
	/**
	    R("{{a,b},{b,c}}") = {"a","b","c"}
	    R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
	
	    R("{{a,b},{b,c},{{a,b}{c{d,e}}}}"")
	     ={{a,b},{b,c},{{a,b}{cd,ce}}}
	     ={{a,b},{b,c},{acd,bcd,ace,bce}}
	     =a,b,c,acd,bcd,ace,bce
	
	    R("{a,b}{c,{d,e}}") = "{a,b}{c,d,e}" = ac,ad,ae,bc,bd,be
	 */
	
	/*
	public List<String> cal(List<String> exp, String sign){
	    if(sign.equals("")){
	        return exp;
	    }
	
	    List<String> lefts = new ArrayList<>();
	    int ind = 0;
	    while(ind < exp.size() && !exp.get(ind).equals(sign)){
	         if(exp.get(ind).equals(",")){
	            ind++;
	            continue;
	        }
	        lefts.add(exp.get(ind++));
	    }
	
	    ind++;
	    List<String> rights = new ArrayList<>();
	    while(ind < exp.size()){
	        if(exp.get(ind).equals(",")){
	            ind++;
	            continue;
	        }
	        rights.add(exp.get(ind++));
	    }
	
	    if(sign.equals("*")){
	        Set<String> res = new HashSet<>();
	        for(String f : rights){
	            for(String s: lefts){
	                res.add(f+s);
	            }
	        }
	        return new ArrayList<>(res);
	    }
	
	    lefts.addAll(rights);
	    return new ArrayList<>(new HashSet<>(lefts));
	}
	
	public List<String> evaluate(List<String> tokens) {
	    List<Set<String>> groups = new ArrayList<>();
	    List<Character> operators = new ArrayList<>();
	    Set<String> currentGroup = new HashSet<>();
	    for (String token : tokens) {
	        if (token.equals(",")) {
	            continue;
	        }
	        if (token.equals("+") || token.equals("*")) {
	            groups.add(currentGroup);
	            currentGroup = new HashSet<>();
	            operators.add(token.charAt(0));
	        } else {
	            currentGroup.add(token);
	        }
	    }
	
	    groups.add(currentGroup);
	
	    Set<String> result = groups.get(0);
	    for (int i = 0; i < operators.size(); i++) {
	        char operator = operators.get(i);
	        Set<String> right = groups.get(i + 1);
	        if (operator == '+') {
	            result.addAll(right);
	        } else {
	            Set<String> next = new HashSet<>();
	
	            for (String left : result) {
	                for (String r : right) {
	                    next.add(left + r);
	                }
	            }
	
	            result = next;
	        }
	    }
	
	    List<String> answer = new ArrayList<>(result);
	    Collections.sort(answer);
	
	    return answer;
	}
	
	public List<String> strToListExpression(String expression){
	    List<String> expL = new ArrayList<>();
	    for(int i = 0; i < expression.length();){
	        if(Character.isLowerCase(expression.charAt(i))){
	            int ind = i;
	            String tmp = "";
	            while(ind < expression.length() && Character.isLowerCase(expression.charAt(ind))){
	                tmp += expression.charAt(ind++);
	            }
	            if(ind < expression.length() && i - 1 >= 0 && (expression.charAt(ind) == '{' || expression.charAt(i-1) == '}')){
	                expL.add("{");
	                expL.add(tmp);
	                expL.add("}");
	            } else{
	                expL.add(tmp);
	            }
	            i = ind;
	            continue;
	        }
	        expL.add(String.valueOf(expression.charAt(i)));
	        i++;
	    }
	
	    return expL;
	}
	
	public List<String> braceExpansionII(String expression) {
	    Stack<String> s = new Stack<>();
	    List<String> expL = strToListExpression(expression);
	
	    System.out.println(String.join("", expL));
	   
	    for(int i = 0; i < expL.size(); i++){
	        if(expL.get(i).equals("}")){
	            Stack<String> mt = new Stack<>();
	            String sign = "";
	            String tmp = "";
	        //    System.out.println(s);
	            while(!s.isEmpty() && !s.peek().equals("{")){
	                String c = s.pop();
	                if(c.equals("+") || c.equals("*")){
	                    sign = c;
	                }
	                mt.push(c);
	            }
	
	            List<String> m = new ArrayList<>(mt);
	
	       //     Collections.reverse(m);
	            System.out.println(m);
	            m = evaluate(m);
	            s.pop();// remove '{'
	            if(!s.isEmpty()){
	                if(s.peek().equals(",")){
	                    s.push("+");
	                }else if(!s.peek().equals("{")){
	                    s.push("*");
	                }
	            }
	            s.addAll(m);
	            continue;
	        }
	        s.push(expL.get(i));
	    }
	
	    System.out.println(s);
	
	    List<String> m = new ArrayList<>();
	    String sign = "";
	    while(!s.isEmpty() && !s.peek().equals("{")){
	        String c = s.pop();
	        if(c.equals("+") || c.equals("*")){
	            sign = c;
	        }
	        m.add(c);
	    }
	
	    return cal(m, sign).stream()
	        .collect(Collectors.toSet()) 
	        .stream()                     
	        .sorted()      
	        .collect(Collectors.toList()); 
	} */


	// we are too lazy to handle this expression so we use chatgpt to support generate the algothihm
	// https://chatgpt.com/c/6aa57611-4678-83ec-acbe-f3c9f8ef7f1d
	// // The clean way is to parse the expression into terms separated by +. Inside each term, process * from left to right.
	public List<String> evaluate(List<String> tokens) {
	    List<Set<String>> terms = new ArrayList<>();
	
	    Set<String> current = null;
	    Set<String> group = new HashSet<>();
	
	    for (String token : tokens) {
	        if (token.equals(",")) {
	            continue;
	        }
	        if (token.equals("+")) {
	            if (current == null) {
	                current = new HashSet<>(group);
	            } else {
	                current = multiply(current, group);
	            }
	            terms.add(current);
	            current = null;
	            group = new HashSet<>();
	            continue;
	        }
	
	        if (token.equals("*")) {
	            if (current == null) {
	                current = new HashSet<>(group);
	            } else {
	                current = multiply(current, group);
	            }
	            group = new HashSet<>();
	
	            continue;
	        }
	        group.add(token);
	    }
	    if (current == null) {
	        current = group;
	    } else {
	        current = multiply(current, group);
	    }
	    terms.add(current);
	    Set<String> result = new HashSet<>();
	    for (Set<String> term : terms) {
	        result.addAll(term);
	    }
	    List<String> answer = new ArrayList<>(result);
	    Collections.sort(answer);
	
	    return answer;
	}
	
	public Set<String> multiply(Set<String> left, Set<String> right) {
		Set<String> result = new HashSet<>();
		for (String l : left) {
			for (String r : right) {
				result.add(l + r);
			}
		}
		return result;
	}
	
	public List<String> strToListExpression(String expression) {
		List<String> expL = new ArrayList<>();
		for (int i = 0; i < expression.length();) {
			if (Character.isLowerCase(expression.charAt(i))) {
				int ind = i;
				String tmp = "";
				while (ind < expression.length() && Character.isLowerCase(expression.charAt(ind))) {
					tmp += expression.charAt(ind++);
				}
				if (ind == expression.length() || (ind < expression.length() && ind - 1 >= 0
						&& (expression.charAt(ind) == '{' || expression.charAt(i - 1) == '}'))) {
					expL.add("{");
					expL.add(tmp);
					expL.add("}");
				} else {
					expL.add(tmp);
				}
				i = ind;
				continue;
			}
			expL.add(String.valueOf(expression.charAt(i)));
			i++;
		}

		return expL;
	}

	record Data(List<String> dt, int atInd){}
	
	public Data recusion(int index, List<String> expL, int lv) {
		List<String> dt = new ArrayList<>();
		int atInd = 0;

		List<Data> temp = new ArrayList<>();
		for (int i = index; i < expL.size(); i++) {
			if (expL.get(i).equals("{")) {
				Data left = recusion(i + 1, expL, lv + 1);
				if (left.atInd > i) {
					i = left.atInd;
				}
				if (left.dt.size() > 0) {
					temp.add(left);
				}
			} else {
				if (expL.get(i).equals("}")) {
					atInd = i;
					break;
				} else if (!expL.get(i).equals(",")) {
					dt.add(expL.get(i));
				}
			}
		}
		if (dt.size() > 0) {
			temp.add(new Data(dt, atInd));
		}

		// System.out.println("level " + lv + ", check " + temp);
		if (temp.size() > 1) {
			List<String> checkEpr = new ArrayList<>(temp.get(0).dt);
			for (int ind = 1; ind < temp.size(); ind++) {
				String sign = expL.get(temp.get(ind - 1).atInd + 1).equals("{") ? "*" : "+";
				checkEpr.add(sign);
				checkEpr.addAll(temp.get(ind).dt);
			}
			// System.out.println(checkEpr);

			return new Data(evaluate(checkEpr), atInd);

			// List<String> ex = new ArrayList<>(temp.get(0).dt);
			// String sign = expL.get(temp.get(0).atInd+1).equals("{") ? "*" : "+";
			// for(int ind = 1; ind < temp.size(); ind++){
			// sign = expL.get(temp.get(ind-1).atInd+1).equals("{") ? "*" : "+";
			// ex.add(sign);
			// ex.addAll(temp.get(ind).dt);
			// ex = cal(ex, sign);
			// }
			// return new Data(ex, atInd);
		}

		return temp.get(0);
	}

	public List<String> braceExpansionII(String expression) {
		List<String> expL = strToListExpression(expression);
		// System.out.println("expression: " + String.join("", expL));
		return recusion(0, expL, 0).dt.stream().distinct().sorted().collect(Collectors.toList());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
