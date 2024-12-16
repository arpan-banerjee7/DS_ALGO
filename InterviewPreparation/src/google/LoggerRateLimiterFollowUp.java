package google;

import java.util.*;

public class LoggerRateLimiterFollowUp {

    /**
     * Processes messages and their corresponding timestamps to determine which messages
     * should be printed based on the 10-second window rule.
     *
     * @param messages An array of messages.
     * @param times    An array of corresponding timestamps.
     * @return A list of messages that should be printed.
     */
    public List<String> solve(String[] messages, int[] times) {
        final int WINDOW = 10;
        int n = times.length;
        Map<String, Integer> messageMap = new HashMap<>();
        List<String> result = new ArrayList<>();
        int left = 0;

        for (int i = 0; i < n; i++) {
            // Clean up messages that are outside the 10-second window
            while (left < i && times[i] - times[left] >= WINDOW) {
                String leftMessage = messages[left];
                if (messageMap.get(leftMessage) != null && messageMap.get(leftMessage) >= 0) {
                    result.add(leftMessage);
                }

                // If the current mapping is the same as 'left' or its negation, remove it
                if (messageMap.get(leftMessage) != null &&
                        (messageMap.get(leftMessage) == left || messageMap.get(leftMessage) == -left)) {
                    messageMap.remove(leftMessage);
                }
                left++;
            }

            String currentMessage = messages[i];
            if (messageMap.containsKey(currentMessage)) {
                // If the message already exists, mark it as having duplicates by negating the index
                messageMap.put(currentMessage, -i);
            } else {
                // If it's a new message, add it with its index
                messageMap.put(currentMessage, i);
            }
        }

        // After processing all messages, check the remaining messages in the window
        while (left < n) {
            String leftMessage = messages[left];
            if (messageMap.get(leftMessage) != null && messageMap.get(leftMessage) >= 0) {
                result.add(leftMessage);
            }
            left++;
        }

        return result;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        LoggerRateLimiterFollowUp logger = new LoggerRateLimiterFollowUp();

        // Test Case 1
        String[] messages1 = {"Hello", "Hello", "Hey", "Hello"};
        int[] times1 = {1, 2, 8, 12};
        List<String> output1 = logger.solve(messages1, times1);
        System.out.println(output1); // Expected Output: [Hey, Hello]

        // Test Case 2
        String[] messages2 = {"Hello", "Hello", "Hey", "Hello", "Hey", "Hello"};
        int[] times2 = {1, 1, 8, 10, 18, 20};
        List<String> output2 = logger.solve(messages2, times2);
        System.out.println(output2); // Expected Output: [Hey, Hey, Hello]
    }
}
