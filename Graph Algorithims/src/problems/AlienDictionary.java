package problems;

import java.util.LinkedList;
import java.util.Stack;

// Alien Dictionary 
// https://practice.geeksforgeeks.org/problems/alien-dictionary/1/

public class AlienDictionary {
	private void topoSortUtil(int u, LinkedList<Integer>[] adj, boolean[] vis, Stack<Integer> st) {
		vis[u] = true;
		for (int it : adj[u]) {
			if (!vis[it]) {
				topoSortUtil(it, adj, vis, st);
			}
		}
		st.push(u);
	}

	private String topologicalSort(LinkedList<Integer>[] adj, int k) {
		boolean[] vis = new boolean[k];
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < k; i++) {
			if (!vis[i]) {
				topoSortUtil(i, adj, vis, st);
			}
		}

		// build result
		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty()) {
			sb.append((char) (st.pop() + 'a'));
		}
		return sb.toString();
	}

	public String findOrder(String[] dict, int N, int K) {
		// build adjaceny list
		LinkedList<Integer>[] adj = new LinkedList[K];
		for (int i = 0; i < K; i++) {
			adj[i] = new LinkedList<Integer>();
		}

		// add the edges
		for (int i = 0; i < N - 1; i++) {
			String word1 = dict[i];
			String word2 = dict[i + 1];

			for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
				if (word1.charAt(j) != word2.charAt(j)) {
					adj[word1.charAt(j) - 'a'].add(word2.charAt(j) - 'a');
					break;
				}
			}
		}

		String res = topologicalSort(adj, K);

		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: N = 5, K = 4 dict = {"baa","abcd","abca","cab","cad"} Output: 1
		 * Explanation: Here order of characters is 'b', 'd', 'a', 'c' Note that words
		 * are sorted and in the given language "baa" comes before "abcd", therefore 'b'
		 * is before 'a' in output. Similarly we can find other orders.
		 */
	}

}
