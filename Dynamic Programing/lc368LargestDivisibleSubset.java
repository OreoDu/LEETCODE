import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * - LeetCode : https://leetcode.com/problems/largest-divisible-subset/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   The brute force way is to enumerate every subset and determine whether the subset meets the condition.
 *   And get the largest subset among those.
 *   The way to enumerate: add new elements continuously.
 *   The way to select the subset: whether the new element is a divisor of or divided by the elements already in the subset.
 *   The way to find the largest: start from the length 2 to nums.length.
 *   But by such solution, the time complexity will be O(n^3).
 *
 *   1. Dynamic Programing
 *      One way to improve the above solution is to optimize the process of selecting the subset and the way we add the elements.
 *      Suppose we have a subset that already meet the condition and it can be represent like this:
 *      [ax,x,bax] , the x is the smallest element.
 *      if we only add new element that is smaller than x, it should be a divisor of x.
 *      if we only add new element that is greater than bax, it should be divided by bax.
 *
 *      So in one way we can sort the nums first and add new element that is greater than the largest element in the subset.
 *      In this case: [x, ax, bax], we can only add `cbax` into the subset.
 *      or we can say if an element x is greater than all the elements in the subset
 *      it can only be put into the subset of which the largest element in it is a divisor of x,
 *      and during the process we select the biggest subset to add the x.
 *
 *      Based on above, we can use dynamic programing to implement this.
 *
 *      Version 1:
 *      res[i] is represented as: the biggest subset of which largest element is nums[i].
 *      then res[i+1] can be calculate like this: res[m] + nums[i+1]
 *      (m: 0 ~ i, nums[m] is a divisor of nums[i+1], it is the biggest subset among res[0]~res[i] which meet the condition)
 *      The result is the biggest subset among the res.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 *      Version 2:
 *      Instead of storing the whole subset, we can just store the size of the subset.
 *      dp[i]: the length of the biggest subset of which largest element is nums[i].
 *      In the end, when we got the largest subset that meets the condition, we can construct the subset through the information we got.
 *      if nums[j] is a divisor of nums[maxSubsetIndex] and dp[j] = dp[maxSubsetIndex] - 1,
 *      then we can be sure that it is one of the elements in that subset.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 *      Version 3: Memorized Recursion
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 *
 *   2. Hasse diagram --- Union Find
 *      https://en.wikipedia.org/wiki/Hasse_diagram
 *      [1,2,3,4,6,8,12,24]
 *                  1
 *                /   \
 *               2     3
 *              |   \  |
 *              4     6
 *              |   \  |
 *              8     12
 *                \  |
 *                 24
 *
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. [1]
 *   2. [1,2,3]
 *   3. [1,2,4,8,24]
 *   4. [1,3,5,7]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   300
 */

public class lc368LargestDivisibleSubset {
/* Solution 1 version 1:
    public List<Integer> largestDivisibleSubset (int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            res.add(new ArrayList<>());
        }

        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            List<Integer> max = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && res.get(j).size() > max.size()) max = res.get(j);
            }

            res.get(i).addAll(max);
            res.get(i).add(nums[i]);
        }

        List<Integer> result = new ArrayList<>();
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            if (res.get(i).size() > maxLen) {
                result = res.get(i);
                maxLen = res.get(i).size();
            }
        }
        return result;
    }
 */
/* Solution 1 version 2:
    public List<Integer> largestDivisibleSubset (int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        Arrays.sort(nums);

        int maxSubsetSize = -1, maxSubsetIndex = -1;
        for (int i = 0; i < len; i++) {
            int maxSize = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && maxSize < dp[j]) maxSize = dp[j];
            }

            dp[i] = maxSize + 1;

            if (dp[i] > maxSubsetSize) {
                maxSubsetSize = dp[i];
                maxSubsetIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        int currSubsetSize = maxSubsetSize;
        int currTail = maxSubsetIndex;
        result.add(nums[currTail]);

        for (int i = maxSubsetIndex - 1; i >= 0 ; i--) {
            if (nums[currTail] % nums[i] == 0 && dp[i] == dp[currTail] - 1) {
                result.add(nums[i]);
                currTail = i;
                currSubsetSize = dp[i];
            }
        }
        return result;
    }
 */
/* Solution 1 version 3:
    public HashMap<Integer, List<Integer>> subsets;
    public int[] nums;

    public List<Integer> largestDivisibleSubset (int[] nums) {
        int len = nums.length;
        this.subsets  = new HashMap<>();

        Arrays.sort(nums);
        this.nums = nums;

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            List<Integer> sub = getSubset(i);
            if (sub.size() > result.size()) result = sub;
        }

        return result;
    }

    public List<Integer> getSubset(int i) {
        if (subsets.containsKey(i)) return subsets.get(i);

        List<Integer> maxSubset = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] == 0) {
                List<Integer> sub = getSubset(j);
                if (sub.size() > maxSubset.size()) maxSubset = sub;
            }
        }

        List<Integer> newSubset = new ArrayList<>();
        newSubset.addAll(maxSubset);
        newSubset.add(nums[i]);
        subsets.put(i, newSubset);

        return maxSubset;
    }

 */
// Solution:
    public List<Integer> largestDivisibleSubset (int[] nums) {
        int len = nums.length;
        int[] father = new int[len];
        int[] level = new int[len];

        Arrays.sort(nums);

        for (int i = 0; i < len; i++) father[i] = i;
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && level[i] < level[j] + 1) {
                    father[i] = j;
                    level[i] = level[j] + 1;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            maxIndex = level[i] > level[maxIndex] ? i : maxIndex;
        }

        List<Integer> result = new ArrayList<>();
        result.add(nums[maxIndex]);

        while(maxIndex != father[maxIndex]) {
            maxIndex = father[maxIndex];
            result.add(nums[maxIndex]);
        }
        return result;
    }


    public static void main(String[] args) {
        lc368LargestDivisibleSubset s = new lc368LargestDivisibleSubset();
        int[] nums = {1,2,3,4,6};
        List<Integer> result = s.largestDivisibleSubset(nums);
        for (int num : result) System.out.println(num);
    }

}
