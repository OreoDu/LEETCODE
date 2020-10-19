import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * - LeetCode : https://leetcode.com/problems/intersection-of-two-arrays/
 * - clarification:
 *   There are duplicate numbers in the two arrays.
 *   nums1 or nums1 is empty.
 *
 * - solutions:
 *   1. We can use one set to store the numbers that showed up in the nums1[],
 *      then iterate the nums2[] and use another set to store the numbers that is already in the first set.
 *      Time complexity: O(n+m)
 *      Space complexity: 0(min(n,m))
 *   2. Sort the two arrays, use two pointers to iterate them
 *      and add the numbers which are equal to the result.
 *      Time complexity: O(nlogn)
 *      Space complexity: 0(min(n,m))
 *   3. Sort the nums2[] and use binary search to see whether the numbers in nums1[] is in the nums2[].
 *      Time complexity: O(nlogn)
 *      Space complexity: 0(min(n,m))

 * - Test cases:
 *   1. nums1 = [1,2,3], nums2 = [];
 *   2. nums1 = [2,3,2], nums2 = [2,3,3,6,7,8];
 *   3. nums1 = [2,4,4,6], nums = [1,2,4,4,6,7];
 *
 * - Important key:
 *   Use set to remove the repetitive elements.
 *   Use hash or two pointers(both sorted) or binary search(one sorted) to speed up the search process.
 *
 * - Related problems:
 *   350
 */

public class lc349IntersectionOfArrays1 {
/* Solution 1:
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];

        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int num: nums1) set1.add(num);
        for(int num: nums2) {
            if (set1.contains(num)) set2.add(num);
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for(int s:set2) intersection[index++] = s;

        return Arrays.copyOfRange(intersection, 0, index);
    }
 */

/* Solution 2:
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersection(nums2, nums1);

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        Set<Integer> set = new HashSet<>();
        while(i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i++]);
                j++;
            } else if(nums1[i] > nums2[j]) j++;
            else i++;
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for(int s:set) intersection[index++] = s;

        return Arrays.copyOfRange(intersection, 0, index);
    }
 */

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) return intersection(nums2, nums1);

        Arrays.sort(nums2);

        Set<Integer> set = new HashSet<>();
        for(int num: nums1) {
            int left = 0, right = nums2.length - 1;
            while (right >= left) {
                int mid = (right + left) / 2;
                if (nums2[mid] > num) right = mid - 1;
                else if (nums2[mid] < num) left = mid + 1;
                else {
                    set.add(num);
                    break;
                }
            }
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for(int s:set) intersection[index++] = s;

        return Arrays.copyOfRange(intersection, 0, index);
    }

}
