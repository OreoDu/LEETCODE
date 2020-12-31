
/**
 * Shifted Array Search
 * A sorted array of distinct integers shiftArr is shifted to the left by an unknown offset
 * and you don’t have a pre-shifted copy of it.
 * For instance, the sequence 1, 2, 3, 4, 5 becomes 3, 4, 5, 1, 2, after shifting it twice to the left.
 *
 * Given shiftArr and an integer num,
 * implement a function shiftedArrSearch that finds and returns the index of num in shiftArr.
 * If num isn’t in shiftArr, return -1.
 *
 * Assume that the offset can be any value between 0 and arr.length - 1.
 *
 * [time limit] 5000ms
 * [input] array.integer arr
 * [input] integer num
 * [output] integer
 *
 * input:
 *             0  1   2   3  4  5  6
 * shiftArr = [9, 12, 17, 2, 4, 5, 6], num = 2
 *                       |
 * output: 3
 *
 * step 1: search for the breakpoint
 * step 2: search the element in the subpart: 0 ~ pos
 *                                            or pos + 1 ~ len - 1
 *
 *
 *
 * special case : shiftArr = [], num = 2             output = -1
 *               shiftArr = [1], num = 1             output = 0
 *               shiftArr = [2, 4, 5, 6], num = 2    output = 0
 *
 * test case: shiftArr = [9, 12, 17, 2, 4, 5, 6], num = 2
 *            shiftArr = [9, 12, 17, 2, 4, 5, 6, 7, 8], num = 13
 *            shiftArr = [9, 12, 13, 15, 2, 4], num = 2
 *
 * time : O(2logn) ~ O(logn)
 * space: O(1)
*/

class prampShiftedArraySearch {

    static int binarySearch(int[] shiftArr, int lo, int hi, int target) {
        int res = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (shiftArr[mid] < target) lo = mid + 1;
            else if (shiftArr[mid] > target) hi = mid - 1;
            else {
                res = mid;
                break;
            }
        }

        return res;
    }

    static int breakPoint(int[] shiftArr, int lo, int hi) {
        int pos = hi;

        while (lo < hi) {
            int mid  = (lo + hi) / 2;

            if (shiftArr[mid] >= shiftArr[lo]) {
                if (shiftArr[mid] > shiftArr[mid + 1]) {
                    pos = mid;
                    break;
                }else lo = mid + 1;
            } else {
                if (shiftArr[mid] < shiftArr[mid - 1]) {
                    pos = mid - 1;
                    break;
                }else hi = mid - 1;
            }
        }
        return pos;
    }

    static int shiftedArrSearch(int[] shiftArr, int num) {
        if (shiftArr.length == 0) return -1;

        int res = -1;
        int len  = shiftArr.length;

        // find the position that divide the array into two parts
        int pos = breakPoint(shiftArr, 0, len - 1);
        System.out.println(pos);

        // search the element
        if (num > shiftArr[0]) res = binarySearch(shiftArr, 0, pos, num);
        else if (num < shiftArr[0]) res = binarySearch(shiftArr, pos + 1, len - 1, num);
        else res = 0;

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2};
        int num = 2;
        int res = shiftedArrSearch(arr, num);
        System.out.println(res);
    }
}