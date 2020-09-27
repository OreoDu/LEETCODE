package Other.UnionFind;

/*
leetcode 547: https://leetcode.com/problems/friend-circles/
- Clarification:
  M = null, M = [], M = [[]]
- Solutions:
  1. Union Find
     Traverse the matrix one by one. When we meet 1 and col!=row, we union the two nodes(col and row)
     and count the number of the roots that generated during the process.
     Time complexity: O(n^2 * α(n)), α(n)is the time complexity of a single Union operation.
     Space complexity: O(n) -- root
  2. Graph traversal -- Get the number of the connected blocks
    · DFS
     Time complexity: O(n^2) -- We have to traverse the whole matrix.
     Space complexity: O(n) -- array visited[]
    · BFS
     Time complexity: O(n^2) -- traverse the whole matrix.
     Space complexity: O(n) -- queue and array visited[]

- Test case:
  1. M = null
  2. M = {}
  3. M = {{}}
  4. M = {{1,0,0,0},
          {0,1,0,0},
          {0,0,1,0},
          {0,0,0,1}};
  5. M = {{1,1,0,0},
          {1,1,0,0},
          {0,0,1,1},
          {0,0,1,1}};
- Important Key:
  Know the main object. -- The people in the friend circle.
  Mark the subset and count the number.
  Use DFS or BFS to search or union the objects based on the relation matrix M[][].
- Related problems
  323, 1101,
 */

public class lc547dfs {

    public void dfs (int i, int[][] M,int[] visited) {
        visited[i] = 1;

        for (int j = 0; j<M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) dfs(j,M,visited);
        }
    }

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0|| M[0].length == 0) return 0;
        int num = M.length;

        int[] visited = new int[num];
        int count = 0;

        for (int i = 0; i<num; i++) {
            if (visited[i] == 0) {
                dfs(i, M, visited);
                count++;
            }
        }
        return count;
    }

    public static void main (String[] args) {
        int[][] M = {{1,0,0,0},
                     {0,1,0,0},
                     {0,0,1,0},
                     {0,0,0,1}};
        lc547dfs s = new lc547dfs();
        int result = s.findCircleNum(M);
        System.out.println(result);
    }
}
