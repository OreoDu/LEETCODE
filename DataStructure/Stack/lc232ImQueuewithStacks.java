import java.util.Stack;

/**
 * - LeetCode : https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * - Important key:
 *   Use two stack to simulate the dequeue and enqueue.
 *
 * - Related problems:
 *   225
 */
public class lc232ImQueuewithStacks {
    class MyQueue {
        private Stack<Integer> enqueue;
        private Stack<Integer> dequeue;
        private int front;

        /** Initialize your data structure here. */
        public MyQueue() {
            enqueue = new Stack<>();
            dequeue = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (enqueue.isEmpty()) front = x;
            enqueue.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (!dequeue.isEmpty()) {
                return dequeue.pop();
            } else {
                if (!enqueue.isEmpty()) {
                    while(!enqueue.isEmpty()) {dequeue.push(enqueue.pop());}
                    return dequeue.pop();
                } else {
                    System.out.println("There is no element in the stack!");
                    return -1;
                }
            }
        }

        /** Get the front element. */
        public int peek() {
            if (!dequeue.isEmpty()) return dequeue.peek();
            else if (!enqueue.isEmpty()) return front;
            else {
                System.out.println("The Queue is empty!");
                return -1;
            }
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return enqueue.isEmpty() && dequeue.isEmpty();
        }
    }
}
