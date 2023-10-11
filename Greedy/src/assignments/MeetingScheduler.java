package assignments;

import java.util.*;

//Intuit Interview Questions
class Meeting {
	int start;
	int end;

	public Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class MeetingScheduler {
	public static boolean isAvailable(List<Meeting> meetings, int requestedStart, int requestedEnd) {
		// Sort meetings by their start times
		Collections.sort(meetings, Comparator.comparingInt(a -> a.start));

		// Linear scan to check for conflicts
		for (Meeting meeting : meetings) {
			if (requestedEnd <= meeting.start) {
				// No conflict, the requested slot ends before this meeting starts
				return true;
			} else if (requestedStart >= meeting.end) {
				// No conflict, the requested slot starts after this meeting ends
				continue;
			} else {
				// Conflict found
				return false;
			}
		}

		// No conflicts found with any meeting
		return true;
	}

	public static void main(String[] args) {
		List<Meeting> meetingHours = List.of(new Meeting(1000, 1100), new Meeting(900, 930), new Meeting(1330, 1430));

		System.out.println(isAvailable(meetingHours, 800, 830)); // Should return true
		System.out.println(isAvailable(meetingHours, 845, 915)); // Should return false
		System.out.println(isAvailable(meetingHours, 830, 930)); // Should return false
	}
}
