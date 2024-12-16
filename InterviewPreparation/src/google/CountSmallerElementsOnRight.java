package google;

import java.util.*;

public class CountSmallerElementsOnRight {
    private int binarySearch(List<Integer> temp, int K) {

        // Lower and upper bounds
        int n = temp.size();
        int start = 0;
        int end = n - 1;

        // Traverse the search space
        while (start <= end) {
            int mid = (start + end) / 2;

            // If K is found
            if (temp.get(mid) == K)
                return mid;

            else if (temp.get(mid) < K)
                start = mid + 1;

            else
                end = mid - 1;
        }

        // Return insert position
        return end + 1;
    }

    int[] constructLowerArray(int[] arr) {
        // code here
        int n = arr.length;
        List<Integer> temp = new ArrayList<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int idx = binarySearch(temp, arr[i]);
            ans[i] = idx;
            temp.add(idx, arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 1, 5, 3, 4, 6};
        CountSmallerElementsOnRight cs = new CountSmallerElementsOnRight();
        int[] res = cs.constructLowerArray(arr);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
