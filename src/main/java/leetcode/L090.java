package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L090 {
    List<List<Integer>> ans = new ArrayList<>();

    private void findSubSets(int[] nums, int start, List<Integer> list) {

        ans.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            findSubSets(nums, i+1, list);
            list.remove(list.size()-1);

            while ((i+1 < nums.length) && (nums[i+1] == nums[i])) {
                i++;
            }
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        findSubSets(nums, 0, new ArrayList<>());

        return ans;
    }
    public static void main(String[] args) {
        L090 l090 = new L090();
        int[] nums = new int[] {
                1,1,2,2
        };

        List<List<Integer>> ans = l090.subsetsWithDup(nums);

        System.out.println();
    }
}
