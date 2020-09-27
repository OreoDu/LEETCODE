package Other.UnionFind;

/*
leetcode 200: https://leetcode.com/problems/number-of-islands/
- clarification:
  grid == null: []
  grid[0] == null : [[]]
- solutions:
  1.Flood fill
    · DFS
    Create a extra matrix to mark the node we have visited.Traverse the grid one by one.
    When we meet the '1' that we didn't visit before, we add one to the count,mark the node visited,
    search its neighbor by DFS and mark every node visited.
    · BFS
    Much the same with the DFS,except that we search its neighbor by BFS.
  2.Union-Find
    Traverse the grid one by one. When we meet '1', union the node with its neighbors
    and count the number of the roots that generated during the process.
- Test cases:
  1. grid = {}
  2. grid = {{}}
  3. grid = {{'1','1','0','0','1'},
             {'1','1','1','1','1'},
             {'1','0','1','0','0'},
             {'0','0','1','0','1'}}
- Important key:
  Mark the subset and count the number.
- Related problems:
  463,695,827
 */

public class lc200UnionFind {

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        // We only have to visit the neighbor that is below it and on the right which is different from the bfs and dfs.
        //
        int[][] index = {{0,1},{1,0}};
        UnionFind uf = new UnionFind(n*m);

        for (int i = 0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    for (int k = 0; k < index[0].length; k++) {
                        int tempx = i + index[0][k];
                        int tempy = j + index[1][k];
                        if (tempx >= 0 && tempy >= 0 && tempx < n && tempy < m && grid[tempx][tempy] == '1'
                             && !uf.connected(i * m + j, tempx * m + tempy))
                            uf.union(i * m + j, tempx * m + tempy);
                    }
                } else uf.rootsNumber--; // deduct the '0' root.
            }
        }
        return uf.rootsNumber;
    }

    public static void main (String[] args) {
        char[][] grid = {{'1','1','1','1','0'},
                         {'1','1','0','1','0'},
                         {'1','1','0','0','0'},
                         {'0','0','0','0','0'}};
        lc200UnionFind s = new lc200UnionFind();
        int result = s.numIslands(grid);
        System.out.println(result);
    }
}
