package Sort.CyclicSort;

import java.util.Arrays;

public class CommonCyclicSort {
    static void main() {
        int[] list = { 5, 1, 4, 2, 3 };
        cyclicSort(list);
        System.out.print(Arrays.toString(list));
    }

    /**
     * Sorts an array that holds the numbers 1 to n, each exactly once, where n is the array
     * length. The array is sorted in place.
     *
     * Approach: in the sorted result the value v sits at index v - 1 (1 at index 0, 2 at
     * index 1, ...), so the final position of every element is known from its value alone.
     * At each index, swap the current element straight into its correct index, and repeat
     * until the element that belongs at this index has arrived.
     *
     * Time complexity:  O(n) for n elements. Every swap puts one element in its final
     *                   position and that element is never moved again, so there are at
     *                   most n - 1 swaps in total across the whole sort. Together with the
     *                   n steps of the outer loop that is linear, even though the loops are
     *                   nested. An already sorted array needs no swaps.
     *
     * Space complexity: O(1). The sort is done in place; only the loop counter and one
     *                   temp variable for swapping are used.
     *
     * Only for the numbers 1 to n. This is not a general-purpose sort:
     *   - a value outside 1 to n has no correct index and throws ArrayIndexOutOfBoundsException
     *   - if a value is repeated, the sort finishes but the result is not fully sorted,
     *     because some other value must be missing
     */
    static void cyclicSort(int[] arr){

        for(int i = 0; i < arr.length; i++){
            // arr[i] belongs at index arr[i] - 1. Keep swapping it there until the element
            // at index i is one that is already sitting at its own correct index.
            // The check compares with arr[arr[i] - 1] instead of with i + 1 so that a
            // repeated value ends the loop, rather than swapping two equal values forever.
            // The last index (i + 1 == arr.length) is skipped: once every other element is
            // in place, the last one must be too.
            while(arr[i] != arr[arr[i] - 1] && i+1 < arr.length){
                // arr[i] is overwritten last because the first two lines use it as the index
                int temp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
