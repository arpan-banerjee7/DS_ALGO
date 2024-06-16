package graph.dsu;

// https://leetcode.com/problems/number-of-provinces/?envType=problem-list-v2&envId=p4wxv0w3

public class NumberOfProvinces {
    // find
    private int find(int u, int[] parent) {
        if (u == parent[u]) {
            return u;
        }
        return parent[u] = find(parent[u], parent);
    }

    //union
    private void union(int u, int v, int[] parent, int[] rank) {
        if (rank[u] < rank[v]) {
            parent[u] = v;
        } else if (rank[v] < rank[u]) {
            parent[v] = u;
        } else {
            parent[v] = u;
            rank[u]++;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int count = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int pU = find(i, parent);
                    int pV = find(j, parent);
                    if (pU != pV) {
                        count--;
                        union(pU, pV, parent, rank);
                    }
                }
            }
        }
        // Set<Integer> seen=new HashSet<>();
        // for(int i:parent){
        //     seen.add(i);
        // }
        // return seen.size()-1;
        return count;
    }
    /*
    Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
    Output: 2
     */
}
