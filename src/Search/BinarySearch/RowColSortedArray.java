package Search.BinarySearch;

import java.util.Arrays;

public class RowColSortedArray {
    static void main(String[] args) {
        int[][] matrix = {
                { 10, 20, 30, 40 },
                { 15, 25, 35, 45 },
                { 28, 29, 37, 49 },
                { 33, 34, 38, 50 }
        };

        int target = 49;

        System.out.print(Arrays.toString(binaryArraySort(matrix, target)));
    }

    /**
     * Searches a matrix whose rows and columns are both sorted in ascending order
     * (staircase search). Returns { row, col } of the target, or { -1, -1 } if absent.
     *
     * Approach: start at the top-right corner. That element is the largest in its row
     * and the smallest in its column, so each comparison rules out a whole row or column:
     *   - element > target -> target can't be in this column, move left
     *   - element < target -> target can't be in this row, move down
     *
     * Time complexity:  O(n + m) for n rows and m columns. Each step moves either one
     *                   column left or one row down, so there are at most n + m steps.
     *                   For an n x n matrix this is O(n).
     *                   Best case O(1), when the target is the top-right element.
     *
     * Space complexity: O(1). Only a few variables (row, col, element) are used,
     *                   regardless of the matrix size.
     */
    static int[] binaryArraySort(int[][] matrix, int target){
        // Empty matrix: nothing to search, and matrix[0] below would fail
        if(matrix.length == 0){
            return new int[] { -1, -1 };
        }

        int row = 0;                        // start at the first row
        int col = matrix[0].length - 1;     // start at the last column

        // Loop runs at most n + m times: row only increases, col only decreases
        while(row < matrix.length && col >= 0){
            int element = matrix[row][col];
            if(element == target){
                return new int[] { row, col };  // found
            }else if (element > target){
                col--;  // everything below in this column is even larger, discard the column
            }else{
                row++;  // everything to the left in this row is even smaller, discard the row
            }
        }

        return new int[] { -1, -1 };    // target is not in the matrix
    }
}
