/**
 * - LeetCode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * - clarification: (Corner case...)
 *   [1]
 *
 * - solutions:
 *   1. DFS (Backtracking) (Out of time)
 *      Because we can buy and sale many times, there are so many possible results.
 *       0-th:         cash
 *                 /      　   ｜
 *      1-th:    cash          hold
 *              /    ｜        /     |
 *      2-th:  cash    hold  hold  cash
 *      ...
 *      We can get every possible result through DFS.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   2. Greedy algorithm
 *      Because there is no handling fee, we can use a local optimal strategy,
 *      as long as it is in the rising interval, we will trade.
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 *   3. Dynamic programing
 *       0-th:         cash
 *                 /      　   ｜
 *      1-th:    cash          hold
 *              /    ｜        /     |
 *      2-th:  cash    hold  hold  cash
 *      ...
 *      Because there will be only two states when one day ends, cash or hold.
 *      As long as the cash or hold status is determined on the day, it will have no effect on subsequent transactions.
 *      We can use dp[i][] to store the profit we can get in i-th day by the recurrence formula.
 *      Therefore, we can reduce the number of states and choose the branch with the greatest benefit both in the cash state and the hold state
 *      as the possible two states of the day.
 *
 *      So we can define the dp[i][0]: the i-th day we dont have stock for now.
 *      dp[i][1]: the i-th day we have stocks.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *                        or O(1), we just need to store the value of last day.
 *
 * - Test cases:
 *   1. [1]
 *   2. [7,6,4,3,1]
 *   3. [1,2,3,4,5]
 *   4. [7,1,5,3,6,4]
 *
 * - Important key:
 *   If we can buy and sale many times, we should focus on the interval of which price is rising.
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 *
 */

public class lc122BestTimeToBuyAndSellStock2 {
/* Solution 1:
    private int res;

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.res = 0;
        dfs(prices, 0, len, 0, res);
        return this.res;
    }

    private void dfs(int[] prices, int index, int len, int status, int profit) {

        if (index == len) {
            this.res = Math.max(this.res, profit);
            return;
        }
        // stay the same with the day before
        dfs(prices, index + 1, len, status, profit);

        // Change the state
        if (status == 0) dfs(prices, index + 1, len, 1, profit - prices[index]);
        else dfs(prices, index + 1, len, 0, profit + prices[index]);
    }
*/

/* Solution 2:
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int profit = 0;

        for (int i = 1; i < prices.length; i++)
            if (prices[i - 1] < prices[i]) profit = profit + prices[i] - prices[i -1];

        return profit;
    }
*/

// Solution 3:
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices.length;

        int[][] dp = new int[l][2];
        dp[0][0] = 0;
        dp[0][1] = - prices[0];

        for (int i = 1; i < l ; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[l - 1][0];

        /* Optimized the space complexity.
        int preCash = 0, preHold = -prices[0];
        for (int i = 1; i < l; i++) {
            int cash = Math.max(preCash, preHold + prices[i]);
            int hold = Math.max(preCash - prices[i], preHold);
            preCash = cash;
            preHold = hold;
        }
        return preCash;
         */
    }

    public static void main(String[] args) {
        lc122BestTimeToBuyAndSellStock2 s = new lc122BestTimeToBuyAndSellStock2();
        int[] prices = {7,1,5,3,6,4};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}
