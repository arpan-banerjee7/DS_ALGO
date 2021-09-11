package stackqueue.problems;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;


//https://www.geeksforgeeks.org/lru-cache-implementation/

// Leetcode 146. LRU Cache : https://leetcode.com/problems/lru-cache/
// https://youtu.be/xDEuM5qa0zg Striver
// https://www.youtube.com/watch?v=Xc4sICC8m4M&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma  (Code)
// https://github.com/striver79/SDESheet/blob/main/lruCacheJava

public class LRUCache {

	// store keys of cache
	private Deque<Integer> doublyQueue;

	// store references of key in cache
	private HashSet<Integer> hashSet;

	// maximum capacity of cache
	private final int CACHE_SIZE;

	LRUCache(int capacity) {
		doublyQueue = new LinkedList<>();
		hashSet = new HashSet<>();
		CACHE_SIZE = capacity;
	}

	/* Refer the page within the LRU cache */
	public void refer(int page) {
		if (!hashSet.contains(page)) {
			if (doublyQueue.size() == CACHE_SIZE) {
				int last = doublyQueue.removeLast();       
				hashSet.remove(last);
			}
		} else {/*
				 * The found page may not be always the last element, even if it's an
				 * intermediate element that needs to be removed and added to the start of the
				 * Queue
				 */
			doublyQueue.remove(page);   // Check : this remove operation takes O(n) 
		}
		doublyQueue.push(page);
		hashSet.add(page);
	}

	// display contents of cache
	public void display() {
		Iterator<Integer> itr = doublyQueue.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(4);
		cache.refer(1);
		cache.refer(2);
		cache.refer(3);
		cache.refer(1);
		cache.refer(4);
		cache.refer(5);
		cache.refer(2);
		cache.refer(2);
		cache.refer(1);
		cache.display();
	}

}


