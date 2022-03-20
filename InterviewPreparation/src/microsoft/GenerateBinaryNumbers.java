package microsoft;
// Generate Binary Numbers 

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// https://practice.geeksforgeeks.org/problems/generate-binary-numbers-1587115620/1/ gfg 
// similar- 1680. Concatenation of Consecutive Binary Numbers

public class GenerateBinaryNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Function to generate binary numbers from 1 to N using a queue.
	static ArrayList<String> generate(int N) {
		ArrayList<String> res = new ArrayList<String>();
		Queue<String> q = new LinkedList<>();
		q.add("1");
		for (int i = 1; i <= N; i++) {
			String s = q.poll();
			res.add(s);

			q.add(s + "0");
			q.add(s + "1");
		}

		return res;
	}
	
/* 1680. Concatenation of Consecutive Binary Numbers
	 // https://www.youtube.com/watch?v=c1T8VKsSuT4 Algorithm Made Easy
	// TC: O(n) SC: O(1)
	class Solution {
	    public int concatenatedBinary(int n) {
	       long res = 0;
	       int mod = 1_000_000_007;
	        
	       for(int i=1; i<=n; i++) {
	           String binaryString = Integer.toBinaryString(i);
	           res = (res << binaryString.length())%mod;
	           res = res + i;
	       }
	       return (int)res;
	    }
	}
	/* TC: O(n) SC: O(1)
	class Solution {
	    public int concatenatedBinary(int n) {
	       long res = 0;
	       int mod = 1_000_000_007;
	       int size = 0; 
	        
	       for(int i=1; i<=n; i++) {
	           if((i&(i-1)) == 0) size++;
	           res = (res << size | i)%mod;
	       }
	       return (int)res;
	    }
	}
*/
}