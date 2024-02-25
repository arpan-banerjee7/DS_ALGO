package problems;

// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/description/
/*
    there are 2 possible ways at each index(p), take one digit, or take 2 digits
    (since digits range is from 1 to 26) Taking one digit is simple, just keep incrementing
    by one index, while taking two digits we need to make sure that it lies bw
    1 to 26, so if char at p is 1, p+1 can be anything, but if char at p is 2 then p+1 can be upto 7,
    for char >2 it is not possible.
 */
public class DecodeWays {
    Integer[] dp;

    private int decodeWays(String s, int p) {
        if (p == s.length()) {
            return 1;
        }
        if (s.charAt(p) == '0') {
            return 0;
        }
        if (dp[p] != null) {
            return dp[p];
        }
        int res = decodeWays(s, p + 1);

        if (p < s.length() - 1 && (s.charAt(p) == '1' || (s.charAt(p) == '2' && s.charAt(p + 1) < '7'))) {
            res += decodeWays(s, p + 2);
        }
        return dp[p] = res;
    }

    public int numDecodings(String s) {

        int n = s.length();
        dp = new Integer[n];
        return decodeWays(s, 0);
    }

    public static void main(String[] args) {
        /*
        Input: s = "226"
        Output: 3
        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
         */
    }
}
