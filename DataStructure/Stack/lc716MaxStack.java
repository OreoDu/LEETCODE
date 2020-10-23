import java.util.Stack;

/**
 * - LeetCode : https://leetcode-cn.com/problems/max-stack/
 * - solutions:
 *   1. Use helper stack to store the maximum value.
 *      Time complexity: all operations are O(1).
 *      Space complexity: O(n) helper stack
 *
 *   2.
 *      Time complexity: all operations are O(1).
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   push(2);
 *   push(5);
 *   push(3);
 *   push(4);
 *   push(1);
 *   top();
 *   popMax();
 *   peekMax();
 *   popMin();
 *   peekMin();
 *
 * - Important key:
 *   Record minimum value of the current stack level.
 *
 * - Related problems:
 *   155
 */

public class lc716MaxStack {

/* Solution 1:
    class MaxStack {
        Stack<Integer> maxStack;
        Stack<Integer> store;

        public MaxStack() {
            maxStack = new Stack<>();
            store = new Stack<>();
        }

        public void push(int x) {
            int max = maxStack.isEmpty()? x: maxStack.peek();
            maxStack.push(x > max? x: max);
            store.push(x);
        }

        public int pop() {
            maxStack.pop();
            return store.pop();
        }

        public int top() {
            return store.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = maxStack.peek();
            Stack<Integer> buffer = new Stack<>();
            while(top() != max) buffer.push(pop());
            pop();
            while(!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }
 */


}
