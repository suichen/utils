package leetcode;

public class L008 {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        str = str.trim();
        if (str == "" || str.length() == 0) {
            return 0;
        }

        int count = 1, ans = 0;

        if (str.charAt(0) == '-') {
            count = -1;
            str = "0"+str.substring(1);
        }

        if (str.charAt(0) == '+') {
            str = "0"+str.substring(1);
        }


        if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= '0' && ch <= '9') {
                    int digit = ch - '0';
                    digit *= count;

                    if (ans > Integer.MAX_VALUE/10 ||
                            (ans == Integer.MAX_VALUE/10 && digit > 7)) {
                        return Integer.MAX_VALUE;
                    }
                    if (ans < Integer.MIN_VALUE/10 ||
                            (ans == Integer.MIN_VALUE/10 && digit < -8)) {
                        return Integer.MIN_VALUE;
                    }

                    ans = ans * 10 + digit;
                }else {
                    break;
                }
            }
            return ans;
        }

        return 0;
    }
    public static void main(String[] args) {
        L008 l008 = new L008();
        String str = "2147483648";
        int res = l008.myAtoi(str);
        System.out.println(res);
    }
}
