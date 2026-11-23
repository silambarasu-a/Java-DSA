package Sort.InsertionSort;

import java.util.Arrays;

public class CommonInsertionSort {
    static void main() {
        int[] arr = { 9 , -10, 8, 36, 9, 7, 19, 44 };
        insertionSort(arr);
        System.out.print(Arrays.toString(arr));
    }

    /**
     * Sorts the array in ascending order using insertion sort. The array is sorted in place.
     *
     * Approach: keep a sorted part at the left of the array, starting with just the first
     * element. Take the next element and move it left, swapping it with its left-hand
     * neighbour for as long as that neighbour is larger. When it stops it is in its correct
     * place, and the sorted part has grown by one.
     *
     * Time complexity:  O(n^2) for n elements in the worst and average cases. In the worst
     *                   case (array in descending order) every new element moves all the
     *                   way to the front: 1 + 2 + ... + (n - 1) = n(n - 1) / 2 comparisons
     *                   and swaps.
     *                   Best case O(n), when the array is already sorted: each new element
     *                   is compared once and the inner loop stops straight away.
     *
     * Space complexity: O(1). The sort is done in place; only the loop counters and one
     *                   temp variable for swapping are used.
     *
     * Stable: equal elements keep their original order, because an element only moves past
     * neighbours that are strictly larger.
     */
    static void insertionSort(int[] arr){

        // arr[0..i] is already sorted. Each pass inserts arr[i + 1] into it, so i stops at
        // the second-to-last index. Empty and single-element arrays never enter the loop.
        for(int i = 0; i < arr.length - 1; i++){
            // j follows the new element as it moves left through the sorted part
            for(int j = i + 1; j > 0; j--){
                if(arr[j] < arr[j-1]){
                    // left neighbour is larger: swap so the new element moves one step left
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    // left neighbour is not larger, and everything before it is smaller
                    // still, so the element is in place
                    break;
                }
            }
        }
    }
}
