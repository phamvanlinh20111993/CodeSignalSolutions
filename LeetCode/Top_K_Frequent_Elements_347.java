package LeetCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/top-k-frequent-elements/?envType=problem-list-v2&envId=bucket-sort
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2

Output: [1,2]

Example 2:

Input: nums = [1], k = 1

Output: [1]

Example 3:

Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2

Output: [1,2]

 

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.
 

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class Top_K_Frequent_Elements_347 {
	class Heap<T> {
	    private Object[] heap;
	    private Comparator<T> comparator;
	    private int capacity = 1 << 4;
	    private int size = 0;

	    public Heap(){
	        this.heap = new Object[capacity];
	        this.comparator = new Comparator<T>() {
	            @Override
	            public int compare(T s1, T s2) {
	                return s1.toString().compareTo(s2.toString());
	            }
	        };
	    }

	    public Heap(int capacity){
	        this.heap = new Object[capacity];
	        this.comparator = new Comparator<T>() {
	            @Override
	            public int compare(T s1, T s2) {
	                return s1.toString().compareTo(s2.toString());
	            }
	        };
	        this.capacity = capacity;
	    }

	    public Heap(Comparator<T> comparator){
	        this.heap = new Object[capacity];
	        this.comparator = comparator;
	    }

	    public Heap(T value, Comparator<T> comparator){
	        this.heap = new Object[capacity];
	        this.comparator = comparator;
	        this.heap[size++] = value;
	    }

	    private int getParentInd(int i){
	        if(i <= 0) return 0;
	        // if(i%2 == 0) return (i-2)/2;
	        return (i-1)/2;
	    }

	    private int getLeftChild(int parentInd){
	        return 2 * parentInd + 1;
	    }

	    private int getRightChild(int parentInd){
	        return 2 * parentInd + 2;
	    }

	    private void heapifyUp(){
	        Comparator<T> cpr = this.comparator;
	        int childInd = size;
	        while(childInd > 0){
	            int parentInd = getParentInd(childInd);
	            int cmp = cpr.compare((T)heap[parentInd], (T)heap[childInd]);
	            if(cmp >= 0){
	                swap(parentInd, childInd);
	            }
	            childInd = parentInd;
	        }
	    }

	    public void add(T value){
	        if(size+1 >= heap.length){
	            increaseHeapSize();
	        }
	        heap[size] = value;
	        heapifyUp();
	        size++;
	    }   

	    private void swap(int i, int j) {
	        Object temp = heap[i];
	        heap[i] = heap[j];
	        heap[j] = temp;
	    }

	    private void heapifyDown(int rootInd, int currSize, Comparator<T> cpr){
	        int leftChildInd = getLeftChild(rootInd);
	        int rightChildInd = getRightChild(rootInd);

	        if(leftChildInd >= currSize && rightChildInd >= currSize) return;

	        if(leftChildInd >= currSize){
	            int val = cpr.compare((T)heap[rootInd], (T)heap[rightChildInd]);
	            if(val < 0) return;
	            swap(rootInd, rightChildInd);
	            heapifyDown(rightChildInd, currSize, cpr);
	        }else if(rightChildInd >= currSize){
	            int val = cpr.compare((T)heap[rootInd], (T)heap[leftChildInd]);
	            if(val < 0) return;
	            swap(rootInd, leftChildInd);
	            heapifyDown(leftChildInd, currSize, cpr);
	        }else{
	           int val = cpr.compare((T)heap[rightChildInd], (T)heap[leftChildInd]); 
	           if(val < 0){
	                int val1 = cpr.compare((T)heap[rootInd], (T)heap[rightChildInd]);
	                if(val1 >= 0){
	                    swap(rootInd, rightChildInd);
	                    heapifyDown(rightChildInd, currSize, cpr);
	                }
	           }else{
	                int val2 = cpr.compare((T)heap[rootInd], (T)heap[leftChildInd]);
	                if(val2 >= 0){
	                    swap(rootInd, leftChildInd);
	                    heapifyDown(leftChildInd, currSize, cpr);
	                }
	           }
	        }
	    }

	    // heap sort
	    public List<T> toList(){
	        List<T> list = new ArrayList<>();
	        if(size <= 0) return list;
	        Comparator<T> cpr = this.comparator;
	        int currSize = size;

	        while(currSize > 0){
	            list.add((T) heap[0]);
	            heap[0] = heap[currSize-1];
	            heapifyDown(0, currSize, cpr);
	            currSize --;
	        }

	        return list;
	    }

	    private void increaseHeapSize(){
	        Object[] newHeap = new Object[capacity*((size/capacity)+1)];
	        for(int ind = 0; ind < size; ind++){
	            newHeap[ind] = heap[ind];
	        }
	        this.heap = newHeap;
	    }

	    public void dfs(int root, int lv){
	        if(root >= this.size) return ;
	        for(int ind = 0; ind < 2*lv; ind++){
	            System.out.print("--");
	        }
	        System.out.println(heap[root]);
	        dfs(getLeftChild(root), lv+1); 
	        dfs(getRightChild(root), lv+1); 
	    }

	    public int size(){
	        return this.size;
	    }
	}

	class Solution {

	    record KV(int k, int v){}

	    public int[] topKFrequent(int[] nums, int k) {
	      /*  Heap<Integer> heap = new Heap<>(new Comparator<Integer>() {
	            @Override
	            public int compare(Integer s1, Integer s2) {
	                if(s1 == null || s2 == null) return 1;
	                if(s1 > s2) return -1;
	                else if(s1 == s2) return 0;
	                return 1;
	            }
	        });

	        for(int ind = 0; ind < nums.length; ind++){
	            heap.add(nums[ind]);
	        }

	        heap.dfs(0, 0);
	        List<Integer> list = heap.toList();
	        System.out.println(list +  "size " + list.size());

	        return new int[]{0,0}; */

	        Map<Integer, Integer> mapC = new HashMap<>();
	        for(int ind = 0; ind < nums.length; ind++){
	            int v = mapC.getOrDefault(nums[ind], 0);
	            mapC.put(nums[ind], v+1);
	        }

	        Heap<KV> heap = new Heap<KV>(new Comparator<KV>() {
	            @Override
	            public int compare(KV s1, KV s2) {
	                if(s1 == null || s2 == null) return 1;
	                if(s1.v > s2.v) return -1;
	                else if(s1.v == s2.v) return s1.k > s1.k ? 1 : -1;
	                return 1;
	            }
	        });

	        for(Map.Entry<Integer, Integer> entry : mapC.entrySet()){
	            heap.add(new KV(entry.getKey(), entry.getValue()));
	        }

	        List<KV> listKV = heap.toList();
	    //    System.out.println(listKV +  "size " + listKV.size());

	        int minFrequence = Math.min(k, nums.length);
	        int [] res = new int[minFrequence];
	        for(int ind = 0; ind < minFrequence; ind++){
	            res[ind] = listKV.get(ind).k;
	        }

	        return res;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
