'''
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.
>>> nums1 = [1, 3],nums2 = [2]
2.0
'''
### Important key: The essence of binarry search is to reduce the search field 
#                  to half of the original one through condition judgment.
#                  So it is very important to set the conditions  correctly and update the search domain.

# Use binarry search.
class Solution:
    def findMedianSortedArrays(self, nums1, nums2):
        """
              left_part          |        right_part
        A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
        B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
        lefti = A[i - 1] if i > 0 else a smallest number
        etc...
        """
        n = len(nums1)
        m = len(nums2)
        if n > m: 
            return self.findMedianSortedArrays(nums2, nums1) # make sure n < m
        l = 0
        r = n
        half = (n + m) // 2
        while l <= r :
            i = (l + r) // 2 
            j = half - i
            lefti = nums1[i - 1] if i > 0 else float('-inf')
            leftj = nums2[j - 1] if j > 0 else float('-inf')
            righti = nums1[i] if i < n else float('inf')
            rightj = nums2[j] if j < m else float('inf')
            # The median is in the left of the nums1 and right of the nums2.
            if lefti > rightj:
                r = i - 1
            # The median is in the right of the nums1 and left of the nums2.
            elif leftj > righti:
                l = i + 1
            # Both the elements of nums1 and the elements of nums2 in the left is smaller than the right.
            # So the Median must among the lefti,leftj,righti and rightj.
            else:
                return (max(lefti, leftj) + min(righti, rightj)) / 2.0 if (n + m) % 2 == 0 else min(righti, rightj);
