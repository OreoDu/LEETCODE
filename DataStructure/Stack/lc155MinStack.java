import java.util.Stack;

/**
 * - LeetCode : https://leetcode.com/problems/min-stack/
 * - solutions:
 *   1. Use helper stack to store the min value.
 *      Time complexity: all operations are O(1).
 *      Space complexity: O(n) helper stack
 *
 *   2. No helper stack, just store the minimum value in a variable.
 *      When we push the new element and have to update the min,
 *      we push the current min into the stack.
 *
 *      When we pop the element and it is equal to the min,
 *      we have to pop the next value and update the min.
 *      Time complexity: all operations are O(1).
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   push(2)
 *   push(1)
 *   push(3)
 *   pop()
 *   top()
 *   getMin()
 *
 * - Important key:
 *   Record minimum value of the current stack level.
 *
 * - Related problems:
 *   716
 */
public class lc155MinStack {
/* Solution 1:
    class MinStack {
        Stack<Integer> minStack;
        Stack<Integer> store;

        public MinStack() {
            minStack = new Stack<>();
            store = new Stack<>();
        }

        public void push(int x) {
            int min = minStack.isEmpty()? x: minStack.peek();
            minStack.push(x < min? x: min);
            store.push(x);
        }

        public int pop() {
            minStack.pop();
            return store.pop();
        }

        public int top() {
            return store.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
 */

    class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> store;

        public MinStack() {
            store = new Stack<>();
        }

        public void push(int x) {
            // Careful here, must add `=`.
            if (x <= min) {
                store.push(min);
                min = x;
            }
            store.push(x);
        }

        public void pop() {
            if (store.pop() == min)
                min = store.pop();
        }

        public int top() {
            return store.peek();
        }

        public int getMin() {
            return min;
        }
    }

}
