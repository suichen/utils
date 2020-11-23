package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L078 {

    public List<List<Integer>> ans = new ArrayList<>();
    public List<Integer> temp = new ArrayList<>();

    public void dfs(int[] nums, int cur) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i = cur; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {

        if (nums == null) {
            return new ArrayList<>();
        }

        dfs(nums, 0);

        return ans;
    }
    public static void main(String[] args) {

        L078 l078 = new L078();

        int[] nums = new int[] {
                1,2,3
        };

        l078.subsets(nums);

        System.out.println();
    }
}
