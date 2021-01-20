import java.util.HashSet;

/**
 * - LeetCode : https://leetcode.com/problems/jewels-and-stones/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   1. HashSet
 *      We can use set to store the jewels, then iterate through the stone array
 *      and see whether the stone is in the set.
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *
 *   2. Use array instead of set.
 *      Time complexity: O(n)
 *      Space complexity: O(256)
 *
 * - Test cases:
 *   1. jewels = "aA", stones = "aAAbbbb"
 *
 * - Important key:
 *
 *
 * - Related problems:
 *
 */

public class lc771JewelsAndStones {
/* Solution 1:
    public int numJewelsInStones(String jewels, String stones) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }

        int counter = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) counter++;
        }

        return counter;
    }
 */

//Solution 2:
    public int numJewelsInStones(String J, String S) {
        int len = J.length();
        int[] type = new int[256];
        for(int i = 0; i < len; i++){
            type[J.charAt(i)] = 1;
        }
        int ans = 0;
        len = S.length();
        for(int i = 0; i < len; i++){
            ans += type[S.charAt(i)];
        }
        return ans;
    }

}
