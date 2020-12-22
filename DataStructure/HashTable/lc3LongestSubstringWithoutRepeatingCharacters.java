import java.util.HashMap;
import java.util.Hashtable;

/**
 * - LeetCode : https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * - clarification: (Corner case...)
 *   s = ""
 *
 * - solutions:
 *   When can iterate through the string and record the starting position the substring,
 *   if we meet a character that has already been in the substring
 *   we should change the starting position into the position which is behind that repeated character.
 *   (We keep the characters behind the repeated char because we are sure that there is no duplicate char in that new substring)
 *   The whole process is like we are moving a sliding window which store the substring without repeating characters.
 *   And we update the longest length of the substring.
 *   Now, the left problem is how to find out that whether a character is already in the substring efficiently.
 *
 *   1. HashMap
 *      we can use haspmap to store the characters in the substring.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *   2. Array
 *      We can also use array with size 256 to store the substring.
 *      Time complexity: O(n)
 *      Space complexity: O(256)

 * - Test cases:
 *   1. s = ""
 *   2. s = "b"
 *   3. s = "bbbbb"
 *   4. s = "abcabcbb"
 *   5. s = "abcdfberty"
 *
 * - Important key:
 *   Update the position of the start point is the key.
 *
 * - Related problems:
 *   159, 340, 992
 */

public class lc3LongestSubstringWithoutRepeatingCharacters {
/* Solution 1:
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 1) return 1;

        int longest = 0;
        int curr = 0;
        int start = 0;
        HashMap<Character,Integer> substr = new HashMap<>();

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (substr.containsKey(c) && substr.get(c) >= start) {
                start = substr.get(c) + 1;
                longest = longest < curr ? curr : longest;
                curr = i - start;
            }
            substr.put(c, i);
            curr++;
        }
        longest = Math.max(longest, curr);
        return longest;
    }

 */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 1) return 1;

        int longest = 0;
        // the length of the current substring.
        int curr = 0;
        // the position of the first element of substring in the original string.
        int start = 0;
        int [] dict = new int[256];

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (dict[c] > start) {
                start = dict[c];
                longest = longest < curr ? curr : longest;
                curr = i - start;
            }
            dict[c] = i + 1;
            curr++;
        }
        longest = longest < curr ? curr : longest;
        return longest;
    }

    public static void main(String[] args) {
        lc3LongestSubstringWithoutRepeatingCharacters s = new lc3LongestSubstringWithoutRepeatingCharacters();
        String str = "abcda";
        int res = s.lengthOfLongestSubstring(str);
        System.out.println(res);
    }
}
