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

public class lc547UnionFind {

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int num = M.length;
        UnionFind uf = new UnionFind(num);

        for (int i = 0; i<num; i++) {
            for (int j = 0; j<num; j++) {
                if (i == j) continue;
                if (M[i][j] == 1 && !uf.connected(i,j)) {
                    uf.union(i,j);
                }
            }
        }
        return uf.rootsNumber;
    }

    public static void main (String[] args) {
        int[][] M = {{1,1,0,1},
                     {1,1,0,1},
                     {0,0,1,0},
                     {1,1,0,1}};
        lc547UnionFind s = new lc547UnionFind();
        int result = s.findCircleNum(M);
        System.out.println(result);
    }


}
