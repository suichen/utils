package leetcode;

public class L086 {
    public ListNode partition(ListNode head, int x) {

        if (head == null) {
            return null;
        }

        ListNode currNode = head, lessXNode = null, moreXNode = null;

        for (; currNode!=null; currNode = currNode.next) {
            if (currNode.val < x) {
                lessXNode = currNode;
            } else {
                moreXNode = currNode;
                break;
            }
        }

        for (; currNode!=null && currNode.next!=null;) {
            if (currNode.next.val < x) {
                ListNode nextNext = currNode.next.next;
                ListNode next = currNode.next;

                currNode.next = nextNext;

                if (lessXNode == null) {
                    head = next;
                    next.next = moreXNode;
                    lessXNode = next;
                }else {
                    lessXNode.next = next;
                    next.next = moreXNode;
                    lessXNode = next;
                }
            }else{
                currNode = currNode.next;
            }
        }

        return head;
    }
    public static void main(String[] args) {
        L086 l086 = new L086();
        ListNode node = new ListNode(3);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);

        ListNode head = l086.partition(node, 3);
        System.out.println();
    }
}
