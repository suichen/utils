package leetcode;


import java.util.ArrayList;
import java.util.List;

public class L089 {

    List<Integer> ans = new ArrayList<>();

    private void generateNum(char[] str, int index) {

        if (index >= str.length) {
            return;
        }
        for (int i = index; i < str.length; i++) {
            str[i] = str[i] == '0' ? '1' : '0';
            ans.add(Integer.parseInt(new String(str), 2));
            generateNum(str, i+1);
            str[i] = str[i] == '1' ? '0' : '1';
        }
    }

    public List<Integer> grayCode(int n) {
         char[] characters= new char[n];

        for (int i = 0; i < n; i++) {
            characters[i] = '0';
        }

        ans.add(0);
        generateNum(characters, 0);

        return ans;
    }

    public static void main(String[] args) {
        L089 l089 = new L089();
        l089.grayCode(3);
        int[][] ints = new int[4][5];
        System.out.println();
    }
}
