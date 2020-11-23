package leetcode;

public class L098 {

    private boolean isHelper(TreeNode node, Integer low, Integer upper) {

        if (node == null) {
            return true;
        }

        int val = node.val;
        if (low!=null && val <= low) {
            return false;
        }
        if (upper!=null && val >= upper) {
            return false;
        }


        if (!isHelper(node.right, val, upper)) {
            return false;
        }


        if (!isHelper(node.left, low, val)) {
            return false;
        }

        return true;
    }
    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        return true;

    }
    public static void main(String[] args) {

    }
}
