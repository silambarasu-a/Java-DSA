package Sort.SelectionSort;

import java.util.Arrays;

public class CommonSelectionSort {

    static void main() {
        int[] arr = { 9 , -10, 8, 36, 7, 19, 44 };
        selectionSort(arr);
        System.out.print(Arrays.toString(arr));
    }

    /**
     * Sorts the array in ascending order using selection sort. The array is sorted in place.
     *
     * Approach: select the largest element of the unsorted part and swap it into the last
     * position of that part. That element is now in its final place, so the sorted part
     * grows from the right and the next pass looks at one element fewer.
     *
     * Time complexity:  O(n^2) for n elements. Finding the largest element scans the whole
     *                   unsorted part, so the passes make (n - 1) + (n - 2) + ... + 1
     *                   = n(n - 1) / 2 comparisons. The input order does not change this,
     *                   so the best, average and worst cases are all O(n^2).
     *                   It makes at most n - 1 swaps, far fewer than bubble sort.
     *
     * Space complexity: O(1). The sort is done in place; only the loop counters and one
     *                   temp variable for swapping are used.
     *
     * Not stable: a swap can move an element past another element with the same value,
     * so equal elements may not keep their original order.
     */
    static void selectionSort(int[] arr){

        // i is the last index of the unsorted part arr[0..i]. It stops at 1 because a single
        // remaining element is already in place. An empty array never enters the loop.
        for(int i = arr.length - 1; i > 0; i--){
            int maxIndex = getMaxIndex(arr, 0, i);  // largest element of the unsorted part
            if(maxIndex != i){
                // move it to the end of the unsorted part (skipped when it is already there)
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }

    /**
     * Returns the index of the largest element between startIndex and endIndex (both
     * inclusive). If the largest value appears more than once, the first one is returned.
     *
     * Time complexity:  O(k), where k = endIndex - startIndex + 1 is the number of elements
     *                   scanned. Each one is looked at once.
     *
     * Space complexity: O(1).
     */
    static int getMaxIndex(int[] arr, int startIndex, int endIndex){
        int maxIndex = startIndex;  // assume the first element is the largest so far
        for(int i = startIndex + 1; i<=endIndex; i++){
            if(arr[maxIndex] < arr[i]){
                maxIndex = i;   // found a larger element
            }
        }
        return maxIndex;
    }
}
