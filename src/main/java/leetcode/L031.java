package leetcode;

public class L031 {

    private void swapNums(int[] nums, int begin, int end) {
        for (; begin < end; begin++, end--) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
        }
    }
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int indexDown = -1;

        //step1. 找出降序的第一个数字的位置 indexDown
        //step2. 从[indexDown+1, length-1]找出比nums[indexDown]大的数字位置indexTarget
        //step3. 交换nums[indexDown]与nums[indexTarget]
        //step4. [indexTarget, length-1]进行倒序排列

        for (indexDown = length-2; indexDown >= 0; indexDown--) {
            if (nums[indexDown] < nums[indexDown+1]) {
                break;
            }
        }

        if (indexDown < 0) {
            swapNums(nums, 0, length-1);
            return;
        }

        int indexTarget = -1;
        for (indexTarget = length-1; indexTarget > indexDown; indexTarget--) {
            if (nums[indexTarget] > nums[indexDown]) {
                break;
            }
        }

        int temp = nums[indexDown];
        nums[indexDown] = nums[indexTarget];
        nums[indexTarget] = temp;

        swapNums(nums, indexDown+1, length-1);
    }

    public static void main(String[] args) {
        L031 l031 = new L031();
        int[] nums = new int[] {
                1,3,2
        };
        l031.nextPermutation(nums);
        System.out.println();
    }
}
