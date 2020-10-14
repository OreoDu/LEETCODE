import java.util.HashSet;
import java.util.Set;

/**
 * - leetcode : https://leetcode.com/problems/linked-list-cycle-ii/
 * - clarification:
 *   The list is empty or just have one element.
 *
 * - solutions:
 *   1. Store the nodes that we have already visited in a set.
 *      Once we found one node that is in the set,
 *      it means that cycle exists in list and that position is the entrance of the cycle.
 *      Time complexity: O(n). We have to traverse the whole lists.
 *      Space complexity: O(n). we have to add all the elements to the set in the worst case.
 *
 *   2. Floyd's cycle detection:
 *      Use two pointers(fast and slow) that move different steps during the iteration,
 *      and after a few steps if two pointers meet it means that the cycle exists.
 *      Related proof:
 *      https://math.stackexchange.com/questions/913499/proof-of-floyd-cycle-chasing-tortoise-and-hare
 *      https://hongyangyu.github.io/algorithm/2017/09/04/Fast-Pointer-and-Slow-Pointer/
 *      https://zhuanlan.zhihu.com/p/60736361
 *
 *      Suppose that slow pointer starts at head and move one step everytime.
 *      The fast pointer starts at head.next and move two steps everytime.
 *      Before the entrance of the cycle there are `a` steps, then after `b` steps they meet,
 *      and there are `c` steps left to the entrance of the cycle.
 *
 *      Based above, we can make a equation: a + n(b+c) + b = 2(a+b) + 1
 *      So we can see that a = (n-1)(b+c) + c.
 *      It means that after they meet if we set the fast point at the head,
 *      the slow pointer continue moving and finally slow.next will meet the fast pointer at the entrance of the cycle.
 *
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   1. [1,2,5,6,7] pos = 0
 *   2. [1,2,4,5,6] pos = 4
 *   3. [1,2,3,9]   pos = -1
 *   5. []          pos = -1
 *   6. [1]         pos = 0
 *
 * - Important key:
 *   Cycle detection.
 *
 * - Related problems:
 *   202, 141, 206, 24, 92, 25
 */

public class lc142LListCycle {
    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
/* Solution 1:
    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();

        while(pos != null) {
            if (!visited.add(pos)) return pos;
            pos = pos.next;
        }
        return null;
    }
 */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != slow) {
            if (fast == null || slow == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (slow.next != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
