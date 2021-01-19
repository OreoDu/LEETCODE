import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * - LeetCode : https://leetcode.com/problems/max-area-of-island/submissions/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. DFS
 *      do DFS in the grid[][] and count the number of lands in the island.
 *      Time complexity: O(nm)
 *      Space complexity: O(nm)
 *                        or O(1) (version 2)
 *
 *   2. BFS
 *      Time complexity: O(nm)
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   1. grid = {{1,1,0,0},
 *              {1,1,0,0},
 *              {0,0,1,1},
 *              {0,0,1,1}};
 *
 * - Important key:
 *
 * - Related problems:
 *   200, 463
 */

public class lc695MaxAreaOfIsland {
/* Solution 1 version 1:
    public int[][] grid;
    public int[][] index = {{0,0,1,-1},{1,-1,0,0}};
    public boolean[][] visited;
    public int res = 0;

    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        this.grid = grid;
        this.visited = new boolean[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(dfs(i,j), tmpRes);
                }
            }
        }
        return res;
    }

    public int dfs(int x, int y) {
        visited[x][y] = true;

        int tmpRes = 1;
        for (int i = 0; i < 4; i++) {
            int tx = x + index[0][i];
            int ty = y + index[1][i];
            if (tx < 0 || tx >= grid.length || ty < 0 || ty >= grid[0].length || grid[tx][ty] == 0 || visited[tx][ty])
                continue;
            if (grid[tx][ty] == 1) tmpRes = tmpRes + dfs(tx, ty);
        }

        return tmpRes;
    }
 */

/* Solution 1 version 2 from leetcode:
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) {
            return 0;
        }
        // Sinking island idea
        grid[i][j] = 0;
        int num = 1;
        num += dfs(i + 1, j, grid);
        num += dfs(i - 1, j, grid);
        num += dfs(i, j + 1, grid);
        num += dfs(i, j - 1, grid);
        return num;
    }
 */
/* Solution 1 version 3 from leetcode: use stack to implement DFS
    public int maxAreaOfIsland(int[][] grid) {
        Deque<int[]> stack = new LinkedList<>();

        int[][] moveIndexArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                stack.push(new int[]{i, j});
                int currMaxArea = 0;
                while (!stack.isEmpty()) {
                    int[] pop = stack.pop();
                    int currI = pop[0];
                    int currJ = pop[1];
                    if (currI < 0 || currI >= grid.length || currJ < 0 || currJ >= grid[0].length || grid[currI][currJ] == 0) {
                        continue;
                    }
                    currMaxArea++;
                    grid[currI][currJ] = 0;
                    for (int[] moveIndex : moveIndexArray) {
                        stack.push(new int[]{currI + moveIndex[0], currJ + moveIndex[1]});
                    }
                }
                maxArea = Math.max(currMaxArea, maxArea);
            }
        }
        return maxArea;
    }

 */

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int tmp = bfs(i, j, grid);
                    res = Math.max(res, tmp);
                }
            }
        }
        return res;
    }

    public int bfs(int i, int j, int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int[][] index = {{0,0,1,-1},{1,-1,0,0}};

        q.offer(new int[]{i, j});
        grid[i][j] = 0;
        int tmpRes = 1;


        while (!q.isEmpty()) {
            int[] land = q.poll();
            int x = land[0];
            int y = land[1];

            for (int k = 0; k < 4; k++) {
                int tx = x + index[0][k];
                int ty = y + index[1][k];
                if (tx < 0 || tx >= grid.length || ty < 0 || ty >= grid[0].length || grid[tx][ty] == 0) continue;
                if (grid[tx][ty] == 1) {
                    q.offer(new int[]{tx, ty});
                    grid[tx][ty] = 0;
                    tmpRes++;
                }
            }
        }
        return tmpRes;
    }

    public static void main(String[] args) {
        lc695MaxAreaOfIsland s = new lc695MaxAreaOfIsland();
        int[][] grid = {{1,1,0,0},
                        {1,1,0,0},
                        {0,0,1,1},
                        {0,0,1,1}};
        int res = s.maxAreaOfIsland(grid);
        System.out.println(res);
    }

}
