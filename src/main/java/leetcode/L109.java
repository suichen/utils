package leetcode;

public class L109 {

    private ListNode getMedian(ListNode left, ListNode right) {

        ListNode slow = left, fast = left;

        while (fast!=right &&
                fast.next != right &&
                fast.next != null) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
    private TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode median = getMedian(left, right);
        TreeNode root = new TreeNode(median.val);
        root.left = buildTree(left, median);
        root.right = buildTree(median.next, right);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        return buildTree(head, null);
    }


    public static void main(String[] args) {
        L109 l109 = new L109();
        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode root = l109.sortedListToBST(head);
        System.out.println();
    }
}
