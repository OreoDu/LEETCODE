import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * - LeetCode : https://leetcode.com/problems/as-far-from-land-as-possible/submissions/
 * - clarification: (Corner case...)
 *   [[1]]
 *   [[0]]
 *
 * - solutions:
 *   Multi-source shortest path
 *   The main problem is to get the longest distance among the shortest distance of the points between two different sets.
 *   1. BFS
 *      - version 1:
 *      we can do BFS to traverse through every water node and find the nearest land of it and get the largest value.
 *      Time complexity: O(n^4)
 *      in the worst case: we have to traverse every position (n^2) and in each BFS we have to visit n^2 - 1 position.
 *      Space complexity: O(n^2)
 *
 *      - version 2: Multi-source BFS
 *      If we do BFS in every water node, we only stop till we met land and the node we have been visited during current BFS.
 *      But actually, if we do BFS starting at all land nodes, we only need to do BFS once.
 *
 *      Every step of BFS we got the shortest distance between the starting land to the current water.
 *      If a water node is visited before it means that it has more shorter or the same distance between other land node instead of the current starting land.
 *      If we met land during the process it means that we can not go any further in current route.
 *
 *      Suppose we have a fake source node of which children are all the land nodes and we start to do BFS at that fake node.
 *      The answer will be the deepest level of the BFS.
 *      Time complexity: O(n^2) cuz we only do BFS once.
 *      Space complexity: O(n^2)
 *
 *   2. Dijkstra
 *      https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 *      Dijkstra is used to find the shortest path of nodes in the graph between a certain node.
 *      Suppose we have a fake node, its children is all the land. The distance between this node and land is 0.
 *      And we find the shortest distance between this node and all the water.
 *      By doing this we can get longest distance among those shortest ones.
 *
 *      And we use priority queue to store the temporary distance and find the smallest everytime.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 *   3. SPFA
 *      https://en.wikipedia.org/wiki/Shortest_Path_Faster_Algorithm
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 *   4. Dynamic Programing
 *      For each ocean area (x, y)(x, y), the path from the land area closest to it to it
 *      is either from the top or the left, or from the right or the bottom.
 *      Time complexity: O(n^2)
 *      Space complexity: O(n^2)
 *
 * - Test cases:
 *   1. [[1,0,1],
 *       [0,0,0],
 *       [1,0,1]]  >>> 2
 *   2. [[1,0,0,0],
 *       [1,0,1,0],
 *       [0,0,0,0]
 *       [0,0,0,0]]  >>>
 *   3. [[1]]
 *   4. [[0]]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   317
 */

class lc1162AsFarFromLandAsPossible {

    public int[][] grid;
    public int n;
    int[][] index = {{1, -1, 0, 0},{0, 0, -1, 1}};

/* Solution 1 version 1:
    public int maxDistance(int[][] grid) {
        int ans = -1;
        this.grid = grid;
        this.n = grid.length;
        if (n == 1) return -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)
                    ans = Math.max(ans, findNearestLand(i,j));
            }
        }
        return ans;
    }

    public int findNearestLand(int i, int j) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{i, j, 0});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            for (int k = 0; k < 4; k++) {
                int newX = x + index[0][k];
                int newY = y + index[1][k];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n || visited[newX][newY]) continue;
                if (grid[newX][newY] == 1) return tmp[2] + 1;
                else {
                    visited[newX][newY] = true;
                    q.offer(new int[]{newX, newY, tmp[2] + 1});
                }
            }
        }
        return -1;
    }

 */

/* Solution 1 version 2: BFS
    public int maxDistance(int[][] grid) {
        int ans = -1;
        this.grid = grid;
        this.n = grid.length;
        if (n == 1) return -1;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        // find all the land and put it into the queue.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) q.offer(new int[]{i, j, 0});
            }
        }

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            if (grid[x][y] == 0) ans = tmp[2];

            for (int i = 0; i < 4; i++) {
                int newX = x + index[0][i];
                int newY = y + index[1][i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n || visited[newX][newY] || grid[newX][newY] == 1) continue;
                q.offer(new int[]{newX, newY, tmp[2] + 1});
                visited[newX][newY] = true;
            }
        }
        return ans;
    }
 */
/* Solution 1 version 2: BFS from leetcode
        public int maxDistance(int[][] grid) {
        final int INF = 1000000;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = grid.length;
        int[][] d = new int[n][n];
        Queue<int[]> queue = new LinkedList<int[]>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] f = queue.poll();
            for (int i = 0; i < 4; ++i) {
                int nx = f[0] + dx[i], ny = f[1] + dy[i];
                if (!(nx >= 0 && nx < n && ny >= 0 && ny < n)) {
                    continue;
                }
                if (d[nx][ny] > d[f[0]][f[1]] + 1) {
                    d[nx][ny] = d[f[0]][f[1]] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, d[i][j]);
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
 */

/*Solution 2: Dijkstra
    public int maxDistance(int[][] grid) {
        int ans = -1;
        this.grid = grid;
        this.n = grid.length;
        int[][] d = new int [n][n];

        if (n == 1) return ans;

        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(
                new Comparator<int[]>() {
                    public int compare(int[] s1, int[] s2) {
                        return s1[0] - s2[0];
                    }
                }
        );

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{0, i , j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[1];
            int y = tmp[2];
            for (int i = 0; i < 4; i++) {
                int newX = x + index[0][i];
                int newY = y + index[1][i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;
                if (tmp[0] + 1 < d[newX][newY]) {
                    d[newX][newY] = tmp[0] + 1;
                    queue.offer(new int[]{d[newX][newY], newX, newY});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) ans = Math.max(ans, d[i][j]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
 */

/* Solution 3: SPFA form leetcode (seems like the update version of )
    public int maxDistance(int[][] grid) {
        final int INF = 1000000;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = grid.length;
        int[][] d = new int[n][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] inq = new boolean[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    d[i][j] = 0;
                    queue.offer(new int[]{i, j});
                    inq[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] f = queue.poll();
            inq[f[0]][f[1]] = false;
            for (int i = 0; i < 4; ++i) {
                int nx = f[0] + dx[i], ny = f[1] + dy[i];
                if (!(nx >= 0 && nx < n && ny >= 0 && ny < n)) {
                    continue;
                }
                if (d[nx][ny] > d[f[0]][f[1]] + 1) {
                    d[nx][ny] = d[f[0]][f[1]] + 1;
                    if (!inq[nx][ny]) {
                        queue.offer(new int[]{nx, ny});
                        inq[nx][ny] = true;
                    }
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, d[i][j]);
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
 */
    public int maxDistance(int[][] grid) {
        final int INF = 1000000;
        int n = grid.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j] = grid[i][j] == 1 ? 0 : INF;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) continue;
                if (i - 1 >= 0) f[i][j] = Math.min(f[i][j], f[i - 1][j] + 1);
                if (j - 1 >= 0) f[i][j] = Math.min(f[i][j], f[i][j - 1] + 1);
            }
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 1) continue;
                if (i + 1 < n) f[i][j] = Math.min(f[i][j], f[i + 1][j] + 1);
                if (j + 1 < n) f[i][j] = Math.min(f[i][j], f[i][j + 1] + 1);
            }
        }

        int ans = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans == INF ? -1 : ans;
    }
}

