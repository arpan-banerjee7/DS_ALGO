package problems;

import java.util.List;

class Node {
	Node links[] = new Node[26];
	boolean flag = false;

	public Node() {

	}

	boolean containsKey(char ch) {
		return (links[ch - 'a'] != null);
	}

	Node get(char ch) {
		return links[ch - 'a'];
	}

	void put(char ch, Node node) {
		links[ch - 'a'] = node;
	}

	void setEnd() {
		flag = true;
	}

	boolean isEnd() {
		return flag;
	}
};

public class LargestWordWithAllPrefixes {
	private Node root;

	// Initialize your data structure here

	LargestWordWithAllPrefixes() {
		root = new Node();
	}

	/** Inserts a word into the trie. */
	void insert(String word) {
		Node node = root;
		for (int i = 0; i < word.length(); i++) {
			if (!node.containsKey(word.charAt(i))) {
				node.put(word.charAt(i), new Node());
			}
			node = node.get(word.charAt(i));
		}
		node.setEnd();
	}

	boolean checkIfAllPrefixExists(String word) {
		Node node = root;
		boolean flag = true;
		for (int i = 0; i < word.length() && flag; i++) {
			if (node.containsKey(word.charAt(i))) {
				node = node.get(word.charAt(i));
				flag = flag && node.isEnd();
			} else {
				return false;
			}
		}
		return flag;
	}

	String completeString(int n, List<String> a) {
		LargestWordWithAllPrefixes obj = new LargestWordWithAllPrefixes();
		for (String word : a)
			obj.insert(word);
		String longest = "";
		for (String word : a) {
			if (obj.checkIfAllPrefixExists(word)) {
				if (word.length() > longest.length()) {
					longest = word;
				} else if (word.length() == longest.length() && word.compareTo(longest) < 0) {
					longest = word;
				}
			}
		}
		if (longest == "")
			return "None";
		return longest;
	}
}