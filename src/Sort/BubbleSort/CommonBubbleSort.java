package Sort.BubbleSort;

import java.util.Arrays;

public class CommonBubbleSort {
    static void main() {
        int[] list = { 3, 1, 5, 4, 2, 10, 20, 4, 2 };

        System.out.print(Arrays.toString(bubbleSort(list)));
    }

    /**
     * Sorts the array in ascending order using bubble sort. The array is sorted in place
     * and the same array is returned.
     *
     * Approach: walk through the array comparing each element with its right-hand neighbour,
     * and swap the two if they are in the wrong order. One full pass moves ("bubbles") the
     * largest remaining element to the end, so the sorted part grows from the right and
     * each pass can stop one position earlier than the one before.
     *
     * Time complexity:  O(n^2) for n elements. The passes make (n - 1) + (n - 2) + ... + 1
     *                   = n(n - 1) / 2 comparisons. This version always makes all of them,
     *                   so the best, average and worst cases are all O(n^2).
     *                   (Stopping as soon as a pass makes no swaps would give a best case
     *                   of O(n) for an already sorted array.)
     *
     * Space complexity: O(1). The sort is done in place; only the loop counters and one
     *                   temp variable for swapping are used.
     *
     * Stable: equal elements keep their original order, because only adjacent elements
     * that are strictly out of order are swapped.
     */
    static int[] bubbleSort(int[] arr){

        // 0 or 1 elements: already sorted
        if(arr.length < 2) {
            return arr;
        }

        // i is the last index of the unsorted part. After each pass arr[i] holds its final
        // value, so the unsorted part shrinks by one.
        for(int i = arr.length -1 ; i >= 0; i--){
            // One pass: compare every adjacent pair in arr[0..i]
            for(int j = 0; j < i; j++ ){
                if(arr[j] > arr[j + 1]){
                    // left neighbour is larger: swap so the larger value moves right
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
