package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * url: https://leetcode.com/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150 
 * 
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
 

Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 * 
 */

/**
How LRU Works
Insertion: When a new item is added to the cache, it is stored at the most recently used position.
Access: When an item is accessed, it is moved to the most recently used position.
Eviction: When the cache reaches its maximum capacity, the item that has not been used for the longest time is removed.

 */

/**
How LRU Works
Insertion: When a new item is added to the cache, it is stored at the most recently used position.
Access: When an item is accessed, it is moved to the most recently used position.
Eviction: When the cache reaches its maximum capacity, the item that has not been used for the longest time is removed.

 */
// Thanks ChatGPT help some bug logics. thanks
class NodeC<T> {
	T val;
	NodeC<T> head;
	NodeC<T> tail;

	public NodeC(T val) {
		this.val = val;
		this.head = null;
		this.tail = null;
	}

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

class DoubledLinkedList<T> {
	private int size;
	private NodeC<T> headNodeC;
	private NodeC<T> tailNodeC;

	public DoubledLinkedList() {
        this.size = 0;
	}

	public DoubledLinkedList(T val) {
		this.headNodeC = new NodeC<T>(val);
		this.tailNodeC = headNodeC;
		this.size = 1;
	}

	public NodeC<T> addFirst(T val) {
        this.size++;
		NodeC<T> NodeC = new NodeC<T>(val);
		if (this.headNodeC == null) {
			this.headNodeC = NodeC;
			this.tailNodeC = NodeC;
			return NodeC;
		}

		NodeC<T> headTemp = this.headNodeC;
		NodeC.tail = headTemp;
		headTemp.head = NodeC;
		this.headNodeC = NodeC;

		return this.headNodeC;
	}

	public NodeC<T> addLast(T val) {
        this.size++;
		NodeC<T> NodeC = new NodeC<T>(val);
		if (this.tailNodeC == null) {
			this.tailNodeC = NodeC;
			this.headNodeC = NodeC;
			return NodeC;
		}

		NodeC<T> NodeCTail = this.tailNodeC;
		NodeC.head = NodeCTail;
		NodeCTail.tail = NodeC;
		this.tailNodeC = NodeC;

		return this.tailNodeC;
	}

	public NodeC<T> getFirstNodeC() {
		return this.headNodeC;
	}

	public NodeC<T> getLastNodeC() {
		return this.tailNodeC;
	}

	public void removeFirst() {
        if (this.headNodeC == null) return;
        this.size--;

		this.headNodeC = this.headNodeC.tail;
        if(this.headNodeC != null){
			this.headNodeC.head = null;
        }else{
            this.tailNodeC = null;
        }
	}

	public void removeLast() {
        if (this.tailNodeC == null) return;
        this.size--;
		this.tailNodeC = this.tailNodeC.head;
		if(this.tailNodeC != null){
			this.tailNodeC.tail = null;
        }else {
            this.headNodeC = null;
        }
	}

    public void remove(NodeC<T> node) {
        if (node == null) return;
        this.size--;
        NodeC<T> prev = node.head;
        NodeC<T> next = node.tail;
        // Case 1: node is head
        if (prev == null) {
            this.headNodeC = next;
        } else {
            prev.tail = next;
        }
        // Case 2: node is tail
        if (next == null) {
            this.tailNodeC = prev;
        } else {
            next.head = prev;
        }
        // Clean up (important)
        node.head = null;
        node.tail = null;
    }

    public T removeLastWithValue() {
        T val = this.tailNodeC.val;
        this.removeLast();
		return val;
	}

	public int getSize() {
		return size;
	}

	public void print() {
		NodeC<T> pointer = this.headNodeC;
		while (pointer != null) {
			System.out.print(pointer.val + " ");
			pointer = pointer.tail;
		}
		System.out.println();
	}

	public void reversePrint() {
		NodeC<T> pointer = this.tailNodeC;
		while (pointer != null) {
			System.out.print(pointer.val + " ");
			pointer = pointer.head;
		}
		System.out.println();
	}
}

class LRUCache {
    private int capacity;
	private int size;
	private Map<Integer, NodeC<KV>> LRUCacheMap;
	private DoubledLinkedList<KV> LRUCacheOrder;
	
	record KV(int k, int v) {}

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.LRUCacheMap = new HashMap<>();
		this.LRUCacheOrder = new DoubledLinkedList<>();
	}

	public int get(int key) {
		NodeC<KV> KVvalue = LRUCacheMap.get(key);
		// move to most recently used position
		if (KVvalue != null) {
            this.LRUCacheOrder.remove(KVvalue);
			NodeC<KV> newNode = this.LRUCacheOrder.addFirst(new KV(key, KVvalue.val.v));
            LRUCacheMap.put(key, newNode);
			return KVvalue.val.v;
		}
		return -1;
	}

	public void put(int key, int value) {
        NodeC<KV> KVvalue = this.LRUCacheMap.getOrDefault(key, null);
        if(KVvalue != null){
           this.LRUCacheOrder.remove(KVvalue);
           this.size--;
        }else if(this.size + 1 > capacity) {
			KV curK = this.LRUCacheOrder.removeLastWithValue();
            if(curK != null){
                this.LRUCacheMap.put(curK.k, null);
            }
		}
		NodeC<KV> dt = this.LRUCacheOrder.addFirst(new KV(key, value));
		this.LRUCacheMap.put(key, dt);   
		this.size++;
	}

	public static void main(String[] args) {
		DoubledLinkedList<Integer> doubledLinkedList = new DoubledLinkedList<>();
		doubledLinkedList.addFirst(4);
		doubledLinkedList.addFirst(3);
		doubledLinkedList.addFirst(2);
		doubledLinkedList.addFirst(1);
		doubledLinkedList.addFirst(0);

		doubledLinkedList.addLast(5);
		doubledLinkedList.addLast(6);
		doubledLinkedList.addLast(7);
		doubledLinkedList.addLast(8);
		doubledLinkedList.addLast(9);

		System.out.println("###########all ##################");
		doubledLinkedList.print();
		doubledLinkedList.reversePrint();

		System.out.println("############remove first #################");
		doubledLinkedList.removeFirst();
		doubledLinkedList.print();

		System.out.println("############remove last #################");
		doubledLinkedList.removeLast();
		doubledLinkedList.print();

	}

}
