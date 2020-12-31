import java.util.*;

/**
 * - LeetCode : https://leetcode.com/problems/word-search-ii/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. Trie and Backtracking(DFS)
 *      We store the words in the Trie, use DFS to search every possible words that can be formed
 *      and determine whether it is in the Trie.
 *      Time complexity: O( M(4⋅3 ^ (L−1) ) ) Where M is the number of cells in a two-dimensional grid and L is the maximum length of a word
 *      Space complexity: O(N), where N is the total number of letters in the dictionary

 * - Test cases:
 *   1.
 *   board = [["o","a","a","n"],
 *            ["e","t","a","e"],
 *            ["i","h","k","r"],
 *            ["i","f","l","v"]],
 *   words = ["oath","pea","eat","rain"]
 *
 *   2.
 *   board = [["a","b","c"],
 *           ["a","e","d"],
 *           ["a","f","g"]]
 *   words = ["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   79, 980, 211, 208
 */

public class lc212WordSearch2 {

/*Solution 1 version1:
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
        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        // if we know that there is no word match in the Trie for a given prefix,
        // then we don't need to explore a certain direction further.
        if (!trie.startsWith(str)) return;
        if (trie.search(str)) res.add(str);
        visited[x][y] = true;

        for (int i = 0; i < 4; i++){
            int tmpx = x + index[0][i];
            int tmpy = y + index[1][i];
            dfs(board, visited, str, tmpx, tmpy, trie);
        }

        // The important step in backtracking.
        visited[x][y] = false;
    }

 */

// Solution 1 version 2:
    class TrieNode{
        public HashMap<Character, TrieNode> map = new HashMap<>();
        String word = null;
        public TrieNode() {}
    }

    Set<String> res = new HashSet<>();
    int[][] index = {{0, 0, 1, -1},{1, -1, 0, 0}};
    char[][] board;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;

        // construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node  = root;

            for (char c : word.toCharArray()) {
                if (node.map.containsKey(c)) node = node.map.get(c);
                else {
                    TrieNode newNode = new TrieNode();
                    node.map.put(c, newNode);
                    node  = newNode;
                }
            }

            node.word = word;
        }

        // Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (root.map.containsKey(board[row][col]))
                    backTracking(row, col, root);
            }
        }
        return new ArrayList<>(res);
    }

    public void backTracking(int row, int col, TrieNode parent) {
        char letter = board[row][col];
        TrieNode curr = parent.map.get(letter);

        if (curr.word != null) res.add(curr.word);

        // mark it as visited.
        board[row][col] = '#';

        for (int i = 0; i < 4; i++){
            int tmpx = row + index[0][i];
            int tmpy = col + index[1][i];
            if (tmpx < 0 || tmpx >= board.length || tmpy < 0 || tmpy >= board[0].length) continue;
            if (curr.map.containsKey(board[tmpx][tmpy])) backTracking(tmpx, tmpy, curr);
        }

        // restore the original letter in the board.
        board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        // Because the word is already in the result and there is no other word's prefix is this word.
        if (curr.map.isEmpty()) parent.map.remove(letter);

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
