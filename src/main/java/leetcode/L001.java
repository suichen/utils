package leetcode;

import java.util.HashMap;
import java.util.Map;

public class L001 {
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        int ans[] = new int[2];

        for (int i = 0; i < nums.length; i++) {
            numToIndex.putIfAbsent(nums[i], i);
        }

        for (Map.Entry<Integer, Integer> entry:numToIndex.entrySet()) {
            int value = entry.getKey();
            int otherValue = target - value;
            if (numToIndex.containsKey(otherValue)) {
                nums[0] = entry.getValue();
                nums[1] = numToIndex.get(otherValue);
                return nums;
            }
        }

        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndexMap = new HashMap<>();

        int[] ans = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];

            if (numToIndexMap.containsKey(value)) {
                ans[0] = i;
                ans[1] = numToIndexMap.get(value);
            }
            numToIndexMap.putIfAbsent(nums[i], i);
        }
        return ans;
    }
}
