import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * - LeetCode :
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. BackTrack
 *      We should enumerate every potential number(only use every number for once) in the array nums[i]
 *      to fill every position in the new array.
 *      for example:
 *      _ _ _
 *      1 2 3
 *        3 2
 *      2 1 3
 *        2 3
 *      3 1 2
 *        2 1
 *      If we finish all the positions, we add the new array to the result.
 *      If not, we enumerate every number that was unused before and add it to the new array.
 *
 *      - version 1: we use an array to mark the number that was already visited.
 *      Time complexity: O(n*n!)
 *      Space complexity: O(n)
 *
 *      - version 2:
 *      In order not to use extra space.
 *      We can maintain a array that arr[0] ~ arr[first - 1] is used before and arr[first] ~ arr[len - 1] is unused.
 *        first
 *      0   1  2
 *      _   _  _
 *      1 | 2  3
 *      For example:
 *      when we fill pos 1(= first), 1(pos 0) is the number that is used before, 2,3 are the numbers that are unused.
 *      Now we want fill 3 in the pos 1. We swap 2 and 3 and then move forward(first++).
 *            first
 *      0  1   2
 *      _  _   _
 *      1  3 | 2
 *      Time complexity: O(n*n!)
 *      Space complexity: O(1)
 *
 *
 *
 *
 * - Test cases:
 *   1. [1]
 *   2. [1,2,3]
 *   3. [2,4,1,7,3]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   51,37
 *   permutations: 47,39,40,77,78,90,60,93
 *   Flood Fill: 733, 200, 130, 79
 *   BackTrack in string: 17, 784, 22
 *   Games: 51, 37, 488, 529
 *
 */
public class lc46Permutations {

/* Solution 1 version 1:
    class Solution {
        boolean[] used;
        List<List<Integer>> res = new ArrayList<>();
        int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            int n = nums.length;
            this.nums = nums;
            this.used = new boolean[n];

            List<Integer> tmp = new ArrayList<>();
            backtrack(tmp, 0);

            return res;
        }

        public void backtrack(List<Integer> tmp, int depth) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(tmp));
                return;
            }

            for (int j = 0; j < nums.length; j++) {
                if (used[j]) continue;

                used[j] = true;
                tmp.add(nums[j]);

                backtrack(tmp, depth + 1);

                used[j] = false;
                tmp.remove(depth);
            }
        }
    }

 */
// Solution 1 version 2 from leetcode:
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // fill all the number
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            // fill next position
            backtrack(n, output, res, first + 1);
            // restore
            Collections.swap(output, first, i);
        }
    }

}
