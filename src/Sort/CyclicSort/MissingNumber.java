package Sort.CyclicSort;

// Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is
// missing from the array.
//Example 1:
//
//Input: nums = [3,0,1]
//
//Output: 2
//
//Explanation:
//
//n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
//
//Example 2:
//
//Input: nums = [0,1]
//
//Output: 2
//
//Explanation:
//
//n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
//
//Example 3:
//
//Input: nums = [9,6,4,2,3,5,7,0,1]
//
//Output: 8
//
//Explanation:
//
//n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

public class MissingNumber {
    static void main() {
        int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };

        System.out.println("Find using cyclic sort: " + cyclicSort(nums));

        System.out.println("Find using maths formula: " + findByAppliedMaths(nums));


    }

    /**
     * Finds the missing number with the sum formula. Does not change the array.
     *
     * Approach: the numbers 0 to n add up to n(n + 1) / 2. Subtract every element of the
     * array from that total, and what is left is the one number that was not there.
     *
     * Time complexity:  O(n) for n elements. One pass to subtract each element.
     *
     * Space complexity: O(1). Only the running total is stored.
     *
     * The total is kept in a long because n * (n + 1) no longer fits in an int once the
     * array has 46,341 or more elements, and the answer can then come out wrong.
     */
    public static int findByAppliedMaths(int[] arr){
        int n = arr.length;
        long sum = (long) n * (n+1)/2;  // sum of 0 to n

        for(int element: arr){
            sum = sum - element;
        }

        return (int) sum;   // what is left is the missing number, which is at most n
    }


    /**
     * Finds the missing number with cyclic sort. The array is rearranged in place.
     *
     * Approach: the value v belongs at index v (0 at index 0, 1 at index 1, ...).
     *   1. Swap each element into its correct index. The value n has no index of its own,
     *      because the last index is n - 1, so it is left wherever it is.
     *   2. Scan the array. The first index that does not hold its own value is the missing
     *      number. If every index matches, the array holds 0 to n - 1 and n is missing.
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

        // for(int i = 0; i< arr.length; i++){
        //     while(arr[i] != i && arr[i] < arr.length){
        //        if((arr[i] != arr[arr[i]]) ){
        //             swap(arr, i, arr[i]);;
        //         }
        //     }
        // }

        int i = 0;

        // Step 1: put every value at its correct index
        while(i < arr.length){
            // Swap arr[i] to index arr[i], unless it is n (no index for it) or that index
            // already holds the same value. Comparing with arr[arr[i]] instead of with i
            // means a repeated value moves on, rather than swapping two equal values forever.
            if(arr[i]< arr.length && arr[i] != arr[arr[i]]){
                swap(arr, i, arr[i]);   // stay on i: a new value has just arrived here
            }else{
                i++;
            }
        }

        // Step 2: the first index that does not hold its own value is the missing number
        for(i = 0; i < arr.length; i++){
            if(arr[i] != i) return i;
        }
        return arr.length;  // 0 to n - 1 are all present, so n is the missing one
    }

    // Swaps the elements at indexes first and second. Time O(1), space O(1).
    public static void swap(int[]arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
