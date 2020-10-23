import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * - LeetCode: https://leetcode.com/problems/valid-parentheses/
 * - clarification:
 *   If the length of the string is odd. It isn't valid.
 *
 * - solutions:
 *   1. Traverse the string.
 *      If it is an opening parenthesis we store it in a stack.
 *      Then if it is a closing parenthesis we pop the element in the stack to see whether they match.
 *      If not or there is no parenthesis in the stack, return false.
 *      When we finished the traversal and the stack is empty, we can return true.
 *      We can use hashmap to map the opening and closing parenthesis.
 *
 *      Time complexity: O(n)
 *      Space complexity: O(n+∣Σ∣)  ∣Σ∣ is the number of the character types. Here we have six types"(){}[]".

 * - Test cases:
 *   1. "()"
 *   2. ")]}"
 *   3. "(]}"
 *   4. "{()[]}"
 *
 * - Important key:
 *   Use map to store the pairs
 *   and use stack to make sure that the parenthesis match with the most recently element.
 *
 * - Related problems:
 *   22, 32, 301, 1003
 */

public class lc20ValidParentheses {
    public boolean isValid(String s) {
        int l = s.length();
        if (l % 2 != 0) return false;

        Map<Character, Character> pairs  =  new HashMap<>(){{
            put(')','(');
            put('}','{');
            put(']','[');
        }};
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < l; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) return false;
                stack.pop();
            } else stack.push(ch);
        }
        return stack.isEmpty();
    }
}
