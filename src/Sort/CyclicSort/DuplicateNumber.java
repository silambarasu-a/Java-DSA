package Sort.CyclicSort;

// LeetCode 287. Find the Duplicate Number
//
// Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//
// There is only one repeated number in nums, return this repeated number.
//
// You must solve the problem without modifying the array nums and using only constant extra space.
//
//Example 1:
//
//Input: nums = [1,3,4,2,2]
//
//Output: 2
//
//Example 2:
//
//Input: nums = [3,1,3,4,2]
//
//Output: 3
//
//Example 3:
//
//Input: nums = [3,3,3,3,3]
//
//Output: 3
//
//Constraints:
//
//1 <= n <= 10^5
//nums.length == n + 1
//1 <= nums[i] <= n
//All the integers in nums appear only once except for precisely one integer which appears two or more times.
//
//Follow up:
//
//How can we prove that at least one duplicate number must exist in nums?
//Can you solve the problem in linear runtime complexity?

public class DuplicateNumber {
    static void main() {
        int[] nums = { 1, 3, 4, 2, 2 };

        System.out.println("Find using cyclic sort: " + findDuplicate(nums));
    }

    // Entry point with the LeetCode method name. The work is done in cyclicSort.
    public static int findDuplicate(int[] nums) {
        return cyclicSort(nums);
    }

    /**
     * Finds the repeated number using cyclic sort. The array is rearranged in place.
     *
     * Approach: the value v belongs at index v - 1 (1 at index 0, 2 at index 1, ...).
     * The array has n + 1 elements but the values only go up to n, so the last index is not
     * the correct index of any value.
     *   1. Swap each element into its correct index. If that index already holds the same
     *      value, the element is a repeat, so it is left where it is.
     *   2. Return the element at the last index. It could not be placed, which means its
     *      correct index already holds the same value, so it is the repeated number.
     *
     * Time complexity:  O(n). Each swap puts one value at its final index and that value is
     *                   never moved again, so the number of swaps is at most the array
     *                   length, and i moves forward once per element: at most about 2n steps.
     *
     * Space complexity: O(1). The array is rearranged in place; only the index i and the
     *                   temp variable in swap are used.
     *
     * Note: the problem asks for a solution that does not modify the array. This one does
     * rearrange it, so it meets the constant-space rule but not that one.
     */
    public static int cyclicSort(int[] arr){
        int n = arr.length;     // the array length, which is the problem's n + 1
        int i = 0;

        // Step 1: put every value at its correct index
        while(i < n){
            // arr[i] belongs at index arr[i] - 1. If that index already holds the same value,
            // arr[i] is either in place or a repeat, so move on. Otherwise swap it there.
            if(arr[i] == arr[arr[i] - 1]){
                i++;
            }else{
                swap(arr, i, arr[i] - 1);   // stay on i: a new value has just arrived here
            }
        }

        // Step 2: the last index belongs to no value, so it is holding the repeat
        return arr[n-1];
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
