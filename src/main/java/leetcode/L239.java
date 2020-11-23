package leetcode;


import java.util.LinkedList;

public class L239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k == 1) {
            return nums;
        }

        int n = nums.length;

        int[] res = new int[n-k+1];

        LinkedList<Integer> dequeue = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            while (!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i]) {
                dequeue.pollLast();
            }

            dequeue.addLast(i);

            while (dequeue.peekFirst() < (i-k+1)) {
                dequeue.pollFirst();
            }

            if (i+1 >= k) {
                res[i-k+1] = nums[dequeue.peekFirst()];
            }
        }

        return res;
    }
    public static void main(String[] args) {
        L239 l239 = new L239();
        int[] nums = new int[]{
                1,3,-1,-3,5,3,6,7
        };

        int[] res = l239.maxSlidingWindow(nums, 3);

        System.out.println();
    }
}
