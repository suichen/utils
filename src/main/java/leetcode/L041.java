package leetcode;

public class L041 {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1]!=nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i+1)) {
                return i+1;
            }
        }
        return nums.length+1;
    }
    public static void main(String[] args) {
        L041 l041 = new L041();

        int[] nums = new int[] {
                1,2,3,4,5
        };
        int ans = l041.firstMissingPositive(nums);
        System.out.println(ans);
    }
}
