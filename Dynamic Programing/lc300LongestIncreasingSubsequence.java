/**
 * - LeetCode : https://leetcode.com/problems/longest-increasing-subsequence/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   The brute way is to iterate through the array
 *   and for each element nums[i] we search for the longest increasing subsequence of which the first element is nums[i].
 *   But during the search process when we met an element that is larger than nums[i],
 *   there will be a total new increasing subsequence.
 *   We have to find the longest one among those and add nums[i] to it.
 *
 *   1. Dynamic programing
 *      version 1:
 *      The solution above has many repeat steps.
 *      For example, when we met an element nums[j] which is larger than nums[i],
 *      we have to figure out the longest subsequence which start at nums[j].
 *      So we can store such value in advance.
 *      dp[i] : the size of the longest increasing subsequence which starts at nums[i].
 *      dp[i] = dp[k] + 1. (k > i, nums[k] > nums[i] and dp[k] is larger than all the dp[j], which j > i and nums[j] > nums[i])
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 *      version 2:
 *      In version 1, we have to search for dp[k] one by one. But if dp[k] is in order, we can use binary search.
 *      So we can figure out a new dp equation.
 *      Actually, we can see solution 2 from another aspect.
 *      arr[i] also means: the last element of the increasing subsequence of which size is i.
 *      So we define an array: tails[i]: the last element of the increasing subsequence of which size is i+1(start at 0 this time).
 *      And we keep updating tails[i] to be the smallest element that meets the condition(tails[i] should be last ele in the subsequence of which size is i+1).
 *      To calculate tails: we put nums[j] in the position k (tails[k-1] < nums[j] < tails[k]), tails[k] = nums[j].
 *      Because we only put nums[j] into k, which tails[k-1] is smaller than nums[j], tails[k+1] > original tails[k] > nums[j].
 *      (Or we can say that tails[i] > tails[j] when i > j which means if the size of the subsequence is bigger, than the last element will also be bigger.)
 *      Time complexity: O(nlogn)
 *      Space complexity: O(n)
 *
 *   2. Greedy and Binary search.
 *      We can construct the longest increasing subsequence arr[] by greedy strategy.
 *      When we met an element which is greater than the last element in the array we can just add it to the array.
 *      If not, we can replace the arr[k] with nums[i] (arr[k - 1] < nums[i] < arr[k]) because that
 *      if the subsequence starts at nums[i] it may be longer than starts at arr[k].(nums[i] < arr[k]).
 *      And we can use binary search to find arr[k] because the arr[] is in order.
 *
 *      Although the array we construct may not be the real subsequence but its length is correct.
 *      Time complexity: O(nlogn)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. [1]
 *   2. [6,4,3,1]
 *   3. [4,1,7,8,2,6,9,5,2,3]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */


public class lc300LongestIncreasingSubsequence {
/* Solution 1 version 1:
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];

        int max = 0;
        for (int i = nums.length - 1; i >= 0; i--){
            dp[i] = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
 */

//
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }


/* Solution 2:
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int[] arr = new int[nums.length + 1];
        arr[1] = nums[0];
        int len = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > arr[len]) arr[++len] = nums[i];
            else if (nums[i] < arr[len]) {
                int k = binarySearch(arr, 1, len, nums[i]);
                arr[k] = nums[i];
            }
        }
        return len;
    }

    public int binarySearch(int[] arr, int lo, int hi, int target) {
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] < target) lo = mid + 1;
            else if (arr[mid] > target) hi = mid;
            else return mid;
        }
        return lo;
    }
 */
}
