package striver.BT;

import java.util.LinkedList;
import java.util.Queue;

// Striver: https://youtu.be/Et9OCDNvJ78---- Cut down TC from o(nlogn) to O(n)
// https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
// https://www.hackerrank.com/challenges/tree-top-view/problem?isFullScreen=false

public class TopViewOfBT {
	static Node buildTree(String str) {

		if (str.length() == 0 || str.charAt(0) == 'N') {
			return null;
		}

		String ip[] = str.split(" ");
		// Create the root of the tree
		Node root = new Node(Integer.parseInt(ip[0]));
		// Push the root to the queue

		Queue<Node> queue = new LinkedList<>();

		queue.add(root);
		// Starting from the second element

		int i = 1;
		while (queue.size() > 0 && i < ip.length) {

			// Get and remove the front of the queue
			Node currNode = queue.peek();
			queue.remove();

			// Get the current node's value from the string
			String currVal = ip[i];

			// If the left child is not null
			if (!currVal.equals("N")) {

				// Create the left child for the current node
				currNode.left = new Node(Integer.parseInt(currVal));
				// Push it to the queue
				queue.add(currNode.left);
			}

			// For the right child
			i++;
			if (i >= ip.length)
				break;

			currVal = ip[i];

			// If the right child is not null
			if (!currVal.equals("N")) {

				// Create the right child for the current node
				currNode.right = new Node(Integer.parseInt(currVal));

				// Push it to the queue
				queue.add(currNode.right);
			}
			i++;
		}

		return root;
	}
}

/*
 
Sparsh Sharma
2 months ago (edited)
I literally saw 3-4 videos on this question but still was not able to get AC on GFG practice, in frustration, I left this question but now after watching your video, this question has become one of my favorite questions on trees. Thank you bhaiya!!
EDIT: I cut down the time complexity of inserting elements in treemap(O(logN)) to O(1) by using a Hash Map. I just stored the min value of the line in the tree and then kept on incrementing from that and kept on storing the elements.

JAVA Code: 

class Pair{
    int state;
    Node root;
    
    Pair(int state, Node root){
        this.state = state;
        this.root = root;
    }
}



class Solution
{
    //Function to return a list of nodes visible from the top view 
    //from left to right in Binary Tree.
    static int traverseLevelOrder(Node root, Map<Integer, Integer> map){
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(0,root));
        
        int min = Integer.MAX_VALUE;
        
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            Node currNode = pair.root;
            int state = pair.state;
            min = Math.min(min, state);
            
            if(!map.containsKey(state))
                map.put(state, currNode.data);
            if(currNode.left != null)
                queue.offer(new Pair(state-1, currNode.left));
            if(currNode.right != null)
                queue.offer(new Pair(state+1, currNode.right));
        }
        return min;
    }
    
    static ArrayList<Integer> topView(Node root)
    {
        
        //This question can't be done using normal dfs beacuse there is a concept
        //of levels being used in this question
        Map<Integer, Integer> map = new HashMap<>();
        int min = traverseLevelOrder(root, map);
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = min; map.containsKey(i); i++)
            ans.add(map.get(i));
        return ans;
    }
}*/
