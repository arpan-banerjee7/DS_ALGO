package google;

import java.util.*;

public class CanFormASquareOptimal {
    private final Set<String> pointsSet = new HashSet<>();
    private final Map<Integer, Set<Integer>> xMap = new HashMap<>();

    public boolean add(int x, int y) {
        String newPoint = x + "," + y;

        if (pointsSet.contains(newPoint)) {
            return false; // Avoid duplicates
        }

        // Check if the new point forms a square with any existing points
        if (xMap.containsKey(x)) {
            for (int y1 : xMap.get(x)) {
                int side = Math.abs(y1 - y);
                if (side == 0) continue;

                // Check points on the same diagonal
                if ((pointsSet.contains((x - side) + "," + y) && pointsSet.contains((x - side) + "," + y1)) ||
                        (pointsSet.contains((x + side) + "," + y) && pointsSet.contains((x + side) + "," + y1))) {
                    return true;
                }
            }
        }



        // Update data structures
        pointsSet.add(newPoint);
        xMap.computeIfAbsent(x, k -> new HashSet<>()).add(y);

        return false;
    }

    public static void main(String[] args) {
        CanFormASquareOptimal detector = new CanFormASquareOptimal();

//        System.out.println(detector.add(1, 2)); // false
//        System.out.println(detector.add(3, 5)); // false
//        System.out.println(detector.add(1, 5)); // false
//        System.out.println(detector.add(4, 2)); // false
//        System.out.println(detector.add(4, 5)); // true

        System.out.println(detector.add(2, 3)); // false
        System.out.println(detector.add(4, 3)); // false
        System.out.println(detector.add(2, 5)); // false
        System.out.println(detector.add(4, 5)); // true
    }
}

/*
import java.util.*;

public class ValidSquareFinder {

    // This map will store the x-coordinates and the set of y-coordinates that align with each x
    private final Map<Integer, Set<Integer>> xMap;

    public ValidSquareFinder() {
        this.xMap = new HashMap<>();
    }

    public boolean add(int x, int y) {
        // Add the y coordinate to the set of y-coordinates that share the x coordinate.
        xMap.computeIfAbsent(x, k -> new HashSet<>()).add(y);

        // Check if adding (x, y) forms a square with previously seen points.
        Set<Integer> ySet = xMap.get(x);

        // Iterate over all y-coordinates that share the same x
        for (int y2 : ySet) {
            if (y2 == y) continue;  // Skip the point itself

            // The potential height difference
            int sideLength = Math.abs(y - y2);

            // The other x-coordinates that must be checked to form a square
            int x1 = x - sideLength;
            int x2 = x + sideLength;

            // Check if the other two points required to form a square are present
            if (xMap.containsKey(x1) && xMap.get(x1).contains(y) && xMap.get(x1).contains(y2)) {
                return true;
            }
            if (xMap.containsKey(x2) && xMap.get(x2).contains(y) && xMap.get(x2).contains(y2)) {
                return true;
            }
        }

        // No square found
        return false;
    }

    public static void main(String[] args) {
        ValidSquareFinder finder = new ValidSquareFinder();

        // Insert points and print whether a square is formed after adding each point
        System.out.println(finder.add(1, 2)); // Expected: false
        System.out.println(finder.add(3, 5)); // Expected: false
        System.out.println(finder.add(1, 5)); // Expected: false
        System.out.println(finder.add(4, 2)); // Expected: false
        System.out.println(finder.add(4, 5)); // Expected: true, as (1, 2), (1, 5), (4, 5), (4, 2) form a square
    }
}

 */
