package stackqueue.problems;

import java.util.Arrays;

// https://leetcode.com/problems/car-fleet/
// https://www.youtube.com/watch?v=-KsJrT77MJk
// https://leetcode.com/problems/car-fleet/discuss/180287/Java-Priority-Queue-Explained

class Car {
	int position;
	double timeToTarget;

	Car(int position, double timeToTarget) {
		this.position = position;
		this.timeToTarget = timeToTarget;
	}
}

public class CarFleetI {

	public static int carFleet(int target, int[] position, int[] speed) {
		int n = speed.length;
		int count = 0;

		Car[] cars = new Car[n];
		for (int i = 0; i < n; i++) {

			cars[i] = new Car(position[i], ((target - position[i]) * 1.0) / speed[i]);
		}
		Arrays.sort(cars, (a, b) -> a.position - b.position);
		for (int i = n - 1; i > 0; i--) {
			// forms fleet
			if (cars[i].timeToTarget >= cars[i - 1].timeToTarget) {
				cars[i - 1] = cars[i];
			} else {
				count++;
			}
		}
		return count + 1;
	}

	public static void main(String[] args) {
		int[] position = { 10, 8, 0, 5, 3 };
		int[] speed = { 2, 4, 1, 1, 3 };
		int target = 12;
		int count = carFleet(target, position, speed);
		System.out.println("No of car fleets = " + count);
	}
}
