package graph.dsu;
import java.util.*;

// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/?envType=problem-list-v2&envId=p4wxv0w3
public class MostStonesRemovedSameRowColumn {
    static class DisjointSet {
        int[] rank;
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                rank[i] = 0;
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int findUpar(int node) {
            if (node == parent[node])
                return node;
            parent[node] = findUpar(parent[node]);
            return parent[node];
        }

        public void unionByRank(int u, int v) {
            int ulp_u = findUpar(u);
            int ulp_v = findUpar(v);
            if (ulp_u == ulp_v)
                return;
            if (rank[ulp_u] < rank[ulp_v])
                parent[ulp_u] = ulp_v;
            else if (rank[ulp_u] > rank[ulp_v])
                parent[ulp_v] = ulp_u;
            else {
                parent[ulp_u] = ulp_v;
                rank[ulp_v]++;
            }
        }

        public void unionBySize(int u, int v) {
            int ulp_u = findUpar(u);
            int ulp_v = findUpar(v);
            if (ulp_u == ulp_v)
                return;
            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
    public int removeStones(int[][] stones) {
        int maxr = 0;
        int maxc = 0;
        for(int i=0;i<stones.length;i++){
            maxr = Math.max(maxr,stones[i][0]);
            maxc = Math.max(maxc,stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxr+maxc+1);
        // this is to find how many true components are there, not a single component on its own where there are no stones at all.
        Map<Integer,Integer> stonesnode = new HashMap<>();
        for(int i=0;i<stones.length;i++){
            int noder = stones[i][0];
            int nodec = stones[i][1]+maxr+1;
            ds.unionByRank(noder,nodec);
            stonesnode.put(noder,1);
            stonesnode.put(nodec,1);
        }
        int count = 0;
        for(Map.Entry<Integer,Integer> it: stonesnode.entrySet()){
            if(ds.findUpar(it.getKey())== it.getKey())
                count++;
        }
        return stones.length-count;
    }
    /*
    Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    Output: 5
    Explanation: One way to remove 5 stones is as follows:
    1. Remove stone [2,2] because it shares the same row as [2,1].
    2. Remove stone [2,1] because it shares the same column as [0,1].
    3. Remove stone [1,2] because it shares the same row as [1,0].
    4. Remove stone [1,0] because it shares the same column as [0,0].
    5. Remove stone [0,1] because it shares the same row as [0,0].
    Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
     */
}
