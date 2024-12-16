package google.tree;

import java.util.*;

public class RemoveDuplicateMessagesStream {

    private static final int TIME_WINDOW = 10;
    private Queue<Message> messageQueue;
    private Map<String, Integer> lastSeen;

    public RemoveDuplicateMessagesStream() {
        this.messageQueue = new LinkedList<>();
        this.lastSeen = new HashMap<>();
    }

    public void receiveMessage(Message message) {
        // Remove messages from the queue that are outside the 10-second window
        while (!messageQueue.isEmpty() && message.timestamp - messageQueue.peek().timestamp > TIME_WINDOW) {
            Message oldMessage = messageQueue.poll();
            if (lastSeen.get(oldMessage.content) == oldMessage.timestamp) {
                lastSeen.remove(oldMessage.content);
            }
        }

        // Check if this message is a duplicate within the 10-second window
        if (lastSeen.containsKey(message.content)) {
            // Remove the previous occurrence from the queue if it exists
            messageQueue.removeIf(m -> m.content.equals(message.content) && m.timestamp == lastSeen.get(message.content));
            // Do not process the current message
            lastSeen.remove(message.content);
        } else {
            // Display the message
            System.out.println(message.timestamp + " " + message.content);
            // Add to the queue and update lastSeen map
            messageQueue.add(message);
            lastSeen.put(message.content, message.timestamp);
        }
    }
    public static void main(String[] args) {
        RemoveDuplicateMessagesStream streamProcessor = new RemoveDuplicateMessagesStream();

        // Example stream of messages
        streamProcessor.receiveMessage(new Message(10, "solar panel activated"));
        streamProcessor.receiveMessage(new Message(11, "low battery warning"));
        streamProcessor.receiveMessage(new Message(12, "tire one: low air pressure"));
        streamProcessor.receiveMessage(new Message(13, "solar panel activated"));
        streamProcessor.receiveMessage(new Message(14, "low battery warning"));
        streamProcessor.receiveMessage(new Message(21, "solar panel activated"));
        streamProcessor.receiveMessage(new Message(35, "solar panel activated"));
    }

    static class Message {
        int timestamp;
        String content;

        Message(int timestamp, String content) {
            this.timestamp = timestamp;
            this.content = content;
        }
    }
}
