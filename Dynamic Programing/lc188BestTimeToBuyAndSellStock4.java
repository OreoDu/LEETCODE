/**
 * - LeetCode : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * - clarification: (Corner case...)
 *   k = 0
 *   []
 *   [1]
 *
 * - solutions:
 *    1. Dynamic programing
 *      The same with problem 122, we can use recurrence formula to store the profit we can get in i-th day.
 *      However, we have to add one more status because we can only trade at most k times.
 *      So we can define profit[i][j][0]: the i-th day we dont have stock after j trades.
 *      profit[i][j][1]: the i-th day we have stock after j trades.
 *      Time complexity: O(kn)
 *      Space complexity: O(2kn) ~ O(n)
 *                        or O(2k) ~ O(1), we just need to store the value of last day.

 * - Test cases:
 *   1. k = 2, prices = [3,2,6,5,0,3]
 *   2. k = 2, prices = [2,4,1]
 *   3. k = 3, prices = [7,5,3,2,1]
 *   4. k = 0, prices = [1,2];
 *   5, k = 1, prices = [1]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   121, 122, 123, 309, 188, 714
 */
public class lc188BestTimeToBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0;
        int l = prices.length;

        int[][][] profit = new int[l][k + 1][2];
        for (int i = 0; i < k + 1; i++) {
            profit[0][i][0] = 0;
            profit[0][i][1] = -prices[0];
        }

        for (int i = 1; i < l; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (j == 0) profit[i][j][0] = profit[i - 1][j][0];
                else profit[i][j][0] = Math.max(profit[i - 1][j][0], profit[i - 1][j - 1][1] + prices[i]);
                profit[i][j][1] = Math.max(profit[i - 1][j][1], profit[i - 1][j][0] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i < k + 1; i++) {
            maxProfit = profit[l - 1][i][0] > maxProfit ? profit[l - 1][i][0] : maxProfit;
        }
        return maxProfit;
    }

/*
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0;
        int l = prices.length;

        int[] hold = new int[k + 1];
        int[] cash = new int[k + 1];
        for (int i = 0; i < k + 1; i++) {
            cash[i] = 0;
            hold[i] = -prices[0];
        }

        for (int i = 1; i < l; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (j == 0) cash[j] = cash[j];
                else cash[j] = Math.max(cash[j], hold[j - 1] + prices[i]);
                hold[j] = Math.max(hold[j], cash[j] - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i < k + 1; i++) {
            maxProfit = cash[i] > maxProfit ? cash[i] : maxProfit;
        }

        return maxProfit;
    }

 */

    public static void main(String[] args) {
        lc188BestTimeToBuyAndSellStock4 s = new lc188BestTimeToBuyAndSellStock4();
        int[] prices = {1,2,3,4,5};
        int k = 2;
        int res = s.maxProfit(k, prices);
        System.out.println(res);
    }
}
