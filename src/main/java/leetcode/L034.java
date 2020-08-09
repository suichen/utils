package leetcode;

public class L034 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{
                    -1,-1
            };
        }
        int left = 0, right = nums.length - 1, mid;
        int[] ans = new int[2];

        while (left <= right) {
            mid = (left+right)/2;
            if (target == nums[mid]) {
                int beg = mid, end = mid;
                while (beg >= 0 && nums[beg] == target) {
                    beg -= 1;
                }
                beg += 1;
                while (end < nums.length && nums[end] == target) {
                    end += 1;
                }
                end -= 1;

                ans[0] = beg;
                ans[1] = end;

                return ans;
            }

            if (target > nums[mid]) {
                left = mid + 1;
                while (left < nums.length && nums[left] == nums[left-1]) {
                    left += 1;
                }
            } else {
                right = mid - 1;
                while (right >= 0 && nums[right+1] == nums[right]) {
                    right -= 1;
                }
            }
        }

        return new int[] {
                -1,-1
        };
    }

    private int searchIndex(int[] nums, int target, boolean left) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (low+high)/2;
            if ((target == nums[mid] && left) || nums[mid] > target) {
                high = mid;
            } else {
                low = mid+1;
            }
        }

        return low;
    }
    public static void main(String[] args) {
        L034 l034 = new L034();
        int[] nums = new int[] {
                10,10
        };
        int target = 10;

        //int[] ans = l034.searchRange(nums, target);

        int index = l034.searchIndex(nums, 10, false);
        System.out.println(index);
    }
}
