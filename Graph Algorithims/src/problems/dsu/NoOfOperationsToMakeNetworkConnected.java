package graph.dsu;

// https://leetcode.com/problems/number-of-operations-to-make-network-connected/?envType=problem-list-v2&envId=p4wxv0w3
public class NoOfOperationsToMakeNetworkConnected {
    static class DisjointSet{
        int[] parent;
        int[] rank;
        int n;

        public int[] getParent(){
            return this.parent;
        }
        public DisjointSet(int n){
            this.parent = new int[n];
            this.rank = new int[n];
            this.n=n;
            makeSet(n);
        }

        private void makeSet(int n){
            for(int i=0;i<n;i++){
                parent[i]=i;
            }
        }

        public int find(int x){
            if(x==parent[x]){
                return x;
            }
            return parent[x]=find(parent[x]);
        }

        public void union(int u, int v){
            if(u==v) return;
            if(rank[u]<rank[v]){
                parent[u]=v;
            }
            else if(rank[v]<rank[u]){
                parent[v]=u;
            }else{
                parent[u]=v;
                rank[v]++;
            }
        }

    }
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1; // Not enough cables to connect all computers
        }

        DisjointSet ds = new DisjointSet(n);
        int extraEdges = 0;

        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            int parentU = ds.find(u);
            int parentV = ds.find(v);

            if (parentU != parentV) {
                ds.union(parentU, parentV);
            } else {
                extraEdges++;
            }
        }

        int components = 0;
        for (int i = 0; i < n; i++) {
            if (ds.find(i) == i) { // very imp here, cannot look into just the parent array.
                components++;
            }
        }

        if (extraEdges >= components - 1) {
            return components - 1;
        }
        return -1;
    }
    /*
    Input: n = 4, connections = [[0,1],[0,2],[1,2]]
    Output: 1
    Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
     */
}
