package Sort.CyclicSort;

import java.util.Arrays;

// LeetCode 645. Set Mismatch
//
// You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some
// error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one
// number and loss of another number.
//
// You are given an integer array nums representing the data status of this set after the error.
//
// Find the number that occurs twice and the number that is missing and return them in the form of an array.
//
//Example 1:
//
//Input: nums = [1,2,2,4]
//
//Output: [2,3]
//
//Example 2:
//
//Input: nums = [1,1]
//
//Output: [1,2]
//
//Constraints:
//
//2 <= nums.length <= 10^4
//1 <= nums[i] <= 10^4

public class SetMismatch {
    static void main() {
        int[] nums = { 1, 2, 2, 4 };

        System.out.println("Find using cyclic sort: " + Arrays.toString(findErrorNums(nums)));
    }

    // Entry point with the LeetCode method name. The work is done in cyclicSort.
    public static int[] findErrorNums(int[] nums) {
        return cyclicSort(nums);
    }

    /**
     * Finds the number that appears twice and the number that is missing, using cyclic sort.
     * Returns { duplicate, missing }. The array is rearranged in place.
     *
     * Approach: the value v belongs at index v - 1 (1 at index 0, 2 at index 1, ...).
     *   1. Swap each element into its correct index. If that index already holds the same
     *      value, the element is the second copy of that value, so it is left where it is.
     *   2. Scan the array. Exactly one index does not hold its own value (i + 1): the value
     *      sitting there is the duplicate, and i + 1, which never arrived, is the missing
     *      number.
     *
     * Time complexity:  O(n) for n elements. Each swap puts one value at its final index and
     *                   that value is never moved again, so there are at most n swaps, and
     *                   i moves forward n times: at most 2n steps for the first loop. The
     *                   second loop is one more pass.
     *
     * Space complexity: O(1). The array is rearranged in place; only the index i, the temp
     *                   variable in swap and the two-element result are used.
     */
    public static int[] cyclicSort(int[] arr){
        int n = arr.length;
        int i = 0;

        // Step 1: put every value at its correct index
        while(i < n){
            // arr[i] belongs at index arr[i] - 1. Swap it there unless that index already
            // holds the same value, which means arr[i] is in place or is the second copy.
            if(arr[i] != arr[arr[i] - 1]){
                swap(arr, i, arr[i] - 1);   // stay on i: a new value has just arrived here
            }else{
                i++;
            }
        }

        // Step 2: the index that does not hold its own value gives both answers
        for(i =0; i<n; i++){
            if(arr[i] != i + 1){
                return new int[] { arr[i], i+1 };   // { duplicate, missing }
            }
        }

        return new int[] {};    // every value is in place: nothing is duplicated or missing
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
