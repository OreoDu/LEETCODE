/**
 * - LeetCode : https://leetcode.com/problems/median-of-two-sorted-arrays/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. The brute way is to merge two sorted array and find the median of the new array.
 *      We use two pointers to merge the array.
 *      Time complexity: O(n+m)
 *      Space complexity: O(n+m)
 *
 *   2. Binary Search
 *      Instead of merging two arrays, we can directly search for the median in the two arrays using binary search.
 *      i = (start + end) / 2
 *      j = (n + m) / 2 - i
 *
 *      when start = 0, end = m - 1. i = m / 2, j = n / 2.
 *            left_part          |        right_part
 *            A[0], ..., A[i-1]  <  A[i], ... , A[m-1]
 *      B[0], B[1], ..., B[j-1]  <  B[j], B[j+1], ..., B[n-1]
 *      if A[i - 1] > B[j], it means that A[i] > B[j] so that A[i] ~A[m - 1] can not be in the left part of the merged array
 *                          and median cannot be in that part too, so we can leave that part.
 *                          Also, B[j - 1] < B[j] < A[i - 1] < A[i] so that B[0] ~ B[j-1] is in the front of the merged array
 *                          and B[j] will never be in the middle(because there are more than (n+m)/2 elements after it), so we can also leave this part.
 *                          so we continue to search the median among the A[0] ~ A[i - 1] and B[j] ~ B[j + 1].
 *                          we update end = i - 1.
 *      if A[i] < B[j - 1], for the similar reason, we leave A[0] ~ A[i - 1] and B[j + 1] ~ B[n - 1] and update start = i + 1
 *      if A[i-1] < B[j] && B[j - 1] < A[i], it means that the median is among A[i - 1], A[i], B[j - 1], B[j]
 *      Time complexity: O(log(n+m))
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   1. []
 *      [2,3]
 *   2. [1]
 *      []
 *   3. [1,3,4,5]
 *      [2,6,7,9,10]
 *
 * - Important key:
 *   The essence of binarry search is to reduce the search field
 *   to half of the original one through condition judgment.
 *   So it is very important to set the conditions  correctly and update the search domain.
 *
 * - Related problems:
 *
 */

public class lc4MedianOfTwoSortedArrays {
/*
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] arr = new int[n + m];

        if (n == 0) arr = nums2;
        if (m == 0) arr = nums1;

        int i = 0, j = 0, k = (n + m) / 2;
        double res = 0.0;

        int l = 0;
        while (i < n && j < m) {
            while(i < n && nums1[i] <= nums2[j]) arr[l++] = nums1[i++];
            if (i == n) {
                while (j < m) arr[l++] = nums2[j++];
                break;
            }

            while(j < m && nums2[j] < nums1[i]) arr[l++] = nums2[j++];
            if (j == m) {
                while (i < n) arr[l++] = nums1[i++];
                break;
            }
        }
        if ((n + m) % 2 == 0) {
            res = arr[k - 1] + arr[k];
            res = res / 2;
        }
        else res = arr[k];

        return res;
    }
 */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        if (n > m) return findMedianSortedArrays(nums2, nums1);

        if (n == 0) return m % 2 != 0 ? nums2[m / 2] : (nums2[m / 2] + nums2[m / 2 - 1]) / 2;
        if (m == 0) return n % 2 != 0 ? nums1[n / 2] : (nums1[n / 2] + nums1[n / 2 - 1]) / 2;

        int half = (n + m) / 2;
        int l = 0;
        int r = n;

        double res = 0.0;
         while(l <= r) {
             int i = (l + r) >> 1;
             int j = half - i;

             int lefti = i > 0 ? nums1[i - 1] : Integer.MIN_VALUE;
             int leftj = j > 0 ? nums2[j - 1] : Integer.MIN_VALUE;
             int righti = i <= n ? nums1[i] : Integer.MAX_VALUE;
             int rightj = j <= m ? nums2[j] : Integer.MAX_VALUE;

             // The median is in the left of the nums1 and right of the nums2.
             if (lefti > rightj) r = i - 1;
             // The median is in the right of the nums1 and left of the nums2.
             else if (leftj > righti) l = i + 1;
             // Both the elements of nums1 and the elements of nums2 in the left is smaller than the right.
             // So the Median must among the lefti,leftj,righti and rightj.
             else {
                 res = (n + m) % 2 == 0 ? (Math.max(lefti, leftj) + Math.min(righti, rightj)) / 2 : Math.min(righti, rightj);
             }
         }

         return res;

    }


}
