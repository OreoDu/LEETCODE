import java.util.HashMap;
import java.util.HashSet;

/**
 * - LeetCode : https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * - clarification: (Corner case...)
 *
 * - solutions:
 *   The largest XOR value can be 111..1 (the length of it will be the length of the largest number in the array).
 *   We know that if a^b = max, then a^max = b.
 *   So if we create a max, and a^max can be found in the array, then we know that the max can be the result.
 *
 *   We create max from the highest bit to the lowest bit.
 *                        i
 *   For position i: if 111.. ^ a can be found in the array then we know that the value in position i of max can be 1.
 *                   if it can not be found, then we know that the value in position i of max can only be 0.
 *
 *   1. HashSet
 *      Based on above, we can use hashset to store the prefix of every element in the array.
 *      Time complexity: O(sum(2^(len - i) * 2^(len - i) + n, i form 0 ~ len -1) = n*len + 4/3(4^len - 1)) ~ O(n)
 *      Space complexity: O(len) ~ O(1)
 *   2. Trie
 *      In the previous solution, we add new prefixes and compute every curXOR^prefix every time we move forward one bit.
 *      But in the process we already know that some numbers with certain prefixes will never be the largest result.
 *      For example: for result: 111... we are now checking whether it can be 1111.. or 1110...
 *                               when we meet a 0001.. and there is no number in the array that starts with 111...
 *                               Maybe there will be a 110... but the XOR result of 110... and 0001.. will never be greater than 111...
 *      So in such condition, we can do pruning.
 *      But it is hard to do pruning by hashset, we can use Trie to achieve this.
 *
 *      For  [3, 10, 5, 25, 2] :
 *      we can construct Trie like this:
 *                      root :  0       1
 *                            /   \      \
 *                           0     1      1
 *                        /   \   /      /
 *                       0    1  0      0
 *                       \   /    \    /
 *                       1   0    1   0
 *                      / \  \   /    \
 *                     0  1  1  0     1
 *                     |  |  |  |     |
 *                     2  3  5  10    25
 *
 *      a^max = b, so for every a we check if b is in the Trie.
 *                      pos:   i
 *      for example: a = 3 = 00011
 *                   if it is "0" in i position, we check if there is a "1" in the current TrieNode.
 *                                               if yes, the value of i position in max can be 1, otherwise, we set it to 0 and move forward.
 *                   if it is "1" in i position, we check if there is a "0" in the current TrieNode.
 *                                               if yes, the value of i position in max can be 1, otherwise, we set it to 0 and move forward.
 *
 *      Time complexity: O(n)
 *      Space complexity: the height of the Trie is len. O(2^len) = O(M) ~ O(1), M is the largest number in the array.

 * - Test cases:
 *   1. nums = [0]
 *   2. nums = [2,4]
 *   3. nums = [8,10,2]
 *   4. nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 *
 * - Important key:
 *
 *
 * - Related problems:
 *   1707
 */

