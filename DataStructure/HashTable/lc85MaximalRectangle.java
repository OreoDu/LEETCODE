/**
 * - LeetCode : https://leetcode.com/problems/maximal-rectangle/
 * - clarification: (Corner case...)
 *   matrix = []
 *   matrix = [["0"]]
 *
 * - solutions:
 *   1. The simplest solution is to enumerate every element in the matrix
 *      and see what kind of rectangle can be formed from it.
 *      Time complexity: O(nm * nm)
 *      Space complexity: O(1)
 *   2.
 *      Time complexity:
 *      Space complexity:

 * - Test cases:
 *   1. matrix = []
 *   2. matrix = [["0"]]
 *   3. matrix = [["1"]]
 *   4. matrix = [["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]]
 *   5.
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   84, 221
 */

public class lc85MaximalRectangle {
/* Solution 1:
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || (matrix.length == 1 && matrix[0].length == 1 &&  matrix[0][0] == '0')) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int curr = 0;
                if (matrix[i][j] == '0') continue;
                int k = j + 1;
                // find the possible biggest length for the rectangle.
                while (k < col && matrix[i][k] == '1') k++;

                int l = i + 1;
                // find the possible biggest width for the rectangle.
                while(l < row && matrix[l][j] == '1') {
                    int m = j + 1;
                    while (m < k && matrix[l][m] == '1') m++;
                    // if we meet '0', we have to shrink the rectangle and update the max value.
                    if (m < k) {
                        curr = (k - j) * (l - i);
                        max = max < curr ? curr : max;
                        k = m;
                    }
                    l++;
                }
                curr = (k - j) * (l - i);
                max = max < curr ? curr : max;
            }
        }
        return max;
    }
 */
    public int maximalRectangle(char[][] matrix) {
        return 1;

    }


    public static void main(String[] args) {
        lc85MaximalRectangle s = new lc85MaximalRectangle();
        char[][] str = {{'1','0','1','0','0'},
                        {'1','0','1','1','1'},
                        {'1','0','1','1','0'},
                        {'1','0','1','1','0'}};
        int res = s.maximalRectangle(str);
        System.out.println(res);
    }
}
