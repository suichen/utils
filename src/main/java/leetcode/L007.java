package leetcode;

public class L007 {
    public int reverse(int x) {

        int res = 0;

        while (x != 0) {
            int pop = (x % 10);
            if ((res > Integer.MAX_VALUE/10) ||
                    (res == Integer.MAX_VALUE/10 && pop > 7)) {
                return 0;
            }

            if ((res < Integer.MIN_VALUE/10) ||
                    (res == Integer.MIN_VALUE/10 && pop < -8))
            res = res * 10 + pop;
            x = x / 10;
        }

        return res;
    }
    public static void main(String[] args) {
        L007 l007 = new L007();
        int num = 1534236469;
        int res = l007.reverse(num);
        System.out.println(res);
    }
}
