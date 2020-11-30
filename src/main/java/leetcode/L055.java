package leetcode;

public class L055 {
    public boolean canJump(int[] nums) {
        if (nums == null) {
            return true;
        }

        int maxDistance = -1;

        for (int i = 0; i < nums.length; i++) {
            if (maxDistance == -1) {
                maxDistance = nums[i];
            } else {
                if (maxDistance >= i) {
                    maxDistance = Math.max(nums[i]+i, maxDistance);
                }
            }

            if (maxDistance >= nums.length-1) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) {

    }
}
