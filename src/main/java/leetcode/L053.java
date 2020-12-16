package leetcode;

public class L053 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = nums[0], dp = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp  = dp + nums[i] > nums[i] ? dp + nums[i] : nums[i];
            if (dp > ans) {
                ans = dp;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        L053 l053 = new L053();
        int[] nums = new int[] {
                -2,1,-3,4,-1,2,1,-5,4
        };

        int ans = l053.maxSubArray(nums);
        System.out.println(ans);
    }
}
