package google.linesweep;

import java.util.*;
// Difference array + prefix sum

// TC- O(k+n) k number of queries, n is the total range

/*
// https://codeforces.com/blog/entry/78762
One solution is to loop over each element for each range but this takes 𝑂(𝑁𝑄)
 time. We can do better.

A difference array can be used to perform multiple range update where we need to find the answer only after performing all the queries. We can do this in 𝑂(𝑁)
 time and space. We can update an arbitrary range in 𝑂(1)
. It is only when we need to print our final answer that we perform an 𝑂(𝑁)
 computation.
 */

public class ParkingGarageOccupancy {
    public static int[] computeOccupancy(int N, List<int[]> tickets) {
        int[] counts = new int[N + 2]; // Extra space to handle exit + 1
        int[] result = new int[N];

        // Process each ticket
        for (int[] ticket : tickets) {
            int entry = ticket[0];
            int exit = ticket[1];

            // Increment at entry time
            if (entry < N) {
                counts[entry] += 1;
            }

            // Decrement after exit time // for train remove the +1
            if (exit + 1 < counts.length) {
                counts[exit + 1] -= 1;
            }
        }

        // Compute prefix sums to get the result
        int currentSum = 0;
        for (int i = 0; i < N; i++) {
            currentSum += counts[i];
            result[i] = currentSum;
        }

        return result;
    }

    public static void main(String[] args) {
        int N = 5;
        List<int[]> tickets = new ArrayList<>();
        tickets.add(new int[]{1, 3});
        tickets.add(new int[]{2, 4});

        int[] result = computeOccupancy(N, tickets);

        System.out.println(Arrays.toString(result)); // Output: [0, 1, 2, 2, 1]
    }
}
/*
14)Construct a histogram of a parking garage's occupancy.
 Imagine we own a parking garage. All day, cars enter and exit. When a car enters, it takes a ticket from the gate machine. When a car exits, it returns the ticket to the gate machine. The machine prints the entry and exit time on each ticket. Now, it's the end of the day and we've got a big pile of tickets, and we want to figure out how many cars were in the garage at each time of the day.
Write a function that takes as input an integer N and a list tickets of size L, and returns a list result such that len(result) == N and result[i] == the number of elements of tickets such that entry <= i <= exit.
Example: N = 5
tickets = [(1, 3), (2, 4)]
result = [0, 1, 2, 2, 1]
Alternative flavour text: we manage a one-way passenger train route that has N segments and N+1 stops. Passengers buy tickets between two stops on this route. Each ticket has the index of the departure stop and the index of the arrival stop. We want to compute the train's occupancy on each segment of the route, where the segment i means the one immediately following the stop i. N = 5 segment index 0 1 2 3 4 route x-----x-----x-----x-----x-----x stop index 0 1 2 3 4 5 This option makes it easier to explain what N means. In this variant, tickets should use exclusive ends, because passengers get off at their final stop and are no longer on the train on the following segment of the route.
Example: N = 5tickets = [(1, 4), (2, 5)]result = [0, 1, 2, 2, 1]
 */