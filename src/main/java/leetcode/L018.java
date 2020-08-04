package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> resLists = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i-1>=0 && nums[i-1] == nums[i]) {
                continue;
            }
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1, l = nums.length-1; k < l;) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        resLists.add(list);

                        l -= 1;
                        k += 1;

                        while ((k < l) && (nums[l] == nums[l+1])) {
                            l-=1;
                        }
                        while ((k < l) && (nums[k] == nums[k-1])) {
                            k+=1;
                        }
                    }else if (sum > target){
                        l -= 1;
                        while ((k < l) && (nums[l] == nums[l+1])) {
                            l-=1;
                        }
                    }else if (sum < target) {
                        k += 1;
                        while ((k < l) && (nums[k] == nums[k-1])) {
                            k+=1;
                        }
                    }
                }

                while (j+1 < nums.length && nums[j+1] == nums[j]) {
                    j+=1;
                }
            }
        }

        return resLists;
    }

    public static void main(String[] args) {
        L018 l018 = new L018();
        int[] nums = new int[] {
                0,0,0,0
        };

        int target = 0;

        List<List<Integer>> ans = l018.fourSum(nums, target);
        System.out.println();
    }
}

