package leetcode;

public class L029 {
    private int divideNegtivate(int dividend, int divisor) {
        int ans = 0;
        if (dividend > divisor) {
            return 0;
        }

        int sum = divisor, count = 1;

       while (sum + sum < 0 && dividend <= sum) {
           dividend -= sum;
           ans += count;
           sum += sum;
           count += count;
       }

       if (dividend <= sum && sum + sum >= 0) {
           dividend -= sum;
           ans += count;
       }

       return ans + divideNegtivate(dividend, divisor);
    }

    public int divide(int dividend, int divisor) {
        int ans = 0, signal = 1;
        if (divisor == 0) {
            return 0;
        }

        if (divisor == 1) {
            return dividend;
        }

        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return -dividend;
            }
        }

        if ((dividend > 0 && divisor > 0)
                || (dividend < 0 && divisor < 0)) {
            signal = 1;
        }else {
            signal = -1;
        }
        dividend = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;

        ans = divideNegtivate(dividend, divisor);

        return signal > 0 ? ans : -ans;
    }
    public static void main(String[] args) {
        L029 l029 = new L029();
        int dividend = 7;
        int divisor = -3;

        int res = l029.divide(dividend, divisor);

        System.out.println(res);
    }
}
