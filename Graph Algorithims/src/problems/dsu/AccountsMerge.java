package graph.dsu;

import java.util.*;

// https://leetcode.com/problems/accounts-merge/description/?envType=problem-list-v2&envId=p4wxv0w3
public class AccountsMerge {
    static class DisjointSet {
        List<Integer> parent;
        List<Integer> rank;
        int n;

        public DisjointSet(int n) {
            this.parent = new ArrayList<>(n);
            this.rank = new ArrayList<>(n);
            this.n = n;
            makeSet(n);
        }

        private void makeSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                rank.add(0);
            }
        }

        public int find(int x) {
            if (x != parent.get(x)) {
                parent.set(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int childU, int childV) {
            int u = find(childU);
            int v = find(childV);
            if (u != v) {
                if (rank.get(u) < rank.get(v)) {
                    parent.set(u, v);
                } else if (rank.get(v) < rank.get(u)) {
                    parent.set(v, u);
                } else {
                    parent.set(u, v);
                    rank.set(v, rank.get(v) + 1);
                }
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mapMailNode = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (mapMailNode.containsKey(mail) == false) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.union(i, mapMailNode.get(mail));
                }
            }
        }

        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) mergedMail[i] = new ArrayList<String>();
        for (Map.Entry<String, Integer> it : mapMailNode.entrySet()) {
            String mail = it.getKey();
            int node = ds.find(it.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) continue;
            Collections.sort(mergedMail[i]);
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));
            for (String it : mergedMail[i]) {
                temp.add(it);
            }
            ans.add(temp);
        }
        return ans;
    }
    /*
    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    Explanation:
    The first and second John's are the same person as they have the common email "johnsmith@mail.com".
    The third John and Mary are different people as none of their email addresses are used by other accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
     */
}
