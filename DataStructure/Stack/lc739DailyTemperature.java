import java.util.Stack;

/**
 * - LeetCode : https://leetcode.com/problems/daily-temperatures/
 * - clarification:
 *   only one day: [34]
 *
 * - solutions:
 *   1. Simple but violent
 *      Count the days that the temperatures are greater than the current day one by one.
 *      Time complexity: O(n^2)
 *      Space complexity: O(1)
 *   2. Count from last day to the first day and use the information
 *      to skip the days that definitely have the lower temperature.
 *      Time complexity: o(n)
 *      Space complexity: O(n)
 *   3. Stack
 *      We have to see the temperature that behind the current day to decide which day will be warmer.
 *      So in order to avoid go back to the days we have visited, we can temporarily store those days
 *      that haven't find the final result yet in the stack.
 *      Once we find some day that have greater temperature than it we can pop it.
 *      Time complexity: o(n)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. [34]
 *   2. [34,32,35,37,38,31,36,37,34,32]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   496
 */


public class lc739DailyTemperature {
/* Solution 1:
    public int[] dailyTemperatures(int[] T) {
        for (int i = 0; i < T.length - 1; i++) {
            int j = i + 1;
            while(j < T.length) {
                if (T[i] < T[j]) {
                    T[i] = j - i;
                    break;
                }
                j++;
            }
            if (j == T.length) T[i] = 0;
        }
        T[T.length - 1] = 0;
        return T;
    }
 */

/* Solution 2:
    public int[] dailyTemperatures(int[] T) {
        int[] t = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int k = i + 1;
            while (k < T.length - 1 && T[k] <= T[i]) {
                if (t[k] == 0) break;
                k = k + t[k];
            }
            if (T[k] > T[i]) t[i] = k - i;
        }
        return t;
    }
 */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> s = new Stack<>();
        int[] t = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            while(!s.isEmpty() && T[i] > T[s.peek()]) {
                int tmp = s.pop();
                t[tmp] = i - tmp;
            }
            s.push(i);
        }
        return t;
    }
}
