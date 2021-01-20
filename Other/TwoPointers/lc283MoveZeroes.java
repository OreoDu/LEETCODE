/**
 * - LeetCode : https://leetcode.com/problems/move-zeroes/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. Move non-zeros elements into the front.
 *      Time complexity: O(n)
 *      Space complexity: O(1)
 *
 *
 * - Test cases:
 *   1. [0,1,0,3,12]
 *   2. []
 *   3. [1,2,3]
 *   4. [0]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc283MoveZeroes {
    public void moveZeroes (int[] nums) {
        if (nums.length == 0) return;
        int first = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (first == -1) first = i;
                else continue;
            } else {
                if (first == -1) continue;
                nums[first] = nums[i];
                first++;
                nums[i] = 0;
            }
        }
    }
}
