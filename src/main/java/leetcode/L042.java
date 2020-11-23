package leetcode;

public class L042 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int ans = 0;

        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];

        left_max[0] = height[0];
        right_max[height.length-1] = height[height.length-1];

        for (int i = 1; i < height.length; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i]);
        }

        for (int i = height.length-2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i+1]);
        }

        for (int i = 1; i < height.length; i++) {
            ans = ans + Math.min(left_max[i], right_max[i])- height[i];
        }

        return ans;
    }
    public static void main(String[] args) {
        L042 l042 = new L042();
        int[] nums = new int[] {
                0,1,0,2,1,0,1,3,2,1,2,1
        };
        int ans = l042.trap(nums);
        System.out.println(ans);
    }
}
