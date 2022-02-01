package striver.BT;

// https://www.youtube.com/watch?v=ArNyupe-XH0 Aditya Verma
// https://www.youtube.com/watch?v=FxgpgxH2k8o - Partially Correct Solution
// https://practice.geeksforgeeks.org/problems/maximum-path-sum/1

// To DO- fully correct sol not found, few edge cases failing]

public class MaximumPathSumFromLeafToleaf {
	int maxDiameter=Integer.MIN_VALUE;
    private int findHeight(Node root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return root.data;
        }
        int left=findHeight(root.left);
        int right=findHeight(root.right);
        
        //do this only when both left and right are not null
        /*          2
                4       1
            7       10
        n       -3
        
        */
        
        // this also solves this problem, max sum already has a
        // bigger value for non leaf nodes summation
        
        /*      -10
            -1          0
        3
        */
        int temp=0;
        if(root.left!=null && root.right!=null){
            temp=Math.max(left,right)+root.data;
             maxDiameter=Math.max(maxDiameter,left+right+root.data);
        }else{
            temp=root.left!=null? left+root.data : right+root.data;
        }
        
        return temp; // explore other nodes to find max if present;
    }
    int maxPathSum(Node root)
    { 
        // code here
        findHeight(root);
        return maxDiameter;
        
    } 
}
