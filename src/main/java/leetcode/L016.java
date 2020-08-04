package leetcode;

import java.util.Arrays;

public class L016 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0, maxGap = Integer.MAX_VALUE, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1, k = nums.length-1; j < k;) {
                sum = nums[i]+nums[j]+nums[k];
                if (sum == target) {
                    return target;
                }

                if (sum < target) {
                    j += 1;
                } else {
                    k -= 1;
                }

                int gap = Math.abs(sum-target);
                if (gap < maxGap) {
                    maxGap = gap;
                    ans = sum;
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        L016 l016 = new L016();
        int[] nums = new int[] {
                -5,-2,-1,10,20,100
        };
        int target = 4;
        int res = l016.threeSumClosest(nums, target);
        System.out.println(res);
    }
}
