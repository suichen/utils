package leetcode;

public class L072 {
    //horse ros
    //hors ros -> horse ros (a+1) add
    //horse ros -> done
    //horse ro -> horse ros (b+1)
    //hors ro -> horse ros (c+1) change
    // min(a+1, b+1, c+1)
    //dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int a = dp[i-1][j]+1;
                int b = dp[i][j-1]+1;
                int c = dp[i-1][j-1];

                if (word1.charAt(i-1) != word2.charAt(j-1)) {
                    c += 1;
                }

                dp[i][j] = Math.min(a, Math.min(b, c));
            }
        }

        return dp[len1][len2];
    }
    public static void main(String[] args) {
        L072 l072 = new L072();
        String word1 = "intention";
        String word2 = "execution";

        int res = l072.minDistance(word1, word2);

        System.out.println(res);
    }
}
