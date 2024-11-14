package Codesignal.InterviewPractice.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @param <T>
 */
class SegmentData<T> {

	private Integer startRange;

	private Integer endRange;

	private T value;

	public SegmentData() {
		super();
	}

	public SegmentData(Integer startRange, Integer endRange, T value) {
		super();
		this.startRange = startRange;
		this.endRange = endRange;
		this.value = value;
	}

	public Integer getStartRange() {
		return startRange;
	}

	public void setStartRange(Integer startRange) {
		this.startRange = startRange;
	}

	public Integer getEndRange() {
		return endRange;
	}

	public void setEndRange(Integer endRange) {
		this.endRange = endRange;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SegmentData [startRange=" + startRange + ", endRange=" + endRange + ", value=" + value + "]";
	}

}

/**
 * Refer: https://thuytrangcoding.wordpress.com/2018/02/13/ds-st-basic/
 * https://www.geeksforgeeks.org/segment-tree-efficient-implementation/
 * 
 * When use array to build a build binary tree:
 * 
 * with node i (i >= 0), then left child of i is 2*i+1 right child of i is 2*i+2
 * 
 * => we have a problem that declare the array with 2*N, that is not good idea.
 * So for replacement, we choose node i, root node is = 1 then left child of i
 * is 2*i right child of i is 2*i+1
 * 
 * 
 */
class SegmentTree {

	private List<SegmentData<Integer>> binaryTree;

	private List<Integer> arr;

	private final Integer rootNodeIndex = 1;

	public SegmentTree(List<Integer> arr) {
		this.arr = arr;
		this.initBinaryTreeArr();

	}

	/**
	 * 
	 */
	private void initBinaryTreeArr() {

		if (this.arr == null || this.arr.size() == 0) {
			throw new NullPointerException("Arr can not be null or empty.");
		}
		// caculate lograrit 2 of array size then upper value
		// with arr size: 12, 14, 15 => the nearest 2^n = 16 => n = 4, then the maximum
		// size for binary tree size is 2*2^4 = 32
		double base = Math.log(this.arr.size()) / Math.log(2);

		int binarySize = (int) (2 * Math.pow(2, Math.ceil(base)));

		System.out.println("binary tree size " + binarySize);

		this.binaryTree = new ArrayList<>(binarySize);
		for (int ind = 0; ind < binarySize; ind++) {
			this.binaryTree.add(new SegmentData<Integer>(0, 0, 0));
		}
	}

	public void buildSumRange() {
		this.buildSumRangeRecursion(this.rootNodeIndex, 0, this.arr.size() - 1);
	}

	/**
	 * 
	 * @param nodeInd
	 * @param l
	 * @param r
	 */
	private void buildSumRangeRecursion(Integer nodeInd, Integer l, Integer r) {

		if (l >= r) {
			SegmentData<Integer> segmentData = new SegmentData<Integer>();
			segmentData.setStartRange(l);
			segmentData.setEndRange(r);
			segmentData.setValue(this.arr.get(r));
			// System.out.println("left: " + l + ", right: " + r + ", nodeInd: " + nodeInd);
			this.binaryTree.set(nodeInd, segmentData);
			return;
		}

		int mid = (l + r) / 2;

		this.buildSumRangeRecursion(this.getLeftNodeInd(nodeInd), l, mid);

		this.buildSumRangeRecursion(this.getRightNodeInd(nodeInd), mid + 1, r);

		SegmentData<Integer> segmentData = new SegmentData<Integer>();
		segmentData.setStartRange(l);
		segmentData.setEndRange(r);

//		System.out.println("left " + l + ", right: " + r + ", nodeInd: " + nodeInd);
		Integer value = this.binaryTree.get(this.getLeftNodeInd(nodeInd)).getValue()
				+ this.binaryTree.get(this.getRightNodeInd(nodeInd)).getValue();
		segmentData.setValue(value);
		this.binaryTree.set(nodeInd, segmentData);

	}

	/**
	 * TODO next time
	 */
	public void buildMaxRange() {

	}

