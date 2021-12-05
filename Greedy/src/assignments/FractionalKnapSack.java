package assignments;

import java.util.Arrays;


/*Fractional Knapsack- beaware of double=int/int divsions- typecasting to double required
https://www.youtube.com/watch?v=F_DDzYnxO14&list=PLgUwDviBIf0pmWCl2nepwGDO05a0-7EfJ&index=6
	https://practice.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1#
	*/

public class FractionalKnapSack {
	static class Item {
		int value, weight;

		Item(int x, int y) {
			this.value = x;
			this.weight = y;
		}
	}

	// Function to get the maximum total value in the knapsack.
	static double fractionalKnapsack(int W, Item arr[], int n) {
		// Your code here
		// sorting based on value/weight ration
		Arrays.sort(arr, (a, b) -> {
			double r1 = (double) a.value / (double) a.weight;
			double r2 = (double) b.value / (double) b.weight;
			if (r1 < r2) {
				return 1;
			} else if (r1 > r2) {
				return -1;
			} else {
				return 0;
			}
		});

		int currWt = 0;
		double totalValue = 0.0;
		for (int i = 0; i < n; i++) {
			if (W - currWt >= arr[i].weight) {
				totalValue += arr[i].value;
				currWt += arr[i].weight;
			} else {
				int remain = W - currWt;
				totalValue += (((double) arr[i].value / (double) arr[i].weight) * (double) remain);
				currWt += remain;
				break;
			}
		}
		return totalValue;
	}

	public static void main(String[] args) {
		int N = 3, W = 50;
		int values[] = { 60, 100, 120 };
		int weight[] = { 10, 20, 30 };
		/*
		 * Output: 240.00 Explanation:Total maximum value of item we can have is 240.00
		 * from the given capacity of sack.
		 */

	}

}
