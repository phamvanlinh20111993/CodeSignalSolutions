package Codesignal.Challenge;

import java.util.Stack;

public class DecodeString {

	static String decodeString(String s) {

		String temp = "", temp1 = "";
		Stack<Integer> stackInt = new Stack<Integer>();
		Stack<Character> stackChar = new Stack<Character>();
		Stack<String> stackStr = new Stack<String>();
		int i, j;
		boolean flag = true;

		for (i = 0; i < s.length();) 
		{
			if(Character.isDigit(s.charAt(i))) {
				//stackInt.push(Character.getNumericValue(s.charAt(i)));
				j = i+1;
                while(Character.isDigit(s.charAt(j++)));
                String num = s.substring(i, j-1);
				stackInt.push(Integer.parseInt(num));
                i = j-2;
			}else if(s.charAt(i) == '[') {
				flag = false;
				if(!stackChar.isEmpty()) {
					stackStr.push(temp1);
					temp1 = "";
				}
				stackChar.push(s.charAt(i));
			}else if(s.charAt(i) == ']') {
				stackChar.pop();
				int num = stackInt.pop();
				String copyStr = temp1;
			
				for(j = 1; j < num; j++)
					temp1 = temp1.concat(copyStr);
				
				//khi stack rỗng
				if(stackChar.isEmpty()) {
					flag = true;
					temp += temp1;
					temp1 = "";
				}else {
					if(!stackStr.isEmpty()) {
						String tmp = temp1;
						temp1 = stackStr.pop() + tmp;
					}
				}
				
			}else {
				if(flag)
					temp += s.charAt(i);
				else 
					temp1 += s.charAt(i);
			}
			
			i++;
		}
	
		return temp;
	}

	public static void main(String[] args) {

		System.out.println("############### test 1 ##############");
		String s = "2[b3[a]]";
		System.out.println(decodeString(s));

		System.out.println("############### test 2 ##############");
		String s1 = "z1[y]zzz2[abc]";
		System.out.println(decodeString(s1));

		System.out.println("############### test 3 ##############");
		String s2 = "2[2[2[2[b]]]]";
		System.out.println(decodeString(s2));
		
		System.out.println("############### test 4 ###############");
		String s3 = "2[aaa]aabbb4[4[7[aaaa]bbbbzzt]ttt]z";
		System.out.println(decodeString(s3));
		
		System.out.println("############### test 5 ###############");
		String s5 = "140[codesignal]";
		System.out.println(decodeString(s5));
		
		System.out.println("############### test 6 ###############");
		String s6 = "4[ab]";
		System.out.println(decodeString(s6));
		
	}

}