public class lc421MaximumXOROfTwoNumbersInAnArray {
/* Solution 1:

    public static int findMaximumXOR(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return arr[0]^arr[1];

        // find the largest number in the array and get the length.
        int max = Integer.MIN_VALUE;
        for (int num: arr) max = Math.max(max, num);
        int len = (Integer.toBinaryString(max)).length();

        // Construct maximum from the highest bit to the lowest bit.
        int maxXOR = 0; // initial the bit with 0
        int curXOR;
        HashSet<Integer> prefixes = new HashSet<>();

        for (int i = len - 1; i >= 0; i--) {
            maxXOR <<= 1;  // go to the next bit by left shift
            curXOR = maxXOR | 1; // set the new bit to 1

            prefixes.clear();

            // compute all possible prefixes of length (L - i) in binary representation
            for (int num : arr) prefixes.add(num >> i);

            // check if p1^p2 == currXor, i.e. p1 == currXor^p2.
            // if yes, update the maxXOR
            for (int num : prefixes) {
                if (prefixes.contains(num ^ curXOR)) {
                    maxXOR = curXOR;
                    break;
                }
            }
        }
        return maxXOR;
    }
 */

/* Solution 1 version 2 (Solution from the leetcode):
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) max = Math.max(max, nums[i]);
        int l = 0;
        while (max > 0) {
            max >>= 1;
            l++;
        }
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = l - 1; i >= 0; i--) {
            int temp = ans;
            temp = (temp >> i) + 1;
            int j;
            for (j = 0; j < len; j++) {
                int e = nums[j] >> i;
                int t = temp ^ e;
                if (!set.contains(t)) set.add(e);
                else break;
            }
            if (j < len) ans = ans + (1 << i);
            set.clear();
        }
        return ans;
    }

 */

/* Solution 2 version 1:

    static class TrieNode {
        public HashMap<Character, TrieNode> children  = new HashMap<>();
        public TrieNode() {}
    }

    public static int findMaximumXOR(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return arr[0]^arr[1];

        // find the largest number in the array and get the length.
        int max = Integer.MIN_VALUE;
        for (int num: arr) max = Math.max(max, num);
        int len = (Integer.toBinaryString(max)).length();

        // zero left-padding to ensure L bits for each number
        int bitmask = 1 << len;
        String[] nums = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            nums[i] = Integer.toBinaryString(arr[i] | bitmask).substring(1);

        int maxXOR = 0;
        TrieNode root = new TrieNode();
        for (String s : nums) {
            TrieNode node  = root;
            TrieNode xorNode = root;
            int curXOR = 0;
            for (Character c : s.toCharArray()) {
                // insert the new number into the Trie
                if (node.children.containsKey(c)) node  = node.children.get(c);
                else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(c, newNode);
                    node  = newNode;
                }

                // construct the curXOR
                Character opBit = c.equals('0') ? '1' : '0';
                if (xorNode.children.containsKey(opBit)) {
                    curXOR = (curXOR << 1 ) | 1;
                    xorNode = xorNode.children.get(opBit);
                } else {
                    curXOR = curXOR << 1;
                    xorNode = xorNode.children.get(c);
                }
            }
            // update the maxXOR
            maxXOR = Math.max(maxXOR, curXOR);
        }
        return maxXOR;
    }
 */

// Solution 2 version 2: use array instead of HashMap.
    static class TrieNode {
        public TrieNode[] children = new TrieNode[2];
        public TrieNode() {}
    }

    public static int findMaximumXOR(int[] arr) {
        if (arr.length == 1) return 0;
        if (arr.length == 2) return arr[0]^arr[1];

        // find the largest number in the array and get the length.
        int max = Integer.MIN_VALUE;
        for (int num: arr) max = Math.max(max, num);
        int len = (Integer.toBinaryString(max)).length();

        // zero left-padding to ensure L bits for each number
        int bitmask = 1 << len;
        String[] nums = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            nums[i] = Integer.toBinaryString(arr[i] | bitmask).substring(1);

        int maxXOR = 0;
        TrieNode root = new TrieNode();
        for (String s : nums) {
            TrieNode node  = root;
            TrieNode xorNode = root;
            int curXOR = 0;
            for (Character c : s.toCharArray()) {
                int num = Character.getNumericValue(c);
                // insert the new number into the Trie
                if (node.children[num] != null) node  = node.children[num];
                else {
                    TrieNode newNode = new TrieNode();
                    node.children[num] = newNode;
                    node  = newNode;
                }

                // construct the curXOR
                int opBit = num == 0 ? 1 : 0;
                if (xorNode.children[opBit] != null) {
                    curXOR = (curXOR << 1 ) | 1;
                    xorNode = xorNode.children[opBit];
                } else {
                    curXOR = curXOR << 1;
                    xorNode = xorNode.children[num];
                }
            }
            // update the maxXOR
            maxXOR = Math.max(maxXOR, curXOR);
        }
        return maxXOR;
    }



    public static void main(String[] args) {
        lc421MaximumXOROfTwoNumbersInAnArray s = new lc421MaximumXOROfTwoNumbersInAnArray();
        int[] nums = {8,10,2};
        int res = s.findMaximumXOR(nums);
        System.out.println(res);
    }
}
