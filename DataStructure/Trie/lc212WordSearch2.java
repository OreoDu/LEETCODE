import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * - LeetCode :
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1.
 *      Time complexity:
 *      Space complexity:
 *   2.
 *      Time complexity:
 *      Space complexity:

 * - Test cases:
 *   1.
 *   board = [["o","a","a","n"],
 *            ["e","t","a","e"],
 *            ["i","h","k","r"],
 *            ["i","f","l","v"]],
 *   words = ["oath","pea","eat","rain"]
 *
 *   [["a","b","c"],
 *   ["a","e","d"],
 *   ["a","f","g"]]
 * ["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc212WordSearch2 {
    Set<String> res = new HashSet<>();
    int[][] index = {{0, 0, 1, -1},{1, -1, 0, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        return new ArrayList<String>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;
        if (trie.search(str)) res.add(str);
        visited[x][y] = true;

        for (int i = 0; i < 4; i++){
            int tmpx = x + index[0][i];
            int tmpy = y + index[1][i];
            dfs(board, visited, str, tmpx,tmpy, trie);
        }

        visited[x][y] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'a','b','c'},
                          {'a','e','d'},
                          {'a','f','g'}};
        String[] words = {"gfedcbaaa","eaabcdgfa"};
        lc212WordSearch2 s = new lc212WordSearch2();
        s.findWords(board, words);
    }
}
