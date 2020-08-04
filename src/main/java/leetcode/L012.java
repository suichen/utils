package leetcode;

import java.util.HashMap;
import java.util.Map;

public class L012 {
    private String extract(int count, int digit, String res, Map<Integer, String> map) {
        if (digit > 0) {
            if (digit == 9 || digit == 4) {
                res = res + map.get(digit * count);
                return res;
            }

            if (digit >= 5) {
                res += map.get(5 * count);
                digit -= 5;
            }

            for (int i = 0; i < digit; i++) {
                res += map.get(count);
            }
        }
        return res;
    }
    public String intToRoman(int num) {
        String res = "";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(400, "CD");
        map.put(90, "XC");
        map.put(40, "XL");
        map.put(9, "IX");
        map.put(4, "IV");

        int individual = 0, ten = 0, hundred = 0, thousand = 0;

        int temp = num;
        thousand = temp / 1000;
        temp = temp % 1000;
        hundred = temp / 100;
        temp = temp % 100;
        ten = temp / 10;
        temp = temp % 10;
        individual = temp;

        if (thousand > 0) {
            for (int i = 0; i < thousand; i++) {
                res += map.get(1000);
            }
        }

        res = extract(100, hundred, res, map);
        res = extract(10, ten, res, map);
        res = extract(1, individual, res, map);
        return res;
    }
    public static void main(String[] args) {
        L012 l012 = new L012();
        int num = 2456;
        String res = l012.intToRoman(num);
        System.out.println(res);
    }
}
