package Search.BinarySearch;

import java.util.Arrays;

public class FullySortedArray {
    static void main() {
        int[][] matrix = {
                { 1, 2, 3, 4, 5 },
                { 6, 7, 8, 9, 10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
        };

        int target = 88;

        System.out.print(Arrays.toString(search(matrix, target)));
    }

    /**
     * Searches a fully sorted matrix: every row is sorted in ascending order and
     * the first element of each row is greater than the last element of the row before it,
     * so reading the matrix row by row gives one sorted sequence.
     * Returns { row, col } of the target, or { -1, -1 } if absent.
     *
     * Approach: two binary searches.
     *   1. Binary search the rows using the middle column. That column is sorted top to
     *      bottom, so each comparison discards half of the remaining rows. Stop when only
     *      two rows are left.
     *   2. In each of the two remaining rows, compare the target with the middle-column
     *      element and binary search the half of the row that could contain it.
     *
     * Time complexity:  O(log n + log m) for n rows and m columns, which is the same as
     *                   O(log(n * m)). Step 1 halves the rows each time: O(log n).
     *                   Step 2 runs at most two binary searches over half a row: O(log m).
     *                   Best case O(1), when the first element checked is the target.
     *
     * Space complexity: O(1). Only index variables are used, and the binary searches are
     *                   loops, so there is no recursion stack.
     */
    static int[] search(int[][] matrix, int target){
        int rows = matrix.length;

        // Empty matrix: nothing to search, and matrix[0] below would fail
        if(rows == 0 || matrix[0].length == 0){
            return new int[] { -1, -1 };
        }

        int cols = matrix[0].length;    // number of columns, so the last column index is cols - 1

        // Single row: plain binary search across that row
        if(rows == 1){
           return rowBinarySearch(matrix, target, 0, 0, cols - 1);
        }

        int rowStart = 0;
        int rowEnd = rows - 1;

        int colMid = cols / 2;  // the middle column, used to compare against in both steps

        // Step 1: binary search the rows on the middle column until only two rows remain.
        // The bounds move to midRow itself (not midRow + 1 or midRow - 1) because the target
        // can still be in midRow, to the left or right of the middle column.
        while ( rowStart < ( rowEnd -1 ) ){
            int midRow = getMid(rowStart, rowEnd);

            if(matrix[midRow][colMid] == target){
                return new int[] { midRow, colMid };    // found in the middle column
            }
            else if( matrix[midRow][colMid] < target ){
                rowStart = midRow;  // target is larger, so it cannot be in any row above midRow
            }
            else {
                rowEnd = midRow;    // target is smaller, so it cannot be in any row below midRow
            }
        }


        // Step 2: only rows rowStart and rowEnd are left, so check each of them
        for(int i = rowStart; i <= rowEnd; i++){
            int[] arr = matrix[i];
            if(arr[colMid] == target ) {
                return new int[]{i, colMid};    // found in the middle column of row i
            }
            else if( arr[colMid] < target ){
                // target is larger than the middle element: binary search the right half of this row
                int[] result = rowBinarySearch(matrix, target, i, colMid + 1, cols - 1);
                if( result[0] > -1 ) {
                    return result;
                }
            }
            else {
                // target is smaller than the middle element: binary search the left half of this row
                int[] result = rowBinarySearch(matrix, target, i, 0, colMid - 1);
                if( result[0] > -1 ) {
                    return result;
                }
            }
        }

        return new int[] { -1, -1 };    // target is not in the matrix

    }

    /**
     * Standard binary search for the target in one row, between columns colStart and colEnd
     * (both inclusive). Returns { row, col } of the target, or { -1, -1 } if absent.
     *
     * Time complexity:  O(log k), where k = colEnd - colStart + 1 is the number of columns
     *                   searched. Each step halves the range.
     *
     * Space complexity: O(1).
     */
    static int[] rowBinarySearch(int[][] matrix, int target, int row, int colStart, int colEnd){
        int[] arr = matrix[row];

        while(colStart <= colEnd){
            int mid = getMid(colStart, colEnd);

            if ( arr[mid] == target ) {
                return new int[] { row, mid };  // found
            }
            else if ( arr[mid] > target ) {
                colEnd = mid - 1;   // target is smaller, discard the right half
            }
            else {
                colStart = mid + 1; // target is larger, discard the left half
            }
        }
        return new int[] { -1, -1 };    // range is empty, target is not in this part of the row
    }

    /**
     * Middle index of the range start..end. Written as start + (end - start) / 2 instead of
     * (start + end) / 2 so the addition cannot overflow int for very large indexes.
     *
     * Time complexity:  O(1).
     * Space complexity: O(1).
     */
    static int getMid(int start, int end){
        return start + (end - start) / 2;
    }
}
