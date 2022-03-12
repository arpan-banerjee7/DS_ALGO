package graph.algorithims;

import java.util.LinkedList;
import java.util.Stack;

/*
 * #######Tarjans strongly connected component TC- o(v+e)
 * algo-https://www.youtube.com/watch?v=ZeDNSeilf-Y&list=
 * PLEJXowNB4kPzByLnnFYNSCoqtFz0VKLk5&index=28
 * 
 * Steps- take discovery time array, lowest DNA array, a stack to enable us to
 * print the scc's, and a boolean presentInStack array- to identify cross edge
 * and back edges.
 * 
 * We won t process cross edge, we will process only back edges and tree edges
 * 1)Start DFS call, before iterating through the adjacent nodes of the given
 * node, fill the required values in the above mentioned data strutures. for(int
 * v: adj[u]) 2) Recursion 1--if not visited make a new dfs call(Recursion) for
 * the ajdacent nodes. 3) Backtracking 1 -- while returning back from the
 * recursion, update the low value of the parent node as
 * min(low[child],low[parent]) 2) Recursion 1 -else --or of 2nd step- if
 * visited, then it can be either cross edge or backedge. We will ignore cross
 * edges.Identification of backedge- if visited true-(dicovery array is filled
 * for this node) and presentInStack value is true for it 3) dn t make any
 * recursive call for this child, just update the low value as low[u] =
 * min(low[u],disc[v]); v=child 4) backtracking 2 -- when there are no recursion
 * calls to bemade for u, i.e when all the sub graphs are visited for parent u,
 * then check if(low[u]==disc[u]) //If u is head node of SCC. Pop from the
 * stack, mark presentInStack as false till u reach the current node. 5) repeat
 * step 4.
 */

public class FIndStronglyConnectedComponentsTarjansAlgo {
	static int time = 0;

	private static void DFS(LinkedList<Integer>[] adj, int u, int[] disc, int[] low, Stack<Integer> mystack,
			boolean[] presentInStack) {

		disc[u] = low[u] = time;
		time += 1;
		mystack.push(u);
		presentInStack[u] = true;

		for (int v : adj[u]) {
			if (disc[v] == -1) // If v is not visited
			{
				DFS(adj, v, disc, low, mystack, presentInStack);
				low[u] = Math.min(low[u], low[v]);
			}
			// Differentiate back-edge and cross-edge
			else if (presentInStack[v]) // Back-edge case
				low[u] = Math.min(low[u], disc[v]);

			// ignore cross edge
		}

		if (low[u] == disc[u]) // If u is head node of SCC
		{
			// pop from stack till head is encountered
			System.out.println("SCC is: ");
			while (mystack.peek() != u) {
				System.out.print(mystack.peek() + " ");
				presentInStack[mystack.peek()] = false;
				mystack.pop();
			}
			System.out.println(mystack.peek() + " ");
			presentInStack[mystack.peek()] = false;
			mystack.pop();
		}
	}

	private static void printSCCTarjan(LinkedList<Integer>[] adj, int noOfVertices) {
		int[] disc = new int[noOfVertices]; // will never change
		int[] low = new int[noOfVertices]; // updated while backtracking

		for (int i = 0; i < noOfVertices; i++) {
			disc[i] = -1;
			low[i] = -1;
		}
		boolean[] presentInStack = new boolean[noOfVertices]; // Avoids cross-edge
		Stack<Integer> mystack = new Stack<>(); // to print SCC

		for (int i = 0; i < noOfVertices; ++i)
			if (disc[i] == -1)
				DFS(adj, i, disc, low, mystack, presentInStack);
	}

	public static void main(String[] args) {
		// building the adjacency list harcoded
		int noOfVertices = 7;
		LinkedList<Integer>[] adj = new LinkedList[noOfVertices];
		for (int i = 0; i < noOfVertices; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		adj[0].add(1);
		adj[1].add(2);
		adj[1].add(3);
		adj[3].add(4);
		adj[4].add(0);
		adj[4].add(5);
		adj[4].add(6);
		adj[5].add(6);
		adj[6].add(5);

		printSCCTarjan(adj, noOfVertices);

	}

}
