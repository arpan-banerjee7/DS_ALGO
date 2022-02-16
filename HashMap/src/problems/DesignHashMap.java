package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
// https://leetcode.com/problems/design-hashmap/

class HashNode {
	int key;
	int value;
	int hashCode;
	HashNode next;

	HashNode(int key, int value, int hashCode) {
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}
}

class MyHashMap {
	List<HashNode> bucketArray;
	int bucketSize;

	/** Initialize your data structure here. */
	public MyHashMap() {
		this.bucketArray = new ArrayList<>();
		this.bucketSize = 16;

		// Create empty chains
		for (int i = 0; i < bucketSize; i++)
			bucketArray.add(null);
	}

	private int hashCode(int key) {
		return Integer.hashCode(key);
	}

	private int getBucketIndex(int key) {
		int idx = hashCode(key) % bucketSize;
		return idx < 0 ? idx * -1 : idx;
	}

	/** value will always be non-negative. */
	public void put(int key, int value) {
		int hashCode = hashCode(key);
		int bucketIndex = getBucketIndex(key);

		// check if already present
		// get the head of te=he ll in that bucket
		HashNode head = bucketArray.get(bucketIndex);
		while (head != null) {
			if (head.key == key) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// inserting at the begining of the LL, so reseting the head pointer
		head = bucketArray.get(bucketIndex);
		HashNode newNode = new HashNode(key, value, hashCode);
		newNode.next = head;
		// till now the bucket array has the reference to the head, which is pointing
		// to the node already present

		// this will make the bucket at that index to point at the new node, thereby
		// changing the head
		bucketArray.set(bucketIndex, newNode);
	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this map
	 * contains no mapping for the key
	 */
	public int get(int key) {
		int hashCode = hashCode(key);
		int bucketIndex = getBucketIndex(key);

		HashNode head = bucketArray.get(bucketIndex);
		while (head != null) {
			if (head.key == key) {
				return head.value;
			}
			head = head.next;
		}
		// key not found
		return -1;

	}

	/**
	 * Removes the mapping of the specified value key if this map contains a mapping
	 * for the key
	 */
	public void remove(int key) {
		int hashCode = hashCode(key);
		int bucketIndex = getBucketIndex(key);

		HashNode head = bucketArray.get(bucketIndex);
		HashNode prev = null;

		// after this, if key is founf head will point to the node having the key
		// and prev will point to a node before it
		while (head != null) {
			if (head.key == key) {
				break;
			}
			prev = head;
			head = head.next;
		}
		if (head == null) {
			return;
		}
		// key found, now de-link that node
		if (prev != null) {
			prev.next = head.next;
		} else { // head has the key
			bucketArray.set(bucketIndex, head.next);
		}

	}
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */

//--------------------------------------------------- full implementation not required--------------------------------//
// A node of chains
/*class HashNode<K, V> {
	K key;
	V value;
	final int hashCode;

	// Reference to next node
	HashNode<K, V> next;

	// Constructor
	public HashNode(K key, V value, int hashCode) {
		this.key = key;
		this.value = value;
		this.hashCode = hashCode;
	}
}

// Class to represent entire hash table
class DesignHashMap<K, V> {
	// bucketArray is used to store array of chains
	private ArrayList<HashNode<K, V>> bucketArray;

	// Current capacity of array list
	private int numBuckets;

	// Current size of array list
	private int size;

	// Constructor (Initializes capacity, size and
	// empty chains.
	public DesignHashMap() {
		bucketArray = new ArrayList<>();
		numBuckets = 10;
		size = 0;

		// Create empty chains
		for (int i = 0; i < numBuckets; i++)
			bucketArray.add(null);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private final int hashCode(K key) {
		return Objects.hashCode(key);
	}

	// This implements hash function to find index
	// for a key
	private int getBucketIndex(K key) {
		int hashCode = hashCode(key);
		int index = hashCode % numBuckets;
		// key.hashCode() coule be negative.
		index = index < 0 ? index * -1 : index;
		return index;
	}

	// Method to remove a given key
	public V remove(K key) {
		// Apply hash function to find index for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);
		// Get head of chain
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Search for key in its chain
		HashNode<K, V> prev = null;
		while (head != null) {
			// If Key found
			if (head.key.equals(key) && hashCode == head.hashCode)
				break;

			// Else keep moving in chain
			prev = head;
			head = head.next;
		}

		// If key was not there
		if (head == null)
			return null;

		// Reduce size
		size--;

		// Remove key
		if (prev != null)
			prev.next = head.next;
		else
			bucketArray.set(bucketIndex, head.next);

		return head.value;
	}

	// Returns value for a key
	public V get(K key) {
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);

		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Search key in chain
		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode)
				return head.value;
			head = head.next;
		}

		// If key not found
		return null;
	}

	// Adds a key value pair to hash
	public void add(K key, V value) {
		// Find head of chain for given key
		int bucketIndex = getBucketIndex(key);
		int hashCode = hashCode(key);
		HashNode<K, V> head = bucketArray.get(bucketIndex);

		// Check if key is already present
		while (head != null) {
			if (head.key.equals(key) && head.hashCode == hashCode) {
				head.value = value;
				return;
			}
			head = head.next;
		}

		// Insert key in chain
		size++;
		head = bucketArray.get(bucketIndex);
		HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
		newNode.next = head;
		bucketArray.set(bucketIndex, newNode);

		// If load factor goes beyond threshold, then
		// double hash table size
		if ((1.0 * size) / numBuckets >= 0.7) {
			ArrayList<HashNode<K, V>> temp = bucketArray;
			bucketArray = new ArrayList<>();
			numBuckets = 2 * numBuckets;
			size = 0;
			for (int i = 0; i < numBuckets; i++)
				bucketArray.add(null);

			for (HashNode<K, V> headNode : temp) {
				while (headNode != null) {
					add(headNode.key, headNode.value);
					headNode = headNode.next;
				}
			}
		}
	}

	// Driver method to test Map class
	public static void main(String[] args) {
		DesignHashMap<String, Integer> map = new DesignHashMap<>();
		map.add("this", 1);
		map.add("coder", 2);
		map.add("this", 4);
		map.add("hi", 5);
		System.out.println(map.size());
		System.out.println(map.remove("this"));
		System.out.println(map.remove("this"));
		System.out.println(map.size());
		System.out.println(map.isEmpty());
	}
}*/
