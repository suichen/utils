package leetcode;

public class L024 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dumpy = new ListNode(0);
        dumpy.next = head;

        ListNode preNode = dumpy, curNode = head;

        while (curNode!=null) {
            ListNode nextNode = curNode.next;
            preNode.next = nextNode;
            nextNode.next = curNode;

            preNode = nextNode;

        }
        return dumpy.next;
    }
    public static void main(String[] args) {
        System.out.println("test");
    }
}
