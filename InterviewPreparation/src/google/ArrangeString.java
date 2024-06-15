package google;

import java.util.PriorityQueue;

// https://leetcode.com/discuss/interview-question/2122202/Google-or-Screening
public class ArrangeString {

    // TC - O(nlogn)
    private static String arrange(String input) {
        int n = input.length();
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> lowerCaseSorted = new PriorityQueue();
        for (char ch : input.toCharArray()) {
            if (Character.isLowerCase(ch))
                lowerCaseSorted.add(ch);
        }
        for (char ch : input.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                sb.append(lowerCaseSorted.poll());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // TC - O(n)
    private static String arrange2(String input) {
        int n = input.length();
        StringBuilder sb = new StringBuilder();

        int[] freq = new int[26];
        for (char ch : input.toCharArray()) {
            if (Character.isLowerCase(ch))
                freq[ch - 'a']++;
        }
        int currIdx = 0;
        for (char ch : input.toCharArray()) {
            // be ready with the lowest small case character
            while (freq[currIdx] == 0) {
                currIdx++;
            }
            if (Character.isLowerCase(ch)) {
                sb.append((char) (currIdx + 'a'));
                freq[currIdx]--; // update the freq array
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "Test@123 Google"; // "Teeg@123 Gloost"
        System.out.println(arrange2(input));
    }
}

