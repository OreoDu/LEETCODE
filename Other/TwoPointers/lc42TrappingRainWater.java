import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * - LeetCode : https://leetcode.com/problems/trapping-rain-water/
 * - clarification: (Corner case...)
 *   n == 0
 *   n == 1
 *   n == 2
 *   height = [0,1,3,5,6]
 *   height = [7,6,3,2,0]
 *
 * - solutions:
 *   1. How many grids of water are trapped above the specific grid
 *      depend on the height of the lower one between the tallest grid on the left
 *      and the tallest grid on the right.
 *      So the simplest way is to iterate through every position and find the tallest grid both in the left and right
 *      ,and then get the lower one to calculate the number of the grids that water are trapped above the position.
 *      Time complexity: O(n^2)
 *      Space complexity: O(1)
 *
 *   2. Dynamic programing
 *      Instead of getting the tallest grid on the left and right everytime we iterate the array,
 *      we can store those value in advance.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   3. Stack
 *      Monotonic stack: After a certain number, the first number is smaller than itself.
 *                       You can use monotonically increasing stack
 *                       (because the stack indicates that the current element is smaller than the top element of the stack);
 *                       after a certain number, the first one is smaller than For large numbers,
 *                       you can use monotonically decrementing the stack
 *                       (because popping out indicates that the current element is larger than the top element)
 *      We maintain a stack while traversing the array.
 *      If the current bar is less than or equal to the bar at the top of the stack,
 *      we pop the index of the bar into the stack,
 *      which means that the current bar is bounded by the previous bar in the stack.
 *      If we find that a bar is higher than the top of the stack,
 *      we can determine that the bar at the top of the stack is bounded by the current bar and the previous bar of the stack,
 *      so we can pop the top element of the stack and calculate the result according the height difference and distance.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   4. Two Pointers
 *      We can continue optimizing the solution 2. Instead of use arrays to store the tallest in the right and left,
 *      we can simplify the progress by using two pointers.
 *      Time complexity: o(n)
 *      Space complexity: O(1)
 *
 * - Test cases:
 *   1. height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   2. height = [0,1]
 *   3. height = [1]
 *   4. height = []
 *   5. height = [0,5,6,4,6,1,0,0,2,7]
 *   6. height = [9,2,4,0,3,4]
 *
 * - Important key:
 *   The key is to use a efficient way to find the bound(tallest height) in the left and right side
 *   and use the lower one to calculate the number of grids which trapped with water.
 *
 * - Related problems:
 *   11, 407, 755, 84, 85
 */

public class lc42TrappingRainWater {
/* Solution 1:
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int i = 0;
        while (i + 1 < height.length && height[i] <= height[i + 1]) i++;
        if (i == height.length - 1) return 0;
        int res = 0;

        for (int j = i; j < height.length; j++) {
            int leftMax = j;
            int rightMax = j;
            for (int k = j - 1; k >= 0; k--) leftMax = height[leftMax] < height[k] ? k : leftMax;
            for (int k = j + 1; k < height.length; k++) rightMax = height[rightMax] < height[k] ? k : rightMax;
            if (leftMax == j || rightMax == j) continue;
            int max = height[leftMax] > height[rightMax] ? rightMax : leftMax;
            res += height[max] - height[j];
        }
        return res;
    }
 */

/* Solution 2:
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int i = 0;

        while (i + 1 < len && height[i] <= height[i + 1]) i++;
        if (i == len - 1) return 0;

        int res = 0;

        int[] leftMax = new int[len];
        leftMax[i] = i;
        for (int j = i + 1; j < len; j++)
            leftMax[j] = height[j] > height[leftMax[j-1]] ? j : leftMax[j-1];

        int[] rightMax = new int[len];
        rightMax[len - 1] = len - 1;
        for (int j = len - 2; j >= i; j--)
            rightMax[j] = height[j] > height[rightMax[j + 1]] ? j : rightMax[j + 1];

        for (int j = i; j < len; j++) {
            int max = height[leftMax[j]] > height[rightMax[j]] ? rightMax[j]: leftMax[j];
            res += height[max] - height[j];
        }
        return res;
    }
 */

/* Solution 3:
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int current = 0;
        while (current + 1 < height.length && height[current] <= height[current + 1]) current++;
        if(current == height.length - 1) return 0;

        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            stack.push(current++);
        }
        return ans;
    }
 */
// Solution 4:!!!
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length - 1;
        int res = 0;

        while (left < right) {
            if(height[left] > height[right]) {
                if (height[right] >= rightMax) rightMax = height[right];
                else res += (rightMax - height[right]);
                right--;
            } else {
                if (height[left] >= leftMax) leftMax = height[left];
                else res += (leftMax - height[left]);
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        lc42TrappingRainWater s = new lc42TrappingRainWater();
        int[] height = {9,2,4,0,3,5};
        int res = s.trap(height);
        System.out.println(res);
    }
}
