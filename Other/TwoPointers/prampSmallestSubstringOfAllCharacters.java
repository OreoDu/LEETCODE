import java.util.HashMap;
import java.util.Map;

/**
 * - Pramp :
 *
 *   Given an array of unique characters arr and a string str,
 *   Implement a function `getShortestUniqueSubstring` that finds the smallest substring of str
 *   containing all the characters in `arr`.
 *   Return "" (empty string) if such a substring doesn’t exist.
 *
 *   input:  arr = ['x','y','z'], str = "xyyzyzyx"
 *   output: "zyx"
 *
 *   1 ≤ arr.length ≤ 30
 *   1 ≤ str.length ≤ 500
 *
 *
 *   If your peer is stuck, ask how they can determine if a given substring is valid
 *   and then ask how to apply that in their solution.
 *   Recall that a substring is considered valid if it contains all the characters in the input array, arr.
 *
 *   If your peer is using a brute force approach by checking all the possible substrings,
 *   ask how they can avoid duplicate computations.
 *
 *  Make sure proper initializations are made.
 *  Watch out for unnecessary variables and steps.
 *  For other solutions, make sure that any permutation of the characters in the input array arr
 *  can be found by the algorithm your peer proposes.
 *
 *  If still no progress, point your peer in the right direction
 *  by hinting them to use two indices (the start and end positions of a sliding window)
 *  in order to efficiently traverse the array and check potential substrings.
 *
 * - clarification: (Corner case...)
 *   str.length() < arr.length
 *
 * - solutions:
 *   1. Two pointers
 *      We have to find a substring that contains all the characters in the array first, then update the result,
 *      then remove one character in the front, determine whether the new substring still meets the conditions.
 *      if not, move forward, add new characters to the substring and repeat the above operations.
 *      if yes, update the result, remove one character in the front and repeat the operations above.
 *
 *      update the result: if the length of new substring is smaller than the previous result
 *                         or equals to the length of the array.
 *      Time complexity: O(N+M)
 *      Space complexity: O(M)
 *
 * - Test cases:
 *   1. arr = ['x','y','z'], str = "xyyzyzyx"
 *   2. arr = ['x','y','z'], str = "xyy"
 *   3. arr = ['x','y','z'], str = "xyyzzyyyx"
 *
 * - Important key:
 *   We have to use map to store the number of each character that have been showed in the substring,
 *   so that we can find out whether a substring contains all the characters in the substring.
 *
 *   The way to determine whether a subarray meets the condition.
 *   -- store the elements in the map and record the frequency of them,
 *      and use `unique` to store the number of unique elements.
 *
 *   The way to get the new array.
 *   -- reduce the subarray or move forward.
 *
 *   The way to get and update the ideal result.
 *   -- once we get a subarray that contains all the characters we start to reduce the length until its length reach to the length of the array.
 *      and see whether it still contains all the characters. So that we can update to the smallest result.
 *
 */


// 1.corner case
    // Hash set

public class prampSmallestSubstringOfAllCharacters {
    public static String getShortestUniqueSubstring(char[] arr, String str) {
        if (str.length() < arr.length) return "";
        Map<Character, Integer> map = new HashMap<>();
        String res = "";
        int uniqueCounter = 0;

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], 0);
        }

        int head = 0;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!map.containsKey(c)) continue;

            int tailCount = map.get(c);
            if (tailCount == 0) uniqueCounter = uniqueCounter + 1;

            map.put(c, map.get(c) + 1);

            while (uniqueCounter == arr.length) {
                int tempLength = i - head + 1;
                if (tempLength == arr.length) return str.substring(head, i + 1);

                if (res.equals("") || tempLength < res.length()) res = str.substring(head, i + 1);

                char h = str.charAt(head);
                if (map.containsKey(h)) {
                    int headCounter = map.get(h) - 1;
                    if (headCounter == 0) uniqueCounter = uniqueCounter - 1;
                    map.put(h, headCounter);
                }
                head += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "yyzzyyyx";
        char[] c = {'x','y','z'};
        String res = getShortestUniqueSubstring(c, s);
        System.out.println(res);
    }
}
