package leetcode;

public class L136 {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int single = 0;

        for (int i = 0; i < nums.length; i++) {
            single ^= nums[i];
        }

        return single;
    }
    public static void main(String[] args) {
        L136 l136 = new L136();
        int nums[] = new int[] {
                4,1,2,1,2
        };

        int ans = l136.singleNumber(nums);
        System.out.println(ans);
    }
}
