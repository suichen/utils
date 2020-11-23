package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L040 {
    List<List<Integer>> ans = new ArrayList<>();

    public void calSum(int[] candidates, int target, int index, List<Integer> list) {
        if (target == 0) {
            List<Integer> integers = new ArrayList<>();
            for (Integer integer:list) {
                integers.add(integer);
            }
            ans.add(integers);
        }
        if (target < 0) {
            return;
        }

        for (; index < candidates.length; index++) {
            list.add(candidates[index]);
            calSum(candidates, target-candidates[index], index+1, list);
            list.remove(list.size()-1);
            while (index+1 < candidates.length && candidates[index+1] == candidates[index]) {
                index += 1;
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        calSum(candidates, target, 0, list);
        return ans;
    }
    public static void main(String[] args) {
       L040 l040 = new L040();
       int[] nums = new int[] {
               2,5,2,1,2
       };
       int target = 5;

       List<List<Integer>> res = l040.combinationSum2(nums, target);

        System.out.println();
    }
}
