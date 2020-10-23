import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * - LeetCode : https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * - clarification:
 *   There are duplicate numbers in the two arrays.
 *   nums1 or nums1 is empty.
 *
 * - solutions:
 *   1. We can use hash map to store the numbers and times that them showed up in the nums1[],
 *      then iterate the nums2[] and add the number to the result array
 *      only if it is in the map and the value of the number in the map is greater than 0.
 *      Time complexity: O(n+m)
 *      Space complexity: 0(min(m,n))
 *   2. Sort the two arrays, use two pointers to iterate them
 *      and add the numbers which are equal to the result.
 *      Time complexity: O(nlogn)
 *      Space complexity: 0(min(n,m))
 *
 * - Test cases:
 *   1. nums1 = [1,2,3], nums2 = [];
 *   2. nums1 = [2,3,2], nums2 = [2,3,3,6,7,8];
 *   3. nums1 = [2,4,4,6], nums = [1,2,4,4,6,7];
 *
 * - Important key:
 *   Use hash or two pointers(both sorted) to speed up the search process.
 *
 * - Related problems:
 *   349
 */

public class lc350IntersectionOfArrays2 {
/* Solution 1:
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersect(nums2, nums1);

        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums1) map.put(num,map.getOrDefault(num,0) + 1);

        int[] intersect = new int[nums1.length];
        int index = 0;
        for (int num: nums2) {
            if(map.containsKey(num) && map.get(num) > 0) {
                intersect[index++] = num;
                map.put(num,map.get(num) - 1);
            }
        }
        return Arrays.copyOfRange(intersect, 0, index);
    }
 */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersect(nums2, nums1);

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        int[] intersect = new int[nums1.length];
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersect[index++] = nums1[i++];
                j++;
            } else if (nums1[i] > nums2[j]) j++;
            else i++;
        }
        return Arrays.copyOfRange(intersect,0,index);
    }

}
