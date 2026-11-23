# DSA Problems in Java

A collection of Data Structures and Algorithms problems solved in Java. Each solution is a small, self-contained class with comments explaining the approach and its time and space complexity.

## Problems

| Topic | Problem | Approach | Time | Space | Solution |
|-------|---------|----------|------|-------|----------|
| Search | Find a target in a matrix whose rows and columns are sorted | Staircase search from the top-right corner | O(n + m) | O(1) | [SortedArray.java](src/Search/BinarySearch/SortedArray.java) |

## Project structure

Solutions are grouped by topic, then by technique:

```
src/
└── Search/
    └── BinarySearch/
        └── SortedArray.java
```

The folder path is also the Java package, so `src/Search/BinarySearch/SortedArray.java` is the class `Search.BinarySearch.SortedArray`.

## Running a solution

**Requirements:** JDK 25 or later. The solutions use the simplified `static void main` entry point, which older JDKs reject. The project is set up with JDK 27.

**IntelliJ IDEA:** open the project folder, open a solution file and click the run icon next to `main`.

**Command line**, from the project root:

```sh
javac -d out src/Search/BinarySearch/SortedArray.java
java -cp out Search.BinarySearch.RowColSortedArray
```

For the example above this prints `[2, 3]`, the row and column of the target.

## Adding a problem

1. Create the class under `src/<Topic>/<Technique>/` with a matching `package` line.
2. Put a sample input in `main` so the solution can be run on its own.
3. Comment the solution method with the approach, time complexity and space complexity.
4. Add a row to the [Problems](#problems) table.
