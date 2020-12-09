package leetcode;

import java.util.HashMap;
import java.util.Map;

public class L076 {

    private boolean match(Map<Character, Integer> schars, Map<Character, Integer> tchats) {
        for (Map.Entry<Character, Integer> entry:tchats.entrySet()) {
            Character key = entry.getKey();
            int val = entry.getValue();

            if (val == 0) {
                continue;
            }

            if (!schars.containsKey(key)) {
                return false;
            }

            if (schars.get(key) < val) {
                return false;
            }
        }

        return true;
    }
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }

        int left = 0, right = 0, maxLength = s.length()+1, ansIndex = -1;

        int lenS = s.length(), lenT = t.length();

        Map<Character, Integer> tchars = new HashMap<>();
        Map<Character, Integer> schars = new HashMap<>();

        for (int i = 0; i < lenT; i++) {
            if (!tchars.containsKey(t.charAt(i))) {
                tchars.put(t.charAt(i), 0);
            }
            tchars.put(t.charAt(i), tchars.get(t.charAt(i))+1);
        }

        while (right < lenS) {
            if (!schars.containsKey(s.charAt(right))) {
                schars.put(s.charAt(right), 0);
            }

            schars.put(s.charAt(right), schars.get(s.charAt(right))+1);
            while (match(schars, tchars)) {
                if (right-left+1 < maxLength) {
                    maxLength = right - left + 1;
                    ansIndex = left;
                }
                int val = schars.get(s.charAt(left));
                schars.put(s.charAt(left), val-1);
                left++;
            }

            right++;
        }

        return maxLength > s.length() ? "" : s.substring(ansIndex, ansIndex+maxLength);
    }
    public static void main(String[] args) {
        L076 l076 = new L076();
        String s = "ADOBECODEBANC", t = "ABC";
        String s1 = l076.minWindow(s, t);
        System.out.println(s1);
    }
}
