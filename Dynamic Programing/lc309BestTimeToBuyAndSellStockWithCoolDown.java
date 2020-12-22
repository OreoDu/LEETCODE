/**
 * - LeetCode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * - clarification: (Corner case...)
 *   []
 *   [1]
 *
 * - solutions:
 *   1. Dynamic programing
 *      The same with problem 122.
 *      But if we want to buy stock at i-th day, we have to make sure that the old stock has been sold before i-2 th day.
 *      Time complexity: O(n)
 *      Space complexity: O(n)

 * - Test cases:
 *   1. [1]
 *   2. [7,5,3,2,1]
 *   3. [7,10,1,6,9,3,11]
 *   4. [1,2,3,0,2]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 */

public class lc309BestTimeToBuyAndSellStockWithCoolDown {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices.length;

        int[][] dp = new int[l][2];
        dp[0][0] = 0;
        dp[0][1] = - prices[0];
        dp[1][0] = Math.max(0, - prices[0] + prices[1]);
        dp[1][1] = Math.max(- prices[1], - prices[0]);

        for (int i = 2; i < l ; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1]);
        }
        return dp[l - 1][0];
    }

    public static void main(String[] args) {
        lc309BestTimeToBuyAndSellStockWithCoolDown s = new lc309BestTimeToBuyAndSellStockWithCoolDown();
        int[] prices = {1,2,3,4,5};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}
