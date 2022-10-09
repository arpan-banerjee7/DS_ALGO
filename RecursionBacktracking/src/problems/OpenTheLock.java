package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 752. Open the Lock
// https://leetcode.com/problems/open-the-lock/

public class OpenTheLock {

	public int openLock(String[] deadends, String target) {
		int level = 0;
		Set<String> deads = new HashSet<>(Arrays.asList(deadends));
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		if (deads.contains("0000"))
			return -1;
		queue.add("0000");
		visited.add("0000");

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String curr = queue.poll();
				if (curr.equals(target)) {
					return level;
				}
				for (int j = 0; j < 4; j++) {
					char ch = curr.charAt(j);
					String s1 = curr.substring(0, j) + (ch == '9' ? 0 : ch - '0' + 1) + curr.substring(j + 1);
					String s2 = curr.substring(0, j) + (ch == '0' ? 9 : ch - '0' - 1) + curr.substring(j + 1);

					if (!visited.contains(s1) && !deads.contains(s1)) {
						visited.add(s1);
						queue.add(s1);
					}

					if (!visited.contains(s2) && !deads.contains(s2)) {
						visited.add(s2);
						queue.add(s2);
					}
				}
			}
			level++;
		}
		return -1;
	}

	public static void main(String[] args) {
		/*
		 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
		 * Output: 6 Explanation: A sequence of valid moves would be "0000" -> "1000" ->
		 * "1100" -> "1200" -> "1201" -> "1202" -> "0202". Note that a sequence like
		 * "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid, because the
		 * wheels of the lock become stuck after the display becomes the dead end
		 * "0102".
		 */
	}

}
