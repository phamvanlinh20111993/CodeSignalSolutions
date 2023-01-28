package Codesignal.InterviewPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HeapSort {

	public static List<Integer> randomEArray(int total) {
		List<Integer> randomArr = new ArrayList<>();
		for (int ind = 0; ind < total; ind++) {
			randomArr.add(ThreadLocalRandom.current().nextInt(1001));
		}

		return randomArr;
	}

	public static List<Integer> heap(List<Integer> array, int max) {
		int parent = 0;
		if (array.get(parent) > array.get(max)) {
			array = swapTwoElementArr(array, parent, max);
		}

		// heap to valid array
		int[] childs = getChildNode(parent);
		while (childs[0] < max && childs[1] < max
				&& (array.get(parent) < array.get(childs[0]) || array.get(parent) < array.get(childs[1]))) {
			if (array.get(childs[0]) > array.get(childs[1])) {
				array = swapTwoElementArr(array, parent, childs[0]);
				parent = childs[0];
			} else {
				array = swapTwoElementArr(array, parent, childs[1]);
				parent = childs[1];
			}
			childs = getChildNode(parent);
		}

		if (max > 1) {
			array = heap(array, max - 1);
		}

		return array;
	}

	public static List<Integer> heapSort(List<Integer> array) {
		int size = array.size();
		for (int ind = 1; ind < size; ind++) {
			int temp = ind;
			int parentNode = getParentNode(temp);
			// when check invalid condition then heap array
			while (parentNode >= 0 && array.get(parentNode) < array.get(temp)) {
				array = swapTwoElementArr(array, parentNode, temp);
				temp = parentNode;
				parentNode = getParentNode(temp);
			}
		}

		return heap(array, array.size() - 1);
	}

	public static int getParentNode(int childTh) {
		return (int) (Math.ceil(childTh / 2.0) - 1);
	}

	public static int[] getChildNode(int parent) {
		return new int[] { parent * 2 + 1, parent * 2 + 2 };
	}

	public static List<Integer> swapTwoElementArr(List<Integer> array, int i, int j) {
		// way 1
		// array.set(i, array.get(i) - array.get(j));
		// array.set(j, array.get(i) + array.get(j));
		// array.set(i, array.get(j) - array.get(i));

		// way 2
		int temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);

		return array;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to heap sort !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		List<Integer> array = randomEArray(20);
		// Integer[] arrays = new Integer[] {32, 34, 80, 7, 91, 93, 8, 33, 43, 0 };
		// List<Integer> array = Arrays.asList(arrays);

		array.stream().map(v -> v).forEach(v -> System.out.print(v + " "));
		array = heapSort(array);
		System.out.println("\nAfter");
		array.stream().map(v -> v).forEach(v -> System.out.print(v + " "));
	}
}
