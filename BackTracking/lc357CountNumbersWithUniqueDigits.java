import java.util.HashSet;

/**
 * - LeetCode : https://leetcode.com/problems/count-numbers-with-unique-digits/
 * - clarification:
 *   n = 0
 *
 * - solutions:
 *   n = 0:  10^0 = 1       0
 *   n = 1:  10^1 = 10      0 ~ 9      _
 *   n = 2:  10^2 = 100     0 ~ 99     _ _
 *   n = 1:  10^2 = 1000    0 ~ 999    _ _ _
 *
 *   1. Back tracking
 *      we can enumerate every possible number that can be put into the position and add it up.
 *      Time complexity: O(10^n)
 *      Space complexity: o(10) (an array to store the elements that have been visited)
 *   2. Dynamic programing （permutation！）
 *      The time complexity of backtracking is exponential, so it'll definitely break down when n grows bigger and bigger.
 *      Obviously, it's a permutation problem.
 *      Instead of enumerating every possible number and see whether it meets the condition,
 *      we can just find out the regular pattern that have showed during the counting process
 *      and directly get the final result by the recurrence formula.
 *      we can use dynamic programing to implement it.
 *      Time complexity: O(n)
 *      Space complexity: O(1)

 * - Test cases:
 *   1. 0    >>> 1
 *   2. 1    >>> 10
 *   3. 5    >>> 32491
 *   4. 8    >>> 2345851
 *
 * - Important key:
 *   Backtracking: At the end of the recursion, remember to eliminate the previous impact.
 *   Dynamic programing：recurrence formula is the key and don't get the initial conditions wrong.
 *
 * - Related problems:
 *
 */


public class lc357CountNumbersWithUniqueDigits {
/* Solution 1:
    public static int backTrack(int[] used, int n, int k) {
        if (k > n) return 0;

        int count = 0;
        for (int i = 0; i < 10 ; i ++) {
            if (k == 1 && i == 0) continue;
            if (used[i] == -1) continue;
            used[i] = -1;
            count = count + backTrack(used, n, k + 1) + 1;
            used[i] = 0;
        }
        return count;
    }

    public static int countNumbersWithUniqueDigits(int n) {
        int[] used = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        return backTrack(used, n, 1) + 1;
    }

 */

// Solution 2:
    public static int countNumbersWithUniqueDigits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (n > 0) dp[1] = 9;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * (11 - i);
        }

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = sum + dp[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 8;
        int result = countNumbersWithUniqueDigits(n);
        System.out.println(result);
    }
}