	/**
	 * We can use prefix sum to get sum in a range of an array, but currently im
	 * using binary tree, so do it :)))
	 * 
	 * @param l
	 * @param r
	 * @return
	 */
	public Integer getSumRange(Integer l, Integer r) {
		long startTime = System.nanoTime();
		if (l < 0 || r >= arr.size() || l > r) {
			System.out.println(
					"Please input the range in the range of arr size: [" + 0 + ", " + (this.arr.size() - 1) + "]");
			return -1;
		}

		if (l == r) {
			return this.arr.get(l);
		}

		Integer total = this.getSumRangeInSegmentTree(rootNodeIndex, l, r);

		long endTime = System.nanoTime();

		System.out.println("getSumRange() total execution time " + (endTime - startTime) / 1000000D + " ms");

		return total;
	}

	/**
	 * 
	 * @param rootInd
	 * @param l
	 * @param r
	 */
	private Integer getSumRangeInSegmentTree(Integer rootInd, Integer l, Integer r) {
		SegmentData<Integer> segmentData = this.binaryTree.get(rootInd);
		if (l.equals(segmentData.getStartRange()) && r.equals(segmentData.getEndRange())) {
			return segmentData.getValue();
		}

		Integer leftNodeInd = this.getLeftNodeInd(rootInd);
		if (leftNodeInd >= this.binaryTree.size()) {
			return 0;
		}

		Integer rightNodeInd = this.getRightNodeInd(rootInd);
		if (rightNodeInd >= this.binaryTree.size()) {
			return 0;
		}

		SegmentData<Integer> segmentLeftNodeData = this.binaryTree.get(leftNodeInd);
		SegmentData<Integer> segmentRightNodeData = this.binaryTree.get(rightNodeInd);
		// the first case, [l, r] totally belong to left node
		if (l >= segmentLeftNodeData.getStartRange() && r <= segmentLeftNodeData.getEndRange()) {
			return this.getSumRangeInSegmentTree(leftNodeInd, l, r);
			// the second case, [l, r] totally belong to right node
		} else if (l >= segmentRightNodeData.getStartRange() && r <= segmentRightNodeData.getEndRange()) {
			return this.getSumRangeInSegmentTree(rightNodeInd, l, r);
			// the final case, [l, r] belong to left and right node
		} else {
			return this.getSumRangeInSegmentTree(leftNodeInd, l, segmentLeftNodeData.getEndRange())
					+ this.getSumRangeInSegmentTree(rightNodeInd, segmentRightNodeData.getStartRange(), r);
		}
	}

	/**
	 * 
	 * @param ind
	 * @param value
	 */
	public void update(Integer ind, Integer value) {
		if (ind < 0 || ind >= this.arr.size()) {
			System.out.println("Please input the ind in the arr size: [" + 0 + ", " + (this.arr.size() - 1) + "]");
			return;
		}
		this.arr.set(ind, value);
		this.updateValueInSegmentTree(this.rootNodeIndex, ind, value);
	}

	/**
	 * 
	 * @param parentNodeInd
	 * @return
	 */
	private Integer getLeftNodeInd(Integer parentNodeInd) {
		return 2 * parentNodeInd;
	}

	/**
	 * 
	 * @param parentNodeInd
	 * @return
	 */
	private Integer getRightNodeInd(Integer parentNodeInd) {
		return 2 * parentNodeInd + 1;
	}

	/**
	 * 
	 * @param rootInd
	 * @param updateIndex
	 * @param value
	 */
	private void updateValueInSegmentTree(Integer rootInd, Integer updateIndex, Integer value) {
		SegmentData<Integer> segmentData = this.binaryTree.get(rootInd);

		if (segmentData.getStartRange().equals(updateIndex) && segmentData.getEndRange().equals(updateIndex)) {
			System.out.println("Update new value at binary tree index " + rootInd + ", value: " + value);
			segmentData.setValue(value);
			this.binaryTree.set(rootInd, segmentData);
			return;
		}

		if (updateIndex < segmentData.getStartRange() || updateIndex > segmentData.getEndRange()) {
			return;
		}

		this.updateValueInSegmentTree(this.getLeftNodeInd(rootInd), updateIndex, value);
		this.updateValueInSegmentTree(this.getRightNodeInd(rootInd), updateIndex, value);

		Integer newValue = this.binaryTree.get(this.getLeftNodeInd(rootInd)).getValue()
				+ this.binaryTree.get(this.getRightNodeInd(rootInd)).getValue();
		segmentData.setValue(newValue);
		this.binaryTree.set(rootInd, segmentData);

	}

