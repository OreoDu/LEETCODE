/**
 * leetcode 206: https://leetcode.com/problems/reverse-linked-list/
 * - clarification:
 *   The list is empty: null
 *
 * - solutions:
 *   1. Add every element in front of the new list while traverse the list.
 *      Time complexity: O(n)
 *      Space complexity: O(n) to store the new list.
 *   2. Change the pointer directly in the original list.
 *      Time complexity: O(n)
 *      Space complexity: O(1) to store temporary ListNode.
 *   3. Recursion: the same with the solution 2 but use recursion to traverse the list.
 *
 * - Test cases:
 *   1. 1->null
 *   2. null
 *   3. 1->2->3->9->null
 *
 * - Important key:
 *   Find a proper way(recursive or iterative) to change the pointer.
 *
 * - Related problems:
 *   92, 24, 141, 142, 25
 */

public class lc206ReLList1 {
    private class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
/* Solution 1:
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode first = new ListNode(head.val);
        ListNode p = head.next;
        while (p != null ) {
            ListNode temp = new ListNode(p.val,first);
            first = temp;
            p = p.next;
        }
        return first;
    }
*/

/* Solution 2: Using two pointers cur,pre.
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode cur = head;
        ListNode pre = null;
        while(cur !=null) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
*/

/* Solution 3: deal the node during the recursion.
    public ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) return pre;

        ListNode nex = cur.next;
        cur.next = pre;
        return reverse(cur,nex);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {return head;}

        return reverse(pre,head);
    }
*/

/* Solution 3: deal with the node after drilling down.
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
           return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
 */
}
