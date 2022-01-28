package problems;

import java.util.ArrayList;

// https://www.youtube.com/watch?v=hMJAlbJIS7E

public class GetStairsPath {

	public static ArrayList<String> getStairPaths(int n, int m) {
		if (n == 1) {
			ArrayList<String> path = new ArrayList<>();
			path.add("1");
			return path;
		}
		if (n == 0) {
			ArrayList<String> path = new ArrayList<>();
			path.add("");
			return path;
		}
		// return an empty list, so that later its path is not considered
		if (n < 0) {
			ArrayList<String> path = new ArrayList<>();
			return path;
		}
		ArrayList<ArrayList<String>> pathsTillNow = new ArrayList<>();
		for (int i = 1; i <= m; i++) {
			ArrayList<String> pathTillNow = getStairPaths(n - i, m);
			pathsTillNow.add(pathTillNow);
		}
		ArrayList<String> paths = new ArrayList<>();
		for (int i = 1; i <= pathsTillNow.size(); i++) {
			for (String s : pathsTillNow.get(i - 1)) {
				paths.add(i + s);
			}
		}

		return paths;

	}

	// upto steps 1 , 2,3
	public static ArrayList<String> getStairPaths(int n) {
		if (n == 1) {
			ArrayList<String> path = new ArrayList<>();
			path.add("1");
			return path;
		}
		if (n == 0) {
			ArrayList<String> path = new ArrayList<>();
			path.add("");
			return path;
		}
		// return an empty list, so that later its path is not considered
		if (n < 0) {
			ArrayList<String> path = new ArrayList<>();
			return path;
		}

		ArrayList<String> paths1 = getStairPaths(n - 1);
		ArrayList<String> paths2 = getStairPaths(n - 2);
		ArrayList<String> paths3 = getStairPaths(n - 3);

		ArrayList<String> paths = new ArrayList<>();

		for (String s : paths1) {
			paths.add(1 + s);
		}

		for (String s : paths2) {
			paths.add(2 + s);
		}

		for (String s : paths3) {
			paths.add(3 + s);
		}
		return paths;

	}

	public static void main(String[] args) {
		int n = 5;
		int m = 5;// upto n
		System.out.println(getStairPaths(n, m));
		System.out.println(getStairPaths(n));
	}

}
