package Other.UnionFind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 547: https://leetcode.com/problems/friend-circles/
 * - Clarification:
 *   M = null, M = [], M = [[]]
 * - Solutions:
 *   1. Union Find
 *      Traverse the matrix one by one. When we meet 1 and col!=row, we union the two nodes(col and row)
 *      and count the number of the roots that generated during the process.
 *      Time complexity: O(n^2 * α(n)), α(n)is the time complexity of a single Union operation.
 *      Space complexity: O(n) -- root
 *   2. Graph traversal -- Get the number of the connected blocks
 *     · DFS
 *      Time complexity: O(n^2) -- We have to traverse the whole matrix.
 *      Space complexity: O(n) -- array visited[]
 *     · BFS
 *      Time complexity: O(n^2) -- traverse the whole matrix.
 *      Space complexity: O(n) -- queue and array visited[]
 *
 * - Test case:
 *   1. M = null
 *   2. M = {}
 *   3. M = {{}}
 *   4. M = {{1,0,0,0},
 *           {0,1,0,0},
 *           {0,0,1,0},
 *           {0,0,0,1}};
 *   5. M = {{1,1,0,0},
 *           {1,1,0,0},
 *           {0,0,1,1},
 *           {0,0,1,1}};
 * - Important Key:
 *   Know the main object. -- The people in the friend circle.
 *   Mark the subset and count the number.
 *   Use DFS or BFS to search or union the objects based on the relation matrix M[][].
 * - Related problems
 *   323, 1101,
 */

public class lc547bfs {
    public void bfs (int i, int[][] M, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        visited[i] = 1;

        while(!q.isEmpty()) {
            int node = q.poll();
            for (int j = 0; j < M.length; j++) {
                if (visited[j] == 0 && M[node][j] == 1) {
                    q.add(j);
                    visited[j] = 1;
                }
            }
        }

    }

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int num = M.length;

        int count = 0;
        int[] visited = new int[num];

        for (int i = 0; i<num; i++) {
            if (visited[i] == 0) {
                bfs(i,M,visited);
                count++;
            }
        }
        return count;
    }

    public static void main (String[] args) {
        int[][] M = {{1,1,0,0},
                     {1,1,0,0},
                     {0,0,1,0},
                     {0,0,0,1}};
        lc547bfs s = new lc547bfs();
        int result = s.findCircleNum(M);
        System.out.println(result);
    }
}

