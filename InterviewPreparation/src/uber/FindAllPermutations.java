package uber;

import java.util.*;

public class FindAllPermutations {
    private void generate(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] vis) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!vis[i]) {
                vis[i] = true;
                temp.add(nums[i]);
                generate(nums, res, temp, vis);
                vis[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] vis = new boolean[nums.length];
        generate(nums, res, new ArrayList<>(), vis);
        return res;
    }

    public static void main(String[] args) {
        FindAllPermutations fp = new FindAllPermutations();
        int[] nums = {1, 2, 3};
        System.out.println(fp.permute(nums));
    }
}
