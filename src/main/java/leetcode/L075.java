package leetcode;

public class L075 {
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }

        int ptr0 = 0, ptr2 = nums.length-1;

        for (int i = 0; i <= ptr2; i++) {
            if (nums[i] == 0) {
                swap(nums, ptr0, i);
                ptr0++;
            }

            if (nums[i] == 2) {
                while (i <= ptr2 && nums[i] == 2) {
                    swap(nums, ptr2, i);
                    ptr2--;
                }
                i -= 1;
            }


        }
    }

    public static void main(String[] args) {
        L075 l075 = new L075();

        int[] nums = new int[] {
                1,2,2,2,2,0,0,0,1,1
        };

        l075.sortColors(nums);
        System.out.println();
    }
}
