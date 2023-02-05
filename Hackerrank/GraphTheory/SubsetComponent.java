package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubsetComponent {
	
	public static int count;

	public static int findConnectedComponents(List<Integer> d) {
		count = 0;
		for (int ind = 1; ind <= d.size(); ind++) {
			backtracking(ind, 0, d, new ArrayList<>());
		}
		
		System.out.println("Count " + count);
		return 0;
	}

	public static void backtracking(int length, int next, List<Integer> d, List<Integer> data) {
		if (data.size() == length) {
			System.out.println(data);
			count ++;
			return;
		}

		for (int i = next; i < d.size(); i++) {
			List<Integer> temp = data.stream().collect(Collectors.toList());
			temp.add(d.get(i));
			backtracking(length, i + 1, d, temp);
		}
	}

	public static void main(String[] args) {

		List<Integer> d = new ArrayList<Integer>();

		d.add(1);
		d.add(2);
		d.add(3);
		d.add(4);
		d.add(5);
		d.add(6);
		d.add(7);
		d.add(8);
		d.add(9);
		d.add(10);
		d.add(11);
		d.add(12);
		d.add(13);
		d.add(14);
		d.add(15);
		d.add(16);
		d.add(17);
		d.add(18);
		d.add(19);
		d.add(20);
		d.add(21);
		
		System.out.println("Input: " + d);
		System.out.println("Output");
		findConnectedComponents(d);
	}

}
