package leetcode;

import java.util.HashMap;
import java.util.Map;

public class L013 {
    public int romanToInt(String s) {
        int res = 0;

        Map<Character, Integer> valueToSymbolMap = new HashMap<>();
        valueToSymbolMap.put('I', 1);
        valueToSymbolMap.put('V', 5);
        valueToSymbolMap.put('X', 10);
        valueToSymbolMap.put('L', 50);
        valueToSymbolMap.put('C', 100);
        valueToSymbolMap.put('D', 500);
        valueToSymbolMap.put('M', 1000);

        int lastValue = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            int value = valueToSymbolMap.get(s.charAt(i));

            if (i == s.length()-1) {
                res += value;
                lastValue = value;
                continue;
            }

            if (value < lastValue) {
                res = res - value;
            } else {
                res = res +value;
            }

            lastValue = value;
        }
        return res;
    }
    public static void main(String[] args) {
        L013 l013 = new L013();
        String s = "III";
        int num = l013.romanToInt(s);
        System.out.println(num);
    }
}
