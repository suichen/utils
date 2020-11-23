package leetcode;

public class L082 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }

            return deleteDuplicates(head.next);
        }else {
            head.next = deleteDuplicates(head.next);
            return head;
        }

    }
    public static void main(String[] args) {

    }
}
