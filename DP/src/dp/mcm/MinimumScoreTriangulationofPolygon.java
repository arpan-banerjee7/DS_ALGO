package dp.mcm;

import java.util.Arrays;
// https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
// Ref: https://www.youtube.com/watch?v=h6MxKmDimhg

/* n vertex polygon to n-2 triangles
we will fix the first and last vertex, and try to triangulate at each possible k
And find the min cost of among all of them
Leetcode discuss : https://leetcode.com/problems/minimum-score-triangulation-of-polygon/discuss/363482/Intuitive-Java-Solution-With-Explanation */

/* No of possible ways of triangulation is find using catalan of (n-2) 
Ref: https://www.youtube.com/watch?v=jSGW3YKkyHQ Pepcoding : Ways of Polygon Triangulation */

public class MinimumScoreTriangulationofPolygon {
	/*
	 * we will fix the first and last vertex, and try to triangulate at each
	 * possible k And find the min cost of among all of them
	 */
	int[][] dp;

	private int minScore(int[] arr, int i, int j) {
		// cannot form a triangle
		// it should have atleast 3 points, 1+1==j means only 2 points are there
		if (i + 1 == j) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;
		for (int k = i + 1; k < j; k++) {
			/*
			 * cost of triangulation at point k + min cost of triangulation(using all
			 * possible combintaions) with points i to k + min cost of triangulation(using
			 * all possible combintaions) with points k to j
			 */
			int temp = arr[i] * arr[k] * arr[j] + minScore(arr, i, k) + minScore(arr, k, j);
			min = Math.min(temp, min);
		}
		return dp[i][j] = min;
	}

	public int minScoreTriangulation(int[] values) {
		int n = values.length;
		dp = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], -1);
		}
		return minScore(values, 0, n - 1);
	}

	public static void main(String[] args) {
		int[] values = { 1, 2, 3 };
		MinimumScoreTriangulationofPolygon obj = new MinimumScoreTriangulationofPolygon();
		System.out.println(obj.minScoreTriangulation(values));// output-6
	}

}
