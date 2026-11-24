package Sort.CyclicSort;

import java.util.ArrayList;
import java.util.List;

// LeetCode 442. Find All Duplicates in an Array
//
// Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer
// appears at most twice, return an array of all the integers that appears twice.
//
// You must write an algorithm that runs in O(n) time and uses only constant auxiliary space, excluding the space
// needed to store the output
//
//Example 1:
//
//Input: nums = [4,3,2,7,8,2,3,1]
//
//Output: [2,3]
//
//Example 2:
//
//Input: nums = [1,1,2]
//
//Output: [1]
//
//Example 3:
//
//Input: nums = [1]
//
//Output: []
//
//Constraints:
//
//n == nums.length
//1 <= n <= 10^5
//1 <= nums[i] <= n
//Each element in nums appears once or twice.

public class AllDuplicates {
    static void main() {
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };

        System.out.println("Find using cyclic sort: " + findDuplicates(nums));
    }

    // Entry point with the LeetCode method name. The work is done in cyclicSort.
    public static List<Integer> findDuplicates(int[] nums) {
        return cyclicSort(nums);
    }

    /**
     * Finds every number that appears twice in the array, using cyclic sort. The array is
     * rearranged in place.
     *
     * Approach: the value v belongs at index v - 1 (1 at index 0, 2 at index 1, ...).
     *   1. Swap each element into its correct index. If that index already holds the same
     *      value, the element is the second copy of that value, so it is left where it is.
     *   2. Scan the array. An index that does not hold its own value (i + 1) is holding one
     *      of those second copies, so the value found there appears twice. Each value
     *      appears at most twice, so each duplicate is collected exactly once.
     *
     * Time complexity:  O(n) for n elements. Each swap puts one value at its final index and
     *                   that value is never moved again, so there are at most n swaps, and
     *                   i moves forward n times: at most 2n steps for the first loop. The
     *                   second loop is one more pass.
     *
     * Space complexity: O(1) extra. The array is rearranged in place; only the index i and
     *                   the temp variable in swap are used. The returned list can hold up
     *                   to n / 2 numbers, but the problem does not count the output as
     *                   extra space.
     */
    public static List<Integer> cyclicSort(int[] arr){
        int n = arr.length;
        int i = 0;

        // Step 1: put every value at its correct index
        while(i < n){
            // arr[i] belongs at index arr[i] - 1. Swap it there unless that index already
            // holds the same value, which means arr[i] is in place or is a second copy.
            if(arr[i] != arr[arr[i] - 1]){
                swap(arr, i, arr[i] - 1);   // stay on i: a new value has just arrived here
            }else{
                i++;
            }
        }

        List<Integer> result = new ArrayList<>();

        // Step 2: every index that does not hold its own value is holding a duplicate
        for(i = 0; i < n; i++){
            if(arr[i] != i+1){
                result.add(arr[i]);
            }
        }

        return result;
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