	public List<SegmentData<Integer>> getBinaryTree() {
		return this.binaryTree;
	}

	public void print() {
		for (int ind = 0; ind < this.binaryTree.size(); ind++) {
			System.out.println(ind + " " + this.binaryTree.get(ind));
		}
	}

}

public class SegmentTreeTest {
	public static List<Integer> randoms(Integer size, Integer max, Integer min) {
		List<Integer> randomList = new ArrayList<>(size);

		for (int ind = 0; ind < size; ind++) {
			randomList.add((int) (Math.random() * max + min));
		}

		return randomList;
	}

	public static List<Integer> mockArr1() {
		return Arrays.asList(-1, 22, 90, 42, 60, 49, 65, -4, 40, -1, 27, 4, 41, 63, 66, 46, 77, 71, 73, 33, 58, 27, 92,
				15, 88, 47, 64, 59, 33, 76, 12, 35, 79, 3, 6, 68, 52, 0, 59, 79, 66, 47, 61, 36, 92, 66, 24, 50, 92, 54,
				17, 12, 30, 26, 85, 18, -2, 52, 41, 90, 89, 59, 53, 46, 91, -2, 12, 45, 50, 29, 70, 4, 92, 40, 51, 22,
				46, 58, 74, 66, 41, 81, 25, -4, 26, 32, 18, 21, 5, 69, 80, 52, 36, 3, 28, 67, 24, -3, 18, 57);

	}

	public static List<Integer> mockArr() {
		return Arrays.asList(-1, 22, 90, 42, 60, 49, 65, -4, 40, 11);

	}

	public static Integer sumInRange(List<Integer> arr, int l, int r) {
		long startTime = System.nanoTime();
		Integer res = 0;
		for (int ind = l; ind <= r; ind++) {
			res += arr.get(ind);
		}

		long endTime = System.nanoTime();

		System.out.println("sumInRange() total execution time " + (endTime - startTime) / 1000000D + " ms");

		return res;
	}

	public static void main(String[] args) {
		System.out.println("Segment Tree");
		List<Integer> list = randoms(1000000, 100, -5);
		// List<Integer> list = mockArr1();

		System.out.println(list.stream().reduce(0, (t, v) -> t + v));

		System.out.println(list);

		SegmentTree ds = new SegmentTree(list);
		ds.buildSumRange();
		// ds.print();

		System.out.println("############################ Update value ################################");
		ds.update(9, 21);
		// ds.print();

		System.out.println("############################ get sum from range ################################");
		System.out.println("Sum range from [3, 8] using segment tree: " + ds.getSumRange(3, 8) + ", using for loop: "
				+ sumInRange(list, 3, 8));
		System.out.println("Sum range from [0, 5] using segment tree: " + ds.getSumRange(0, 5) + ", using for loop: "
				+ sumInRange(list, 0, 5));
		System.out.println("Sum range from [5, 7] using segment tree: " + ds.getSumRange(5, 7) + ", using for loop: "
				+ sumInRange(list, 5, 7));
		System.out.println("Sum range from [4, 7] using segment tree: " + ds.getSumRange(4, 7) + ", using for loop: "
				+ sumInRange(list, 4, 7));
		System.out.println("Sum range from [0, 9] using segment tree: " + ds.getSumRange(0, 9) + ", using for loop: "
				+ sumInRange(list, 0, 9));
		System.out.println("Sum range from [45454, 745454] using segment tree: " + ds.getSumRange(45454, 745454)
				+ ", using for loop: " + sumInRange(list, 45454, 745454));
		System.out.println("Sum range from [454, 784933] using segment tree: " + ds.getSumRange(454, 784933)
				+ ", using for loop: " + sumInRange(list, 454, 784933));

	}

}
