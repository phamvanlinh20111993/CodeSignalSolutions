package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordCracker {

	public static List<List<Integer>> ALL_POSSIBLE;

	public static String passwordCracker(List<String> passwords, String loginAttempt) {
		
		return "WRONG PASSWORD";
	}

	public static void backtracking(int length, int next, List<Integer> d, List<Integer> data) {
		if (data.size() == length) {
			ALL_POSSIBLE.add(data);
			return;
		}

		for (int i = next; i < d.size(); i++) {
			List<Integer> temp = data.stream().collect(Collectors.toList());
			temp.add(d.get(i));
			backtracking(length, i + 1, d, temp);
		}
	}

	public static void main(String[] args) {
		List<String> passwords = new ArrayList<>();
		passwords.add("because");
		passwords.add("can");
		passwords.add("do");
		passwords.add("must");
		passwords.add("we");
		passwords.add("what");

		System.err.println(passwordCracker(passwords, "wedowhatwemustbecausewecan"));

	}

}
