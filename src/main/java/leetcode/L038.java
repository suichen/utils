package leetcode;

public class L038 {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        if (n == 3) {
            return "21";
        }

        String res = countAndSay(n-1), ans = "";
        int count = 1, i = 0;

        for (i = 1; i < res.length(); i++) {
            if (res.charAt(i-1) == res.charAt(i)) {
                count += 1;
                continue;
            }
            ans = ans + count + res.charAt(i-1);
            count = 1;
        }

        ans = ans + count + res.charAt(i-1);

        return ans;
    }
    public static void main(String[] args) {
        L038 l038 = new L038();
        String ans = l038.countAndSay(6);
        System.out.println(ans);
    }
}
