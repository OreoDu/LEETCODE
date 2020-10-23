/**
 * - LeetCode : https://leetcode.com/problems/reverse-nodes-in-k-group/
 * - clarification:
 *   k = 1.
 *
 * - solutions:
 *   1. Change the links between the nodes in k-group during the recursion,
 *      everytime the last node is the head of the reversed group
 *      which should returned to the upper level of the recursion,
 *      and the first node is the tail of the the reversed group.
 *      After we get the return value from the lower level, we should link the tail to it.
 *      Time complexity: O(n)
 *      Space complexity: O(n) occupied by the recursion stack.
 *   2. Change the links between the nodes during the iteration, using the pointers to remember the position.
 *      `preTail`: the tail of previous group
 *      `curTail`: the tail of current group
 *      The tail should link the first node of the group.
 *      Time complexity: O(n)
 *      Space complexity: O(1)

 * - Test cases:
 *   1. [1,2] , k = 1
 *   2. [1,2,3,4,5], k = 2
 *   3. [1,4,5,2,7,8,9,0], k = 3
 *
 * - Important key:
 *   We have to use pointers carefully in case we lose or change some references without knowing it.
 *
 * - Related problems:
 *   24, 206, 92, 142, 141
 */


public class lc25ReKGroup {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

/* Solution 1:
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)  return head;
        ListNode p = head;
        int l = k;
        while(l>1) {
            l--;
            p = p.next;
            if (p == null) return head;
        }

        ListNode nex = reverseKGroup(p.next, k);
        ListNode pre = head;
        ListNode cur = head.next;

        head.next = nex;
        ListNode temp = null, n = p.next;
        while (cur != n) {
            temp = cur.next;
            cur.next = pre;

            pre = cur;
            cur = temp;
        }
        return p;
    }
*/
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode curTail = head, preTail = null;
        ListNode pre = null, cur = head;

        while (cur != null) {
            ListNode p = cur;
            int l = k;
            while(l>1) {
                l--;
                p = p.next;
                if (p == null) return head;
            }

            ListNode temp, n = p.next;
            while (cur != n) {
                temp = cur.next;
                cur.next = pre;

                pre = cur;
                cur = temp;
            }
            if (preTail != null) preTail.next = pre;
            else head = pre;
            // Important!! 1) Avoid cycle 2) We have to link the group that isn't reversed.
            // Or we can set curTail.next = null,
            // but we have to add `preTail.next = cur;` in the first while loop to link the last unreversed part.
            curTail.next = cur;

            preTail = curTail;
            curTail = cur;
        }
        return head;
    }
}
