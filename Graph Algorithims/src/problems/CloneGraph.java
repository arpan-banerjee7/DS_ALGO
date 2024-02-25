package problems;

// Clone Graph
// https://leetcode.com/problems/clone-graph/description/
// https://www.youtube.com/watch?v=mQeF6bN8hMk -neetcode
// Graph dfs amazon

import java.util.ArrayList;
import java.util.List;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {
// Definition for a Node.


    public void dfs(Node node, Node copy, Node[] visited) {
        visited[copy.val] = copy;

        for (Node n : node.neighbors) {
            if (visited[n.val] == null) {
                Node newNode = new Node(n.val);
                copy.neighbors.add(newNode);
                dfs(n, newNode, visited);
            } else {
                // in normal DFS we would have skipped it, thus not entering a the already visited neigbor, but here we
                // need to insert, and we have to insert the copy node which he had already created earlier
                copy.neighbors.add(visited[n.val]);
            }
        }

    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node copy = new Node(node.val);

        // in this question we will create an array of Node(not boolean) why ? ,
        // because i have to add all the adjacent nodes of particular vertex, whether it's visited or not,
        // so in the Node[] initially null is stored,
        // if that node is visited, we will store the respective node at the index, and can retrieve that easily.
        Node[] visited = new Node[101];
        dfs(node, copy, visited); // make a dfs call for traversing all the vertices of the root node
        return copy; // in the end return the copy node
    }
}

