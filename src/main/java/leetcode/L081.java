package leetcode;

public class L081 {
    public boolean search(int[] nums, int target) {
        if (nums == null) {
            return false;
        }

        int left = 0, right = nums.length-1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }

            //前半部分有序
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = left + 1;
                }
            } else {
                if (target <= nums[right] && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
    public static void main(String[] args) {
        L081 l081 = new L081();
        int[] nums = new int[] {
                3,1
        };

        boolean ans = l081.search(nums, 1);
        System.out.println(ans);
    }
}
