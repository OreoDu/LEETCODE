import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * - LeetCode : https://leetcode.com/problems/binary-tree-level-order-traversal/
 * - clarification: (Corner case...)
 *   root = null
 *
 * - solutions:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *   1. BFS
 *      First, put the root into the queue.
 *      When the queue is not empty, get the length of the current queue 'len',
 *      take len nodes from the queue and put their children into the queue,
 *      and then enter the next iteration.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   2. DFS
 *      We add the node during the DFS according to the level information.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 * - Test cases:
 *   1. []
 *   2. [3,9,20,null,null,15,7]
 *   3. [5,2,3,null,1,null,7]
 *   4. [5,3,4,2,6,7,8]
 *
 * - Important key:
 *   use current length to mark the level during the BFS.
 *
 * - Related problems:
 *   103, 199, 515, 637
 */

public class lc102BinaryTreeLevelOrderTraversal {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
/* Solution 1:
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

         while (!q.isEmpty()) {
             List<Integer> level = new ArrayList<>();
             int len = q.size();
             for (int i = 0; i < len ; i++) {
                 TreeNode node = q.poll();
                 if (node.left != null) q.offer(node.left);
                 if (node.right != null) q.offer(node.right);
                 level.add(node.val);
             }
             res.add(level);
         }
         return res;
    }

 */

// Solution 2:
    public void dfs(int level, TreeNode node, List<List<Integer>> res) {
        if (res.size() - 1 < level) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        if (node.left != null) dfs(level + 1, node.left, res);
        if (node.right != null) dfs(level + 1, node.right, res);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        dfs(0, root, res);
        return res;
    }

    public static void main(String[] args) {

    }
}
