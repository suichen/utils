package leetcode;

public class L026 {
    public int removeDuplicates(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i-1>=0 && nums[i] == nums[i-1]) {
                continue;
            }
            nums[count++] = nums[i];
        }

        return count;
    }
    public static void main(String[] args) {
        L026 l026 = new L026();
        int[] nums = new int[] {
                0,0,1,1,1,2,2,3,3,4
        };
        int res = l026.removeDuplicates(nums);
        System.out.println(res);
    }
}
