package hashmap;

import java.util.HashMap;
import java.util.Map;

// 781. Rabbits in Forest
// https://leetcode.com/problems/rabbits-in-forest/

public class RabbitsInForest {

	/*
	 * for each ans we are creating a group of ans- (ans) in the hashmap which tells
	 * us that there are ans rabbits of a color. Actually there are ans+1 rabbits,
	 * since we are considering a place to be already occupied so, now there are
	 * only ans rabbits.
	 * 
	 * for example-[1,2,4,2,1,3,1] 1- [*,_]-- there are two rabbits of C1 color, out
	 * of which one is the current one, so there is a place left for only one
	 * rabbit.
	 * 
	 * 2- [*,_,_]-- exclding me, there are 2 other rabbits of C2 color. So 3 spots
	 * out of which 1 is occupied by me.
	 * 
	 * so while creating these groups we will add ans+1 to our results which
	 * indicates total number of rabbit so far.
	 * 
	 * now suppose at index 4 we encounter another 1 so it will be 1- [*,*] both the
	 * places are occupied, here we wn t update the res variable. now at last index
	 * there is one more 1. We do this by substracting the frequency and removing
	 * the element from the hashmap.
	 * 
	 * so here we wll create a new group and add 2 to the res 1- [*,_] or else it
	 * wll violate the first 1's condition, that there is only 1 rabbit part from me
	 * with the same color.
	 * 
	 * And we need to handle the 0 cases are well, for that we wn t put in in map,
	 * we will smply add 1 to the res each time we encounter 0, since it means that
	 * there are no other rabbits with the same color as itself, so its only 1
	 * rabbit each time we encounter a 0.
	 */

	public int numRabbits(int[] answers) {
		int res = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : answers) {
			if (i == 0) { // [1,0,1,0,0] ans-5
				res += 1;
			} else if (!map.containsKey(i)) {
				map.put(i, i);
				res += (i + 1);
			} else {
				map.put(i, map.get(i) - 1);
				if (map.get(i) == 0) {
					map.remove(i);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		/*
		 * Input: answers = [1,1,2] Output: 5 Explanation: The two rabbits that answered
		 * "1" could both be the same color, say red. The rabbit that answered "2" can't
		 * be red or the answers would be inconsistent. Say the rabbit that answered "2"
		 * was blue. Then there should be 2 other blue rabbits in the forest that didn't
		 * answer into the array. The smallest possible number of rabbits in the forest
		 * is therefore 5: 3 that answered plus 2 that didn't.
		 */}
	
}
