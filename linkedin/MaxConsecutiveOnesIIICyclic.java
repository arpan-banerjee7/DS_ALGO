package linkedin;

public class MaxConsecutiveOnesIIICyclic {

    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int start = 0, max = 0;
        int k = 1; // at most one zero can be flipped

        for (int end = 0; end < 2 * n; end++) {
            if (nums[end % n] == 0) {
                k--;
            }

            // If we have used more than one flip or window size exceeds n
            while (k < 0 ) {
                if (nums[start % n] == 0) {
                    k++;
                }
                start++;
            }

            max = Math.max(max, end - start + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] input = {1, 0, 0, 1, 1, 0, 1};
        MaxConsecutiveOnesIIICyclic maxOnes = new MaxConsecutiveOnesIIICyclic();
        System.out.println(maxOnes.findMaxConsecutiveOnes(input));
    }
}


