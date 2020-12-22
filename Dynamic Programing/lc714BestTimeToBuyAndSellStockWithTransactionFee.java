/**
 * - LeetCode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1.
 *      Time complexity:
 *      Space complexity:
 *   2.
 *      Time complexity:
 *      Space complexity:

 * - Test cases:
 *   1. prices = [1, 3, 2, 8, 4, 9], fee = 2
 *   2. prices = [1, 2, 3, 4, 5, 6], fee = 2
 *   3. prices = [7, 5, 3, 2, 1], fee = 2
 *   4. prices = [1], fee = 2
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 */

public class lc714BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        if (prices.length < 2) return 0;
        int l = prices.length;

        int[][] dp = new int[l][2];
        dp[0][0] = 0;
        dp[0][1] = - prices[0];

        for (int i = 1; i < l ; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[l - 1][0];

    }

    public static void main(String[] args) {
        lc714BestTimeToBuyAndSellStockWithTransactionFee s = new lc714BestTimeToBuyAndSellStockWithTransactionFee();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int res = s.maxProfit(prices, fee);
        System.out.println(res);
    }
}
