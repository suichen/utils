package leetcode;

public class L003 {

    /**
     * length[i]表示s[i]结尾的无重复字符串长度
     *
     *
     * 那么 length[i-1]表示以s[i-1]结尾 以s[beginIndex]为起始 的无重复字符串
     *
     * 那么 index = s.substring(beginIndex, i).lastIndex(s[i])
     * if (index == -1) { //表示s[beginIndex]...s[i-1]中的字符没有与s[i]相同
     *     length = i - beginIndex + 1;
     * } else { //表示 s[beginIndex]...s[i-1]中的字符有与s[i]相同
     *  length = i - beginIndex - index;   //abcafdef
     *                                     //01234567
     *
     *   beginIndex = beginIndex+index+1;                                  //i = 7 beginIndex = 1 index = 3
     * }
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2Me(String s) {
        if (s == null || s == "") {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int length = 0, maxLength = 0;
        int beginIndex = 0;

        for (int i = 0; i < s.length(); i++) {

            int index = s.substring(beginIndex, i)
                            .lastIndexOf(s.charAt(i));

            if (index == -1) {
                length = i-beginIndex+1;
            } else {
                length = i-index-beginIndex;
                beginIndex = beginIndex+index+1;
            }

            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }

    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128];
        for (int i = 0; i < 128; i++) {
            lastIndex[i] = -1;
        }

        int start = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            start = Math.max(start, lastIndex[index]);
            res = Math.max(res, i-start+1);
        }

        return res;
    }
    public static void main(String[] args) {
        L003 l003 = new L003();
        String test = "aab";
        //length[i] = length[i-1][s[i]]

        System.out.println(l003.lengthOfLongestSubstring2Me(test));
    }
}
