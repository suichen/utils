package leetcode;

public class L063 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null) {
            return 0;
        }

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[] dp = new int[row];

        dp[0] = 1;
        if (obstacleGrid[0][0] == 1) {
            dp[0] = 0;
        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i] = 0;
                    continue;
                }
                if (i-1>=0 && obstacleGrid[i-1][j] == 0) {
                    dp[i] += dp[i-1];
                }
            }
        }

        return dp[row-1];
    }
    public static void main(String[] args) {

    }
}
