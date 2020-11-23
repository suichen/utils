package leetcode;


public class L091 {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }


        int[] dp = new int[s.length()];

        dp[0] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                dp[i] = dp[i-1];
            }


            String subStr = s.substring(i - 1, i + 1);
            int num = Integer.parseInt(subStr);
            if (num >= 10 && num <= 26) {
                dp[i] = dp[i] + (i >= 2 ? dp[i - 2] : 1);
            }

        }

        return dp[s.length()-1];
    }
    public static void main(String[] args) {
        L091 l091 = new L091();
        int ans = l091.numDecodings("10211");
        System.out.println(ans);
    }
}
