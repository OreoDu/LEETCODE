package Other.UnionFind;

/*
leetcode 200: https://leetcode.com/problems/number-of-islands/
- clarification:
  grid == null, grid == [], grid ==[[]]
- solutions:
  1.Flood fill
    · DFS
    Create a extra matrix to mark the node we have visited.Traverse the grid one by one.
    When we meet the '1' that we didn't visit before, we add one to the count,mark the node visited,
    search its neighbor by DFS and mark every node visited.
    Time complexity: O(n*m)
    Space complexity: O(n*m)
    · BFS
    Much the same with the DFS,except that we search its neighbor by BFS.
    Time complexity: O(n*m)
    Space complexity: O(n*m)
  2.Union-Find
    Traverse the grid one by one. When we meet '1', union the node with its neighbors
    and count the number of the roots that generated during the process.
    Time complexity: O(nm * α(mn)), α(mn)is the time complexity of a single Union operation.
    Space complexity: O(n*m)
- Test cases:
  1. grid = null
  2. grid = {}
  3. grid = {{}}
  4. grid = {{'0','0','1','0','1'},
             {'0','1','1','1','0'},
             {'1','0','1','0','0'},
             {'0','0','1','0','1'}}
- Important key:
  Know the main object. -- Land coordinates.
  Mark the subset and count the number.
  Use DFS or BFS to search or union the objects based on the relation matrix Grid[][].
- Related problems:
  463,695,827
 */

public class lc200dfs{
    private void dfs(int i, int j, char[][] grid,int[][] index,int[][] visited) {
        if (grid[i][j] == '0' || visited[i][j] == 1) return;

        visited[i][j] = 1;

        for (int a = 0; a<index[0].length; a++) {
            int tempx = i + index[0][a];
            int tempy = j + index[1][a];
            if (tempx >= 0 && tempx < grid.length && tempy >= 0 && tempy < grid[0].length)
                dfs(tempx, tempy, grid, index, visited);
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        int[][] index = {{0,0,-1,1},{-1,1,0,0}};
        int[][] visited = new int[n][m];

        for (int i = 0; i< n; i++) {
            for (int j = 0; j< m; j++) {
                if(grid[i][j] == '0' || visited[i][j] == 1) continue;
                dfs(i,j,grid,index,visited);
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] grid  = {{'1','0','1','1','0'}};
        lc200dfs s = new lc200dfs();
        int result = s.numIslands(grid);
        System.out.println(result);
    }
}