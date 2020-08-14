package leetcode;

public class L035 {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }
    public static void main(String[] args) {
        L035 l035 = new L035();
        int[] nums = new int[] {
            1,3,5,6
        };
        int target = 0;
        int ans = l035.searchInsert(nums, target);
        System.out.println(ans);
    }
}
