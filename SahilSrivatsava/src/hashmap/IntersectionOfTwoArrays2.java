package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 350. Intersection of Two Arrays II
// https://leetcode.com/problems/intersection-of-two-arrays-ii/

public class IntersectionOfTwoArrays2 {
	public int[] intersect(int[] nums1, int[] nums2) {
	       List<Integer> res=new ArrayList<>();
	        Map<Integer,Integer> map=new HashMap<>();
	        for(int i:nums1){
	            map.put(i,map.getOrDefault(i,0)+1);
	        }
	        for(int j:nums2){
	            if(map.containsKey(j) && map.get(j)>0){
	                res.add(j);
	                map.put(j,map.get(j)-1);
	            }
	                
	        }
	        return res.stream().mapToInt(i -> i).toArray();
	    }
	public static void main(String[] args) {
		/*
		 * Input: nums1 = [1,2,2,1], nums2 = [2,2] Output: [2,2]
		 */

	}

}
