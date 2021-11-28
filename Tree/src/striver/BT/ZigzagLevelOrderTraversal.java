package striver.BT;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 103. Binary Tree Zigzag Level Order Traversal
// Ref: https://www.youtube.com/watch?v=3OXWEdlIGl4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=20 Take u Forward

public class ZigzagLevelOrderTraversal {
	// same as level order traversal
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root != null) {
			queue.add(root);
		}

		boolean flag = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			LinkedList<Integer> tempList = new LinkedList<Integer>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (flag == true) {
					tempList.addLast(node.val);
				} else {
					// reverse order
					tempList.addFirst(node.val);
				}

				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			flag = !flag;
			res.add(tempList);
		}
		return res;
	}
	
	 /*
	List<List<Integer>> result = new ArrayList();
    
    if(root==null){
        return result;
    }
    
    Queue<TreeNode> queue=new LinkedList<>();
    
    queue.add(root);
    int level=0;
    
    while(!queue.isEmpty()){
        int size=queue.size();
        List<Integer> list=new ArrayList<>();
        
        for(int i=0;i<size;i++){
            TreeNode node=queue.poll();
            int x=node.val;
            
            if(level%2==0){
                list.add(x);
            }
            else{
                list.add(0,x);
            }
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
        }
        level++;
        result.add(list);
    }
    return result;
    */

}
