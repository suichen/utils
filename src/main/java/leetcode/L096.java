package leetcode;

public class L096 {

    int[][] dp;
    private int numSubTrees(int start, int end) {

        int num = 0;

        if (start == end) {
            dp[start][end] = 1;
            return 1;
        }
        if (start > end) {
            dp[start][end] = 0;
            return 0;
        }

        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        for (int i = start; i <= end; i++) {
            int leftNums = numSubTrees(start, i-1);
            int rightNums = numSubTrees(i+1, end);

            dp[start][i-1] = leftNums;
            dp[i+1][end] = rightNums;

            if (leftNums == 0) {
                leftNums = 1;
            }

            if (rightNums == 0) {
                rightNums = 1;
            }

            num = num + leftNums*rightNums;
        }
        dp[start][end] = num;
        return num;
    }
    public int numTrees(int n) {
        if (n <= 0) {
            return 0;
        }

        dp = new int[n+2][n+2];

        for (int i = 0; i <= n+1; i++) {
            for (int j = 0; j <= n+1; j++) {
                dp[i][j] = -1;
            }
        }

        return numSubTrees(1,n);
    }
    public static void main(String[] args) {
        L096 l096 = new L096();
        int ans = l096.numTrees(4);
        System.out.println(ans);
    }
}
