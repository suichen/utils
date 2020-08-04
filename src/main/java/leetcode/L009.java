package leetcode;

public class L009 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int ans = 0, temp = x;

        while (x != 0) {
            ans = ans * 10 + x % 10;
            x = x / 10;
        }

        return  ans == temp;
    }
    public static void main(String[] args) {
        L009 l009 = new L009();
        int res = 10;
        boolean ans = l009.isPalindrome(res);
        System.out.println(ans);
    }
}
