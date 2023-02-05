package Hackerrank.Implementation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/encryption/problem?isFullScreen=true
 */
public class Encryption {

	public static String encryption(String s) {
		int le = (int) Math.ceil(Math.sqrt(s.length())), tmp = 1;

		List<List<Character>> matrix = new ArrayList<>();
		for (int ind = 0; ind < s.length();) {
			List<Character> chars = new ArrayList<>();
			while (ind < s.length() && ind < le * tmp) {
				chars.add(s.charAt(ind));
				ind++;
			}
			matrix.add(chars);
			tmp++;
		}

		String res = "";

		for (int ind = 0; ind < le; ind++) {
			for (int pos = 0; pos < matrix.size(); pos++) {
				res += ind < matrix.get(pos).size() ? matrix.get(pos).get(ind) : "";
			}
			res += " ";
		}

		return res;
	}

	public static void main(String[] args) {
		

	}

}
