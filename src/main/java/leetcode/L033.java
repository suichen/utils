package leetcode;

public class L033 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        int mid = 0;
        while (left <= right) {
            mid = (left+right)/2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            } else {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        L033 l033 = new L033();
        int[] nums = new int[] {
                5,1,3
        };
        int target = 2;
        System.out.println(l033.search(nums, target));
    }
}
