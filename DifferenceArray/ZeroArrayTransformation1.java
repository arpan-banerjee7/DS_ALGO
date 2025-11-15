/* Difference Array Technique
Step 1: Given an original array A of length n, define a new array D (also length n) so that
D[0] = A[0]
for i > 0, D[i] = A[i] − A[i−1]

If the initial array contains all elements as 0, then no need to perform step 1.

Step 2: Apply range updates
range[1,2,5]
nums[1]+=5
nums[2+1]-=5

Step 3: Find cumulative sum
sum=0;
sum+=diffArray[i]
diffArray[i]=sum;

The resutant array would be the same, if you apply the range updates separately.
EG- nums=[0,0,0,0,0]
updates- [1,3,2] , [2,4,3], [0,2-2]

(1,3,2) -> {0,2,0,0,-2}
(2,4,3) -> {0,2,3,0,2}
(0,2,-2)->{-2,2,3,2,-2}

Cumulative sum-
{-2,0,3,5,3}
 */
public class ZeroArrayTransformation1 {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        // Step 1: Make difference array using queries
        int[] diff = new int[n];
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            diff[start] += 1;
            if (end + 1 < n) {
                diff[end + 1] -= 1;
            }
        }

        // Step 2: Compute cumulative effect on each index
        int cumSum = 0;
        for (int i = 0; i < n; i++) {
            cumSum += diff[i];
            diff[i] = cumSum;
        }

        // Step 3: Check if each value can reach 0
        for (int i = 0; i < n; i++) {
            if (diff[i] < nums[i]) {
                return false; // nums[i] won't become 0
            }
        }

        return true;
    }

    // Example runner
    public static void main(String[] args) {
        ZeroArrayTransformation1 solver = new ZeroArrayTransformation1();
        int[] nums = { 4, 3, 2, 1 };
        int[][] queries = { { 1, 3 }, { 0, 2 } };

        boolean result = solver.isZeroArray(nums, queries);
        System.out.println(result);
        // Expected output: false
    }
}
