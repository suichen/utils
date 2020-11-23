package leetcode;

public class L023 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        return mergeLists(lists, 0, lists.length-1);
    }



    private ListNode mergeLists(ListNode[] listNodes, int low, int high) {
        if (low == high) {
            return listNodes[low];
        }


        int mid = (low+high)/2;

        return null;
        //return merge2Lists()
    }
    public ListNode merge2Lists(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (; node1!=null && node2!=null;) {
            if (node1.val <= node2.val) {
                curNode.next = node1;
                node1 = node1.next;
            }else {
                curNode.next = node2;
                node2 = node2.next;
            }
        }

        if (node1!=null) {
            curNode.next = node1;
        }
        if (node2!=null) {
            curNode.next = node2;
        }
        return head.next;
    }

    public static void main(String[] args) {

    }
}
