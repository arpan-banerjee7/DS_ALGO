package google;

import java.util.HashSet;
import java.util.Set;

public class CanFormASquare {
    private final Set<String> pointsSet = new HashSet<>();

    public boolean add(int x, int y) {
        // Create a unique representation for the point
        String newPoint = x + "," + y;

        // Check if the point already exists
        if (pointsSet.contains(newPoint)) {
            return false; // Avoid duplicate points
        }

        // Check for squares
        for (String point : pointsSet) {
            // Parse the existing point
            String[] split = point.split(",");
            int x1 = Integer.parseInt(split[0]);
            int y1 = Integer.parseInt(split[1]);

            // If the points are aligned diagonally to form a square
            if (Math.abs(x - x1) == Math.abs(y - y1) && x != x1 && y != y1) {
                // Check the other two required points
                String point1 = x + "," + y1;
                String point2 = x1 + "," + y;

                if (pointsSet.contains(point1) && pointsSet.contains(point2)) {
                    return true; // Square found
                }
            }
        }

        // Add the new point to the set
        pointsSet.add(newPoint);
        return false; // No square formed
    }

    public static void main(String[] args) {
        CanFormASquare detector = new CanFormASquare();

        System.out.println(detector.add(1, 2)); // false
        System.out.println(detector.add(3, 5)); // false
        System.out.println(detector.add(1, 5)); // false
        System.out.println(detector.add(4, 2)); // false
        System.out.println(detector.add(4, 5)); // true (forms a square with (1,2), (1,5), (4,2))
    }
}


