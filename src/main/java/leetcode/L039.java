package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L039 {
    List<List<Integer>> ans = new ArrayList<>();

    private void calSum(int[] candidates, int target, List<Integer> list, int index) {
        if (target == 0) {
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                arr.add(list.get(i));
            }
            ans.add(arr);
            return;
        }

        if (target < 0) {
            return;
        }

        for (; index < candidates.length; index++) {
            list.add(candidates[index]);
            target -= candidates[index];
            calSum(candidates, target, list, index);
            list.remove(list.size()-1);
            target += candidates[index];
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        calSum(candidates, target, list, 0);

        return ans;
    }
    public static void main(String[] args) {
        L039 l039 = new L039();
        int[] nums = new int[] {
                2,3,6,7
        };
        int target = 8;
        List<List<Integer>> ans = l039.combinationSum(nums, target);
        System.out.println();
    }
}
