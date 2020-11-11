/**
 * leetcode : https://leetcode.com/problems/reverse-linked-list-ii/
 * - clarification:
 *   The list is empty: null.
 *   n = m
 *
 * - solutions:
 *   1. Change the pointer of the node which is between the m and n while iteration using two pointers(cur and pre).
 *      Store the special position to link the new list. (con and tail)
 *      Careful about the special case.
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 *   2. Instead of changing the pointers, we can modify the values of the nodes for achieving the reversal.
 *      We can use two pointers to point at the end and beginning of the sublist and move the towards the center.
 *      At the same time, we change the value of two nodes pointed by the pointers.
 *      Because it's a linked list, we don't have indices to access the value randomly,
 *      we can use recursion to simulate the process. The backtrack of the recursion makes the backwards possible.
 *      Time complexity: O(n)
 *      In the worst case, we process all the nodes during the recursion and process half of nodes during the backtrack.
 *      Space complexity: O(n) occupied by the recursion stack.
 *
 * - Test cases:
 *   1. null, n = m = 0
 *   2. 1->null, n = m = 1
 *   3. 2->8->null n = 1, m= 2
 *   4. 2->8->3->5->null n = 1, m = 2
 *   5. 2->8->3->5->null n = 2, m = 4
 *
 * - Important key:
 *   Link the reversed sublist with the rest of the list. (Remember the special position)
 *
 * - Related problems:
 *   206, 24, 141, 142, 25
 */

public class lc92ReLList2 {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

/* Solution 1:
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n == m) {return head;}
        int count = 0;
        ListNode cur = head;
        ListNode pre = null;

        // the tail of the reverse sublist.
        ListNode curm = null;
        // connects to the new head of the reversed sublist.
        ListNode prem = null;

        while (cur != null) {
            count++;
            if (count <= n && count > m) {
                ListNode  temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
                if (count == n) {
                    curm.next = cur;
                    // if m = 1, prem is null and don't have `next`.
                    if (m != 1) prem.next = pre;
                    // if m = 1, curm = head and curm only linked the first node with the (n+1)th node to the end, which isn't the result.
                    else head = pre;
                }
            }else if (count == m) {
                    curm = cur;
                    prem = pre;
                    pre = cur;
                    cur = cur.next;
                    if (m != 1 ) prem.next = null;
                    curm.next = null;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
 */

/* Solution 1: a better version. */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n == m) {return head;}
        ListNode cur = head, pre = null, con, tail;

        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        con = pre;
        tail = cur;

        ListNode temp;
        while (n > 0) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            n--;
        }

        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }

        tail.next = cur;
        return head;
    }


/* Solution 2:
    private ListNode left;
    private boolean stop = false;

    public void reverse(ListNode right, int m, int n) {
        if(n == 1) return;

        if (m > 1) left = left.next;
        right = right.next;

        reverse(right,m-1,n-1);

        if(right == left || right.next == left) {stop = true;}
        if(!stop) {
            int num = left.val;
            left.val = right.val;
            right.val = num;

            left = left.next;
        }

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || n == m) {return head;}
        ListNode right = head;
        left = head;
        reverse(right,m,n);
        return head;
    }
 */
}
