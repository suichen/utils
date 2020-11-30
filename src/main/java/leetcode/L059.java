package leetcode;

public class L059 {

    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        if (n == 1) {
            matrix[0][0] = 1;
            return matrix;
        }

        int low = 0, high = n - 1, cnt = 1;

        while (low <= high) {

            for (int i = low; i <= high; i++) {
                matrix[low][i] = cnt++;
            }

            for (int i = low+1; i <= high; i++) {
                matrix[i][high] = cnt++;
            }

            for (int i = high-1; i >= low; i--) {
                matrix[high][i] = cnt++;
            }

            for (int i = high-1; i > low; i--) {
                matrix[i][low] = cnt++;
            }

            low += 1;
            high -= 1;
        }
        return matrix;
    }

    public static void main(String[] args) {
        L059 l059 = new L059();
        int[][] res = l059.generateMatrix(2);
        System.out.println();
    }
}
