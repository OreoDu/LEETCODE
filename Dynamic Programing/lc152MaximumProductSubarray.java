/**
 * - LeetCode : https://leetcode.com/problems/maximum-product-subarray/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. Dynamic programing
 *      The product of a subarray can be represented as:
 *      nums[i] * dp[i + 1] (which means the largest product of the subarray which starts at nums[i+1]).
 *      But there is negative value in the array which can make the largest become the smallest and the smallest become the largest.
 *      So we also should record the smallest product of a subarray.
 *      max[i] = max(nums[i], nums[i] * max[i + 1], nums[i] * min[i + 1])
 *      min[i] = min(nums[i], nums[i] * max[i + 1], nums[i] * min[i + 1])
 *      The result will be the largest value in the max[].
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *      version 2:
 *      We only need max and min before instead of the whole array.
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   1. [-4,1,3,4,-2,-1]
 *   2. [1]
 *   3. [-1,-4,-3]
 *   4. [1,2,3]
 *
 * - Important key:
 *
 * - Related problems:
 *   53
 */

public class lc152MaximumProductSubarray {
/* Solution 1 version 1:
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] max = new int[len];
        int[] min = new int[len];
        max[len - 1] = nums[len - 1];
        min[len - 1] = nums[len - 1];

        int res = nums[len - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= 0) {
                max[i] = Math.max(nums[i], nums[i] * max[i+1]);
                min[i] = Math.min(nums[i], nums[i] * min[i+1]);
            } else {
                max[i] = Math.max(nums[i], nums[i] * min[i+1]);
                min[i] = Math.min(nums[i], nums[i] * max[i+1]);
            }
            res = Math.max(res, max[i]);
        }
        return res;
    }
 */

// Solution 1 version 2: memory optimize
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            // if we met the negative value we switch the value of max and min.
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}
