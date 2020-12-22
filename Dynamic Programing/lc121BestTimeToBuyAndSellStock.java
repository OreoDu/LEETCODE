/**
 * - LeetCode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * - clarification: (Corner case...)
 *   []
 *   [1]
 *
 * - solutions:
 *   1. Two Pointers
 *      We just have to iterate through the array.
 *      Then keeping record the current lowest price and update the profit(current price  - lowest price).
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *   2. Dynamic programing.
 *      Instead of keeping recording the min to get the current profit,
 *      we can get the profit from the recurrence formula.
 *      We can use dynamic programing to do this.
 *      There are two ways defining dp[i].
 *      dp[i]: the highest profit we can get if we sale the stock at day i;
 *             we can only know the initial condition: dp[0] = 0 so we get dp[i + 1] from dp[i].
 *      or dp[i]: the highest profit we can get if we buy the stock at day i;
 *                we can only know the initial condition: dp[prices.length - 1] = 0 so we get dp[i] from dp[i + 1].
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *                        or O(1) we can just store the value of the day before or later.
 *
 * - Test cases:
 *   1. [1]
 *   2. [7,5,3,2,1]
 *   3. [7,10,1,6,9,3,11]
 *
 * - Important key:
 *   If we can only buy and sale one time, we should focus on the point with lowest price.
 *   DP: the way we define dp[i] and the initial condition.
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 */
public class lc121BestTimeToBuyAndSellStock {
/* Solution1 :
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int currPro = prices[i] - min;
            profit = currPro > profit ? currPro : profit;
            min = min > prices[i] ? prices[i] : min;
        }
        return profit;
    }
 */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int l = prices.length;
        int profit = 0;
        int[] dp = new int[l];

        /*
        dp[l - 1] = 0;
        for (int i = l - 2; i >= 0; i--)
            dp[i] = Math.max(0,dp[i + 1]) + prices[i + 1] - prices[i];
         */

        dp[0] = 0;
        for (int i = 1; i < l; i++)
            dp[i] = Math.max(0,dp[i -1]) + prices[i] - prices[i - 1];

        for (int i = 0; i < l; i++)
            profit = dp[i] > profit ? dp[i] : profit;

        return profit;
    }

    public static void main(String[] args) {
        lc121BestTimeToBuyAndSellStock s = new lc121BestTimeToBuyAndSellStock();
        int[] prices = {7,1,5,3,6,4};
        int res = s.maxProfit(prices);
        System.out.println(res);
    }
}
