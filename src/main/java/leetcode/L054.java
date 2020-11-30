package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L054 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        int rowNums = matrix.length-1;
        int colNums = matrix[0].length-1;

        int top = 0, bottom = rowNums;
        int left = 0, right = colNums;

        int nums = matrix.length * matrix[0].length;

        while(left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top+1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }

            if (right > left && bottom > top) {
                for (int i = right-1; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                for (int i = bottom+1; i >= top-1; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left += 1;
            right -= 1;
            top += 1;
            bottom -= 1;
        }

        return res;
    }
    public static void main(String[] args) {
        L054 l054 = new L054();
        int[][] nums = new int[][] {
                {1, 2, 3,},
                {4, 5, 6},
                {7,8,9}
        };

        List<Integer> res = l054.spiralOrder(nums);
        System.out.println();
    }
}
