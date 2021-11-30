package assignments;

import java.util.ArrayList;
import java.util.List;

// 763. Partition Labels
// https://www.youtube.com/watch?v=B7m8UmZE-vw     Neetcode two pointers o(n) o(26) extra space
// https://leetcode.com/problems/partition-labels/

// TC -O(n)
public class PartitionLabels {

	public static List<Integer> partitionLabels(String s) {
		int n = s.length();
		List<Integer> res = new ArrayList<>();
		// find the last occurance of each char in s
		int[] lastIndex = new int[26];
		for (int i = 0; i < n; i++) {
			lastIndex[s.charAt(i) - 'a'] = i;
		}

		// fix the end pointer at the last occurance of current char
		int end = 0;
		int start = 0;
		for (int i = 0; i < n; i++) {
			end = Math.max(end, lastIndex[s.charAt(i) - 'a']);

			// all the letter before this index has their last index at this same
			// end point so it is one chunk
			if (i == end) {
				res.add(end - start + 1);
				start = end + 1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "ababcbacadefegdehijhklij";
		List<Integer> res = partitionLabels(s);
		System.out.println(res);
		/*
		 * Output: [9,7,8] Explanation: The partition is "ababcbaca", "defegde",
		 * "hijhklij". This is a partition so that each letter appears in at most one
		 * part. A partition like "ababcbacadefegde", "hijhklij" is incorrect, because
		 * it splits s into less parts.
		 */
	}

}
