package intuit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class EvenlyDistributeArray {
    // Utility method to reverse an array in-place
    private static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    public static int[] evenlyDistribute(int[] arr, int k) {
        int n = arr.length;

        // Step 1: Sort the array from biggest to smallest
        Arrays.sort(arr);
        reverse(arr);

        // Step 2: Create a max heap (PriorityQueue) to hold the k biggest numbers
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 0; i < k; i++) {
            maxHeap.offer(arr[i]);
        }

        // Step 3: Add the rest of the numbers to the smallest slot in the priority queue
        for (int i = k; i < n; i++) {
            int smallest = maxHeap.poll();
            smallest += arr[i];
            maxHeap.offer(smallest);
        }

        // Step 4: Extract the results from the max heap
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }

        return result;
    }


    public static void main(String[] args) {
        int[] arr1 = {5, 5, 5, 5};
//        int k1 = 2;
//        int[] result1 = evenlyDistribute(arr1, k1);
//        System.out.println(Arrays.toString(result1)); // Output: [10, 10]

        int[] arr2 = {20, 15, 10, 50, 22};
        int k2 = 3;
        int[] result2 = evenlyDistribute(arr2, k2);
        System.out.println(Arrays.toString(result2)); // Output: [32, 35, 50]
    }
}


