package leetcode;

public class L014 {
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        if (strs == null || strs.length == 0) {
            return res;
        }

        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null || strs[i].length() == 0) {
                return res;
            }
        }

        int index = 0;
        char ch = 0;
        while (true) {
            if (index < strs[0].length()) {
               ch = strs[0].charAt(index);
            }
            for (int i = 0; i < strs.length; i++) {
                if ((index >= strs[i].length()) || (ch != strs[i].charAt(index))) {
                    return strs[0].substring(0, index);
                }
            }
            index += 1;
        }
    }
    public static void main(String[] args) {
        L014 l014 = new L014();
        String[] strs = new String[] {
                "flower","flow","flight"
        };
        String res = l014.longestCommonPrefix(strs);
        System.out.println(res);
    }
}
