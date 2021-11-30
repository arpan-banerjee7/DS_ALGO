package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Notes-- Examples of comparator
https://leetcode.com/submissions/detail/594288888/
Arrays.sort(events, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
https://java-journal.blogspot.com/2011/01/when-to-use-comparable-and-when-to-use.html
 
Default implementation of Arrays.sort and collection.sort in java - https://www.youtube.com/watch?v=oM3g-rkUlaE
 */

class Tuple1 implements Comparable<Tuple1> {
	char c;
	int cost;
	int idx;

	Tuple1(char c, int cost, int idx) {
		this.c = c;
		this.cost = cost;
		this.idx = idx;
	}

	public Tuple1() {
	}

	@Override
	public int compareTo(Tuple1 o) {
		if (this.cost < o.cost) {
			return 1;
		} else if (this.cost == o.cost) {
			if (this.idx < o.idx) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}
//		if (this.cost < o.cost) {
//			return 1;
//		} else if (this.cost > o.cost) {
//			return -1;
//		}
//		return 0;
//	}
}

public class JavaComparatorComparableTest {

	public static void main(String[] args) {
		String s = "aabaa";
		int[] cost = { 1, 2, 3, 4, 1 };
		Arrays.sort(cost);
		String s1 = "330";
		String s2 = "303";
		int a = 440;
		int b = 560;
		String k = String.valueOf(b);
		// System.out.println(a.compareTo(b)); // does not work
		System.out.println(s1.compareTo(s2));

		// System.out.println(minCost(s,cost));

		Tuple1 t1 = new Tuple1();
		t1.c = 'a';
		t1.cost = 10;
		t1.idx = 0;

		Tuple1 t2 = new Tuple1();
		t2.c = 'b';
		t2.cost = 10;
		t2.idx = 4;

		Tuple1 t3 = new Tuple1();
		t3.c = 'c';
		t3.cost = 5;
		t3.idx = 14;

		Tuple1 t4 = new Tuple1();
		t4.c = 'd';
		t4.cost = 17;
		t4.idx = 9;
		List<Tuple1> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);

		Collections.sort(list);

		list.forEach(e -> System.out.print(e.cost + " " + e.c + "-->" + e.idx + " || "));
		Collections.sort(list, (x, y) -> x.cost == y.cost ? x.idx - y.idx : x.cost - y.cost);
		System.out.println();
		list.forEach(e -> System.out.print(e.cost + " " + e.c + "-->" + e.idx + " || "));

	}

}
