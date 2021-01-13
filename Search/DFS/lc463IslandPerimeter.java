/**
 * - LeetCode :
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. DFS
 *      We do DFS in the map and when we met water or boarder: perimeter + 1.
 *      Time complexity: O(nm)
 *      Space complexity: O(nm)
 *
 * - Test cases:
 *   1. nums = {{0,1,0,0},
 *              {1,1,1,0},
 *              {0,1,0,0},
 *              {1,1,0,0}};
 *   2. nums = {{1}}
 *
 * - Important key:
 *
 * - Related problems:
 *   200, 695
 */

public class lc463IslandPerimeter {
    public int[][] index = {{0,0,1,-1},{1,-1,0,0}};
    public int[][] grid;
    public boolean[][] visited;
    public int perimeter = 0;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        int row = grid.length;
        int col = grid[0].length;
        this.visited = new boolean[row][col];

        outerLoop:
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j);
                    break outerLoop;
                }
            }
        }

        return perimeter;
    }

    public void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tmpX = x + index[0][i];
            int tmpY = y + index[1][i];
            if (tmpX < 0 || tmpX >= grid.length || tmpY < 0 ||
                    tmpY >= grid[0].length || (grid[tmpX][tmpY] == 0 && !visited[tmpX][tmpY])) {
                perimeter++;
                continue;
            }
            if (visited[tmpX][tmpY]) continue;
            if (grid[tmpX][tmpY] == 1) dfs(tmpX, tmpY);
        }
    }

    public static void main(String[] args) {
        lc463IslandPerimeter s = new lc463IslandPerimeter();
        int[][] nums = {{0,1,0,0},
                        {1,1,1,0},
                        {0,1,0,0},
                        {1,1,0,0}};
        s.islandPerimeter(nums);
    }
}
