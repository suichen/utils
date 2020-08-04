package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L017 {
    Map<Integer, String> map = new HashMap<>();
    List<String> resLists = new ArrayList<>();

    private void calString(String digits, int index, StringBuffer res) {
        if (index == digits.length()) {
            resLists.add(res.toString());
            return;
        }

        int num = digits.charAt(index) - '0';

        String value = map.get(num);

        for (int i = 0; i < value.length(); i++) {
            calString(digits, index+1, res.append(value.charAt(i)));
        }
    }
    public List<String> letterCombinations(String digits) {

        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        StringBuffer res = new StringBuffer();
        calString(digits, 0, res);

        return resLists;
    }
    public static void main(String[] args) {
        L017 l017 = new L017();
        List<String> res = l017.letterCombinations("236");
        System.out.println();
    }
}
