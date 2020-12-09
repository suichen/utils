package leetcode;


public class L767 {
    public String reorganizeString(String S) {
        if (S == null || "".equals(S)) {
            return "";
        }

        char[] characters = new char[26];
        int max = -1;

        for (int i = 0; i < S.length(); i++) {
            characters[S.charAt(i)-'a']+=1;
            if (characters[S.charAt(i)-'a'] > max) {
                max = characters[S.charAt(i)-'a'];
            }
        }

        if (max > (S.length()+1)/2) {
            return "";
        }

        int half = S.length()/2;
        char[] ans = new char[S.length()];
        int odd = 1, even = 0;
        for (int i = 0; i < 26; i++) {

            while (characters[i] > 0 && odd < S.length() && characters[i] <= half) {
                ans[odd]= (char) (i+'a');
                characters[i]--;
                odd+=2;
            }

            while (characters[i] > 0) {
                ans[even] =  (char) (i+'a');
                characters[i]--;
                even+=2;
            }
        }

        return new String(ans);
    }

    public static void main(String[] args) {

    }
}
