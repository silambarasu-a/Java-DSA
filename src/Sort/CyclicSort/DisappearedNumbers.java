package Sort.CyclicSort;

import java.util.ArrayList;
import java.util.List;

// LeetCode 448. Find All Numbers Disappeared in an Array
//
// Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the
// range [1, n] that do not appear in nums.
//
//Example 1:
//
//Input: nums = [4,3,2,7,8,2,3,1]
//
//Output: [5,6]
//
//Example 2:
//
//Input: nums = [1,1]
//
//Output: [2]
//
//Constraints:
//
//n == nums.length
//1 <= n <= 10^5
//1 <= nums[i] <= n
//
//Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count
//as extra space.

public class DisappearedNumbers {
    static void main() {
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };

        System.out.println("Find using cyclic sort: " + findDisappearedNumbers(nums));
    }

    // Entry point with the LeetCode method name. The work is done in cyclicSort.
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        return cyclicSort(nums);
    }

    /**
     * Finds every number from 1 to n that does not appear in the array, using cyclic sort.
     * The array is rearranged in place.
     *
     * Approach: the value v belongs at index v - 1 (1 at index 0, 2 at index 1, ...).
     *   1. Swap each element into its correct index. If that index already holds the same
     *      value, the element is a repeat, so it is left where it is.
     *   2. Scan the array. An index that does not hold its own value (i + 1) is holding a
     *      repeat instead, which means i + 1 never appeared: it is a missing number.
     *
     * Time complexity:  O(n) for n elements. Each swap puts one value at its final index and
     *                   that value is never moved again, so there are at most n swaps, and
     *                   i moves forward n times: at most 2n steps for the first loop. The
     *                   second loop is one more pass.
     *
     * Space complexity: O(1) extra. The array is rearranged in place; only the index i and
     *                   the temp variable in swap are used. The returned list can hold up
     *                   to n - 1 numbers, but the problem does not count the output as
     *                   extra space.
     */
    public static List<Integer> cyclicSort(int[] arr){
        int n = arr.length;
        int i = 0;

        // Step 1: put every value at its correct index
        while(i < n){
            // arr[i] belongs at index arr[i] - 1. If that index already holds the same value,
            // arr[i] is either in place or a repeat, so move on. Comparing the two values,
            // rather than arr[i] with i + 1, is what stops a repeat from being swapped forever.
            if(arr[i] == arr[arr[i]-1]){
                i++;
            }else {
                swap(arr, i, arr[i] - 1 );  // stay on i: a new value has just arrived here
            }
        }

        ArrayList<Integer> missingNumber = new ArrayList<>();

        // Step 2: every index that does not hold its own value gives a missing number
        for(i = 0; i<n; i++){
            if(arr[i] != i+1){
                missingNumber.add(i+1);
            }
        }

        return missingNumber;
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
