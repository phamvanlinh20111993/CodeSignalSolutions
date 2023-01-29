package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @Url https://www.hackerrank.com/challenges/k-factorization/problem?isFullScreen=true
 *
 */
public class KFactorization {

	private static int minimal;
	private static List<List<Integer>> storageMins;

	public static List<Integer> kFactorization(int n, List<Integer> A) {
		minimal = 2000;
		storageMins = new ArrayList<>();
		backtracking(0, n, A, new ArrayList<Integer>());
		
		if(storageMins.size() == 0) {
			List<Integer> res = new ArrayList<>();
			res.add(-1);
			return res;
		}

		storageMins = storageMins.stream()
				.map(list -> list.stream().sorted().collect(Collectors.toList()))
				.collect(Collectors.toList());

		List<List<Integer>> minStages = storageMins.stream().map(list -> {
			List<Integer> modify = new ArrayList<>();
			int temp = n;
			for (int ind = list.size() - 1; ind > -1; ind--) {
				temp = temp / list.get(ind);
				modify.add(temp);
			}
			modify.add(n);
			return modify.stream().sorted().collect(Collectors.toList());
		}).collect(Collectors.toList());

		List<String> toStr = minStages.stream().map(list -> {
			return list.stream().map(String::valueOf).collect(Collectors.joining(","));
		}).sorted(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.equalsIgnoreCase(o2) ? -1 : 1;
			}
		}).collect(Collectors.toList());
		
		return Arrays.asList(toStr.get(0).split(","))
				.stream()
				.map(v -> Integer.parseInt(v))
				.collect(Collectors.toList());
	}

	public static void backtracking(int start, int n, List<Integer> A, List<Integer> storage) {

		for (int ind = start; ind < A.size(); ind++) {
			if (n == 1) {
				if (minimal > storage.size()) {
					minimal = storage.size();
					storageMins = new ArrayList<>();
					storageMins.add(storage);
				} else if (minimal == storage.size()) {
					storageMins.add(storage);
				}
				return;
			} else if (n % A.get(ind) == 0 && storage.size() < minimal) {
				List<Integer> storageT = storage.stream().map(v -> v).collect(Collectors.toList());
				storageT.add(A.get(ind));
				backtracking(ind, n / A.get(ind), A, storageT);
			}
		}
	}

	public static void main(String[] args) {
		int n = 672;
		List<Integer> A = new ArrayList<Integer>();
		A.add(2);
		A.add(4);
		A.add(6);
		A.add(9);
		A.add(3);
		A.add(7);
		A.add(16);
		A.add(10);
		A.add(15);
		System.out.println("################ test 1 ##################");
		kFactorization(n, A);

		int n1 = 72;
		List<Integer> A1 = new ArrayList<Integer>();
		A1.add(2);
		A1.add(4);
		A1.add(6);
		A1.add(9);
		A1.add(3);
		A1.add(7);
		A1.add(16);
		A1.add(10);
		A1.add(5);

		System.out.println("################ test 2 ##################");
		kFactorization(n1, A1);

		int n2 = 1000000000;
		List<Integer> A2 = new ArrayList<Integer>();
		A2.add(2);
		A2.add(4);
		A2.add(6);
		A2.add(9);
		A2.add(3);
		A2.add(7);
		A2.add(16);
		A2.add(10);
		A2.add(11);
		A2.add(12);
		A2.add(14);
		A2.add(8);
		A2.add(15);
		A2.add(18);
		A2.add(22);
		A2.add(20);
		A2.add(30);
		A2.add(26);
		A2.add(24);
		A2.add(25);

		System.out.println("################ test 3 ##################");
		kFactorization(n2, A2);

		int n3 = 12;
		List<Integer> A3 = new ArrayList<Integer>();
		A3.add(2);
		A3.add(3);
		A3.add(4);
		System.out.println("################ test 4 ##################");
		kFactorization(n3, A3);
	}

}
