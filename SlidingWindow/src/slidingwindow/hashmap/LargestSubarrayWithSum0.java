package slidingwindow.hashmap;
import java.util.*;
// https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
public class LargestSubarrayWithSum0 {
    int maxLen(int arr[], int n)
    {
        // Your code here
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);

        int sum=0;
        int maxLen=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(map.containsKey(sum)){
                maxLen=Math.max(maxLen,i-map.get(sum));
            }else{
                map.put(sum,i);
            }

        }
        return maxLen;
    }
}
/*
Input: arr[] = [15, -2, 2, -8, 1, 7, 10, 23]
Output: 5
Explanation: The largest subarray with a sum of 0 is [-2, 2, -8, 1, 7].
 */