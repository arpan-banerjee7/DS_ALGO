package graph.algorithims;

import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/disjoint-set-data-structure-union-find-algorithm/

public class DSUFBrurteForce {
	// A class to represent a disjoint set
	private Map<Integer, Integer> parent = new HashMap<>();

	// perform MakeSet operation
	public void makeSet(int[] universe) {
		// create `n` disjoint sets (one for each item)
		for (int i : universe) {
			parent.put(i, i);
		}
	}

	// Find the root of the set in which element `k` belongs
	public int Find(int k) {
		// if `k` is root
		if (parent.get(k) == k) {
			return k;
		}

		// recur for the parent until we find the root
		return Find(parent.get(k));
	}

	// Perform Union of two subsets
	public void Union(int a, int b) {
		// find the root of the sets in which elements
		// `x` and `y` belongs
		int x = Find(a);
		int y = Find(b);

		parent.put(x, y);
	}

	public static void printSets(int[] universe, DSUFBrurteForce ds) {
		for (int i : universe) {
			System.out.print(ds.Find(i) + " ");
		}

		System.out.println();
	}

	// Disjoint–Set data structure (Union–Find algorithm)
	public static void main(String[] args) {
		// universe of items
		int[] universe = { 1, 2, 3, 4, 5 };

		// initialize `DisjointSet` class
		DSUFBrurteForce ds = new DSUFBrurteForce();

		// create a singleton set for each element of the universe
		ds.makeSet(universe);
		printSets(universe, ds);

		ds.Union(4, 3); // 4 and 3 are in the same set
		printSets(universe, ds);

		ds.Union(2, 1); // 1 and 2 are in the same set
		printSets(universe, ds);

		ds.Union(1, 3); // 1, 2, 3, 4 are in the same set
		printSets(universe, ds);
	}
}
