import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * - LeetCode : https://leetcode.com/problems/pancake-sorting/description/
 * - clarification: (Corner case...)
 *
 *
 * - solutions:
 *   1. If we want to sort an array, we have to move every element to the right position.
 *      So we can use flip() to achieve.
 *      Because flip() can only reverse the first k elements, we can shrink the array
 *      by continuously moving the current largest element to the end of the array.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. [1]
 *   2. [1,2,3]
 *   3. [4,3,2,1]
 *   4. [5,4,2,1,6,3]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc969PancakeSorting {

    static int[] flip(int[] arr, int k) {
        for (int i = 0; i < k / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[k - i - 1];
            arr[k - i - 1] = tmp;
        }
        return arr;
    }

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {

            int max = 0;
            for(int j = 1; j <= i; j++) {
                max = arr[max] < arr[j] ? j : max;
            }

            arr = flip(arr, max + 1);
            arr = flip(arr, i + 1);

            if (max != i) {
                res.add(max + 1);
                res.add(i + 1);
            }
        }

        return res;

    }

    public static void main(String[] args) {
        lc969PancakeSorting s = new lc969PancakeSorting();
        int[] arr = {5,4,2,1,6,3};
        List<Integer> res = s.pancakeSort(arr);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}
