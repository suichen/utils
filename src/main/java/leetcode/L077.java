package leetcode;

import java.util.ArrayList;
import java.util.List;

public class L077 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public void combineK(int n, int k, int cur) {
        if (temp.size()+n-cur+1 < k) {
            return;
        }

        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        if (cur > n) {
            return;
        }
        temp.add(cur);
        combineK(n, k, cur+1);
        temp.remove(temp.size()-1);
        combineK(n, k, cur+1);
    }
    public List<List<Integer>> combine(int n, int k) {

        if (k > n || n <= 0) {
            return new ArrayList<>();
        }

        combineK(n, k, 1);
        return ans;
    }
    public static void main(String[] args) {

        L077 l077 = new L077();
        l077.combine(4,2);
    }
}
