import java.util.LinkedList;
import java.util.Queue;

/**
 * - LeetCode : https://leetcode.com/problems/implement-stack-using-queues/
 *
 * - Related problems:
 *   232
 */
public class lc225ImStackwithQueue {

    class MyStack {
        private Queue<Integer> q;
        private int top;

        /** Initialize your data structure here. */
        public MyStack() {
            q = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q.add(x);
            top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int size = q.size();
            while(size>1) {
                size--;
                if (size == 1) top = q.peek();
                q.add(q.remove());
            }
            return q.remove();
        }

        /** Get the top element. */
        public int top() {
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q.isEmpty();
        }
    }
}
