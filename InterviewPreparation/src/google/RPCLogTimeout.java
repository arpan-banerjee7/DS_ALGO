package google;

import java.util.*;

/*
Find in log file if RPC Timed Out First ask if TC knows what an RPC server is. If they don't, explain to them in a few words e.g. as a web server serving page requests. Imagine you have an RPC server that produces log of entries and we're analyzing it offline. There are two entries for each call, one when the RPC starts and one when
the RPC finishes processing. We'd like to know as soon as possible if there's an RPC that took too much time / timed out.
 */
public class RPCLogTimeout {
    static class Node {
        int requestId;
        int startTime;

        Node(int requestId, int startTime) {
            this.requestId = requestId;
            this.startTime = startTime;
        }
    }

    private Map<Integer, Node> ongoingRequestMap; // HashMap for O(1) access
    private Deque<Node> logsQueue; // Deque to maintain order of requests by start time
    private int timeoutThreshold; // The timeout threshold

    public RPCLogTimeout(int timeoutThreshold) {
        this.timeoutThreshold = timeoutThreshold;
        this.ongoingRequestMap = new HashMap<>();
        this.logsQueue = new LinkedList<>();
    }

    // Method to process a single log entry
    public void processLogEntry(String logEntry) {
        // Parse the log entry
        String[] parts = logEntry.trim().split("\\s+");
        int requestId = Integer.parseInt(parts[0]);
        int currentTimeStamp = Integer.parseInt(parts[1]);
        String action = parts[2];

        // Before processing the new log entry, check for timed-out requests
        checkForTimeouts(currentTimeStamp);

        // Process the log entry
        if (action.equals("START")) {
            // Add new request
            Node node = new Node(requestId, currentTimeStamp);
            ongoingRequestMap.put(requestId, node);
            logsQueue.offerLast(node);
        } else if (action.equals("END")) {
            // Remove completed request
            Node node = ongoingRequestMap.get(requestId);
            if (node != null) {
                logsQueue.remove(node);
                ongoingRequestMap.remove(requestId);
            }
        }
    }

    // Method to check for timed-out requests
    private void checkForTimeouts(int currentTimeStamp) {
        while (!logsQueue.isEmpty()) {
            Node oldestRequest = logsQueue.peekFirst();
            int duration = currentTimeStamp - oldestRequest.startTime;
            if (duration > timeoutThreshold) {
                System.out.println("Request " + oldestRequest.requestId + " timed out and is discovered at " + currentTimeStamp);
                logsQueue.pollFirst(); // Remove from deque
                ongoingRequestMap.remove(oldestRequest.requestId); // Remove from map
            } else {
                break; // Since the deque is ordered by time, we can stop here
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        int timeoutThreshold = 5; // Given timeout threshold

        // Instantiate the LogProcessor with the timeout threshold
        RPCLogTimeout logProcessor = new RPCLogTimeout(timeoutThreshold);

        // Simulated log entries
        String[] logEntries = {
                "1 1 START",
                "2 2 START",
                "1 4 END",
                "3 8 START",
                "3 15 END"
        };

        // Process each log entry
        for (String logEntry : logEntries) {
            logProcessor.processLogEntry(logEntry);
        }

        // Simulated log entries
        String[] logEntries1 = {
                "0 0 START",
                "1 1 START",
                "0 2 END",
                "2 6 START",
                "1 7 END"
        };
        timeoutThreshold = 3; // Given timeout threshold

        // Instantiate the LogProcessor with the timeout threshold
        RPCLogTimeout logProcessor1 = new RPCLogTimeout(timeoutThreshold);

        // Process each log entry
        for (String logEntry : logEntries1) {
            logProcessor1.processLogEntry(logEntry);
        }

        // Simulated log entries
        String[] logEntries2 = {
                "0 0 START",
                "1 1 START",
                "0 5 END",
                "2 6 START"
        };
        timeoutThreshold = 3; // Given timeout threshold

        // Instantiate the LogProcessor with the timeout threshold
        RPCLogTimeout logProcessor2 = new RPCLogTimeout(timeoutThreshold);

        // Process each log entry
        for (String logEntry : logEntries2) {
            logProcessor2.processLogEntry(logEntry);
        }

    }
}
/*
Hi,
I have question from Google interview, that I still don't know how to resolve :)


We have a big log file - several gigabytes. Each line contains request/response log - with columns like REQUEST_ID, TIMESTAMP, START/END FLAG. We need to parse file, and print requests ids that exceeded given time threshold. Some of requests contains start log, but has never completed and do not have log with end time.


i.e.
1 1 START
2 2 START
1 4 END
3 8 START
3 15 END
And given timeout threshold as 5.


Request 1 started at 1
Request 2 started at 2
Request 1 ended at 4 ->4-1 = 3 < 5 - under threshold - it's ok - do nothing.
Request 3 started at 8 -> In this place we should already know that request 2 started at 2, and 8-2 = 6 what is > 5, that means we should print here that request 2 is timed out.
Request 3 ended at 15 - >15-8 =7 > 5 -> we shoud print that request 3 timed out.


Does anyone have idea how to solve this efficiently ?
 */


