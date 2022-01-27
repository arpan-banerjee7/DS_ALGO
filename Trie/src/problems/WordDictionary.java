package problems;


//211. Design Add and Search Words Data Structure

//Ref: https://leetcode.com/problems/design-add-and-search-words-data-structure/discuss/452734/Java-Trie
//Ref: https://www.youtube.com/watch?v=4OyefqK-LDA Knowledge Center
public class WordDictionary {

	public static void main(String[] args) {

	}

	private TrieNodes root;

	public WordDictionary() {
		root = new TrieNodes();
	}

	public void addWord(String word) {
		TrieNodes pCrawl = root;

		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNodes();

			pCrawl = pCrawl.children[index];
		}

		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return partSearch(word, root);
	}

	public boolean partSearch(String word, TrieNodes start) {
		TrieNodes pCrawl = start;
		for (int i = 0; i < word.length(); i++) {

			// Speacial case for dots '.'
			char c = word.charAt(i);
			if (c == '.') {
				for (TrieNodes t : pCrawl.children) {
					if (t != null && partSearch(word.substring(i + 1), t))
						return true;
				}
				return false;
			} else {
				int index = word.charAt(i) - 'a';
				if (pCrawl.children[index] == null)
					return false;

				pCrawl = pCrawl.children[index];
			}

		}

		return (pCrawl != null && pCrawl.isEndOfWord);
	}
}

class TrieNodes {
	TrieNodes children[] = new TrieNodes[26];
	boolean isEndOfWord;
}