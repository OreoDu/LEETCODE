import java.util.ArrayDeque;
import java.util.Deque;

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
 *   2. Monotone Stack
 *      Convert the problem into lc84 to solve the largest rectangle in the histogram.
 *      str = {{'1','0','1','0','0'},         => 1, 0, 1, 0, 0
 *             {'1','0','1','1','1'},         => 2, 0, 2, 1, 1
 *             {'1','0','1','1','0'},         => 3, 0, 3, 2, 0
 *             {'1','0','1','1','0'}};        => 4, 0, 4, 3, 0
 *      Time complexity: O(mn)
 *      Space complexity: O(n)

 * - Test cases:
 *   1. matrix = []
 *   2. matrix = [["0"]]
 *   3. matrix = [["1"]]
 *   4. matrix = [["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   84, 221
 */

public class lc85MaximalRectangle {
/* Solution 1 version:
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

/* Solution 1 version2: O(m^2 * n)
    public int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || (matrix.length == 1 && matrix[0].length == 1 &&  matrix[0][0] == '0')) return 0;

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] left = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
            }
        }

        //
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') continue;
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, width * (i - k + 1));
                }
                res = Math.max(area, res);
            }
        }
        return res;
    }
 */

/* Solution 1 version3: O(m^2 * n)
    public int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0) return 0;
        int maxarea = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == '1'){

                    // compute the maximum width and update dp with it
                    dp[i][j] = j == 0 ? 1 : dp[i][j-1] + 1;

                    int width = dp[i][j];

                    // compute the maximum area rectangle with a lower right corner at [i, j]
                    for(int k = i; k >= 0; k--){
                        width = Math.min(width, dp[k][j]);
                        maxarea = Math.max(maxarea, width * (i - k + 1));
                    }
                }
            }
        }
        return maxarea;
    }
 */

// Solution 2:
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || (matrix.length == 1 && matrix[0].length == 1 &&  matrix[0][0] == '0')) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        // The height array of the histogram.
        // Add a "sentinel" with a height of 0 before and after to avoid determining whether the stack is empty.
        int[] heights = new int[n + 2];

        // Regard row 0～i as a histogram, find "the largest rectangle in the histogram"
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //The height of each column is either 0 or the height of the previous row + 1
                heights[j + 1] = matrix[i][j] == '0' ? 0 : 1 + heights[j + 1];
            }
            // solve "the largest rectangle of histogram" by using Monotone stack
            Deque<Integer> stack = new ArrayDeque<>(m);
            stack.addLast(0);
            for (int j = 1; j < heights.length; j++) {
                while (heights[j] < heights[stack.peekLast()]) {
                    int h = heights[stack.pollLast()];
                    int w = j - stack.peekLast() - 1;
                    res = Math.max(res, h * w);
                }
                stack.addLast(j);
            }
        }
        return res;
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
