package google.linesweep;


import java.util.*;

// https://leetcode.com/discuss/interview-question/4835565/Google-or-Phone-Interview-or-Banglore-oror-SDE-III-oror-Android-(732024)
// https://leetcode.com/discuss/interview-question/2006734/google-onsite-l4-rejected
/*
Invert rotation table which is similar but sufficiently different to keep as a separate question)
Given on-call rotation schedule for multiple people by: their unique name, start time (timestamp 0-max int64) and end time (timestamp 0-max int64) of the rotation:
Example input:
Name | Start | End
----- | ----- | ---
Abby | 10     | 100
Ben   | 50     | 70
Carla | 60    | 120
David | 150  | 300
Your goal is to return a rotation table without overlapping periods representing who is on call during that time.
Return "Start time", "End time" and list of on-call people: Do not include time periods that do not have on-call people.
Specifically in the given example, this means excluding the period between 120-150.
 Example output:
Start | End | Names
----- | --- | ----------------
10 	  | 50 | Abby
50	  | 60 | Abby, Ben
60	  | 70 | Abby, Ben, Carla
70 	  | 100 | Abby, Carla
100   | 120 | Carla
150   | 300 | David
Why this works well - No deep insight or a-ha moment needed. This is about analytical thinking and good coding. - This works for all levels as there are multiple solutions of varying complexities, from simple sorting, interval splitting to the use of min-heaps. - Gives chance to ask lots of clarifying questions:
Can a person have multiple start/end times?
Can those schedules intersect for the same person?
If the name matches, are they the same person?
Can a time period have 0 length?
 - Solution involves multiple data structures. - Have multiple solutions, some of them are really beautiful. - May include copying a data structure. - Lots of edge cases to test. I advise making sure that TC is aware of all assumptions before implementing their solution. - This question is deeper than it looks at first. Many candidates approach it with an abundance of confidence. It's excellent to measure the level of the candidate's thinking. - Solution to this problem cannot have complexity better than O(n^2), where n is number of people, because of the output nature.
 Imagine a case with 1000 people, where first starts at 1 and finishes at 1001, second starts at 2 and finishes 1001, etc. Program will print n^2/2 (500K - half of 1000x1000 table) names in the output. Analysis of the complexity can give additional signals about TC's comprehension of their solution complexity (many of them claims their complexity is O(nlogn)) and fix attempts may tell you more about TC's knowledge of working with data structures. Often TC either doesn't know how to easily copy the list and what is this operation complexity (it is O(length of the list)) or passes pointer to the list which will result in it being further modified and printing last interval's names for all intervals. - What hints do you give when the candidate is stuck? - Reduced problems: - Each person only has a single segment. - Only two or three people participate. - Segments are already sorted. - All start & end times are unique, aka every input number is different. - Totally forget about names and return just intervals. - Output suboptimal intervals, like 1-2-Abby, 2-3-Abby, 3-4-Abby instead of 1-4-Abby.
 */
public class OnCallRotationSchedule {
    static class Rotation {
        String name;
        int start;
        int end;

