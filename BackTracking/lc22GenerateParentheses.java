import java.util.*;

/**
 * - LeetCode :
 * - clarification: (Corner case...)
 *
 * - solutions: https://leetcode.com/problems/generate-parentheses/
 *   1. DFS / BackTracking
 *      We can use DFS to generate every valid parentheses. (Only add'(' or')' if the sequence is still valid)
 *      If the number of left parentheses is not greater than n, we can put a left parenthesis.
 *      If the number of right parentheses is less than the number of left parentheses, we can put a right parenthesis.
 *      Time complexity:  Nth Cattleya number
 *      Space complexity: O(n) recursion stack.
 *
 *   2. BFS
 *
 *   3. Recursion + Memorize / Dynamic programing
 *      Break the big problem into smaller problem.
 *      generate(n) : Return all possible bracket sequences with n left and n right brackets.
 *      generate(n) = "(" + generate(i) + ")" + generate(n - 1 - i);
 *                    (i: n - 1 ~ 0)
 *      Time complexity: Nth Cattleya number * n
 *      Space complexity: Nth Cattleya number * n
 *
 * - Test cases:
 *   1. n = 1
 *   2. n = 6
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   20, 17
 */

public class lc22GenerateParentheses {
/* Solution 1:
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        String tmp = "(";
        int left = 1;
        int right = 0;
        backTracking(tmp, left, right, n);
        return res;
    }

    public void backTracking(String tmp, int left, int right, int n) {
        if (right == left && left == n) {
            res.add(tmp);
        }

        if (left > right) backTracking(tmp + ")", left, right + 1, n);
        if (left < n) backTracking(tmp + "(", left + 1, right, n);
    }

 */

/* Solution 2:
    class Node {
        private String res;
        private int left;
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }
 */

/* Solution 3 version 1:
    ArrayList[] res;

    public List<String> generate(int i) {
        if (res[i] != null) return res[i];
        ArrayList<String> ans = new ArrayList<>();

        if (i == 0) ans.add("");
        else {
            for (int j = i - 1; j >= 0; j--) {
                for (String mid : generate(j)) {
                    for (String end : generate(i - j - 1)) {
                        ans.add("(" + mid + ")" + end);
                    }
                }
            }
        }

        res[i] = ans;
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        this.res = new ArrayList[n];
        return generate(n);
    }

 */

/* Solution 3 version 2:
    public List<String> generateParenthesisDp(int n) {
        List<String>[] dp = new List[n+1];
        dp[0] = Arrays.asList("");

        for (int p = 1; p <= n; p++) {
            dp[p] = new ArrayList<>();
            for (int q = 0; q < p; q++) {
                for (String a : dp[q]) {
                    for (String b : dp[p-q-1]) {
                        dp[p].add("("+a+")"+b);
                    }
                }
            }
        }
        return dp[n];
    }
 */

}
