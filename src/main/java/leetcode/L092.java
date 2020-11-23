package leetcode;


import java.util.List;


public class L092 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }

        int cnt = 1;
        ListNode wrapperHead = new ListNode(0);
        wrapperHead.next = head;

        head = wrapperHead;
        for (; cnt < m; m++) {
            head = head.next;
        }

        ListNode originalMPreNode = head, originalMNode = head.next;

        ListNode nNode = originalMNode, nNextNode = null, nNodePreNode = originalMPreNode;

        for (; m <= n; m++) {
            nNextNode = nNode.next;
            nNode.next = nNodePreNode;
            nNodePreNode = nNode;
            nNode = nNextNode;
        }

        originalMPreNode.next = nNodePreNode;
        originalMNode.next = nNode;
        return wrapperHead.next;

    }
    public static void main(String[] args) {

    }
}
