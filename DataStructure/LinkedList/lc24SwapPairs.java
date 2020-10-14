/**
 * leetcode : https://leetcode.com/problems/swap-nodes-in-pairs/
 * - clarification:
 *   The list is empty : null
 *   The list only have one element.
 *
 * - solutions:
 *   1. change the links between the nodes during the iteration, using the pointers to remember the position.
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 *   2. change the links between the nodes during the recursion,
 *      and everytime the second node is the head of the reversed pair.
 *      Time complexity: O(n)
 *      Space complexity: O(n)

 * - Test cases:
 *   1. null
 *   2. 1->null
 *   3. 1->2->3->null
 *   4. 1->2->3->4->null
 *
 * - Important key:
 *   Use special pointers to manipulation the nodes during iteration or recursion.
 *
 * - Related problems:
 *   206, 92, 141, 142, 25
 */

public class lc24SwapPairs {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

/* Solution 1:
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode pre = null, cur = head, nex = head.next;

        while (cur != null && nex != null) {
            cur.next = nex.next;
            nex.next = cur;
            if(pre != null) pre.next = nex;
            else head = nex;

            pre = cur;
            cur = cur.next;
            if (cur != null) nex = cur.next;
            else nex = null;
        }
        return head;
    }
 */

/* Solution 2:
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {return head;}
        ListNode cur = head, nex = head.next;

        cur.next = swapPairs(nex.next);
        nex.next = cur;

        return nex;
    }
*/
}
