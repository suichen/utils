package leetcode;


import java.util.ArrayList;
import java.util.List;

public class L095 {
    private List<TreeNode> generateTrees(int start, int end) {

        List<TreeNode> nodes = new ArrayList<>();

        if (start > end) {
            nodes.add(null);
            return nodes;
        }

        if (start == end) {
            TreeNode node = new TreeNode(start);
            nodes.add(node);
            return nodes;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i-1);
            List<TreeNode> rightTrees = generateTrees(i+1, end);

            for (TreeNode left:leftTrees) {
                for (TreeNode right:rightTrees) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    nodes.add(node);
                }
            }
        }

        return nodes;
    }
    public List<TreeNode> generateTrees(int n) {

        if (n == 0) {
            return new ArrayList<>();
        }

        return generateTrees(1, n);

    }
    public static void main(String[] args) {
        L095 l095 = new L095();
        List<TreeNode> res = l095.generateTrees(3);
        System.out.println();
    }
}
