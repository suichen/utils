package leetcode;

public class L080 {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int len = 0, cnt = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                cnt += 1;
                continue;
            }

            if (nums[i] != nums[i-1]) {
                len++;
                cnt = 1;
                nums[len] = nums[i];
            } else {
                cnt += 1;
                if (cnt <= 2) {
                    len++;
                    nums[len] = nums[i];
                }
            }
        }

        return len+1;
    }
    public static void main(String[] args) {

        L080 l080 = new L080();
        int[] nums = new int[] {
                1,1,1
        };
        int len = l080.removeDuplicates(nums);
        System.out.println(len);
    }
}
