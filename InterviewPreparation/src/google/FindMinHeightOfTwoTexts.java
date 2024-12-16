package google;

/*
// https://leetcode.com/discuss/interview-question/1423422/google-phone-screen
// https://leetcode.com/discuss/interview-experience/1706779/google-phone-swe-india
// https://leetcode.com/problems/text-justification/
We have given two texts as text1 and text2 and we try to put it in 1 x 2 table, so we need to find the minium height
where we can put the divider.
where we should put this divider in table to get minimum height, we are given width of the table
Approach which I was able to get: good case is when the length of both text is equal so we start with putting the divider in middle and get height h, then we check if len of the text in 1st cell is less then I can move my divider in left else if my len of text in 2nd cell is less then I can move my divider to right side, this movement I can do with bineary search will get a value where my height started increasing again
though could not get time to code it up properly, took time to think through this approcah only
 */
public class FindMinHeightOfTwoTexts {
    public static int findMinimumHeight(String text1, String text2, int totalWidth) {
        int left = getMaxWordLength(text1); // Minimum possible width for column 1
        int right = totalWidth - getMaxWordLength(text2); // Maximum possible width for column 1
        int minHeight = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int width1 = mid;
            int width2 = totalWidth - mid;

            int linesText1 = countLines(text1, width1);
            int linesText2 = countLines(text2, width2);

            int currentHeight = Math.max(linesText1, linesText2);
            minHeight = Math.min(minHeight, currentHeight);

            // Decide which way to adjust the divider
            if (linesText1 > linesText2) {
                // Need more width for text1
                left = mid + 1;
            } else {
                // Can try reducing width1 to see if height remains minimum
                right = mid - 1;
            }
        }

        return minHeight;
    }

    private static int getMaxWordLength(String text) {
        String[] words = text.split("\\s+");
        int maxLength = 0;
        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }

    private static int countLines(String text, int maxWidth) {
        String[] words = text.split("\\s+");
        int lines = 0;
        int currLength = 0;

        for (String word : words) {
            if (word.length() > maxWidth) {
                // Word itself is longer than maxWidth, cannot fit
                return Integer.MAX_VALUE;
            }
            if (currLength + word.length() > maxWidth) {
                // Start a new line
                lines++;
                currLength = 0;
            }
            currLength += word.length() + 1; // +1 for the space
        }

        // Add the last line if there's remaining text
        if (currLength > 0) {
            lines++;
        }

        return lines;
    }

    public static void main(String[] args) {
        String text1 = "This is a sample text for column one.";
        String text2 = "Another sample text for column two, which may be longer or shorter.";
        int totalWidth = 50; // Total width of the table

        int minHeight = findMinimumHeight(text1, text2, totalWidth);
        System.out.println("Minimum Height: " + minHeight);
    }

}
