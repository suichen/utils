package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L022 {

    List<String> resLists = new ArrayList<>();

    private void dsf(String res, int i, int j, int n) {
        if (res.length() == n * 2) {
            resLists.add(res);
            return;
        }

        if (i < n) {
            dsf(res+'(', i + 1, j, n);
        }
        if (j < n && j < i) {
            dsf(res+')', i, j + 1, n);
        }
    }
    public List<String> generateParenthesis(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        dsf("", 0, 0, n);
        return resLists;
    }

    public static void main(String[] args) {
        L022 l022 = new L022();
        l022.generateParenthesis(3);
        System.out.println();
    }
}
