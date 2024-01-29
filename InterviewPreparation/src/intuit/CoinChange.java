package intuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CoinChange {
    private static int helper(int[] coins, int idx, int target, Integer[][] dp) {
        if (target == 0) return 0;
        if (target < 0 || idx >= coins.length) return Integer.MAX_VALUE;
        if (dp[idx][target] != null) return dp[idx][target];
        int take = helper(coins, idx, target - coins[idx], dp);
        if (take == Integer.MAX_VALUE) {
            take = 0;
        } else {
            take += 1;
        }
        int skip = helper(coins, idx + 1, target, dp);
        int amt = Math.min(take, skip);

        return dp[idx][target] = amt;
    }

    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        Integer[][] dp = new Integer[n][amount + 1];
        return helper(coins, 0, amount, dp);

    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 10;
        System.out.println(coinChange(coins, amount));
    }
}
