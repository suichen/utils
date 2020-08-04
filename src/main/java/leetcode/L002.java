package leetcode;


public class L002 {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {

        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private int calRemainNode(ListNode node, int count) {
        while (node!=null) {
            int originalVal = node.val;
            node.val = (originalVal+count)%10;
            count = (originalVal+count)/10;
            node = node.next;
        }
        return count;
    }

    private void calCount(ListNode node, int count) {
        if (count == 0) {
            return;
        }
        while (node.next!=null) {
            node = node.next;
        }
        node.next = new ListNode(count);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null ) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode l1Node = null, l2Node = null;
        int count = 0;

        for (l1Node = l1, l2Node = l2; (l1Node != null) && (l2Node!=null);
             l1Node = l1Node.next, l2Node = l2Node.next) {
            int node1Val = l1Node.val;
            int node2Val = l2Node.val;

            int val = (node1Val+node2Val+count)%10;
            count = (node1Val+node2Val+count)/10;

            l1Node.val = val;
            l2Node.val = val;
        }

        if (l1Node!=null) {
            count = calRemainNode(l1Node, count);
            calCount(l1, count);
            return l1;
        }

        if (l2Node!=null) {
            count = calRemainNode(l2Node, count);
            calCount(l2, count);
            return l2;
        }

        calCount(l1, count);
        return l1;
    }
}
