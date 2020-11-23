package leetcode;


import java.util.ArrayList;
import java.util.List;

public class L094 {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        inorderTraversal(root.left);
        ans.add(root.val);
        inorderTraversal(root.right);

        return ans;
    }
    public static void main(String[] args) {
        L094 l094 = new L094();

    }
}
