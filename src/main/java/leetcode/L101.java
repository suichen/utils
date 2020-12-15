package leetcode;

public class L101 {
    private boolean check(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }

        if (leftNode == null || rightNode == null) {
            return false;
        }

        return leftNode.val == rightNode.val && check(leftNode.left, rightNode.right)
                && check(leftNode.right, rightNode.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root, root);
    }

    public static void main(String[] args) {

    }
}
