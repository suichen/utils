package leetcode;


import java.util.*;

public class L015 {

    public List<List<Integer>> threeSum(int[] sums) {
        List<List<Integer>> ret = new ArrayList<>();

        if (sums.length < 3) {
            return ret;
        }

        Arrays.sort(sums);
        int length = sums.length, i = 0, j = 0, n = length;

        for (i = 0; i < length; i++) {
            if ((i - 1 >= 0) && (sums[i - 1] == sums[i])) {
                continue;
            }
            for (j = i + 1, n = length - 1; (j < length) && (j < n); j++, n--) {
                int target = sums[i] + sums[j] + sums[n];

                if (target > 0) {
                    j -= 1;
                } else if (target == 0) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(sums[i]);
                    ans.add(sums[j]);
                    ans.add(sums[n]);
                    ret.add(ans);
                    while ((j < n) && (sums[j + 1] == sums[j])) {
                        j++;
                    }
                    while ((j < n) && (sums[n - 1] == sums[n])) {
                        n--;
                    }

                } else {
                    n += 1;
                }
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        //[1,2,-2,-1]
        int[] nums = new int[4];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = -2;
        nums[3] = -1;

        L015 l015 = new L015();

        List<List<Integer>> res = l015.threeSum(nums);
        System.out.println();
    }
}
