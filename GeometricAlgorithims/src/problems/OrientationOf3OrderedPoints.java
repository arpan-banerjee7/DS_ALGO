package problems;
// https://www.geeksforgeeks.org/orientation-3-ordered-points/

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class OrientationOf3OrderedPoints {
	// ordered points

	// To find orientation of ordered triplet
	// (p1, p2, p3). The function returns
	// following values
	// 0 --> p, q and r are collinear
	// 1 --> Clockwise
	// 2 --> Counterclockwise
	public static int orientation(Point p1, Point p2, Point p3) {
		// See 10th slides from following link
		// for derivation of the formula
		int val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);

		if (val == 0)
			return 0; // collinear

		// clock or counter-clock wise
		return (val > 0) ? 1 : 2;
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(4, 4);
		Point p3 = new Point(1, 2);

		int o = orientation(p1, p2, p3);

		if (o == 0)
			System.out.print("Linear");
		else if (o == 1)
			System.out.print("Clockwise");
		else
			System.out.print("CounterClockwise");

	}
}
