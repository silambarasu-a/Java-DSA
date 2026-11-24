package Sort.CyclicSort;

// LeetCode 41. First Missing Positive
//
// Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
//
// You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
//
//Example 1:
//
//Input: nums = [1,2,0]
//
//Output: 3
//
//Explanation: The numbers in the range [1,2] are all in the array.
//
//Example 2:
//
//Input: nums = [3,4,-1,1]
//
//Output: 2
//
//Explanation: 1 is in the array but 2 is missing.
//
//Example 3:
//
//Input: nums = [7,8,9,11,12]
//
//Output: 1
//
//Explanation: The smallest positive integer 1 is missing.
//
//Constraints:
//
//1 <= nums.length <= 10^5
//-2^31 <= nums[i] <= 2^31 - 1

public class FirstMissingPositive {
    static void main() {
        int[] nums = { 3, 4, -1, 1 };

        System.out.println("Find using cyclic sort: " + firstMissingPositive(nums));
    }

    // Entry point with the LeetCode method name. The work is done in cyclicSort.
    public static int firstMissingPositive(int[] nums) {
        return cyclicSort(nums);
    }

    /**
     * Finds the smallest positive number that is not in the array, using cyclic sort.
     * The array is rearranged in place.
     *
     * Approach: an array of n elements can hold at most the numbers 1 to n, so the answer
     * is always between 1 and n + 1. Only the values 1 to n matter; anything else (zero,
     * negatives, values above n) cannot affect the answer and is ignored.
     *   1. Swap each value v from 1 to n into its correct index v - 1. A value is left where
     *      it is if it is out of that range, or if its correct index already holds it.
     *   2. Scan the array. The first index that does not hold its own value (i + 1) gives
     *      the answer. If every index matches, 1 to n are all present and the answer is n + 1.
     *
     * Time complexity:  O(n) for n elements. Each swap puts one value at its final index and
     *                   that value is never moved again, so there are at most n swaps, and
     *                   i moves forward n times: at most 2n steps for the first loop. The
     *                   second loop is one more pass.
     *
     * Space complexity: O(1). The array is rearranged in place; only the index i and the
     *                   temp variable in swap are used.
     */
    public static int cyclicSort(int[] arr){
        int n = arr.length;
        int i = 0;

        // Step 1: put every value from 1 to n at its correct index
        while(i < n){
            // The range checks come first, so arr[arr[i] - 1] is only read when arr[i] - 1
            // is a valid index. Comparing with the value already there stops a repeat from
            // being swapped forever.
            if(arr[i] > 0 && arr[i] <= n && arr[i] != arr[arr[i] - 1]){
                swap(arr, i, arr[i] - 1);   // stay on i: a new value has just arrived here
            }else{
                i++;    // out of range, already in place, or a repeat: leave it
            }
        }

        // Step 2: the first index that does not hold its own value is the answer
        for(i = 0; i< n; i++){
            if(arr[i] != i+1){
                return i+1;
            }
        }
        return n+1;     // 1 to n are all present, so the next number is the first one missing
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
