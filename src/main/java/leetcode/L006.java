package leetcode;

public class L006 {

    public String convert(String s, int numRows) {
        if (s == "" || s == null || numRows <= 1) {
            return s;
        }

        StringBuffer sbf = new StringBuffer();
        int n = s.length();

        int steps = (numRows-2)*2+2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j+=steps) {
                sbf.append(s.charAt(i+j));
                if ((i!=0) && (i!=numRows-1) && ((i + j + (numRows - i) * 2 - 2) < n)) {
                    sbf.append(s.charAt(i + j + (numRows - i) * 2 - 2));
                }
            }
        }
        return sbf.toString();
    }
    public static void main(String[] args) {
        L006 l006 = new L006();
        String s = "LEETCODEISHIRING";

        String res = l006.convert(s, 3);

        System.out.println(res.equals("LCIRETOESIIGEDHN"));
    }
}
