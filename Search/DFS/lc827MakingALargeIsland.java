import java.util.HashSet;

/**
 * - LeetCode : https://leetcode.com/problems/making-a-large-island/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   The brute way is to change every 0 into 1, search its neighbor in the grid
 *   and calculate the area of the island that it belongs to and get the largest area in the end.
 *
 *   1. There are some repeat steps in the solution above.
 *      When we met different 0 and search its neighbor, we may met an island that have already met before.
 *      So we can store area of those original islands in the grid
 *      and mark the island with a number(so that we know some grids are in the same island).
 *      After doing this we can simply add all the islands around the 0 together and get the total area of the new island.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 *   2. Union Find
 *      We can also union all the 1 that are connected in the grid so that they will have the same root in one island.
 *      Then we calculate the area of the island(with the same root) and store them in an array.
 *      In the end, we add all the islands around the 0 together.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 * - Test cases:
 *   1. grid = [[0,0,1,0],
 *              [1,0,1,0],
 *              [1,0,1,1],
 *              [0,0,1,0]]
 *   2. grid = [[1,0,1,0,1],
 *              [0,1,1,0,1],
 *              [1,1,1,0,0],
 *              [1,0,1,1,1],
 *              [0,0,1,1,0]]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc827MakingALargeIsland {
/* Solution 1:
    public int[][] grid;
    public int[][] index = {{0,0,1,-1},{1,-1,0,0}};

    private boolean isValid(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid.length;
    }

    private int dfs(int x, int y, int num) {
        if (!isValid(x,y) || grid[x][y] != 1) return 0;

        grid[x][y] = num;
        int res = 1;
        for (int i = 0; i < 4; i++) {
            res += dfs(x + index[0][i], y + index[1][i],num);
        }
        return res;
    }

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        int n = grid.length;
        int[] area = new int[n * n + 2];

        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area[num] = dfs(i,j,num++);
                }
            }
        }

        int res = 0;
        for (int a : area) res = Math.max(res, a);
        if (res == 0) return 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    HashSet<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int nx = i + index[0][k];
                        int ny = j + index[1][k];
                        if (isValid(nx, ny) && grid[nx][ny] >= 2) set.add(grid[nx][ny]);
                    }

                    int tmpRes = 1;
                    for (int island : set) tmpRes = tmpRes + area[island];
                    res = Math.max(tmpRes, res);
                }
            }
        }
        return res;
    }
 */
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] index = {{0,0,1,-1},{1,-1,0,0}};
        UnionFind u = new UnionFind(n*n);
        int[] area = new int[n*n];

        //union the island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (j + 1 < n && grid[i][j + 1] == 1)
                        u.union(i * n + j, i * n + j + 1);
                    if (i + 1 < n && grid[i + 1][j] == 1)
                        u.union(i * n + j, (i + 1) * n + j);
                }
            }
        }

        // calculated the area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int root = u.root(i * n + j);
                    area[root]++;
                    maxArea = Math.max(maxArea, area[root]);
                }
            }
        }
        if (maxArea == 0) return 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    HashSet<Integer> set = new HashSet<>();
                    int tmpArea = 1;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + index[0][k];
                        int ny = j + index[1][k];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == 0) continue;
                        int root = u.root(nx * n + ny);
                        if (!set.contains(root)) {
                            tmpArea += area[root];
                            set.add(root);
                        }
                    }
                    maxArea = Math.max(maxArea, tmpArea);
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,1,0,1},
                        {0,1,1,0,1},
                        {1,1,1,0,0},
                        {1,0,1,1,1},
                        {0,0,1,1,0}};
        lc827MakingALargeIsland s = new lc827MakingALargeIsland();
        int res = s.largestIsland(grid);
        System.out.println(res);
    }


}
