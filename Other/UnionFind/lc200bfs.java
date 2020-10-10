package Other.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 200: https://leetcode.com/problems/number-of-islands/
 * - clarification:
 *   grid == null, grid == [], grid ==[[]]
 * - solutions:
 *   1.Flood fill
 *     · DFS
 *     Create a extra matrix to mark the node we have visited.Traverse the grid one by one.
 *     When we meet the '1' that we didn't visit before, we add one to the count,mark the node visited,
 *     search its neighbor by DFS and mark every node visited.
 *     Time complexity: O(n*m)
 *     Space complexity: O(n*m)
 *     · BFS
 *     Much the same with the DFS,except that we search its neighbor by BFS.
 *     Time complexity: O(n*m)
 *     Space complexity: O(n*m)
 *   2.Union-Find
 *     Traverse the grid one by one. When we meet '1', union the node with its neighbors
 *     and count the number of the roots that generated during the process.
 *     Time complexity: O(nm * α(mn)), α(mn)is the time complexity of a single Union operation.
 *     Space complexity: O(n*m)
 * - Test cases:
 *   1. grid = null
 *   2. grid = {}
 *   3. grid = {{}}
 *   4. grid = {{'0','0','1','0','1'},
 *              {'0','1','1','1','0'},
 *              {'1','0','1','0','0'},
 *              {'0','0','1','0','1'}}
 * - Important key:
 *   Know the main object. -- Land coordinates.
 *   Mark the subset and count the number.
 *   Use DFS or BFS to search or union the objects based on the relation matrix Grid[][].
 * - Related problems:
 *   463,695,827
 */

public class lc200bfs {
    private boolean isValid(int i, int j, char[][] grid, int[][] visited) {
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length) return false;
        return grid[i][j] == '1' && visited[i][j] == 0;
    }

    private void bfs(int i, int j, char[][] grid, int[][] visited,int[][] index){
        int m = grid[0].length;

        Queue<Integer> q = new LinkedList<>();
        visited[i][j] = 1;
        q.add(i * m + j);

        while(!q.isEmpty()) {
            int node = q.poll();
            int row = node / m;
            int col = node % m;

            for(int a = 0; a<index[0].length; a++){
                int tempx = row + index[0][a];
                int tempy = col + index[1][a];
                if (isValid(tempx,tempy,grid,visited)){
                    // After being put in the queue, it must be marked as visited immediately.
                    // If mark it when it is moved out the queue, it will cause many duplicate nodes to enter the queue.
                    visited[tempx][tempy] = 1;
                    q.add(tempx * m + tempy);
                }
            }
        }
    }

    public int numIslands(char[][] grid)  {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        int[][] visited = new int[n][m];
        int[][] index = {{0,0,-1,1},{-1,1,0,0}};

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<m; j++) {
                if (grid[i][j] == '1' && visited[i][j] == 0) {
                    bfs(i,j,grid,visited,index);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] grid  = {{'0','0','1','0','1'},
                          {'0','1','1','1','0'},
                          {'1','0','1','0','0'},
                          {'0','0','1','0','1'}};
        lc200bfs s = new lc200bfs();
        int result = s.numIslands(grid);
        System.out.println(result);
    }
}
