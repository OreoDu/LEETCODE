import java.util.ArrayList;
import java.util.List;

/**
 * - LeetCode: https://leetcode.com/problems/triangle/
 * - clarification: (Corner case...)
 *   []
 *   [[4]]
 *
 * - solutions:
 *   1. Dynamic Programing
 *      Version 1:
 *      We can define dp[i][j]: Represents the minimum path sum from the top of the triangle to the position (i, j).
 *      dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1]) + tri[i][j]
 *                 when j = 0: dp[i - 1][j] + tri[i][j]
 *                 when j = i: dp[i - 1][j - 1] + tri[i][j]
 *      initial condition: dp[0][0] = tri[0][0]
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *                        or O(n) (version 2: only store the value of the previous layer)
 *                        or O(1) (version 3：change the value in the triangle directly)
 *      Version 4:
 *      Instead of get the sum form the top to the bottom, we can also get the sum form the bottom to the top.
 *      dp[i][j]: Represents the minimum path sum from the bottom of the triangle to the position (i, j)
 *      dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + tri[i][j]
 *      initial condition: dp[len - 1][i] = tri[len - 1][i]
 *      we can optimize the space and only store the value of the layer below.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 *
 *   2. DFS
 *      Use DFS to search for all kind of results.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *
 *   1.   [
 *        [2],
 *       [3,4],
 *      [6,5,7],
 *     [4,1,8,3]
 *   ]
 *   2. [[1]]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */
public class lc120Triangle {
/* Soultion 1 version1:
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        if (triangle.size() == 1 && triangle.get(0).size() == 1) return triangle.get(0).get(0);

        int len = triangle.size();
        ArrayList<Integer>[] dp = new ArrayList[len];
        for (int i = 0; i < len ; i++) {
            dp[i] = new ArrayList();
        }
        dp[0].add(triangle.get(0).get(0));

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                int num = triangle.get(i).get(j);
                int sum1 = j - 1 >= 0 ? dp[i - 1].get(j - 1): Integer.MAX_VALUE;
                int sum2 = j < i ? dp[i - 1].get(j): Integer.MAX_VALUE;
                int sum = Math.min(sum1, sum2) + num;
                dp[i].add(sum);
            }
        }

        int min = dp[len - 1].get(0);
        for (int i = 1; i < len; i++) {
            min = dp[len - 1].get(i) < min ? dp[len - 1].get(i) : min;
        }
        return min;
    }
 */

/* Solution 1 version 2:
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        if (triangle.size() == 1 && triangle.get(0).size() == 1) return triangle.get(0).get(0);

        int len = triangle.size();
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                dp[i] = triangle.get(0).get(0);
                continue;
            }
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                int num = triangle.get(i).get(j);
                int sum1 = j - 1 >= 0 ? dp[j - 1]: Integer.MAX_VALUE;
                int sum2 = j < i ? dp[j]: Integer.MAX_VALUE;
                dp[j] = Math.min(sum1, sum2) + num;
            }
        }

        int min = dp[0];
        for (int i = 1; i < len; i++) {
            min = dp[i] < min ? dp[i] : min;
        }
        return min;
    }

 */
/* Solution 1 version 3:
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        if (triangle.size() == 1 && triangle.get(0).size() == 1) return triangle.get(0).get(0);

        int len = triangle.size();
        for (int i = 1; i < len; i++) {
            int num0 = triangle.get(i).get(0);
            triangle.get(i).set(0, triangle.get(i-1).get(0) + num0);
            for (int j = 1; j < i; j++) {
                int numj = triangle.get(i).get(j);
                triangle.get(i).set(j, Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j - 1)) + numj);
            }
            int numi = triangle.get(i).get(i);
            triangle.get(i).set(i, triangle.get(i-1).get(i - 1) + numi);
        }

        int min = triangle.get(len - 1).get(0);
        for (int i = 1; i < len; i++) {
            min = triangle.get(len - 1).get(i) < min ? triangle.get(len - 1).get(i) : min;
        }
        return min;
    }
 */
/* Solution 1 version 4:
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        if (triangle.size() == 1 && triangle.get(0).size() == 1) return triangle.get(0).get(0);

        int len = triangle.size();
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            dp[i] = triangle.get(len - 1).get(i);
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
 */

/* Solution 2: (Out of time)
    public int dfs (int i, int j, List<List<Integer>> triangle, int sum) {
        if (i == triangle.size() - 1) {
            sum += triangle.get(i).get(j);
            return sum;
        }

        sum += triangle.get(i).get(j);

        int sum1 = dfs(i + 1, j, triangle, sum);
        int sum2 = dfs(i + 1, j + 1, triangle, sum);

        return Math.min(sum1, sum2);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        if (triangle.size() == 1 && triangle.get(0).size() == 1) return triangle.get(0).get(0);
        return dfs(0, 0, triangle, 0);
    }

 */

}
