/**
 * - LeetCode :https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * - clarification: (Corner case...)
 *    []
 *    [1]
 *
 * - solutions:
 *   1. DFS (Backtracking) (Out of time)
 *      Because we can buy and sale many times, there are so many possible results.
 *       0-th:         cash
 *                 /      　   \
 *      1-th:     cash          hold
 *              /      \       /   \
 *      2-th:  cash   hold  hold  cash
 *      ...
 *      We can get every possible result through DFS.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   2. Dynamic programing
 *      The same with problem 122, we can use recurrence formula to store the profit we can get in i-th day.
 *      However, we have to add one more status because we can only trade at most twice.
 *      So we can define profit[i][k][0]: the i-th day we dont have stock after k trades.
 *      profit[i][k][1]: the i-th day we have stock after k trades.
 *      Time complexity: O(2n)
 *      Space complexity: O(6n) ~ O(n)
 *                        or O(6) ~ O(1), we just need to store the value of last day.

 * - Test cases:
 *   1. [1]
 *   2. [7,5,3,2,1]
 *   3. [7,10,1,6,9,3,11]
 *   4. [3,3,5,0,0,3,1,4]
 *
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 */

public class lc123BestTimeToBuyAndSellStock3 {
/*
    private int res;

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.res = 0;
        dfs(prices, len, 0, 0, 2, 0);
        return this.res;
    }

    private void dfs(int[] prices, int len, int profit, int index, int k, int status) {

        if (index == len || k <= 0) {
            this.res = Math.max(this.res, profit);
            return;
        }

        // stay the same with the day before
        dfs(prices, len, profit, index + 1, k, status);

        // Change the state
        if (status == 0) dfs(prices, len, profit - prices[index], index + 1, k , 1);
        else dfs(prices, len, profit + prices[index], index + 1, k - 1, 0);
    }

 */

/* Solution 2 version 1:
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices.length;

        int[][][] profit = new int[l][3][2];
        for (int i = 0; i < 3; i++) {
            profit[0][i][0] = 0;
            profit[0][i][1] = -prices[0];
        }

        for (int i = 1; i < l; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) profit[i][j][0] = profit[i - 1][j][0];
                else profit[i][j][0] = Math.max(profit[i - 1][j][0], profit[i - 1][j - 1][1] + prices[i]);
                profit[i][j][1] = Math.max(profit[i - 1][j][1], profit[i - 1][j][0] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i < 3; i++) {
            maxProfit = profit[l - 1][i][0] > maxProfit ? profit[l - 1][i][0] : maxProfit;
        }
        return maxProfit;
    }

 */

// Solution 2 version 2:
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices.length;

        int[] hold = new int[3];
        int[] cash = new int[3];
        for (int i = 0; i < 3; i++) {
            cash[i] = 0;
            hold[i] = -prices[0];
        }

        for (int i = 1; i < l; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) cash[j] = cash[j];
                else cash[j] = Math.max(cash[j], hold[j - 1] + prices[i]);
                hold[j] = Math.max(hold[j], cash[j] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i < 3; i++) {
            maxProfit = cash[i] > maxProfit ? cash[i] : maxProfit;
        }

        return maxProfit;
        /*
        int cash1 = 0, cash2 = 0;
        int hold1 = -prices[0], hold2 = -prices[0];


        for (int i = 1; i < l; i++) {
            cash2 = Math.max(cash2, hold2 + prices[i]);
            hold2 = Math.max(hold2, cash1 - prices[i]);
            cash1 = Math.max(cash1, hold1 + prices[i]);
            hold1 = Math.max(hold1, -prices[i]);
        }
        return cash2;
         */
    }

    public static void main(String[] args) {
        lc123BestTimeToBuyAndSellStock3 s = new lc123BestTimeToBuyAndSellStock3();
        int[] prices = {1,2,3,4,5};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}