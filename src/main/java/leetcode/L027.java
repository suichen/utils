package leetcode;

public class L027 {
    public int removeElement(int[] nums, int val) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }
        }

        return count;
    }
    public static void main(String[] args) {
        L027 l027 = new L027();
        int[] nums = new int[] {
                0,1,2,2,3,0,4,2
        };
        int val = 2;
        int res = l027.removeElement(nums, val);
        System.out.println(res);
    }
}
