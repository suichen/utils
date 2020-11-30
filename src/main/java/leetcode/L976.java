package leetcode;

import java.util.Arrays;

public class L976 {
    public int largestPerimeter(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }

        Arrays.sort(A);

        int len = A.length;

        for (int i = len - 1; i >= 2; i--) {
            int a = A[i-1], b = A[i-2];
            if (a+b > A[i]) {
                return a+b+A[i];
            }
        }

        return 0;
    }
    public static void main(String[] args) {
        L976 l976 = new L976();
        int[] A = new int[] {
                3,6,2,3
        };
        int res = l976.largestPerimeter(A);
        System.out.println(res);
    }
}
