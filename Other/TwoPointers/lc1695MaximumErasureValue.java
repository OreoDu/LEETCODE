import java.util.HashMap;
import java.util.Map;
/**
 * - LeetCode :
 * - clarification: (Corner case...)
 *   [3]
 *
 * - solutions:
 *   1. Two Pointers， Dynamic programing
 *      This problem is similar with lc3, they both have to find the subarray that have unique elements.
 *      But the result lc3 has to be the longest length and the result of this problem is to get the biggest sum.
 *      The basic solution is:
 *      We use two pointers to mark the start and end of the subarray.
 *      We start at the front of the array and add new elements one by one,
 *      and use map to store both the elements in the subarray and their indices.
 *
 *      when we meet a element that was in the map and its index in the map is greater than the start
 *      which means it has already showed in the subarray and we have to update the result and start index.
 *
 *      we get the new sum of the subarray and compare it with the old one,
 *      then move the start pointer to the position behind the duplicated element in the subarray.
 *
 *      In order to get the sum of subarray in O(1) time, we can use dp[i] - dp[start] to compute the sum of the subarray (start ~ i-1)
 *      dp[i] : the sum of subarray from the 0th to the i-1 the elements.
 *
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *    2. Use array instead of map.
 *
 * - Test cases:
 *   1. [3]
 *   2. [4,2,4,5,6]
 *
 * - Important key:
 *   The way to represent the subarray. -- two pointers
 *   The way to determine whether a subarray meets the condition.  -- store the elements in the map see whether it has duplicated elements.
 *   The way to get the new array.  -- store the index in the map to get the new position of the new subarray.
 *   The way to get and update the ideal result.  -- use prefix sum to store the sum in advance.
 *
 * - Related problems:
 *   3
 */

public class lc1695MaximumErasureValue {
/* Use the template of Sliding window.
    public int maximumUniqueSubarray(int[] nums) {
        //1.Make the window
        Map<Integer, Integer> window = new HashMap<>();
        int left = 0, right = 0;

        int res = Integer.MIN_VALUE, cur = 0;
        while (right < nums.length) {
            //2.Right expansion
            int k = nums[right++];
            window.put(k, window.getOrDefault(k, 0) + 1);
            cur += k;

            //3.Determine whether to shrink on the left
            while (window.get(k) > 1){
                int d = nums[left++];
                window.put(d, window.get(d) - 1);
                cur -= d;
            }
            res = Math.max(res, cur);
        }
        return res;
    }
 */

/*Solution 1:
    public static int maximumUniqueSubarray(int[] arr) {
        if (arr.length == 1) return arr[0];

        int start = 0;
        int res = Integer.MIN_VALUE;

        int[] dp = new int[arr.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= arr.length; i ++) {
            dp[i] = dp[i - 1] + arr[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();

        int tmp =  0;
        for(int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i]) && map.get(arr[i]) >= start) {
                tmp = dp[i] - dp[start];
                res = tmp > res ? tmp : res;
                start = map.get(arr[i]) + 1;
            }
            map.put(arr[i], i);
        }
        tmp = dp[arr.length] - dp[start];
        return Math.max(res, tmp);
    }
 */

    public static int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int left = 0;
        int right = 0;
        int[] vis = new int[10001];

        int max = Integer.MIN_VALUE;
        int num;

        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= nums.length; i ++) {
            dp[i] = dp[i - 1] + nums[i - 1];
        }

        while (right < len) {
            int now = nums[right];
            if (vis[now] > left) {
                num = dp[right] - dp[left];
                max = Math.max(num, max);
                left = vis[now];
            }
            // store index + 1 to avoid 0;
            vis[now] = right + 1;
            right++;
        }
        num = dp[nums.length] - dp[left];
        return Math.max(num, max);
    }

    public static void main(String[] args) {
        int[] arr = {4,2,4,5,6};
        int res = maximumUniqueSubarray(arr);
        System.out.println(res);
    }
}
