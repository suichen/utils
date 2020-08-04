package leetcode;

public class L019 {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int length = 0;
        ListNode resNode = head;

        while (head!=null) {
            length += 1;
            head = head.next;
        }

        head = resNode;

        int index = length - n;

        if (index == 0) {
            return resNode.next;
        }

        for (int i = 1; i < index; i++) {
            head = head.next;
        }

        if (head.next!=null) {
            head.next = head.next.next;
        }

        return resNode;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;

        ListNode cur = head, resNode = pre;

       for (int i = 1; i < n; i++) {
           cur = cur.next;
       }

        while (cur.next!=null) {
            cur = cur.next;
            pre = pre.next;
        }

        pre.next = pre.next.next;

        return resNode.next;
    }
    public static void main(String[] args) {

    }
}
