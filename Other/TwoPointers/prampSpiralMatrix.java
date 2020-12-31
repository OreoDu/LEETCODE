import java.io.*;
import java.util.*;

/*
feedback:
1. confirm the question --- good
2. explain the thoughts --- use case explain the solution first(don't forget the corner case)
   and explain the time and space complexity!!! (15 min)
3. write the code and remember to write down the comments(15 min)
4. test the case --- didn't do this!!! (10 min)
5. the whole process should be in 45min!
6. clam down and avoid small syntax mistakes!!! (write code on the white board and pay attention the special case!!!)

inputMatrix  = [ [1,    2,   3,  4,    5],
                 [6,    7,   8,  9,   10],
                 [11,  12,  13,  14,  15],
                 [16,  17,  18,  19,  20] ]

corner case matrix[0],length == 1: matrix[0]

row = matrix.length
col = matrix[0].length
int i,j
right = col, down = row, left = 0, up = 0
while left < right && up < down {
 while j=left; j < right  res.put(matrix[up][j++])
 up++
 while j = up to down res.put(matrix[j][right])
 right--
 while j = right to left res.put(matrix[down][j])
 down --
 while j = down to up res.put(matrix[j][left])
 left ++
 4 for loops ==> right, down, left, up
}

Time complexity: O(n) n is row * col
Space complexity : O(1)
*/


class prampSpiralMatrix {

    static int[] spiralCopy(int[][] inputMatrix) {
        if (inputMatrix[0].length == 1) return inputMatrix[0];
        ArrayList<Integer> res = new ArrayList<>();
        int row = inputMatrix.length;
        int col = inputMatrix[0].length;

        int right = col - 1, left = 0, down = row - 1, up = 0;
        while (left < right && up < down) {
            for (int j = left; j <= right; j++) res.add(inputMatrix[up][j]);
            up++;
            for (int j = up; j <= down; j++) res.add(inputMatrix[j][right]);
            right--;
            for (int j = right; j >= left; j--) res.add(inputMatrix[down][j]);
            down--;
            for (int j = down; j >= up; j--) res.add(inputMatrix[j][left]);
            left++;
        }

        int[] result = new int[row * col];
        for (int i = 0; i < row * col; i++) result[i] = res.get(i);

        return result;
    }

    public static void main(String[] args) {
        int[][] inputMatrix = {{1,2,3},
                                {4,5,6}};
        int[] res = spiralCopy(inputMatrix);
        for (int i = 0; i < res.length; i++)
            System.out.println(res[i]);
    }

}