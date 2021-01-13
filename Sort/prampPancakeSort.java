/**
 * Given an array of integers arr:
 * Write a function flip(arr, k) that reverses the order of the first k elements in the array arr.
 * Write a function pancakeSort(arr) that sorts and returns the input array.
 * You are allowed to use only the function flip you wrote in the first step in order to make changes in the array.
 *
 *
 * input: arr = [1, 5, 4, 3, 2]
 * output: arr = [1, 2, 3, 4, 5]
 *
 * Solution :
 *       get index `max` of largest ele in the subarray in arr[0] ~ arr[end]: (end starts from arr.length - 1)
 *       => max;
 *       flip(max+1) [5,1, 4,3,2]
 *       flip(end+1) [2,3,4,1,5]
 *       end = end - 1;
 *
 * Test case:
 *       arr[0] ~arr[end] max = 2
 *       flip(3) [4,3,2,1,5]
 *       flip(4) [1,2,3,4,5]
 *
 * Time: O(n^2)
 * Space: O(c)
 *
 */

/**
 * feedback:
 * 1. confirm the question
 * 2. explain the thoughts --- talk while thinking and explain the solution with some keywords
 *       (15 min)              try the solution by using case and explain it
 *                             explain the time and space complexity!!!
 * 3. write the code and remember to write down the comments(15 min)
 * 4. test the case (use special cases)
 * 5. the whole process should be in 45min!
 * 6. clam down and avoid small syntax mistakes!!! (write code on the white board and pay attention the special case!!!)
 */


public class prampPancakeSort {

    static int[] flip(int[] arr, int k) {
        for (int i = 0; i < k / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[k - i - 1];
            arr[k - i - 1] = tmp;
        }
        return arr;
    }

    static int[] pancakeSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {

            int max = 0;
            for(int j = 1; j <= i; j++) {
                max = arr[max] < arr[j] ? j : max;
            }

            arr = flip(arr, max + 1);
            arr = flip(arr, i + 1);
        }

        return arr;
    }

    public static void main(String[] args) {
        prampPancakeSort s = new prampPancakeSort();
        int[] arr = {1,2,3};
        arr = s.pancakeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
