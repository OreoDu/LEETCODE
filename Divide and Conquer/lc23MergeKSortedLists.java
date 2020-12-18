import java.util.PriorityQueue;

/**
 * - LeetCode : https://leetcode.com/problems/merge-k-sorted-lists/
 * - clarification:
 *  lists = null
 *  lists = [null,]
 *  lists = [[1,2,3]]
 *
 * - solutions:
 *   1. Divide and Conquer
 *      This problem can be divided into many the same sub-problems.
 *      Top-down: The lists can be divided into -> (k/2 lists, k/2 lists) -> (k/4 lists, k/4 lists, k/4 lists, k/4 lists)...-> (1,1,1,...1)
 *      Bottom-up: Continuously merge the two adjacent linked lists to get the final result.
 *                 First round: k/2 pairs lists that need to be merged, every merge operation cost O(2n). (n is the length of the list)
 *                 ...
 *                 The ith round: k/(2^i), pairs lists that need to be merged, every merge operation cost O(2^i * n).
 *                 There will be logk rounds in total.
 *      Time complexity: (nk logk)
 *      Space complexity: O(logk) for the recursion.
 *
 *   2. Priority Queue
 *      Instead of just merging two lists every time, we can merge k lists at one time.
 *      Now the problem is how to get the smallest value in these lists in an efficient way.
 *      Because the lists are all sorted, so we just have to compare the values of the first nodes among these lists.
 *      The priority queue is a efficient data structure to keep recording the max/min of certain data set.
 *      So we can store the k nodes(the first node of every list) into the priority queue.
 *      Time complexity:
 *      Space complexity:

 * - Test cases:
 *   1. []       >>> []
 *   2. [[]]     >>> []
 *   3. [[],[3],[3->4->5]]
 *   4. [[2->4->7],[3->4->5->7],[1->6->9],[],[1->4->10],[3->6->8]]
 *
 * - Important key:
 *   Use O(1) space complexity to merge two ordered linkedList.
 *
 * - Related problems:
 *
 */


public class lc23MergeKSortedLists {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

/* Solution 1:
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(-1, null);
        ListNode curr = result;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                curr = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;

        return result.next;
    }

    public ListNode mergeKList(int lo, int hi, ListNode[] lists) {
        if (lo >= hi) return lists[lo];

        int mid = (hi + lo) / 2;
        ListNode l1 = mergeKList(lo, mid, lists);
        ListNode l2 = mergeKList(mid + 1, hi, lists);
        ListNode result = mergeTwoList(l1, l2);

        return result;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];

        return mergeKList(0, lists.length - 1, lists);
    }

 */
    private class firstNode implements Comparable<firstNode> {
        private int val;
        private ListNode node;

        public firstNode(int val, ListNode node) {
            this.val = val;
            this.node  = node;
        }

        public int compareTo(firstNode other) {
            if (val > other .val) return 1;
            else if (val < other.val) return -1;
            else return 0;
        }
}

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        if (lists.length == 1) return lists[0];

        PriorityQueue<firstNode> q = new PriorityQueue<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;
            firstNode f = new firstNode(lists[i].val, lists[i]);
            q.offer(f);
        }

        ListNode result = new ListNode(-1, null);
        ListNode curr = result;

        while (!q.isEmpty()) {
            firstNode min = q.poll();
            curr.next = min.node;
            curr = curr.next;
            if (min.node.next != null) {
                firstNode f = new firstNode(min.node.next.val, min.node.next);
                q.offer(f);
            }
        }
        return result.next;
    }

    public static void main(String[] args) {


    }

}
