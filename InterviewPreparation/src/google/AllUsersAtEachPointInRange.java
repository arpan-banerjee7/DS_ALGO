package google;
import java.util.*;

public class AllUsersAtEachPointInRange {
    public static void countUser(int[][] session, int n) {
        int[] count = new int[n];
        Arrays.fill(count, 0);
        for (int[] se : session) {
            int s = se[0];
            int e = se[1];
            count[s] += 1;
            if (e + 1 < n) {
                count[e + 1] -= 1;
            }
        }
        for (int i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " -> " + count[i]);
        }
    }

    public static void main(String[] args) {
        int[][] session = {{0, 3}, {1, 4}};
        countUser(session, 7);
    }
}


