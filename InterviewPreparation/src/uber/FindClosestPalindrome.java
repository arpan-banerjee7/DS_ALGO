package uber;

import java.util.*;

public class FindClosestPalindrome {
    // Helper function to create a palindrome by mirroring the first half
    private long halfToPalindrome(long left, boolean even) {
        long resultNum = left;
        if (!even) {
            left = left / 10;
        }

        // Mirroring the first half to form a palindrome
        while (left > 0) {
            int digit = (int) (left % 10);
            resultNum = (resultNum * 10) + digit;
            left /= 10;
        }
        return resultNum;
    }

    public String nearestPalindromic(String n) {
        int len = n.length();
        int mid = len / 2;
        long firstHalf = Long.parseLong(n.substring(0, len % 2 == 0 ? mid : mid + 1));

        /*
            Generate possible palindromic candidates:
            1. Palindrome by mirroring the first half.
            2. Palindrome by mirroring the first half + 1.
            3. Palindrome by mirroring the first half - 1.
            4. Handle edge cases by considering palindromes of the form 999...
               and 100...001

            I was not able to catch the 3rd and 4th edge case :-( . But it's ok , I got to learn something.
        */
        // Generate possible palindromic candidates
        List<Long> possibleResults = new ArrayList<>();
        possibleResults.add(halfToPalindrome(firstHalf, len % 2 == 0));
        possibleResults.add(halfToPalindrome(firstHalf + 1, len % 2 == 0));
        possibleResults.add(halfToPalindrome(firstHalf - 1, len % 2 == 0));
        possibleResults.add((long) Math.pow(10, len - 1) - 1);  // Edge case like 100...001|| 1001- ans 999
        possibleResults.add((long) Math.pow(10, len) + 1);      // Edge case like 999...|| 99 ans 101

        long diff = Long.MAX_VALUE;
        long result = 0;
        long originalNum = Long.parseLong(n);

        // Find the closest palindrome
        for (long num : possibleResults) {
            if (num == originalNum) continue;
            if (Math.abs(num - originalNum) < diff) {
                diff = Math.abs(num - originalNum);
                result = num;
            } else if (Math.abs(num - originalNum) == diff) {
                result = Math.min(result, num);
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String n = "12345";
        FindClosestPalindrome fcp = new FindClosestPalindrome();
        System.out.println(fcp.nearestPalindromic(n));
    }
}
