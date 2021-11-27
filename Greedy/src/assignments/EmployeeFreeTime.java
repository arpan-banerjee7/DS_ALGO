package assignments;
//Ref: https://medium.com/algorithm-and-datastructure/employee-free-time-795c7682c973

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Leetcode 759 and Lintcode 850 - Employee Free Time

/*
* We are given a list schedule of employees, which represents the working time for each employee.
	Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
	Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
	
	Example 1:
	Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
	Output: [[3,4]]
	Explanation:
	There are a total of three employees, and all common
	free time intervals would be [-inf, 1], [3, 4], [10, inf].
	We discard any intervals that contain inf as they aren't finite.
	
	Example 2:
	Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
	Output: [[5,6],[7,9]]
	(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
	Also, we wouldn’t include intervals like [5, 5] in our answer, as they have zero length.
	
	Note:
	schedule and schedule[i] are lists with lengths in range [1, 50].
	0 <= schedule[i].start < schedule[i].end <= 10^8.
*/

/*
 * uses concept of mnerge intervals- Leetcode 56
 * Employee free time - uses this conecpt

 * Steps-
 * 1. Use list.addall to add all the intervals into one list
 * 2. Merge the intervals
 * 3. Find the gap bw the intervals
 */
public class EmployeeFreeTime {
	private static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

	}

	public static void main(String[] args) {
		List<List<Interval>> schedule = buildInput();
		List<Interval> res = employeeFreeTime(schedule);
		for (Interval i : res) {
			System.out.println(" [" + i.start + ", " + i.end + "] ");
		}
	}

	private static List<List<Interval>> buildInput() {
		List<Interval> intervals1 = new ArrayList<>();
		intervals1.add(new Interval(1, 2));
		intervals1.add(new Interval(5, 6));

		List<Interval> intervals2 = new ArrayList<>();
		intervals2.add(new Interval(1, 3));

		List<Interval> intervals3 = new ArrayList<>();
		intervals3.add(new Interval(4, 10));

		List<List<Interval>> lists = new ArrayList<>();
		lists.add(intervals1);
		lists.add(intervals2);
		lists.add(intervals3);
		return lists;
	}

	public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
		if (schedule == null || schedule.size() == 0)
			return null;
		List<Interval> list = new ArrayList<>();
		for (List<Interval> l : schedule) {
			list.addAll(l);
		}
		List<Interval> merged = merge(list);
		if (merged.size() == 1)
			return null;
		List<Interval> result = new ArrayList<>();
		for (int i = 0; i < merged.size() - 1; i++) {
			Interval curr = merged.get(i);
			Interval next = merged.get(i + 1);

			if (curr.end < next.start) {
				result.add(new Interval(curr.end, next.start));
			}
		}
		return result;
	}

	private static List<Interval> merge(List<Interval> intervals) {
		// intervals.sort(Comparator.comparingInt(i -> i.start));
		Collections.sort(intervals, (a, b) -> a.start - b.start);

		if (intervals.size() == 0)
			return intervals;
		if (intervals.size() == 1)
			return intervals;

		List<Interval> res = new ArrayList<>();
		res.add(intervals.get(0));
		int k = 0;
		for (int i = 1; i < intervals.size(); i++) {
			// overlap
			if (res.get(k).end >= intervals.get(i).start) {
				res.set(k, new Interval(Math.min(res.get(k).start, intervals.get(i).start),
						Math.max(res.get(k).end, intervals.get(i).end)));
			}
			// no overlap
			else {
				res.add(intervals.get(i));
				k++;
			}
		}
		return res;
	}
}
