import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * - LeetCode : https://leetcode.com/problems/largest-rectangle-in-histogram/
 * - clarification: (Corner case...)
 *   heights = null;
 *   heights = [];
 *   heights = [1];
 *
 * - solutions:
 *   1. The simplest way is to iterate through the array and for each element we find biggest rectangle it can form.
 *      For heights[i]:
 *      find the first elements that are smaller than the heights[i] from both the left and right side of i.
 *      Time complexity: O(n^2)
 *      Space complexity: O(1)
 *
 *   2. Monotone Stack: After a certain number, the first number is smaller than itself.
 *                       You can use monotonically increasing stack
 *                       (because the stack indicates that the current element is smaller than the top element of the stack);
 *                       after a certain number, the first one is smaller than For large numbers,
 *                       you can use monotonically decrementing the stack
 *                       (because popping out indicates that the current element is larger than the top element)
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. [2,1,5,6,2,3]
 *   2. null
 *   3. []
 *   4. [1]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   85, 42
 */

public class lc84LargestRectangleInHistogram {
/* Solution 1:
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int res = 0;

        for (int i = 0; i < heights.length; i++) {
            int left = i, right = i;
            while (left >= 0 && heights[left] >= heights[i])
                left--;
            while (right < heights.length && heights[right] >= heights[i])
                right++;
            res = Math.max(res, (right - left - 1) * heights[i]);
        }
        return res;
    }
 */


/* Solution 2 version 1: use stack to get the right and left boundary of heights[i].

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> mono_stack = new Stack<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
 */

/* Solution 2 version 2:
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Stack<Integer> mono_stack = new Stack<>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
    */

/* Solution 2 version 3:
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int res = 0;

        Deque<Integer> stack = new ArrayDeque<>(heights.length);

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] >= heights[i]) {
                int j = stack.pollLast();
                if (stack.isEmpty()) res = Math.max(res, i * heights[j]);
                else res = Math.max(res, (i - stack.peekLast() - 1) * heights[j]);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pollLast();
            if (stack.isEmpty()) res = Math.max(res, heights.length * heights[j]);
            else res = Math.max(res, (heights.length - 1 - stack.peekLast()) * heights[j]);
        }
        return res;
    }
 */

// Solution 2 version 4:
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int len = heights.length;

        int res = 0;

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;

        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }

    public static void main(String[] args) {
        lc84LargestRectangleInHistogram s = new lc84LargestRectangleInHistogram();
        int[] heights = {3,2,1,5,6};
        int res = s.largestRectangleArea(heights);
        System.out.println(res);
    }
}
