package leetcode;

public class L028 {
    //private boolean match()
    public int strStr(String haystack, String needle) {
        int index = -1;
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0 || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int end = i + needle.length();
                if (end < haystack.length()) {
                    if (needle.equals(haystack.substring(i, end))) {
                        index = i;
                        break;
                    }
                }
            }
        }
        return index;
    }
    public static void main(String[] args) {
        L028 l028 = new L028();
        /**
         * "mississippi"
         * "issipi"
         */
        String haystack = "mississippi", needle = "issipi";
        int index = l028.strStr(haystack, needle);
        System.out.println(index);

        System.out.println(Integer.MIN_VALUE/-1);
    }
}
