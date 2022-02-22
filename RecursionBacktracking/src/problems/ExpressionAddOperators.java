package problems;

import java.util.ArrayList;
import java.util.List;

// 282. Expression Add Operators
// https://leetcode.com/problems/expression-add-operators/
// https://www.youtube.com/watch?v=WcgjFrZceU8

public class ExpressionAddOperators {

	// 3 cases-

	// 1. number can be of multiple digits, so take care of that in the foor
	// loop(j=i), substr(i,j+1)

	// 2. there should not be any leading 0's in the operands(eliinate this by check
	// if j>1 && arr[i]==0 break) e.g- 1205--> (1+2+0 || 1+2-0 || 1+2*0), next iter
	// currNum=05 so it will break here, (1+2+05 --> not allowed so break), return
	// back to calling function- which will go to its next iteration that is 1+20 ||
	// 1-20 || 1*20

	// 3. trick to follow BODMAS rule,
	// Case '+': 732--> at a stage 7+3*2-- resSoFar=10,prev=3, now 10*2=20 which is
	// wrong, we need to somehow rewind, thats why we maintain a prev variable, 7+3,
	// resSoFar=10, prev=3, now 7+3*2= resSoFar-prev+prev*currNum= 10-3+3*2=7+6=13

	// Case '-': 7-3--> resSoFar=4,prev=-3, now 7-3*2= 4-(-3)+(-3)*2=7-6=1

	// Case '*': 7*3+2--> resSoFar=23, prev=2, now 7*3+2*2=(23-2)+(2*2)=25,
	// resSoFar=25, prev=cur*prev=2*2=4, now 7*3+2*2*3= 25-4 + 4*3=33

	private void dfs(int i, List<String> res, String path, long resSoFar, long prev, String s, int target) {
		if (i == s.length()) {
			if (resSoFar == target) {
				res.add(path);
				return;
			}
		}

		for (int j = i; j < s.length(); j++) {
			if (j > i && s.charAt(i) == '0')
				break; // ignore leading 0s
			long curr = Long.parseLong(s.substring(i, j + 1));

			// first iteration, path is just starting, no previous elements
			if (i == 0) {
				dfs(j + 1, res, path + curr, curr, curr, s, target);
			} else {
				dfs(j + 1, res, path + "+" + curr, resSoFar + curr, curr, s, target);
				dfs(j + 1, res, path + "-" + curr, resSoFar - curr, -curr, s, target);
				dfs(j + 1, res, path + "*" + curr, resSoFar - prev + prev * curr, prev * curr, s, target);
			}
		}
	}

	// to return the count
	private static int dfs(int i, String path, long resSoFar, long prev, String s, int target) {
		if (i == s.length()) {
			if (resSoFar == target) {
				return 1;
			}
			return 0;
		}
		int count = 0;
		for (int j = i; j < s.length(); j++) {
			if (j > i && s.charAt(i) == '0')
				break; // ignore leading 0s
			long curr = Long.parseLong(s.substring(i, j + 1));

			// first iteration, path is just starting, no previous elements
			if (i == 0) {
				count += dfs(j + 1, path + curr, curr, curr, s, target);
			} else {
				count += dfs(j + 1, path + "+" + curr, resSoFar + curr, curr, s, target);
				count += dfs(j + 1, path + "-" + curr, resSoFar - curr, -curr, s, target);
				count += dfs(j + 1, path + "*" + curr, resSoFar - prev + prev * curr, prev * curr, s, target);

			}
		}
		return count;
	}

	public List<String> addOperators(String num, int target) {
		List<String> res = new ArrayList<>();
		dfs(0, res, "", 0, 0, num, target);
		return res;
	}

	public static void main(String[] args) {
		String num = "123";
		int target = 6;
		// Output: ["1*2*3","1+2+3"]

	}

}
