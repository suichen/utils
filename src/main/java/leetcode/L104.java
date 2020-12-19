package leetcode;

public class L104 {
    private int depth = -1;
    private int findMath(TreeNode node, int count) {
        if (node == null) {
            if (count > depth) {
                depth = count;
            }
            return depth;
        }

        int left = findMath(node.left, count+1);
        int right = findMath(node.right, count+1);

        if (left > depth) {
            depth = left;
        }

        if (right > depth) {
            depth = right;
        }

        return depth;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return findMath(root, 0);
    }
    public static void main(String[] args) {

    }
}
