import java.util.ArrayList;
import java.util.List;

/**
 * - LeetCode : https://leetcode.com/problems/zigzag-conversion/
 * - clarification: (Corner case...)
 *   s = "A", numRows = 1
 * - solutions:
 *   1. The elements of the new string are determined by the regular pattern of the index of different lines.
 *      (k: the k-th row, start at 0)
 *      The character in row 0 is at index: 2 * (numRows - 1) * k;
 *      The character in row numRows−1 is at index: k + 2 * (numRows - 1) * k;
 *      The characters in the inner line i are located at indexes: k + 2 * (numRows - 1) * k and k + 2 * (numRows - k - 1);
 *      Time complexity: O(n)
 *      Space complexity: O(n)
 *   2. Instead of forming the new string from the index pattern,
 *      we can iterate through the original string and put the character to corresponding row.
 *      Time complexity: O(n)
 *      Space complexity: o(n)

 * - Test cases:
 *   1. s = "A", numRows = 1
 *   2. s = "ADFCH", numRows = 1
 *
 * - Important key:
 *   Find the pattern of the index in the string after conversion.
 *
 * - Related problems:
 *
 */

public class lc6ZigZagConversion {
/* Solution 1:
    public String convert(String s, int numRows) {
        if (s.length() == 1 || numRows == 1) return s;
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < numRows; i++) {
            int index = i;
            while (index < s.length()) {
                res.append(s.charAt(index));
                if (i != 0 && i != numRows - 1 && index + 2 * (numRows - i - 1) < s.length())
                    res.append(s.charAt(index + 2 * (numRows - i - 1)));
                index = index + 2 * (numRows - 1);
            }
        }
        return "" + res;
    }
 */
    public String convert(String s, int numRows) {
        if (s.length() == 1 || numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int currRow = 0;
        boolean convert = false;

        for (char c: s.toCharArray()) {
            rows.get(currRow).append(c);
            if (currRow == 0 || currRow == numRows - 1) convert = !convert;
            currRow += convert ? 1: -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row: rows) res.append(row);
        return res.toString();
    }

    public static void main(String[] args) {
        lc6ZigZagConversion solu = new lc6ZigZagConversion();
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String res = solu.convert(s, numRows);
        System.out.println(res);
    }
}