        public Rotation(String name, int start, int end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
    static class TableRow {
        int start;
        int end;
        List<String> names;

        public TableRow(int start, int end, List<String> names) {
            this.start = start;
            this.end = end;
            this.names = names;
        }

        @Override
        public String toString() {
            return start + " " + end + " " + String.join(", ", names);
        }
    }


    public static List<TableRow> createRotationTable(List<Rotation> rotations) {
        TreeMap<Integer, List<String>> timeMap = new TreeMap<>();

        // Add start and end events to the TreeMap
        for (Rotation rotation : rotations) {
            // Add start time
            timeMap.putIfAbsent(rotation.start, new ArrayList<>());
            timeMap.get(rotation.start).add(rotation.name);

            // Add end time with a marker (negative to remove later)
            timeMap.putIfAbsent(rotation.end, new ArrayList<>());
            timeMap.get(rotation.end).add("-" + rotation.name);
        }

        List<TableRow> result = new ArrayList<>();
        Set<String> onCall = new TreeSet<>(); // To keep names sorted
        Integer previousTime = null;

        // Iterate through the sorted timeMap
        for (Map.Entry<Integer, List<String>> entry : timeMap.entrySet()) {
            int currentTime = entry.getKey();
            List<String> events = entry.getValue();

            // If the time has changed and there's an active on-call list, create a new TableRow
            if (previousTime != null && !onCall.isEmpty()) {
                result.add(new TableRow(previousTime, currentTime, new ArrayList<>(onCall)));
            }

            // Process events at the current time
            for (String event : events) {
                if (event.startsWith("-")) {
                    // Remove a name (end time)
                    onCall.remove(event.substring(1));
                } else {
                    // Add a name (start time)
                    onCall.add(event);
                }
            }

            previousTime = currentTime; // Update the previous time
        }

        return result;
    }

    public static Set<String> currentlyOncall(List<Rotation> rotations, int time) {
        Set<String> onCall = new HashSet<>();
        for (Rotation rotation : rotations) {
            if (rotation.start <= time && time < rotation.end) {
                onCall.add(rotation.name);
            }
        }
        return onCall;
    }

    public static List<String> optimisedCurrentlyOncall(List<TableRow> rotationTable, int time) {
        if (rotationTable == null || rotationTable.isEmpty()) {
            return Collections.emptyList();
        }

        int left = 0;
        int right = rotationTable.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            TableRow row = rotationTable.get(mid);

            if (time >= row.start && time < row.end) {
                // Time falls within this interval
                return row.names;
            } else if (time < row.start) {
                // Search left half
                right = mid - 1;
            } else {
                // Search right half
                left = mid + 1;
            }
        }

        // Time does not fall within any interval
        return Collections.emptyList();
    }
    public static void main(String[] args) {
        // Input Rotations
        List<Rotation> rotations = Arrays.asList(
                new Rotation("Abby", 1, 10),
                new Rotation("Ben", 5, 7),
                new Rotation("Carla", 6, 12),
                new Rotation("David", 15, 17)
        );

        // Generate the rotation table
        List<TableRow> table = createRotationTable(rotations);

        // Print the results
        for (TableRow row : table) {
            System.out.println(row);
        }

        System.out.println("*************************************************************");
        // Input Rotations
        List<Rotation> rotations1 = Arrays.asList(
                new Rotation("Abby", 10, 100),
                new Rotation("Ben", 50, 70),
                new Rotation("Carla", 60, 120),
                new Rotation("David", 150, 300)
        );

        // Generate the rotation table
        List<TableRow> table1 = createRotationTable(rotations1);

        // Print the results
        for (TableRow row : table1) {
            System.out.println(row);
        }

        System.out.println("*************************************************************");
        // Input Rotations
        List<Rotation> rotations2 = Arrays.asList(
                new Rotation("Abby", 1, 10),
                new Rotation("Ben", 5, 7),
                new Rotation("Carla", 6, 12),
                new Rotation("David", 15, 17),
                new Rotation("Abby", 8, 13)
        );

        // Generate the rotation table
        List<TableRow> table2 = createRotationTable(rotations2);

        // Print the results
        for (TableRow row : table2) {
            System.out.println(row);
        }
        System.out.println();
        // Generate the rotation table
        System.out.println("Currently on-call: " + currentlyOncall(rotations2, 9));
        System.out.println("Currently on-call: " + optimisedCurrentlyOncall(table2, 9));
    }
}

/*

We are given on-call rotation schedule for multiple people by: their name, start time and end time of the rotation:
Abby 1 10
Ben 5 7
Carla 6 12
David 15 17
Abby 8 13

Question: Giving a T time, return all people names who are oncall during that time.
So for T = 9; Expected output: [Abby, Carla]

data class Rotation(name: String, start: Int, end: Int)

// O(n)
// T = 9
// filtered in: [Abby, Carla, Abby]
// filtered out: [Ben, David,
// Abby - 1 <= 9, 10 >= 9
// 7 >= 9
// 6 9
// 6 <= 9 T 12 >= 9 T
// 15 <= 9 F
// 8 <= 9 T 13 >= 9 T
fun currentlyOncall(rotations: List<Rotation>, time: Int): Set<String> {
  return rotations.filter { it.start <= time && it.end >= time }.map { it.name }.toSet()
}
 */

// for same person having overlapping ranges, if we use set it will remove the person when the first end time comes
/*
EXAMPLE
List<Rotation> rotations = Arrays.asList(
    new Rotation("Abby", 1, 10),
    new Rotation("Abby", 5, 15)
);

public static List<TableRow> createRotationTable(List<Rotation> rotations) {
    TreeMap<Integer, List<String>> timeMap = new TreeMap<>();

    // Add start and end events to the TreeMap
    for (Rotation rotation : rotations) {
        // Add start time
        timeMap.putIfAbsent(rotation.start, new ArrayList<>());
        timeMap.get(rotation.start).add(rotation.name);

        // Add end time with a marker (negative to remove later)
        timeMap.putIfAbsent(rotation.end, new ArrayList<>());
        timeMap.get(rotation.end).add("-" + rotation.name);
    }

    List<TableRow> result = new ArrayList<>();
    Map<String, Integer> onCallCounts = new TreeMap<>(); // To keep names sorted
    Integer previousTime = null;

    // Iterate through the sorted timeMap
    for (Map.Entry<Integer, List<String>> entry : timeMap.entrySet()) {
        int currentTime = entry.getKey();
        List<String> events = entry.getValue();

        // If the time has changed and there's an active on-call list, create a new TableRow
        if (previousTime != null && !onCallCounts.isEmpty()) {
            List<String> onCallNames = new ArrayList<>();
            for (Map.Entry<String, Integer> countEntry : onCallCounts.entrySet()) {
                if (countEntry.getValue() > 0) {
                    onCallNames.add(countEntry.getKey());
                }
            }
            result.add(new TableRow(previousTime, currentTime, onCallNames));
        }

        // Process events at the current time
        for (String event : events) {
            String name = event.startsWith("-") ? event.substring(1) : event;
            int count = onCallCounts.getOrDefault(name, 0);

            if (event.startsWith("-")) {
                // Decrease count (end time)
                if (count > 1) {
                    onCallCounts.put(name, count - 1);
                } else {
                    onCallCounts.remove(name);
                }
            } else {
                // Increase count (start time)
                onCallCounts.put(name, count + 1);
            }
        }

        previousTime = currentTime; // Update the previous time
    }

    return result;
}
 */
