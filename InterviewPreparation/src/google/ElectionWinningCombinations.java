package google;

import java.util.*;

// Similar to count subsets with a given sum, base case changed sum>=targer sum
/*
How many ways can the party win the election? Imagine that each state is assigned a number of votes as follows:
Alabama = 9
Alaska = 3
Arizona = 11
Arkansas = 6
Wyoming = 3
For the purpose of this question, assume that all states are "winner takes all." The winner of a state gets all the votes for that state.
To be elected, a candidate must win more than total_votes / 2.
The candidate from Party 1 is running against the candidate from Party 2. candidate. How many different combinations of states won by Party 1 are there that will elect the Party 1 candidate?
For example, if Party 1 wins every state, the Party 1 candidate wins. If they win every state but lose Alabama, the candidate still wins.
Another example with three states: StateA = 3 StateB = 4 StateC = 8 If Party 1 wins every state, their candidate will get elected. If they win StateA and StateC, their candidate will still get elected. If they win StateA and StateB but lose StateC, their candidate will not get elected

 */
public class ElectionWinningCombinations {

    private static int countWinningCombinations(List<Integer> votes, int requiredVotes) {
        int n = votes.size();
        int[][] memo = new int[n][requiredVotes + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return findCombinations(votes, 0, 0, requiredVotes, memo);
    }

    private static int findCombinations(List<Integer> votes, int index, int currentSum, int requiredVotes, int[][] memo) {
        // Base case: if currentSum already exceeds requiredVotes, this is a valid combination
        if (currentSum >= requiredVotes) {
            return 1;
        }

        // If we've exhausted all states, return 0
        if (index >= votes.size()) {
            return 0;
        }

        // Check memoization table
        if (memo[index][currentSum] != -1) {
            return memo[index][currentSum];
        }

        // Recursive case: explore both including and excluding the current state
        int includeCurrent = findCombinations(votes, index + 1, currentSum + votes.get(index), requiredVotes, memo);
        int excludeCurrent = findCombinations(votes, index + 1, currentSum, requiredVotes, memo);

        // Store the result in memo table
        memo[index][currentSum] = includeCurrent + excludeCurrent;

        return memo[index][currentSum];
    }

    public static void main(String[] args) {
        // State votes
        Map<String, Integer> stateVotes = new HashMap<>();
        stateVotes.put("Alabama", 9);
        stateVotes.put("Alaska", 3);
        stateVotes.put("Arizona", 11);
        stateVotes.put("Arkansas", 6);
        stateVotes.put("Wyoming", 3);

        int totalVotes = stateVotes.values().stream().mapToInt(Integer::intValue).sum();
        int requiredVotes = (totalVotes / 2) + 1;

        // List of all state votes
        List<Integer> votes = new ArrayList<>(stateVotes.values());

        // Find all winning combinations
        int winningCombinations = countWinningCombinations(votes, requiredVotes);
        System.out.println("Number of ways Party 1 can win: " + winningCombinations);

        Map<String, Integer> stateVotes2 = new HashMap<>();
        stateVotes2.put("A", 3);
        stateVotes2.put("B", 4);
        stateVotes2.put("C", 8);

        int totalVotes2 = stateVotes2.values().stream().mapToInt(Integer::intValue).sum();
        int requiredVotes2 = (totalVotes2 / 2) + 1;

        // List of all state votes
        List<Integer> votes2 = new ArrayList<>(stateVotes2.values());

        // Find all winning combinations
        int winningCombinations2 = countWinningCombinations(votes2, requiredVotes2);
        System.out.println("Number of ways Party 1 can win: " + winningCombinations2);

    }
}
