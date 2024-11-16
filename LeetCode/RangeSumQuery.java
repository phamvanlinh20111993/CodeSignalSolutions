package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * url: https://leetcode.com/problems/range-sum-query-mutable/description/?envType=problem-list-v2&envId=segment-tree
 * 
 * Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
 */
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
class SegmentTreeDS {

	private List<SegmentData<Integer>> binaryTree;

	private List<Integer> arr;

	private final Integer rootNodeIndex = 1;

	public SegmentTreeDS(List<Integer> arr) {
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

	//	System.out.println("binary tree size " + binarySize);

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
		//	System.out.println(
		//			"Please input the range in the range of arr size: [" + 0 + ", " + (this.arr.size() - 1) + "]");
			return -1;
		}

		if (l == r) {
			return this.arr.get(l);
		}

		Integer total = this.getSumRangeInSegmentTree(rootNodeIndex, l, r);

		long endTime = System.nanoTime();

	//	System.out.println("getSumRange() total execution time " + (endTime - startTime) / 1000000D + " ms");

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
		//	System.out.println("Please input the ind in the arr size: [" + 0 + ", " + (this.arr.size() - 1) + "]");
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
		//	System.out.println("Update new value at binary tree index " + rootInd + ", value: " + value);
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


class NumArray {
    private SegmentTreeDS ds;
    public NumArray(int[] nums) {
        List<Integer> numArr = new ArrayList<Integer>(nums.length);
        for (int i : nums){
            numArr.add(i);
        }

        ds = new SegmentTreeDS(numArr);
        ds.buildSumRange();
    }
    
    public void update(int index, int val) {
        ds.update(index, val);
    }
    
    public int sumRange(int left, int right) {
        return ds.getSumRange(left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */

public class RangeSumQuery {

}
