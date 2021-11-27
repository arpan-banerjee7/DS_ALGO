package assignments;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://www.youtube.com/watch?v=D_jE64CJ5UE  PepCoding
// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

class Tuple {
	char c;
	int f;

	Tuple(char c, int f) {
		this.c = c;
		this.f = f;
	}
}

public class ReorganizeString {
	public static String reorganizeString1(String s) {
		int n = s.length();

		// form the freq array
		int[] freq = new int[26];
		for (int i = 0; i < n; i++) {
			freq[s.charAt(i) - 'a']++;
		}

		// put it in a max heap, so that on each iteration we place the char
		// with max freq and block it for the next pos
		PriorityQueue<Tuple> maxHeap = new PriorityQueue<>((a, b) -> b.f - a.f);

		for (int i = 0; i < 26; i++) {
			if (freq[i] > 0) {
				maxHeap.add(new Tuple((char) (i + 'a'), freq[i]));
			}
		}

		// form the string by iterating through the pq
		StringBuilder res = new StringBuilder();
		Tuple blocked = maxHeap.poll();
		blocked.f--;
		res.append(blocked.c);
		while (!maxHeap.isEmpty()) {
			Tuple next = maxHeap.poll();
			res.append(next.c);
			next.f--;
			if (blocked.f != 0) {
				maxHeap.add(blocked);
			}
			blocked = next;

		}
		if (blocked.f > 0) {
			return "";
		}
		return res.toString();
	}

	public String reorganizeString2(String s) {

		// form the freq map
		Map<Character, Integer> freq = new HashMap<>();
		for (char c : s.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		// put it in a max heap, so that on each iteration we place the char
		// with max freq and block it for the next pos
		PriorityQueue<Tuple> maxHeap = new PriorityQueue<>((a, b) -> b.f - a.f);

		for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
			Tuple t = new Tuple(entry.getKey(), entry.getValue());
			maxHeap.add(t);
		}

		// form the string by iterating through the pq
		StringBuilder res = new StringBuilder();
		Tuple blocked = maxHeap.poll();
		blocked.f--;
		res.append(blocked.c);
		while (!maxHeap.isEmpty()) {
			Tuple next = maxHeap.poll();
			res.append(next.c);
			next.f--;
			if (blocked.f != 0) {
				maxHeap.add(blocked);
			}
			blocked = next;

		}
		if (blocked.f > 0) {
			return "";
		}
		return res.toString();
	}

	public String reorganizeString3(String s) {

		// form the freq map
		Map<Character, Integer> freq = new HashMap<>();
		for (char c : s.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		// put it in a max heap, so that on each iteration we place the char
		// with max freq and block it for the next pos
		PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));
		maxHeap.addAll(freq.keySet());

		// form the string by iterating through the pq
		StringBuilder res = new StringBuilder();
		char blocked = maxHeap.poll();
		freq.put(blocked, freq.get(blocked) - 1);
		res.append(blocked);
		while (!maxHeap.isEmpty()) {
			char next = maxHeap.poll();
			res.append(next);
			freq.put(next, freq.get(next) - 1);
			if (freq.get(blocked) != 0) {
				maxHeap.add(blocked);
			}
			blocked = next;

		}
		if (freq.get(blocked) > 0) {
			return "";
		}
		return res.toString();
	}
}
