package leetcode;

public class L021 {
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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            if (l1!=null) {
                return l1;
            }
            if (l2!=null) {
                return l2;
            }
        }

        ListNode resNode = new ListNode();
        ListNode cur = resNode;

        for (; l1!=null && l2!=null;) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
                cur.next.next = l2;
                l2 = l2.next;
                cur = cur.next.next;
            }
        }

        if (l1!=null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }

        return resNode.next;
    }
    public static void main(String[] args) {

    }
}
