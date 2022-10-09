package bit.masking.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//318. Maximum Product of Word Lengths
//https://leetcode.com/problems/maximum-product-of-word-lengths/description/

/*Java Hashmap- https://leetcode.com/problems/maximum-product-of-word-lengths/discuss/1234661/Java-%2B-HashMap-Concise-solution

Bit Msk- https://leetcode.com/problems/maximum-product-of-word-lengths/discuss/2085333/Short-Bitmask-Solution-(explained)-or-JAVA
Knowledge center- https://www.youtube.com/watch?v=E8Ctj36CzuY
*/

public class MaximumProductWordLengths {

	// using hashset.retainall()
	// set1.retainAll(set2) means set1 will retain all the elements of set2, if none
	// of the elements are common, set1 will be an empty set.

	// TC(o(MN)+o(MN))
	public int maxProduct(String[] words) {

		Map<Integer, Set<Character>> map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			Set<Character> set = new HashSet<>();
			for (int j = 0; j < words[i].length(); j++) {
				char c = words[i].charAt(j);
				set.add(c);

			}
			map.put(i, set);
		}

		int max = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				Set<Character> jSet = new HashSet<>(map.get(j));

				jSet.retainAll(map.get(i));

				if (jSet.isEmpty()) {
					int l = words[i].length() * words[j].length();

					max = Math.max(max, l);
				}
			}
		}
		return max;
	}

	// using bit manipulation

	// Time Complexity: O(n^2 + m) where n is the length of the words array and m is
	// the total length of all words in the array.
	// Space Complexity: O(n) due to the array of masks.

	public int maxProduct2(String[] words) {
		int n = words.length;
		int max = 0;
		int[] masks = new int[n];
		// converting words to binary numbers
		for (int i = 0; i < n; i++) {
			for (char ch : words[i].toCharArray()) {
				masks[i] |= (1 << (ch - 'a'));
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((masks[i] & masks[j]) == 0) {
					max = Math.max(max, words[i].length() * words[j].length());
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
