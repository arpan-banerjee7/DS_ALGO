package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/insert-delete-getrandom-o1/
// https://www.youtube.com/watch?v=WtkwD7ikxfg

public class InsertDelGetRandomO1 {

		Map<Integer, Integer> map; // fater lookup by values
		List<Integer> lis; // for mainting the indices--

		/** Initialize your data structure here. */
		public InsertDelGetRandomO1() {
			this.map = new HashMap<>();
			this.lis = new ArrayList<>();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already contain
		 * the specified element.
		 */
		public boolean insert(int val) {
			if (map.containsKey(val)) {
				return false;
			}
			lis.add(val);
			map.put(val, lis.size() - 1);
			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified
		 * element.
		 */
		// tricky part is here
		public boolean remove(int val) {
			if (!map.containsKey(val)) {
				return false;
			}
			int idx = map.get(val);
			int lastElement = lis.get(lis.size() - 1);
			lis.set(idx, lastElement); // effectively deleting given value
			lis.remove(lis.size() - 1);
			map.put(lastElement, idx); // swapping the index of the last element in the map
			map.remove(val);
			return true;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			// generate a random number bw 0 to lis.size()-1
			int max = lis.size();
			int min = 0;
			int randInd = (int) (Math.random() * (max - min) + min);
			return lis.get(randInd);
		}
	}

	/**
	 * Your RandomizedSet object will be instantiated and called as such:
	 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
	 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
	 */

