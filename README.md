# DSA Problems in Java

A collection of Data Structures and Algorithms problems solved in Java. Each solution is a small, self-contained class with comments explaining the approach and its time and space complexity.

## Problems

| Topic | Problem | Approach | Time | Space | Solution |
|-------|---------|----------|------|-------|----------|
| Search | Find a target in a matrix whose rows and columns are sorted | Staircase search from the top-right corner | O(n + m) | O(1) | [RowColSortedArray.java](src/Search/BinarySearch/RowColSortedArray.java) |
| Search | Find a target in a fully sorted matrix, where each row is sorted and starts above the end of the row before it | Binary search the rows on the middle column, then binary search within a row | O(log n + log m) | O(1) | [FullySortedArray.java](src/Search/BinarySearch/FullySortedArray.java) |
| Sort | Sort an array in ascending order | Bubble sort: swap adjacent out-of-order pairs so the largest remaining value moves to the end on each pass | O(n²) | O(1) | [CommonBubbleSort.java](src/Sort/BubbleSort/CommonBubbleSort.java) |
| Sort | Sort an array in ascending order | Selection sort: find the largest remaining value and swap it to the end of the unsorted part on each pass | O(n²) | O(1) | [CommonSelectionSort.java](src/Sort/SelectionSort/CommonSelectionSort.java) |
| Sort | Sort an array in ascending order | Insertion sort: take the next element and move it left into its place in the already sorted part | O(n²) | O(1) | [CommonInsertionSort.java](src/Sort/InsertionSort/CommonInsertionSort.java) |
| Sort | Sort an array holding the numbers 1 to n, each exactly once | Cyclic sort: the value v belongs at index v − 1, so swap each element straight into its correct index | O(n) | O(1) | [CommonCyclicSort.java](src/Sort/CyclicSort/CommonCyclicSort.java) |
| Sort | Find the one number missing from an array of n distinct numbers in the range 0 to n | Cyclic sort: put each value v at index v, then the first index that does not hold its own value is the answer. Also solved with the sum formula: n(n + 1) / 2 minus every element | O(n) | O(1) | [MissingNumber.java](src/Sort/CyclicSort/MissingNumber.java) |
| Sort | Find every number from 1 to n that does not appear in an array of n numbers in the range 1 to n | Cyclic sort: put each value v at index v − 1, then every index that does not hold its own value gives a missing number | O(n) | O(1) | [DisappearedNumbers.java](src/Sort/CyclicSort/DisappearedNumbers.java) |
| Sort | Find the one repeated number in an array of n + 1 numbers in the range 1 to n | Cyclic sort: put each value v at index v − 1, then the last index, which belongs to no value, holds the repeat | O(n) | O(1) | [DuplicateNumber.java](src/Sort/CyclicSort/DuplicateNumber.java) |
| Sort | Find every number that appears twice in an array of n numbers in the range 1 to n, where each number appears once or twice | Cyclic sort: put each value v at index v − 1, then every index that does not hold its own value is holding a duplicate | O(n) | O(1) | [AllDuplicates.java](src/Sort/CyclicSort/AllDuplicates.java) |

For the matrix problems, n is the number of rows and m is the number of columns. For the array problems, n is the number of elements.

## Project structure

Solutions are grouped by topic, then by technique:

```
src/
├── Search/
│   └── BinarySearch/
│       ├── FullySortedArray.java
│       └── RowColSortedArray.java
└── Sort/
    ├── BubbleSort/
    │   └── CommonBubbleSort.java
    ├── CyclicSort/
    │   ├── AllDuplicates.java
    │   ├── CommonCyclicSort.java
    │   ├── DisappearedNumbers.java
    │   ├── DuplicateNumber.java
    │   └── MissingNumber.java
    ├── InsertionSort/
    │   └── CommonInsertionSort.java
    └── SelectionSort/
        └── CommonSelectionSort.java
```

The folder path is also the Java package, so `src/Search/BinarySearch/RowColSortedArray.java` is the class `Search.BinarySearch.RowColSortedArray`.

## Running a solution

**Requirements:** JDK 25 or later. The solutions use the simplified `static void main` entry point, which older JDKs reject. The project is set up with JDK 27.

**IntelliJ IDEA:** open the project folder, open a solution file and click the run icon next to `main`.

**Command line**, from the project root:

```sh
javac -d out src/Search/BinarySearch/RowColSortedArray.java
java -cp out Search.BinarySearch.RowColSortedArray
```

For the example above this prints `[2, 3]`, the row and column of the target.

## Adding a problem

1. Create the class under `src/<Topic>/<Technique>/` with a matching `package` line.
2. Put a sample input in `main` so the solution can be run on its own.
3. Comment the solution method with the approach, time complexity and space complexity.
4. Add a row to the [Problems](#problems) table.
