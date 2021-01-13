import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * - LeetCode : https://leetcode-cn.com/problems/course-schedule/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   The relationship between the courses can form a graph, and the graph can be represented by a 2D Array (Adjacency list).
 *   For example: [[3,1],[2,3],[4,3],[5,3],[5,6],[0,2],[0,4]]
 *   which can be represented as :
 *   matrix: [[],[3],[0],[2,4,5],[0],[],[5]]
 *   matrix[i] means the children of i-th node.
 *
 *   if we cannot finish the courses, it means that there is a circle in the graph.
 *   ... A => ... => B...
 *             <=
 *   if we want to take the course B we must take course A first,
 *   but if we want to take course A we must finish the course B first.
 *   So in the end there will be a dead lock and course A,B will wait for each other forever.
 *
 *   So the key is to figure out whether there is a cycle in the graph.
 *   We can use DFS and BFS to traverse the graph and verify the result.
 *
 *   1. DFS
 *      When we do DFS in the graph, if we meet a node that we have already visited once during the recursion.
 *      So we have to mark the state of the node.
 *      There are there three states of one node:
 *      State 1: never visited before.   >> visited[i] == 0
 *      State 2: visited once but still in the recursion stack(not be backtracked yet).  >> visited[i] == 1
 *      State 3: visited twice and is out of the recursion stack.  >> visited[i] == 2
 *      So if we meet a node i of which visited[i] is equal to 1, it means there is a circle in the graph.
 *      Time complexity: O(m + n)
 *      Space complexity: O(n + m)
 *
 *   2. BFS
 *      A graph without circle must have node of which in-Degree is zero.
 *      Based on this, we can do BFS in the graph
 *      and add nodes with zero in-degree into a queue repeatedly during the traversal and delete them from the graph
 *      until the queue is empty.
 *
 *      More specifically, we do following steps:
 *      Firstly, we push all the nodes with zero in-degree into the queue.
 *      Then, we pop out the node in the queue, everytime we pop out a node we delete it from the graph,
 *      and we minus one to the in-degree of the node's children. If the in-degree of the children become zero we push that node into the queue.
 *      Repeat above operations until the queue is empty.
 *
 *      If the queue is empty and there's still node left in the graph,
 *      it means that the in-degree of those nodes are all greater than zero.
 *      It proves that there is circle in the graph.
 *      Time complexity: O(m + n)
 *      Space complexity: O(m + n)
 *
 * - Test cases:
 *   1. numCourses = 2, prerequisites = [[1,0]]    >>> true
 *   2. numCourses = 7, prerequisites = [[3,1],[2,3],[4,3],[5,3],[5,6],[0,2],[0,4]]   >>> true
 *   3. numCourses = 7, prerequisites = [[3,1],[3,0],[2,3],[4,3],[5,3],[5,6],[0,2],[0,4]]   >>> false
 *
 * - Important key:
 *   Topological Sort
 *
 * - Related problems:
 *   210
 */
public class lc207CourseSchedule {
/* Solution 1:
    public List<List<Integer>> graph;
    public int[] visited;
    public boolean valid = true;

    public boolean canFinish(int n, int[][] arr) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        visited = new int[n];

        for (int i = 0; i < arr.length; i++) {
            graph.get(arr[i][1]).add(arr[i][0]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && valid) dfs(i);
        }

        return valid;
    }

    public void dfs(int i) {
        visited[i] = 1;
        for (int j = 0; j < graph.get(i).size(); j++) {
            int children = graph.get(i).get(j);
            if (visited[children] == 0) {
                dfs(children);
                if (!valid) return;
            } else if (visited[children] == 1) {
                valid  = false;
                return;
            }
        }
        visited[i] = 2;
    }
 */

    public List<List<Integer>> edges;
    public int[] inDegree;

    public boolean canFinish(int n, int[][] arr) {
        edges = new ArrayList<>();
        inDegree = new int[n];

        for (int i = 0; i < n; i++) edges.add(new ArrayList<Integer>());
        for (int[] edge : arr) {
            edges.get(edge[1]).add(edge[0]);
            inDegree[edge[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        int visited = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            visited++;

            for (int children : edges.get(node)) {
                inDegree[children]--;
                if (inDegree[children] == 0) q.offer(children);
            }
        }

        return visited == n;

    }




}
